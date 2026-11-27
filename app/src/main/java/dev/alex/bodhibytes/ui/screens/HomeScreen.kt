package dev.alex.bodhibytes.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alex.bodhibytes.R
import dev.alex.bodhibytes.data.LessonRepository
import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.ui.components.BodhiBytesTopBar
import dev.alex.bodhibytes.ui.components.ContinueReadingCard
import dev.alex.bodhibytes.ui.components.LessonCard
import dev.alex.bodhibytes.ui.components.WelcomeCard
import dev.alex.bodhibytes.ui.state.ReadingPosition
import dev.alex.bodhibytes.ui.theme.CardShape
import dev.alex.bodhibytes.ui.theme.cardBorder

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

@Composable
fun LessonListScreen(
    initialIndex: Int,
    initialOffset: Int,
    expandedKeys: Set<String>,
    readingPositions: Map<Int, ReadingPosition> = emptyMap(),
    completedQuizLessonIds: Set<Int> = emptySet(),
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
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val trimmedQuery = searchQuery.trim()

    val filteredLessons = remember(allLessons, trimmedQuery) {
        if (trimmedQuery.isEmpty()) {
            emptyList()
        } else {
            allLessons.filter { lesson ->
                lesson.title.contains(trimmedQuery, ignoreCase = true) ||
                    lesson.summary.contains(trimmedQuery, ignoreCase = true) ||
                    lesson.content.contains(trimmedQuery, ignoreCase = true)
            }
        }
    }

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
                        summary = "Foundational stages of the path, ethics, and core principles guiding practice."
                    )
                )
            )
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "The Three Marks of Existence",
                        lessons = allLessons.filter { it.id in 7..10 },
                        summary = "Exploring impermanence, unsatisfactoriness, and non-self across conditioned experience."
                    )
                )
            )
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "Compassion, Karma & Practice",
                        lessons = allLessons.filter { it.id in 11..15 },
                        summary = "Contemplating mortality, cause and effect, bodhicitta, the six perfections, and Tsongkhapa."
                    )
                )
            )
            add(
                HomeItem.RegularModule(
                    LessonModule(
                        title = "Interdependence & Wisdom (Emptiness)",
                        lessons = allLessons.filter { it.id in 16..21 },
                        summary = "Insight into interdependence, emptiness, Nāgārjuna, and Āryadeva."
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
                            lessons = allLessons.filter { it.id == 32 },
                            summary = "Lam gyi gtso bo rnam gsum by Tsongkhapa: determination to be free, supreme bodhicitta, and the wisdom realizing dependent arising and emptiness."
                        ),
                        LessonModule(
                            title = "A Guide to the Bodhisattva's Way of Life by Śāntideva",
                            lessons = allLessons.filter { it.id in 22..31 },
                            summary = "Bodhicaryāvatāra — Chapters 1 to 10 on the awakening mind, ethics, patience, effort, meditation, wisdom, and dedication."
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

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = initialIndex,
        initialFirstVisibleItemScrollOffset = initialOffset
    )

    var isJumpingToLibrary by remember { mutableStateOf(false) }

    LaunchedEffect(scrollToLibraryToken, libraryParentKey) {
        if (scrollToLibraryToken > 0) {
            isJumpingToLibrary = true
            searchQuery = ""
            val updatedKeys = if (libraryParentKey != null) {
                expandedKeys + libraryParentKey
            } else {
                expandedKeys
            }
            onExpandedKeysChanged(updatedKeys)
            withFrameNanos { }
            val lastIndex = (listState.layoutInfo.totalItemsCount - 1).coerceAtLeast(0)
            listState.scrollToItem(index = lastIndex, scrollOffset = Int.MAX_VALUE)
            onScrollChanged(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset)
            isJumpingToLibrary = false
            onScrollToLibraryHandled()
        }
    }

    LaunchedEffect(listState, isJumpingToLibrary, trimmedQuery) {
        if (isJumpingToLibrary || trimmedQuery.isNotEmpty()) return@LaunchedEffect
        snapshotFlow { listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset }
            .collect { (index, offset) ->
                onScrollChanged(index, offset)
            }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BodhiBytesTopBar(
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
                }
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
            item(key = "curriculum_search_bar") {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = CardShape,
                    placeholder = {
                        Text(
                            text = "Search lessons, terms, or texts…",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear Search",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                    )
                )
            }

            if (trimmedQuery.isNotEmpty()) {
                item(key = "search_results_header") {
                    val resultCount = filteredLessons.size
                    val resultLabel = if (resultCount == 1) "1 MATCH" else "$resultCount MATCHES"
                    Text(
                        text = resultLabel,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (filteredLessons.isEmpty()) {
                    item(key = "search_empty_state") {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = CardShape,
                            border = cardBorder(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Text(
                                text = "No lessons or texts matched \"$trimmedQuery\".",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(18.dp)
                            )
                        }
                    }
                } else {
                    items(filteredLessons, key = { "search_lesson_${it.id}" }) { lesson ->
                        LessonCard(
                            lesson = lesson,
                            hasSavedPosition = readingPositions[lesson.id]?.hasProgress() == true,
                            isQuizCompleted = completedQuizLessonIds.contains(lesson.id),
                            showSummary = true,
                            isExpandedLesson = false,
                            onClick = { onLessonSelected(lesson) }
                        )
                    }
                }
            } else {
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
                            val isExpanded = expandedKeys.contains(key)
                            val moduleSummary = buildModuleSummary(module.title, module.lessons, module.summary)

                            item(key = key) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            onExpandedKeysChanged(
                                                if (isExpanded) {
                                                    expandedKeys - key
                                                } else {
                                                    expandedKeys + key
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
                                            isQuizCompleted = completedQuizLessonIds.contains(lesson.id),
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
                                            isQuizCompleted = completedQuizLessonIds.contains(lesson.id),
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
                                                    isQuizCompleted = completedQuizLessonIds.contains(lesson.id),
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
}
