package dev.alex.bodhibytes.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Quiz
import dev.alex.bodhibytes.ui.components.BodhiBytesTopBar
import dev.alex.bodhibytes.ui.theme.CardShape
import dev.alex.bodhibytes.ui.theme.ReadingFontSize
import dev.alex.bodhibytes.ui.theme.ReadingLineSpacing
import dev.alex.bodhibytes.ui.theme.cardBorder

@Composable
fun QuizScreen(
    quiz: Quiz,
    lesson: Lesson,
    nextLesson: Lesson? = null,
    showGoToLibrary: Boolean = false,
    readingFontSize: ReadingFontSize = ReadingFontSize.DEFAULT,
    readingLineSpacing: ReadingLineSpacing = ReadingLineSpacing.COMFORTABLE,
    onQuizCompleted: (Int) -> Unit = {},
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit = {},
    onNextLessonClick: (Lesson) -> Unit = {},
    onGoToLibraryClick: () -> Unit = {}
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }

    val totalQuestions = quiz.questions.size
    val currentQuestion = quiz.questions.getOrNull(currentQuestionIndex)

    val rawProgress = when {
        totalQuestions == 0 || isQuizFinished -> 1f
        else -> {
            val answeredOffset = if (selectedOptionIndex != null) 1 else 0
            (currentQuestionIndex + answeredOffset).toFloat() / totalQuestions.toFloat()
        }
    }
    val animatedProgress by animateFloatAsState(
        targetValue = rawProgress,
        label = "quiz_progress"
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BodhiBytesTopBar(
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
                progress = animatedProgress
            )
        }
    ) { innerPadding ->
        if (isQuizFinished || currentQuestion == null) {
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
                        if (showGoToLibrary) {
                            Button(
                                onClick = onGoToLibraryClick,
                                shape = CircleShape,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(46.dp)
                            ) {
                                Text(
                                    text = "Go to Library",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        } else if (nextLesson != null) {
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
                        OutlinedButton(
                            onClick = onHomeClick,
                            shape = CircleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                        ) {
                            Text("Return to Home")
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
                        !hasAnswered -> cardBorder()
                        isCorrectOption -> BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                        isSelected -> BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary)
                        else -> cardBorder()
                    }

                    val statusTag = when {
                        !hasAnswered -> null
                        isSelected && isCorrectOption -> "Your choice · Correct"
                        isSelected -> "Your choice"
                        isCorrectOption -> "Correct answer"
                        else -> null
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
                        shape = CardShape,
                        border = borderStroke,
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        colors = CardDefaults.cardColors(containerColor = containerColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
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
                                modifier = Modifier.weight(1f)
                            )
                            if (statusTag != null) {
                                Text(
                                    text = statusTag,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isCorrectOption) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    }
                                )
                            }
                        }
                    }
                }

                if (hasAnswered) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
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
                                    onQuizCompleted(lesson.id)
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
