package dev.alex.bodhibytes.ui

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alex.bodhibytes.R
import dev.alex.bodhibytes.data.KoanRepository
import dev.alex.bodhibytes.data.LessonRepository
import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Quiz
import dev.alex.santideva.repository.SantidevaRepository
import kotlinx.coroutines.launch

private val AppCardShape = RoundedCornerShape(16.dp)

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

    // Initialize SharedPreferences for persistent storage (bookmarks and preferences)
    val sharedPreferences = remember {
        context.getSharedPreferences("bodhibytes_prefs", android.content.Context.MODE_PRIVATE)
    }

    // Load dark mode preference
    var isDarkMode by remember {
        mutableStateOf(sharedPreferences.getBoolean("dark_mode", false))
    }

    // Load saved bookmarks from local storage on startup
    var bookmarkedLessonIds by remember {
        val savedStringSet = sharedPreferences.getStringSet("bookmarked_ids", emptySet()) ?: emptySet()
        mutableStateOf(savedStringSet.mapNotNull { it.toIntOrNull() }.toSet())
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
                        onExpandedKeysChanged = { lessonListExpandedKeys = it },
                        onScrollChanged = { index, offset ->
                            lessonListScrollIndex = index
                            lessonListScrollOffset = offset
                        },
                        onLessonSelected = { lesson -> currentScreen = AppScreen.Detail(lesson) },
                        onMenuClick = { scope.launch { drawerState.open() } }
                    )
                }
                is AppScreen.Detail -> {
                    LessonDetailScreen(
                        lesson = screen.lesson,
                        isBookmarked = bookmarkedLessonIds.contains(screen.lesson.id),
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
                            currentScreen = AppScreen.Detail(targetLesson)
                        }
                    )
                }
                is AppScreen.Bookmarks -> {
                    BookmarksScreen(
                        bookmarkedIds = bookmarkedLessonIds,
                        allLessons = orderedLessons,
                        initialIndex = bookmarksScrollIndex,
                        initialOffset = bookmarksScrollOffset,
                        onScrollChanged = { index, offset ->
                            bookmarksScrollIndex = index
                            bookmarksScrollOffset = offset
                        },
                        onLessonSelected = { lesson -> currentScreen = AppScreen.Detail(lesson) },
                        onBackClick = { currentScreen = AppScreen.List }
                    )
                }
                is AppScreen.Settings -> {
                    SettingsScreen(
                        isDarkMode = isDarkMode,
                        onDarkModeChanged = { enabled ->
                            isDarkMode = enabled
                            sharedPreferences.edit().putBoolean("dark_mode", enabled).apply()
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
            summary = "Ignorance grasps phenomena as more than dependent arising.",
            content = "Ignorance grasps phenomena as more than dependent arising.",
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(key = "welcome_lesson_card") {
                WelcomeCard(
                    lesson = welcomeLesson,
                    onClick = { onLessonSelected(welcomeLesson) }
                )
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
                                shape = AppCardShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
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
                                            text = module.title,
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = moduleSummary,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
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
                                        showSummary = true,
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
                                shape = AppCardShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
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
                                    textIndent = TextIndent(firstLine = 12.sp, restLine = 12.sp)
                                )
                            ) {
                                append(item.subModules.joinToString("\n") { "• ${it.title}" })
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
                                shape = AppCardShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
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
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Icon(
                                        imageVector = if (isGroupExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
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
                                            shape = AppCardShape,
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
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
                                                        style = MaterialTheme.typography.titleLarge,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Spacer(modifier = Modifier.height(4.dp))
                                                    Text(
                                                        text = subSummary,
                                                        style = MaterialTheme.typography.bodyMedium,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(12.dp))
                                                Icon(
                                                    imageVector = if (isSubExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
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
                                                showSummary = showLessonSummaries,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    bookmarkedIds: Set<Int>,
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
                        showSummary = true,
                        onClick = { onLessonSelected(lesson) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkMode: Boolean,
    onDarkModeChanged: (Boolean) -> Unit,
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "App Preferences",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

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
    lesson: Lesson,
    onClick: () -> Unit
) {
    val displaySummary = lesson.summary.ifBlank {
        buildBriefSummary(lesson.content, lesson.title)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = AppCardShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f),
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "WELCOME",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            if (displaySummary.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
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
fun LessonCard(
    lesson: Lesson,
    showSummary: Boolean = true,
    onClick: () -> Unit
) {
    val displaySummary = lesson.summary.ifBlank {
        buildBriefSummary(lesson.content, lesson.title)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = AppCardShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
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
            if (showSummary && displaySummary.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    lesson: Lesson,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
    onQuizClick: () -> Unit
) {
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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = lesson.content,
                        style = MaterialTheme.typography.bodyLarge
                    )
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
                        shape = AppCardShape,
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
