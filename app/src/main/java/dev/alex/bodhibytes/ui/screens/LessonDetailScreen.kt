package dev.alex.bodhibytes.ui.screens

import android.app.Activity
import android.view.WindowManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.ui.components.BodhiBytesTopBar
import dev.alex.bodhibytes.ui.state.ReadingPosition
import dev.alex.bodhibytes.ui.theme.CardShape
import dev.alex.bodhibytes.ui.theme.ReadingFontSize
import dev.alex.bodhibytes.ui.theme.ReadingLineSpacing
import dev.alex.bodhibytes.ui.theme.cardBorder

private sealed interface ContentBlock {
    data class SectionHeading(val title: String) : ContentBlock
    data class NumberedCard(val number: String, val title: String, val body: String) : ContentBlock
    data class KeyValueCard(val term: String, val definition: String) : ContentBlock
    data class VerseBlock(val lines: List<String>) : ContentBlock
    data class SourceNote(val text: String) : ContentBlock
    data class BodyParagraph(val text: String) : ContentBlock
}

private val DefinitionRegex = Regex(
    "^([\\p{L}\\p{N}\\s()/&\\-–—]{1,45}):\\s+(.+)$",
    RegexOption.DOT_MATCHES_ALL
)

private val NumberedItemRegex = Regex(
    "^(\\d+)\\.\\s+([\\p{L}\\p{N}\\s()/&\\-–—]{1,65}):\\s+(.+)$",
    RegexOption.DOT_MATCHES_ALL
)

private val StandaloneHeadingRegex = Regex(
    "^([\\p{L}\\p{N}\\s()/&\\-–—]{3,75}):$"
)

private val NumberedStanzaRegex = Regex(
    "^(?:\\d+\\.)?\\d+(?:–\\d+)?\\.?\\s+(.+)$",
    RegexOption.DOT_MATCHES_ALL
)

private val RomanStanzaRegex = Regex(
    "^([IVXLCDM]+(?:–[IVXLCDM]+)?)\\.\\s+(.+)$",
    RegexOption.DOT_MATCHES_ALL
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

        if (NumberedStanzaRegex.matches(paragraph) || RomanStanzaRegex.matches(paragraph)) {
            return@map ContentBlock.VerseBlock(lines)
        }

        if (lines.size >= 3 && lines.all { it.length <= 75 }) {
            return@map ContentBlock.VerseBlock(lines)
        }

        val firstLine = lines.firstOrNull() ?: ""
        if (firstLine.length <= 90) {
            val defMatch = DefinitionRegex.matchEntire(paragraph)
            if (defMatch != null) {
                val term = defMatch.groupValues[1].trim().replace(Regex("\\s+"), " ")
                val body = defMatch.groupValues[2].trim()
                if (term.isNotEmpty() && body.isNotEmpty() && term.count { it == ' ' } <= 5) {
                    return@map ContentBlock.KeyValueCard(term = term, definition = body)
                }
            }
        }

        ContentBlock.BodyParagraph(paragraph)
    }
}

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
            BodhiBytesTopBar(
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
                progress = animatedProgress
            )
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
