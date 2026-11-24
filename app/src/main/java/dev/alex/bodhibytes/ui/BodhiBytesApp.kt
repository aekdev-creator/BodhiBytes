package dev.alex.bodhibytes.ui

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alex.bodhibytes.R
import dev.alex.bodhibytes.data.LessonRepository
import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Quiz
import kotlinx.coroutines.launch

private const val KEY_COMPLETED_QUIZ_LESSON_IDS = "completed_quiz_lesson_ids"
private const val KEY_LAST_OPENED_LESSON_ID = "last_opened_lesson_id"

private val UnconditionedLightColorScheme = lightColorScheme(
    primary = Color(0xFF47608B),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE7EDF6),
    onPrimaryContainer = Color(0xFF1A2942),
    secondary = Color(0xFF566378),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFEBEEF3),
    onSecondaryContainer = Color(0xFF1F2735),
    background = Color(0xFFF3F5F8),
    onBackground = Color(0xFF161A22),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF161A22),
    surfaceVariant = Color(0xFFF8F9FB),
    onSurfaceVariant = Color(0xFF5A6475),
    outline = Color(0xFFC2CBD8),
    outlineVariant = Color(0xFFD9DFE8)
)

private val UnconditionedDarkColorScheme = darkColorScheme(
    primary = Color(0xFF9CB4DE),
    onPrimary = Color(0xFF14243D),
    primaryContainer = Color(0xFF283A57),
    onPrimaryContainer = Color(0xFFDCE6F7),
    secondary = Color(0xFFBAC4D6),
    onSecondary = Color(0xFF242E3D),
    secondaryContainer = Color(0xFF2C3442),
    onSecondaryContainer = Color(0xFFDCE2ED),
    background = Color(0xFF101318),
    onBackground = Color(0xFFE5E9F0),
    surface = Color(0xFF171B22),
    onSurface = Color(0xFFE5E9F0),
    surfaceVariant = Color(0xFF1E242E),
    onSurfaceVariant = Color(0xFF9CA6B8),
    outline = Color(0xFF434D5E),
    outlineVariant = Color(0xFF2D3542)
)

private val CardShape = RoundedCornerShape(14.dp)

@Composable
private fun cardBorder(): BorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)

private data class ParsedDefinition(
    val term: String,
    val definition: String
)

private val DefinitionRegex = Regex(
    "^([\\p{L}\\p{N}\\s()/\\-–—]{1,75}):\\s+(.+)$",
    RegexOption.DOT_MATCHES_ALL
)

private fun parseDefinition(paragraph: String): ParsedDefinition? {
    val trimmed = paragraph.trim()
    val firstLine = trimmed.lines().firstOrNull()?.trim() ?: return null
    if (firstLine.length > 140) return null
    val match = DefinitionRegex.matchEntire(trimmed) ?: return null
    val term = match.groupValues[1].trim().replace(Regex("\\s+"), " ")
    val definition = match.groupValues[2].trim()
    if (term.isEmpty() || definition.isEmpty()) return null
    if (term.count { it == ' ' } > 8) return null
    return ParsedDefinition(term = term, definition = definition)
}

enum class ReadingFontSize(
    val key: String,
    val label: String,
    val bodyFontSize: TextUnit,
    val titleFontSize: TextUnit
) {
    SMALL("small", "Small", 13.sp, 15.sp),
    DEFAULT("default", "Default", 14.sp, 16.sp),
    LARGE("large", "Large", 16.sp, 18.sp),
    EXTRA_LARGE("extra_large", "Extra Large", 18.sp, 21.sp);

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
    COMPACT("compact", "Compact", 18.sp, 4),
    COMFORTABLE("comfortable", "Comfortable", 20.sp, 6),
    SPACIOUS("spacious", "Spacious", 24.sp, 10);

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
    data class NestedModule(
        val module: LessonModule,
        val subModules: List<LessonModule>
    ) : HomeItem
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
        val strokeWidth = 2.dp.toPx()
        val horizontalInset = size.width * 0.28f
        val topY = size.height * 0.38f
        val bottomY = size.height * 0.62f
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

    BackHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }

    BackHandler(enabled = !drawerState.isOpen && currentScreen !is AppScreen.List) {
        currentScreen = when (val screen = currentScreen) {
            is AppScreen.QuizScreenState -> AppScreen.Detail(screen.lesson)
            else -> AppScreen.List
        }
    }

    var lessonListScrollIndex by remember { mutableStateOf(0) }
    var lessonListScrollOffset by remember { mutableStateOf(0) }
    var lessonListExpandedKeys by remember { mutableStateOf(emptySet<String>()) }

    var bookmarksScrollIndex by remember { mutableStateOf(0) }
    var bookmarksScrollOffset by remember { mutableStateOf(0) }

    val sharedPreferences = remember {
        context.getSharedPreferences("bodhibytes_prefs", android.content.Context.MODE_PRIVATE)
    }

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

    var bookmarkedLessonIds by remember {
        val savedStringSet = sharedPreferences.getStringSet("bookmarked_ids", emptySet()) ?: emptySet()
        mutableStateOf(savedStringSet.mapNotNull { it.toIntOrNull() }.toSet())
    }

    var readingPositions by remember {
        mutableStateOf(loadReadingPositions(sharedPreferences))
    }

    var completedQuizLessonIds by remember {
        val saved = sharedPreferences.getStringSet(KEY_COMPLETED_QUIZ_LESSON_IDS, emptySet()) ?: emptySet()
        mutableStateOf(saved.mapNotNull { it.toIntOrNull() }.toSet())
    }

    var lastOpenedLessonId by remember {
        val savedId = sharedPreferences.getInt(KEY_LAST_OPENED_LESSON_ID, -1)
        mutableStateOf(if (savedId != -1) savedId else null)
    }

    val persistCompletedQuizIds: (Set<Int>) -> Unit = { updated ->
        completedQuizLessonIds = updated
        sharedPreferences.edit()
            .putStringSet(KEY_COMPLETED_QUIZ_LESSON_IDS, updated.map { it.toString() }.toSet())
            .apply()
    }

    val recordLastOpenedLesson: (Int) -> Unit = { lessonId ->
        if (lastOpenedLessonId != lessonId) {
            lastOpenedLessonId = lessonId
            sharedPreferences.edit().putInt(KEY_LAST_OPENED_LESSON_ID, lessonId).apply()
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

            if (position.hasProgress() && completedQuizLessonIds.contains(lessonId)) {
                persistCompletedQuizIds(completedQuizLessonIds - lessonId)
                recordLastOpenedLesson(lessonId)
            }
        }
    }

    val clearContinueReadingLesson: (Int) -> Unit = { lessonId ->
        if (readingPositions.containsKey(lessonId)) {
            val updatedPositions = readingPositions - lessonId
            readingPositions = updatedPositions
            saveReadingPositions(sharedPreferences, updatedPositions)
        }
        if (lastOpenedLessonId == lessonId) {
            lastOpenedLessonId = null
            sharedPreferences.edit().remove(KEY_LAST_OPENED_LESSON_ID).apply()
        }
    }

    val handleQuizCompleted: (Int) -> Unit = { completedLessonId ->
        persistCompletedQuizIds(completedQuizLessonIds + completedLessonId)

        if (readingPositions.containsKey(completedLessonId)) {
            val updatedPositions = readingPositions - completedLessonId
            readingPositions = updatedPositions
            saveReadingPositions(sharedPreferences, updatedPositions)
        }

        if (lastOpenedLessonId == completedLessonId) {
            lastOpenedLessonId = null
            sharedPreferences.edit().remove(KEY_LAST_OPENED_LESSON_ID).apply()
        }
    }

    val allLessons = remember { LessonRepository.getCurriculum() }

    val orderedLessons = remember(allLessons) {
        buildList {
            allLessons.find { it.id == 1 }?.let { add(it) }
            addAll(allLessons.filter { it.id in 2..6 })
            addAll(allLessons.filter { it.id in 13..17 })
            addAll(allLessons.filter { it.id in 18..21 })
            addAll(allLessons.filter { it.id in 7..12 })
            addAll(allLessons.filter { it.id in 101..199 })
        }
    }

    val continueReadingLesson = remember(
        lastOpenedLessonId,
        readingPositions,
        completedQuizLessonIds,
        orderedLessons
    ) {
        val lastLesson = lastOpenedLessonId
            ?.takeIf { id -> !completedQuizLessonIds.contains(id) }
            ?.let { id -> orderedLessons.find { it.id == id } }

        if (lastLesson != null && readingPositions[lastLesson.id]?.hasProgress() == true) {
            return@remember lastLesson
        }

        orderedLessons.firstOrNull { lesson ->
            !completedQuizLessonIds.contains(lesson.id) &&
                readingPositions[lesson.id]?.hasProgress() == true
        }
    }

    MaterialTheme(
        colorScheme = if (isDarkMode) UnconditionedDarkColorScheme else UnconditionedLightColorScheme
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = MaterialTheme.colorScheme.surface
                ) {
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
                            modifier = Modifier.size(36.dp)
                        )
                        Text(
                            text = "BODHIBYTES",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )

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

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )

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
                        onClearContinueReading = { lesson ->
                            clearContinueReadingLesson(lesson.id)
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
                        readingFontSize = readingFontSize,
                        readingLineSpacing = readingLineSpacing,
                        onQuizCompleted = handleQuizCompleted,
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
    onClearContinueReading: (Lesson) -> Unit = {},
    onMenuClick: () -> Unit
) {
    val allLessons = remember { LessonRepository.getCurriculum() }

    val welcomeLesson = remember {
        Lesson(
            id = 0,
            title = "Welcome to BodhiBytes",
            summary = "",
            content = "",
            quiz = null
        )
    }

    val wordsAndMeaningsLesson = remember(allLessons) { allLessons.find { it.id == 1 } }

    val homeItems = remember(allLessons, wordsAndMeaningsLesson) {
        buildList {
            if (wordsAndMeaningsLesson != null) {
                add(HomeItem.StandaloneLesson(wordsAndMeaningsLesson))
            }
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "The Path",
                        lessons = allLessons.filter { it.id in 2..6 },
                        summary = "Foundational terms, stages of the path, and core principles guiding practice."
                    )
                )
            )
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "The Three Marks of Existence",
                        lessons = allLessons.filter { it.id in 13..17 },
                        summary = "Exploring impermanence, unsatisfactoriness, and non-self across conditioned experience."
                    )
                )
            )
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "Compassion (Bodhicitta)",
                        lessons = allLessons.filter { it.id in 18..21 },
                        summary = "Cultivating universal compassion and the awakening mind for the benefit of all beings."
                    )
                )
            )
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "Wisdom (Emptiness)",
                        lessons = allLessons.filter { it.id in 7..12 },
                        summary = "Insight into emptiness, interdependent origination, and seeing reality clearly."
                    )
                )
            )
            add(
                HomeItem.NestedModule(
                    module = LessonModule(
                        title = "Library",
                        lessons = emptyList(),
                        summary = "Source texts and classical Buddhist works."
                    ),
                    subModules = listOf(
                        LessonModule(
                            title = "A Guide to the Bodhisattva's Way of Life by Śāntideva",
                            lessons = allLessons.filter { it.id in 101..199 },
                            summary = "Bodhicaryāvatāra — Chapter 1: The Benefits of the Awakening Mind."
                        )
                    )
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
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.mipmap.ic_launcher),
                                contentDescription = "App Icon",
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = "BODHIBYTES",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
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
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
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
                        onResumeClick = { onLessonSelected(continueReadingLesson) },
                        onClearClick = { onClearContinueReading(continueReadingLesson) }
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
                                shape = CardShape,
                                border = cardBorder(),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(18.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(end = 8.dp)
                                    ) {
                                        Text(
                                            text = "MODULE · ${module.lessons.size} LESSONS",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.SemiBold,
                                            letterSpacing = 1.sp,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = module.title,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = moduleSummary,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 5,
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
                    is HomeItem.NestedModule -> {
                        val module = item.module
                        val parentKey = "nested_$index"
                        val isParentExpanded = expandedKeys.contains(parentKey)
                        val parentSummary = if (module.summary.isNotBlank()) {
                            module.summary
                        } else if (item.subModules.isEmpty()) {
                            "No items in ${module.title} yet."
                        } else {
                            val subCount = item.subModules.size
                            val subLabel = if (subCount == 1) "1 work" else "$subCount works"
                            "$subLabel in ${module.title}."
                        }

                        item(key = parentKey) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onExpandedKeysChanged(
                                            if (isParentExpanded) {
                                                expandedKeys - parentKey
                                            } else {
                                                expandedKeys + parentKey
                                            }
                                        )
                                    },
                                shape = CardShape,
                                border = cardBorder(),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(18.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(end = 8.dp)
                                    ) {
                                        Text(
                                            text = "COLLECTION",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.SemiBold,
                                            letterSpacing = 1.sp,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = module.title,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = parentSummary,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 5,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    ExpandChevronIcon(
                                        expanded = isParentExpanded,
                                        contentDescription = if (isParentExpanded) "Collapse Module" else "Expand Module",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        if (isParentExpanded) {
                            items(module.lessons, key = { "nested_${index}_lesson_${it.id}" }) { lesson ->
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

                            item.subModules.forEachIndexed { subIndex, subModule ->
                                val subKey = "nested_${index}_sub_$subIndex"
                                val isSubExpanded = expandedKeys.contains(subKey)
                                val subSummary = buildModuleSummary(
                                    subModule.title,
                                    subModule.lessons,
                                    subModule.summary
                                )

                                item(key = subKey) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 12.dp)
                                            .clickable {
                                                onExpandedKeysChanged(
                                                    if (isSubExpanded) {
                                                        expandedKeys - subKey
                                                    } else {
                                                        expandedKeys + subKey
                                                    }
                                                )
                                            },
                                        shape = CardShape,
                                        border = cardBorder(),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.surface
                                        )
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(18.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .padding(end = 8.dp)
                                            ) {
                                                Text(
                                                    text = "TEXT · ${subModule.lessons.size} SECTIONS",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontWeight = FontWeight.SemiBold,
                                                    letterSpacing = 1.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                                Spacer(modifier = Modifier.height(6.dp))
                                                Text(
                                                    text = subModule.title,
                                                    style = MaterialTheme.typography.titleMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.onSurface
                                                )
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = subSummary,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    maxLines = 5,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(8.dp))
                                            ExpandChevronIcon(
                                                expanded = isSubExpanded,
                                                contentDescription = if (isSubExpanded) "Collapse Module" else "Expand Module",
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }
                                }

                                if (isSubExpanded) {
                                    items(
                                        subModule.lessons,
                                        key = { "nested_${index}_sub_${subIndex}_lesson_${it.id}" }
                                    ) { lesson ->
                                        Box(modifier = Modifier.padding(start = 24.dp)) {
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
                                shape = CardShape,
                                border = cardBorder(),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(18.dp)
                                ) {
                                    Text(
                                        text = "GLOSSARY",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.SemiBold,
                                        letterSpacing = 1.sp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = lesson.title,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    if (cardSummary.isNotBlank()) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = cardSummary,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 5,
                                            overflow = TextOverflow.Ellipsis
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

@Composable
fun ContinueReadingCard(
    lesson: Lesson,
    onResumeClick: () -> Unit,
    onClearClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onResumeClick),
        shape = CardShape,
        border = cardBorder(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CONTINUE READING",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                TextButton(
                    onClick = onClearClick,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    modifier = Modifier.height(28.dp)
                ) {
                    Text(
                        text = "Clear",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            Button(
                onClick = onResumeClick,
                shape = CircleShape,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = "Resume Reading",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
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
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Bookmarks",
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
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
                    style = MaterialTheme.typography.bodyMedium,
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
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Settings",
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    shape = CardShape,
                    border = cardBorder(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "APPEARANCE",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onDarkModeChanged(!isDarkMode) }
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Dark Mode",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
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
                }
            }

            item {
                Card(
                    shape = CardShape,
                    border = cardBorder(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "READING & COMFORT",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Reading Font Size",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Adjust text size for lesson readings",
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
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Line Spacing",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
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
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onKeepScreenAwakeChanged(!keepScreenAwake) }
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Keep Screen Awake While Reading",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Prevent display sleep while viewing a lesson",
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(onBackClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Feedback & Support",
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                shape = CardShape,
                border = cardBorder(),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "We value your feedback!",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "If you found a bug or have suggestions to improve BodhiBytes, please feel free to open an issue or pull request on GitHub.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(
                        onClick = { uriHandler.openUri("https://github.com/aekdev-creator/BodhiBytes/issues") },
                        shape = CircleShape,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Open GitHub Issues", fontWeight = FontWeight.SemiBold)
                    }
                }
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
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "About BodhiBytes",
                            fontWeight = FontWeight.SemiBold
                        )
                    },
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
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CardShape,
                border = cardBorder(),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_launcher),
                        contentDescription = "App Icon",
                        modifier = Modifier.size(72.dp)
                    )
                    Text(
                        text = "BodhiBytes",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Version $versionName",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "BodhiBytes is your companion for exploring foundational Buddhist teachings, traditional terminology, and insights into wisdom and compassion.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )

                    Text(
                        text = "Developed by Alexander Kelly",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Built with Jetpack Compose & Kotlin",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    OutlinedButton(
                        onClick = { uriHandler.openUri("https://github.com/aekdev-creator/BodhiBytes") },
                        shape = CircleShape,
                        border = cardBorder()
                    ) {
                        Text("View Source Code on GitHub")
                    }

                    Text(
                        text = "Privacy Policy: BodhiBytes does not collect, store, or share any personal user data. All lesson progress and local states remain entirely on your device.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "© 2026 Alexander Kelly. All rights reserved.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun WelcomeCard(
    lesson: Lesson
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Text(
            text = lesson.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
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
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.8.sp,
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
    val hasQuiz = lesson.quiz != null

    if (isExpandedLesson) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
            shape = CardShape,
            border = cardBorder(),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
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
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
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
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                if (showSummary && displaySummary.isNotBlank()) {
                    Text(
                        text = displaySummary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
            shape = CardShape,
            border = cardBorder(),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
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
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                if (showSummary && displaySummary.isNotBlank()) {
                    Text(
                        text = displaySummary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 5,
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
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
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
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
        },
        bottomBar = {
            Surface(
                tonalElevation = 0.dp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = onBackClick,
                            shape = CircleShape,
                            border = cardBorder(),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Back", style = MaterialTheme.typography.labelLarge)
                        }

                        if (lesson.quiz != null) {
                            Button(
                                onClick = onQuizClick,
                                shape = CircleShape,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Proceed to Quiz", style = MaterialTheme.typography.labelLarge)
                            }
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
                        color = MaterialTheme.colorScheme.surface,
                        shape = CardShape,
                        border = cardBorder(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Resumed reading position",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                Card(
                    shape = CardShape,
                    border = cardBorder(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Text(
                            text = lesson.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = readingFontSize.titleFontSize,
                                lineHeight = (readingFontSize.titleFontSize.value * 1.3f).sp
                            ),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Spacer(modifier = Modifier.height(12.dp))

                        val parsedItems = remember(paragraphs) {
                            paragraphs.map { it to parseDefinition(it) }
                        }

                        Column(
                            verticalArrangement = Arrangement.spacedBy(readingLineSpacing.paragraphSpacing.dp)
                        ) {
                            parsedItems.forEachIndexed { idx, (paragraph, def) ->
                                val nextIsDef = parsedItems.getOrNull(idx + 1)?.second != null
                                if (def != null) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = buildAnnotatedString {
                                                withStyle(
                                                    SpanStyle(
                                                        color = MaterialTheme.colorScheme.primary,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                ) {
                                                    append("${def.term}: ")
                                                }
                                                withStyle(
                                                    SpanStyle(
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                ) {
                                                    append(def.definition)
                                                }
                                            },
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontSize = readingFontSize.bodyFontSize,
                                                lineHeight = readingLineSpacing.lineHeight
                                            )
                                        )
                                        if (nextIsDef) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                            HorizontalDivider(
                                                thickness = 0.75.dp,
                                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.75f)
                                            )
                                        }
                                    }
                                } else {
                                    Text(
                                        text = paragraph,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontSize = readingFontSize.bodyFontSize,
                                            lineHeight = readingLineSpacing.lineHeight
                                        ),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
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
