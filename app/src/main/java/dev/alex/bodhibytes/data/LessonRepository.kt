package dev.alex.bodhibytes.data

import android.content.Context
import android.util.Log
import dev.alex.bodhibytes.model.Lesson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

sealed interface ContentBlock {
    data class SectionHeading(val title: String) : ContentBlock
    data class NumberedCard(val number: String, val title: String, val body: String) : ContentBlock
    data class KeyValueCard(val term: String, val definition: String) : ContentBlock
    data class VerseBlock(val lines: List<String>) : ContentBlock
    data class SourceNote(val text: String) : ContentBlock
    data class BodyParagraph(val text: String) : ContentBlock
}

private val ParagraphSplitRegex = Regex("\\n\\s*\\n")
private val WhitespaceCollapseRegex = Regex("\\s+")

private val DefinitionPrefixRegex = Regex(
    "^([\\p{L}\\p{N}\\s()/&\\-–—]{1,45}):\\s+(\\S.*)$"
)

private val NumberedItemRegex = Regex(
    "^(\\d+)\\.\\s+([\\p{L}\\p{N}\\s()/&\\-–—]{1,65}):\\s+(\\S.*)$",
    RegexOption.DOT_MATCHES_ALL
)

private val StandaloneHeadingRegex = Regex(
    "^([\\p{L}\\p{N}\\s()/&\\-–—]{3,75}):$"
)

private val NumberedStanzaRegex = Regex(
    "^(?:\\d+\\.)?\\d+(?:–\\d+)?\\.?\\s+\\S.*$",
    RegexOption.DOT_MATCHES_ALL
)

private val RomanStanzaRegex = Regex(
    "^([IVXLCDM]+(?:–[IVXLCDM]+)?)\\.\\s+\\S.*$",
    RegexOption.DOT_MATCHES_ALL
)

fun parseContentBlocks(content: String): List<ContentBlock> {
    if (content.isBlank()) return emptyList()

    val rawParagraphs = content.split(ParagraphSplitRegex)
    val result = ArrayList<ContentBlock>(rawParagraphs.size)

    for (rawParagraph in rawParagraphs) {
        val paragraph = rawParagraph.trim()
        if (paragraph.isEmpty()) continue

        if (paragraph.startsWith("Source & Study Note:") ||
            paragraph.startsWith("Source and Translation Note") ||
            paragraph.startsWith("SOURCE AND TRANSLATION NOTE")
        ) {
            result.add(ContentBlock.SourceNote(paragraph))
            continue
        }

        val lines = paragraph.lines().mapNotNull { line ->
            val trimmed = line.trim()
            trimmed.ifEmpty { null }
        }
        if (lines.isEmpty()) continue

        if (lines.size == 1) {
            val singleLine = lines[0]
            if (singleLine.endsWith(':') && singleLine.length in 4..76 && singleLine.count { it == ' ' } <= 9) {
                val headingMatch = StandaloneHeadingRegex.matchEntire(singleLine)
                if (headingMatch != null) {
                    result.add(ContentBlock.SectionHeading(headingMatch.groupValues[1].trim()))
                    continue
                }
            }
        }

        val firstLine = lines[0]
        if (firstLine.isNotEmpty() && firstLine[0].isDigit()) {
            val numberedMatch = NumberedItemRegex.matchEntire(paragraph)
            if (numberedMatch != null) {
                result.add(
                    ContentBlock.NumberedCard(
                        number = numberedMatch.groupValues[1],
                        title = numberedMatch.groupValues[2].trim().replace(WhitespaceCollapseRegex, " "),
                        body = numberedMatch.groupValues[3].trim()
                    )
                )
                continue
            }
        }

        if (lines.size >= 2 && (NumberedStanzaRegex.matches(paragraph) || RomanStanzaRegex.matches(paragraph))) {
            result.add(ContentBlock.VerseBlock(lines))
            continue
        }

        if (lines.size >= 3 && lines.all { it.length <= 75 }) {
            result.add(ContentBlock.VerseBlock(lines))
            continue
        }

        val colonIndex = firstLine.indexOf(':')
        if (colonIndex in 1..45) {
            val prefixPart = firstLine.substring(0, colonIndex)
            if (prefixPart.count { it == ' ' } <= 4) {
                val prefixMatch = DefinitionPrefixRegex.matchEntire(firstLine)
                if (prefixMatch != null) {
                    val term = prefixMatch.groupValues[1].trim().replace(WhitespaceCollapseRegex, " ")
                    val remainderFirstLine = prefixMatch.groupValues[2].trim()
                    val body = if (lines.size == 1) {
                        remainderFirstLine
                    } else {
                        buildString {
                            if (remainderFirstLine.isNotEmpty()) append(remainderFirstLine)
                            for (i in 1 until lines.size) {
                                if (isNotEmpty()) append('\n')
                                append(lines[i])
                            }
                        }
                    }

                    if (term.isNotEmpty() && body.isNotEmpty()) {
                        result.add(ContentBlock.KeyValueCard(term = term, definition = body))
                        continue
                    }
                }
            }
        }

        result.add(ContentBlock.BodyParagraph(paragraph))
    }

    return result
}

object LessonRepository {
    private const val TAG = "LessonRepository"
    private const val CURRICULUM_ASSET_PATH = "curriculum.json"

    @Volatile
    private var cachedLessons: List<Lesson>? = null

    @Volatile
    private var cachedBlocksByLessonId: Map<Int, List<ContentBlock>> = emptyMap()

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
                val parsedMap = decoded.associate { lesson ->
                    lesson.id to parseContentBlocks(lesson.content)
                }
                cachedBlocksByLessonId = parsedMap
                cachedLessons = decoded
            }
            decoded
        }
    }

    fun getCachedCurriculum(): List<Lesson> = cachedLessons.orEmpty()

    fun getCachedContentBlocks(lessonId: Int): List<ContentBlock>? = cachedBlocksByLessonId[lessonId]

    suspend fun getContentBlocks(lesson: Lesson): List<ContentBlock> {
        cachedBlocksByLessonId[lesson.id]?.let { return it }
        return withContext(Dispatchers.Default) {
            cachedBlocksByLessonId[lesson.id] ?: parseContentBlocks(lesson.content).also { parsed ->
                cachedBlocksByLessonId = cachedBlocksByLessonId + (lesson.id to parsed)
            }
        }
    }
}
