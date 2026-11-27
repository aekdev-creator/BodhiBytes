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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
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
private const val LAST_MAIN_CURRICULUM_LESSON_ID = 12

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

private sealed interface ContentBlock {
    data class SectionHeading(val title: String) : ContentBlock
    data class NumberedCard(val number: String, val title: String, val body: String) : ContentBlock
    data class KeyValueCard(val term: String, val definition: String) : ContentBlock
    data class VerseBlock(val lines: List<String>) : ContentBlock
    data class SourceNote(val text: String) : ContentBlock
    data class BodyParagraph(val text: String) : ContentBlock
}

private val DefinitionRegex = Regex(
    "^([\\p{L}\\p{N}\\s()/&\\-–—]{1,75}):\\s+(.+)$",
    RegexOption.DOT_MATCHES_ALL
)

private val NumberedItemRegex = Regex(
    "^(\\d+)\\.\\s+([\\p{L}\\p{N}\\s()/&\\-–—]{1,65}):\\s+(.+)$",
    RegexOption.DOT_MATCHES_ALL
)

private val StandaloneHeadingRegex = Regex(
    "^([\\p{L}\\p{N}\\s()/&\\-–—]{3,75}):$"
)

private fun parseContentBlocks(content: String): List<ContentBlock> {
    val rawParagraphs = content
        .split(Regex("\\n\\s*\\n"))
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    return rawParagraphs.map { paragraph ->
        val lines = paragraph.lines().map { it.trim() }.filter { it.isNotEmpty() }

        if (paragraph.startsWith("Source & Study Note:") ||
            paragraph.startsWith("Source and Translation Note") ||
            paragraph.startsWith("SOURCE AND TRANSLATION NOTE")
        ) {
            return@map ContentBlock.SourceNote(paragraph)
        }

        if (lines.size == 1) {
            val headingMatch = StandaloneHeadingRegex.matchEntire(lines[0])
            if (headingMatch != null && lines[0].count { it == ' ' } <= 9) {
                return@map ContentBlock.SectionHeading(headingMatch.groupValues[1].trim())
            }
        }

        val numberedMatch = NumberedItemRegex.matchEntire(paragraph)
        if (numberedMatch != null) {
            return@map ContentBlock.NumberedCard(
                number = numberedMatch.groupValues[1].trim(),
                title = numberedMatch.groupValues[2].trim().replace(Regex("\\s+"), " "),
                body = numberedMatch.groupValues[3].trim()
            )
        }

        if (lines.size >= 3 && lines.all { it.length <= 75 }) {
            return@map ContentBlock.VerseBlock(lines)
        }

        val firstLine = lines.firstOrNull() ?: ""
        if (firstLine.length <= 140) {
            val defMatch = DefinitionRegex.matchEntire(paragraph)
            if (defMatch != null) {
                val term = defMatch.groupValues[1].trim().replace(Regex("\\s+"), " ")
                val body = defMatch.groupValues[2].trim()
                if (term.isNotEmpty() && body.isNotEmpty() && term.count { it == ' ' } <= 8) {
                    return@map ContentBlock.KeyValueCard(term = term, definition = body)
                }
            }
        }

        ContentBlock.BodyParagraph(paragraph)
    }
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
    val paragraphSpacing: Dp
) {
    COMPACT("compact", "Compact", 18.sp, 8.dp),
    COMFORTABLE("comfortable", "Comfortable", 21.sp, 12.dp),
    SPACIOUS("spacious", "Spacious", 25.sp, 16.dp);

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
    var scrollToLibraryToken by remember { mutableIntStateOf(0) }

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

    val allLessons by produceState(
        initialValue = LessonRepository.getCachedCurriculum(),
        key1 = context
    ) {
        value = LessonRepository.getCurriculum(context)
    }

    val orderedLessons = remember(allLessons) {
        buildList {
            allLessons.find { it.id == 1 }?.let { add(it) }
            addAll(allLessons.filter { it.id in 2..6 })
            addAll(allLessons.filter { it.id in 13..17 })
            addAll(allLessons.filter { it.id in 18..21 })
            addAll(allLessons.filter { it.id in 7..12 })
            addAll(allLessons.filter { it.id in 101..110 })
            addAll(allLessons.filter { it.id in 111..199 })
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
                            text = "BodhiBytes",
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
                        isDarkMode = isDarkMode,
                        allLessons = allLessons,
                        scrollToLibraryToken = scrollToLibraryToken,
                        onScrollToLibraryHandled = { scrollToLibraryToken = 0 },
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
                    val isLastMainLesson = screen.lesson.id == LAST_MAIN_CURRICULUM_LESSON_ID
                    val currentIndex = orderedLessons.indexOfFirst { it.id == screen.lesson.id }
                    val nextLesson = if (!isLastMainLesson && currentIndex != -1 && currentIndex + 1 < orderedLessons.size) {
                        orderedLessons[currentIndex + 1]
                    } else {
                        null
                    }

                    QuizScreen(
                        quiz = screen.quiz,
                        lesson = screen.lesson,
                        nextLesson = nextLesson,
                        showGoToLibrary = isLastMainLesson,
                        readingFontSize = readingFontSize,
                        readingLineSpacing = readingLineSpacing,
                        onQuizCompleted = handleQuizCompleted,
                        onBackClick = { currentScreen = AppScreen.Detail(screen.lesson) },
                        onHomeClick = { currentScreen = AppScreen.List },
                        onNextLessonClick = { targetLesson: Lesson ->
                            recordLastOpenedLesson(targetLesson.id)
                            currentScreen = AppScreen.Detail(targetLesson)
                        },
                        onGoToLibraryClick = {
                            scrollToLibraryToken += 1
                            currentScreen = AppScreen.List
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
    isDarkMode: Boolean = false,
    allLessons: List<Lesson> = remember { LessonRepository.getCachedCurriculum() },
    scrollToLibraryToken: Int = 0,
    onScrollToLibraryHandled: () -> Unit = {},
    onExpandedKeysChanged: (Set<String>) -> Unit,
    onScrollChanged: (Int, Int) -> Unit,
    onLessonSelected: (Lesson) -> Unit,
    onClearContinueReading: (Lesson) -> Unit = {},
    onMenuClick: () -> Unit
) {

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
                            title = "The Three Principal Aspects of the Path by Tsongkhapa",
                            lessons = allLessons.filter { it.id == 111 },
                            summary = "Lam gyi gtso bo rnam gsum by Tsongkhapa: determination to be free, supreme bodhicitta, and the wisdom realizing dependent arising and emptiness."
                        ),
                        LessonModule(
                            title = "A Guide to the Bodhisattva's Way of Life by Śāntideva",
                            lessons = allLessons.filter { it.id in 101..110 },
                            summary = "Bodhicaryāvatāra — Chapter 1: The Benefits of the Awakening Mind."
                        )
                    )
                )
            )
        }
    }

    val libraryHomeIndex = remember(homeItems) {
        homeItems.indexOfFirst {
            it is HomeItem.NestedModule && it.module.title == "Library"
        }
    }
    val libraryParentKey = remember(libraryHomeIndex) {
        if (libraryHomeIndex >= 0) "nested_$libraryHomeIndex" else null
    }
    val targetExpandedKeysForLibrary = remember(libraryParentKey) {
        if (libraryParentKey != null) setOf(libraryParentKey) else emptySet()
    }
    val effectiveExpandedKeys = if (scrollToLibraryToken > 0) {
        targetExpandedKeysForLibrary
    } else {
        expandedKeys
    }

    val bottomLazyItemIndex = remember(homeItems, effectiveExpandedKeys, continueReadingLesson) {
        var count = 1 + if (continueReadingLesson != null) 1 else 0
        homeItems.forEachIndexed { i, currentItem ->
            count += 1
            when (currentItem) {
                is HomeItem.RegularModule -> {
                    if (effectiveExpandedKeys.contains("regular_$i")) {
                        count += currentItem.module.lessons.size
                    }
                }
                is HomeItem.NestedModule -> {
                    if (effectiveExpandedKeys.contains("nested_$i")) {
                        count += currentItem.module.lessons.size
                        currentItem.subModules.forEachIndexed { subIdx, subMod ->
                            count += 1
                            if (effectiveExpandedKeys.contains("nested_${i}_sub_$subIdx")) {
                                count += subMod.lessons.size
                            }
                        }
                    }
                }
                is HomeItem.StandaloneLesson -> Unit
            }
        }
        (count - 1).coerceAtLeast(0)
    }

    val effectiveInitialIndex = if (scrollToLibraryToken > 0) {
        bottomLazyItemIndex
    } else {
        initialIndex
    }
    val effectiveInitialOffset = if (scrollToLibraryToken > 0) {
        Int.MAX_VALUE
    } else {
        initialOffset
    }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = effectiveInitialIndex,
        initialFirstVisibleItemScrollOffset = effectiveInitialOffset
    )

    var isJumpingToLibrary by remember { mutableStateOf(false) }

    LaunchedEffect(scrollToLibraryToken, bottomLazyItemIndex, targetExpandedKeysForLibrary) {
        if (scrollToLibraryToken > 0) {
            isJumpingToLibrary = true
            onExpandedKeysChanged(targetExpandedKeysForLibrary)
            withFrameNanos { }
            listState.scrollToItem(index = bottomLazyItemIndex, scrollOffset = Int.MAX_VALUE)
            onScrollChanged(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset)
            isJumpingToLibrary = false
            onScrollToLibraryHandled()
        }
    }

    LaunchedEffect(listState, isJumpingToLibrary) {
        if (isJumpingToLibrary) return@LaunchedEffect
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
                                text = "BodhiBytes",
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
                WelcomeCard(
                    lesson = welcomeLesson,
                    isDarkMode = isDarkMode
                )
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
                        val isExpanded = effectiveExpandedKeys.contains(key)
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
                        val isParentExpanded = effectiveExpandedKeys.contains(parentKey)
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
                                val isSubExpanded = effectiveExpandedKeys.contains(subKey)
                                val subSummary = buildModuleSummary(
                                    subModule.title,
                                    subModule.lessons,
                                    subModule.summary
                                )
                                val sectionWord = if (subModule.lessons.size == 1) "SECTION" else "SECTIONS"

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
                                                    text = "TEXT · ${subModule.lessons.size} $sectionWord",
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
                        val glossaryContainerColor = if (isDarkMode) {
                            Color(0xFF221F1A)
                        } else {
                            Color(0xFFF7F3EA)
                        }
                        val glossaryLabelColor = if (isDarkMode) {
                            Color(0xFFD6A66B)
                        } else {
                            Color(0xFF8C5A2B)
                        }
                        val glossaryBorderColor = if (isDarkMode) {
                            Color(0xFF473E31)
                        } else {
                            Color(0xFFE3D8C3)
                        }

                        item(key = "standalone_lesson_${lesson.id}") {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onLessonSelected(lesson) },
                                shape = CardShape,
                                border = BorderStroke(1.dp, glossaryBorderColor),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = glossaryContainerColor
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
                                        color = glossaryLabelColor
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = lesson.title,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
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
                .padding(horizontal = 16.dp, vertical = 12.dp),
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
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onClearClick,
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                        modifier = Modifier.height(30.dp)
                    ) {
                        Text(
                            text = "Clear",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                    Button(
                        onClick = onResumeClick,
                        shape = CircleShape,
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp),
                        modifier = Modifier.height(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = "Resume",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
            }
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
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
                    modifier = Modifier.fillMaxWidth(),
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
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(
                            text = "APPEARANCE",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Dark Mode",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Switch between light and dark app themes",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Switch(
                                checked = isDarkMode,
                                onCheckedChange = onDarkModeChanged
                            )
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Keep Screen Awake",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Prevent screen timeout while reading lessons",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Switch(
                                checked = keepScreenAwake,
                                onCheckedChange = onKeepScreenAwakeChanged
                            )
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
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
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "READING TYPOGRAPHY",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = "Text Size",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ReadingFontSize.entries.forEach { size ->
                                FilterChip(
                                    selected = readingFontSize == size,
                                    onClick = { onReadingFontSizeChanged(size) },
                                    label = { Text(size.label) }
                                )
                            }
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                        Text(
                            text = "Line Spacing",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ReadingLineSpacing.entries.forEach { spacing ->
                                FilterChip(
                                    selected = readingLineSpacing == spacing,
                                    onClick = { onReadingLineSpacingChanged(spacing) },
                                    label = { Text(spacing.label) }
                                )
                            }
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(readingLineSpacing.paragraphSpacing)
                            ) {
                                Text(
                                    text = "PREVIEW",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    letterSpacing = 1.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Mindfulness is the gentle awareness of the present moment, observed with clarity, patience, and compassion.",
                                    fontSize = readingFontSize.bodyFontSize,
                                    lineHeight = readingLineSpacing.lineHeight,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Adjusting text size and spacing helps keep longer contemplative texts comfortable to read.",
                                    fontSize = readingFontSize.bodyFontSize,
                                    lineHeight = readingLineSpacing.lineHeight,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(onBackClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    val supportEmail = "bodhibytes@googlegroups.com"

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
                modifier = Modifier.fillMaxWidth(),
                shape = CardShape,
                border = cardBorder(),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "CONTACT & SUPPORT",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Get in Touch",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Have suggestions for new lessons, spotted a typo, or need assistance with the app? Send us an email anytime.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Button(
                        onClick = {
                            uriHandler.openUri("mailto:$supportEmail?subject=BodhiBytes%20Feedback")
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Default.Email,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Email $supportEmail")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current
    val versionName = remember(context) {
        try {
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageInfo(context.packageName, 0)
            }
            packageInfo.versionName ?: "1.0"
        } catch (_: Exception) {
            "1.0"
        }
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
                modifier = Modifier.fillMaxWidth(),
                shape = CardShape,
                border = cardBorder(),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.ic_launcher),
                            contentDescription = "BodhiBytes Logo",
                            modifier = Modifier.size(48.dp)
                        )
                        Column {
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
                        }
                    }

                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                    Text(
                        text = "BodhiBytes offers clear, structured introductions to foundational Buddhist philosophy, ethics, mindfulness, and classical contemplative texts.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Designed for quiet, distraction-free reading and reflection.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun WelcomeCard(
    lesson: Lesson,
    isDarkMode: Boolean = false
) {
    val containerColor = if (isDarkMode) {
        Color(0xFF1D2430)
    } else {
        Color(0xFFEDF1F7)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = CardShape,
        border = cardBorder(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            Text(
                text = "WELCOME",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Explore structured lessons and classical texts on Buddhist wisdom, ethics, and meditation.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
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
    val summaryText = remember(lesson.id, lesson.title, lesson.summary, lesson.content) {
        lesson.summary.trim().ifEmpty {
            buildBriefSummary(lesson.content, lesson.title)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = CardShape,
        border = cardBorder(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isExpandedLesson) {
                MaterialTheme.colorScheme.surfaceVariant
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = lesson.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                if (hasSavedPosition) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            text = "In Progress",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }
            if (showSummary && summaryText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = summaryText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
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

    val contentBlocks = remember(lesson.content) {
        parseContentBlocks(lesson.content)
    }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = savedReadingPosition.index,
        initialFirstVisibleItemScrollOffset = savedReadingPosition.offset
    )

    val readingProgress by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            if (totalItems <= 1) {
                1f
            } else {
                val visibleItems = layoutInfo.visibleItemsInfo
                val lastVisible = visibleItems.lastOrNull()
                if (lastVisible != null && lastVisible.index == totalItems - 1 &&
                    lastVisible.offset + lastVisible.size <= layoutInfo.viewportEndOffset
                ) {
                    1f
                } else {
                    (listState.firstVisibleItemIndex.toFloat() / (totalItems - 1).coerceAtLeast(1))
                        .coerceIn(0f, 1f)
                }
            }
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = readingProgress,
        label = "lesson_reading_progress"
    )

    val currentOnReadingPositionChanged by rememberUpdatedState(onReadingPositionChanged)

    LaunchedEffect(listState, lesson.id) {
        snapshotFlow {
            val atBottom = !listState.canScrollForward
            if (atBottom && (listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0)) {
                ReadingPosition(0, 0)
            } else {
                ReadingPosition(
                    index = listState.firstVisibleItemIndex,
                    offset = listState.firstVisibleItemScrollOffset
                )
            }
        }.collect { position ->
            currentOnReadingPositionChanged(position)
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = lesson.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = onBookmarkClick) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = if (isBookmarked) "Remove Bookmark" else "Add Bookmark",
                                tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.5.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(readingLineSpacing.paragraphSpacing)
        ) {
            if (lesson.summary.isNotBlank()) {
                item(key = "lesson_overview_card") {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = CardShape,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "LESSON OVERVIEW",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = lesson.summary.trim(),
                                fontSize = readingFontSize.bodyFontSize,
                                lineHeight = readingLineSpacing.lineHeight,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            items(contentBlocks.size) { index ->
                when (val block = contentBlocks[index]) {
                    is ContentBlock.SectionHeading -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 2.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = block.title,
                                fontSize = readingFontSize.titleFontSize,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                            )
                        }
                    }

                    is ContentBlock.NumberedCard -> {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
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
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    modifier = Modifier.size(26.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = block.number,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = block.title,
                                        fontSize = readingFontSize.bodyFontSize,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = block.body,
                                        fontSize = readingFontSize.bodyFontSize,
                                        lineHeight = readingLineSpacing.lineHeight,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }

                    is ContentBlock.KeyValueCard -> {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
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
                                    .padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = block.term,
                                    fontSize = readingFontSize.bodyFontSize,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = block.definition,
                                    fontSize = readingFontSize.bodyFontSize,
                                    lineHeight = readingLineSpacing.lineHeight,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    is ContentBlock.VerseBlock -> {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            border = cardBorder()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(4.dp)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                                        .background(MaterialTheme.colorScheme.primary)
                                )
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 14.dp, vertical = 12.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    block.lines.forEach { line ->
                                        Text(
                                            text = line,
                                            fontSize = readingFontSize.bodyFontSize,
                                            lineHeight = readingLineSpacing.lineHeight,
                                            fontStyle = FontStyle.Italic,
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    }

                    is ContentBlock.SourceNote -> {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                            border = cardBorder()
                        ) {
                            Text(
                                text = block.text,
                                style = MaterialTheme.typography.bodySmall,
                                lineHeight = 18.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    is ContentBlock.BodyParagraph -> {
                        Text(
                            text = block.text,
                            fontSize = readingFontSize.bodyFontSize,
                            lineHeight = readingLineSpacing.lineHeight,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            if (lesson.quiz != null) {
                item(key = "lesson_quiz_button") {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = onQuizClick,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = "Take Lesson Quiz",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
