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
                The Middle Way is the core framework of Buddhist teachings, first introduced by the Buddha in his inaugural discourse delivered to five monks at the Deer Park.

                The Buddha identified two physical and emotional extremes to avoid:

                1. Over-Indulgence: Pursuing sensual pleasures which are low, coarse, unworthy, and unprofitable.

                2. Self-Denial: Avoiding worldly pleasures and fasting which is painful, unworthy, and unprofitable.

                Avoiding both extremes, the Buddha realized the Middle Path, which leads to calm, insight, enlightenment, and ultimate peace.

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

                - Right Action: Refraining from harming living beings, taking what is not given, and misconduct.

                - Right Speech: Abstaining from false, harsh, divisive, or idle speech.

                - Right Livelihood: Earning a living in a way that does not cause harm to others.

                - Right Effort: Cultivating wholesome states of mind and releasing unwholesome ones.

                - Right Mindfulness: Maintaining clear awareness of body, feelings, and mind.

                - Right Concentration: Developing deep mental focus, stability, and stillness.

                - Right Understanding: Developing a clear view of reality and cause-and-effect.

                - Right Thought: Cultivating intentions of renunciation, goodwill, and harmlessness.
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

                1. Abstaining from Taking Life
                Practicing non-harm and reverence for all living beings. This training cultivates kindness, patience, and compassion, moving away from cruelty, anger, and violence toward both people and other creatures.

                2. Abstaining from Taking What Is Not Given
                Practicing generosity, honesty, and respect for others' property and well-being. It means refraining from theft, fraud, manipulation, or taking advantage of others' vulnerabilities.

                3. Abstaining from Sexual Misconduct
                Practicing care, integrity, and respect in relationships and intimacy. It means avoiding harm, exploitation, deception, or breaking trust in ways that cause emotional or relational suffering.

                4. Abstaining from False Speech
                Practicing truthfulness, reliability, and wholesome communication. Beyond avoiding outright lies, this training encourages speech that is honest, helpful, timely, and free from harshness, gossip, or division.

                5. Abstaining from Intoxicants That Cloud the Mind
                Practicing mindfulness and clear awareness by avoiding alcohol and intoxicants that dull perception or weaken self-restraint. Because heedlessness makes it much easier to break the other four precepts, this fifth precept serves as a safeguard for overall mindfulness.

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
                            "Release unrealistic expectations, stop striving for permanent control, and find deeper peace",
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

                - Permanence: Aryadeva confronts our deep denial of death, reminding us that life is fleeting like cattle being led to slaughter, leaving no room for complacency.

                - Pleasurable & Clean: He challenges our physical attachments by revealing the inherent limitations, vulnerabilities, and impurities of the body.

                - Ego/Self: He dismantles pride and self-cherishing, urging practitioners to look past superficial status and recognize the emptiness of ego.

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
                        id = 3,
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

    private fun libraryLessons(): List<Lesson> = emptyList()

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

                English translation: Original contemporary English rendering prepared for the BodhiBytes project. It is not copied from a published English translation.

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

                English translation: Original contemporary English rendering prepared for the BodhiBytes project. It is not copied from a published English translation.

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

                English translation: Original contemporary English rendering prepared for the BodhiBytes project. It is not copied from a published English translation.

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

                English translation: Original contemporary English rendering prepared for the BodhiBytes project. It is not copied from a published English translation.

                End of Chapter 4.
            """.trimIndent(),
            quiz = null
        )
    )
}
