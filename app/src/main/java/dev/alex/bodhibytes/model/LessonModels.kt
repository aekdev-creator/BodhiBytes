package dev.alex.bodhibytes.model

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String
)

@Serializable
data class Quiz(
    val id: Int,
    val title: String,
    val questions: List<Question>
)

@Serializable
data class Lesson(
    val id: Int,
    val title: String,
    val summary: String,
    val content: String,
    val quiz: Quiz? = null
)
