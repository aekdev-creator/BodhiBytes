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
import dev.alex.bodhibytes.ui.state.ReadingPosition
import dev.alex.bodhibytes.ui.state.rememberBodhiBytesAppState
import dev.alex.bodhibytes.ui.theme.UnconditionedDarkColorScheme
import dev.alex.bodhibytes.ui.theme.UnconditionedLightColorScheme
import kotlinx.coroutines.launch

private const val LAST_MAIN_CURRICULUM_LESSON_ID = 21

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
    val appState = rememberBodhiBytesAppState(context)

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

    val allLessons by produceState(
        initialValue = LessonRepository.getCachedCurriculum(),
        key1 = context,
        key2 = appState.curriculumRetryToken
    ) {
        val loaded = LessonRepository.getCurriculum(
            context = context,
            forceRefresh = appState.curriculumRetryToken > 0
        )
        if (loaded.isNotEmpty() || value.isEmpty()) {
            value = loaded
        }
    }

    val orderedLessons = remember(allLessons) {
        buildList {
            allLessons.find { it.id == 1 }?.let { add(it) }
            addAll(allLessons.filter { it.id in 2..6 })
            addAll(allLessons.filter { it.id in 7..10 })
            addAll(allLessons.filter { it.id in 11..15 })
            addAll(allLessons.filter { it.id in 16..21 })
            addAll(allLessons.filter { it.id in 22..31 })
            addAll(allLessons.filter { it.id == 32 })
        }
    }

    val continueReadingLesson = remember(
        appState.lastOpenedLessonId,
        appState.readingPositions,
        appState.completedQuizLessonIds,
        orderedLessons
    ) {
        val lastLesson = appState.lastOpenedLessonId
            ?.takeIf { id -> !appState.completedQuizLessonIds.contains(id) }
            ?.let { id -> orderedLessons.find { it.id == id } }

        if (lastLesson != null && appState.readingPositions[lastLesson.id]?.hasProgress() == true) {
            return@remember lastLesson
        }

        orderedLessons.firstOrNull { lesson ->
            !appState.completedQuizLessonIds.contains(lesson.id) &&
                appState.readingPositions[lesson.id]?.hasProgress() == true
        }
    }

    MaterialTheme(
        colorScheme = if (appState.isDarkMode) UnconditionedDarkColorScheme else UnconditionedLightColorScheme
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
                        initialIndex = appState.lessonListScrollIndex,
                        initialOffset = appState.lessonListScrollOffset,
                        expandedKeys = appState.lessonListExpandedKeys,
                        readingPositions = appState.readingPositions,
                        completedQuizLessonIds = appState.completedQuizLessonIds,
                        continueReadingLesson = continueReadingLesson,
                        isDarkMode = appState.isDarkMode,
                        allLessons = allLessons,
                        scrollToLibraryToken = appState.scrollToLibraryToken,
                        onScrollToLibraryHandled = { appState.scrollToLibraryToken = 0 },
                        onExpandedKeysChanged = { appState.lessonListExpandedKeys = it },
                        onScrollChanged = { index, offset ->
                            appState.updateLessonListScroll(index, offset)
                        },
                        onLessonSelected = { lesson ->
                            appState.onLessonOpened(lesson.id)
                            currentScreen = AppScreen.Detail(lesson)
                        },
                        onClearContinueReading = { lesson ->
                            appState.clearContinueReadingLesson(lesson.id)
                        },
                        onMenuClick = { scope.launch { drawerState.open() } }
                    )
                }

                is AppScreen.Detail -> {
                    LaunchedEffect(screen.lesson.id, appState.completedQuizLessonIds) {
                        appState.onLessonOpened(screen.lesson.id)
                    }
                    val savedPosition = appState.readingPositions[screen.lesson.id] ?: ReadingPosition()
                    LessonDetailScreen(
                        lesson = screen.lesson,
                        isBookmarked = appState.bookmarkedLessonIds.contains(screen.lesson.id),
                        savedReadingPosition = savedPosition,
                        readingFontSize = appState.readingFontSize,
                        readingLineSpacing = appState.readingLineSpacing,
                        keepScreenAwake = appState.keepScreenAwake,
                        onReadingPositionChanged = { position ->
                            appState.updateReadingPosition(screen.lesson.id, position)
                        },
                        onBookmarkClick = { appState.toggleBookmark(screen.lesson.id) },
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
                        readingFontSize = appState.readingFontSize,
                        readingLineSpacing = appState.readingLineSpacing,
                        onQuizCompleted = { appState.handleQuizCompleted(it) },
                        onBackClick = { currentScreen = AppScreen.Detail(screen.lesson) },
                        onHomeClick = { currentScreen = AppScreen.List },
                        onNextLessonClick = { targetLesson: Lesson ->
                            appState.onLessonOpened(targetLesson.id)
                            currentScreen = AppScreen.Detail(targetLesson)
                        },
                        onGoToLibraryClick = {
                            appState.scrollToLibraryToken += 1
                            currentScreen = AppScreen.List
                        }
                    )
                }

                is AppScreen.Bookmarks -> {
                    BookmarksScreen(
                        bookmarkedIds = appState.bookmarkedLessonIds,
                        readingPositions = appState.readingPositions,
                        completedQuizLessonIds = appState.completedQuizLessonIds,
                        allLessons = allLessons.ifEmpty { orderedLessons },
                        initialIndex = appState.bookmarksScrollIndex,
                        initialOffset = appState.bookmarksScrollOffset,
                        onScrollChanged = { index, offset ->
                            appState.updateBookmarksScroll(index, offset)
                        },
                        onLessonSelected = { lesson ->
                            appState.onLessonOpened(lesson.id)
                            currentScreen = AppScreen.Detail(lesson)
                        },
                        onBackClick = { currentScreen = AppScreen.List }
                    )
                }

                is AppScreen.Settings -> {
                    SettingsScreen(
                        isDarkMode = appState.isDarkMode,
                        readingFontSize = appState.readingFontSize,
                        readingLineSpacing = appState.readingLineSpacing,
                        keepScreenAwake = appState.keepScreenAwake,
                        onDarkModeChanged = { appState.updateDarkMode(it) },
                        onReadingFontSizeChanged = { appState.updateReadingFontSize(it) },
                        onReadingLineSpacingChanged = { appState.updateReadingLineSpacing(it) },
                        onKeepScreenAwakeChanged = { appState.updateKeepScreenAwake(it) },
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
