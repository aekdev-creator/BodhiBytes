package dev.alex.bodhibytes.ui.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alex.bodhibytes.R
import dev.alex.bodhibytes.data.LessonRepository
import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Quiz
import dev.alex.bodhibytes.ui.screens.AboutScreen
import dev.alex.bodhibytes.ui.screens.BookmarksScreen
import dev.alex.bodhibytes.ui.screens.FeedbackScreen
import dev.alex.bodhibytes.ui.screens.LessonDetailScreen
import dev.alex.bodhibytes.ui.screens.LessonListScreen
import dev.alex.bodhibytes.ui.screens.QuizScreen
import dev.alex.bodhibytes.ui.screens.SettingsScreen
import dev.alex.bodhibytes.ui.state.KEY_BOOKMARKED_IDS
import dev.alex.bodhibytes.ui.state.KEY_COMPLETED_QUIZ_LESSON_IDS
import dev.alex.bodhibytes.ui.state.KEY_DARK_MODE
import dev.alex.bodhibytes.ui.state.KEY_KEEP_SCREEN_AWAKE
import dev.alex.bodhibytes.ui.state.KEY_LAST_OPENED_LESSON_ID
import dev.alex.bodhibytes.ui.state.KEY_READING_FONT_SIZE
import dev.alex.bodhibytes.ui.state.KEY_READING_LINE_SPACING
import dev.alex.bodhibytes.ui.state.PREFS_NAME
import dev.alex.bodhibytes.ui.state.ReadingPosition
import dev.alex.bodhibytes.ui.state.loadReadingPositions
import dev.alex.bodhibytes.ui.state.saveReadingPositions
import dev.alex.bodhibytes.ui.theme.ReadingFontSize
import dev.alex.bodhibytes.ui.theme.ReadingLineSpacing
import dev.alex.bodhibytes.ui.theme.UnconditionedDarkColorScheme
import dev.alex.bodhibytes.ui.theme.UnconditionedLightColorScheme
import kotlinx.coroutines.launch

private const val LAST_MAIN_CURRICULUM_LESSON_ID = 12

sealed interface AppScreen {
    object List : AppScreen
    data class Detail(val lesson: Lesson) : AppScreen
    data class QuizScreenState(val quiz: Quiz, val lesson: Lesson) : AppScreen
    object Bookmarks : AppScreen
    object Settings : AppScreen
    object Feedback : AppScreen
    object About : AppScreen
}

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
        context.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
    }

    var isDarkMode by remember {
        mutableStateOf(sharedPreferences.getBoolean(KEY_DARK_MODE, false))
    }
    var readingFontSize by remember {
        mutableStateOf(
            ReadingFontSize.fromKey(
                sharedPreferences.getString(KEY_READING_FONT_SIZE, ReadingFontSize.DEFAULT.key)
            )
        )
    }
    var readingLineSpacing by remember {
        mutableStateOf(
            ReadingLineSpacing.fromKey(
                sharedPreferences.getString(
                    KEY_READING_LINE_SPACING,
                    ReadingLineSpacing.COMFORTABLE.key
                )
            )
        )
    }
    var keepScreenAwake by remember {
        mutableStateOf(sharedPreferences.getBoolean(KEY_KEEP_SCREEN_AWAKE, true))
    }

    var bookmarkedLessonIds by remember {
        val savedStringSet = sharedPreferences.getStringSet(KEY_BOOKMARKED_IDS, emptySet()) ?: emptySet()
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
                                KEY_BOOKMARKED_IDS,
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
                            sharedPreferences.edit().putBoolean(KEY_DARK_MODE, enabled).apply()
                        },
                        onReadingFontSizeChanged = { size ->
                            readingFontSize = size
                            sharedPreferences.edit().putString(KEY_READING_FONT_SIZE, size.key).apply()
                        },
                        onReadingLineSpacingChanged = { spacing ->
                            readingLineSpacing = spacing
                            sharedPreferences.edit().putString(KEY_READING_LINE_SPACING, spacing.key).apply()
                        },
                        onKeepScreenAwakeChanged = { enabled ->
                            keepScreenAwake = enabled
                            sharedPreferences.edit().putBoolean(KEY_KEEP_SCREEN_AWAKE, enabled).apply()
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
