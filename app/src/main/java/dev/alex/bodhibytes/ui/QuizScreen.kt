package dev.alex.bodhibytes.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Quiz

private val QuizCardShape = RoundedCornerShape(14.dp)

@Composable
private fun quizCardBorder(): BorderStroke =
    BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    quiz: Quiz,
    lesson: Lesson,
    nextLesson: Lesson? = null,
    readingFontSize: ReadingFontSize = ReadingFontSize.DEFAULT,
    readingLineSpacing: ReadingLineSpacing = ReadingLineSpacing.COMFORTABLE,
    onQuizCompleted: (Int) -> Unit = {},
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit = {},
    onNextLessonClick: (Lesson) -> Unit = {}
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }

    val totalQuestions = quiz.questions.size
    val currentQuestion = quiz.questions.getOrNull(currentQuestionIndex)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = quiz.title,
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
        if (isQuizFinished || currentQuestion == null) {
            LaunchedEffect(lesson.id) {
                onQuizCompleted(lesson.id)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = QuizCardShape,
                    border = quizCardBorder(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "QUIZ COMPLETE",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "You scored $score out of $totalQuestions",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Lesson: ${lesson.title}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Button(
                            onClick = {
                                score = 0
                                currentQuestionIndex = 0
                                selectedOptionIndex = null
                                isQuizFinished = false
                            },
                            shape = CircleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                        ) {
                            Text("Retake Quiz")
                        }
                        if (nextLesson != null) {
                            Button(
                                onClick = { onNextLessonClick(nextLesson) },
                                shape = CircleShape,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(46.dp)
                            ) {
                                Text(
                                    text = "Next Lesson: ${nextLesson.title}",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        OutlinedButton(
                            onClick = onBackClick,
                            shape = CircleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                        ) {
                            Text("Back to Lesson")
                        }
                        TextButton(
                            onClick = onHomeClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                        ) {
                            Text("Return to Curriculum")
                        }
                    }
                }
            }
        } else {
            val hasAnswered = selectedOptionIndex != null
            val isCorrect = selectedOptionIndex == currentQuestion.correctOptionIndex

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Text(
                        text = "QUESTION ${currentQuestionIndex + 1} OF $totalQuestions",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = currentQuestion.text,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = readingFontSize.titleFontSize,
                            lineHeight = readingLineSpacing.lineHeight
                        ),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                items(currentQuestion.options.size) { optionIndex ->
                    val optionText = currentQuestion.options[optionIndex]
                    val isSelected = selectedOptionIndex == optionIndex
                    val isCorrectOption = optionIndex == currentQuestion.correctOptionIndex

                    val containerColor = when {
                        !hasAnswered -> MaterialTheme.colorScheme.surface
                        isCorrectOption -> MaterialTheme.colorScheme.primaryContainer
                        isSelected -> MaterialTheme.colorScheme.secondaryContainer
                        else -> MaterialTheme.colorScheme.surface
                    }

                    val borderStroke = when {
                        !hasAnswered -> quizCardBorder()
                        isCorrectOption -> BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                        isSelected -> BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary)
                        else -> quizCardBorder()
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !hasAnswered) {
                                selectedOptionIndex = optionIndex
                                if (optionIndex == currentQuestion.correctOptionIndex) {
                                    score += 1
                                }
                            },
                        shape = QuizCardShape,
                        border = borderStroke,
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        colors = CardDefaults.cardColors(containerColor = containerColor)
                    ) {
                        Text(
                            text = optionText,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = readingFontSize.bodyFontSize,
                                lineHeight = readingLineSpacing.lineHeight
                            ),
                            fontWeight = if (isSelected || (hasAnswered && isCorrectOption)) {
                                FontWeight.SemiBold
                            } else {
                                FontWeight.Normal
                            },
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }

                if (hasAnswered) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = QuizCardShape,
                            border = quizCardBorder(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = if (isCorrect) "Correct" else "Not quite",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCorrect) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onSurface
                                    }
                                )
                                Text(
                                    text = currentQuestion.explanation,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = readingFontSize.bodyFontSize,
                                        lineHeight = readingLineSpacing.lineHeight
                                    ),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    item {
                        Button(
                            onClick = {
                                if (currentQuestionIndex + 1 < totalQuestions) {
                                    currentQuestionIndex += 1
                                    selectedOptionIndex = null
                                } else {
                                    isQuizFinished = true
                                }
                            },
                            shape = CircleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(
                                text = if (currentQuestionIndex + 1 < totalQuestions) "Next Question" else "Finish Quiz",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}
