package dev.alex.bodhibytes.data

import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Question
import dev.alex.bodhibytes.model.Quiz

object LessonRepository {

    fun getCurriculum(): List<Lesson> {
        return foundationalPathLessons() +
            insightAndSufferingLessons() +
            interdependenceAndEmptinessLessons() +
            compassionAndBodhicittaLessons()
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
                id = quizIdFor(3),
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
        ),
        Lesson(
            id = 4,
            title = "Koan Study: A Buddha",
            summary = "Exploring Koan 13 from Zen Flesh, Zen Bones to understand how true practice transcends rigid rule-following and recognizes the inherent Buddha-nature in every moment.",
            content = """
                The Koan Text:

                In Tokyo in the Meiji era there lived two prominent teachers of opposite characteristics. One, Unsho, an instructor in Shingon, kept Buddha's precepts scrupulously. He never drank intoxicants, nor did he eat after eleven o'clock in the morning. The other teacher, Tanzan, a professor of philosophy at the Imperial University, never observed the precepts. When he felt like eating, he ate, and when he felt like sleeping in the daytime, he slept.

                One day Unsho visited Tanzan, who was drinking wine at the time, not even a drop of which is supposed to touch the tongue of a Buddhist.

                "Hello, brother," Tanzan greeted him. "Won't you have a drink?"

                "I never drink!" exclaimed Unsho solemnly.

                "One who does not drink is not even human," said Tanzan.

                "Do you mean to call me inhuman just because I do not indulge in intoxicating liquids!" exclaimed Unsho in anger. "Then if I am not human, what am I?"

                "A Buddha," answered Tanzan.

                Breaking Rigid Dualism: This encounter targets the trap of spiritual pride and external rigidity. Unsho relied heavily on outward rules to define his purity, while looking down on others. By calling him a "Buddha" when pushed, Tanzan touched past superficial conceptual labels straight to ultimate reality.

                The Essence of Practice: Precepts and rules are helpful training wheels, but treating them as a badge of superior identity creates a subtle ego. True realization does not cling to being "good" or "bad," "strict" or "lax"; it rests in the open, unconditioned nature of the mind.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(4),
                title = "Quiz: Koan Study: A Buddha",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What was Tanzan's response when an angry Unsho asked what he was if he wasn't human?",
                        options = listOf(
                            "An ordinary person",
                            "A strict ascetic",
                            "A Buddha",
                            "A sleeping scholar"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Tanzan answered, \"A Buddha.\""
                    ),
                    Question(
                        id = 2,
                        text = "How did Unsho approach his daily spiritual practice?",
                        options = listOf(
                            "He scrupulously kept the precepts, avoiding alcohol and late meals",
                            "He completely ignored all traditions and lived as a wandering hermit",
                            "He focused solely on teaching philosophy at the Imperial University",
                            "He spent his entire day composing poetry and playing the harp"
                        ),
                        correctOptionIndex = 0,
                        explanation = "Unsho kept Buddha's precepts scrupulously, avoiding intoxicants and not eating after eleven o'clock in the morning."
                    )
                )
            )
        ),
        Lesson(
            id = 5,
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
                id = quizIdFor(5),
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
            id = 6,
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
                id = quizIdFor(6),
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
            id = 7,
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
                id = quizIdFor(7),
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
            id = 8,
            title = "Koan Study: No Attachment to Dust",
            summary = "Exploring Koan 77 from Zen Flesh, Zen Bones to learn the practical guidelines of a true Zen student for living in the world without being trapped by its illusions.",
            content = """
                The Koan Text:

                Zengetsu, a Chinese master of the T'ang dynasty, wrote the following advice for his pupils:

                Living in the world yet not forming attachments to the dust of the world is the way of a true Zen student.

                When witnessing the good action of another encourage yourself to follow his example. Hearing of the mistaken action of another, advise yourself not to emulate it.

                Even though alone in a dark room, be as if you were facing a noble guest. Express your feelings, but become no more expressive than your true nature.

                Poverty is your treasure. Never exchange it for an easy life.

                A person may appear a fool and yet not be one. He may only be guarding his wisdom carefully.

                Virtues are the fruit of self-discipline and do not drop from heaven of themselves as does rain or snow.

                Modesty is the foundation of all virtues. Let your neighbors discover you before you make yourself known to them.

                A noble heart never forces itself forward. Its words are as rare gems, seldom displayed and of great value.

                To a sincere student, every day is a fortunate day. Time passes but he never lags behind. Neither glory nor shame can move him.

                Censure yourself, never another. Do not discuss right and wrong.

                Some things, though right, were considered wrong for generations. Since the value of righteousness may be recognized after centuries, there is no need to crave immediate appreciation.

                Live with cause and leave results to the great law of the universe. Pass each day in peaceful contemplation.

                Transcending the World While Living Within It: This text emphasizes that true Zen practice is not about escaping society, but about maintaining inner clarity and non-attachment amidst daily life. By guarding our character when alone just as we would in public, we build a steady foundation of genuine virtue.

                The Art of Non-Contention: Zengetsu counsels us to stop arguing over right and wrong, to focus entirely on self-reflection rather than criticizing others, and to let go of the desperate craving for immediate approval. True practice lives quietly in the present moment, indifferent to both shame and glory.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(8),
                title = "Quiz: Koan Study: No Attachment to Dust",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What does Zengetsu recommend regarding blame and discussing right and wrong?",
                        options = listOf(
                            "Strictly judge your neighbors to keep the community honest",
                            "Argue publicly whenever righteousness is questioned",
                            "Censure yourself, never another, and do not discuss right and wrong",
                            "Seek immediate appreciation for your good deeds"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Zengetsu advises to censure yourself, never another, and not discuss right and wrong."
                    ),
                    Question(
                        id = 2,
                        text = "According to Zengetsu's advice, how should a practitioner respond to the actions of others?",
                        options = listOf(
                            "Publicly correct everyone's mistakes immediately",
                            "Follow the example of good actions and advise yourself not to emulate mistaken ones",
                            "Ignore everyone and isolate completely from society",
                            "Judge others based on traditional cultural norms"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Zengetsu wrote to encourage yourself to follow another's good action and advise yourself not to emulate a mistaken action."
                    )
                )
            )
        )
    )

    private fun insightAndSufferingLessons(): List<Lesson> = listOf(
        Lesson(
            id = 15,
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
                id = quizIdFor(15),
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
            id = 16,
            title = "Impermanence",
            summary = "Understanding that all conditioned phenomena are transient, shifting, and in constant flux.",
            content = """
                Building on our understanding of experience, we examine impermanence as the underlying characteristic of all conditioned experience.

                Because all conditioned phenomena arise due to causes, they are transient, shifting, and constantly in flux. Grasping onto temporary things as if they are permanent is what deepens distress.

                Understanding impermanence liberates the mind. When we realize that both pleasant and painful experiences are fleeting, we stop clinging, cultivate deep equanimity, and uncover lasting inner peace.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(16),
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
            id = 17,
            title = "Unsatisfactoriness",
            summary = "Examining how grasping onto impermanence and trying to control changing conditions leads to friction and distress.",
            content = """
                Building directly upon the reality of impermanence, unsatisfactoriness is the inherent limitation and friction found in all conditioned things.

                Because every physical and mental experience is constantly shifting, nothing within the conditioned world can provide permanent, unshakeable satisfaction. When we expect temporary states to remain stable or treat them as sources of ultimate security, we create inner tension and distress.

                Recognizing unsatisfactoriness is not meant to be pessimistic, but rather profoundly liberating. It helps us release unrealistic expectations, stop striving for permanent control over changing conditions, and find a deeper peace independent of external circumstances.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(17),
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
            id = 18,
            title = "Non-Self",
            summary = "Discovering that at the core of human experience there is no static, permanent, or isolated ego.",
            content = """
                As the culmination of insight into impermanence, emptiness, and unsatisfactoriness, the teaching of non-self reveals that there is no static, permanent, or isolated ego at the center of human existence.

                We commonly view our thoughts, emotions, and physical bodies as an unchanging core identity. However, when we observe closely, we see that these elements are merely a fluid, interdependent process changing from moment to moment.

                Realizing non-self dissolves the heavy burden of guarding a fixed ego. Without a rigid self to defend, the mind opens up to boundless empathy, humility, and effortless freedom.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(18),
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
            id = 19,
            title = "Koan Study: Is That So?",
            summary = "Exploring Koan 3 from Zen Flesh, Zen Bones to learn how to maintain equanimity, non-attachment, and emotional freedom in the face of false accusations and praise alike.",
            content = """
                The Koan Text:

                The Zen master Hakuin was praised by his neighbors as one living a pure life.

                A beautiful Japanese girl whose parents owned a food store lived near him. Suddenly, without any warning, her parents discovered she was with child.

                This made her parents very angry. She would not confess who the man was, but after much harassment at last named Hakuin.

                In great anger the parents went to the master. "Is that so?" was all he would say.

                After the child was born it was brought to Hakuin. By this time he had lost his reputation, which did not trouble him, but he took very good care of the child. He obtained milk from his neighbors and everything else the little one needed.

                A year later the girl-mother could stand it no longer. She told her parents the truth - that the real father of the child was a young man who worked in the fishmarket.

                The mother and father of the girl at once went to Hakuin to ask his forgiveness, to apologize at length, and to get the child back again.

                Hakuin was willing. In yielding the child, all he said was: "Is that so?"

                Equanimity in Praise and Blame: Hakuin demonstrates complete freedom from the opinions of the world. Whether praised as a saint or condemned as a sinner, his inner state remains undisturbed, anchored in unshakeable peace.

                The Nature of Non-Attachment: True practice does not scramble to defend the ego's reputation or react with anger to injustice. By meeting both slander and apology with the exact same response — "Is That So?" — Hakuin reveals a mind that accepts unfolding reality without grasping or resistance.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(19),
                title = "Quiz: Koan Study: Is That So?",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "How did Hakuin respond when the angry parents accused him of fathering their daughter's child?",
                        options = listOf(
                            "He angrily denied the accusation and threatened legal action",
                            "\"Is that so?\" was all he would say",
                            "He immediately confessed and begged for mercy",
                            "He refused to speak to them and locked his door"
                        ),
                        correctOptionIndex = 1,
                        explanation = "When the angry parents came to him, \"Is that so?\" was all he would say."
                    ),
                    Question(
                        id = 2,
                        text = "What did Hakuin say when the parents returned to apologize and get the child back?",
                        options = listOf(
                            "He scolded them severely for their previous harsh judgment",
                            "He refused to give the child back until they paid for the milk",
                            "In yielding the child, all he said was: \"Is that so?\"",
                            "He explained a long philosophical discourse on truth"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Hakuin was willing and, in yielding the child, all he said was: \"Is that so?\""
                    )
                )
            )
        ),
        Lesson(
            id = 20,
            title = "Sutta Nipata Book 4: How Opinion & Thought Contaminate the Mind",
            summary = "Exploring 'How Opinion & Thought Contaminate the Mind' from Book IV of the Sutta-Nipata to understand how letting go of rigid beliefs, arguments, and pride leads to inner stillness.",
            content = """
                HOW OPINION & THOUGHT CONTAMINATE THE MIND

                Some people debate maliciously. Others honestly. But the wise are silent, stand back from arguments, keep the mind open.

                How can you ever free the mind of its opinions if you let desire lead you and do exactly as you like?

                You're bound by your own habits, can speak only what you know.

                If you credit anything based on rational thought or fantasy you're not clear. They're part of the conditioned world. The benefit they give is shaky, built on sand.

                To overcome habitual points of view is hard. You investigate them all, abandon some and choose a special one.

                You argue to defend a point of view but if you're free of set beliefs no need! You've nothing to deny or to assert, quite purified right now of worldly views.

                The wise have no such view about what is or what is not. They know both thought and pride are meaningless. Nothing defines them.

                The wise see your failings if you blow your own trumpet, boast about your virtue and awareness, but they acknowledge your virtue if you're calm, don't brag, are selfless and unworldly.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(20),
                title = "Quiz: How Opinion & Thought Contaminate the Mind",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "How does the text describe the benefit of crediting anything based on rational thought or fantasy?",
                        options = listOf(
                            "Permanent and completely reliable",
                            "Shaky, built on sand, and part of the conditioned world",
                            "The sole path to purifying worldly views",
                            "Essential for defending one's beliefs"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Rational thought and fantasy are part of the conditioned world, and \"the benefit they give is shaky, built on sand.\""
                    ),
                    Question(
                        id = 2,
                        text = "How do the wise act when others debate maliciously or honestly?",
                        options = listOf(
                            "They argue passionately to prove their own view is superior",
                            "They declare a special point of view to defeat both sides",
                            "They are silent, stand back from arguments, and keep the mind open",
                            "They boast about their own awareness and virtue"
                        ),
                        correctOptionIndex = 2,
                        explanation = "The text says: \"But the wise are silent, stand back from arguments, keep the mind open.\""
                    ),
                    Question(
                        id = 3,
                        text = "When do the wise acknowledge your virtue according to the final stanza?",
                        options = listOf(
                            "When you blow your own trumpet and boast about your awareness",
                            "When you're calm, don't brag, and are selfless and unworldly",
                            "When you win debates and defend set beliefs",
                            "When you assert views about what is or what is not"
                        ),
                        correctOptionIndex = 1,
                        explanation = "The wise acknowledge your virtue \"if you're calm, don't brag, are selfless and unworldly.\""
                    )
                )
            )
        ),
        Lesson(
            id = 21,
            title = "Koan Study: A Cup of Tea",
            summary = "Exploring Koan 1 from Zen Flesh, Zen Bones to learn how an overactive, opinionated mind prevents the reception of true understanding.",
            content = """
                The Koan Text:

                1. A Cup of Tea

                Nan-in, a Japanese master during the Meiji era (1868-1912), received a university professor who came to inquire about Zen.

                Nan-in served tea. He poured his visitor's cup full, and then kept on pouring.

                The professor watched the overflow until he no longer could restrain himself. "It is overfull. No more will go in!"

                "Like this cup," Nan-in said, "you are full of your own opinions and speculations. How can I show you Zen unless you first empty your cup?"
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(21),
                title = "Quiz: Koan Study: A Cup of Tea",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What did Nan-in say the professor was full of?",
                        options = listOf(
                            "Only hot tea",
                            "Questions about Japanese history",
                            "His own opinions and speculations",
                            "Silence and patience"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Nan-in said: \"Like this cup, you are full of your own opinions and speculations. How can I show you Zen unless you first empty your cup?\""
                    ),
                    Question(
                        id = 2,
                        text = "What did Nan-in do when serving tea to the visiting professor?",
                        options = listOf(
                            "He stopped pouring right before the cup was full",
                            "He poured his visitor's cup full, and then kept on pouring",
                            "He spilled the tea intentionally on the floor",
                            "He drank the tea himself without offering any to the professor"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Nan-in poured his visitor's cup full, and then kept on pouring until it overflowed."
                    )
                )
            )
        ),
        Lesson(
            id = 22,
            title = "Impermanence and Death",
            summary = "Contemplating the transience of life and physical existence to overcome worldly attachment and prioritize meaningful practice.",
            content = """
                Impermanence and death remind us that every conditioned physical and mental state is temporary. Acknowledging that life is fleeting helps cut through superficial distractions and idle pursuits.

                When we confront the reality of death honestly, we stop taking our time for granted. It inspires a strong sense of urgency to focus our lives on meaningful inner development, kindness, and wisdom.

                Instead of causing despair, meditating on impermanence brings a liberating clarity, helping us let go of petty grievances and invest our energy into what truly matters.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(22),
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
        ),
        Lesson(
            id = 23,
            title = "Koan Study: Finding a Diamond on a Muddy Road",
            summary = "Exploring Koan 2 from Zen Flesh, Zen Bones to understand how true spiritual practice meets suffering with unconditional compassion and recognizes the impermanence of worldly attachments.",
            content = """
                The Koan Text:

                Gudo was the emperor's teacher of his time. Nevertheless, he used to travel alone as a wandering mendicant. Once when he was on his way to Edo, the cultural and political center of the shogunate, he approached a little village named Takenaka. It was evening and a heavy rain was falling. Gudo was thoroughly wet. His straw sandals were in pieces. At a farmhouse near the village he noticed four or five pairs of sandals in the window and decided to buy some dry ones.

                The woman who offered him the sandals, seeing how wet he was, invited him in to remain for the night at her home. Gudo accepted, thanking her. He entered and recited a sutra before the family shrine. He then was introduced to the woman's mother, and to her children. Observing that the entire family was depressed, Gudo asked what was wrong.

                "My husband is a gambler and a drunkard," the housewife told him. "When he happens to win he drinks and becomes abusive. When he loses he borrows money from others. Sometimes when he becomes thoroughly drunk he does not come home at all. What can I do?"

                "I will help him," said Gudo. "Here is some money. Get me a gallon of fine wine and something good to eat. Then you may retire. I will meditate before the shrine."

                When the man of the house returned about midnight, quite drunk, he bellowed: "Hey, wife, I am home. Have you something for me to eat?"

                "I have something for you," said Gudo. "I happened to get caught in the rain and your wife kindly asked me to remain here for the night. In return I have bought some wine and fish, so you might as well have them."

                The man was delighted. He drank the wine at once and laid himself down on the floor. Gudo sat in meditation beside him.

                In the morning when the husband awoke he had forgotten about the previous night. "Who are you? Where do you come from?" he asked Gudo, who still was meditating.

                "I am Gudo of Kyoto and I am going on to Edo," replied the Zen master.

                The man was utterly ashamed. He apologized profusely to the teacher of his emperor.

                Gudo smiled. "Everything in this life is impermanent," he explained. "Life is very brief. If you keep on gambling and drinking, you will have no time left to accomplish anything else, and you will cause your family to suffer too."

                The perception of the husband awoke as if from a dream. "You are right," he declared. "How can I ever repay you for this wonderful teaching! Let me see you off and carry your things a little way."

                "If you wish," assented Gudo.

                The two started out. After they had gone three miles Gudo told him to return. "Just another five miles," he begged Gudo. They continued on.

                "You may return now," suggested Gudo.

                "After another ten miles," the man replied.

                "Return now," said Gudo, when the ten miles had been passed.

                "I am going to follow you all the rest of my life," declared the man.

                Modern Zen teachers in Japan spring from the lineage of a famous master who was the successor of Gudo. His name was Mu-nan, the man who never turned back.

                Meeting Suffering Without Condemnation: Rather than scolding the drunken husband or judging the troubled household, Gudo enters directly into their reality with radical hospitality and kindness. True wisdom does not isolate itself from human messiness; it meets distress with gentle, transformative presence.

                The Awakening of Impermanence: Gudo's concise reminder that life is brief and impermanent cuts through the illusions of destructive habits. Awakening is not found in an abstract realm, but right on the muddy roads of daily existence, turning despair into a dedicated path of transformation.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(23),
                title = "Quiz: Koan Study: Finding a Diamond on a Muddy Road",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What did Gudo explain to the husband in the morning that awakened his perception?",
                        options = listOf(
                            "That local authorities would punish him",
                            "That everything in this life is impermanent and life is very brief",
                            "That he had broken an imperial law",
                            "That he must study scripture for years before changing"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Gudo explained that everything in this life is impermanent and life is very brief. Continuing to gamble and drink leaves no time to accomplish anything else and causes his family to suffer."
                    ),
                    Question(
                        id = 2,
                        text = "How did Gudo offer to help the family when he learned about the husband's gambling and drinking?",
                        options = listOf(
                            "He scolded the husband harshly for his lack of discipline",
                            "He advised the wife to leave her husband immediately",
                            "He gave money to buy wine and food, welcomed the husband home, and meditated beside him",
                            "He ignored their problems and focused solely on his own shelter"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Gudo gave money to get wine and food, greeted the husband when he came home drunk, and sat in meditation beside him."
                    )
                )
            )
        )
    )

    private fun interdependenceAndEmptinessLessons(): List<Lesson> = listOf(
        Lesson(
            id = 9,
            title = "Interdependence",
            summary = "Exploring how all things are profoundly connected and arise in relation to one another.",
            content = """
                Interdependence reveals that nothing exists in total isolation. Everything in the universe—from human beings to nature—arises in connection with a vast web of conditions.

                When we look deeply, we see that our well-being is tied to the well-being of others and our environment. Every action, word, and thought ripples outward and impacts the whole.

                Realizing interdependence dissolves feelings of absolute separation. It inspires a natural sense of care, shared responsibility, and harmony with the world around us.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(9),
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
            id = 10,
            title = "Thich Nhat Hanh and Interbeing",
            summary = "Exploring the teachings of Zen master Thich Nhat Hanh on 'Interbeing,' discovering how every element of the universe is profoundly interconnected.",
            content = """
                The Teacher of Interbeing: Thich Nhat Hanh was a globally renowned Vietnamese Thien (Zen) master, poet, scholar, and peace activist who popularized the practice of mindfulness in the West. He coined the term "Interbeing" to describe the profound truth that nothing exists separately, but is instead composed entirely of everything else in the universe.

                The Practice of Looking Deeply: Interbeing is not merely an abstract philosophical concept, but a direct invitation to look deeply into the nature of daily phenomena. When we examine a simple object like a sheet of paper, Thich Nhat Hanh taught that we can see clouds, rain, sunshine, the forest, and the logger—all cooperating intimately to bring that paper into existence.

                Living with Mindfulness and Compassion: Recognizing our interbeing transforms how we relate to the world and to one another. Because our actions, thoughts, and words ripple through the web of life, living mindfully becomes an act of deep reverence, urging us to cultivate compassion, reduce suffering, and foster global peace.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(10),
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
            id = 11,
            title = "Dependent Origination",
            summary = "Understanding how events and experiences arise conditioned by preceding causes.",
            content = """
                Dependent origination is the core teaching that nothing happens by random chance or as an isolated event. Every physical and mental state arises conditioned by preceding causes.

                In human experience, this shows how habits, reactions, and emotional patterns form a continuous chain. For example, sensory contact leads to feeling, which can trigger craving if we are not mindful.

                Seeing how this chain operates gives us the power to interrupt unwholesome cycles. By introducing awareness at the moment of contact, we break old reactionary habits and create new, peaceful possibilities.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(11),
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
            id = 12,
            title = "Emptiness",
            summary = "Realizing that all phenomena are empty of a separate, permanent, or independent self-nature.",
            content = """
                Building directly on dependent origination, emptiness is the profound insight that nothing exists independently, permanently, or by its own separate power.

                In this philosophy, emptiness does not mean nothingness or that the world is an illusion. Instead, it means that things are completely empty of an isolated self-nature because they are made entirely of non-self elements and shifting conditions.

                Understanding emptiness frees the mind from rigid labels and absolute boundaries. When we see that people, events, and objects are fluid and interconnected, we stop grasping, dissolve rigid mental barriers, and experience ultimate liberation.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(12),
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
            id = 13,
            title = "Nagarjuna and The Fundamental Wisdom of the Middle Way",
            summary = "Exploring the foundational text of Madhyamaka philosophy by Nagarjuna, which demonstrates the emptiness of all phenomena through logical analysis.",
            content = """
                The Master of the Middle Way: Nagarjuna, living in 2nd-century India, is regarded as one of the most influential philosophers in Buddhist history. He resuscitated and systematically articulated the profound teachings on emptiness (sunyata) found in the Perfection of Wisdom sutras. Through razor-sharp logical analysis, Nagarjuna demonstrated that nothing possesses an intrinsic, independent essence (svabhava).

                The Fundamental Wisdom (Mulamadhyamakakarika): Nagarjuna's masterwork, the Mulamadhyamakakarika, examines every major category of experience—including causation, motion, time, the elements, and the self—to show how grasping at absolute existence collapses under scrutiny. By examining how things arise dependently, he showed that dependent origination and emptiness are completely synonymous.

                Refuting Extremes of Existence and Nihilism: Nagarjuna famously argued that emptiness does not mean nothingness (nihilism), nor does it mean things exist inherently (eternalism). Instead, because things exist dependently, they are empty of inherent existence while remaining functionally operational in conventional reality. This delicate realization avoids all conceptual extremes.

                The Purpose of Deconstruction: The logical deconstructions presented by Nagarjuna are not meant to foster skepticism or academic debate. Rather, they serve a therapeutic purpose: dismantling our deeply ingrained habit of reifying concepts, clinging to views, and building mental barriers, thereby freeing the mind to experience ultimate peace.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(13),
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
            id = 14,
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
                id = quizIdFor(14),
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
            id = 24,
            title = "Karma (Cause and Effect)",
            summary = "Understanding how intentional actions yield corresponding ethical and psychological results in human experience.",
            content = """
                Karma refers to the universal principle of moral cause and effect. Every intentional action of body, speech, and mind leaves a deep impression and bears fruit in corresponding experiences.

                Wholesome intentions rooted in generosity, kindness, and clarity lead naturally to inner peace and positive outcomes. Conversely, unwholesome actions driven by greed, hostility, or delusion generate friction and distress.

                Understanding karma places personal responsibility squarely in our own hands. By observing our intentions and choosing constructive actions, we actively shape our future state of mind and well-being.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(24),
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
            id = 25,
            title = "Koan Study: The Tunnel",
            summary = "Exploring Koan 60 from Zen Flesh, Zen Bones to examine how intentional action, remorse, and dedicated service can transform past karma and reconcile even the deepest enmities.",
            content = """
                The Koan Text:

                Zenkai, the son of a samurai, journeyed to Edo and there became the retainer of a high official. He fell in love with the official's wife and was discovered. In self-defence, he slew the official. Then he ran away with the wife.

                Both of them later became thieves. But the woman was so greedy that Zenkai grew disgusted. Finally, leaving her, he journeyed far away to the province of Buzen, where he became a wandering mendicant.

                To atone for his past, Zenkai resolved to accomplish some good deed in his lifetime. Knowing of a dangerous road over a cliff that had caused death and injury to many persons, he resolved to cut a tunnel through the mountain there.

                Begging food in the daytime, Zenkai worked at night digging his tunnel. When thirty years had gone by, the tunnel was 2,280 feet long, 20 feet high, and 30 feet wide.

                Two years before the work was completed, the son of the official he had slain, who was a skillful swordsman, found Zenkai out and came to kill him in revenge.

                "I will give you my life willingly," said Zenkai. "Only let me finish this work. On the day it is completed, then you may kill me."

                So the son awaited the day. Several months passed and Zenkai kept digging. The son grew tired of doing nothing and began to help with the digging. After he had helped for more than a year, he came to admire Zenkai's strong will and character.

                At last the tunnel was completed and the people could use it and travel safely.

                "Now cut off my head," said Zenkai. "My work is done."

                "How can I cut off my own teacher's head?" asked the younger man with tears in his eyes.

                Karmic Transformation through Service: Zenkai's life demonstrates how intentional action (karma) can be redirected. Burdened by grave past mistakes, he chose neither despair nor evasion, but devoted decades to an arduous act of public welfare that saved countless lives.

                The Dissolution of Hatred: The vengeful son arrives seeking blood, yet encounters a man utterly transformed by unselfish dedication. Through shared labor and steadfast purpose, hatred melts into profound respect and discipleship, showing that true spiritual practice heals even the deepest cycles of retaliation.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(25),
                title = "Quiz: Koan Study: The Tunnel",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "What happened after the vengeful son helped with the digging for more than a year?",
                        options = listOf(
                            "Zenkai defeated him in a sword duel",
                            "He came to admire Zenkai's strong will and character and asked how he could cut off his own teacher's head",
                            "Local villagers intervened and drove the son away",
                            "Zenkai fled the province before the tunnel was finished"
                        ),
                        correctOptionIndex = 1,
                        explanation = "After helping for more than a year, the son came to admire Zenkai's strong will and character and could not kill his teacher."
                    ),
                    Question(
                        id = 2,
                        text = "How did Zenkai work to atone for his past?",
                        options = listOf(
                            "By surrendering immediately to local authorities",
                            "By becoming a wealthy merchant and donating gold to temples",
                            "By begging food by day and digging a 2,280-foot tunnel through a mountain by night",
                            "By wandering the country in absolute silence"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Zenkai begged food in the daytime and worked at night digging a tunnel 2,280 feet long, 20 feet high, and 30 feet wide through the mountain."
                    )
                )
            )
        ),
        Lesson(
            id = 26,
            title = "Developing Bodhicitta",
            summary = "Cultivating universal love, compassion, and the altruistic aspiration to attain full awakening for the benefit of all.",
            content = """
                Bodhicitta is the heartfelt aspiration to achieve complete awakening specifically for the purpose of helping all living beings overcome distress.

                This mindset shifts focus away from isolated self-interest. It expands the heart through boundless loving-kindness and deep empathy, recognizing that our well-being is entirely bound up with the welfare of others.

                Cultivating bodhicitta transforms ordinary daily actions into a powerful path of universal service, infusing every task with warmth, purpose, and profound altruism.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(26),
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
            id = 27,
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
                id = quizIdFor(27),
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
            id = 28,
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
                id = quizIdFor(28),
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
        ),
        Lesson(
            id = 29,
            title = "Koan Study: Reciting Sutras",
            summary = "Exploring Koan 24 from Zen Flesh, Zen Bones to examine how spiritual practice encompasses all living beings rather than harboring petty exclusions.",
            content = """
                The Koan Text:

                A farmer requested a Tendai priest to recite sutras for his wife, who had died. After the recitation was over the farmer asked: "Do you think my wife will gain merit from this?"

                "Not only your wife, but all sentient beings will benefit from the recitation of sutras," answered the priest.

                "If you say all sentient beings will benefit," said the farmer, "my wife may be very weak and others will take advantage of her, getting the benefit she should have. So please recite sutras just for her."

                The priest explained that it was the desire of a Buddhist to offer blessings and wish merit for every living being.

                "That is a fine teaching," concluded the farmer, "but please make one exception. I have a neighbor who is rough and mean to me. Just exclude him from all those sentient beings."

                The Illusion of Separation: The farmer's practical, conditioned worldview exposes how easily our compassion remains shackled by personal grudges and competitive framing. He views spiritual merit as a finite commodity subject to scarcity and exploitation.

                Universal Compassion: The priest's teaching underscores that true spiritual practice cannot be partitioned or doled out exclusively. Awakening and dedication to the welfare of others embrace the entire web of existence without petty exclusions.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(29),
                title = "Quiz: Koan Study: Reciting Sutras",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "Why did the farmer first ask the priest to recite sutras just for his deceased wife?",
                        options = listOf(
                            "Because the priest charged too much money for the ceremony",
                            "Because he worried his wife was weak and others would take advantage of her and get the benefit",
                            "Because the priest used the wrong scripture",
                            "Because his wife would not remember him in the next life"
                        ),
                        correctOptionIndex = 1,
                        explanation = "The farmer worried that his wife might be very weak and others would take advantage of her, getting the benefit she should have."
                    ),
                    Question(
                        id = 2,
                        text = "What one exception did the farmer finally ask the priest to make?",
                        options = listOf(
                            "To recite the sutras twice as loud",
                            "To refund half of his donation",
                            "To exclude his neighbor who is rough and mean to him from all sentient beings",
                            "To include his farm animals in the prayers"
                        ),
                        correctOptionIndex = 2,
                        explanation = "The farmer asked the priest to exclude his neighbor who is rough and mean to him from all those sentient beings."
                    )
                )
            )
        ),
        Lesson(
            id = 30,
            title = "Santideva and Bodhicitta",
            summary = "Explore the life and teachings of the 8th-century master Santideva, focusing on how true bodhicitta transforms the mind and overcomes self-cherishing.",
            content = """
                The Legend of Santideva:

                In 8th-century India at the great monastic university of Nalanda, Santideva appeared lazy to his peers.

                He ate, slept, and strolled the grounds without participating in debates or chanting, earning the mocking nickname "Bhusuku" (one who only eats, sleeps, and walks).

                To test him and force him to leave, the monks asked him to give a public discourse from the high teaching throne. They expected him to humiliate himself.

                Instead, Santideva sat down and delivered the "Bodhicaryavatara" (Engaging in the Bodhisattva's Deeds)—a masterpiece of philosophy and poetry that left the assembly utterly transfixed.

                The Core Message:

                Santideva's teachings focus on the immense power of Bodhicitta (the mind of awakening) and the dismantling of self-cherishing.

                He famously noted that all the suffering in the world comes from seeking happiness for oneself, while all joy comes from wishing happiness for others.

                Destroying the Real Enemy:

                Santideva taught that our true enemy is not an external person or circumstance, but inner mental afflictions like anger, pride, and ego.

                By learning to guard the mind against these internal states, we develop unshakeable patience and compassion.

                Joyful Perseverance:

                True practice requires joyous effort—not a grim, exhausting struggle, but an uplifting enthusiasm born from recognizing the immense value of living a compassionate life.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(30),
                title = "Quiz: Santideva and Bodhicitta",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "According to Santideva's teachings noted in this lesson, where does all suffering in the world come from?",
                        options = listOf(
                            "External weather events",
                            "Seeking happiness for oneself",
                            "A lack of physical exercise",
                            "Unavoidable bad luck"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Santideva noted that all the suffering in the world comes from seeking happiness for oneself, while all joy comes from wishing happiness for others."
                    ),
                    Question(
                        id = 2,
                        text = "What work did Santideva deliver from the teaching throne at Nalanda?",
                        options = listOf(
                            "The Dhammapada",
                            "The Heart Sutra",
                            "The Bodhicaryavatara (Engaging in the Bodhisattva's Deeds)",
                            "The Lamrim Chenmo"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Santideva delivered the \"Bodhicaryavatara\" (Engaging in the Bodhisattva's Deeds)."
                    )
                )
            )
        ),
        Lesson(
            id = 31,
            title = "The Three Principal Aspects of the Path",
            summary = "Je Tsongkhapa's classic 14-verse synthesis of the entire Buddhist path into three core realizations: renunciation, bodhicitta, and the wisdom realizing emptiness.",
            content = """
                Reverence to the venerable, noble teachers!

                To the best of my ability, I shall explain the essential meaning of all the Buddhas' scriptures, the path praised by the noble bodhisattvas, and the gateway for fortunate ones wishing for liberation.

                Those who are not attached to the pleasures of cyclic existence, who strive to make their freedoms and endowments meaningful, and who place their trust in the path that pleases the Buddhas—fortunate ones, listen with a clear mind!

                I. The Determination to Be Free (Renunciation)

                Without pure renunciation, there is no way to pacify the pursuit of pleasurable results in the ocean of existence. Moreover, embodied beings are thoroughly bound by craving for existence; therefore, seek renunciation from the very start.

                Freedoms and endowments are difficult to find, and life has no time to spare—by habituating to this, reverse attachment to this life. Repeatedly contemplate the infallible effects of karma and the sufferings of cyclic existence—by habituating to this, reverse attachment to future lives.

                When, through meditating in this way, admiration does not arise even for an instant for the marvels of cyclic existence, and an attitude seeking liberation arises day and night, at that time, renunciation has been generated.

                II. The Awakening Mind (Bodhicitta)

                However, if that renunciation is not embraced by the generation of a pure awakening mind, it will not become a cause for the perfect bliss of unsurpassed enlightenment; therefore, the wise generate the supreme awakening mind.

                Swept along by the currents of birth, aging, sickness, and death, tightly bound by the cords of karma so hard to undo, caught inside the iron cage of grasping at a self, and completely enveloped in the thick darkness of ignorance—taking birth in limitless cyclic existence again and again, unceasingly tormented by the three sufferings: contemplating the condition of your mothers who have reached such a state, generate the supreme awakening mind.

                III. The Correct View (Wisdom of Emptiness)

                If you lack the wisdom realizing the mode of being, even though you have habituated to renunciation and the awakening mind, you will be unable to cut the root of cyclic existence; therefore, make effort in the methods for realizing dependent origination.

                Whoever sees that cause and effect are unfailing for all phenomena of cyclic existence and nirvana, and for whom all objects of conceptual apprehension have collapsed, has entered the path that pleases the Buddhas.

                As long as the two—the understanding of unfailing dependent appearance and the understanding of emptiness free from assertions—appear to you as separate, you have still not realized the intent of the Able One.

                When these two occur simultaneously, not alternating, and from merely seeing unfailing dependent origination, certainty is born that completely destroys all modes of apprehending objects, at that time, the analysis of the view is complete.

                Furthermore, when appearance dispels the extreme of existence, and emptiness dispels the extreme of non-existence, and you know the way emptiness dawns as cause and effect, you will not be stolen away by views that grasp at extremes.

                Conclusion & Closing Note

                When you have understood the key points of the three principal aspects of the path just as they are, rely on solitude, dear disciple, and generate the power of joyous perseverance—swiftly accomplish your ultimate aspiration!

                This instruction was given by the extensively learned monk Lobzang Drakpai Pal (Tsongkhapa) to Tsakho Onpo Ngawang Drakpa.
            """.trimIndent(),
            quiz = Quiz(
                id = quizIdFor(31),
                title = "Quiz: The Three Principal Aspects of the Path",
                questions = listOf(
                    Question(
                        id = 1,
                        text = "When is the analysis of the correct view complete?",
                        options = listOf(
                            "When appearance and emptiness are understood separately in alternating sessions",
                            "When unfailing dependent appearance and emptiness free from assertions occur simultaneously, not alternating",
                            "When one rejects all cause and effect as non-existent",
                            "When one memorizes all philosophical treatises"
                        ),
                        correctOptionIndex = 1,
                        explanation = "The analysis of the view is complete when unfailing dependent appearance and emptiness free from assertions occur simultaneously, not alternating."
                    ),
                    Question(
                        id = 2,
                        text = "What are the three principal aspects of the path outlined by Je Tsongkhapa?",
                        options = listOf(
                            "Generosity, ethical discipline, and physical asceticism",
                            "Ritual chanting, pilgrimage, and monastic debate",
                            "Renunciation (determination to be free), the Awakening Mind (bodhicitta), and the Correct View (wisdom of emptiness)",
                            "Wealth, longevity, and rebirth in celestial realms"
                        ),
                        correctOptionIndex = 2,
                        explanation = "Tsongkhapa outlines I. The Determination to Be Free (Renunciation), II. The Awakening Mind (Bodhicitta), and III. The Correct View (Wisdom of Emptiness)."
                    ),
                    Question(
                        id = 3,
                        text = "According to the text, what happens if you lack the wisdom realizing the mode of being despite habituating to renunciation and the awakening mind?",
                        options = listOf(
                            "You immediately achieve unsurpassed enlightenment",
                            "You will be unable to cut the root of cyclic existence",
                            "You no longer need to practice ethical conduct",
                            "You automatically master all meditative states"
                        ),
                        correctOptionIndex = 1,
                        explanation = "Without the wisdom realizing the mode of being, even with renunciation and the awakening mind, you will be unable to cut the root of cyclic existence."
                    )
                )
            )
        )
    )
}
