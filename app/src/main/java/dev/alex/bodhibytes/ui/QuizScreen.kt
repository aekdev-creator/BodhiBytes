package dev.alex.bodhibytes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Quiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    quiz: Quiz,
    lesson: Lesson,
    nextLesson: Lesson?,
    readingFontSize: ReadingFontSize = ReadingFontSize.DEFAULT,
    readingLineSpacing: ReadingLineSpacing = ReadingLineSpacing.COMFORTABLE,
    onQuizCompleted: (Int) -> Unit = {},
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onNextLessonClick: (Lesson) -> Unit
) {
    var currentQuestionIndex by remember(quiz.id) { mutableStateOf(0) }
    var selectedOptionIndex by remember(quiz.id) { mutableStateOf<Int?>(null) }
    var feedbackMessage by remember(quiz.id) { mutableStateOf<String?>(null) }
    var isCorrect by remember(quiz.id) { mutableStateOf(false) }
    var quizFinished by remember(quiz.id) { mutableStateOf(false) }
    var hasRecordedCompletion by remember(quiz.id) { mutableStateOf(false) }

    val questionFontSize = readingFontSize.titleFontSize
    val questionLineHeight = (questionFontSize.value * 1.3f).sp
    val bodyFontSize = readingFontSize.bodyFontSize
    val bodyLineHeight = readingLineSpacing.lineHeight
    val spacingDp = readingLineSpacing.paragraphSpacing.dp

    fun markCompletedOnce() {
        if (!hasRecordedCompletion) {
            hasRecordedCompletion = true
            onQuizCompleted(lesson.id)
        }
    }

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
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = (questionFontSize.value + 6f).sp,
                        lineHeight = ((questionFontSize.value + 6f) * 1.25f).sp
                    ),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(spacingDp + 4.dp))
                Text(
                    text = "You have successfully completed the quiz for this lesson.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = bodyFontSize,
                        lineHeight = bodyLineHeight
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        markCompletedOnce()
                        onHomeClick()
                    }
                ) {
                    Text("Return to Home", style = MaterialTheme.typography.labelLarge)
                }
                if (nextLesson != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = {
                            markCompletedOnce()
                            onNextLessonClick(nextLesson)
                        }
                    ) {
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
                verticalArrangement = Arrangement.spacedBy(spacingDp + 10.dp)
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(spacingDp)
                    ) {
                        Text(
                            text = "Question ${currentQuestionIndex + 1} of ${quiz.questions.size}",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = bodyFontSize,
                                lineHeight = bodyLineHeight
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = question.text,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = questionFontSize,
                                lineHeight = questionLineHeight
                            ),
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
                            containerColor = if (isSelected) {
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                            } else {
                                MaterialTheme.colorScheme.surface
                            }
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp + spacingDp),
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
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = bodyFontSize,
                                    lineHeight = bodyLineHeight
                                )
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(spacingDp + 10.dp)
                    ) {
                        if (feedbackMessage != null) {
                            Text(
                                text = feedbackMessage!!,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = bodyFontSize,
                                    lineHeight = bodyLineHeight
                                ),
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
                                        markCompletedOnce()
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
                                    if (currentQuestionIndex < quiz.questions.size - 1) {
                                        "Next Question"
                                    } else {
                                        "Finish Quiz"
                                    }
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
