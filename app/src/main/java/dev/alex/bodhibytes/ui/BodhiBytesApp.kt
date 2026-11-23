package dev.alex.bodhibytes.ui

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.view.WindowManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alex.bodhibytes.R
import dev.alex.bodhibytes.data.KoanRepository
import dev.alex.bodhibytes.data.LessonRepository
import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Quiz
import dev.alex.santideva.repository.SantidevaRepository
import kotlinx.coroutines.launch

enum class ReadingFontSize(
    val key: String,
    val label: String,
    val bodyFontSize: TextUnit,
    val titleFontSize: TextUnit
) {
    SMALL("small", "Small", 14.sp, 18.sp),
    DEFAULT("default", "Default", 16.sp, 22.sp),
    LARGE("large", "Large", 19.sp, 25.sp),
    EXTRA_LARGE("extra_large", "Extra Large", 22.sp, 28.sp);

    companion object {
        fun fromKey(key: String?): ReadingFontSize {
            return entries.find { it.key == key } ?: DEFAULT
        }
    }
}

enum class ReadingLineSpacing(
    val key: String,
    val label: String,
    val lineHeight: TextUnit,
    val paragraphSpacing: Int
) {
    COMPACT("compact", "Compact", 24.sp, 12),
    COMFORTABLE("comfortable", "Comfortable", 30.sp, 16),
    SPACIOUS("spacious", "Spacious", 36.sp, 22);

    companion object {
        fun fromKey(key: String?): ReadingLineSpacing {
            return entries.find { it.key == key } ?: COMFORTABLE
        }
    }
}

data class ReadingPosition(
    val index: Int = 0,
    val offset: Int = 0
) {
    fun hasProgress(): Boolean = index > 0 || offset > 0

    fun encode(): String = "$index:$offset"

    companion object {
        fun decode(raw: String?): ReadingPosition {
            if (raw.isNullOrBlank()) return ReadingPosition()
            val parts = raw.split(":")
            if (parts.size != 2) return ReadingPosition()
            val index = parts[0].toIntOrNull()?.coerceAtLeast(0) ?: 0
            val offset = parts[1].toIntOrNull()?.coerceAtLeast(0) ?: 0
            return ReadingPosition(index = index, offset = offset)
        }
    }
}

sealed interface AppScreen {
    object List : AppScreen
    data class Detail(val lesson: Lesson) : AppScreen
    data class QuizScreenState(val quiz: Quiz, val lesson: Lesson) : AppScreen
    object Bookmarks : AppScreen
    object Settings : AppScreen
    object Feedback : AppScreen
    object About : AppScreen
}

data class LessonModule(
    val title: String,
    val lessons: List<Lesson>,
    val summary: String = ""
)

sealed interface HomeItem {
    data class RegularModule(val module: LessonModule) : HomeItem
    data class ParentGroup(val title: String, val subModules: List<LessonModule>) : HomeItem
    data class StandaloneLesson(val lesson: Lesson) : HomeItem
}

private fun buildBriefSummary(content: String, fallbackTitle: String, maxChars: Int = 150): String {
    val normalized = content
        .lineSequence()
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .joinToString(" ")
        .replace(Regex("\\s+"), " ")
        .trim()

    if (normalized.isEmpty()) return fallbackTitle
    if (normalized.length <= maxChars) return normalized

    val truncated = normalized.take(maxChars)
    val lastSpace = truncated.lastIndexOf(' ')
    val cleanCut = if (lastSpace > 60) truncated.substring(0, lastSpace) else truncated
    return "$cleanCut…"
}

private fun buildModuleSummary(
    title: String,
    lessons: List<Lesson>,
    explicitSummary: String = ""
): String {
    if (explicitSummary.isNotBlank()) return explicitSummary
    if (lessons.isEmpty()) return "No lessons in $title yet."

    if (title == "A Guide to the Bodhisattva's Way of Life") {
        return "Śāntideva's classic Mahāyāna guide on cultivating bodhicitta, ethical conduct, patience, meditative concentration, and wisdom."
    }

    val countLabel = if (lessons.size == 1) "1 reading" else "${lessons.size} readings"
    return "$countLabel in $title."
}

private fun loadReadingPositions(prefs: android.content.SharedPreferences): Map<Int, ReadingPosition> {
    val rawEntries = prefs.getStringSet("reading_positions", emptySet()) ?: emptySet()
    val result = mutableMapOf<Int, ReadingPosition>()
    for (entry in rawEntries) {
        val parts = entry.split("=", limit = 2)
        if (parts.size == 2) {
            val lessonId = parts[0].toIntOrNull()
            if (lessonId != null) {
                result[lessonId] = ReadingPosition.decode(parts[1])
            }
        }
    }
    return result
}

private fun saveReadingPositions(
    prefs: android.content.SharedPreferences,
    positions: Map<Int, ReadingPosition>
) {
    val serialized = positions
        .filterValues { it.hasProgress() }
        .map { (lessonId, pos) -> "$lessonId=${pos.encode()}" }
        .toSet()
    prefs.edit().putStringSet("reading_positions", serialized).apply()
}

@Composable
private fun ExpandChevronIcon(
    expanded: Boolean,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "expand_chevron_rotation"
    )
    Canvas(
        modifier = modifier
            .size(24.dp)
            .graphicsLayer { rotationZ = rotation }
            .semantics { this.contentDescription = contentDescription }
    ) {
        val strokeWidth = 2.2.dp.toPx()
        val horizontalInset = size.width * 0.26f
        val topY = size.height * 0.36f
        val bottomY = size.height * 0.64f
        val midX = size.width * 0.5f
        val rightX = size.width - horizontalInset

        drawLine(
            color = tint,
            start = Offset(horizontalInset, topY),
            end = Offset(midX, bottomY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(midX, bottomY),
            end = Offset(rightX, topY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodhiBytesApp() {
    val context = LocalContext.current
    val activity = context as? Activity
    var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.List) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Persistent state preservation across navigation pops/pushes
    var lessonListScrollIndex by remember { mutableStateOf(0) }
    var lessonListScrollOffset by remember { mutableStateOf(0) }
    var lessonListExpandedKeys by remember { mutableStateOf(emptySet<String>()) }

    var bookmarksScrollIndex by remember { mutableStateOf(0) }
    var bookmarksScrollOffset by remember { mutableStateOf(0) }

    // Initialize SharedPreferences for persistent storage (bookmarks, reading positions, and preferences)
    val sharedPreferences = remember {
        context.getSharedPreferences("bodhibytes_prefs", android.content.Context.MODE_PRIVATE)
    }

    // Load preferences
    var isDarkMode by remember {
        mutableStateOf(sharedPreferences.getBoolean("dark_mode", false))
    }
    var readingFontSize by remember {
        mutableStateOf(ReadingFontSize.fromKey(sharedPreferences.getString("reading_font_size", ReadingFontSize.DEFAULT.key)))
    }
    var readingLineSpacing by remember {
        mutableStateOf(ReadingLineSpacing.fromKey(sharedPreferences.getString("reading_line_spacing", ReadingLineSpacing.COMFORTABLE.key)))
    }
    var keepScreenAwake by remember {
        mutableStateOf(sharedPreferences.getBoolean("keep_screen_awake", true))
    }

    // Load saved bookmarks from local storage on startup
    var bookmarkedLessonIds by remember {
        val savedStringSet = sharedPreferences.getStringSet("bookmarked_ids", emptySet()) ?: emptySet()
        mutableStateOf(savedStringSet.mapNotNull { it.toIntOrNull() }.toSet())
    }

    // Load saved reading positions per lesson
    var readingPositions by remember {
        mutableStateOf(loadReadingPositions(sharedPreferences))
    }

    // Track most recently opened lesson for quick resumption on Home
    var lastOpenedLessonId by remember {
        val savedId = sharedPreferences.getInt("last_opened_lesson_id", -1)
        mutableStateOf(if (savedId != -1) savedId else null)
    }

    val recordLastOpenedLesson: (Int) -> Unit = { lessonId ->
        if (lastOpenedLessonId != lessonId) {
            lastOpenedLessonId = lessonId
            sharedPreferences.edit().putInt("last_opened_lesson_id", lessonId).apply()
        }
    }

    val updateReadingPosition: (Int, ReadingPosition) -> Unit = { lessonId, position ->
        val current = readingPositions[lessonId] ?: ReadingPosition()
        if (current != position) {
            val updated = if (position.hasProgress()) {
                readingPositions + (lessonId to position)
            } else {
                readingPositions - lessonId
            }
            readingPositions = updated
            saveReadingPositions(sharedPreferences, updated)
        }
    }

    val allLessons = remember { LessonRepository.getCurriculum() }
    val allKoans = remember { KoanRepository.getKoans() }

    val santidevaLessons = remember {
        SantidevaRepository.generalReadingModules.flatMapIndexed { moduleIndex, module ->
            module.lessons.mapIndexed { lessonIndex, sLesson ->
                Lesson(
                    id = 300 + (moduleIndex * 100) + lessonIndex + 1,
                    title = sLesson.title,
                    summary = buildBriefSummary(sLesson.content, sLesson.title),
                    content = sLesson.content,
                    quiz = null
                )
            }
        }
    }

    val orderedLessons = remember(allLessons, allKoans, santidevaLessons) {
        buildList {
            allLessons.find { it.id == 1 }?.let { add(it) }
            addAll(allLessons.filter { it.id in 2..8 })
            addAll(allLessons.filter { it.id in 15..23 })
            allLessons.find { it.id == 31 }?.let { add(it) }
            addAll(allLessons.filter { it.id in 24..30 })
            addAll(allLessons.filter { it.id in 9..14 })
            addAll(allKoans)
            addAll(santidevaLessons)
        }
    }

    val continueReadingLesson = remember(lastOpenedLessonId, readingPositions, orderedLessons) {
        val lastLesson = lastOpenedLessonId?.let { id -> orderedLessons.find { it.id == id } }
        if (lastLesson != null && readingPositions[lastLesson.id]?.hasProgress() == true) {
            return@remember lastLesson
        }
        orderedLessons.firstOrNull { lesson ->
            readingPositions[lesson.id]?.hasProgress() == true
        }
    }

    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.ic_launcher),
                            contentDescription = "App Icon",
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "BodhiBytes",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home / Curriculum") },
                        selected = currentScreen is AppScreen.List,
                        onClick = {
                            scope.launch { drawerState.close() }
                            currentScreen = AppScreen.List
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Bookmarks") },
                        label = { Text("Bookmarks") },
                        selected = currentScreen is AppScreen.Bookmarks,
                        onClick = {
                            scope.launch { drawerState.close() }
                            currentScreen = AppScreen.Bookmarks
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                        label = { Text("Settings") },
                        selected = currentScreen is AppScreen.Settings,
                        onClick = {
                            scope.launch { drawerState.close() }
                            currentScreen = AppScreen.Settings
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Email, contentDescription = "Feedback") },
                        label = { Text("Feedback & Support") },
                        selected = currentScreen is AppScreen.Feedback,
                        onClick = {
                            scope.launch { drawerState.close() }
                            currentScreen = AppScreen.Feedback
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Info, contentDescription = "About") },
                        label = { Text("About") },
                        selected = currentScreen is AppScreen.About,
                        onClick = {
                            scope.launch { drawerState.close() }
                            currentScreen = AppScreen.About
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Exit") },
                        label = { Text("Exit") },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                activity?.finishAffinity()
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        ) {
            when (val screen = currentScreen) {
                is AppScreen.List -> {
                    LessonListScreen(
                        initialIndex = lessonListScrollIndex,
                        initialOffset = lessonListScrollOffset,
                        expandedKeys = lessonListExpandedKeys,
                        readingPositions = readingPositions,
                        continueReadingLesson = continueReadingLesson,
                        onExpandedKeysChanged = { lessonListExpandedKeys = it },
                        onScrollChanged = { index, offset ->
                            lessonListScrollIndex = index
                            lessonListScrollOffset = offset
                        },
                        onLessonSelected = { lesson ->
                            recordLastOpenedLesson(lesson.id)
                            currentScreen = AppScreen.Detail(lesson)
                        },
                        onMenuClick = { scope.launch { drawerState.open() } }
                    )
                }
                is AppScreen.Detail -> {
                    LaunchedEffect(screen.lesson.id) {
                        recordLastOpenedLesson(screen.lesson.id)
                    }
                    val savedPosition = readingPositions[screen.lesson.id] ?: ReadingPosition()
                    LessonDetailScreen(
                        lesson = screen.lesson,
                        isBookmarked = bookmarkedLessonIds.contains(screen.lesson.id),
                        savedReadingPosition = savedPosition,
                        readingFontSize = readingFontSize,
                        readingLineSpacing = readingLineSpacing,
                        keepScreenAwake = keepScreenAwake,
                        onReadingPositionChanged = { position ->
                            updateReadingPosition(screen.lesson.id, position)
                        },
                        onBookmarkClick = {
                            bookmarkedLessonIds = if (bookmarkedLessonIds.contains(screen.lesson.id)) {
                                bookmarkedLessonIds - screen.lesson.id
                            } else {
                                bookmarkedLessonIds + screen.lesson.id
                            }
                            sharedPreferences.edit().putStringSet(
                                "bookmarked_ids",
                                bookmarkedLessonIds.map { it.toString() }.toSet()
                            ).apply()
                        },
                        onBackClick = { currentScreen = AppScreen.List },
                        onQuizClick = {
                            screen.lesson.quiz?.let { quiz ->
                                currentScreen = AppScreen.QuizScreenState(quiz, screen.lesson)
                            }
                        }
                    )
                }
                is AppScreen.QuizScreenState -> {
                    val currentIndex = orderedLessons.indexOfFirst { it.id == screen.lesson.id }
                    val nextLesson = if (currentIndex != -1 && currentIndex + 1 < orderedLessons.size) {
                        orderedLessons[currentIndex + 1]
                    } else {
                        null
                    }

                    QuizScreen(
                        quiz = screen.quiz,
                        lesson = screen.lesson,
                        nextLesson = nextLesson,
                        onBackClick = { currentScreen = AppScreen.Detail(screen.lesson) },
                        onHomeClick = { currentScreen = AppScreen.List },
                        onNextLessonClick = { targetLesson ->
                            recordLastOpenedLesson(targetLesson.id)
                            currentScreen = AppScreen.Detail(targetLesson)
                        }
                    )
                }
                is AppScreen.Bookmarks -> {
                    BookmarksScreen(
                        bookmarkedIds = bookmarkedLessonIds,
                        readingPositions = readingPositions,
                        allLessons = orderedLessons,
                        initialIndex = bookmarksScrollIndex,
                        initialOffset = bookmarksScrollOffset,
                        onScrollChanged = { index, offset ->
                            bookmarksScrollIndex = index
                            bookmarksScrollOffset = offset
                        },
                        onLessonSelected = { lesson ->
                            recordLastOpenedLesson(lesson.id)
                            currentScreen = AppScreen.Detail(lesson)
                        },
                        onBackClick = { currentScreen = AppScreen.List }
                    )
                }
                is AppScreen.Settings -> {
                    SettingsScreen(
                        isDarkMode = isDarkMode,
                        readingFontSize = readingFontSize,
                        readingLineSpacing = readingLineSpacing,
                        keepScreenAwake = keepScreenAwake,
                        onDarkModeChanged = { enabled ->
                            isDarkMode = enabled
                            sharedPreferences.edit().putBoolean("dark_mode", enabled).apply()
                        },
                        onReadingFontSizeChanged = { size ->
                            readingFontSize = size
                            sharedPreferences.edit().putString("reading_font_size", size.key).apply()
                        },
                        onReadingLineSpacingChanged = { spacing ->
                            readingLineSpacing = spacing
                            sharedPreferences.edit().putString("reading_line_spacing", spacing.key).apply()
                        },
                        onKeepScreenAwakeChanged = { enabled ->
                            keepScreenAwake = enabled
                            sharedPreferences.edit().putBoolean("keep_screen_awake", enabled).apply()
                        },
                        onBackClick = { currentScreen = AppScreen.List }
                    )
                }
                is AppScreen.Feedback -> {
                    FeedbackScreen(onBackClick = { currentScreen = AppScreen.List })
                }
                is AppScreen.About -> {
                    AboutScreen(onBackClick = { currentScreen = AppScreen.List })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonListScreen(
    initialIndex: Int,
    initialOffset: Int,
    expandedKeys: Set<String>,
    readingPositions: Map<Int, ReadingPosition> = emptyMap(),
    continueReadingLesson: Lesson? = null,
    onExpandedKeysChanged: (Set<String>) -> Unit,
    onScrollChanged: (Int, Int) -> Unit,
    onLessonSelected: (Lesson) -> Unit,
    onMenuClick: () -> Unit
) {
    val allLessons = remember { LessonRepository.getCurriculum() }
    val allKoans = remember { KoanRepository.getKoans() }

    val welcomeLesson = remember {
        Lesson(
            id = 0,
            title = "Welcome to BodhiBytes",
            summary = "Buddhist teachings, foundational terms, Zen koans, and classic readings.",
            content = "Buddhist teachings, foundational terms, Zen koans, and classic readings.",
            quiz = null
        )
    }

    val santidevaModules = remember {
        SantidevaRepository.generalReadingModules.mapIndexed { moduleIndex, sModule ->
            LessonModule(
                title = sModule.title,
                lessons = sModule.lessons.mapIndexed { lessonIndex, sLesson ->
                    Lesson(
                        id = 300 + (moduleIndex * 100) + lessonIndex + 1,
                        title = sLesson.title,
                        summary = buildBriefSummary(sLesson.content, sLesson.title),
                        content = sLesson.content,
                        quiz = null
                    )
                }
            )
        }
    }

    val wordsAndMeaningsLesson = remember(allLessons) { allLessons.find { it.id == 1 } }
    val threeAspectsLesson = remember(allLessons) { allLessons.find { it.id == 31 } }

    val homeItems = remember(allLessons, allKoans, santidevaModules, wordsAndMeaningsLesson, threeAspectsLesson) {
        buildList {
            val pathLessons = buildList {
                if (wordsAndMeaningsLesson != null) {
                    add(wordsAndMeaningsLesson)
                }
                addAll(allLessons.filter { it.id in 2..8 })
                addAll(allLessons.filter { it.id in 15..23 })
                if (threeAspectsLesson != null) {
                    add(threeAspectsLesson)
                }
            }
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "The Path",
                        lessons = pathLessons,
                        summary = "Foundational terms, stages of the path, and core principles guiding practice."
                    )
                )
            )
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "Compassion (Bodhicitta)",
                        lessons = allLessons.filter { it.id in 24..30 },
                        summary = "Cultivating universal compassion and the awakening mind for the benefit of all beings."
                    )
                )
            )
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "Wisdom (Emptiness)",
                        lessons = allLessons.filter { it.id in 9..14 },
                        summary = "Insight into emptiness, interdependent origination, and seeing reality clearly."
                    )
                )
            )
            val generalReadingSubModules = listOf(
                LessonModule(
                    title = "Zen Koans (101)",
                    lessons = allKoans,
                    summary = "Classic Zen koans for contemplative inquiry and non-conceptual insight."
                )
            ) + santidevaModules.map { sMod ->
                sMod.copy(
                    summary = buildModuleSummary(sMod.title, sMod.lessons, sMod.summary)
                )
            }
            add(
                HomeItem.ParentGroup(
                    title = "General Reading",
                    subModules = generalReadingSubModules
                )
            )
        }
    }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = initialIndex,
        initialFirstVisibleItemScrollOffset = initialOffset
    )

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset }
            .collect { (index, offset) ->
                onScrollChanged(index, offset)
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.ic_launcher),
                            contentDescription = "App Icon",
                            modifier = Modifier.size(32.dp)
                        )
                        Text("BodhiBytes", style = MaterialTheme.typography.titleLarge)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Open Navigation Drawer")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item(key = "welcome_lesson_card") {
                WelcomeCard(lesson = welcomeLesson)
            }

            if (continueReadingLesson != null) {
                item(key = "continue_reading_card") {
                    ContinueReadingCard(
                        lesson = continueReadingLesson,
                        onResumeClick = { onLessonSelected(continueReadingLesson) }
                    )
                }
            }

            homeItems.forEachIndexed { index, item ->
                when (item) {
                    is HomeItem.RegularModule -> {
                        val module = item.module
                        val key = "regular_$index"
                        val isExpanded = expandedKeys.contains(key)
                        val moduleSummary = buildModuleSummary(module.title, module.lessons, module.summary)

                        item(key = key) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onExpandedKeysChanged(
                                            if (isExpanded) expandedKeys - key else expandedKeys + key
                                        )
                                    },
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.42f)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(end = 8.dp)
                                    ) {
                                        Text(
                                            text = module.title,
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = moduleSummary,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 3,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    ExpandChevronIcon(
                                        expanded = isExpanded,
                                        contentDescription = if (isExpanded) "Collapse Module" else "Expand Module",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        if (isExpanded) {
                            items(module.lessons, key = { "lesson_${it.id}" }) { lesson ->
                                Box(modifier = Modifier.padding(start = 12.dp)) {
                                    LessonCard(
                                        lesson = lesson,
                                        hasSavedPosition = readingPositions[lesson.id]?.hasProgress() == true,
                                        showSummary = true,
                                        isExpandedLesson = true,
                                        onClick = { onLessonSelected(lesson) }
                                    )
                                }
                            }
                        }
                    }
                    is HomeItem.StandaloneLesson -> {
                        val lesson = item.lesson
                        val cardSummary = lesson.summary.ifBlank {
                            buildBriefSummary(lesson.content, lesson.title)
                        }
                        item(key = "standalone_lesson_${lesson.id}") {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onLessonSelected(lesson) },
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.42f)
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = lesson.title,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                    if (cardSummary.isNotBlank()) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = cardSummary,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }
                    }
                    is HomeItem.ParentGroup -> {
                        val groupKey = "group_$index"
                        val isGroupExpanded = expandedKeys.contains(groupKey)
                        val allGroupLessons = item.subModules.flatMap { it.lessons }
                        val groupSummary = buildAnnotatedString {
                            append("${item.subModules.size} collections (${allGroupLessons.size} readings):\n")
                            withStyle(
                                style = ParagraphStyle(
                                    textIndent = TextIndent(firstLine = 16.sp, restLine = 16.sp)
                                )
                            ) {
                                append(item.subModules.joinToString("\n") { it.title })
                            }
                        }

                        item(key = groupKey) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onExpandedKeysChanged(
                                            if (isGroupExpanded) expandedKeys - groupKey else expandedKeys + groupKey
                                        )
                                    },
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.42f)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = item.title,
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = groupSummary,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            overflow = TextOverflow.Clip
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    ExpandChevronIcon(
                                        expanded = isGroupExpanded,
                                        contentDescription = if (isGroupExpanded) "Collapse Group" else "Expand Group",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        if (isGroupExpanded) {
                            item.subModules.forEachIndexed { subIndex, subModule ->
                                val subKey = "submodule_${index}_$subIndex"
                                val isSubExpanded = expandedKeys.contains(subKey)
                                val subSummary = buildModuleSummary(subModule.title, subModule.lessons, subModule.summary)
                                val showLessonSummaries = subModule.title != "A Guide to the Bodhisattva's Way of Life"

                                item(key = subKey) {
                                    Box(modifier = Modifier.padding(start = 12.dp)) {
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    onExpandedKeysChanged(
                                                        if (isSubExpanded) expandedKeys - subKey else expandedKeys + subKey
                                                    )
                                                },
                                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.26f)
                                            )
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(
                                                        text = subModule.title,
                                                        style = MaterialTheme.typography.titleMedium,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Spacer(modifier = Modifier.height(4.dp))
                                                    Text(
                                                        text = subSummary,
                                                        style = MaterialTheme.typography.bodyMedium,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(8.dp))
                                                ExpandChevronIcon(
                                                    expanded = isSubExpanded,
                                                    contentDescription = if (isSubExpanded) "Collapse SubModule" else "Expand SubModule",
                                                    tint = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        }
                                    }
                                }

                                if (isSubExpanded) {
                                    items(subModule.lessons, key = { "sub_lesson_${it.id}" }) { lesson ->
                                        Box(modifier = Modifier.padding(start = 24.dp)) {
                                            LessonCard(
                                                lesson = lesson,
                                                hasSavedPosition = readingPositions[lesson.id]?.hasProgress() == true,
                                                showSummary = showLessonSummaries,
                                                isExpandedLesson = true,
                                                onClick = { onLessonSelected(lesson) }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContinueReadingCard(
    lesson: Lesson,
    onResumeClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onResumeClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CONTINUE READING",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                FilledTonalButton(
                    onClick = onResumeClick,
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(
                        text = "Resume",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        softWrap = false
                    )
                }
            }
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    bookmarkedIds: Set<Int>,
    readingPositions: Map<Int, ReadingPosition> = emptyMap(),
    allLessons: List<Lesson>,
    initialIndex: Int,
    initialOffset: Int,
    onScrollChanged: (Int, Int) -> Unit,
    onLessonSelected: (Lesson) -> Unit,
    onBackClick: () -> Unit
) {
    val bookmarkedLessons = remember(bookmarkedIds, allLessons) {
        allLessons.filter { bookmarkedIds.contains(it.id) }
    }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = initialIndex,
        initialFirstVisibleItemScrollOffset = initialOffset
    )

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset }
            .collect { (index, offset) ->
                onScrollChanged(index, offset)
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bookmarks") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (bookmarkedLessons.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No bookmarked lessons yet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(bookmarkedLessons, key = { it.id }) { lesson ->
                    LessonCard(
                        lesson = lesson,
                        hasSavedPosition = readingPositions[lesson.id]?.hasProgress() == true,
                        showSummary = true,
                        isExpandedLesson = false,
                        onClick = { onLessonSelected(lesson) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
    isDarkMode: Boolean,
    readingFontSize: ReadingFontSize,
    readingLineSpacing: ReadingLineSpacing,
    keepScreenAwake: Boolean,
    onDarkModeChanged: (Boolean) -> Unit,
    onReadingFontSizeChanged: (ReadingFontSize) -> Unit,
    onReadingLineSpacingChanged: (ReadingLineSpacing) -> Unit,
    onKeepScreenAwakeChanged: (Boolean) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    text = "Appearance",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDarkModeChanged(!isDarkMode) }
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Dark Mode",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Use dark theme across the application",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { onDarkModeChanged(it) }
                    )
                }
            }

            item {
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Reading & Comfort",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(12.dp))

                // 1. Reading Font Size
                Text(
                    text = "Reading Font Size",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Adjust text size for lesson and koan readings",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ReadingFontSize.entries.forEach { sizeOption ->
                        FilterChip(
                            selected = readingFontSize == sizeOption,
                            onClick = { onReadingFontSizeChanged(sizeOption) },
                            label = { Text(sizeOption.label) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 2. Line Spacing
                Text(
                    text = "Line Spacing",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Control line height and spacing between paragraphs",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ReadingLineSpacing.entries.forEach { spacingOption ->
                        FilterChip(
                            selected = readingLineSpacing == spacingOption,
                            onClick = { onReadingLineSpacingChanged(spacingOption) },
                            label = { Text(spacingOption.label) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 3. Keep Screen Awake While Reading
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onKeepScreenAwakeChanged(!keepScreenAwake) }
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Keep Screen Awake While Reading",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Prevent display sleep while viewing a lesson or koan",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = keepScreenAwake,
                        onCheckedChange = { onKeepScreenAwakeChanged(it) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(onBackClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feedback & Support") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "We value your feedback!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "If you found a bug or have suggestions to improve BodhiBytes, please feel free to open an issue or pull request on GitHub.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { uriHandler.openUri("https://github.com/aekdev-creator/BodhiBytes/issues") }) {
                Text("Open GitHub Issues")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val versionName = remember(context) {
        val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.PackageInfoFlags.of(0)
            )
        } else {
            @Suppress("DEPRECATION")
            context.packageManager.getPackageInfo(context.packageName, 0)
        }
        packageInfo.versionName ?: "Unknown"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About BodhiBytes") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher),
                contentDescription = "App Icon",
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "BodhiBytes",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Version $versionName",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "BodhiBytes is your companion for exploring foundational Buddhist teachings, traditional terminology, Zen koans, and insights into wisdom and compassion.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Developed by Alexander Kelly",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Built with Jetpack Compose & Kotlin",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            TextButton(onClick = { uriHandler.openUri("https://github.com/aekdev-creator/BodhiBytes") }) {
                Text("View Source Code on GitHub")
            }

            Text(
                text = "Privacy Policy: BodhiBytes does not collect, store, or share any personal user data. All lesson progress and local states remain entirely on your device.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "© 2026 Alexander Kelly. All rights reserved.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun WelcomeCard(
    lesson: Lesson
) {
    val displaySummary = lesson.summary.ifBlank {
        buildBriefSummary(lesson.content, lesson.title)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.42f),
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            if (displaySummary.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = displaySummary,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
                )
            }
        }
    }
}

@Composable
private fun LessonMetadataBadge(
    label: String,
    containerColor: Color,
    contentColor: Color
) {
    Box(
        modifier = Modifier
            .background(color = containerColor, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = contentColor,
            maxLines = 1,
            softWrap = false
        )
    }
}

@Composable
fun LessonCard(
    lesson: Lesson,
    hasSavedPosition: Boolean = false,
    showSummary: Boolean = true,
    isExpandedLesson: Boolean = false,
    onClick: () -> Unit
) {
    val displaySummary = lesson.summary.ifBlank {
        buildBriefSummary(lesson.content, lesson.title)
    }
    val isKoan = lesson.id in 101..201
    val hasQuiz = lesson.quiz != null

    if (isExpandedLesson) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        if (hasSavedPosition) {
                            LessonMetadataBadge(
                                label = "Resumed",
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                        if (hasQuiz) {
                            LessonMetadataBadge(
                                label = "Quiz",
                                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        } else if (isKoan) {
                            LessonMetadataBadge(
                                label = "Koan",
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                if (showSummary && displaySummary.isNotBlank()) {
                    Text(
                        text = displaySummary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            HorizontalDivider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.55f)
            )
        }
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        if (hasSavedPosition) {
                            LessonMetadataBadge(
                                label = "Resumed",
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                        if (hasQuiz) {
                            LessonMetadataBadge(
                                label = "Quiz",
                                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        } else if (isKoan) {
                            LessonMetadataBadge(
                                label = "Koan",
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                if (showSummary && displaySummary.isNotBlank()) {
                    Text(
                        text = displaySummary,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    lesson: Lesson,
    isBookmarked: Boolean,
    savedReadingPosition: ReadingPosition = ReadingPosition(),
    readingFontSize: ReadingFontSize = ReadingFontSize.DEFAULT,
    readingLineSpacing: ReadingLineSpacing = ReadingLineSpacing.COMFORTABLE,
    keepScreenAwake: Boolean = true,
    onReadingPositionChanged: (ReadingPosition) -> Unit = {},
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
    onQuizClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    DisposableEffect(keepScreenAwake) {
        val window = (context as? Activity)?.window
        if (keepScreenAwake) {
            window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
        onDispose {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    val paragraphs = remember(lesson.content) {
        lesson.content
            .split(Regex("\\n\\s*\\n"))
            .map { it.trim() }
            .filter { it.isNotEmpty() }
    }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = savedReadingPosition.index,
        initialFirstVisibleItemScrollOffset = savedReadingPosition.offset
    )

    var resumedFromSaved by remember(lesson.id) {
        mutableStateOf(savedReadingPosition.hasProgress())
    }

    LaunchedEffect(lesson.id, listState) {
        snapshotFlow { listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset }
            .collect { (index, offset) ->
                if (index > 0 || offset > 0) {
                    onReadingPositionChanged(ReadingPosition(index = index, offset = offset))
                }
            }
    }

    DisposableEffect(lesson.id, listState) {
        onDispose {
            val index = listState.firstVisibleItemIndex
            val offset = listState.firstVisibleItemScrollOffset
            if (index > 0 || offset > 0) {
                onReadingPositionChanged(ReadingPosition(index = index, offset = offset))
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onBookmarkClick) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Bookmark Lesson",
                            tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onBackClick,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Back", style = MaterialTheme.typography.labelLarge)
                    }

                    if (lesson.quiz != null) {
                        Button(
                            onClick = onQuizClick,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Proceed to Quiz", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (resumedFromSaved) {
                item(key = "resume_position_banner") {
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.55f),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Resumed reading position",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.weight(1f)
                            )
                            TextButton(
                                onClick = {
                                    resumedFromSaved = false
                                    onReadingPositionChanged(ReadingPosition())
                                    scope.launch {
                                        listState.scrollToItem(0, 0)
                                    }
                                }
                            ) {
                                Text("Restart", style = MaterialTheme.typography.labelLarge)
                            }
                        }
                    }
                }
            }

            item(key = "lesson_body_content") {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = lesson.title,
                        fontSize = readingFontSize.titleFontSize,
                        lineHeight = (readingFontSize.titleFontSize.value * 1.25f).sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(readingLineSpacing.paragraphSpacing.dp)
                    ) {
                        paragraphs.forEach { paragraph ->
                            Text(
                                text = paragraph,
                                fontSize = readingFontSize.bodyFontSize,
                                lineHeight = readingLineSpacing.lineHeight
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    quiz: Quiz,
    lesson: Lesson,
    nextLesson: Lesson?,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onNextLessonClick: (Lesson) -> Unit
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }
    var isCorrect by remember { mutableStateOf(false) }
    var quizFinished by remember { mutableStateOf(false) }

    val question = quiz.questions.getOrNull(currentQuestionIndex)

    LaunchedEffect(currentQuestionIndex) {
        selectedOptionIndex = null
        feedbackMessage = null
        isCorrect = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        if (quizFinished) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Quiz Completed!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "You have successfully completed the quiz for this lesson.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onHomeClick) {
                    Text("Return to Home", style = MaterialTheme.typography.labelLarge)
                }
                if (nextLesson != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(onClick = { onNextLessonClick(nextLesson) }) {
                        Text("Next Lesson", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        } else if (question != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Question ${currentQuestionIndex + 1} of ${quiz.questions.size}",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = question.text,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                items(question.options.size) { index ->
                    val option = question.options[index]
                    val isSelected = selectedOptionIndex == index
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (!isCorrect) {
                                    selectedOptionIndex = index
                                    feedbackMessage = null
                                }
                            },
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = {
                                    if (!isCorrect) {
                                        selectedOptionIndex = index
                                        feedbackMessage = null
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if (feedbackMessage != null) {
                            Text(
                                text = feedbackMessage!!,
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (isCorrect) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                            )
                        }

                        Button(
                            onClick = {
                                if (!isCorrect) {
                                    if (selectedOptionIndex != null) {
                                        if (selectedOptionIndex == question.correctOptionIndex) {
                                            isCorrect = true
                                            feedbackMessage = "Correct! ${question.explanation}"
                                        } else {
                                            isCorrect = false
                                            feedbackMessage = "Incorrect. ${question.explanation}"
                                        }
                                    }
                                } else {
                                    if (currentQuestionIndex < quiz.questions.size - 1) {
                                        currentQuestionIndex++
                                    } else {
                                        quizFinished = true
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .navigationBarsPadding(),
                            enabled = selectedOptionIndex != null
                        ) {
                            Text(
                                text = if (!isCorrect) {
                                    "Submit Answer"
                                } else {
                                    if (currentQuestionIndex < quiz.questions.size - 1) "Next Question" else "Finish Quiz"
                                },
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }
}
