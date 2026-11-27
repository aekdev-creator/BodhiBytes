package dev.alex.bodhibytes.ui.state

import android.content.SharedPreferences

const val PREFS_NAME = "bodhibytes_prefs"
const val KEY_COMPLETED_QUIZ_LESSON_IDS = "completed_quiz_lesson_ids"
const val KEY_LAST_OPENED_LESSON_ID = "last_opened_lesson_id"
const val KEY_READING_POSITIONS = "reading_positions"
const val KEY_BOOKMARKED_IDS = "bookmarked_ids"
const val KEY_DARK_MODE = "dark_mode"
const val KEY_READING_FONT_SIZE = "reading_font_size"
const val KEY_READING_LINE_SPACING = "reading_line_spacing"
const val KEY_KEEP_SCREEN_AWAKE = "keep_screen_awake"

data class ReadingPosition(
    val index: Int = 0,
    val offset: Int = 0
) {
    fun hasProgress(): Boolean = index > 0 || offset > 0

    fun encode(): String = "$index:$offset"

    companion object {
        fun decode(raw: String?): ReadingPosition {
            if (raw.isNullOrBlank()) return ReadingPosition()
            val parts = raw.split(":")
            if (parts.size != 2) return ReadingPosition()
            val index = parts[0].toIntOrNull()?.coerceAtLeast(0) ?: 0
            val offset = parts[1].toIntOrNull()?.coerceAtLeast(0) ?: 0
            return ReadingPosition(index = index, offset = offset)
        }
    }
}

fun loadReadingPositions(prefs: SharedPreferences): Map<Int, ReadingPosition> {
    val rawEntries = prefs.getStringSet(KEY_READING_POSITIONS, emptySet()) ?: emptySet()
    val result = mutableMapOf<Int, ReadingPosition>()
    for (entry in rawEntries) {
        val parts = entry.split("=", limit = 2)
        if (parts.size == 2) {
            val lessonId = parts[0].toIntOrNull()
            if (lessonId != null) {
                result[lessonId] = ReadingPosition.decode(parts[1])
            }
        }
    }
    return result
}

fun saveReadingPositions(
    prefs: SharedPreferences,
    positions: Map<Int, ReadingPosition>
) {
    val serialized = positions
        .filterValues { it.hasProgress() }
        .map { (lessonId, pos) -> "$lessonId=${pos.encode()}" }
        .toSet()
    prefs.edit().putStringSet(KEY_READING_POSITIONS, serialized).apply()
}
