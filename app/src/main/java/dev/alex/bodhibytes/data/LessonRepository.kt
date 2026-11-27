package dev.alex.bodhibytes.data

import android.content.Context
import android.util.Log
import dev.alex.bodhibytes.model.Lesson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

object LessonRepository {
    private const val TAG = "LessonRepository"
    private const val CURRICULUM_ASSET_PATH = "curriculum.json"

    @Volatile
    private var cachedLessons: List<Lesson>? = null

    private val jsonParser = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    suspend fun getCurriculum(
        context: Context,
        forceRefresh: Boolean = false
    ): List<Lesson> {
        if (!forceRefresh) {
            cachedLessons?.let { return it }
        }

        return withContext(Dispatchers.IO) {
            if (!forceRefresh) {
                cachedLessons?.let { return@withContext it }
            }

            val decoded = runCatching {
                val rawJson = context.assets.open(CURRICULUM_ASSET_PATH)
                    .bufferedReader()
                    .use { it.readText() }
                jsonParser.decodeFromString<List<Lesson>>(rawJson)
            }.onFailure { error ->
                Log.e(TAG, "Failed to load $CURRICULUM_ASSET_PATH", error)
            }.getOrDefault(emptyList())

            if (decoded.isNotEmpty()) {
                cachedLessons = decoded
            }
            decoded
        }
    }

    fun getCachedCurriculum(): List<Lesson> = cachedLessons.orEmpty()
}
