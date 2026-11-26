package dev.alex.bodhibytes.data

import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Question
import dev.alex.bodhibytes.model.Quiz

object LessonRepository {

    fun getCurriculum(): List<Lesson> {
        return foundationalPathLessons() +
            insightAndSufferingLessons() +
            interdependenceAndEmptinessLessons() +
            compassionAndBodhicittaLessons() +
            libraryLessons() +
            bodhisattvaWayOfLifeLessons()
    }

    private fun quizIdFor(lessonId: Int): Int = 1000 + lessonId

    private fun foundationalPathLessons(): List<Lesson> = listOf(
        Lesson(
            id = 1,
            title = "Buddhist Words and Their Meanings",
            summary = "Explore key traditional Pali and Sanskrit terms used throughout Buddhist philosophy to deepen your understanding of the teachings.",
            content = """
                Understanding the foundational terminology of Buddhism opens a clearer window into the philosophy and meditative traditions.

                Dharma: The Buddha's teachings.

                Nirvana: Extinguishment of attachment and grasping, resulting in peace.

                Bodhi: Enlightenment.

                Citta: Mind.

                Bodhicitta: The enlightened mind.

                Sattva: Living beings.

                Bodhisattva: A person on the path to enlightenment out of compassion for all beings.

                Sentient Beings: Living creatures who view the world through their senses.

                Equanimity: Unshakeable emotional balance undisturbed by fluctuating life conditions.

                Sangha: The community of monks, nuns, and practitioners walking the path together.

                Samsara: The ongoing, repetitive cycle of birth, existence, and dying.

                Prajna: Transcendent wisdom and intuitive insight into reality and emptiness.

                Metta: Boundless loving-kindness and goodwill toward all living beings.

                Kalyana-mitra: A spiritual friend, mentor, or guide along the path.

                Upaya: Skillful means or adaptive strategies used to guide practice.

                Epithet: A descriptive title or honorary phrase used to characterize a figure or quality, such as a title of the Buddha.

                Tathagata: An epithet for the Buddha, meaning one who has realized ultimate truth.

                Sugata: An epithet for the Buddha meaning "Well-Gone" or "One Who Has Blissfully Gone," signifying complete awakening and liberation.

                Madhyamaka: The "Middle Way" school demonstrating the emptiness of all phenomena.

                Precepts: Voluntary ethical training guidelines undertaken to cultivate non-harm, inner peace, and clarity of mind.

                Ascetic: A practitioner who voluntarily renounces worldly comforts and sensual indulgences to cultivate self-discipline and spiritual focus.

                Monastic: A member of a dedicated religious community—such as a monk or nun—who lives a structured life of vows, study, and practice.

                Alms: Food, clothing, or offerings generously given by laypeople to support monks, nuns, and mendicants.

                Mendicant: A wandering ascetic or monastic practitioner who relies on alms and lives a simple, non-possessive life.

                Sutta / Sutra: Discourse attributed to the Buddha.

                Concept / Conceptual: A memory or thought about something.

                Phenomena: What you observe with your senses, observable events.

                Dependent Origination (Dependent Arising): Everything you observe results from causes and conditions.

                Ignorance: Viewing what you observe to be more than dependent arising by conceptualizing.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(1),
                title = "Quiz: Buddhist Words and Their Meanings",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What does Phenomena mean in this lesson?",
                        options = listOf(
                            "A memory or thought about something",
                            "What you observe with your senses, observable events",
                            "A wandering ascetic who lives by begging",
                            "Unshakeable emotional balance in daily life"
                        ),
                        correctOptionIndex = 1,
                        explanation = "This lesson defines Phenomena as what you observe with your senses, observable events."
                    ),
                    Question(
                        id = 2,
                        text = "How is Ignorance defined in this lesson?",
                        options = listOf(
                            "Viewing what you observe to be more than dependent arising by conceptualizing.",
                            "The community of monks, nuns, and practitioners",
                            "Transcendent wisdom and intuitive insight",
                            "Discourse attributed to the Buddha."
                        ),
                        correctOptionIndex = 0,
                        explanation = "This lesson defines Ignorance as viewing what you observe to be more than dependent arising by conceptualizing."
                    ),
                    Question(
                        id = 3,
                        text = "What does Concept / Conceptual refer to in this lesson?",
                        options = listOf(
                            "Boundless loving-kindness toward living beings",
                            "Extinguishment of attachment and grasping",
                            "A memory or thought about something",
                            "The repetitive cycle of birth, existence, and dying"
                        ),
                        correctOptionIndex = 2,
                        explanation = "This lesson defines Concept / Conceptual as a memory or thought about something."
                    ),
                    Question(
                        id = 4,
                        text = "What are Precepts according to this lesson?",
                        options = listOf(
                            "A spiritual friend or mentor along the path",
                            "Skillful means or adaptive strategies for practice",
                            "An epithet meaning \"Well-Gone\" or \"One Who Has Blissfully Gone\"",
                            "Voluntary ethical training guidelines undertaken to cultivate non-harm, inner peace, and clarity of mind"
                        ),
                        correctOptionIndex = 3,
                        explanation = "This lesson defines Precepts as voluntary ethical training guidelines undertaken to cultivate non-harm, inner peace, and clarity of mind."
                    ),
                    Question(
                        id = 5,
                        text = "What does Dependent Origination (Dependent Arising) mean in this lesson?",
                        options = listOf(
                            "Everything you observe results from causes and conditions.",
                            "Renouncing all worldly comforts for life",
                            "A descriptive title used for the Buddha",
                            "The enlightened mind of awakening"
                        ),
                        correctOptionIndex = 0,
                        explanation = "This lesson states that Dependent Origination (Dependent Arising) means everything you observe results from causes and conditions."
                    )
                )
            )
        ),
        Lesson(
            id = 2,
            title = "The Middle Way",
            summary = "Discovering the balanced path between over-indulgence and self-denial as taught in the first discourse.",
            content = """
                The Middle Way is the core framework of Buddhist teachings. It was first introduced by the Buddha in his inaugural discourse delivered to five monks at the Deer Park.

                The Buddha identified two physical and emotional extremes to avoid:

                1. Over-Indulgence: Pursuing sensual pleasures which are low, coarse, unworthy, and unprofitable.

                2. Self-Denial: Avoiding worldly pleasures and fasting which is painful, unworthy, and unprofitable.

                Avoiding both extremes, the Buddha realized the Middle Path—leading to calm, insight, enlightenment, and ultimate peace.

                This Middle Path is the Noble Eightfold Path, consisting of eight practical factors for ethical and mental development.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(2),
                title = "Quiz: The Middle Way",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What are the two extremes that a practitioner must avoid?",
                        options = listOf(
                            "Wealth and poverty",
                            "Dogma and skepticism",
                            "Over-indulgence and self-denial",
                            "Activity and laziness"
                        ),
                        correctOptionIndex = 2,
                        explanation = "The Buddha explicitly warned against over-indulgence and self-denial."
                    ),
                    Question(
                        id = 2,
                        text = "Where did the Buddha deliver his first discourse?",
                        options = listOf(
                            "The Deer Park",
                            "A bamboo grove",
                            "The foothills of the Himalayas",
                            "An assembly hall"
                        ),
                        correctOptionIndex = 0,
                        explanation = "The discourse was given to five monks at the Deer Park."
                    ),
                    Question(
                        id = 3,
                        text = "What specifically constitutes the Middle Path?",
                        options = listOf(
                            "Strict adherence to ritual sacrifices",
                            "The Noble Eightfold Path",
                            "The Four Noble Truths alone",
                            "Living a life of moderate luxury"
                        ),
                        correctOptionIndex = 1,
                        explanation = "The Middle Path is defined as the Noble Eightfold Path."
                    )
                )
            )
        ),
        Lesson(
            id = 3,
            title = "The Four Noble Truths",
            summary = "Building upon the Middle Way, this lesson explores the foundational diagnosis of human suffering, its root cause, its cessation, and the path to freedom.",
            content = """
                Having established the Middle Way as the practical method to live by, the Buddha detailed the four fundamental truths that form the backbone of his philosophy. They act like a physician's diagnosis: identifying the condition, finding its root cause, determining whether a cure is possible, and prescribing the treatment.

                1. The Truth of Suffering: Acknowledging that life involves inevitable challenges, including birth, aging, sickness, death, and emotional distress.

                2. The Origin of Suffering: Tracing the root cause back to craving, attachment, and passionate greed.

                3. The Cessation of Suffering: Understanding that true peace is found through the complete release and relinquishment of that craving.

                4. The Path to Cessation: Applying the practical treatment — the Noble Eightfold Path.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(3),
                title = "Quiz: The Four Noble Truths",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "How are the Four Noble Truths structurally compared in their approach?",
                        options = listOf(
                            "A physician's medical diagnosis and treatment plan",
                            "An architectural blueprint for a temple",
                            "A legal contract between kingdoms",
                            "A map of historical trade routes"
                        ),
                        correctOptionIndex = 0,
                        explanation = "They act like a physician's diagnosis: identifying the condition, finding its root cause, determining whether a cure is possible, and prescribing the treatment."
                    ),
                    Question(
                        id = 2,
                        text = "Which of the following completes the fourth truth regarding the path to cessation?",
                        options = listOf(
                            "Strict isolation from society",
                            "Accumulating merit through wealth",
                            "The Noble Eightfold Path",
                            "Enduring hardship without complaint"
                        ),
                        correctOptionIndex = 2,
                        explanation = "The path to cessation is the Noble Eightfold Path."
                    ),
                    Question(
                        id = 3,
                        text = "What is identified as the root origin of suffering in the second truth?",
                        options = listOf(
                            "Lack of formal education",
                            "Craving, attachment, and passionate greed",
                            "Physical exhaustion",
                            "Environmental changes"
                        ),
                        correctOptionIndex = 1,
                        explanation = "The origin of suffering is traced back to craving, attachment, and passionate greed."
                    )
                )
            )
        ),
        Lesson(
            id = 4,
            title = "The Noble Eightfold Path",
            summary = "Exploring the eight practical factors of the path that guide ethics, concentration, and wisdom.",
            content = """
                The Noble Eightfold Path is the practical guide designed to lead away from suffering and toward awakening. It is categorized into three essential areas: Ethics, concentration, and wisdom.

                The eight factors are:

                Right Action: Refraining from harming living beings, taking what is not given, and misconduct.

                Right Speech: Abstaining from false, harsh, divisive, or idle speech.

                Right Livelihood: Earning a living in a way that does not cause harm to others.

                Right Effort: Cultivating wholesome states of mind and releasing unwholesome ones.

                Right Mindfulness: Maintaining clear awareness of body, feelings, and mind.

                Right Concentration: Developing deep mental focus, stability, and stillness.

                Right Understanding: Developing a clear view of reality and cause-and-effect.

                Right Thought: Cultivating intentions of renunciation, goodwill, and harmlessness.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(4),
                title = "Quiz: The Noble Eightfold Path",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "Which factor involves abstaining from false, harsh, divisive, or idle speech?",
                        options = listOf(
                            "Right Action",
                            "Right Speech",
                            "Right Livelihood",
                            "Right Effort"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Right Speech is abstaining from false, harsh, divisive, or idle speech."
                    ),
                    Question(
                        id = 2,
                        text = "Into what three essential areas is the Noble Eightfold Path categorized?",
                        options = listOf(
                            "History, philosophy, and geography",
                            "Wealth, health, and status",
                            "Ethics, concentration, and wisdom",
                            "Ritual, prayer, and sacrifice"
                        ),
                        correctOptionIndex = 2,
                        explanation = "The path is categorized into three essential areas: Ethics, concentration, and wisdom."
                    )
                )
            )
        ),
        Lesson(
            id = 5,
            title = "The Three Higher Trainings",
            summary = "Engaging in the practical triad of higher ethics, higher concentration, and higher wisdom.",
            content = """
                The path of practice is structured around the Three Higher Trainings, which systematically develop human potential across ethical, mental, and cognitive dimensions:

                1. Higher Ethics (Morality): Establishing a clean, harmless foundation of speech, action, and livelihood to create safety and mental calm.

                2. Higher Concentration (Meditation): Developing deep mental stability, focus, and clarity through sustained mindfulness.

                3. Higher Wisdom: Applying a sharp, investigative understanding to see through illusions and perceive reality as it is.

                These three trainings reinforce one another: ethics supports calm concentration, and calm concentration empowers clear, liberating wisdom.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(5),
                title = "Quiz: The Three Higher Trainings",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "How do the three trainings reinforce one another?",
                        options = listOf(
                            "They operate completely independently without overlap",
                            "Ethics supports calm concentration, and calm concentration empowers clear, liberating wisdom",
                            "Wisdom replaces the need for ethical conduct entirely",
                            "Concentration is only useful for physical exercise"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Ethics supports calm concentration, and calm concentration empowers clear, liberating wisdom."
                    ),
                    Question(
                        id = 2,
                        text = "What are the three components of the Higher Trainings?",
                        options = listOf(
                            "Wealth, health, and status",
                            "Memory, speed, and agility",
                            "Prayer, ritual, and sacrifice",
                            "Higher ethics, higher concentration, and higher wisdom"
                        ),
                        correctOptionIndex = 3,
                        explanation = "The Three Higher Trainings are Higher Ethics (Morality), Higher Concentration (Meditation), and Higher Wisdom."
                    )
                )
            )
        ),
        Lesson(
            id = 6,
            title = "The Five Moral Precepts",
            summary = "Explore the foundational ethical training of the Five Precepts and how mindful restraint cultivates inner peace, trust, and clarity.",
            content = """
                What Are the Five Moral Precepts?

                In Buddhism, the Five Moral Precepts form the foundational ethical framework for lay practitioners and beginners on the path. Rather than acting as rigid commandments imposed from outside, the precepts are voluntary training rules undertaken to free the mind from harm, guilt, and agitation.

                Ethical conduct stabilizes attention and supports meditative clarity and insight. When our actions do not create fear or regret, the mind naturally settles more easily.

                1. Abstaining from Taking Life: Practicing non-harm and reverence for all living beings. This training cultivates kindness, patience, and compassion, moving away from cruelty, anger, and violence toward both people and other creatures.

                2. Abstaining from Taking What Is Not Given: Practicing generosity, honesty, and respect for others' property and well-being. It means refraining from theft, fraud, manipulation, or taking advantage of others' vulnerabilities.

                3. Abstaining from Sexual Misconduct: Practicing care, integrity, and respect in relationships and intimacy. It means avoiding harm, exploitation, deception, or breaking trust in ways that cause emotional or relational suffering.

                4. Abstaining from False Speech: Practicing truthfulness, reliability, and wholesome communication. Beyond avoiding outright lies, this training encourages speech that is honest, helpful, timely, and free from harshness, gossip, or division.

                5. Abstaining from Intoxicants That Cloud the Mind: Practicing mindfulness and clear awareness by avoiding alcohol and intoxicants that dull perception or weaken self-restraint. Because heedlessness makes it much easier to break the other four precepts, this fifth precept serves as a safeguard for overall mindfulness.

                Living the Precepts Skillfully

                The precepts are meant to be practiced with wisdom and self-compassion rather than perfectionism or judgment. If a precept is slipped on, reflecting honestly on what happened, learning from it, and renewing one's intention keeps the ethical path alive and constructive.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(6),
                title = "Quiz: The Five Moral Precepts",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What positive quality is cultivated by the first precept, abstaining from taking life?",
                        options = listOf(
                            "Detached indifference toward all living things",
                            "Strict debate over philosophical doctrines",
                            "Material wealth and social status",
                            "Non-harm, kindness, and compassion toward living beings"
                        ),
                        correctOptionIndex = 3,
                        explanation = "Abstaining from taking life cultivates non-harm, kindness, patience, and compassion toward living beings."
                    ),
                    Question(
                        id = 2,
                        text = "In Buddhist practice, how are the Five Moral Precepts best understood?",
                        options = listOf(
                            "Punitive commandments enforced by a supreme judge",
                            "Voluntary ethical training rules that support a calm, clear mind",
                            "Advanced rituals reserved only for monastic scholars",
                            "Strict rules that replace meditation and wisdom"
                        ),
                        correctOptionIndex = 1,
                        explanation = "The precepts are voluntary ethical training rules that free the mind from harm, guilt, and agitation."
                    ),
                    Question(
                        id = 3,
                        text = "Why is the fifth precept (abstaining from intoxicants that cloud the mind) especially important?",
                        options = listOf(
                            "Clouding the mind dulls perception and makes breaking the other precepts much easier",
                            "It is the only precept mentioned in early Buddhist texts",
                            "It requires avoiding all sweets and warm beverages",
                            "It replaces the need for honest speech"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Intoxicants dull perception or weaken self-restraint, and heedlessness makes it much easier to break the other four precepts."
                    ),
                    Question(
                        id = 4,
                        text = "Which of the following is most aligned with the fourth precept (abstaining from false speech)?",
                        options = listOf(
                            "Staying completely silent at all times",
                            "Speaking only to win arguments",
                            "Cultivating truthfulness, reliability, and wholesome communication",
                            "Repeating rumors so others stay informed"
                        ),
                        correctOptionIndex = 2,
                        explanation = "The fourth precept encourages truthfulness, reliability, and wholesome communication while avoiding lies, harshness, gossip, or division."
                    ),
                    Question(
                        id = 5,
                        text = "How does ethical conduct relate to the rest of the Buddhist path?",
                        options = listOf(
                            "It has no connection to meditation or insight",
                            "It stabilizes attention and supports meditative clarity and insight",
                            "It is only practiced after full enlightenment is achieved",
                            "It focuses solely on outward appearances"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Ethical conduct stabilizes attention and supports meditative clarity and insight."
                    )
                )
            )
        )
    )

    private fun insightAndSufferingLessons(): List<Lesson> = listOf(
        Lesson(
            id = 13,
            title = "The Three Marks of Existence",
            summary = "Exploring the three fundamental characteristics shared by all things: impermanence, unsatisfactoriness, and non-self.",
            content = """
                The Three Marks of Existence form the foundational lens through which reality is viewed in these teachings. Everything in the physical and mental universe shares three universal characteristics:

                1. Impermanence: All conditioned things are transient, shifting, and in constant flux.

                2. Unsatisfactoriness: Clinging to these temporary things as if they are permanent or controllable creates inevitable friction and distress.

                3. Non-Self: There is no static, permanent, or isolated ego at the core of our experience.

                Recognizing these three marks cuts through illusion, dismantles mental clinging, and clears the path toward deep inner peace.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(13),
                title = "Quiz: The Three Marks of Existence",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What does recognizing the mark of non-self reveal?",
                        options = listOf(
                            "That the physical body has no need for rest",
                            "That there is no static, permanent, or isolated ego at the core of our experience",
                            "That emotions should be eliminated altogether",
                            "That ethics are unnecessary"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Non-self means there is no static, permanent, or isolated ego at the core of our experience."
                    ),
                    Question(
                        id = 2,
                        text = "What are the three universal characteristics shared by all conditioned things?",
                        options = listOf(
                            "Wealth, health, and social status",
                            "Birth, growth, and destruction",
                            "Impermanence, unsatisfactoriness, and non-self",
                            "Joy, sorrow, and indifference"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Everything in the physical and mental universe shares impermanence, unsatisfactoriness, and non-self."
                    )
                )
            )
        ),
        Lesson(
            id = 14,
            title = "Impermanence",
            summary = "Understanding that all conditioned phenomena are transient, shifting, and in constant flux.",
            content = """
                Building on our understanding of experience, we examine impermanence as the underlying characteristic of all conditioned experience.

                Because all conditioned phenomena arise due to causes, they are transient, shifting, and constantly in flux. Grasping onto temporary things as if they are permanent is what deepens distress.

                Understanding impermanence liberates the mind. When we realize that both pleasant and painful experiences are fleeting, we stop clinging, cultivate deep equanimity, and uncover lasting inner peace.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(14),
                title = "Quiz: Impermanence",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What do we cultivate when we realize both pleasant and painful experiences are fleeting and stop clinging?",
                        options = listOf(
                            "Equanimity and lasting inner peace",
                            "Indifference and emotional numbness",
                            "Restless pursuit of new sensations",
                            "Rigid attachment to routine"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Realizing that experiences are fleeting helps us stop clinging, cultivate deep equanimity, and uncover lasting inner peace."
                    ),
                    Question(
                        id = 2,
                        text = "Why does grasping onto temporary things as if they are permanent deepen distress?",
                        options = listOf(
                            "Because conditioned phenomena are inherently rigid and unchanging",
                            "Because they are disconnected from human action",
                            "Because all conditioned phenomena are transient, shifting, and in constant flux",
                            "Because they require constant financial investment"
                        ),
                        correctOptionIndex = 2,
                        explanation = "All conditioned phenomena are transient, shifting, and in constant flux."
                    )
                )
            )
        ),
        Lesson(
            id = 15,
            title = "Unsatisfactoriness",
            summary = "Examining how grasping onto impermanence and trying to control changing conditions leads to friction and distress.",
            content = """
                Building directly upon the reality of impermanence, unsatisfactoriness is the inherent limitation and friction found in all conditioned things.

                Because every physical and mental experience is constantly shifting, nothing within the conditioned world can provide permanent, unshakeable satisfaction. When we expect temporary states to remain stable or treat them as sources of ultimate security, we create inner tension and distress.

                Recognizing unsatisfactoriness is not meant to be pessimistic, but rather profoundly liberating. It helps us release unrealistic expectations, stop striving for permanent control over changing conditions, and find a deeper peace independent of external circumstances.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(15),
                title = "Quiz: Unsatisfactoriness",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "How does inner tension and distress arise according to this lesson?",
                        options = listOf(
                            "From the complete absence of physical motion",
                            "From expecting temporary states to remain stable or treating them as sources of ultimate security",
                            "From practicing the Noble Eightfold Path",
                            "From a lack of formal philosophical training"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Expecting temporary, shifting states to remain stable or treating them as ultimate security creates inner tension and distress."
                    ),
                    Question(
                        id = 2,
                        text = "What does recognizing unsatisfactoriness help us do?",
                        options = listOf(
                            "Withdraw completely from all social relationships",
                            "Manipulate external events permanently",
                            "Release unrealistic expectations, stop striving for permanent control over changing conditions, and find deeper peace",
                            "Achieve physical immortality"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Recognizing unsatisfactoriness helps us release unrealistic expectations, stop striving for permanent control over changing conditions, and find deeper peace."
                    )
                )
            )
        ),
        Lesson(
            id = 16,
            title = "Non-Self",
            summary = "Discovering that at the core of human experience there is no static, permanent, or isolated ego.",
            content = """
                As the culmination of insight into impermanence, emptiness, and unsatisfactoriness, the teaching of non-self reveals that there is no static, permanent, or isolated ego at the center of human existence.

                We commonly view our thoughts, emotions, and physical bodies as an unchanging core identity. However, when we observe closely, we see that these elements are merely a fluid, interdependent process changing from moment to moment.

                Realizing non-self dissolves the heavy burden of guarding a fixed ego. Without a rigid self to defend, the mind opens up to boundless empathy, humility, and effortless freedom.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(16),
                title = "Quiz: Non-Self",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What opens up when realizing non-self dissolves the burden of guarding a fixed ego?",
                        options = listOf(
                            "Boundless empathy, humility, and effortless freedom",
                            "Cold emotional detachment",
                            "Control over external events",
                            "Loss of memory and awareness"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Without a rigid self to defend, the mind opens up to boundless empathy, humility, and effortless freedom."
                    ),
                    Question(
                        id = 2,
                        text = "What does close observation reveal about our thoughts, emotions, and physical bodies?",
                        options = listOf(
                            "That they form an unchanging core identity",
                            "That they are separate from nature",
                            "That they are a fluid, interdependent process changing from moment to moment",
                            "That they never change"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Close observation shows that thoughts, emotions, and physical bodies are a fluid, interdependent process changing from moment to moment."
                    )
                )
            )
        ),
        Lesson(
            id = 17,
            title = "Impermanence and Death",
            summary = "Contemplating the transience of life and physical existence to overcome worldly attachment and prioritize meaningful practice.",
            content = """
                Impermanence and death remind us that every conditioned physical and mental state is temporary. Acknowledging that life is fleeting helps cut through superficial distractions and idle pursuits.

                When we confront the reality of death honestly, we stop taking our time for granted. It inspires a strong sense of urgency to focus our lives on meaningful inner development, kindness, and wisdom.

                Instead of causing despair, meditating on impermanence brings a liberating clarity, helping us let go of petty grievances and invest our energy into what truly matters.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(17),
                title = "Quiz: Impermanence and Death",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What does confronting the reality of death honestly inspire?",
                        options = listOf(
                            "A strong sense of urgency to focus on meaningful inner development, kindness, and wisdom",
                            "Indifference toward the well-being of others",
                            "Strict attachment to fixed routines",
                            "Eliminating all emotional responses"
                        ),
                        correctOptionIndex = 0,
                        explanation = "It inspires a strong sense of urgency to focus our lives on meaningful inner development, kindness, and wisdom."
                    ),
                    Question(
                        id = 2,
                        text = "How does acknowledging that life is fleeting help us in daily practice?",
                        options = listOf(
                            "By creating persistent anxiety about the future",
                            "By encouraging total isolation from community life",
                            "By helping cut through superficial distractions and idle pursuits",
                            "By promoting endless accumulation of material wealth"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Acknowledging that life is fleeting helps cut through superficial distractions and idle pursuits."
                    )
                )
            )
        )
    )

    private fun interdependenceAndEmptinessLessons(): List<Lesson> = listOf(
        Lesson(
            id = 7,
            title = "Interdependence",
            summary = "Exploring how all things are profoundly connected and arise in relation to one another.",
            content = """
                Interdependence reveals that nothing exists in total isolation. Everything in the universe—from human beings to nature—arises in connection with a vast web of conditions.

                When we look deeply, we see that our well-being is tied to the well-being of others and our environment. Every action, word, and thought ripples outward and impacts the whole.

                Realizing interdependence dissolves feelings of absolute separation. It inspires a natural sense of care, shared responsibility, and harmony with the world around us.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(7),
                title = "Quiz: Interdependence",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What does realizing interdependence naturally inspire?",
                        options = listOf(
                            "A sense of care, shared responsibility, and harmony with the world",
                            "Indifference toward social and environmental issues",
                            "A desire to isolate completely from society",
                            "Competition and rivalry with others"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Realizing interdependence dissolves feelings of absolute separation and inspires care, shared responsibility, and harmony."
                    ),
                    Question(
                        id = 2,
                        text = "What does the principle of interdependence reveal about existence?",
                        options = listOf(
                            "That everything exists in complete isolation",
                            "That only human actions matter in the universe",
                            "That everything arises in connection with a vast web of conditions",
                            "That physical objects are entirely permanent"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Interdependence reveals that nothing exists in total isolation and everything arises in connection with a vast web of conditions."
                    )
                )
            )
        ),
        Lesson(
            id = 8,
            title = "Thich Nhat Hanh and Interbeing",
            summary = "Exploring the teachings of Zen master Thich Nhat Hanh on 'Interbeing,' discovering how every element of the universe is profoundly interconnected.",
            content = """
                The Teacher of Interbeing: Thich Nhat Hanh was a globally renowned Vietnamese Thien (Zen) master, poet, scholar, and peace activist who popularized the practice of mindfulness in the West. He coined the term "Interbeing" to describe the profound truth that nothing exists separately, but is instead composed entirely of everything else in the universe.

                The Practice of Looking Deeply: Interbeing is not merely an abstract philosophical concept, but a direct invitation to look deeply into the nature of daily phenomena. When we examine a simple object like a sheet of paper, Thich Nhat Hanh taught that we can see clouds, rain, sunshine, the forest, and the logger—all cooperating intimately to bring that paper into existence.

                Living with Mindfulness and Compassion: Recognizing our interbeing transforms how we relate to the world and to one another. Because our actions, thoughts, and words ripple through the web of life, living mindfully becomes an act of deep reverence, urging us to cultivate compassion, reduce suffering, and foster global peace.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(8),
                title = "Quiz: Thich Nhat Hanh and Interbeing",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "According to this lesson, what does recognizing interbeing urge us to cultivate?",
                        options = listOf(
                            "Compassion, reducing suffering, and fostering global peace",
                            "Withdrawal from society into complete isolation",
                            "Ignoring the consequences of daily actions",
                            "Competing aggressively with others for resources"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Living mindfully with interbeing urges us to cultivate compassion, reduce suffering, and foster global peace."
                    ),
                    Question(
                        id = 2,
                        text = "What does the term \"Interbeing\" describe in Thich Nhat Hanh's teachings?",
                        options = listOf(
                            "That objects exist entirely in complete isolation from one another",
                            "That physical matter is permanent and unchanging",
                            "That nothing exists separately and everything is composed of everything else in the universe",
                            "That individual human choices have no effect on others"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Thich Nhat Hanh coined \"Interbeing\" to describe that nothing exists separately, but is composed of everything else in the universe."
                    )
                )
            )
        ),
        Lesson(
            id = 9,
            title = "Dependent Origination",
            summary = "Understanding how events and experiences arise conditioned by preceding causes.",
            content = """
                Dependent origination is the core teaching that nothing happens by random chance or as an isolated event. Every physical and mental state arises conditioned by preceding causes.

                In human experience, this shows how habits, reactions, and emotional patterns form a continuous chain. For example, sensory contact leads to feeling, which can trigger craving if we are not mindful.

                Seeing how this chain operates gives us the power to interrupt unwholesome cycles. By introducing awareness at the moment of contact, we break old reactionary habits and create new, peaceful possibilities.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(9),
                title = "Quiz: Dependent Origination",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What does dependent origination teach about every physical and mental state?",
                        options = listOf(
                            "They happen entirely by random chance",
                            "They arise conditioned by preceding causes",
                            "They are completely unchanging and permanent",
                            "They exist independently of the mind"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Every physical and mental state arises conditioned by preceding causes."
                    ),
                    Question(
                        id = 2,
                        text = "How can we interrupt unwholesome cycles according to this lesson?",
                        options = listOf(
                            "By ignoring sensory experiences entirely",
                            "By forcing the mind to dwell on the past",
                            "By introducing awareness at the moment of contact to break old reactionary habits",
                            "By avoiding all social interactions"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Introducing awareness at the moment of contact helps break old reactionary habits."
                    )
                )
            )
        ),
        Lesson(
            id = 10,
            title = "Emptiness",
            summary = "Realizing that all phenomena are empty of a separate, permanent, or independent self-nature.",
            content = """
                Building directly on dependent origination, emptiness is the profound insight that nothing exists independently, permanently, or by its own separate power.

                In this philosophy, emptiness does not mean nothingness or that the world is an illusion. Instead, it means that things are completely empty of an isolated self-nature because they are made entirely of non-self elements and shifting conditions.

                Understanding emptiness frees the mind from rigid labels and absolute boundaries. When we see that people, events, and objects are fluid and interconnected, we stop grasping, dissolve rigid mental barriers, and experience ultimate liberation.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(10),
                title = "Quiz: Emptiness",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What does emptiness mean in this lesson?",
                        options = listOf(
                            "That the world is a total illusion and nothing exists at all",
                            "That nothing exists independently, permanently, or by its own separate power",
                            "That physical objects are entirely rigid and unchanging",
                            "That human actions have no consequences"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Emptiness means nothing exists independently, permanently, or by its own separate power because things are made of non-self elements and shifting conditions."
                    ),
                    Question(
                        id = 2,
                        text = "What happens when we understand emptiness and see things as fluid and interconnected?",
                        options = listOf(
                            "We gain rigid control over everyone around us",
                            "We become coldly detached from reality",
                            "We stop grasping, dissolve rigid mental barriers, and experience ultimate liberation",
                            "We withdraw into permanent isolation"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Understanding emptiness frees the mind from rigid labels and boundaries, helping us stop grasping and experience ultimate liberation."
                    )
                )
            )
        ),
        Lesson(
            id = 11,
            title = "Nagarjuna and The Fundamental Wisdom of the Middle Way",
            summary = "Exploring the foundational text of Madhyamaka philosophy by Nagarjuna, which demonstrates the emptiness of all phenomena through logical analysis.",
            content = """
                The Master of the Middle Way: Nagarjuna, living in 2nd-century India, is regarded as one of the most influential philosophers in Buddhist history. He resuscitated and systematically articulated the profound teachings on emptiness (sunyata) found in the Perfection of Wisdom sutras. Through razor-sharp logical analysis, Nagarjuna demonstrated that nothing possesses an intrinsic, independent essence (svabhava).

                The Fundamental Wisdom (Mulamadhyamakakarika): Nagarjuna's masterwork, the Mulamadhyamakakarika, examines every major category of experience—including causation, motion, time, the elements, and the self—to show how grasping at absolute existence collapses under scrutiny. By examining how things arise dependently, he showed that dependent origination and emptiness are completely synonymous.

                Refuting Extremes of Existence and Nihilism: Nagarjuna famously argued that emptiness does not mean nothingness (nihilism), nor does it mean things exist inherently (eternalism). Instead, because things exist dependently, they are empty of inherent existence while remaining functionally operational in conventional reality. This delicate realization avoids all conceptual extremes.

                The Purpose of Deconstruction: The logical deconstructions presented by Nagarjuna are not meant to foster skepticism or academic debate. Rather, they serve a therapeutic purpose: dismantling our deeply ingrained habit of reifying concepts, clinging to views, and building mental barriers, thereby freeing the mind to experience ultimate peace.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(11),
                title = "Quiz: Nagarjuna and The Fundamental Wisdom of the Middle Way",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What is the therapeutic purpose of Nagarjuna's logical deconstructions?",
                        options = listOf(
                            "To foster skepticism and academic debate",
                            "To dismantle our habit of reifying concepts, clinging to views, and building mental barriers",
                            "To establish strict rules for ritual sacrifices",
                            "To assert a permanent soul behind every mental state"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Their purpose is to dismantle our habit of reifying concepts, clinging to views, and building mental barriers, freeing the mind to experience ultimate peace."
                    ),
                    Question(
                        id = 2,
                        text = "What did Nagarjuna demonstrate through logical analysis in the Mulamadhyamakakarika?",
                        options = listOf(
                            "That all phenomena possess an unchanging, eternal essence",
                            "That the physical world is a permanent creation of distinct atoms",
                            "That nothing possesses an intrinsic, independent essence (svabhava) and dependent origination and emptiness are synonymous",
                            "That logic and reasoning have no place in spiritual practice"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Nagarjuna demonstrated that nothing possesses an intrinsic, independent essence (svabhava) and that dependent origination and emptiness are synonymous."
                    )
                )
            )
        ),
        Lesson(
            id = 12,
            title = "Aryadeva and The Four Hundred",
            summary = "Exploring the masterpiece of the 3rd-century Madhyamaka master Aryadeva, The Four Hundred Verse Treatise on the Conduct of Bodhisattva Yogis.",
            content = """
                The Lineage of Wisdom: Aryadeva was the chief spiritual disciple and heart-son of Nagarjuna, the founding father of the Madhyamaka (Middle Way) school. Hailing originally from Sri Lanka, Aryadeva traveled to the great monastic university of Nalanda in India, where he became a legendary scholar, debater, and teacher.

                Structure of the Text: The Four Hundred (Catuhsataka) is divided into 16 chapters of 25 verses each, totaling 400 verses. Structurally, the text is split into two halves:

                1. The First Eight Chapters (Conventional Reality & Merit): Focus on abandoning the four fundamental distortions of reality—misconceiving what is impermanent as permanent, suffering as pleasurable, impure as clean, and non-self as having a self. These chapters use the human body and everyday life as practical meditation objects to cut through ordinary attachments.

                2. The Final Eight Chapters (Ultimate Reality & Emptiness): Systematically dismantle philosophical errors, reified concepts of time, space, atoms, and self, guiding the practitioner directly to the realization of emptiness.

                Overcoming the Four Perverted Views:

                Permanence: Aryadeva confronts our deep denial of death, reminding us that life is fleeting like cattle being led to slaughter, leaving no room for complacency.

                Pleasurable & Clean: He challenges our physical attachments by revealing the inherent limitations, vulnerabilities, and impurities of the body.

                Ego/Self: He dismantles pride and self-cherishing, urging practitioners to look past superficial status and recognize the emptiness of ego.

                The Gradual Progression of Practice: Aryadeva encapsulates the entire path of a Bodhisattva in a famous instruction:
                First prevent the demeritorious.
                Next prevent self.
                Later prevent views of all kinds.

                By sequentially clearing away negative karma, dissolving the illusion of an inherent self, and finally letting go of all conceptual fixations on philosophical views, the mind rests completely free in ultimate truth.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(12),
                title = "Quiz: Aryadeva and The Four Hundred",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What is the structural focus of the first eight chapters of The Four Hundred?",
                        options = listOf(
                            "Detailed calculations of historical rebirth timelines",
                            "Architectural guidelines for building monastic debate halls",
                            "Abandoning the four fundamental distortions of permanence, pleasure, cleanliness, and self",
                            "Strict rules regarding dietary restrictions for monks"
                        ),
                        correctOptionIndex = 2,
                        explanation = "The first eight chapters focus on abandoning the four fundamental distortions of reality using the body and everyday life as meditation objects."
                    ),
                    Question(
                        id = 2,
                        text = "Who was Aryadeva's primary teacher and founder of the Madhyamaka school?",
                        options = listOf(
                            "Asanga",
                            "Nagarjuna",
                            "Santideva",
                            "Vasubandhu"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Aryadeva was the chief spiritual disciple and heart-son of Nagarjuna, founder of the Madhyamaka school."
                    ),
                    Question(
                        id = 3,
                        text = "According to Aryadeva's instruction on the gradual progression of practice, what is the full order?",
                        options = listOf(
                            "First prevent the demeritorious, next prevent self, later prevent views of all kinds",
                            "First prevent views of all kinds, next prevent self, later prevent the demeritorious",
                            "First prevent self, next prevent the demeritorious, later prevent views of all kinds",
                            "Prevent views of all kinds while keeping demeritorious actions"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Aryadeva instructs: first prevent the demeritorious, next prevent self, later prevent views of all kinds."
                    )
                )
            )
        )
    )

    private fun compassionAndBodhicittaLessons(): List<Lesson> = listOf(
        Lesson(
            id = 18,
            title = "Karma (Cause and Effect)",
            summary = "Understanding how intentional actions yield corresponding ethical and psychological results in human experience.",
            content = """
                Karma refers to the universal principle of moral cause and effect. Every intentional action of body, speech, and mind leaves a deep impression and bears fruit in corresponding experiences.

                Wholesome intentions rooted in generosity, kindness, and clarity lead naturally to inner peace and positive outcomes. Conversely, unwholesome actions driven by greed, hostility, or delusion generate friction and distress.

                Understanding karma places personal responsibility squarely in our own hands. By observing our intentions and choosing constructive actions, we actively shape our future state of mind and well-being.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(18),
                title = "Quiz: Karma (Cause and Effect)",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "Where does understanding karma place responsibility?",
                        options = listOf(
                            "In our own hands by observing our intentions and choosing constructive actions",
                            "On external circumstances and luck",
                            "On others' opinions of our actions",
                            "Outside our ability to influence"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Understanding karma places personal responsibility in our own hands through our intentions and constructive actions."
                    ),
                    Question(
                        id = 2,
                        text = "What is the core principle of karma described in this lesson?",
                        options = listOf(
                            "Random luck and unpredictable fortune",
                            "Strict predetermination by external authorities",
                            "Every intentional action of body, speech, and mind leaves an impression and bears fruit in corresponding experiences",
                            "Complete independence from personal choices"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Karma is the principle that every intentional action of body, speech, and mind leaves an impression and bears fruit in corresponding experiences."
                    )
                )
            )
        ),
        Lesson(
            id = 19,
            title = "Developing Bodhicitta",
            summary = "Cultivating universal love, compassion, and the altruistic aspiration to attain full awakening for the benefit of all.",
            content = """
                Bodhicitta is the heartfelt aspiration to achieve complete awakening specifically for the purpose of helping all living beings overcome distress.

                This mindset shifts focus away from isolated self-interest. It expands the heart through boundless loving-kindness and deep empathy, recognizing that our well-being is entirely bound up with the welfare of others.

                Cultivating bodhicitta transforms ordinary daily actions into a powerful path of universal service, infusing every task with warmth, purpose, and profound altruism.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(19),
                title = "Quiz: Developing Bodhicitta",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "How does cultivating bodhicitta shift our focus?",
                        options = listOf(
                            "Away from isolated self-interest toward universal service and empathy",
                            "Toward cold emotional detachment",
                            "Toward making daily life more rigid",
                            "Away from ethical conduct"
                        ),
                        correctOptionIndex = 0,
                        explanation = "It shifts focus away from isolated self-interest and expands the heart through boundless loving-kindness and deep empathy."
                    ),
                    Question(
                        id = 2,
                        text = "What is bodhicitta according to this lesson?",
                        options = listOf(
                            "The desire for personal fame and spiritual status",
                            "The strict avoidance of all community engagement",
                            "The heartfelt aspiration to achieve complete awakening specifically to help all living beings overcome distress",
                            "The practice of physical endurance"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Bodhicitta is the heartfelt aspiration to achieve complete awakening specifically to help all living beings overcome distress."
                    )
                )
            )
        ),
        Lesson(
            id = 20,
            title = "Engaging in the Bodhisattva Practices",
            summary = "Practicing the six perfections of generosity, ethics, patience, joyous effort, concentration, and wisdom.",
            content = """
                With an altruistic heart established, practice is expressed through the six perfections, which guide active engagement in the world:

                1. Generosity: Cultivating a spirit of giving and open-handed helpfulness.

                2. Ethical Conduct: Maintaining integrity and harmlessness in all interactions.

                3. Patience: Developing resilience and emotional balance in the face of provocation or hardship.

                4. Joyous Effort: Sustaining enthusiasm and steady energy for wholesome endeavors.

                5. Meditative Concentration: Cultivating inner stillness and focused clarity.

                6. Wisdom: Discerning ultimate reality and cutting through mental confusion.

                Mastering these perfections integrates deep insight with compassionate action in daily life.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(20),
                title = "Quiz: Engaging in the Bodhisattva Practices",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What does mastering the six perfections integrate?",
                        options = listOf(
                            "Deep insight with compassionate action in daily life",
                            "Personal praise and status",
                            "Avoiding all physical labor",
                            "Winning arguments against opponents"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Mastering these perfections integrates deep insight with compassionate action in daily life."
                    ),
                    Question(
                        id = 2,
                        text = "Which of the following are included among the six perfections?",
                        options = listOf(
                            "Competition, speed, and luxury",
                            "Isolation, silence, and fasting",
                            "Generosity, patience, and joyous effort",
                            "Doubt, skepticism, and debate"
                        ),
                        correctOptionIndex = 2,
                        explanation = "The six perfections are generosity, ethical conduct, patience, joyous effort, meditative concentration, and wisdom."
                    )
                )
            )
        ),
        Lesson(
            id = 21,
            title = "Tsongkhapa and The Six Perfections",
            summary = "Explore the life and teachings of Je Tsongkhapa, founder of the Gelug school, and how he synthesized rigorous study with the practice of the six perfections.",
            content = """
                The Life of Je Tsongkhapa:

                Born in 1357 in Amdo, Tibet, Je Tsongkhapa Losang Drakpa was a towering figure of Buddhist scholarship and reform.

                From a young age, he showed extraordinary aptitude for learning and meditation.

                He traveled extensively across Tibet, studying under masters of all major traditions.

                Tsongkhapa observed that while monastic discipline was sometimes lax and philosophical understanding fragmented, a deep synthesis was needed.

                He founded the Gelug ("Virtuous") tradition, emphasizing a balanced approach that united rigorous scholarly study (especially of the Madhyamaka philosophy of emptiness) with deep meditative realization and pure ethical conduct.

                The Lamrim Tradition:

                Tsongkhapa's greatest literary legacy is the "Lamrim Chenmo" (Great Treatise on the Stages of the Path to Enlightenment).

                This monumental work structures the entire Buddhist path into stages suitable for practitioners of varying capacities, showing how foundational ethics, the development of Bodhicitta, and profound insight fit seamlessly together.

                Synthesizing Wisdom and Method:

                Central to Tsongkhapa's teaching is the indivisibility of method (compassion, bodhicitta, and the six perfections) and wisdom (the realization of emptiness).

                He argued that meditation without philosophical analysis lacks the sharp tool to cut ego, while philosophy without compassionate method remains cold and disconnected from the welfare of others.

                The Six Perfections in Daily Practice:

                Tsongkhapa detailed how the six perfections—generosity, ethics, patience, joyous effort, concentration, and wisdom—must be practiced not just as isolated virtues, but as a unified lifestyle of awakening, where each perfection supports and purifies the others.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(21),
                title = "Quiz: Tsongkhapa and The Six Perfections",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What major Tibetan Buddhist tradition did Je Tsongkhapa found?",
                        options = listOf(
                            "The Gelug (\"Virtuous\") tradition",
                            "The Zen school of sudden awakening",
                            "The Shingon esoteric lineage",
                            "The Theravada forest tradition"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Je Tsongkhapa founded the Gelug (\"Virtuous\") tradition."
                    ),
                    Question(
                        id = 2,
                        text = "What is central to Tsongkhapa's teaching on practice?",
                        options = listOf(
                            "Wealth and political power",
                            "The indivisibility of method (compassion, bodhicitta, and the six perfections) and wisdom (emptiness)",
                            "Physical endurance and fasting",
                            "Ancient ritual and modern science"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Central to Tsongkhapa's teaching is the indivisibility of method and wisdom."
                    ),
                    Question(
                        id = quizIdFor(21),
                        text = "What does Tsongkhapa's \"Lamrim Chenmo\" do?",
                        options = listOf(
                            "A military history of medieval Tibet",
                            "A collection of short folk tales and poetry",
                            "Structures the entire Buddhist path into stages suitable for practitioners of varying capacities",
                            "Strict architectural designs for monastic temples"
                        ),
                        correctOptionIndex = 2,
                        explanation = "The Lamrim Chenmo structures the entire Buddhist path into stages suitable for practitioners of varying capacities."
                    )
                )
            )
        )
    )

    private fun libraryLessons(): List<Lesson> = listOf(
        Lesson(
            id = 111,
            title = "The Three Principal Aspects of the Path",
            summary = "Lam gyi gtso bo rnam gsum by Tsongkhapa: determination to be free, supreme bodhicitta, and the wisdom realizing dependent arising and emptiness.",
            content = """
                THE THREE PRINCIPAL ASPECTS OF THE PATH
                Tsongkhapa (1357–1419)

                I. I bow to the venerable gurus. I will explain, as clearly as I can, the essential meaning of all the Conqueror’s teachings, the path praised by the noble children of the Conqueror, and the gateway for fortunate people who seek liberation.

                II. Those who are not attached to the pleasures of cyclic existence, who strive to make their freedom and good fortune meaningful, and who place their trust in the path that delights the Conqueror—fortunate ones, listen with an open and clear mind.

                III. Without genuine determination to be free, there is no way to end the pursuit of pleasure in the ocean of existence. Thirsting for cyclic existence, embodied beings remain bound; therefore, begin by developing the determination to be free.

                IV. Freedom and good fortune are difficult to find, and life does not last. Familiarize your mind with this, and turn away from fixation on this life. Reflect again and again on the unfailing effects of karma and on the suffering of cyclic existence; turn away from fixation on future lives.

                V. When you have become familiar with this way of thinking, you will no longer feel even a moment’s longing for the splendors of cyclic existence. When the wish for liberation is present day and night, you have developed genuine determination to be free.

                VI. That determination to be free, too, will not become the cause of the unsurpassed bliss of awakening unless it is joined with the pure awakening mind. Therefore, those with discernment should generate supreme bodhicitta.

                VII–VIII. Swept along by the current of four powerful rivers, tightly bound by karma’s bonds, which are so hard to break, caught in the iron net of grasping at a self, and completely enveloped in the thick darkness of ignorance, they are born again and again in endless cyclic existence, and throughout those lives they are tormented without interruption by the three kinds of suffering. Think of our mothers, living in such a condition, and generate the supreme awakening mind for their sake.

                IX. Even if you have cultivated determination to be free and the awakening mind, if you lack the wisdom that realizes how things truly exist, you cannot cut the root of cyclic existence. Therefore, make an effort to develop the means of realizing dependent arising.

                X. Whoever sees that the causes and effects of all phenomena of cyclic existence and nirvana are never deceptive, and brings to an end the way objects are apprehended as truly existent, has entered the path that delights the Buddha.

                XI. As long as the two understandings remain separate—that appearances arise dependently and are therefore unfailing, and that emptiness is free from asserting inherent existence—you have not yet realized the Buddha’s intent.

                XII. When these two understandings no longer alternate but arise together, simultaneously, then, simply by seeing dependent arising as unfailing, certain knowledge destroys the way things are grasped as inherently existent. At that point, the analysis of the view is complete.

                XIII. Moreover, understanding appearances as dependently arisen rules out the extreme of inherent existence; understanding emptiness rules out the extreme of nonexistence. When you understand how emptiness appears as cause and effect, you will no longer be carried away by extreme views.

                XIV. When you have realized, just as they are, the essential points of the three principal aspects of the path, rely on solitude and build the strength of joyful effort. Then, my child, quickly accomplish your ultimate aim.

                SOURCE AND TRANSLATION NOTE

                Original work: Tsongkhapa, Lam gyi gtso bo rnam gsum (The Three Principal Aspects of the Path), 14 stanzas.

                Source text: The original Tibetan composition, in Tibetan script, as presented on the UMA Institute for Tibetan Studies page, “The Three Principal Aspects of the Path by Tsong-kha-pa – Tibetan Language Learning”:
                https://uma-tibet.org/three-principal-aspects-of-the-path-by-tsong-kha-pa-tibetan-language-learning/

                End of translation.
            """.trimIndent(),
            quiz = null
        )
    )

    private fun bodhisattvaWayOfLifeLessons(): List<Lesson> = listOf(
        Lesson(
            id = 101,
            title = "Chapter 1 — The Benefits of the Awakening Mind",
            summary = "Bodhicittānuśaṃsaḥ: exploring the transformative power of aspiring to and engaging in bodhicitta.",
            content = """
                The Bodhicaryāvatāra
                Chapter 1 — The Benefits of the Awakening Mind
                Bodhicittānuśaṃsaḥ

                1.1
                With reverence, I bow to the Buddhas, their heirs, and those worthy of honor throughout the world. I will briefly explain, in accordance with the teachings, how to enter the discipline of the children of the Buddhas.

                1.2
                There is nothing here that has never been said before, and I have no special skill in composing works. I have written this not for the benefit of others, but to cultivate my own mind.

                1.3
                Through this work, my wholesome qualities may grow as my mind becomes more joyful in cultivating goodness. And if someone with a similar disposition happens to read it, it may be meaningful for them as well.

                1.4
                This precious human life, so difficult to obtain, has now been gained. It gives us the opportunity to fulfill our deepest purpose. If we do not reflect on what is beneficial now, when will we ever have such an opportunity again?

                1.5
                Just as lightning briefly illuminates the darkness of a stormy night, the power of the Buddhas may sometimes awaken a moment of wholesome understanding in the minds of people.

                1.6
                Therefore, goodness is always weak, while the force of wrongdoing is immense and terrifying. What other virtue could overcome it, if not the awakening mind?

                1.7
                After reflecting for countless ages, the great sages recognized this very truth: through it, happiness grows with ease and can carry away immeasurable floods of suffering.

                1.8
                Those who wish to cross beyond the hundreds of sufferings of existence, who wish to remove the hardships of living beings, and who long to experience countless forms of happiness must never abandon the awakening mind.

                1.9
                The moment this awakening mind arises, even someone trapped in the prison of existence is called a child of the Buddhas and becomes worthy of honor from the world, including gods and human beings.

                1.10
                Take hold of this awakening mind as you would a powerful alchemical substance: it transforms this impure human body into the priceless image of a Buddha.

                1.11
                This jewel of the awakening mind has been carefully examined by those of immeasurable wisdom and treasured by the great guides of the world. Hold firmly to this precious jewel, which leads beings who wander through the paths of existence toward liberation.

                1.12
                All other forms of goodness eventually wither away, like a plantain tree after it has borne fruit. But the tree of the awakening mind continually bears fruit and never comes to an end.

                1.13
                Even those who have committed terrible wrongs can quickly rise beyond them by relying on this mind. Just as someone facing great danger finds safety under the protection of a brave person, why would those who understand so little fail to rely on it?

                1.14
                Like the fire at the end of an age, it burns away immense wrongdoing in an instant. The wise Maitreya explained its immeasurable benefits to Sudhana.

                1.15
                In brief, the awakening mind should be understood as having two forms: the mind that aspires to awakening and the mind that sets out to put that aspiration into practice.

                1.16
                The difference between these two is like the difference between someone who wants to travel and someone who has actually set out on the journey. Wise people should understand the distinction in this way.

                1.17
                The mind that merely aspires to awakening brings great benefits even within cyclic existence. Yet its stream of merit is not continuous in the same way as that of the mind that actively sets out on the path.

                1.18
                From the moment someone firmly and unwaveringly embraces the intention to free the entire limitless realm of living beings,

                1.19
                from that moment onward, even while sleeping or distracted, streams of merit as vast as the sky continue to arise without interruption.

                1.20
                The Buddha himself explained this with sound reasoning in the Subāhu-paripṛcchā Sūtra, for the benefit of beings whose spiritual aspirations are more limited.

                1.21
                Simply thinking, “May I relieve living beings of their headaches,” gives rise to immeasurable merit when that thought is guided by a sincere wish to help.

                1.22
                How much greater, then, must the merit be of someone who wishes to remove the incomparable suffering of every single being and to bring each one immeasurable qualities?

                1.23
                What mother or father has ever held such a benevolent wish? What god, sage, or creator deity could have such an intention?

                1.24
                Even for their own benefit, these living beings have never before given rise to such a wish—not even in a dream. How much less likely, then, is it that they would wish for the benefit of others?

                1.25
                How does this extraordinary jewel of a mind arise in beings? The wish to benefit others is something that does not even arise naturally when they seek their own welfare.

                1.26
                How could the merit of this precious mind ever be measured? It is the seed of joy for the world and the medicine that heals the world's suffering.

                1.27
                Even the mere wish to benefit others surpasses the worship offered to the Buddhas. How much more extraordinary, then, is the effort to bring happiness to every living being?

                1.28
                Although they long to escape suffering, living beings rush straight toward it. Although they desire happiness, through confusion they destroy their own happiness as if they were their own enemies.

                1.29
                For those who are starved for happiness and tormented in countless ways, the awakening mind brings satisfaction through every kind of happiness and cuts away all their suffering.

                1.30
                It also dispels confusion. Where could there be anyone as good as this? Where could there be a friend like this? Where could there be merit equal to it?

                1.31
                Even someone who repays a kindness deserves praise. What, then, should be said of a bodhisattva, who does good for others without having been asked?

                1.32
                People praise as virtuous someone who gives a few people a meal, even if it is only enough to feed them for half a day, and even if the gift is given with disrespect.

                1.33
                What, then, of someone who gives endlessly to limitless numbers of living beings for an immeasurable length of time, fulfilling every wish for happiness as boundlessly as the sky?

                1.34
                The Buddha declared that anyone who allows hostility toward a child of the Buddhas to arise in their heart will remain in the hell realms for as many ages as there were moments of that hostile state of mind.

                1.35
                But if someone's mind becomes sincerely joyful and trusting toward them, the result is even greater. For wrongdoing directed toward the children of the Buddhas has tremendous power, while goodness directed toward them arises effortlessly and bears great fruit.

                1.36
                I bow to the bodies of those in whom this precious and noble mind has arisen. I take refuge in those who bring happiness, for even when they are harmed, their actions ultimately lead toward happiness.

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 1, “Bodhicittānuśaṃsaḥ” (“The Benefits of the Awakening Mind”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 1.
            """.trimIndent(),
            quiz = null
        ),
        Lesson(
            id = 102,
            title = "Chapter 2 — Confession of Wrongdoing",
            summary = "Pāpadeśanā: making offerings, taking refuge, and openly confessing past wrongdoing before the Buddhas and bodhisattvas.",
            content = """
                The Bodhicaryāvatāra
                Chapter 2 — Confession of Wrongdoing
                Pāpadeśanā

                2.1
                To embrace that precious jewel of the awakening mind, I now make offerings to the Tathāgatas, to the stainless jewel of the true Dharma, and to the oceans of virtues who are the Buddhas’ children.

                2.2
                All the flowers and fruits that exist, every kind of medicine, all the jewels found in the world, and all the clear, refreshing waters—

                2.3
                the jeweled mountains and other beautiful, secluded places in the forests; the vines shining with their flowers and ornaments; and the trees whose branches bend beneath the weight of fine fruit—

                2.4
                the fragrant incense in the worlds of the gods and others, wish-fulfilling trees and jeweled trees, and lakes adorned with lotuses and made enchanting by the calls of swans—

                2.5
                the crops that grow without cultivation, and every other beautiful thing worthy of offering, extending throughout the whole expanse of space and belonging to no one—

                2.6
                I gather all these in my mind and offer them to the supreme sages, together with their spiritual heirs. Greatly compassionate ones, you who are worthy of the finest gifts, please accept them out of compassion for me.

                2.7
                I am poor in merit and have nothing else to offer. Therefore, protectors, please accept these offerings through your own power, for the sake of my welfare, you whose minds are devoted to the welfare of others.

                2.8
                I offer myself completely to the Buddhas and, in every way, to their spiritual children. Supreme beings, please take me into your care. With devotion, I place myself in your service.

                2.9
                With you as my protectors, I will act without fear for the welfare of living beings. I will overcome the wrongs I committed in the past and will not create further wrongdoing.

                2.10
                In those beautiful bathing halls, with jeweled pillars, lovely pearl canopies, and floors of clear, shining crystal, all filled with fragrance,

                2.11
                I bathe the Tathāgatas and their spiritual children in many jeweled vessels filled with fragrant water and flowers, accompanied by music and song.

                2.12
                I dry their bodies with incomparable, spotless, perfumed cloths. Then I offer them beautiful robes, richly colored and delicately scented.

                2.13
                I adorn Samantabhadra, Mañjughoṣa, Lokeśvara, and the others with divine garments, soft, fine, splendid, and varied, together with the finest ornaments.

                2.14
                I anoint the bodies of all the supreme sages, radiant like brilliantly polished, refined gold, with the finest perfumes whose fragrance spreads throughout the thousand-millionfold universe.

                2.15
                With lovely, fragrant flowers—māndārava, blue lotuses, jasmine, and others—I worship the supreme sages, who are worthy of the highest worship, and adorn them with beautiful garlands.

                2.16
                I envelop them in clouds of incense, rich, fragrant, and delightful. I present them with every kind of delicious food, solid and liquid, and with all manner of refreshments.

                2.17
                I offer rows of jeweled lamps set upon golden lotuses, and scatter lovely heaps of flowers across floors anointed with perfume.

                2.18
                I offer to those whose nature is loving radiant celestial palaces, adorned in every direction, shining with hanging strings of pearls and jewels, and made beautiful by songs of praise.

                2.19
                I hold above the great sages magnificent jeweled parasols, raised high and mounted on beautiful golden staffs, decorated with strings of pearls.

                2.20
                From now on, may lovely clouds of offerings arise, along with clouds of music and song that bring delight to all living beings.

                2.21
                May showers of flowers, jewels, and other precious things fall without interruption upon all the jewels of the true Dharma, upon sacred monuments, and upon images of the Buddhas.

                2.22
                Just as Mañjughoṣa and the others worship the Jinas, so do I worship the Tathāgatas, the protectors, together with their spiritual children.

                2.23
                I praise these oceans of virtue with songs whose melodies are as vast as the ocean. May clouds of songs and praise arise for them without end.

                2.24
                With as many prostrations as there are atoms in all the realms, I bow to the Buddhas of the three times and to the supreme community of the Dharma.

                2.25
                I pay homage to all sacred monuments and to the places where bodhisattvas dwell. I bow to my teachers and to all worthy monastics.

                2.26
                Until I reach the heart of awakening, I take refuge in the Buddha. I take refuge in the Dharma, and likewise in the community of bodhisattvas.

                2.27
                With folded hands, I appeal to the fully awakened Buddhas who dwell in every direction, and to the bodhisattvas, the great beings of compassion.

                2.28
                Throughout beginningless existence, and in this life as well, whatever wrongdoing I have committed or caused to be committed while blinded by ignorance—

                2.29
                and whatever wrongdoing I have foolishly approved, bringing harm to myself—I acknowledge it all, overwhelmed with regret.

                2.30
                Whatever harm I have done to the Three Jewels, to my mother and father, to my teachers, or to others deserving respect, through disrespect in body, speech, or mind—

                2.31
                O guides, whatever terrible wrongdoing I have committed through the many faults that stain me, all of it I openly acknowledge.

                2.32
                How can I escape this, O guides? I live in constant fear. May death not come upon me before my store of wrongdoing has been exhausted.

                2.33
                How can I escape this? Please protect me quickly. Death may come upon me soon, while my wrongdoing remains unexhausted.

                2.34
                Death does not stop to consider whether our tasks are finished or unfinished. It destroys our confidence and cannot be trusted by the healthy or the sick. It strikes suddenly, like a terrible thunderbolt.

                2.35
                Again and again, through attachment to what I like and aversion to what I dislike, I have committed wrongdoing. I failed to understand that I would have to leave everything behind and go.

                2.36
                Those I dislike will not remain, those I love will not remain, I myself will not remain, and nothing else will remain.

                2.37
                Whatever we experience gradually fades from memory. Everything passes away like something experienced in a dream, never to be seen again.

                2.38
                Even while I remain here, many loved ones and disliked people have already gone. Yet the terrible wrongdoing committed because of them still stands before me.

                2.39
                I never considered that I was only a temporary visitor here. Through ignorance, attachment, and hatred, I committed wrongdoing again and again.

                2.40
                Day and night, without pause, the time left in my life keeps running out. It never increases. So why should I not die?

                2.41
                Whether I lie in bed or stand among my family and friends, the pain of being cut apart at the most vital places will have to be endured by me alone.

                2.42
                When the messengers of Yama seize me, where will my family be? Where will my friends be? At that time, only merit can protect me—and I have not cultivated it.

                2.43
                O protectors, because I was careless and did not understand the danger of clinging to this impermanent life, I have accumulated much wrongdoing.

                2.44
                Even someone being led away today to have a limb cut off grows faint with thirst and sees the world differently, looking around with helpless eyes.

                2.45
                How much worse will it be when I am surrounded by the terrifying messengers of Yama, overcome by the fever of great terror and soiled with my own waste?

                2.46
                With frightened, helpless glances, I will search in every direction for protection. Who will be the good person who saves me from this great danger?

                2.47
                When I look in all directions and find no protection, I will fall into utter confusion. What will I do then, in that place of overwhelming danger?

                2.48
                So today I take refuge in the mighty protectors of the world, the Jinas who strive to protect all living beings and remove every kind of fear.

                2.49
                I also take sincere refuge in the Dharma they have realized, which destroys the terror of cyclic existence, and in the community of bodhisattvas.

                2.50
                Overwhelmed with fear, I offer myself to Samantabhadra. And again, of my own accord, I offer myself to Mañjughoṣa.

                2.51
                I cry out in distress to the protector Avalokiteśvara, whose actions are filled with compassion. May that noble one protect me, a wrongdoer, in my fear.

                2.52
                Seeking protection, I sincerely call upon the noble Ākāśagarbha, Kṣitigarbha, and all the great compassionate ones.

                2.53
                I bow to Vajrapāṇi. At the sight of him, evil beings such as the messengers of Yama flee in terror in every direction.

                2.54
                In the past I ignored your words. But now, seeing the danger, I take refuge in you, terrified. Please quickly drive away my fear.

                2.55
                Even someone suffering from a minor illness should not disregard a physician’s advice. How much less should someone afflicted by hundreds of diseases, far more serious than these, ignore it?

                2.56
                A single one of these illnesses could destroy all the people living on the entire continent of Jambudvīpa, and no medicine to cure it could be found anywhere in the world.

                2.57
                Yet I disregard the words of the all-knowing physician who removes every thorn of suffering. Shame on me, so utterly deluded!

                2.58
                I am extremely cautious even near ordinary precipices. How much more careful should I be near the precipice that is thousands of leagues deep and stretches across a long span of time?

                2.59
                Death may not come today, but it is not right for me to sit back in comfort. The time will certainly arrive when I will no longer exist.

                2.60
                Who has promised me safety? How will I escape? I will certainly cease to exist. So why does my mind remain so at ease?

                2.61
                What lasting value remains in the things I experienced and then lost? Because I was attached to them, I disregarded the words of my teachers.

                2.62
                After leaving this world of living beings, and leaving behind my family and familiar companions, I will go somewhere alone. What use will all those I loved or disliked be to me then?

                2.63
                Therefore, day and night, this alone is the proper concern for me: how can I escape the certain suffering that comes from wrongdoing?

                2.64
                Whatever wrongdoing I, childish and confused, have committed, whether wrong by its very nature or wrong because it was prohibited,

                2.65
                I now openly acknowledge it all before the guides. With folded hands, terrified of suffering, I bow again and again.

                2.66
                O guides, please accept this confession as a confession of my wrongdoing. This was not good, and I promise not to do it again.

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 2, “Pāpadeśanā” (“Confession of Wrongdoing”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 2.
            """.trimIndent(),
            quiz = null
        ),
        Lesson(
            id = 103,
            title = "Chapter 3 — Embracing the Awakening Mind",
            summary = "Bodhicittaparigrahaḥ: rejoicing in merit, offering oneself for the welfare of all beings, and embracing bodhicitta.",
            content = """
                The Bodhicaryāvatāra
                Chapter 3 — Embracing the Awakening Mind
                Bodhicittaparigrahaḥ

                3.1
                I rejoice wholeheartedly in every wholesome deed performed by living beings that brings relief from the suffering of the lower realms. May those who suffer find happiness and peace.

                3.2
                I rejoice in the liberation of embodied beings from the suffering of cyclic existence. I rejoice in the awakening of those who protect the world, in their attainment of the bodhisattva stages and of Buddhahood.

                3.3
                I rejoice in the oceans of awakening mind that arise and bring happiness to all living beings, and in the teachings and actions that establish the welfare of every being.

                3.4
                With folded hands, I ask the fully awakened Buddhas in every direction: Please light the lamp of the Dharma for those who are falling into suffering through confusion.

                3.5
                With folded hands, I beg those Buddhas who wish to pass into final nirvana: Please remain for countless ages. Do not leave this world blind and in darkness.

                3.6
                Through whatever goodness I have gathered by doing all these things, may I become the one who brings an end to every suffering of every living being.

                3.7
                For those who are ill, may I become medicine, a physician, and the one who cares for them, until their illness has been completely overcome.

                3.8
                May I overcome the suffering of hunger and thirst by bringing down showers of food and drink. During times of famine, may I myself become food and drink.

                3.9
                For those who are poor and destitute, may I become an inexhaustible treasure. May I stand before them, ready to serve in every way they need.

                3.10
                I freely give away my body, my possessions, and all the goodness accumulated throughout the past, present, and future, so that the welfare of all living beings may be fulfilled.

                3.11
                Complete giving is nirvana, and my mind longs for nirvana. If everything must eventually be given up, it is best that I give it all to living beings.

                3.12
                I have made this body available for the happiness of all embodied beings. Let them strike it, insult it, or cover it with dust as they please.

                3.13
                Let them play with my body, laugh at it, and treat it as they wish. I have given it to them. What need have I to worry about it?

                3.14
                Let them have me perform whatever actions bring them happiness. May no one ever come to harm because of me.

                3.15
                For anyone whose thoughts become angry or joyful because of me, may that very connection become a cause for their fulfillment of every good purpose, now and always.

                3.16
                May all those who slander me, harm me, or mock me, and all others who act against me, become participants in awakening.

                3.17
                May I be a protector for those without protection, a guide for travelers, a boat, a bridge, and a crossing for those who wish to reach the other shore.

                3.18
                May I be a lamp for those who need a lamp, a bed for those who need a bed, and a servant for all living beings who need a servant.

                3.19
                May I become a wish-fulfilling jewel, a magic vessel, a sacred formula, a great medicine, a wish-granting tree, and a wish-fulfilling cow for all embodied beings.

                3.20
                Like the earth and the other elements, and like space itself, may I serve in countless ways as the support and means of enjoyment for all the immeasurable living beings who dwell throughout space.

                3.21
                In the same way, for as long as all living beings have not reached nirvana, may I remain a source of support and sustenance for them throughout the whole expanse of space.

                3.22
                Just as the Buddhas of the past embraced the awakening mind, and just as they followed the stages of training in the bodhisattva discipline,

                3.23
                so too do I generate the awakening mind for the welfare of the world, and I will train in those disciplines, step by step, in the same way.

                3.24
                Having joyfully embraced the awakening mind in this way, a wise person should strengthen and nourish that joy by reflecting on it again and again.

                3.25
                Today my birth has borne fruit. I have obtained a human life well used. Today I have been born into the family of the Buddhas; now I am a child of the Buddhas.

                3.26
                From this day forward, I must act in a way worthy of this family, so that no stain may bring disgrace upon this pure and noble lineage.

                3.27
                Just as a blind person might happen upon a precious jewel in a heap of rubbish, somehow this awakening mind has arisen within me.

                3.28
                This is the elixir born to overcome the death of the world. It is an inexhaustible treasure that puts an end to the world's poverty.

                3.29
                This is the supreme medicine that heals the world's illnesses, and the tree beneath which the world, exhausted by wandering along the roads of existence, can find rest.

                3.30
                It is a bridge for all travelers to cross beyond the lower realms, and a rising moon of awakening mind that soothes the afflictions of the world.

                3.31
                It is a great sun that drives away the darkness of the world's ignorance, and the fresh butter that rises from the churning of the milk of the true Dharma.

                3.32
                For the caravan of living beings traveling along the road of existence, hungry for the enjoyment of happiness, this is a feast of joy prepared for them, welcoming and satisfying every guest.

                3.33
                Today I have invited the whole world to Buddhahood and happiness in the time before that is attained. May the gods, demigods, and all others rejoice in this!

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 3, “Bodhicittaparigrahaḥ” (“Embracing the Awakening Mind”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 3.
            """.trimIndent(),
            quiz = null
        ),
        Lesson(
            id = 104,
            title = "Chapter 4 — Vigilance Regarding the Awakening Mind",
            summary = "Bodhicittāpramādaḥ: safeguarding the awakening mind, recognizing the rarity of human opportunity, and overcoming mental afflictions.",
            content = """
                The Bodhicaryāvatāra
                Chapter 4 — Vigilance Regarding the Awakening Mind
                Bodhicittāpramādaḥ

                4.1
                Having firmly taken up the awakening mind, a child of the Victorious Ones should make a constant, tireless effort not to neglect the trainings.

                4.2
                It is reasonable to reconsider a promise made hastily, before thinking it through, when one is unsure whether one can fulfill it.

                4.3
                But what the Buddhas and their profoundly wise children have examined, I too should undertake to the best of my ability. Why should I hesitate?

                4.4
                If, after making such a promise, I fail to put it into practice, having deceived all these beings, what will become of me?

                4.5
                It is said that even someone who thinks of giving a small thing but then withholds it becomes a hungry ghost.

                4.6
                What, then, will happen if I loudly and sincerely promise the highest happiness, only to deceive the entire world?

                4.7
                Only the Omniscient One understands the inconceivable workings of karma: even those who abandon the awakening mind may, through this, be released.

                4.8
                For a bodhisattva, this is therefore the gravest kind of downfall: by falling into it, one brings harm to the welfare of all beings.

                4.9
                Whoever obstructs even for a moment the goodness of such a person, whose purpose is the welfare of beings, faces an unending course of unfortunate rebirths.

                4.10
                If harming the welfare of even one being brings such ruin, what of harming the welfare of beings who fill all the reaches of space?

                4.11
                Because of the force of such downfalls, and because of the power of the awakening mind, one may continue wavering in saṃsāra and delay reaching the bodhisattva grounds.

                4.12
                So I must earnestly carry out what I have promised. If I make no effort today, I will sink lower and lower.

                4.13
                Countless Buddhas have come and gone, seeking the liberation of every being. Yet through my own faults, I have not come within reach of their healing care.

                4.14
                If I remain as I have been, again and again, I will continue to undergo suffering in the lower realms, illness, death, dismemberment, and all the rest.

                4.15
                When will I again obtain such rare conditions as the appearance of a Tathāgata, faith, a human life, and the ability to cultivate what is wholesome?

                4.16
                This day of health, this life free from hardship, with food and shelter available, is fleeting. Life can vanish in an instant, like a body borrowed for a moment.

                4.17
                With conduct such as mine, this human life will not be easy to obtain again. If I cannot obtain a human life, how will I practice what is wholesome?

                4.18
                If, though capable of practicing what is wholesome, I do not do so, what will I do when I am overwhelmed and confused by the suffering of the lower realms?

                4.19
                For those who fail to cultivate goodness while accumulating harmful actions, even the very words “fortunate rebirth” may disappear for hundreds of millions of ages.

                4.20
                That is why the Blessed One said that a human life is exceedingly difficult to obtain—like a turtle placing its neck through the hole in a yoke floating on the vast ocean.

                4.21
                A single moment of harmful action can lead to an age in the Avīci hell. What can be said, then, about a fortunate rebirth after wrongdoing accumulated since beginningless time?

                4.22
                Nor is one freed simply by experiencing the consequences of that wrongdoing. While undergoing those consequences, one may create still more harmful actions.

                4.23
                There is no greater deception, no greater delusion, than to obtain such an opportunity and fail to cultivate what is wholesome.

                4.24
                If I understand all this and still give in to confusion again, I will suffer for a long time, driven onward by the messengers of death.

                4.25
                The unbearable fires of hell will burn my body for a long time, and the fire of regret will burn my untrained mind for just as long.

                4.26
                Though I have somehow reached this rare ground of opportunity and understand its value, I am led once again toward those same hells.

                4.27
                It is as though I have lost the power to choose, as though I were enchanted by spells. I do not know what deludes me or what has taken up residence within me.

                4.28
                These enemies—craving, hatred, and the rest—have no hands or feet. They are neither brave nor wise. How, then, have they made me their servant?

                4.29
                They dwell in my own mind and, while remaining there, destroy me. Yet I feel no anger toward them. How shameful that I tolerate this!

                4.30
                Even if all the gods and humans were my enemies, they could not drag me into the fires of the Avīci hell.

                4.31
                The afflictions, these powerful enemies, can cast me into that place in an instant, where not even the ashes of Mount Meru would remain.

                4.32
                No other enemy has a life as long as these enemies, the mental afflictions: they have been with me for ages without beginning or end.

                4.33
                When treated with kindness, everyone else may become helpful. But the more these afflictions are indulged, the more they cause suffering.

                4.34
                When such enduring enemies live in my heart, giving rise to torrents of misery, how can I delight in the comforts of saṃsāra without fear?

                4.35
                These are the guards of the prison of existence; they are executioners in the hells and elsewhere. If they remain in the cage of craving within my mind, where can I find happiness?

                4.36
                So I will not lay down my effort until I have directly defeated these enemies. Ordinary people become furious over even a small injury and cannot sleep until they have struck back.

                4.37
                People who are naturally subject to death and suffering, though weak and vulnerable, enter the battlefield fiercely, enduring the pain of arrows and weapons without turning away until they achieve their aim.

                4.38
                How much more should I, who have set out to destroy the natural enemies that cause every kind of suffering without end, refuse to fall into discouragement—whatever hundreds of hardships may come?

                4.39
                Some people wear the wounds inflicted by enemies as ornaments, though they serve no higher purpose. Why, then, should suffering obstruct me when I am striving for a great purpose?

                4.40
                Fishermen, outcast workers, farmers, and others endure cold, heat, and many hardships simply to earn a living. Why should I not endure hardship for the welfare of the whole world?

                4.41
                I have promised to free the world, extending to the limits of the ten directions, from its afflictions. Yet I have not even freed myself from my own.

                4.42
                Not knowing the measure of my own strength, was I speaking like a madman? Therefore I will never turn back from the struggle to overcome the afflictions.

                4.43
                I will hold firmly to this struggle and remain their implacable enemy. My only concern will be to destroy the afflictions—not to indulge in any other kind of conflict.

                4.44
                Let my entrails spill out, let my head fall; still, I will never bow down to these enemies, the mental afflictions.

                4.45
                An ordinary enemy, even after being driven from one country, may settle elsewhere and return with renewed strength. The afflictions have no such independent place to go.

                4.46
                Once driven from my mind, where could these enemies remain? From what place could they try to destroy me? They are weak; they are overcome by the insight of wisdom. My own weakness and lack of resolve are the only obstacles.

                4.47
                The afflictions are not located in objects, in the senses, or in the space between them. Where, then, do they dwell while tormenting the whole world? They are like illusions. Let go of your fear and strive for wisdom. Why suffer needlessly in hell?

                4.48
                Having reached this conclusion, I will make the effort to put the trainings into practice as they have been taught. How can someone who ignores a physician’s instructions expect to recover through medicine?

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 4, “Bodhicittāpramādaḥ” (“Vigilance Regarding the Awakening Mind”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 4.
            """.trimIndent(),
            quiz = null
        ),
        Lesson(
            id = 105,
            title = "Chapter 5 — Guarding Alertness",
            summary = "Saṃprajanyarakṣaṇaṃ: guarding the mind, maintaining mindfulness and clear awareness, and practicing bodhisattva conduct in daily life.",
            content = """
                The Bodhicaryāvatāra
                Chapter 5 — Guarding Alertness
                Saṃprajanyarakṣaṇaṃ

                5.1
                Those who wish to protect their training must carefully protect their minds. Without guarding a restless mind, it is impossible to protect one’s training.

                5.2
                An untamed, raging elephant does not cause the kind of suffering that an unrestrained elephant of the mind can bring in the hells and elsewhere.

                5.3
                If the elephant of the mind is bound on every side with the rope of mindfulness, all danger disappears and every good quality comes within reach.

                5.4
                Tigers, lions, elephants, bears, snakes, and every other enemy;

                5.5
                all the guardians of hell, demons, and other terrifying beings—all are restrained by restraining this one mind. When this mind is tamed, they are all tamed.

                5.6
                The one who speaks truthfully has explained that all fears and immeasurable sufferings arise from the mind alone.

                5.7
                Who carefully forged the weapons in the hells? Who made the burning iron ground? Where did those women come from?

                5.8
                The Sage taught that all these things arise from a harmful state of mind. Nothing in the three worlds is more frightening than the mind.

                5.9
                If the perfection of generosity meant making the whole world free from poverty, how could the former protectors of the world have achieved it, when the world is still poor?

                5.10
                The perfection of generosity is said to be the intention to give everything, together with its results, to everyone. It is therefore a quality of the mind itself.

                5.11
                Where could I possibly take all the fish and other creatures, so that I would not have to kill them? The perfection of ethical discipline is achieved when the intention to refrain from harm is cultivated.

                5.12
                How could I kill all the wicked beings, whose number is as limitless as the sky? But when the mind of anger is killed, all enemies are killed.

                5.13
                Where could I find enough leather to cover the entire earth? Yet the earth is covered by wearing leather on the soles of my feet.

                5.14
                In the same way, I cannot control external things. But I can control my own mind. What need is there to control anything else?

                5.15
                Even when someone acts with both body and speech, if the mind is dull and distracted, the result is limited. A focused mind alone can bring even the highest attainments.

                5.16
                The Omniscient One taught that recitations and austerities, even when practiced for a long time, are fruitless when the mind is elsewhere and unfocused.

                5.17
                Those who have not cultivated this essential, hidden principle of the teaching—the mind—wander about in vain, hoping to escape suffering and find happiness.

                5.18
                Therefore, I must carefully guard and steady my own mind. What use are many vows if I abandon the vow to guard the mind?

                5.19
                Just as someone living among careless people protects a wound with great care, so I should always protect the wound of my mind while living among harmful influences.

                5.20
                If I carefully protect a wound because I fear even a little pain, why do I not protect the wound of my mind, fearing the crushing blows of mountains?

                5.21
                By living in this way, even among difficult people and in the midst of distractions, a wise practitioner remains steady and unbroken.

                5.22
                Let my wealth, honor, reputation, body, and livelihood be lost. Let other forms of goodness be lost too—but never let my mind be lost.

                5.23
                With joined palms, I urge those who wish to protect their minds: guard mindfulness and clear awareness with every effort.

                5.24
                Just as a person overwhelmed by illness cannot carry out ordinary tasks, a mind weakened by the loss of mindfulness and clear awareness cannot accomplish anything.

                5.25
                For a mind lacking clear awareness, what has been heard, considered, and cultivated does not remain in memory—just as water does not stay in a cracked pot.

                5.26
                Many people have learned much, have faith, and make great efforts, yet the fault of lacking clear awareness leads them into wrongdoing.

                5.27
                The thief of absent-mindedness steals the merit accumulated through effort and leads people toward unfortunate states of rebirth.

                5.28
                A band of afflictions waits for an opening. Once it finds one, it steals the good fortune of a favorable rebirth and destroys it.

                5.29
                Therefore, mindfulness must never be allowed to leave the doorway of the mind. If it has slipped away, bring it back by remembering the suffering of unfortunate states.

                5.30
                For fortunate people who act carefully out of respect, fear of wrongdoing, and the guidance of their teachers, mindfulness becomes easier to maintain.

                5.31
                The Buddhas and bodhisattvas see without obstruction in every direction. I stand before them at all times.

                5.32
                Remembering this, one should remain mindful, filled with a sense of modesty, respect, and care. In this way, recollection of the Buddhas will arise again and again.

                5.33
                When mindfulness stands guard at the doorway of the mind, clear awareness appears—and, once it has appeared, it does not easily depart.

                5.34
                First, I must establish this mind firmly and keep it present. I should remain still, like a piece of wood, when there is no need to act.

                5.35
                I should never make pointless movements with my eyes. I should keep my gaze lowered and calm, as though resting in meditation.

                5.36
                From time to time, I may look around to rest my eyes. If I notice someone approaching, I should look up and acknowledge them.

                5.37
                I should look in every direction repeatedly, especially when traveling, so that I notice danger. After pausing, I should also look behind me.

                5.38
                Before moving forward or turning back, I should first examine what lies ahead and behind. In every situation, I should act only after understanding what needs to be done.

                5.39
                Having decided, “This is how my body should remain,” I should periodically check how it is actually positioned.

                5.40
                With every effort, I should examine the wild elephant of my mind and keep it firmly tied to the great pillar of reflection on the Dharma, so that it cannot break free.

                5.41
                I should continually examine where my mind is. I should not let go, even for a moment, of the responsibility to keep it focused.

                5.42
                When danger, celebration, or similar circumstances arise, I may act as the situation permits. But at the time of giving, the teaching says that rules of discipline may be set aside when compassion requires it.

                5.43
                Once I have understood what I am setting out to do, I should think of nothing else. With my whole mind directed toward it, I should complete that task first.

                5.44
                In this way, everything I do can be done well. Otherwise, neither task will be accomplished, and the afflictions caused by lack of clear awareness will only grow.

                5.45
                When all kinds of pointless chatter and distractions are taking place, I should put an end to the excitement and curiosity that arise in me.

                5.46
                When I notice myself playing with dirt, pulling grass, or making meaningless marks, I should remember the Buddha’s training and stop at once, with care.

                5.47
                Whenever I wish to move or speak, I should first examine my own mind and then act calmly and appropriately.

                5.48
                When I see that my mind is attached or hostile, I should neither act nor speak. I should remain still, like a piece of wood.

                5.49
                When my mind becomes arrogant, mocking, boastful, conceited, or deceitful,

                5.50
                when I feel inclined to praise myself or criticize others, to speak harshly, or to stir up conflict, I should remain still, like a piece of wood.

                5.51
                When my mind seeks gain, honor, or fame, or longs for a following and people to attend to me, I should remain still, like a piece of wood.

                5.52
                When my mind wants to speak harshly to others, pursue my own advantage, or attract an audience, I should remain still, like a piece of wood.

                5.53
                When my mind becomes impatient, lazy, fearful, bold inappropriately, talkative, or stubbornly attached to its own side, I should remain still, like a piece of wood.

                5.54
                Seeing my mind disturbed in these ways, or eager to begin something pointless, a courageous person should firmly restrain it with the appropriate antidote.

                5.55
                I should cultivate a mind that is steady, confident, clear, respectful, modest, cautious, peaceful, and devoted to helping others.

                5.56
                I should not be worn down by the conflicting wishes of immature people. I should remember with compassion that these wishes arise from their afflictions.

                5.57
                I should maintain a mind that is free from pride, like an illusion, while remaining constantly under my own control and devoted to what is blameless.

                5.58
                Again and again, I should remember this rare opportunity, obtained after a very long time, and keep my mind as unshakable as Mount Meru.

                5.59
                When vultures, eager for flesh, pull this body in every direction, the body does not resist. Why, then, do you protect it so possessively?

                5.60
                Why do you guard this mind so carefully by treating this body as “me”? If the body is separate from you, what does its condition have to do with you?

                5.61
                Foolish mind, you do not regard a clean wooden puppet as yourself. Why do you protect this foul body, a machine assembled from impure things?

                5.62
                Use your own understanding to separate this skin covering from the body. With the sword of wisdom, remove the flesh from the skeleton.

                5.63
                Separate the bones as well and examine the marrow within. Ask yourself: what is truly substantial here?

                5.64
                After searching carefully, you have found no lasting essence in this body. So tell me: why do you still protect it so possessively?

                5.65
                You cannot eat its impurities, drink its blood, or suck its entrails. What use is this body to you?

                5.66
                It is understandable to preserve the body as food for vultures and jackals. But the human body is an instrument for carrying out beneficial actions.

                5.67
                Even if you protect it in every way, merciless death will eventually seize this body and give it to the vultures. What will you do then?

                5.68
                You do not give clothes and other provisions to a servant who will not stay. This body will leave after consuming what you give it. Why spend so much effort on it?

                5.69
                So pay this body its wages, and now use it for your own true purpose. Do not give everything to a servant who has been hired for a task.

                5.70
                Treat the body like a boat, a means of coming and going. Make it serve your wishes by using it to accomplish the welfare of beings.

                5.71
                When you have brought yourself under control in this way, always wear a gentle smile. Let go of frowning and scowling. Be a friend to the world and speak first.

                5.72
                Do not noisily or abruptly move chairs and other objects. Do not slam doors. Always take pleasure in quietness.

                5.73
                A heron, a cat, and a thief move quietly and carefully, accomplishing what they intend. A practitioner should always act with similar attentiveness.

                5.74
                Always receive with respect, even on your head, the advice of those who are skilled at pointing out faults and offering help without being asked. Become a student of everyone.

                5.75
                Praise every well-spoken word, and when you see someone doing something good, encourage them with appreciation.

                5.76
                Speak of others’ good qualities when they are not present, and repeat praise you have heard with genuine pleasure. When others speak of your own qualities, appreciate that they recognize goodness.

                5.77
                All endeavors are undertaken for the sake of happiness, but happiness is difficult to obtain even with wealth. So let me enjoy the happiness created by the good qualities of others.

                5.78
                There is no loss to me in doing this, and it brings great happiness in the future. But resentment brings great suffering, both now and later.

                5.79
                I should speak plainly and understandably, in a pleasant way, with words that are easy to hear, gentle, unhurried, and rooted in compassion.

                5.80
                I should always look directly and kindly at beings, as though drinking them in with my eyes. It is through these beings that I will attain awakening.

                5.81
                Great goodness comes from sustained dedication, from applying the appropriate antidotes, from serving those who are worthy of support, and from helping those who suffer.

                5.82
                I should be capable, energetic, and self-reliant. I should not leave any task entirely unattended.

                5.83
                The perfections, beginning with generosity, become progressively more profound. I should not abandon a higher practice for the sake of a lower one, except when doing so is necessary to preserve the boundaries of ethical conduct.

                5.84
                Understanding this, I should always make an effort for the welfare of others. For a compassionate person who understands what is beneficial, even something ordinarily forbidden may be permitted when circumstances require it.

                5.85
                I should share with those who have fallen into hardship, those without protection, and those committed to the monastic life. I should eat only a moderate amount and give away everything beyond the three robes.

                5.86
                I should not harm this body, which serves the Dharma, for the sake of something less important. By using it in this way, I can quickly fulfill the hopes of beings.

                5.87
                Therefore, I should not give up my life when my compassionate intention is impure. But when my intention is equally pure, I may give it up. In this way, I will not fall short of the path.

                5.88
                I should not teach the Dharma to someone who is disrespectful, healthy but wearing a head covering, or carrying an umbrella, staff, or weapon, or whose head is otherwise covered.

                5.89
                I should not teach profound and elevated teachings to those who are not ready for them. I should show equal respect to teachings of different levels, and not teach a man in the presence of a woman without an appropriate reason.

                5.90
                I should not place someone suited to profound teachings into a lesser teaching. Nor should I abandon proper conduct and entice people with scriptures or mantras.

                5.91
                I should not spit out tooth-cleaning sticks or saliva in places used by others, nor should I relieve myself in water or on land where people may use it.

                5.92
                I should not eat with my mouth full, noisily, or with my mouth hanging open. I should not sit with my legs stretched out, nor rub my hands together inappropriately.

                5.93
                I should not travel, sleep, or sit alone with another person’s spouse. Having observed and asked about what may displease the world, I should avoid it.

                5.94
                I should not point with one finger. I should gesture respectfully with my whole hand, and likewise indicate the way.

                5.95
                I should not wave my arms about or shout over a minor concern. I should instead make a quiet signal, such as a finger snap; otherwise, I would be behaving without restraint.

                5.96
                I should lie down in the direction chosen by the Buddha for his final repose, remain fully aware, and rise promptly and deliberately.

                5.97
                The conduct of bodhisattvas has been described as immeasurable. At the very least, I should certainly practice the conduct that purifies the mind.

                5.98
                Three times by day and three times by night, I should recite the Three Sections. Through the power of the Buddhas and the awakening mind, this will help pacify any remaining faults.

                5.99
                Whatever situation I find myself in, whether by my own choice or through the control of others, I should diligently practice the trainings appropriate to that situation.

                5.100
                There is nothing that bodhisattvas should not learn, and there is no merit that cannot arise for someone who lives in this way.

                5.101
                Whether directly or indirectly, I should do nothing that does not serve the welfare of beings. For the sake of beings alone, I should dedicate everything to awakening.

                5.102
                I should never abandon a spiritual friend, even at the cost of my life—a person who upholds the bodhisattva vows and understands the meaning of the Great Vehicle.

                5.103
                I should learn how to relate to a spiritual teacher from the Liberation of Śrīsaṃbhava. I should also learn this and other teachings spoken by the Buddha by studying the sutras.

                5.104
                The trainings are found in the sutras; therefore, I should read the sutras. I should also examine the fundamental downfalls described in the Ākāśagarbha Sutra.

                5.105
                I should certainly read the Compendium of Trainings again and again, because it explains in detail the conduct that should always be followed.

                5.106
                Or, at least, I should study the Compendium of Sutras, composed by the noble Nāgārjuna, with diligent care.

                5.107
                I should examine the teachings to see what is prohibited and what is encouraged, and then put them into practice in order to protect the minds of ordinary people.

                5.108
                In brief, the defining feature of clear awareness is this: repeatedly examining the condition of one’s body and mind.

                5.109
                I will put these teachings into practice with my body. What is the use of merely reciting words? What good is it to a sick person simply to read a medical prescription?

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 5, “Saṃprajanyarakṣaṇaṃ” (“Guarding Alertness”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 5.
            """.trimIndent(),
            quiz = null
        ),
        Lesson(
            id = 106,
            title = "Chapter 6 — The Perfection of Patience",
            summary = "Kṣāntipāramitā: overcoming anger and hatred, enduring hardship, and cultivating patience toward all beings.",
            content = """
                The Bodhicaryāvatāra
                Chapter 6 — The Perfection of Patience
                Kṣāntipāramitā

                6.1
                All the good deeds gathered over thousands of ages—generosity, offerings to the Buddhas, and every other wholesome act—can be destroyed by a single moment of anger.

                6.2
                There is no wrongdoing equal to hatred, and no spiritual discipline equal to patience. Therefore, cultivate patience diligently in every way.

                6.3
                When the thorn of hatred is lodged in the heart, the mind finds no peace, no joy, no rest, and no steadiness.

                6.4
                Even people whom an angry person has honored and supported may turn against them, because hatred makes them difficult to live with.

                6.5
                Friends become uneasy around such a person. They may give things away, yet others do not want to spend time with them. In short, an angry person has no lasting happiness.

                6.6
                Recognizing that hatred creates such suffering, the one who firmly overcomes anger becomes happy in this life and beyond.

                6.7
                Hatred feeds on the frustration of unwanted events and the obstruction of what we want. Once it has been fed by distress, it destroys me.

                6.8
                Therefore, I will deprive this enemy of its food. Hatred has no purpose other than to destroy me.

                6.9
                Even when something extremely unpleasant happens, I will not let my joy be disturbed. Distress brings no benefit; it only weakens what is wholesome.

                6.10
                If there is a remedy for a problem, what is the use of becoming distressed? If there is no remedy, what is the use of becoming distressed?

                6.11
                Humiliation, harsh words, and loss of reputation are things I dislike, whether they happen to me or to those I love. But my enemies may welcome their opposites.

                6.12
                Happiness is hard to find, while suffering comes without effort. Yet suffering can bring an end to the cycle of existence. So, mind, be strong.

                6.13
                People endure the pain of burning, cutting, and other ordeals for the sake of liberation. Why, then, should I be so timid?

                6.14
                There is nothing that cannot become easier through practice. By growing accustomed to small discomforts, I can learn to endure great suffering.

                6.15
                Do you not see how much needless suffering comes from insect bites, hunger, thirst, and painful itching?

                6.16
                I should not pamper myself against cold, heat, rain, wind, illness, or injury. Otherwise, my discomfort will only grow.

                6.17
                Some people become faint at the sight of their own blood, while others faint at the sight of someone else’s.

                6.18
                This difference comes from the strength or weakness of the mind. Therefore, I should become strong in the face of suffering and overcome it.

                6.19
                A wise person should not let suffering disturb the clarity of the mind. The struggle against the afflictions is a battle, and pain is common in battle.

                6.20
                Those who endure terrible blows to the chest and defeat their enemies are the true heroes. The rest are merely those who have been struck down.

                6.21
                Another benefit of suffering is that it can awaken disillusionment with arrogance, compassion for beings caught in saṃsāra, fear of harmful actions, and devotion to the Buddha.

                6.22
                I do not become angry at bile and other bodily conditions, even though they cause great suffering. Why become angry at conscious beings? They too are provoked by conditions.

                6.23
                Just as a painful wound may arise without being wanted, anger can arise forcefully even when it is not wanted.

                6.24
                No one thinks, “I will become angry,” and then freely chooses anger. Anger arises when its conditions come together.

                6.25
                Whatever wrongs and harmful actions occur arise through the power of conditions. Nothing acts independently.

                6.26
                People do not deliberately decide, “I will create this entire collection of conditions.” Nor does an action, once produced, decide, “I have now been produced.”

                6.27
                What we imagine to be the chief thing we want, or what we call the self, does not arise by first deciding, “I will become this.”

                6.28
                If it has not arisen, there is nothing there to desire becoming. And because it is occupied with its objects, it does not simply will itself to stop.

                6.29
                If the self were permanent and without awareness, like space, it could not act. How could something unchanging perform an action, even when other conditions are present?

                6.30
                If it had already acted as it does now, what further action would be performed? If the action belongs to it, what establishes that relationship?

                6.31
                Everything is dependent on other things, and those other things are dependent too. Like magical creations, beings have no independent control. At whom, then, should anger be directed?

                6.32
                Someone may ask, “If this is so, why try to prevent anything? Who could prevent what?” It is precisely because things arise dependently that suffering can cease.

                6.33
                Therefore, whether I see a friend or an enemy acting unjustly, I can remain at ease by understanding that such behavior arises from conditions.

                6.34
                If beings could achieve everything simply by wishing, no one would suffer, because no one wishes to suffer.

                6.35
                People harm themselves through carelessness—with thorns, by cutting their food badly, through anger, or through longing for what is difficult to obtain.

                6.36
                When people, under the power of afflictions, even harm themselves, how can I expect them never to harm others?

                6.37
                When people are driven mad by afflictions and turn against themselves, why should I feel anger rather than compassion?

                6.38
                If harming others were simply the nature of foolish people, anger toward them would make no more sense than anger toward fire for burning.

                6.39
                If harming others is only a passing condition, while beings are gentle by nature, anger is just as misplaced as anger at the sky for carrying smoke.

                6.40
                If I become angry at the person who urges someone to strike me, rather than at the weapon, I should remember that the person is driven by hatred. Better to direct my anger at hatred itself.

                6.41
                I too have caused beings this kind of suffering in the past. Therefore, it is only fitting that I now experience harm from those I have troubled.

                6.42
                The weapon and my body are both causes of the pain. The other person takes up the weapon, and I offer the body. At whom should I be angry?

                6.43
                This body is like a sore, easily hurt by touch. I grasp at it blindly through craving. When pain comes, whom should I blame?

                6.44
                I do not want suffering, yet I cling to its causes. Foolish as I am, why blame someone else for suffering that comes from my own actions?

                6.45
                Just as the beings and birds in the hell of razor-sharp leaves arise through my karma, so does this situation. At whom should I be angry?

                6.46
                Those who harm me have arisen through the force of my own karma. Because of my actions, they may go to hell. Have I not, in effect, brought about their destruction?

                6.47
                By relying on these people, much of my wrongdoing can be exhausted through patience. By relying on me, they may undergo long suffering in hell.

                6.48
                I am the one who harms them; they are the ones who help me. Why, perverse mind, do you reverse the truth and become angry?

                6.49
                If my good intention keeps me from going to hell, what does that have to do with them? I may have protected myself, but what benefit have they gained?

                6.50
                Even if I retaliate, they will not be protected. My own practice will also deteriorate. In that case, my spiritual discipline will have been lost.

                6.51
                No one can directly injure the mind, because it has no physical form. But because I cling to the body, I am troubled by bodily pain.

                6.52
                Insults, harsh words, and loss of reputation do not injure the body. Why, then, mind, do you become angry?

                6.53
                If someone loses confidence in me, how does that consume me, in this life or another, that I should dislike it so much?

                6.54
                If I dislike someone because they obstruct my gains, those gains will disappear in this very life, while the harmful karma will certainly remain.

                6.55
                It would be better for me to die today than to live a long life dishonestly. Even if I live a long time, the suffering of death will still come.

                6.56
                Someone who enjoys a hundred years of happiness in a dream and then wakes, and someone who enjoys happiness for only a moment and then wakes, both find that the happiness has ended.

                6.57
                When both awaken, the pleasure is gone. In the same way, whether life is long or short, death brings it to an end.

                6.58
                Even after gaining much wealth and enjoying many pleasures for a long time, I will leave empty-handed and naked, as though I had been robbed.

                6.59
                If I use my gains to exhaust wrongdoing and accumulate merit, but become angry for the sake of those gains, then I will destroy merit and accumulate wrongdoing.

                6.60
                If the very thing for which I live is lost, what good is a life spent doing nothing but harmful things?

                6.61
                If I think anger is justified because someone speaks badly of me and destroys the confidence of others, why do I not become angry when someone damages another person’s reputation?

                6.62
                You can be patient with those who dislike you because their attitude depends on other conditions. But you are not patient with those who criticize you, even though your anger also depends on conditions.

                6.63
                I should not become angry at those who damage images, stupas, or the true Dharma, because the Buddhas and the Dharma cannot be harmed in that way.

                6.64
                When people harm my teachers, relatives, or loved ones, I should restrain anger by seeing, as before, that their actions arise from conditions.

                6.65
                The suffering of embodied beings is caused by both conscious and unconscious things. Since I accept pain caused by unconscious things, I should learn to endure pain caused by conscious beings too.

                6.66
                Some people do wrong out of confusion, while others become angry because they too are confused. Whom can we call entirely innocent, and whom entirely at fault?

                6.67
                Why did I act in the past in ways that bring me harm from others now? Everyone is subject to karma and conditions. Who am I to make things otherwise?

                6.68
                Understanding this, I will strive to cultivate goodness so that all beings may become loving toward one another.

                6.69
                When a house is burning and the fire spreads to another building, anything that can catch fire—straw and the like—is pulled away and removed.

                6.70
                In the same way, when my mind is burning with the fire of hatred because of attachment, I should immediately let go of it, for fear that my merit will be consumed.

                6.71
                If someone must be cut or injured to be freed from a worse danger, what is wrong with that? If ordinary human suffering can prevent hell, what is wrong with enduring it?

                6.72
                If I cannot endure even this small suffering now, why do I not restrain anger, the cause of the torments of hell?

                6.73
                For the sake of anger, I have endured thousands of hellish sufferings, yet I have done nothing for my own benefit or for the benefit of others.

                6.74
                This suffering is not of that kind. It can accomplish a great purpose. It is fitting to take joy in suffering that removes the suffering of the world.

                6.75
                If you feel happy when others praise someone for their virtues, why does your mind not rejoice when you praise that person too?

                6.76
                This happiness is blameless and a source of joy. The wise have not forbidden it; indeed, it is an excellent way to bring others close.

                6.77
                If you dislike this because the happiness belongs to someone else, then you should also give up wages, gifts, and every other benefit, both seen and unseen.

                6.78
                When your own qualities are praised, you want others to be happy. Yet when someone else’s qualities are praised, you do not want to be happy yourself.

                6.79
                Having generated the awakening mind, wishing for the happiness of all beings, why do you become angry when beings find happiness?

                6.80
                Why do you not wish for beings to attain Buddhahood, which is honored throughout the three worlds? Why burn with envy when you see them receive temporary respect?

                6.81
                Someone who supports the person you are responsible for is giving something to you as well. When you find someone who sustains your family, you are pleased, not angry.

                6.82
                How could someone who wishes beings to attain awakening not wish them well? How could someone whose mind is awakening become angry at another’s good fortune?

                6.83
                If someone does not receive a gift, it remains in the donor’s house. In any case, it is not yours. What does it matter whether it is given or withheld?

                6.84
                Why do you not become angry if someone prevents you from gaining merit, or if someone refuses to accept the good qualities you offer?

                6.85
                You do not merely fail to regret your own wrongdoing; you also want to compete with others who have accumulated merit.

                6.86
                If something unpleasant happens to your enemy, what does your happiness accomplish? Your wish alone will not make the event happen without causes.

                6.87
                If the event comes about through your wish, what happiness do you gain from another’s suffering? And if that is what you want, what could be more harmful?

                6.88
                This is a terrible hook, cast by the fisherman of the afflictions. The guardians of hell will seize you with it and cook you in their cauldrons.

                6.89
                Praise, reputation, and honor do not bring me merit, a longer life, strength, health, or physical comfort.

                6.90
                For someone wise who understands their own interests, that is all such things amount to. If I wanted only mental pleasure, I might as well pursue drinking and gambling.

                6.91
                People squander their wealth and even take their own lives for the sake of reputation. But what use are words after death? Who will enjoy that pleasure then?

                6.92
                When a child’s sandcastle is destroyed, the child cries in distress. In the same way, my mind becomes upset when praise and reputation are lost.

                6.93
                Because sound has no mind, it cannot praise me. The thought that another person is pleased with me is what makes me happy.

                6.94
                What benefit is another person’s pleasure to me, even if it is directed toward me? That happiness belongs to that person; I receive not even a small part of it.

                6.95
                If I should be happy because another person is happy, then I should be happy whenever anyone is happy. Why, then, do I not rejoice when others are pleased by someone else?

                6.96
                Thinking, “I have been praised,” and feeling pleased with myself is childish behavior, because there is no real connection between the praise and the self I imagine.

                6.97
                These things—praise and the like—disturb my sense of urgency and destroy my joy in virtue. They also make me jealous of those with good qualities and angry at their success.

                6.98
                Therefore, those who stand in the way of praise and similar things are actually protecting me from falling into unfortunate states.

                6.99
                For someone seeking liberation, it is wrong to be bound by gain and honor. How can I feel hatred toward those who free me from these chains?

                6.100
                Those who become a barrier at the doorway of suffering for someone who wants to enter it are like a blessing from the Buddhas. How can I be angry with them?

                6.101
                The thought that someone has obstructed my merit is not a reasonable basis for anger. There is no spiritual discipline equal to patience, and here is an opportunity to practice it.

                6.102
                If I fail to practice patience because of my own faults, then I myself have created the obstacle where an opportunity for merit has appeared.

                6.103
                That without which something cannot exist, and in whose presence it does exist, is its cause. How can that be called an obstacle?

                6.104
                A person who arrives at the proper time to receive a gift does not obstruct generosity. When someone comes to join the monastic life, that person is not an obstacle to ordination.

                6.105
                Begging people are easy to find in the world; those who cause harm are rare. Since I have harmed no one, few people have reason to harm me.

                6.106
                Therefore, an enemy who appears without effort, like a treasure found in my own home, is worthy of honor because they help my bodhisattva practice.

                6.107
                Through this person and through me, patience has arisen. So the fruit of patience should first be offered to them, for they were the first cause of it.

                6.108
                If an enemy does not intend to help me develop patience, they may not deserve honor for that intention. But why is the true Dharma, which is a fitting cause of accomplishment, honored?

                6.109
                If an enemy is not honored because they intend to harm me, how could patience arise? A person who acts as a remedy for illness is beneficial, even without intending to help.

                6.110
                Therefore, patience arises in dependence on an enemy’s harmful intention. For that reason, I should honor the enemy as I honor the true Dharma, because they are a cause of patience.

                6.111
                The Sage taught that beings are a field of merit, just as the Buddhas are. Many have reached perfection by honoring beings.

                6.112
                Since the qualities of Buddhahood arise equally in dependence on beings and Buddhas, why should I honor the Buddhas but not beings?

                6.113
                The greatness of an intention is shown by what it accomplishes, not by itself alone. In that respect, beings and Buddhas are equal.

                6.114
                The intention of loving-kindness is what makes beings worthy of honor. The merit that arises from faith in the Buddhas is due to the greatness of the Buddhas.

                6.115
                By the part they play in the arising of the Buddha’s qualities, beings are equal to the Buddhas. Yet no beings are equal to the Buddhas in their immeasurable ocean of qualities.

                6.116
                Even a tiny particle of virtue in someone who is a storehouse of excellent qualities deserves honor; the entire three worlds would not be enough to repay it.

                6.117
                The part of the Buddha’s qualities that can arise in beings is especially precious. Honoring beings in proportion to that potential is honoring what is worthy.

                6.118
                Moreover, beings are sincere friends who help us in immeasurable ways. What greater way could there be to repay them than to serve them?

                6.119
                For whose sake people endure having their bodies cut apart and enter Avīci hell, even toward those great wrongdoers I should act with goodness.

                6.120
                Even the Buddhas, who are my masters, are selfless for the sake of beings. How can I show pride toward these masters instead of behaving as their servant?

                6.121
                The great sages rejoice when beings are happy and are troubled when beings suffer. By pleasing beings, I please all the Buddhas; by harming beings, I harm them.

                6.122
                Just as no collection of pleasures can bring comfort to someone whose body is entirely on fire, there is no way for compassionate beings to be pleased when beings are suffering.

                6.123
                Today I confess before the great compassionate ones all the harm I have caused by bringing suffering to beings. May the sages forgive me for having distressed them.

                6.124
                To honor the Tathāgatas, I now place myself entirely in the service of the world. Let crowds of people step on my head or strike me; may the Protector of the World be pleased.

                6.125
                Without doubt, these compassionate ones have made the whole world their own. These beings are right here before me in living form. Why do I fail to respect them?

                6.126
                Serving beings is itself the worship of the Tathāgatas. It is also the fulfillment of my own purpose, and it removes the suffering of the world. Therefore, let this be my vow.

                6.127
                Just as a king’s servant may mistreat a crowd, yet a wise crowd does not retaliate because the servant is backed by the king’s power,

                6.128
                so I should not look down on anyone who has harmed me, however weak they may seem. The power of the guardians of hell and of the compassionate Buddhas supports all beings.

                6.129
                Therefore, I should serve beings as a servant would serve a fierce king. What could an angry king do to me that would compare with the suffering caused by displeasing beings?

                6.130
                What suffering could an angry king inflict that would compare with the torments of hell experienced because I have made beings unhappy?

                6.131
                What could a pleased king give me that would compare with Buddhahood, which arises from making beings happy?

                6.132
                Even setting aside the future Buddhahood that comes from serving beings, can you not see that even in this life patience brings good fortune, a good reputation, health, and well-being?

                6.133
                While wandering through saṃsāra, the patient person gains an attractive presence, good health, joy, long life, and the abundant happiness of a universal ruler.

                6.134
                Thus, patience brings the practitioner lasting well-being and supports the path to awakening.

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 6, “Kṣāntipāramitā” (“The Perfection of Patience”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 6.
            """.trimIndent(),
            quiz = null
        ),
        Lesson(
            id = 107,
            title = "Chapter 7 — The Perfection of Joyful Effort",
            summary = "Vīryapāramitā: overcoming laziness and discouragement, cultivating wholesome enthusiasm, and sustaining joyful effort on the path.",
            content = """
                The Bodhicaryāvatāra
                Chapter 7 — The Perfection of Joyful Effort
                Vīryapāramitā

                7.1
                In this way, having become patient, one should cultivate joyful effort, for awakening is grounded in effort. Without effort, merit does not grow, just as movement cannot occur without wind.

                7.2
                What is effort? It is enthusiasm for what is wholesome. What opposes it? Laziness, attachment to what is harmful, discouragement, and self-contempt.

                7.3
                Laziness arises from delight in idleness, craving for sleep, attraction to pleasures, and failure to feel concern about the suffering of saṃsāra.

                7.4
                Caught in the hunter’s snare of afflictions, you have entered the trap of birth. Do you still not realize that you have come within the jaws of death?

                7.5
                You do not even see your companions being led away and killed one after another. Yet you continue to sleep, like a buffalo being led to slaughter.

                7.6
                When Yama watches you and every path of escape is blocked, how can eating, sleeping, and pleasure still appeal to you?

                7.7
                Death will come before you have completed all the preparations you have gathered. Even if you abandon laziness then, what will you be able to do? It will be too late.

                7.8
                You will think, “This is unfinished; that has not even been begun; this is only half done. Death has arrived suddenly—how terrible, I am lost!”

                7.9
                You will see your relatives in despair, their faces swollen with grief, their eyes red and full of tears, and the messengers of death before you.

                7.10
                Burning with memories of your own harmful actions, hearing the cries of the hell realms, your body stained with fear, what will you do in your confusion?

                7.11
                You should fear even now, as a living fish fears being caught. How much more should you fear the intense suffering of hell after having committed harmful deeds?

                7.12
                You are so delicate that even hot water causes you pain. How can you sit at ease after creating the causes of hell?

                7.13
                You are lazy, yet you long for results; you are delicate, yet you endure so much pain. Though death has seized you, you act as if you were immortal. How miserable you are!

                7.14
                Having found this human life, a boat for crossing the great river of suffering, foolish one, this is no time to sleep. This boat is difficult to find again.

                7.15
                Having abandoned the supreme joy of delight in the Dharma, which brings an unbroken stream of happiness, why take pleasure in distractions, laughter, and frivolity—the causes of suffering?

                7.16
                The powers to overcome discouragement, to gather strength, to remain intent, and to bring oneself under control, along with equalizing and exchanging self and others—

                7.17
                I should never give in to discouragement, thinking, “How could I ever awaken?” The Tathāgata, who speaks the truth, declared this truth:

                7.18
                Even flies, mosquitoes, bees, and worms have attained the highest awakening through their perseverance.

                7.19
                Why should I, born as a human being and capable of distinguishing what helps from what harms, fail to attain awakening by following the teachings of the Omniscient One?

                7.20
                I may be afraid of having to give away my hands and feet. But that fear comes from failing to distinguish what is difficult from what is easy.

                7.21
                I may be cut apart, pierced, burned, and torn apart countless times over millions of ages, yet awakening may still not come.

                7.22
                But the suffering I must endure for awakening is limited. It is like the pain of removing a deeply embedded splinter in order to end a much greater pain.

                7.23
                All physicians use painful treatments to restore health. Therefore, to overcome many sufferings, I must endure a little pain.

                7.24
                The supreme physician does not begin by prescribing the most difficult treatment. He heals those who are gravely ill through gentle remedies.

                7.25
                At first, the Guide trains us to give away vegetables and other simple things. Gradually, we learn to give even our own flesh.

                7.26
                When wisdom has developed so that I regard my own flesh as no more precious than vegetables, what difficulty will remain in giving away bones and flesh?

                7.27
                Because I have abandoned harmful actions, I will not be miserable; because I understand, my mind will not be distressed. Mental suffering comes from mistaken ideas, while bodily pain comes from harmful actions.

                7.28
                The body is made happy through merit, and the mind is made happy through wisdom. What can sadden a compassionate person who remains in saṃsāra for the sake of others?

                7.29
                By exhausting past wrongdoing and gathering oceans of merit, through the power of the awakening mind I can progress more quickly than the hearers.

                7.30
                Thus, moving from happiness to happiness, what thoughtful person would become discouraged after mounting the chariot of the awakening mind, which removes all exhaustion and hardship?

                7.31
                For the welfare of beings, I should cultivate the powers of aspiration, steadfastness, joy, and letting go. I should develop aspiration by contemplating suffering and the benefits of wholesome action.

                7.32
                Having uprooted the opposing forces in this way, I should strive to increase joyful effort through the powers of aspiration, confidence, joy, dedication, and self-mastery.

                7.33
                The faults of myself and others are immeasurable and must be overcome. Since even one fault may take an ocean of ages to eliminate,

                7.34
                I see not even a trace of effort in myself to remove these faults. How can my heart not break, knowing that I am destined for immeasurable suffering?

                7.35
                I must also develop countless good qualities for myself and others. Yet I have not practiced even one of these qualities for an ocean of ages.

                7.36
                I have never cultivated even a small part of these qualities. How have I wasted this extraordinary life, obtained with such difficulty?

                7.37
                I have not enjoyed the great celebration of making offerings to the Blessed Ones. I have not served the teachings, nor fulfilled the hopes of those in need.

                7.38
                I have not given protection to those who were afraid, nor brought happiness to those who were suffering. I have only caused my mother pain by remaining in her womb.

                7.39
                Because I lacked enthusiasm for the Dharma in the past, I have fallen into such a state now. Who would ever abandon enthusiasm for the Dharma?

                7.40
                The Sage taught that aspiration is the root of every wholesome quality. Its own root is constant reflection on the ripening results of actions.

                7.41
                Sufferings, unhappiness, fears of many kinds, and the frustration of desires arise for those who act harmfully.

                7.42
                Wherever the wishes of those who act well are directed, there their merit honors them with its fruits.

                7.43
                Wherever those who act harmfully direct their wishes for happiness, their own harmful actions strike them with weapons of suffering.

                7.44
                Through their wholesome actions, the children of the Sugata are born from lotus blossoms before the Buddha, within cool and fragrant lotus hearts. Their bodies are radiant, nourished by the Buddha’s sweet voice, and they emerge as noble beings when the lotus is opened by the Sage’s hand.

                7.45
                Through harmful actions, people are dragged away by the servants of Yama. Their skin is stripped away, they cry out in agony, and their bodies are drenched in molten copper heated by blazing fires. Their flesh is cut into hundreds of pieces by burning swords and spears, and they repeatedly fall upon the scorching iron ground.

                7.46
                Having reflected carefully on this, I should cultivate wholesome aspiration. Then, following the method of the Vajradhvaja Sutra, I should develop confidence and determination.

                7.47
                First, I should examine whether I have the resources and ability to undertake a task. If I do not, I should not begin. It is better not to start than to begin and then turn back.

                7.48
                If I abandon a task after beginning it, the habit of doing so will continue in future lives, and harmful actions and suffering will increase. Other tasks will also be left unfinished, and this one will not be accomplished.

                7.49
                I should develop confidence in three areas: actions, afflictions, and abilities. The confidence regarding action is the resolve: “I alone will do this.”

                7.50
                People in this world are powerless, controlled by afflictions, and unable to accomplish their own welfare. Therefore, I must work for their sake, unlike those who are unable.

                7.51
                Someone else is doing this lowly task—why should I not do it too? If I refuse because of pride, it would be better for my pride to be destroyed.

                7.52
                Even a crow behaves like an eagle when it finds a dead snake. If my mind becomes weak, even a small difficulty will overwhelm me.

                7.53
                When discouragement makes me inactive, difficulties become easy to defeat. But when I rise up and make an effort, even great difficulties can be overcome.

                7.54
                Therefore, with a firm mind, I will turn every difficulty into an opportunity. It would be ridiculous for me, who wishes to conquer the three worlds, to be defeated by adversity.

                7.55
                I must conquer everything; I must never be conquered by anything. I should maintain this confidence, for I am a child of the Lion of the Conquerors.

                7.56
                Those beings who are overcome by pride are truly unfortunate; they are not people of genuine confidence. A person with true confidence does not fall under the power of the enemy, while those ruled by pride are under the power of the enemy called conceit.

                7.57
                Led by pride, people fall into unfortunate states. Even when born human, they lose their joy, live as servants, depend on others for food, and become foolish, unattractive, and weak.

                7.58
                They are despised everywhere, and their pride makes them rigid and miserable. If even these are considered people of pride, tell me, what could be more wretched?

                7.59
                Those who conquer the enemy of pride are the truly confident and victorious heroes. They carry confidence into battle against the enemy called conceit. Having killed that enemy, they bring the fruit of victory to the world.

                7.60
                Even when surrounded by thousands of afflictions, I should remain bold, like a lion among deer, and not be overcome by the forces of mental defilement.

                7.61
                Just as the eye does not lose its composure even in great danger, I should not fall under the power of afflictions, even when I encounter severe hardship.

                7.62
                Whatever task I happen to undertake, I should become devoted to that task. I should be so absorbed in it that I am never satisfied, like someone who longs for the pleasure of play.

                7.63
                People act for the sake of happiness, yet happiness may or may not result. But how can someone whose happiness is the action itself ever be unhappy through inaction?

                7.64
                In saṃsāra, sensual pleasures never satisfy, though they are like honey on a razor’s edge. How could I ever be satisfied with the nectar of merit, whose results are sweet and wholesome?

                7.65
                Therefore, even when I have completed a task, I should immerse myself in the next one, like an elephant scorched by the midday sun plunging into a lake.

                7.66
                But when my strength is declining, I should stop and rest, so that I can return to the work. And when a task is well completed, I should let it go and turn eagerly toward the next.

                7.67
                I should guard against the blows of the afflictions and strike the afflictions firmly, as a trained swordsman does when fighting an enemy with a sword.

                7.68
                Just as someone quickly retrieves a dropped sword in fear, remembering the dangers of hell, I should quickly retrieve the sword of mindfulness if I have dropped it.

                7.69
                Just as poison spreads through the body after entering the bloodstream, so a fault spreads through the mind when it finds an opening.

                7.70
                A practitioner should be as attentive as someone carrying a vessel filled with oil, watched by people holding swords, who knows that a single slip means death.

                7.71
                Just as someone quickly rises when a snake falls into their lap, so I should immediately respond when sleepiness and laziness arise.

                7.72
                At every lapse, I should examine myself with care and reflect: “How can I make sure this does not happen again?”

                7.73
                I should seek out the company and activities that make it possible to practice mindfulness in every situation.

                7.74
                Remembering the teachings on carefulness, I should make myself ready and alert, as I was before undertaking a task.

                7.75
                Just as a tuft of cotton moves wherever the wind carries it, I should move under the power of joyful effort. In this way, my spiritual abilities will flourish.

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 7, “Vīryapāramitā” (“The Perfection of Joyful Effort”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 7.
            """.trimIndent(),
            quiz = null
        ),
        Lesson(
            id = 108,
            title = "Chapter 8 — The Perfection of Meditation",
            summary = "Dhyānapāramitā: cultivating solitude and calm abiding, letting go of attachment and sensual craving, and equalizing and exchanging self and others.",
            content = """
                ŚĀNTIDEVA — BODHICARYĀVATĀRA
                Chapter 8 — The Perfection of Meditation
                (Dhyānapāramitā)

                8.1 Having strengthened joyful effort in this way, settle the mind in meditation. A distracted mind stands within reach of the afflictions’ fangs.
                8.2 When body and mind are secluded, distraction cannot take hold. Therefore, leave worldly entanglements and set aside restless thoughts.
                8.3 We cling to the world through attachment and craving for gain and the like. A wise person should reflect carefully on why these must be relinquished.
                8.4 Understand that calm abiding joined with insight destroys the afflictions. First seek calm abiding, which grows through freedom from attachment to worldly life.
                8.5 How can someone who knows that things are impermanent become attached to what is impermanent, when a loved one may not be seen again for thousands of lives?
                8.6 Without seeing the beloved, the mind grows dissatisfied and cannot settle in meditation. Even after seeing them, it is never satisfied; desire presses on as before.
                8.7 It fails to see things as they are and loses its sense of urgency. Burning with grief, it longs for reunion with the beloved.
                8.8 Life is wasted again and again in such thoughts. The lasting path is abandoned for what cannot last.
                8.9 One who keeps company with the immature is led toward painful states. They reject what is wholesome; what good can come from keeping their company?
                8.10 Ordinary people become friends in a moment and enemies just as quickly. They grow angry even when treated kindly, and are difficult to please.
                8.11 They become angry when offered good advice and turn others away from what is beneficial. If their advice is not followed, they may fall into painful states themselves.
                8.12 They envy those above them, compete with their equals, look down on those below, become conceited when praised, and resent criticism. When has anything good come from the immature?
                8.13 Self-praise, speaking ill of others, and delighting in worldly existence—these and countless other harmful habits arise among the immature.
                8.14 Keeping company with them brings the same troubles. I will live alone, peacefully, with an undisturbed mind.
                8.15 Keep far from the immature; when meeting them, treat them kindly, without becoming attached. Be like a good person who remains even-minded.
                8.16 Like a bee gathering nectar from flowers, I will take only what serves the Dharma and wander freely, unknown and unattached.
                8.17 “I have wealth and honor, and many people admire me”—when death approaches, such thoughts bring fear to a mortal.
                8.18 Wherever a mind deluded by pleasure finds delight, suffering will arise there, multiplied a thousandfold.
                8.19 Therefore the wise do not crave such things, for craving gives birth to fear. Let them patiently wait, remaining steady.
                8.20 Many have gained wealth; many have become famous. No one knows where they have gone, together with their riches and renown.
                8.21 Why should I rejoice when others praise me, when others despise me? Why should I despair when others criticize me, when others praise me?
                8.22 Beings have different inclinations; even the Buddhas have not pleased everyone. Why, then, worry about what the world thinks of someone as unwise as I?
                8.23 People criticize those without wealth and look down on those who have it. How can one delight in the company of those who are naturally so difficult to live with?
                8.24 The Awakened One taught that the immature are no one’s true friends: their affection arises only when they see some benefit for themselves.
                8.25 Affection that depends on personal advantage is affection for oneself alone. When possessions are lost, distress follows because happiness has been lost.
                8.26 Trees do not scorn me, and I need not work to please them. When will I live happily among such companions?
                8.27 When will I dwell in an empty shrine, beneath a tree, or in a cave, free of concern and never looking back?
                8.28 When will I live in vast, naturally open places that belong to no one, moving freely wherever I wish?
                8.29 With only an earthen bowl and robes no one would want to steal, when will I live without fear and without guarding my body?
                8.30 When will I go to a charnel ground and compare my own body with the other skeletons there, seeing that it too is subject to decay?
                8.31 This very body of mine will become so foul that even jackals will not come near because of its smell.
                8.32 Even the bones that once belonged to this single body will separate and scatter. How much more will other loved ones go their own ways?
                8.33 Each being is born alone and dies alone. No one else can take on that person's suffering; why become entangled with loved ones who obstruct the path?
                8.34 Just as a traveler takes temporary lodging along a road, a being traveling through existence takes up a dwelling at birth.
                8.35 Before four people have carried the body away, while the world is still mourning, one should go to the forest.
                8.36 Living without entanglements and without conflict, one is already as good as dead to the world; when death comes, there is no reason for grief.
                8.37 No companions nearby will cause distress by grieving, and no one will distract the mind from recollecting the Buddha.
                8.38 Therefore solitude is delightful, free from trouble and a source of peace. It quiets every distraction and should always be cultivated.
                8.39 Free from all other concerns, I will make the mind single-pointed and strive to settle and discipline it.
                8.40 Sensual desires bring harm in this life and the next: here through imprisonment, violence, and death; hereafter through hell and other painful states.
                8.41 For their sake one has repeatedly begged messengers and attendants, and endured disgrace and wrongdoing.
                8.42 For their sake one has risked life and squandered wealth, embracing what seemed to promise the greatest pleasure.
                8.43 Yet those bodies are only bones, no different from others, and are not under one's control. Why not embrace them without craving and find peace?
                8.44 The face once carefully lifted and lowered, whether seen or hidden behind a veil—
                8.45 Look now: vultures have exposed that same face, as though it could no longer bear your anxious care. Why do you run away?
                8.46 The body once protected even from another person's gaze is now being eaten. Why do you not protect it from this, if you are so possessive?
                8.47 See this mass of flesh being eaten by vultures and other creatures. What was adorned with garlands and sandalwood has become food for others.
                8.48 You tremble even at the sight of a motionless skeleton. Why are you not frightened by a corpse being moved as though by a spirit?
                8.49 Saliva and filth come from the same food. If you dislike the filth, why do you enjoy drinking saliva?
                8.50 Lovers delight in soft pillows filled with down, because they do not smell foul. They are confused by the body's impurity.
                8.51 If you are attracted to what is hidden beneath a covering, why is the uncovered body unpleasant? If the covering serves no purpose, why rub it away?
                8.52 If you are not attracted to impurity, why embrace another body—a framework of bones bound by sinews and smeared with flesh?
                8.53 Your own body contains plenty of impurity; be content with that. Forget the other foul bag, filled with waste.
                8.54 You say you love this person's flesh and want to see and touch it. But flesh is insentient by nature; why desire it?
                8.55 The mind you wish to see and touch cannot be seen or touched. What can be seen does not know; why embrace it pointlessly?
                8.56 It is no surprise that you recognize impurity in another's body. What is astonishing is that you fail to recognize the impurity of your own.
                8.57 Apart from a young lotus opening in the sunlight, what delight can there be for a mind obsessed with impurity in a cage of filth?
                8.58 If you refuse to touch something because it is smeared with dirt, why do you wish to touch the body from which that dirt came?
                8.59 If you are not attracted to impurity, why embrace another body, whose seed was nurtured in an impure place?
                8.60 You would not desire a tiny worm born from filth because it is impure. Why desire a body made of so much impurity and born from impurity?
                8.61 You do not merely overlook the impurity of your own body; you desire other containers of impurity as well.
                8.62 Even the ground is considered impure when food such as rice and delicacies is placed in the mouth and then expelled.
                8.63 If you cannot accept this impurity even when it is obvious, look at other terrible bodies lying in a charnel ground.
                8.64 When skin is stripped away, great fear arises. Knowing this, how can desire arise again for that very body?
                8.65 The fragrance on a body comes from sandalwood, not from the body itself. Why become attached to something because of a scent that belongs to something else?
                8.66 If the body's natural odor is unpleasant, that is hardly admirable. Why do people so foolishly cover it with perfume?
                8.67 What has sandalwood to do with the body? Why become attached to the body because of a fragrance that comes from elsewhere?
                8.68 With long hair and nails, stained and discolored teeth, and a body covered in grime, the naked body is naturally frightening.
                8.69 Why carefully decorate it as though preparing a weapon against oneself? The world is crowded with people intent on deceiving themselves.
                8.70 You feel disgust at seeing a few skeletons in a charnel ground, yet delight in the village charnel ground crowded with moving skeletons.
                8.71 Even this impure body cannot be obtained without payment. Great effort is spent earning what brings little benefit, along with suffering in hell and elsewhere.
                8.72 A child cannot earn wealth; how can childhood be happy? Youth passes in earning; what can an old person do with sensual pleasures?
                8.73 Some foolish pleasure-seekers, exhausted by work throughout the day, return home in the evening and lie down like the dead.
                8.74 Others suffer the hardships of military campaigns and distant journeys. Though they seek pleasure, they may not see their children or spouses for years.
                8.75 Deluded by desire, people sell themselves for the sake of what they never obtain. Their lives are wasted doing another person's work.
                8.76 The wives of those who have sold their freedom and are always sent on errands give birth to children in forests and beneath trees.
                8.77 Some enter battles where life is uncertain merely to survive; others become slaves for the sake of status. Such are the deceptions of desire.
                8.78 Some who crave pleasure are mutilated; others are impaled, burned, or struck down with weapons.
                8.79 Understand the endless harm of wealth: the distress of acquiring it, protecting it, and losing it. Those obsessed with riches are too distracted to escape the suffering of existence.
                8.80 Thus the drawbacks of sensual desire are many, while its taste is slight—like the scraps of grass a beast gets while pulling a cart.
                8.81 For the sake of that tiny taste, rarer even than what an animal can easily obtain, this precious human opportunity is squandered.
                8.82 All this effort is made for the sake of a body that must soon depart and fall into hell or another painful state.
                8.83 Yet awakening requires effort beyond measure, and the hardships of the path are great. Those devoted to sensual pleasure will not attain it.
                8.84 No weapon, poison, fire, fall, or enemy can compare with the sufferings of sensual desire, when one remembers the torments of hell and other painful states.
                8.85 Seeing these dangers, turn away from sensual desire and delight in solitude, in peaceful forest places free from conflict and toil.
                8.86 Fortunate ones walk and reflect for the benefit of others on broad, lovely stone terraces, cool in moonlight and sandalwood breezes, fanned by the quiet, gentle wind of the forest.
                8.87 After staying as long as they wish in an empty dwelling, beneath a tree, or in a cave, they wander freely, released from the burden of possessions and their protection.
                8.88 Living freely, bound to no one, they enjoy a contentment that even Indra would find difficult to attain.
                8.89 By cultivating the qualities of solitude in these and other ways, and calming restless thought, one should develop the awakening mind.
                8.90 First, cultivate equality between oneself and others with care: all beings experience pleasure and pain just as I do, and all deserve protection.
                8.91 Just as a body with many parts—hands and the rest—is cared for as one, so this whole world, diverse yet sharing the nature of pleasure and pain, should be cared for as one.
                8.92 Although the suffering of another body does not hurt me, it is still difficult for me to bear my own suffering because I cling to myself.
                8.93 In the same way, although I do not feel another's suffering, that person's suffering is difficult for them to bear because they cling to themselves.
                8.94 I should relieve the suffering of others because it is suffering, just as I relieve my own. I should help others because they are living beings, just as I care for myself.
                8.95 Since my happiness and that of others are equally dear, what makes my own happiness so special that I strive for it alone?
                8.96 Since my fear and suffering are no more welcome to me than to others, what makes me protect myself and not them?
                8.97 If I do not protect others because their suffering does not hurt me, why protect myself against future suffering that does not hurt me now?
                8.98 The idea “I will be the one who suffers later” is mistaken: one person dies, another is born.
                8.99 If the suffering belongs only to the one who experiences it, why does the hand protect the foot from pain?
                8.100 Even if this seems unreasonable, it arises through the force of self-identification. Whatever is unreasonable should be rejected, whether it concerns oneself or another, according to one's ability.
                8.101 A continuum and a collection are as unreal as a row or an army. The one who suffers does not exist as a separate, permanent entity; to whom, then, does the suffering belong?
                8.102 All suffering, without distinction, has no owner. It should be relieved simply because it is suffering. What special rule could justify treating it otherwise?
                8.103 “Why should suffering be prevented?” Because everyone agrees it should. If it should be prevented, then all suffering should be; otherwise, my own suffering is no different from that of other beings.
                8.104 If compassion brings much suffering, why cultivate it? Yet when the suffering of the world is considered, how could the suffering born of compassion be so great?
                8.105 If the suffering of many can be ended by one person's suffering, a compassionate person should willingly accept that suffering for the sake of others.
                8.106 Thus Supuṣpacandra, though aware of the danger to the king, did not avoid his own suffering when it could benefit many who were suffering.
                8.107 Those whose minds have been trained in this way, and who regard others' suffering as dear as their own, plunge into the depths of hell as swans enter a lotus pond.
                8.108 What greater joy could there be than the ocean of happiness that arises when beings are freed? What use is a liberation that feels empty and tasteless by comparison?
                8.109 Therefore, even after acting for others, do not become proud or amazed, and do not hope for a reward. Let there be only the ardent wish to benefit others.
                8.110 Just as I protect myself even from the slightest insult, I should cultivate the same protective and compassionate attitude toward others.
                8.111 Through habit, the thought “I” arises even in relation to drops of another person's semen and blood, though no permanent self exists there.
                8.112 So why should another person's body not be regarded as “I”? It is not difficult to see that one's own body is also other than oneself.
                8.113 Recognizing the faults in oneself and the ocean of good qualities in others, cultivate giving up self-cherishing and taking up concern for others.
                8.114 Just as hands and other limbs are cherished as parts of the body, why are living beings not cherished as parts of the world?
                8.115 Through habit, the sense of “I” has arisen in this selfless body. Why should the same sense of identification not arise toward others through practice?
                8.116 Even while acting for others, do not become proud or amazed; just as one does not expect a reward for feeding oneself, do not seek reward for helping others.
                8.117 Just as you wish to protect yourself from pain and sorrow, train yourself to extend that protective and compassionate mind to the whole world.
                8.118 Thus the Lord took the name Avalokita, to remove people's fear of being seen or criticized in public.
                8.119 Do not turn away from what is difficult: through the power of practice, even what once caused terror can become a source of joy.
                8.120 Whoever wishes to protect themselves and others quickly should practice this profound secret: exchanging self and others.
                8.121 Who would not regard as an enemy the self-cherishing that, through attachment to oneself, creates fear even over the smallest danger?
                8.122 To satisfy hunger, thirst, and fatigue, such a person kills birds, fish, and animals, and stands in the way of others.
                8.123 For gain and honor, such a person might even kill their parents or steal the treasures of the Three Jewels, becoming fuel for the hell of Avīci.
                8.124 What wise person would wish to protect and honor such a self-cherishing mind, rather than see it as an enemy and oppose it?
                8.125 “If I give, what will I have to enjoy?”—that is the spirit of a demon. “If I enjoy, what can I give?”—that is the spirit of a divine king.
                8.126 Acting for oneself at the expense of others leads to suffering in painful states. Bearing hardship oneself for the sake of others brings every kind of good fortune.
                8.127 The wish to elevate oneself brings low status, foolishness, and painful rebirth. Transfer that wish to others, and it brings fortunate states, honor, and wisdom.
                8.128 By ordering others around for one's own benefit, one experiences servitude and the like. By placing oneself in service to others, one experiences leadership and the like.
                8.129 All those who are unhappy in the world are so because they seek their own happiness. All those who are happy are so because they seek the happiness of others.
                8.130 Why say more? Look at the difference between the immature person who seeks their own benefit and the sage who acts for the benefit of others.
                8.131 One who does not exchange their own happiness for the suffering of others cannot attain awakening; how could such a person find happiness even in worldly existence?
                8.132 Even ordinary goals in this life fail when a servant does not do the work and an employer does not pay the servant.
                8.133 Deluded people abandon the mutual joy that creates happiness in this life and beyond, and instead seize terrible suffering by causing one another pain.
                8.134 All the troubles, sufferings, and fears in the world arise from self-cherishing. What use is this great burden of self-concern?
                8.135 Just as one cannot escape a burn without moving away from the fire, one cannot escape suffering without giving up self-cherishing.
                8.136 Therefore, to end my own suffering and calm the suffering of others, I give myself to others and accept others as I would myself.
                8.137 “I am bound to others,” make this conviction firm, O mind. From now on, think of nothing except the welfare of all beings.
                8.138 It is not right to use the eyes and other senses of others for my own benefit, nor to use their hands and other limbs for my own purposes.
                8.139 Therefore, become devoted to living beings. Whatever you notice in this body, take it away from self-interest and use it for the benefit of others.
                8.140 Regard those who are lower as yourself, and regard yourself as another. With an undistracted mind, cultivate envy and pride in this way.
                8.141 “This person is honored, not I. This person receives wealth, not I. I am praised, while this person is criticized. I suffer, while this person is happy.”
                8.142 “I do the work, while this person rests. This person is renowned in the world, while I am lowly and without distinction.”
                8.143 “What can someone without qualities do? Everyone has qualities. There are those whose qualities exceed mine, and those whose qualities are less.”
                8.144 “Because of my faults in conduct, understanding, and other afflictions, I should be treated as someone needing help. I will accept the hardship of being corrected.”
                8.145 “If I cannot be helped, why do you look down on me? What use are my qualities to me, when this person has qualities?”
                8.146 “This person is in the jaws of the beast of a painful rebirth, yet still competes with the wise out of pride in their own qualities.”
                8.147 “By seeing myself as equal to others, I will strive to increase my own gain and honor, even if it means conflict.”
                8.148 “May my good qualities become known everywhere in the world; may no one hear of the good qualities of this other person.”
                8.149 “May my faults remain hidden, and may this person receive no honor. Today I have gained wealth and respect, while this person has not.”
                8.150 “How glad we are to see this wretched person humiliated at last, mocked and criticized by everyone.”
                8.151 “This poor person even competes with me! What learning, appearance, family, or wealth does this person possess?”
                8.152 Hearing my own qualities praised in every direction, I will be thrilled and enjoy a festival of happiness.
                8.153 “If this person gains wealth, we should take it by force. We should give just enough to live, and make them do our work.”
                8.154 “We should take away this person's happiness and make them endure our suffering. We have suffered in existence because of this person countless times.”
                8.155 For countless ages you have sought your own benefit. After all that great effort, you have gained only suffering.
                8.156 Act now without hesitation, following my instruction. Later you will see the benefits; the Sage's words are true.
                8.157 If you had done this work before, you would not be in this condition, deprived of the happiness of awakening.
                8.158 Just as you developed the idea “I” toward drops of another person's semen and blood, cultivate the same attitude toward others.
                8.159 Become as though you were another person. Whatever you see in this body, take it away for the benefit of others.
                8.160 “This person is comfortable, that one is suffering; this one is low, that one is high.” Turn envy toward yourself in this way.
                8.161 Remove yourself from happiness and place yourself in the suffering of others. Examine the other's faults: “What is this person doing? What are they gaining?”
                8.162 Place even another person's faults on this one's head, and reveal even the smallest fault of this supposedly great person.
                8.163 Tarnish this person's reputation by praising others as superior. Make this person work for the welfare of beings like a lowly servant.
                8.164 Do not praise this person for qualities that are merely temporary; they are full of faults. Act so that no one recognizes this person's good qualities.
                8.165 In short, whatever harm you have done to others for your own sake, bring that very suffering upon yourself for the sake of living beings.
                8.166 Do not give this person encouragement that would make them boastful. Keep them modest, cautious, restrained, and respectful, like a new bride.
                8.167 “Do this. Stay this way. You must not do that.” In this way, keep the mind under control and discipline it whenever it strays.
                8.168 If, even when spoken to in this way, you refuse, O mind, I will have to restrain you, for every fault depends on you.
                8.169 Where will you go? I have seen you; I will destroy your arrogance. That earlier time when you ruined me is over.
                8.170 Give up the hope that you still have some personal interest to pursue. I have sold you to others; do not think of the hardship.
                8.171 If I do not gladly give you to living beings, you will certainly hand me over to the guardians of hell.
                8.172 You have made me suffer for so long by giving me away in countless ways. Remembering these injuries, I will destroy you, servant of selfishness.
                8.173 If you care for yourself, do not cherish yourself. If you wish to protect yourself, you should not protect this self-cherishing mind.
                8.174 The more this body is pampered and protected, the more delicate and vulnerable it becomes.
                8.175 Even the whole earth cannot satisfy the desires of this body as it declines. Who could ever fulfill its cravings?
                8.176 For one who desires the impossible, frustration and distress arise. But one who expects nothing is never disappointed and enjoys contentment.
                8.177 Therefore, do not give the body room to increase its demands. What is desirable is not taken simply because it is desired.
                8.178 This body ends in ashes and is motionless, moved only by others. It is an impure and dreadful image; why am I so attached to it?
                8.179 Whether living or dead, what difference is this machine to me? How is it different from a lump of clay? Alas, why does the pride of “I” not disappear?
                8.180 By favoring the body, one gathers needless suffering. What use is affection or hatred toward something as insentient as a piece of wood?
                8.181 Whether I care for it or vultures consume it, the body has neither affection nor hatred. Why should I feel affection for it?
                8.182 If it does not know that it is pleased by praise or hurt by abuse, for whose sake am I wearing myself out?
                8.183 Those who desire this body are supposedly my friends. Yet everyone desires their own body; why should I not regard them as dear?
                8.184 Therefore, without attachment, I have given up this body for the welfare of the world. Even with its many faults, I will maintain it as a tool for carrying out beneficial actions.
                8.185 Enough of worldly conduct. Remember the teachings on vigilance, overcome dullness and sleepiness, and follow the wise.
                8.186 To remove the obstacles, I will cultivate concentration, continually drawing the mind away from mistaken paths and keeping it focused on its proper object.

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 8, “Dhyānapāramitā” (“The Perfection of Meditation”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 8.
            """.trimIndent(),
            quiz = null
        ),
        Lesson(
            id = 109,
            title = "Chapter 9 — The Perfection of Wisdom",
            summary = "Prajñāpāramitā: exploring the two truths, conventional reality and ultimate emptiness, and analytical inquiry into self, body, mind, and phenomena.",
            content = """
                ŚĀNTIDEVA — BODHICARYĀVATĀRA
                Chapter 9 — The Perfection of Wisdom
                (Prajñāpāramitā)

                9.1 The Sage taught all these preparations for the sake of wisdom. Therefore, anyone who wishes to bring suffering to an end should develop wisdom.

                9.2 There are said to be two truths: conventional truth and ultimate truth. Ultimate reality lies beyond the reach of ordinary conceptual thought; ordinary understanding operates within convention.

                9.3 People are spoken of as belonging to two groups: contemplatives and ordinary people. The understanding of ordinary people is corrected by the understanding of contemplatives.

                9.4 Even contemplatives are corrected by those with deeper insight, in progressively subtler ways. Both sides rely on commonly accepted examples for practical purposes, without examining them ultimately.

                9.5 Ordinary people see things and imagine them to be real in themselves. Yet contemplatives and ordinary people agree that things appear like illusions.

                9.6 Even direct perception of forms and other things is not reliable simply because it is commonly accepted, just as the commonly accepted idea that something impure is pure is mistaken.

                9.7 The Teacher taught that things exist for the purpose of guiding ordinary people. To say they are not momentary in the conventional sense would contradict experience.

                9.8 There is no fault in the contemplative's conventional account, which sees things more truly than the ordinary world does. Otherwise, even the teaching that the body is impure would be contradicted.

                9.9 If beings are like illusions, how can merit arise from honoring the Buddhas, who are also like illusions? If beings are illusion-like, why should they be reborn after death?

                9.10 As long as the conditions that produce an illusion remain, the illusion appears. Why should a stream of moments be considered truly existent merely because it continues for a long time?

                9.11 Killing an illusory person does not create wrongdoing, because no mind is present. But when an illusory mind is involved, both harmful and beneficial actions can arise.

                9.12 Mantras and other conditions do not all have the same power to produce illusions. Different illusions arise from different combinations of conditions; no single condition can do everything.

                9.13 If someone is liberated in the ultimate sense but continues to wander in cyclic existence conventionally, then even a Buddha would continue to wander. What, then, would be the point of the path to awakening?

                9.14 As long as the conditions remain, an illusion does not disappear. But when the conditions are cut off, even conventionally it can no longer arise.

                9.15 When the conditions have been cut off, even confusion is absent. If there is no illusion, what is there to perceive as an illusion?

                9.16 If there is no illusion, what is perceived? Perhaps it is only an appearance of the mind. But if something other than mind exists ultimately, that too must be explained.

                9.17 If mind itself is an illusion, what perceives it, and by what is it perceived? The Protector of the World has said that mind cannot see mind.

                9.18 A sword cannot cut itself, and mind cannot turn around and illuminate itself. You might argue that a lamp illuminates itself and other things.

                9.19 But a lamp does not illuminate itself, since it is not covered by darkness. A blue object does not need another blue thing to make it blue.

                20. Some things are seen in dependence on something else, while others are said not to depend on anything. Yet what is not blue cannot be seen as blue merely by being called its cause.

                9.21 What could make blue itself blue? It cannot make itself blue by its own power. And what is not blue cannot make itself blue either.

                9.22 We say, “The lamp shines,” because we know that it illuminates. But who knows that awareness itself illuminates, and on what basis is this said?

                9.23 Whether illumination or non-illumination has ever been observed, talk of awareness illuminating itself is empty—like describing the daughter of a barren woman.

                9.24 If awareness does not know itself, how can it remember? Memory can arise through a connection with something experienced by another moment of awareness, just as a rat may be poisoned by a poisoned object.

                9.25 Awareness can be recognized through another condition that accompanies it. Seeing a jar with a special eye-salve does not mean the jar itself has become that salve.

                9.26 What is seen, heard, and known in ordinary life is not being denied here. What is being rejected is the idea that these things exist as ultimately real, because that assumption is a cause of suffering.

                9.27 If an illusion is said to be neither different from mind nor identical with it, how is it understood? If it is a real thing, how can it be neither one nor the other? If it is neither, it has no reality of its own.

                9.28 Just as an illusion can be seen without being real, mind too can appear without possessing independent reality. If cyclic existence rests on something inherently real, it would be unchanging like space.

                9.29 How could something unreal perform an action by relying on something real? On your account, mind would be a single thing acting without any genuine support.

                9.30 If the object of awareness is absent, then all the Buddhas would be without awareness. What benefit is gained by imagining that everything is only mind?

                9.31 If you know that things are like illusions, how can the afflictions cease? Someone may still feel desire for an illusory woman, and the magician who creates her may feel desire too.

                9.32 The creator of the illusion has not yet abandoned the latent habits that cause attachment to objects. At the moment of seeing the illusion, the habit of emptiness is still weak.

                9.33 By familiarizing yourself with emptiness, the habit of treating things as real grows weaker. By repeatedly contemplating that nothing has inherent existence, even that habit eventually falls away.

                9.34 When the thing imagined to exist cannot be found, how can its absence remain before the mind as something to grasp?

                9.35 When neither existence nor nonexistence remains before the mind, and there is no other reference point, the mind becomes calm, without support.

                9.36 A wish-fulfilling jewel or a tree that grants wishes fulfills desires. In the same way, an image of the Buddha appears in response to the aspirations and needs of those to be guided.

                9.37 A magician may create an apparition of a person and then disappear. Even after the magician has gone, the apparition can still calm the effects of poison and other harms.

                9.38 In the same way, an image of the Buddha is brought forth through the power of the path of awakening. Even after a bodhisattva has entered nirvāṇa, that image can continue to accomplish beneficial work.

                9.39 How can offerings made to one who has no mind produce results? The teachings describe offerings to the Buddha as fruitful whether the Buddha is present or has entered nirvāṇa.

                9.40 The scriptures teach that such offerings bear fruit, whether understood conventionally or ultimately. How can offerings made to a Buddha who truly awakened be considered fruitless?

                9.41 If liberation comes from seeing reality, what is the use of seeing emptiness? The teachings say that awakening cannot be attained without this path.

                9.42 You may object that the Mahāyāna has not been established, so how can its scriptures be authoritative? But your own scriptures are not established from the outset for someone who does not already accept them.

                9.43 Apply to the Mahāyāna the same grounds on which you place confidence in your own tradition. If a teaching is accepted as true by both sides, then even the Vedas could be called true on that basis.

                9.44 If you reject the Mahāyāna because it is disputed, then reject other scriptures too: they are disputed by non-Buddhists, and their own traditions are disputed by one another.

                9.45 The Buddhist teaching depends on the monastic community, yet you say monastic status is unstable. For those whose minds still grasp at things, even nirvāṇa would then be unstable.

                9.46 If liberation follows the abandonment of the afflictions, let it follow immediately when they are abandoned. Yet actions can still occur even in someone who is free from afflictions.

                9.47 You may say that craving is the condition for grasping, and that liberated people have no craving. But could they not have a non-afflicted form of desire, just as they may retain some confusion about conventional matters?

                9.48 Craving depends on feeling, and liberated people still have feelings. Wherever they are, they would therefore still have to remain with a mind that takes something as an object.

                9.49 Without insight into emptiness, the mind becomes bound again. Therefore, just as one cultivates the meditative attainment beyond perception, one should cultivate emptiness.

                9.50 If you accept a statement in a sūtra as the Buddha's word, why not accept the Mahāyāna, which is largely in agreement with your own sūtras?

                9.51 If the whole collection is considered flawed because one passage is not accepted, why not consider everything taught by the Buddha flawed because one sūtra is not accepted?

                9.52 Even a teaching that great disciples such as Mahākāśyapa could not fully understand should not be rejected simply because you do not understand it.

                9.53 The purpose of emptiness is to free beings from attachment and fear, and to make it possible to remain in cyclic existence for the sake of those who suffer in confusion.

                9.54 Thus the objections raised against emptiness do not hold. Emptiness should therefore be cultivated without hesitation.

                9.55 Emptiness is the antidote to the darkness of the afflictive and cognitive obscurations. How could someone who longs quickly for complete knowledge fail to cultivate it?

                9.56 If something truly existed and produced suffering, fear might reasonably arise. But emptiness brings suffering to an end—what is there to fear?

                9.57 If fear arises from something, then what is that thing? If there is no independent “I,” and no independent thing that belongs to an “I,” who is there to be afraid?

                9.58 I am not my teeth, hair, or nails. I am not my bones, blood, mucus, or phlegm, nor pus or lymph.

                9.59 I am not fat or sweat, nor body fat or internal organs. I am not urine or feces, nor the substances that fill the body's channels.

                9.60 I am not flesh or tendons, heat or breath. I am not the body's openings, nor am I any of the six kinds of consciousness.

                9.61 If consciousness were simply the knowing of sound, it would always be aware of sound. But without an object to be known, what could it know that would make it consciousness?

                9.62 If awareness could exist without knowing anything, then a piece of wood would also be awareness. Therefore, it is clear that awareness does not exist without an object of knowledge.

                9.63 If awareness knows only visible form, why does it not also hear sound at the same time? If sound is absent, then that awareness of sound is absent too.

                9.64 If knowing sound is one nature and knowing form another, how can they belong to one thing? A father and son are spoken of as one another's relations, but they are not one thing in reality.

                9.65 Since clarity, activity, and inertia are neither father nor son, they cannot explain the nature of awareness. No independent essence of knowing sound can be found.

                9.66 If the same thing appears in another form, like an actor taking a different role, it is still impermanent. If it has a wholly different nature, then calling it the same thing is strange.

                9.67 If the other form is unreal, let the supposed original form be identified. If that original is simply knowing, then all people would have to be one and the same.

                9.68 If conscious and unconscious things were one because their existence was equally established, then the distinction between them would be false. What basis would remain for saying they are similar?

                9.69 I am not unconscious like a cloth or other inert thing. But if I am conscious by nature, then the idea of an ignorant self disappears.

                9.70 If the self is unchanging, what does consciousness do for it? On this account, the self would be like space: unaware and inactive.

                9.71 Without a self, you may object, there can be no connection between action and its result. If the one who acts has perished, who will experience the result?

                9.72 But if action and result are established in separate continuities, as both of us accept, then what purpose is served by arguing for an inactive self?

                9.73 It is not possible for a cause to be the very same thing as its result. The Buddha taught the continuity of a stream, and on that basis spoke of an agent and one who experiences the result.

                9.74 I am not the mind of the past or the future, for those minds no longer or not yet exist. If I were the present mind, then when it ceased, I would cease as well.

                9.75 Just as a banana tree has no solid core when its trunk is examined, the self too is found to be without substance when investigated.

                9.76 You may ask: if no being truly exists, toward whom can compassion be directed? Compassion is directed toward beings who are accepted for practical purposes, though they are imagined through confusion.

                9.77 If no being exists, for whose sake is the work done? The aim is accepted through confusion, but the effort to relieve suffering should not be abandoned.

                9.78 The sense of “I” is a cause of suffering, and it grows through confusion about the self. If it cannot be stopped, the cultivation of selflessness is the best remedy.

                9.79 The body is not the feet, the calves, or the thighs. It is not the hips, belly, or back, nor the chest or arms.

                9.80 It is not the hands, sides, armpits, or shoulders. It is not the neck or head. So what, exactly, is the body?

                9.81 If the body exists in all its parts, does it exist in each part separately? And if the parts exist within other parts, where does the body itself stand?

                9.82 If the body exists completely in every hand and other part, then there would be as many bodies as there are hands and other limbs.

                9.83 The body is not inside the limbs or outside them. How can it exist in the limbs? Since it is not separate from the limbs, how can it be found at all?

                9.84 Therefore, the body does not exist as an independent thing. Because of a particular arrangement of limbs, the idea of a body arises, just as a person may be imagined in a tree stump.

                9.85 As long as the appropriate conditions are assembled, the body appears, just as a person appears to be present. In the same way, as long as the hand and other parts are assembled, a body is perceived.

                9.86 In the same way, what is the foot? It is a collection of toes. Each toe is a collection of joints, and each joint can be divided into its own parts.

                9.87 Those parts can be divided into atoms, and atoms can be divided according to directions. Since directional divisions have no independent parts, the atom itself cannot be established.

                9.88 When visible forms are like dreams, what thoughtful person would become attached to them? When the body itself cannot be found, who is a woman and who is a man?

                9.89 If suffering truly existed in itself, why would it not torment those who are joyful? If delicious food were truly pleasant in itself, why would it not appeal to someone overwhelmed by grief?

                9.90 You may say that suffering is not felt because a stronger feeling overwhelms it. But how can something be called a feeling if it is not experienced?

                9.91 Perhaps suffering remains in a subtle form while its gross form is suppressed. But if mere satisfaction is another kind of feeling, that too must be subtle suffering.

                9.92 If suffering does not arise when an opposing condition appears, then feeling is a conceptual construction. This is precisely what analysis reveals.

                9.93 Therefore, this analysis is cultivated as the antidote to such fixation. Contemplatives are nourished by the meditative absorption that arises from examining concepts.

                9.94 If the sense faculty and its object are separated by an interval, how can they come into contact? If they are uninterrupted, they would be one; what could connect them?

                9.95 An atom cannot enter another atom, because each is without spatial parts. If there is no entry, there can be no mixture; without mixture, how can contact occur?

                9.96 How could something without parts make contact? If you claim that contact is possible, show an example of it.

                9.97 Consciousness is formless, so it cannot make physical contact. A collection, too, has no independent reality, as was explained earlier.

                9.98 If there is no contact, how can feeling arise? For whom, then, is there this suffering, and from what does it come?

                9.99 When there is no one who feels and no feeling that exists independently, why does craving not fall apart when this is understood?

                9.100 Things are seen and touched, but they are like dreams and illusions. Since feeling arises together with the mind, it is not independently perceived.

                9.101 What arose before or after an experience can only be remembered, not experienced at that moment. The self does not experience itself, nor is it experienced by another self.

                9.102 Ultimately, there is no experiencer apart from feeling, and no feeling apart from an experiencer. In this selfless collection, who is harmed by suffering?

                9.103 The mind is not located in the senses, in visible forms, in the space between them, inside the body, outside it, or anywhere else.

                9.104 That which is found neither in the body nor elsewhere, neither as a mixture nor as something separate, has no independent existence. Beings are naturally beyond fixed selfhood.

                9.105 If awareness arose before its object, what would it depend on? If it arose together with its object, what would it depend on?

                9.106 If awareness arose after its object, where would awareness come from? In this way, the arising of all things cannot be established as inherently real.

                9.107 If conventional truth did not exist, how could there be two truths? If convention itself depended on another convention, how could anyone attain liberation?

                9.108 Another person's mind is known through convention, but it is not established through one's own direct experience. If it were not established in this way, convention itself would collapse.

                9.109 Concept and what is conceived depend on each other. All analysis proceeds by relying on what is commonly accepted.

                9.110 When analysis itself is examined by further analysis, an endless regress follows, because that further analysis would also need examination.

                9.111 When the object of analysis is examined and no basis for analysis remains, analysis has no support. With no support, it does not arise; this is called nirvāṇa.

                9.112 Those who regard both sides of this argument as ultimately real are in a difficult position. If an object exists because of consciousness, what establishes consciousness?

                9.113 If consciousness exists because of its object, what establishes the object? If each depends on the other, both are left without independent existence.

                9.114 A father exists in relation to a child; without a child, how could there be a father? Without a father, there is no child. Neither has independent existence.

                9.115 A sprout arises from a seed, and the seed is known through the sprout. Why not say that the object's existence is established by the consciousness that arises from it?

                9.116 If the seed is known through a consciousness that is different from the sprout, how is the existence of that consciousness known? The same problem remains.

                9.117 Ordinary people observe causes in the world: different kinds of lotus stalks and other things arise from different causes.

                9.118 If causes differ because of earlier causes, then the earlier causes also require explanation. Why does a cause produce its result? Because of prior causes.

                9.119 If a creator is said to be the cause of the world, tell us what this creator is. If the elements are meant, then that is merely a name for them.

                9.120 The elements are many, impermanent, inactive, not divine, subject to change, and impure. Earth and the other elements cannot be an eternal creator.

                9.121 Nor can the self be the creator, because it was already examined earlier. If space is uncreated, how could it be an active cause?

                9.122 If a result arises without causes, how could it exist at a particular time? If it always has causes, it would always be arising.

                9.123 A cause has no power to produce a result by itself, nor do other causes exist apart from it. What was not there before cannot arise by itself, and nothing else can make it arise.

                9.124 If something arose from another thing, then everything would come from everything. And what is not a cause is not distinct from what is a cause.

                9.125 The result is not contained within the cause, nor is it separate from it. It is neither found outside the cause nor produced by it.

                9.126 How, then, can a result be said to arise from a cause? It appears only through the force of confusion, like an illusion or a dream.

                9.127 If that which is examined is seen to have no independent origin, how can it have an end? Therefore, everything is like an illusion or a dream.

                9.128 Just as a reflection appears in a mirror without actually entering it, so things appear without existing independently.

                9.129 Sound is not inside a drum, nor does it come from outside it. In the same way, results do not come from causes as separate things.

                9.130 When a piece of wood is burned, it turns into ash and is no longer seen. In the same way, things cease when their conditions change.

                9.131 If a result does not arise before its cause has ceased, how could it arise after the cause has ceased? And when the cause has ceased, what can produce it?

                9.132 Even if a result appeared at the very moment a cause ceased, how could the result be joined with the cause?

                9.133 If a result were already present in the cause, there would be no arising. Even if a result were similar to its cause, it would still not be the cause itself.

                9.134 What is not established even in a conventional sense cannot be established ultimately. And if nothing is established, there can be neither arising nor ceasing.

                9.135 In the same way, in cyclic existence there is neither arising nor ceasing. Everything is like a dream, an echo, or a city of clouds.

                9.136 Though a result does not exist as a separate thing, it is not completely absent either. When all conditions are gathered, a result appears.

                9.137 That which does not arise from itself cannot arise from something else. How can something exist if it arises from neither?

                9.138 That which cannot be established as existing cannot cease. Therefore, beings are naturally peaceful and liberated.

                9.139 Even in a dream, when a person is seen, it is uncertain whether they are there. When there is no arising, there is no ceasing either.

                9.140 Space and other things have no real nature of their own; they are merely names. How can something with no real nature be real or unreal?

                9.141 What is neither identical with nor different from reality cannot be found. When it cannot be found, how can it be established?

                9.142 When analysis finds nothing to grasp, there is no basis for further conceptuality. Without fuel, the fire of conceptuality goes out, and peace remains.

                9.143 A wish-fulfilling jewel or a tree that grants wishes fulfills every hope. In the same way, the Buddha appears in accordance with the needs of beings.

                9.144 Like a garuḍa pillar erected against poison, which calms harm even after the maker has gone, the Buddha's presence continues to benefit beings.

                9.145 At first, one should meditate on the image of the Buddha. Then examine the body, and next examine emptiness in stages.

                9.146 When one sees that even emptiness itself is empty of independent existence, there is nothing left to cling to. That is the supreme peace.

                9.147 When nothing is affirmed or denied, and no object remains before the mind, there is no further conceptual elaboration; this is nirvāṇa, free from sorrow.

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 9, “Prajñāpāramitā” (“The Perfection of Wisdom”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 9.
            """.trimIndent(),
            quiz = null
        ),
        Lesson(
            id = 110,
            title = "Chapter 10 — Dedication",
            summary = "Pariṇāmanā: dedicating all merit from composing and practicing the Bodhicaryāvatāra to the relief of suffering and the awakening of all beings.",
            content = """
                ŚĀNTIDEVA — BODHICARYĀVATĀRA
                Chapter 10 — Dedication
                (Pariṇāmanā)

                10.1 Through whatever merit I have gathered by composing the Bodhicaryāvatāra for the sake of beings to enter the bodhisattva training, may all beings follow this same path.

                10.2 Through my merit, may all beings in every realm, throughout the ten directions, experience the joy of happiness wherever they suffer in body or mind.

                10.3 As long as cyclic existence endures, may none of them ever lose their happiness. May the bodhisattvas rejoice in the constant happiness of the world.

                10.4 However many hells exist in all the worlds, may the beings in them experience the supreme bliss of Sukhāvatī.

                10.5 May those freezing in the cold hells find warmth through the boundless compassion of the bodhisattva ocean, and may the great clouds of the bodhisattvas pour down cool rain upon those in the burning hells.

                10.6 May the forest of sword-leaves become for them a pleasant grove of joy, and may the trees of the śālmalī forest become wish-granting trees.

                10.7 May the regions of the hells become delightful with lakes fragrant with lotuses and marked by the calls of swans, ducks, and geese.

                10.8 May the burning heaps of coals become jewels, the boiling rivers of filth become cool waters, the scorching iron ground become crystal, and the crushing mountains become shrines of the Buddhas.

                10.9 May glowing stones, weapons, and poisons become garlands of flowers from now on, and may those who are hostile become gentle and friendly.

                10.10 In the hells, may cats, jackals, and vultures become places of refuge, and may the terrifying trees become groves of joy.

                10.11 Through the radiance of the bodhisattvas, may the scorching heat of the hells cool down and bring delight, as though the moon had risen.

                10.12 For those who suffer in the river of Vaitaraṇī, in ashes, on sharp stakes, or beneath falling weapons, may all that become a place of happiness and peace.

                10.13 May those who have been imprisoned no longer be afraid, may they be freed and gain power, and may they find great joy.

                10.14 May those who are helpless, deprived, and afflicted by illness and other hardships be freed from their distress and attain happiness.

                10.15 May the helpless find protection, may travelers find guides, and may those who wish to cross over find a boat, a raft, or a bridge.

                10.16 May those who are lost find their way, may those who are weary find rest, and may all who are in need find what they lack.

                10.17 May those who are tormented by thirst and hunger be refreshed with cool water and delicious food and drink.

                10.18 May the poor and destitute find abundant wealth, and may those who are thin and sorrowful become joyful and noble in appearance.

                10.19 May those who are exhausted by cold and illness be restored to warmth and health, and may those who are weak and frail regain their strength.

                10.20 May all beings be freed from every kind of illness, and may no one remain sick anywhere in the world.

                10.21 May those who are afraid become free from fear, and may those who are bound be released from their bonds.

                10.22 May the weak become strong, and may all beings become helpful to one another.

                10.23 May every direction become peaceful and fortunate for all beings, and may all who suffer find happiness.

                10.24 May those who have gone forth into homelessness find what they need, and may they never be harmed by harmful intentions or afflictions.

                10.25 May those who are low-born become noble, may those who are unattractive become handsome, and may those who are humble become great in spirit.

                10.26 May all women in the world attain the male state, may the humble gain greatness, and may pride be completely overcome.

                10.27 Through my merit, may no living being anywhere commit any wrongdoing, be ill, be despised, be mistreated, or suffer harm.

                10.28 May no living being remain without good qualities, without wealth, without happiness, or without a protector in cyclic existence.

                10.29 May there be many who possess good qualities, wisdom, wealth, and happiness, and may they always be steady and noble in character.

                10.30 May all beings in the ten directions find peace and joy, and may they always have the companionship of spiritual friends.

                10.31 May the Dharma spread everywhere, may the monastic sangha be honored and supported, and may all beings quickly attain awakening.

                10.32 May all beings always hear the Dharma, may they practice it with diligence, and may they always experience the happiness of the Dharma.

                10.33 May the sangha always be in harmony, may its undertakings always succeed, and may the wishes of the sangha be fulfilled.

                10.34 May those who practice solitude and meditation, with minds free from distraction, overcome all afflictions and obstacles.

                10.35 May those who wish to study read widely, may those who meditate succeed, and may those who teach be able to share the Dharma.

                10.36 May all beings give up harmful actions, always practice what is wholesome, and never turn away from the awakening mind.

                10.37 May beings be guarded by the bodhisattvas, may they hold to pure conduct, and may they be free from all afflictions and be filled with compassion.

                10.38 May the whole world be filled with beings who are free from illness and attachment, who are wise, generous, and gentle, and who live in peace.

                10.39 May all beings gain the qualities of a Buddha, may they adorn the world with their virtues, and may they become free from all suffering.

                10.40 May all beings hear the sounds of birds, trees, rays of light, and the sky itself proclaiming the Dharma without cease.

                10.41 May beings always meet the Buddhas and their spiritual children, and may they honor the teacher of the world with clouds of offerings for as long as space endures.

                10.42 May the Buddhas rain down the Dharma in abundance, may the Dharma flourish everywhere, and may kings and rulers uphold the Dharma with justice.

                10.43 May medicines always be effective, may prayers and mantras always bear fruit, and may spirits and other beings always protect with compassion.

                10.44 May no being ever be deaf, blind, or deformed; may all who suffer be freed from their pain.

                10.45 May those who are burdened by sorrow find relief, may those who are frightened find peace, and may those who are exhausted find comfort.

                10.46 May travelers reach their destinations safely, and may those who travel by boat or ship reach their goals without harm.

                10.47 May those who seek companions find good friends, and may they stay close to one another without fear or danger.

                10.48 May children, elders, and those in need be protected, and may all beings find happiness in this life and beyond.

                10.49 May no one be deprived of anything, may no one suffer distress, and may no one feel fear or regret.

                10.50 May all beings have abundant wealth and good fortune, and may they always attain whatever is wholesome and beneficial.

                10.51 May those who are bound by attachment, anger, or delusion be freed from their afflictions and find peace.

                10.52 May those who are threatened by enemies, thieves, or wild beasts be protected and freed from fear.

                10.53 Through whatever merit I have gained, may all beings attain awakening and become guides who free the world from suffering.

                10.54 By the power of the Buddhas' truth and of my own pure intention, may all these dedications be fulfilled for the welfare of every being.

                10.55 As for me, whatever merit I have gathered by composing the Bodhicaryāvatāra, may it serve to bring about the awakening of all beings.

                10.56 And for those through whose kindness I have written this work, may all of them share in the fruit of awakening together with me.

                Source and Translation Note

                Original work: Śāntideva, Bodhicaryāvatāra, Chapter 10, “Pariṇāmanā” (“Dedication”).

                Sanskrit edition: P. L. Vaidya, ed., Bodhicaryāvatāra of Śāntideva with the Commentary Pañjikā of Prajñākaramati, Buddhist Sanskrit Texts 12 (Darbhanga: Mithila Institute of Post-Graduate Studies and Research in Sanskrit Learning, 1960).

                Digital Sanskrit text consulted: GRETIL, Śāntideva: Bodhicaryāvatāra, based on Vaidya’s 1960 edition.

                End of Chapter 10.
            """.trimIndent(),
            quiz = null
        )
    )
}
