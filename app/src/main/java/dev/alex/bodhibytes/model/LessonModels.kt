package dev.alex.bodhibytes.model

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String
)

data class Quiz(
    val id: Int,
    val title: String,
    val questions: List<Question>
)

data class Lesson(
    val id: Int,
    val title: String,
    val summary: String,
    val content: String,
    val quiz: Quiz? = null // Default value makes the parameter optional
)
