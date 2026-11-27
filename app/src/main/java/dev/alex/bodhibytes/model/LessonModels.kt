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
    val quiz: Quiz? = null,
    val category: String? = null,
    val order: Int? = null
)

data class CurriculumModuleSpec(
    val key: String,
    val title: String,
    val summary: String,
    val lessonIds: List<Int>,
    val isLibraryCollection: Boolean = false,
    val subModules: List<CurriculumModuleSpec> = emptyList()
)

object CurriculumStructure {
    val modules: List<CurriculumModuleSpec> = listOf(
        CurriculumModuleSpec(
            key = "glossary",
            title = "Buddhist Words and Their Meanings",
            summary = "",
            lessonIds = listOf(1)
        ),
        CurriculumModuleSpec(
            key = "path",
            title = "The Path",
            summary = "Foundational stages of the path, ethics, and core principles guiding practice.",
            lessonIds = listOf(2, 3, 4, 5, 6)
        ),
        CurriculumModuleSpec(
            key = "three_marks",
            title = "The Three Marks of Existence",
            summary = "Exploring impermanence, unsatisfactoriness, and non-self across conditioned experience.",
            lessonIds = listOf(7, 8, 9, 10)
        ),
        CurriculumModuleSpec(
            key = "compassion_karma",
            title = "Compassion, Karma & Practice",
            summary = "Contemplating mortality, cause and effect, bodhicitta, the six perfections, and Tsongkhapa.",
            lessonIds = listOf(11, 12, 13, 14, 15)
        ),
        CurriculumModuleSpec(
            key = "interdependence_wisdom",
            title = "Interdependence & Wisdom (Emptiness)",
            summary = "Insight into interdependence, emptiness, Nāgārjuna, and Āryadeva.",
            lessonIds = listOf(16, 17, 18, 19, 20, 21)
        ),
        CurriculumModuleSpec(
            key = "library",
            title = "Library",
            summary = "Source texts and classical Buddhist works.",
            lessonIds = emptyList(),
            isLibraryCollection = true,
            subModules = listOf(
                CurriculumModuleSpec(
                    key = "three_principal_aspects",
                    title = "The Three Principal Aspects of the Path by Tsongkhapa",
                    summary = "Lam gyi gtso bo rnam gsum by Tsongkhapa: determination to be free, supreme bodhicitta, and the wisdom realizing dependent arising and emptiness.",
                    lessonIds = listOf(32)
                ),
                CurriculumModuleSpec(
                    key = "bodhicaryavatara",
                    title = "A Guide to the Bodhisattva's Way of Life by Śāntideva",
                    summary = "Bodhicaryāvatāra — Chapters 1 to 10 on the awakening mind, ethics, patience, effort, meditation, wisdom, and dedication.",
                    lessonIds = listOf(22, 23, 24, 25, 26, 27, 28, 29, 30, 31)
                )
            )
        )
    )

    val mainCurriculumLessonIds: List<Int> = modules
        .filter { !it.isLibraryCollection && it.key != "glossary" }
        .flatMap { it.lessonIds }

    val lastMainCurriculumLessonId: Int? = mainCurriculumLessonIds.lastOrNull()

    fun orderedLessons(allLessons: List<Lesson>): List<Lesson> {
        if (allLessons.isEmpty()) return emptyList()
        val byId = allLessons.associateBy { it.id }
        val configuredIds = buildList {
            modules.forEach { mod ->
                addAll(mod.lessonIds)
                mod.subModules.forEach { sub -> addAll(sub.lessonIds) }
            }
        }.distinct()

        val ordered = configuredIds.mapNotNull { byId[it] }
        val remaining = allLessons
            .filter { it.id !in configuredIds.toSet() }
            .sortedWith(compareBy({ it.order ?: Int.MAX_VALUE }, { it.id }))

        return ordered + remaining
    }
}
