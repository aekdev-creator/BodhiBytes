package dev.alex.bodhibytes.ui.state

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.alex.bodhibytes.ui.theme.ReadingFontSize
import dev.alex.bodhibytes.ui.theme.ReadingLineSpacing

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

@Stable
class BodhiBytesAppState(
    private val sharedPreferences: SharedPreferences,
    initialLoadingCurriculum: Boolean = false
) {
    var lessonListScrollIndex by mutableIntStateOf(0)
    var lessonListScrollOffset by mutableIntStateOf(0)
    var lessonListExpandedKeys by mutableStateOf(emptySet<String>())
    var scrollToLibraryToken by mutableIntStateOf(0)
    var curriculumRetryToken by mutableIntStateOf(0)
    var isLoadingCurriculum by mutableStateOf(initialLoadingCurriculum)

    var bookmarksScrollIndex by mutableIntStateOf(0)
    var bookmarksScrollOffset by mutableIntStateOf(0)

    var isDarkMode by mutableStateOf(
        sharedPreferences.getBoolean(KEY_DARK_MODE, false)
    )
        private set

    var readingFontSize by mutableStateOf(
        ReadingFontSize.fromKey(
            sharedPreferences.getString(KEY_READING_FONT_SIZE, ReadingFontSize.DEFAULT.key)
        )
    )
        private set

    var readingLineSpacing by mutableStateOf(
        ReadingLineSpacing.fromKey(
            sharedPreferences.getString(
                KEY_READING_LINE_SPACING,
                ReadingLineSpacing.COMFORTABLE.key
            )
        )
    )
        private set

    var keepScreenAwake by mutableStateOf(
        sharedPreferences.getBoolean(KEY_KEEP_SCREEN_AWAKE, true)
    )
        private set

    var bookmarkedLessonIds by mutableStateOf(
        (sharedPreferences.getStringSet(KEY_BOOKMARKED_IDS, emptySet()) ?: emptySet())
            .mapNotNull { it.toIntOrNull() }
            .toSet()
    )
        private set

    var readingPositions by mutableStateOf(loadReadingPositions(sharedPreferences))
        private set

    var completedQuizLessonIds by mutableStateOf(
        (sharedPreferences.getStringSet(KEY_COMPLETED_QUIZ_LESSON_IDS, emptySet()) ?: emptySet())
            .mapNotNull { it.toIntOrNull() }
            .toSet()
    )
        private set

    var lastOpenedLessonId by mutableStateOf(
        sharedPreferences.getInt(KEY_LAST_OPENED_LESSON_ID, -1).takeIf { it != -1 }
    )
        private set

    fun updateLessonListScroll(index: Int, offset: Int) {
        if (lessonListScrollIndex != index || lessonListScrollOffset != offset) {
            lessonListScrollIndex = index
            lessonListScrollOffset = offset
        }
    }

    fun updateBookmarksScroll(index: Int, offset: Int) {
        if (bookmarksScrollIndex != index || bookmarksScrollOffset != offset) {
            bookmarksScrollIndex = index
            bookmarksScrollOffset = offset
        }
    }

    fun updateDarkMode(enabled: Boolean) {
        if (isDarkMode == enabled) return
        isDarkMode = enabled
        sharedPreferences.edit().putBoolean(KEY_DARK_MODE, enabled).apply()
    }

    fun updateReadingFontSize(size: ReadingFontSize) {
        if (readingFontSize == size) return
        readingFontSize = size
        sharedPreferences.edit().putString(KEY_READING_FONT_SIZE, size.key).apply()
    }

    fun updateReadingLineSpacing(spacing: ReadingLineSpacing) {
        if (readingLineSpacing == spacing) return
        readingLineSpacing = spacing
        sharedPreferences.edit().putString(KEY_READING_LINE_SPACING, spacing.key).apply()
    }

    fun updateKeepScreenAwake(enabled: Boolean) {
        if (keepScreenAwake == enabled) return
        keepScreenAwake = enabled
        sharedPreferences.edit().putBoolean(KEY_KEEP_SCREEN_AWAKE, enabled).apply()
    }

    fun toggleBookmark(lessonId: Int) {
        val updated = if (bookmarkedLessonIds.contains(lessonId)) {
            bookmarkedLessonIds - lessonId
        } else {
            bookmarkedLessonIds + lessonId
        }
        bookmarkedLessonIds = updated
        sharedPreferences.edit()
            .putStringSet(KEY_BOOKMARKED_IDS, updated.map { it.toString() }.toSet())
            .apply()
    }

    fun onLessonOpened(lessonId: Int) {
        if (!completedQuizLessonIds.contains(lessonId) && lastOpenedLessonId != lessonId) {
            lastOpenedLessonId = lessonId
            sharedPreferences.edit().putInt(KEY_LAST_OPENED_LESSON_ID, lessonId).apply()
        }
    }

    fun updateReadingPosition(lessonId: Int, position: ReadingPosition) {
        val current = readingPositions[lessonId] ?: ReadingPosition()
        if (current == position) return

        val updated = if (position.hasProgress()) {
            readingPositions + (lessonId to position)
        } else {
            readingPositions - lessonId
        }
        readingPositions = updated
        saveReadingPositions(sharedPreferences, updated)

        if (position.hasProgress() && !completedQuizLessonIds.contains(lessonId)) {
            onLessonOpened(lessonId)
        }
    }

    private fun clearActiveReadingState(lessonId: Int) {
        if (readingPositions.containsKey(lessonId)) {
            val updatedPositions = readingPositions - lessonId
            readingPositions = updatedPositions
            saveReadingPositions(sharedPreferences, updatedPositions)
        }
        if (lastOpenedLessonId == lessonId) {
            lastOpenedLessonId = null
            sharedPreferences.edit().remove(KEY_LAST_OPENED_LESSON_ID).apply()
        }
    }

    fun clearContinueReadingLesson(lessonId: Int) {
        clearActiveReadingState(lessonId)
    }

    fun handleQuizCompleted(completedLessonId: Int) {
        val updatedCompleted = completedQuizLessonIds + completedLessonId
        completedQuizLessonIds = updatedCompleted
        sharedPreferences.edit()
            .putStringSet(
                KEY_COMPLETED_QUIZ_LESSON_IDS,
                updatedCompleted.map { it.toString() }.toSet()
            )
            .apply()

        clearActiveReadingState(completedLessonId)
    }
}

@Composable
fun rememberBodhiBytesAppState(
    context: Context,
    initialLoadingCurriculum: Boolean = false
): BodhiBytesAppState {
    val prefs = remember(context) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    return remember(prefs) {
        BodhiBytesAppState(
            sharedPreferences = prefs,
            initialLoadingCurriculum = initialLoadingCurriculum
        )
    }
}
