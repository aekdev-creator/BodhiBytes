package dev.alex.bodhibytes.data

import android.content.Context
import dev.alex.bodhibytes.model.Lesson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

object LessonRepository {
    private const val CURRICULUM_ASSET_PATH = "curriculum.json"

    @Volatile
    private var cachedLessons: List<Lesson>? = null

    private val jsonParser = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    suspend fun getCurriculum(context: Context): List<Lesson> {
        cachedLessons?.let { return it }

        return withContext(Dispatchers.IO) {
            cachedLessons?.let { return@withContext it }

            val rawJson = context.assets.open(CURRICULUM_ASSET_PATH)
                .bufferedReader()
                .use { it.readText() }

            val decoded = jsonParser.decodeFromString<List<Lesson>>(rawJson)
            cachedLessons = decoded
            decoded
        }
    }

    fun getCachedCurriculum(): List<Lesson> = cachedLessons.orEmpty()
}
