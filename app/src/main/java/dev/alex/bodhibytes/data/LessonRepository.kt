package dev.alex.bodhibytes.data

import dev.alex.bodhibytes.model.Lesson
import dev.alex.bodhibytes.model.Question
import dev.alex.bodhibytes.model.Quiz

object LessonRepository {

    fun getCurriculum(): List<Lesson> {
        return listOf(
            Lesson(
                id = 1,
                title = "Buddhist Words and Their Meanings",
                summary = "Explore key traditional Pali and Sanskrit terms used throughout Buddhist philosophy to deepen your understanding of the teachings.",
                content = """
                    Understanding the foundational terminology of Buddhism opens a clearer window into the philosophy and meditative traditions. 

                    Sentient Beings: Living creatures who view the world through their senses.

                    Equanimity: Unshakeable emotional balance undisturbed by fluctuating life conditions.

                    Dharma: The teachings and doctrine expounded by the Buddha.

                    Nirvana: The ultimate state of liberation and release from suffering, craving, and rebirth.

                    Bodhicitta: The altruistic aspiration to attain full enlightenment for all beings.

                    Sangha: The community of monks, nuns, and practitioners walking the path together.

                    Samsara: The ongoing, repetitive cycle of birth, existence, and dying.

                    Prajna: Transcendent wisdom and intuitive insight into reality and emptiness.

                    Metta: Boundless loving-kindness and goodwill toward all living beings.

                    Kalyana-mitra: A spiritual friend, mentor, or guide along the path.

                    Upaya: Skillful means or adaptive strategies used to guide practice.

                    Tathagata: An epithet for the Buddha, meaning one who has realized ultimate truth.

                    Madhyamaka: The "Middle Way" school demonstrating the emptiness of all phenomena.
                """.trimIndent(),
                quiz = Quiz(
                    id = 1,
                    title = "Quiz: Buddhist Words and Their Meanings",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What is the definition of *Sentient Beings* in this curriculum?",
                            options = listOf(
                                "Living creatures who view the world through their senses.",
                                "Spiritual beings who have attained full enlightenment.",
                                "Entities that exist outside of physical reality.",
                                "Monastic teachers who guide the community."
                            ),
                            correctOptionIndex = 0,
                            explanation = "Sentient beings are defined as living creatures who view the world through their senses."
                        ),
                        Question(
                            id = 2,
                            text = "How is *Equanimity* defined in Buddhist practice?",
                            options = listOf(
                                "Extreme emotional swings during meditation",
                                "An unshakeable emotional balance undisturbed by fluctuating life conditions.",
                                "The rejection of all physical sensations",
                                "An intense attachment to pleasant outcomes"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Equanimity is unshakeable emotional balance undisturbed by fluctuating life conditions."
                        ),
                        Question(
                            id = 3,
                            text = "What does the term *Dharma* specifically represent in Buddhist practice?",
                            options = listOf(
                                "Ancient architectural temple designs",
                                "The teachings and doctrine expounded by the Buddha.",
                                "The system of monastic taxation",
                                "Physical health and bodily postures"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Dharma refers to the teachings and doctrine expounded by the Buddha."
                        ),
                        Question(
                            id = 4,
                            text = "What is *Nirvana* described as?",
                            options = listOf(
                                "A temporary state of deep sleep",
                                "The ultimate state of liberation and release from suffering, craving, and rebirth.",
                                "The physical journey to sacred shrines",
                                "An emotional reaction to loss"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Nirvana is the ultimate state of liberation and release from suffering, craving, and rebirth."
                        ),
                        Question(
                            id = 5,
                            text = "What does *Bodhicitta* mean?",
                            options = listOf(
                                "The intellectual study of ancient texts",
                                "The altruistic aspiration to attain full enlightenment for all beings.",
                                "The physical endurance of hardship",
                                "A strict set of dietary rules"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Bodhicitta is the altruistic aspiration to attain full enlightenment for all beings."
                        ),
                        Question(
                            id = 6,
                            text = "Who or what comprises the *Sangha*?",
                            options = listOf(
                                "Only the historical figures of early Buddhism",
                                "The community of monks, nuns, and practitioners walking the path together.",
                                "The collection of sacred scriptures",
                                "The lay merchants who fund temples"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Sangha is the community of monks, nuns, and practitioners walking the path together."
                        ),
                        Question(
                            id = 7,
                            text = "How is *Samsara* defined?",
                            options = listOf(
                                "The peaceful state of final liberation",
                                "The ongoing, repetitive cycle of birth, existence, and dying.",
                                "A specialized breathing technique",
                                "The moral law of cause and effect"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Samsara is the ongoing, repetitive cycle of birth, existence, and dying."
                        ),
                        Question(
                            id = 8,
                            text = "What does *Prajna* signify?",
                            options = listOf(
                                "Physical strength and agility",
                                "Transcendent wisdom and intuitive insight into reality and emptiness.",
                                "Strict adherence to daily rituals",
                                "Emotional attachment to family"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Prajna is transcendent wisdom and intuitive insight into reality and emptiness."
                        ),
                        Question(
                            id = 9,
                            text = "What is *Metta*?",
                            options = listOf(
                                "Boundless loving-kindness and goodwill toward all living beings.",
                                "A rigorous form of physical fasting",
                                "The avoidance of all social contact",
                                "Financial contribution to monasteries"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Metta is boundless loving-kindness and goodwill toward all living beings."
                        ),
                        Question(
                            id = 10,
                            text = "Who or what is a *Kalyana-mitra*?",
                            options = listOf(
                                "A fierce opponent in debate",
                                "A spiritual friend, mentor, or guide along the path.",
                                "A sacred text written on palm leaves",
                                "An ancient meditation hall"
                            ),
                            correctOptionIndex = 1,
                            explanation = "A kalyana-mitra is a spiritual friend, mentor, or guide along the path."
                        ),
                        Question(
                            id = 11,
                            text = "What does *Upaya* mean?",
                            options = listOf(
                                "Rigid dogma that cannot be questioned",
                                "Skillful means or adaptive strategies used to guide practice.",
                                "An inescapable karmic punishment",
                                "The complete cessation of thought"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Upaya refers to skillful means or adaptive strategies used to guide practice."
                        ),
                        Question(
                            id = 12,
                            text = "What is the meaning of the epithet *Tathagata*?",
                            options = listOf(
                                "One who rules over kingdoms",
                                "An epithet for the Buddha, meaning one who has realized ultimate truth.",
                                "A wanderer lost in the forest",
                                "A creator of physical matter"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Tathagata is an epithet for the Buddha, meaning one who has realized ultimate truth."
                        ),
                        Question(
                            id = 13,
                            text = "What does the school of *Madhyamaka* demonstrate?",
                            options = listOf(
                                "The permanent existence of physical atoms",
                                "The 'Middle Way' school demonstrating the emptiness of all phenomena.",
                                "The strict hierarchy of monastic life",
                                "The predictability of future rebirths"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Madhyamaka is the 'Middle Way' school demonstrating the emptiness of all phenomena."
                        )
                    )
                )
            ),
            Lesson(
                id = 2,
                title = "The Middle Way",
                summary = "Discovering the balanced path between over indulgence and self-denial as taught in the first discourse.",
                content = """
                    The Middle Way is the core framework of Buddhist teachings, first introduced by the Buddha in his inaugural discourse delivered to five monks at the Deer Park.

                    The Buddha identified two physical and emotional extremes to avoid:
                    
                    1. Over Indulgence: Pursuing sensual pleasures which are low, coarse, unworthy, and unprofitable.
                    
                    2. Self-Denial: Avoiding worldly pleasures and fasting which is painful, unworthy, and unprofitable.

                    Avoiding both extremes, the Buddha realized the Middle Path, which leads to calm, insight, enlightenment, and ultimate peace. 

                    This Middle Path is the Noble Eightfold Path, consisting of eight practical factors for ethical and mental development.
                """.trimIndent(),
                quiz = Quiz(
                    id = 2,
                    title = "Quiz: The Middle Way",
                    questions = listOf(
                        Question(
                            id = 1,
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
                            id = 2,
                            text = "What are the two extremes that a practitioner must avoid?",
                            options = listOf(
                                "Wealth and poverty",
                                "Over indulgence and self-denial",
                                "Dogma and skepticism",
                                "Activity and laziness"
                            ),
                            correctOptionIndex = 1,
                            explanation = "The Buddha explicitly warned against addiction to over indulgence and self-denial."
                        ),
                        Question(
                            id = 3,
                            text = "What specifically constitutes the Middle Path?",
                            options = listOf(
                                "Strict adherence to ritual sacrifices",
                                "The Four Noble Truths alone",
                                "The Noble Eightfold Path",
                                "Living a life of moderate luxury"
                            ),
                            correctOptionIndex = 2,
                            explanation = "The Middle Path is defined as the Noble Eightfold Path."
                        )
                    )
                )
            ),
            Lesson(
                id = 3,
                title = "Koan Study: A Buddha",
                summary = "Exploring Koan 13 from Zen Flesh, Zen Bones to understand how true practice transcends rigid rule-following and recognizes the inherent Buddha-nature in every moment.",
                content = """
                    The Koan Text:

                    "In Tokyo in the Meiji era there lived two prominent teachers of opposite characteristics. One, Unsho, an instructor in Shingon, kept Buddha's precepts scrupulously. He never drank intoxicants, nor did he eat after eleven o'clock in the morning. The other teacher, Tanzan, a professor of philosophy at the Imperial University, never observed the precepts. When he felt like eating, he ate, and when he felt like sleeping in the daytime, he slept.

                    One day Unsho visited Tanzan, who was drinking wine at the time, not even a drop of which is supposed to touch the tongue of a Buddhist.

                    'Hello, brother,' Tanzan greeted him. 'Won't you have a drink?'

                    'I never drink!' exclaimed Unsho solemnly.

                    'One who does not drink is not even human,' said Tanzan.

                    'Do you mean to call me inhuman just because I do not indulge in intoxicating liquids!' exclaimed Unsho in anger. 'Then if I am not human, what am I?'

                    'A Buddha,' answered Tanzan."

                    Breaking Rigid Dualism: This encounter targets the trap of spiritual pride and external rigidity. Unsho relied heavily on outward rules to define his purity, while looking down on others. By calling him a "Buddha" when pushed, Tanzan touched past superficial conceptual labels straight to ultimate reality.

                    The Essence of Practice: Precepts and rules are helpful training wheels, but treating them as a badge of superior identity creates a subtle ego. True realization does not cling to being "good" or "bad," "strict" or "lax"; it rests in the open, unconditioned nature of the mind.
                """.trimIndent(),
                quiz = Quiz(
                    id = 3,
                    title = "Quiz: Koan Study: A Buddha",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "How did Unsho approach his daily spiritual practice?",
                            options = listOf(
                                "He scrupulously kept the precepts, avoiding alcohol and late meals",
                                "He completely ignored all traditions and lived as a wandering hermit",
                                "He focused solely on teaching philosophy at the Imperial University",
                                "He spent his entire day composing poetry and playing the harp"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Unsho was known for keeping the precepts very strictly."
                        ),
                        Question(
                            id = 2,
                            text = "What was Tanzan's startling response when an angry Unsho asked what he was if he wasn't human?",
                            options = listOf(
                                "An ordinary person",
                                "A Buddha",
                                "A strict ascetic",
                                "A sleeping scholar"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Tanzan answered, 'A Buddha'."
                        )
                    )
                )
            ),
            Lesson(
                id = 4,
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
                    id = 4,
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
                            explanation = "They function like a physician's diagnosis: identifying the symptom, finding the cause, confirming a cure, and prescribing treatment."
                        ),
                        Question(
                            id = 2,
                            text = "What is identified as the root origin of suffering in the second truth?",
                            options = listOf(
                                "Lack of formal education",
                                "Craving and passionate greed",
                                "Physical exhaustion",
                                "Environmental changes"
                            ),
                            correctOptionIndex = 1,
                            explanation = "The origin of suffering is identified as craving and passionate greed."
                        ),
                        Question(
                            id = 3,
                            text = "Which of the following completes the fourth truth regarding the path to cessation?",
                            options = listOf(
                                "Strict isolation from society",
                                "The Noble Eightfold Path",
                                "Accumulating merit through wealth",
                                "Enduring hardship without complaint"
                            ),
                            correctOptionIndex = 1,
                            explanation = "The path leading to the cessation of suffering is the Noble Eightfold Path."
                        )
                    )
                )
            ),
            Lesson(
                id = 5,
                title = "The Noble Eightfold Path",
                summary = "Exploring the eight practical factors of the path that guide ethical conduct, mental discipline, and wisdom.",
                content = """
                    The Noble Eightfold Path is the practical guide designed to lead away from suffering and toward awakening. It is categorized into three essential areas: ethical conduct, mental discipline, and wisdom.

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
                    id = 5,
                    title = "Quiz: The Noble Eightfold Path",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "Into what core areas are the eight factors generally grouped?",
                            options = listOf(
                                "Wisdom, ethical conduct, and mental discipline",
                                "History, philosophy, and geography",
                                "Wealth, health, and status",
                                "Ritual, prayer, and sacrifice"
                            ),
                            correctOptionIndex = 0,
                            explanation = "The path encompasses factors related to wisdom, ethical conduct, and mental discipline."
                        ),
                        Question(
                            id = 2,
                            text = "Which factor involves abstaining from false, harsh, or divisive speech?",
                            options = listOf(
                                "Right Action",
                                "Right Speech",
                                "Right Livelihood",
                                "Right Effort"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Right Speech guides practitioners to avoid harmful and dishonest communication."
                        )
                    )
                )
            ),
            Lesson(
                id = 6,
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
                    id = 6,
                    title = "Quiz: The Three Higher Trainings",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What are the three components of the Higher Trainings?",
                            options = listOf(
                                "Wealth, health, and status",
                                "Higher ethics, higher concentration, and higher wisdom",
                                "Memory, speed, and agility",
                                "Prayer, ritual, and sacrifice"
                            ),
                            correctOptionIndex = 1,
                            explanation = "The framework consists of higher ethics, concentration, and wisdom."
                        ),
                        Question(
                            id = 2,
                            text = "How do the three trainings interact with each other?",
                            options = listOf(
                                "They operate completely independently without overlap",
                                "Ethics supports concentration, which empowers clear wisdom",
                                "Wisdom replaces the need for ethical conduct entirely",
                                "Concentration is only useful for physical exercise"
                            ),
                            correctOptionIndex = 1,
                            explanation = "They reinforce each other, forming a cohesive progressive structure."
                        )
                    )
                )
            ),
            Lesson(
                id = 7,
                title = "Koan Study: No Attachment to Dust",
                summary = "Exploring Koan 77 from Zen Flesh, Zen Bones to learn the practical guidelines of a true Zen student for living in the world without being trapped by its illusions.",
                content = """
                    The Koan Text:

                    "Zengetsu, a Chinese master of the T'ang dynasty, wrote the following advice for his pupils:

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

                    Live with cause and leave results to the great law of the universe. Pass each day in peaceful contemplation."

                    Transcending the World While Living Within It: This text emphasizes that true Zen practice is not about escaping society, but about maintaining inner clarity and non-attachment amidst daily life. By guarding our character when alone just as we would in public, we build a steady foundation of genuine virtue.

                    The Art of Non-Contention: Zengetsu counsels us to stop arguing over right and wrong, to focus entirely on self-reflection rather than criticizing others, and to let go of the desperate craving for immediate approval. True practice lives quietly in the present moment, indifferent to both shame and glory.
                """.trimIndent(),
                quiz = Quiz(
                    id = 7,
                    title = "Quiz: Koan Study: No Attachment to Dust",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "According to Zengetsu's advice, how should a practitioner handle the actions of others?",
                            options = listOf(
                                "Publicly correct everyone's mistakes immediately",
                                "Imitate good actions and avoid emulating mistaken ones",
                                "Ignore everyone and isolate completely from society",
                                "Judge others based on traditional cultural norms"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Zengetsu advised encouraging yourself to follow good examples and choosing not to emulate mistakes."
                        ),
                        Question(
                            id = 2,
                            text = "What does Zengetsu recommend regarding blame and judgment?",
                            options = listOf(
                                "Censure yourself, never another, and do not discuss right and wrong",
                                "Strictly judge your neighbors to keep the community honest",
                                "Argue publicly whenever righteousness is questioned",
                                "Seek immediate appreciation for your good deeds"
                            ),
                            correctOptionIndex = 0,
                            explanation = "The teaching explicitly states to censure yourself, never another, and avoid discussing right and wrong."
                        )
                    )
                )
            ),
            Lesson(
                id = 8,
                title = "Interdependence",
                summary = "Exploring how all things are profoundly connected and arise in relation to one another.",
                content = """
                    Interdependence reveals that nothing exists in total isolation. Everything in the universe—from human beings to nature—arises in connection with a vast web of conditions.

                    When we look deeply, we see that our well-being is tied to the well-being of others and our environment. Every action, word, and thought ripples outward and impacts the whole.

                    Realizing interdependence dissolves feelings of absolute separation. It inspires a natural sense of care, shared responsibility, and harmony with the world around us.
                """.trimIndent(),
                quiz = Quiz(
                    id = 8,
                    title = "Quiz: Interdependence",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What does the principle of interdependence teach about existence?",
                            options = listOf(
                                "That everything exists in complete isolation",
                                "That all things arise in connection with a vast web of conditions",
                                "That only human actions matter in the universe",
                                "That physical objects are entirely permanent"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Interdependence teaches that everything arises in connection with a vast web of conditions."
                        ),
                        Question(
                            id = 2,
                            text = "What does realizing interdependence naturally inspire in a person?",
                            options = listOf(
                                "A sense of shared responsibility and care for the whole",
                                "Indifference toward social and environmental issues",
                                "A desire to isolate completely from society",
                                "Competition and rivalry with others"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Recognizing our connection to everything inspires shared responsibility and care."
                        )
                    )
                )
            ),
            Lesson(
                id = 9,
                title = "Thich Nhat Hanh and Interbeing",
                summary = "Exploring the teachings of Zen master Thich Nhat Hanh on 'Interbeing,' discovering how every element of the universe is profoundly interconnected.",
                content = """
                    The Teacher of Interbeing: Thich Nhat Hanh was a globally renowned Vietnamese Thiền (Zen) master, poet, scholar, and peace activist who popularized the practice of mindfulness in the West. He coined the term "Interbeing" to describe the profound truth that nothing exists separately, but is instead composed entirely of everything else in the universe.

                    The Practice of Looking Deeply: Interbeing is not merely an abstract philosophical concept, but a direct invitation to look deeply into the nature of daily phenomena. When we examine a simple object like a sheet of paper, Thich Nhat Hanh taught that we can see clouds, rain, sunshine, the forest, and the logger—all cooperating intimately to bring that paper into existence.

                    Living with Mindfulness and Compassion: Recognizing our interbeing transforms how we relate to the world and to one another. Because our actions, thoughts, and words ripple through the web of life, living mindfully becomes an act of deep reverence, urging us to cultivate compassion, reduce suffering, and foster global peace.
                """.trimIndent(),
                quiz = Quiz(
                    id = 9,
                    title = "Quiz: Thich Nhat Hanh and Interbeing",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What does the term 'Interbeing' describe in Thich Nhat Hanh's teachings?",
                            options = listOf(
                                "That objects exist entirely in complete isolation from one another",
                                "That nothing exists separately and everything is composed of the rest of the universe",
                                "That physical matter is permanent and unchanging",
                                "That individual human choices have no effect on others"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Interbeing expresses that all things are interconnected and composed of everything else."
                        ),
                        Question(
                            id = 2,
                            text = "According to the lesson, what does recognizing interbeing inspire a practitioner to do?",
                            options = listOf(
                                "Cultivate compassion, reduce suffering, and foster global peace",
                                "Withdraw from society into complete isolation",
                                "Ignore the consequences of daily actions",
                                "Compete aggressively with others for resources"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Recognizing our profound connection to all life inspires compassion and active peace-building."
                        )
                    )
                )
            ),
            Lesson(
                id = 10,
                title = "Dependent Origination",
                summary = "Understanding how events and experiences arise conditioned by preceding causes.",
                content = """
                    Dependent origination is the core teaching that nothing happens by random chance or as an isolated event. Every physical and mental state arises conditioned by preceding causes.

                    In human experience, this shows how habits, reactions, and emotional patterns form a continuous chain. For example, sensory contact leads to feeling, which can trigger craving if we are not mindful.

                    Seeing how this chain operates gives us the power to interrupt unwholesome cycles. By introducing awareness at the moment of contact, we break old reactionary habits and create new, peaceful possibilities.
                """.trimIndent(),
                quiz = Quiz(
                    id = 10,
                    title = "Quiz: Dependent Origination",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What does dependent origination state about events and experiences?",
                            options = listOf(
                                "They happen entirely by random chance",
                                "They arise conditioned by preceding causes",
                                "They are completely unchanging and permanent",
                                "They exist independently of the mind"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Dependent origination teaches that everything arises conditioned by preceding causes."
                        ),
                        Question(
                            id = 2,
                            text = "How can a practitioner break unwholesome cycles according to this teaching?",
                            options = listOf(
                                "By introducing awareness at the moment of contact to stop automatic reactions",
                                "By ignoring sensory experiences entirely",
                                "By forcing the mind to dwell on the past",
                                "By avoiding all social interactions"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Introducing awareness breaks the automatic link between contact and unwholesome reaction."
                        )
                    )
                )
            ),
            Lesson(
                id = 11,
                title = "Emptiness",
                summary = "Realizing that all phenomena are empty of a separate, permanent, or independent self-nature.",
                content = """
                    Building directly on dependent origination, emptiness is the profound insight that nothing exists independently, permanently, or by its own separate power. 

                    In this philosophy, emptiness does not mean nothingness or that the world is an illusion. Instead, it means that things are completely empty of an isolated self-nature because they are made entirely of non-self elements and shifting conditions.

                    Understanding emptiness frees the mind from rigid labels and absolute boundaries. When we see that people, events, and objects are fluid and interconnected, we stop grasping, dissolve rigid mental barriers, and experience ultimate liberation.
                """.trimIndent(),
                quiz = Quiz(
                    id = 11,
                    title = "Quiz: Emptiness",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What does emptiness mean in this philosophy?",
                            options = listOf(
                                "That the world is a total illusion and nothing exists at all",
                                "That phenomena are empty of a separate, permanent, or independent self-nature",
                                "That physical objects are entirely rigid and unchanging",
                                "That human actions have no consequences"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Emptiness means things lack a separate, independent self-nature because they rely on conditions."
                        ),
                        Question(
                            id = 2,
                            text = "What does the realization of emptiness help a practitioner achieve?",
                            options = listOf(
                                "Rigid control over everyone around them",
                                "Freedom from absolute boundaries, leading to ultimate liberation",
                                "Cold emotional detachment from reality",
                                "Permanent social isolation"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Realizing emptiness dissolves rigid mental barriers and brings ultimate liberation."
                        )
                    )
                )
            ),
            Lesson(
                id = 12,
                title = "Nagarjuna and The Fundamental Wisdom of the Middle Way",
                summary = "Exploring the foundational text of Madhyamaka philosophy by Nagarjuna, which demonstrates the emptiness of all phenomena through logical analysis.",
                content = """
                    The Master of the Middle Way: Nagarjuna, living in 2nd-century India, is regarded as one of the most influential philosophers in Buddhist history. He resuscitated and systematically articulated the profound teachings on emptiness (śūnyatā) found in the Perfection of Wisdom sutras. Through razor-sharp logical analysis, Nagarjuna demonstrated that nothing possesses an intrinsic, independent essence (svabhāva).

                    The Fundamental Wisdom (Mūlamadhyamakakārikā): Nagarjuna's masterwork, the Mūlamadhyamakakārikā, examines every major category of experience—including causation, motion, time, the elements, and the self—to show how grasping at absolute existence collapses under scrutiny. By examining how things arise dependently, he showed that dependent origination and emptiness are completely synonymous.

                    Refuting Extremes of Existence and Nihilism: Nagarjuna famously argued that emptiness does not mean nothingness (nihilism), nor does it mean things exist inherently (eternalism). Instead, because things exist dependently, they are empty of inherent existence while remaining functionally operational in conventional reality. This delicate realization avoids all conceptual extremes.

                    The Purpose of Deconstruction: The logical deconstructions presented by Nagarjuna are not meant to foster skepticism or academic debate. Rather, they serve a therapeutic purpose: dismantling our deeply ingrained habit of reifying concepts, clinging to views, and building mental barriers, thereby freeing the mind to experience ultimate peace.
                """.trimIndent(),
                quiz = Quiz(
                    id = 12,
                    title = "Quiz: Nagarjuna and The Fundamental Wisdom of the Middle Way",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What is the central thesis demonstrated by Nagarjuna in the Mūlamadhyamakakārikā?",
                            options = listOf(
                                "That all phenomena possess an unchanging, eternal essence",
                                "That nothing possesses intrinsic, independent existence (svabhāva) and all things are empty",
                                "That the physical world is a permanent creation of distinct atoms",
                                "That logic and reasoning have no place in spiritual practice"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Nagarjuna demonstrated that all phenomena are empty of inherent existence because they arise dependently."
                        ),
                        Question(
                            id = 2,
                            text = "How does Nagarjuna's philosophy avoid the extremes of eternalism and nihilism?",
                            options = listOf(
                                "By proving that the world is a total illusion and nothing functions at all",
                                "By demonstrating that dependent origination means things are empty yet functionally operational",
                                "By establishing strict rules for ritual sacrifices and moral codes",
                                "By asserting that a permanent soul exists behind every mental state"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Emptiness as dependent origination bridges existence and non-existence, avoiding both absolute permanence and total nothingness."
                        )
                    )
                )
            ),
            Lesson(
                id = 13,
                title = "Āryadeva and The Four Hundred",
                summary = "Exploring the masterpiece of the 3rd-century Madhyamaka master Āryadeva, The Four Hundred Verse Treatise on the Conduct of Bodhisattva Yogis.",
                content = """
                    The Lineage of Wisdom: Āryadeva was the chief spiritual disciple and heart-son of Nāgārjuna, the founding father of the Madhyamaka (Middle Way) school. Hailing originally from Sri Lanka, Āryadeva traveled to the great monastic university of Nalanda in India, where he became a legendary scholar, debater, and teacher.

                    Structure of the Text: The Four Hundred (Catuḥśataka) is divided into 16 chapters of 25 verses each, totaling 400 verses. Structurally, the text is split into two halves:

                    1. The First Eight Chapters (Conventional Reality & Merit): Focus on abandoning the four fundamental distortions of reality—misconceiving what is impermanent as permanent, suffering as pleasurable, impure as clean, and non-self as having a self. These chapters use the human body and everyday life as practical meditation objects to cut through ordinary attachments.

                    2. The Final Eight Chapters (Ultimate Reality & Emptiness): Systematically dismantle philosophical errors, reified concepts of time, space, atoms, and self, guiding the practitioner directly to the realization of emptiness.

                    Overcoming the Four Perverted Views: 

                    - Permanence: Āryadeva confronts our deep denial of death, reminding us that life is fleeting like cattle being led to slaughter, leaving no room for complacency.

                    - Pleasurable & Clean: He challenges our physical attachments by revealing the inherent limitations, vulnerabilities, and impurities of the body.

                    - Ego/Self: He dismantles pride and self-cherishing, urging practitioners to look past superficial status and recognize the emptiness of ego.

                    The Gradual Progression of Practice: Āryadeva encapsulates the entire path of a Bodhisattva in a famous instruction:
                    
                         First prevent the demeritorious
                    
                         Next prevent self
                         
                         Later prevent views of all kinds.
                     
                    By sequentially clearing away negative karma, dissolving the illusion of an inherent self, and finally letting go of all conceptual fixations on philosophical views, the mind rests completely free in ultimate truth.
                """.trimIndent(),
                quiz = Quiz(
                    id = 13,
                    title = "Quiz: Āryadeva and The Four Hundred",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "Who was Āryadeva's primary teacher and founder of the Madhyamaka tradition?",
                            options = listOf(
                                "Asanga",
                                "Nāgārjuna",
                                "Śāntideva",
                                "Vasubandhu"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Āryadeva was the principal heart-disciple and successor to Nāgārjuna."
                        ),
                        Question(
                            id = 2,
                            text = "What is the structural focus of the first half (chapters 1 through 8) of The Four Hundred?",
                            options = listOf(
                                "Detailed calculations of historical rebirth timelines",
                                "Architectural guidelines for building monastic debate halls",
                                "Abandoning the four perverted views of permanence, pleasure, cleanliness, and self",
                                "Strict rules regarding dietary restrictions for monks"
                            ),
                            correctOptionIndex = 2,
                            explanation = "The first eight chapters target conventional reality, helping practitioners overcome foundational cognitive errors regarding the body and world."
                        ),
                        Question(
                            id = 3,
                            text = "According to Āryadeva's progressive advice on practice, what should be addressed last?",
                            options = listOf(
                                "Preventing demeritorious actions",
                                "Overcoming physical sickness",
                                "Preventing views of all kinds",
                                "Accumulating material wealth for charity"
                            ),
                            correctOptionIndex = 2,
                            explanation = "Āryadeva instructs to first stop negative actions, next prevent the ego/self, and finally let go of all conceptual views."
                        )
                    )
                )
            ),
            Lesson(
                id = 14,
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
                    id = 14,
                    title = "Quiz: The Three Marks of Existence",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What are the three universal characteristics shared by all conditioned things?",
                            options = listOf(
                                "Wealth, health, and social status",
                                "Impermanence, unsatisfactoriness, and non-self",
                                "Birth, growth, and destruction",
                                "Joy, sorrow, and indifference"
                            ),
                            correctOptionIndex = 1,
                            explanation = "All conditioned things share the marks of impermanence, unsatisfactoriness, and non-self."
                        ),
                        Question(
                            id = 2,
                            text = "What does recognizing the mark of non-self help dissolve?",
                            options = listOf(
                                "The illusion of a static, isolated ego or permanent self",
                                "The physical body's natural need for rest",
                                "The ability to experience emotions altogether",
                                "The necessity of ethical conduct"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Recognizing non-self dissolves the illusion of a fixed, permanent ego."
                        )
                    )
                )
            ),
            Lesson(
                id = 15,
                title = "Impermanence",
                summary = "Understanding that all conditioned phenomena are transient, shifting, and in constant flux.",
                content = """
                    Building on our understanding of experience, we examine impermanence as the underlying characteristic of all conditioned experience. 

                    Because all conditioned phenomena arise due to causes, they are transient, shifting, and constantly in flux. Grasping onto temporary things as if they are permanent is what deepens distress.

                    Understanding impermanence liberates the mind. When we realize that both pleasant and painful experiences are fleeting, we stop clinging, cultivate deep equanimity, and uncover lasting inner peace.
                """.trimIndent(),
                quiz = Quiz(
                    id = 15,
                    title = "Quiz: Impermanence",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "Why do conditioned phenomena inevitably lead to dissatisfaction if clung to?",
                            options = listOf(
                                "Because they are inherently rigid and unchanging",
                                "Because they are transient, shifting, and in constant flux",
                                "Because they are entirely disconnected from human action",
                                "Because they require constant financial investment"
                            ),
                            correctOptionIndex = 1,
                            explanation = "All conditioned phenomena are impermanent, transient, and in constant flux."
                        ),
                        Question(
                            id = 2,
                            text = "What mental state is cultivated by truly recognizing the fleeting nature of experiences?",
                            options = listOf(
                                "Equanimity and lasting inner peace",
                                "Indifference and emotional numbness",
                                "Restless pursuit of new sensations",
                                "Rigid attachment to routine"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Understanding impermanence helps us stop clinging, cultivating equanimity and inner peace."
                        )
                    )
                )
            ),
            Lesson(
                id = 16,
                title = "Unsatisfactoriness",
                summary = "Examining how grasping onto impermanence and trying to control changing conditions leads to friction and distress.",
                content = """
                    Building directly upon the reality of impermanence, unsatisfactoriness is the inherent limitation and friction found in all conditioned things. 

                    Because every physical and mental experience is constantly shifting, nothing within the conditioned world can provide permanent, unshakeable satisfaction. When we expect temporary states to remain stable or treat them as sources of ultimate security, we create inner tension and distress.

                    Recognizing unsatisfactoriness is not meant to be pessimistic, but rather profoundly liberating. It helps us release unrealistic expectations, stop striving for permanent control over changing conditions, and find a deeper peace independent of external circumstances.
                """.trimIndent(),
                quiz = Quiz(
                    id = 16,
                    title = "Quiz: Unsatisfactoriness",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What is the primary root cause of unsatisfactoriness described in this lesson?",
                            options = listOf(
                                "The complete absence of physical motion",
                                "Clinging to temporary things as if they can provide permanent security",
                                "Strict adherence to the Noble Eightfold Path",
                                "A lack of formal philosophical training"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Unsatisfactoriness arises when we expect changing, impermanent things to provide permanent security."
                        ),
                        Question(
                            id = 2,
                            text = "What is the practical benefit of recognizing unsatisfactoriness?",
                            options = listOf(
                                "It helps us release unrealistic expectations and stop striving for permanent control",
                                "It forces us to withdraw completely from all social relationships",
                                "It teaches us how to manipulate external events permanently",
                                "It guarantees complete physical immortality"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Recognizing it helps us stop trying to control changing conditions, freeing us from friction."
                        )
                    )
                )
            ),
            Lesson(
                id = 17,
                title = "Non-Self",
                summary = "Discovering that at the core of human experience there is no static, permanent, or isolated ego.",
                content = """
                    As the culmination of insight into impermanence, emptiness, and unsatisfactoriness, the teaching of non-self reveals that there is no static, permanent, or isolated ego at the center of human existence.

                    We commonly view our thoughts, emotions, and physical bodies as an unchanging core identity. However, when we observe closely, we see that these elements are merely a fluid, interdependent process changing from moment to moment.

                    Realizing non-self dissolves the heavy burden of guarding a fixed ego. Without a rigid self to defend, the mind opens up to boundless empathy, humility, and effortless freedom.
                """.trimIndent(),
                quiz = Quiz(
                    id = 17,
                    title = "Quiz: Non-Self",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What does the teaching of non-self reveal about human existence?",
                            options = listOf(
                                "That there is a permanent, unchanging soul hidden within",
                                "That there is no static, permanent, or isolated ego at the center of our experience",
                                "That human beings are entirely separate from nature",
                                "That thoughts and emotions never change"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Non-self reveals that our experience consists of fluid, changing processes rather than a static ego."
                        ),
                        Question(
                            id = 2,
                            text = "What mental shift occurs when a practitioner realizes non-self?",
                            options = listOf(
                                "The heavy burden of defending a fixed ego is dissolved, bringing freedom",
                                "The individual becomes entirely emotionless and cold",
                                "External circumstances begin to obey personal commands",
                                "Memory and physical awareness are permanently erased"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Releasing the illusion of a fixed ego frees the mind from constant defense and struggle."
                        )
                    )
                )
            ),
            Lesson(
                id = 18,
                title = "Koan Study: Is That So?",
                summary = "Exploring Koan 3 from Zen Flesh, Zen Bones to learn how to maintain equanimity, non-attachment, and emotional freedom in the face of false accusations and praise alike.",
                content = """
                    The Koan Text:

                    "The Zen master Hakuin was praised by his neighbors as one living a pure life.

                    A beautiful Japanese girl whose parents owned a food store lived near him. Suddenly, without any warning, her parents discovered she was with child.

                    This made her parents very angry. She would not confess who the man was, but after much harassment at last named Hakuin.

                    In great anger the parents went to the master. 'Is that so?' was all he would say.

                    After the child was born it was brought to Hakuin. By this time he had lost his reputation, which did not trouble him, but he took very good care of the child. He obtained milk from his neighbors and everything else the little one needed.

                    A year later the girl-mother could stand it no longer. She told her parents the truth - that the real father of the child was a young man who worked in the fishmarket.

                    The mother and father of the girl at once went to Hakuin to ask his forgiveness, to apologize at length, and to get the child back again.

                    Hakuin was willing. In yielding the child, all he said was: 'Is that so?'"

                    Equanimity in Praise and Blame: Hakuin demonstrates complete freedom from the opinions of the world. Whether praised as a saint or condemned as a sinner, his inner state remains undisturbed, anchored in unshakeable peace.

                    The Nature of Non-Attachment: True practice does not scramble to defend the ego's reputation or react with anger to injustice. By meeting both slander and apology with the exact same response — "Is That So?" — Hakuin reveals a mind that accepts unfolding reality without grasping or resistance.
                """.trimIndent(),
                quiz = Quiz(
                    id = 18,
                    title = "Quiz: Koan Study: Is That So?",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "How did Hakuin initially respond when the angry parents accused him of fathering their daughter's child?",
                            options = listOf(
                                "He angrily denied the accusation and threatened legal action",
                                "'Is that so?' was all he would say",
                                "He immediately confessed and begged for mercy",
                                "He refused to speak to them and locked his door"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Hakuin calmly replied only with 'Is that so?', showing complete detachment from public opinion."
                        ),
                        Question(
                            id = 2,
                            text = "What did Hakuin say when the parents returned to apologize and take the child back?",
                            options = listOf(
                                "He scolded them severely for their previous harsh judgment",
                                "He refused to give the child back until they paid for the milk",
                                "He simply said 'Is that so?' and willingly yielded the child",
                                "He explained a long philosophical discourse on truth"
                            ),
                            correctOptionIndex = 2,
                            explanation = "True to his equanimity, Hakuin yielded the child with the exact same response: 'Is that so?'"
                        )
                    )
                )
            ),
            Lesson(
                id = 19,
                title = "Sutta Nipāta Book 4: How Opinion & Thought Contaminate the Mind",
                summary = "Exploring 'How Opinion & Thought Contaminate the Mind' from Book IV of the Sutta-Nipāta to understand how letting go of rigid beliefs, arguments, and pride leads to inner stillness.",
                content = """
                    HOW OPINION & THOUGHT CONTAMINATE THE MIND

                    Some people debate maliciously. Others honestly. But the wise are silent, stand back from arguments, keep the mind open.

                    How can you ever free the mind of its opinions if you let desire lead you and do exactly as you like?

                    You're bound by your own habits, can speak only what you know.

                    If you credit anything based on rational thought or fantasy you're not clear. They're part of the conditioned world. The benefit they give is shaky, built on sand.

                    To overcome habitual points of view is hard. You investigate them all, abandon some and choose a special one.

                    You argue to defend a point of view but if you're free of set beliefs no need! You've nothing to deny or to assert, quite purified right now of worldly views.

                    The wise have no such view about what is or what is not. They know both thought and pride are meaningless. Nothing defines them.

                    The wise see your failings if you blow your own trumpet, skite about your virtue and awareness but they acknowledge your virtue if you're calm, don't brag, are selfless and unworldly.
                """.trimIndent(),
                quiz = Quiz(
                    id = 19,
                    title = "Quiz: How Opinion & Thought Contaminate the Mind",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "How do the wise respond when others engage in malicious or honest debates?",
                            options = listOf(
                                "They argue passionately to prove their own view is superior",
                                "They are silent, stand back from arguments, and keep the mind open",
                                "They declare a special point of view to defeat both sides",
                                "They boast about their own awareness and virtue"
                            ),
                            correctOptionIndex = 1,
                            explanation = "The text states that 'the wise are silent, stand back from arguments, keep the mind open.'"
                        ),
                        Question(
                            id = 2,
                            text = "How does the text describe the benefit of relying on rational thought or fantasy?",
                            options = listOf(
                                "Permanent and completely reliable",
                                "Shaky, built on sand, and part of the conditioned world",
                                "The sole path to purifying worldly views",
                                "Essential for defending one's beliefs"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Rational thought and fantasy are part of the conditioned world, and 'the benefit they give is shaky, built on sand.'"
                        ),
                        Question(
                            id = 3,
                            text = "What qualities do the wise acknowledge as genuine virtue according to the final stanza?",
                            options = listOf(
                                "Blowing your own trumpet and skiting about your awareness",
                                "Being calm, not bragging, and being selfless and unworldly",
                                "Winning debates and defending set beliefs",
                                "Asserting views about what is or what is not"
                            ),
                            correctOptionIndex = 1,
                            explanation = "The wise acknowledge your virtue 'if you're calm, don't brag, are selfless and unworldly.'"
                        )
                    )
                )
            ),
            Lesson(
                id = 20,
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
                    id = 20,
                    title = "Quiz: Koan Study: A Cup of Tea",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What did Nan-in do when he poured tea for the visiting professor?",
                            options = listOf(
                                "He stopped pouring right before the cup was full",
                                "He poured the visitor's cup full, and then kept on pouring",
                                "He spilled the tea intentionally on the floor",
                                "He drank the tea himself without offering any to the professor"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Nan-in filled the professor's cup completely and then continued pouring until it overflowed."
                        ),
                        Question(
                            id = 2,
                            text = "What lesson did Nan-in impart through the overflowing cup?",
                            options = listOf(
                                "That tea must always be served piping hot",
                                "That the professor was full of his own opinions and speculations and needed to empty his cup to learn Zen",
                                "That university professors make poor students",
                                "That the teacup was broken and needed replacement"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Nan-in compared the professor's mind, full of opinions and speculations, to the overfull cup."
                        )
                    )
                )
            ),
            Lesson(
                id = 21,
                title = "Impermanence and Death",
                summary = "Contemplating the transience of life and physical existence to overcome worldly attachment and prioritize meaningful practice.",
                content = """
                    Impermanence and death remind us that every conditioned physical and mental state is temporary. Acknowledging that life is fleeting helps cut through superficial distractions and idle pursuits.

                    When we confront the reality of death honestly, we stop taking our time for granted. It inspires a strong sense of urgency to focus our lives on meaningful inner development, kindness, and wisdom.

                    Instead of causing despair, meditating on impermanence brings a liberating clarity, helping us let go of petty grievances and invest our energy into what truly matters.
                """.trimIndent(),
                quiz = Quiz(
                    id = 21,
                    title = "Quiz: Impermanence and Death",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What is the primary psychological benefit of contemplating impermanence and death?",
                            options = listOf(
                                "Creating persistent anxiety about the future",
                                "Helping us cut through distractions and prioritize meaningful practice",
                                "Encouraging total isolation from community life",
                                "Promoting endless accumulation of material wealth"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Contemplating transience cuts through trivial distractions and inspires meaningful focus."
                        ),
                        Question(
                            id = 2,
                            text = "How should meditating on the fleeting nature of life affect daily actions?",
                            options = listOf(
                                "It should inspire urgency to invest energy into kindness and wisdom",
                                "It should make a person indifferent to the well-being of others",
                                "It should encourage strict attachment to fixed routines",
                                "It should eliminate all emotional responses entirely"
                            ),
                            correctOptionIndex = 0,
                            explanation = "It creates a healthy urgency to cultivate positive qualities and inner growth."
                        )
                    )
                )
            ),
            Lesson(
                id = 22,
                title = "Koan Study: Finding a Diamond on a Muddy Road",
                summary = "Exploring Koan 2 from Zen Flesh, Zen Bones to understand how true spiritual practice meets suffering with unconditional compassion and recognizes the impermanence of worldly attachments.",
                content = """
                    The Koan Text:

                    "Gudo was the emperor's teacher of his time. Nevertheless, he used to travel alone as a wandering mendicant. Once when he was on his was to Edo, the cultural and political center of the shogunate, he approached a little village named Takenaka. It was evening and a heavy rain was falling. Gudo was thoroughly wet. His straw sandals were in pieces. At a farmhouse near the village he noticed four or five pairs of sandals in the window and decided to buy some dry ones.

                    The woman who offered him the sandals, seeing how wet he was, invited him in to remain for the night at her home. Gudo accepted, thanking her. He entered and recited a sutra before the family shrine. He then was introduced to the woman's mother, and to her children. Observing that the entire family was depressed, Gudo asked what was wrong.

                    'My husband is a gambler and a drunkard,' the housewife told him. 'When he happens to win he drinks and becomes abusive. When he loses he borrows money from others. Sometimes when he becomes thoroughly drunk he does not come home at all. What can I do?'

                    'I will help him,' said Gudo. 'Here is some money. Get me a gallon of fine wine and something good to eat. Then you may retire. I will meditate before the shrine.'

                    When the man of the house returned about midnight, quite drunk, he bellowed: 'Hey, wife, I am home. Have you something for me to eat?'

                    'I have something for you,' said Gudo. 'I happened to get caught in the rain and your wife kindly asked me to remain here for the night. In return I have bought some wine and fish, so you might as well have them.'

                    The man was delighted. He drank the wine at once and laid himself down on the floor. Gudo sat in meditation beside him.

                    In the morning when the husband awoke he had forgotten about the previous night. 'Who are you? Where do you come from?' he asked Gudo, who still was meditating.

                    'I am Gudo of Kyoto and I am going on to Edo,' replied the Zen master.

                    The man was utterly ashamed. He apologized profusely to the teacher of his emperor.

                    Gudo smiled. 'Everything in this life is impermanent,' he explained. 'Life is very brief. If you keep on gambling and drinking, you will have no time left to accomplish anything else, and you will cause your family to suffer too.'

                    The perception of the husband awoke as if from a dream. 'You are right,' he declared. 'How can I ever repay you for this wonderful teaching! Let me see you off and carry your things a little way.'

                    'If you wish,' assented Gudo.

                    The two started out. After they had gone three miles Gudo told him to return. 'Just another five miles,' he begged Gudo. They continued on.

                    'You may return now,' suggested Gudo.

                    'After another ten miles,' the man replied.

                    'Return now,' said Gudo, when the ten miles had been passed.

                    'I am going to follow you all the rest of my life,' declared the man.

                    Modern Zen teachers in Japan spring from the lineage of a famous master who was the successor of Gudo. His name was Mu-nan, the man who never turned back."

                    Meeting Suffering Without Condemnation: Rather than scolding the drunken husband or judging the troubled household, Gudo enters directly into their reality with radical hospitality and kindness. True wisdom does not isolate itself from human messiness; it meets distress with gentle, transformative presence.

                    The Awakening of Impermanence: Gudo’s concise reminder that life is brief and impermanent cuts through the illusions of destructive habits. Awakening is not found in an abstract realm, but right on the muddy roads of daily existence, turning despair into a dedicated path of transformation.
                """.trimIndent(),
                quiz = Quiz(
                    id = 22,
                    title = "Quiz: Koan Study: Finding a Diamond on a Muddy Road",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "How did the Zen master Gudo initially respond to the family's distress over the husband's behavior?",
                            options = listOf(
                                "He scolded the husband harshly for his lack of discipline",
                                "He offered money to buy food and wine, sitting in meditation to help him",
                                "He advised the wife to leave her husband immediately",
                                "He ignored their problems and focused solely on his own shelter"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Gudo offered money for food and wine, welcomed the husband warmly, and meditated beside him through the night."
                        ),
                        Question(
                            id = 2,
                            text = "What core realization prompted the husband to completely change his life the next morning?",
                            options = listOf(
                                "A severe punishment enforced by local authorities",
                                "The realization that life is impermanent and brief, leaving no time to waste on suffering",
                                "The fear of being reported to the emperor",
                                "A formal lecture on traditional moral philosophy"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Gudo gently explained that everything is impermanent and life is brief, awakening the man from his destructive patterns."
                        )
                    )
                )
            ),
            Lesson(
                id = 23,
                title = "Karma (Cause and Effect)",
                summary = "Understanding how intentional actions yield corresponding ethical and psychological results in human experience.",
                content = """
                    Karma refers to the universal principle of moral cause and effect. Every intentional action of body, speech, and mind leaves a deep impression and bears fruit in corresponding experiences.

                    Wholesome intentions rooted in generosity, kindness, and clarity lead naturally to inner peace and positive outcomes. Conversely, unwholesome actions driven by greed, hostility, or delusion generate friction and distress.

                    Understanding karma places personal responsibility squarely in our own hands. By observing our intentions and choosing constructive actions, we actively shape our future state of mind and well-being.
                """.trimIndent(),
                quiz = Quiz(
                    id = 23,
                    title = "Quiz: Karma (Cause and Effect)",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What is the core principle governing karma?",
                            options = listOf(
                                "Random luck and unpredictable fortune",
                                "Intentional actions bearing corresponding psychological and ethical results",
                                "Strict predetermination by external authorities",
                                "Complete independence from personal choices"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Karma is the principle that intentional actions generate corresponding consequences."
                        ),
                        Question(
                            id = 2,
                            text = "How does understanding karma empower a practitioner?",
                            options = listOf(
                                "By placing personal responsibility on their own intentions and actions",
                                "By guaranteeing immunity from physical sickness",
                                "By allowing them to control external weather patterns",
                                "By removing the need for mindfulness"
                            ),
                            correctOptionIndex = 0,
                            explanation = "It emphasizes personal accountability, showing that our choices shape our well-being."
                        )
                    )
                )
            ),
            Lesson(
                id = 24,
                title = "Koan Study: The Tunnel",
                summary = "Exploring Koan 60 from Zen Flesh, Zen Bones to examine how intentional action, remorse, and dedicated service can transform past karma and reconcile even the deepest enmities.",
                content = """
                    The Koan Text:

                    "Zenkai, the son of a samurai, journeyed to Edo and there became the retainer of a high official. He fell in love with the official's wife and was discovered. In self-defence, he slew the official. Then he ran away with the wife."

                    "Both of them later became thieves. But the woman was so greedy that Zenkai grew disgusted. Finally, leaving her, he journeyed far away to the province of Buzen, where he became a wandering mendicant."

                    "To atone for his past, Zenkai resolved to accomplish some good deed in his lifetime. Knowing of a dangerous road over a cliff that had caused death and injury to many persons, he resolved to cut a tunnel through the mountain there."

                    "Begging food in the daytime, Zenkai worked at night digging his tunnel. When thirty years had gone by, the tunnel was 2,280 feet long, 20 feet high, and 30 feet wide."

                    "Two years before the work was completed, the son of the official he had slain, who was a skillful swordsman, found Zenkai out and came to kill him in revenge."

                    "'I will give you my life willingly,' said Zenkai. 'Only let me finish this work. On the day it is completed, then you may kill me.'"

                    "So the son awaited the day. Several months passed and Zenkai kept digging. The son grew tired of doing nothing and began to help with the digging. After he had helped for more than a year, he came to admire Zenkai's strong will and character."

                    "At last the tunnel was completed and the people could use it and travel safely."

                    "'Now cut off my head,' said Zenkai. 'My work is done.'"

                    "'How can I cut off my own teacher's head?' asked the younger man with tears in his eyes."

                    Karmic Transformation through Service: Zenkai's life demonstrates how intentional action (karma) can be redirected. Burdened by grave past mistakes, he chose neither despair nor evasion, but devoted decades to an arduous act of public welfare that saved countless lives.

                    The Dissolution of Hatred: The vengeful son arrives seeking blood, yet encounters a man utterly transformed by unselfish dedication. Through shared labor and steadfast purpose, hatred melts into profound respect and discipleship, showing that true spiritual practice heals even the deepest cycles of retaliation.
                """.trimIndent(),
                quiz = Quiz(
                    id = 24,
                    title = "Quiz: Koan Study: The Tunnel",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "How did Zenkai choose to atone for the misdeeds of his past?",
                            options = listOf(
                                "By surrendering immediately to local authorities",
                                "By resolving to cut a massive tunnel through a dangerous mountain road to help travelers",
                                "By becoming a wealthy merchant and donating gold to temples",
                                "By wandering the country in absolute silence"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Zenkai dedicated decades to digging a 2,280-foot tunnel to protect travelers from a dangerous cliffside road."
                        ),
                        Question(
                            id = 2,
                            text = "What ultimately happened when the son of the slain official found Zenkai to exact revenge?",
                            options = listOf(
                                "Zenkai defeated him in a sword duel",
                                "The son joined him in digging the tunnel and eventually came to view him as a teacher",
                                "Local villagers intervened and drove the son away",
                                "Zenkai fled the province before the son could reach him"
                            ),
                            correctOptionIndex = 1,
                            explanation = "After waiting and eventually helping with the work, the son grew to admire Zenkai's character and refused to kill him."
                        )
                    )
                )
            ),
            Lesson(
                id = 25,
                title = "Developing Bodhicitta",
                summary = "Cultivating universal love, compassion, and the altruistic aspiration to attain full awakening for the benefit of all.",
                content = """
                    Bodhicitta is the heartfelt aspiration to achieve complete awakening specifically for the purpose of helping all living beings overcome distress.

                    This mindset shifts focus away from isolated self-interest. It expands the heart through boundless loving-kindness and deep empathy, recognizing that our well-being is entirely bound up with the welfare of others.

                    Cultivating bodhicitta transforms ordinary daily actions into a powerful path of universal service, infusing every task with warmth, purpose, and profound altruism.
                """.trimIndent(),
                quiz = Quiz(
                    id = 25,
                    title = "Quiz: Developing Bodhicitta",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What is the core definition of bodhicitta?",
                            options = listOf(
                                "The desire for personal fame and spiritual status",
                                "The altruistic aspiration to attain awakening for the benefit of all beings",
                                "The strict avoidance of all community engagement",
                                "The practice of physical endurance"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Bodhicitta is the altruistic intention to awaken for the welfare of everyone."
                        ),
                        Question(
                            id = 2,
                            text = "How does developing bodhicitta change a practitioner's perspective?",
                            options = listOf(
                                "It shifts focus from isolated self-interest to universal care",
                                "It encourages cold emotional detachment",
                                "It makes daily life more rigid and stressful",
                                "It eliminates the need for ethical conduct"
                            ),
                            correctOptionIndex = 0,
                            explanation = "It expands compassion outward, transforming personal practice into universal service."
                        )
                    )
                )
            ),
            Lesson(
                id = 26,
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
                    id = 26,
                    title = "Quiz: Engaging in the Bodhisattva Practices",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "Which of the following is included among the six perfections?",
                            options = listOf(
                                "Generosity, patience, and joyous effort",
                                "Competition, speed, and luxury",
                                "Isolation, silence, and fasting",
                                "Doubt, skepticism, and debate"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Generosity, patience, and joyous effort form part of the six perfections."
                        ),
                        Question(
                            id = 2,
                            text = "What is the overarching purpose of practicing the six perfections?",
                            options = listOf(
                                "To integrate deep insight with compassionate action in daily life",
                                "To accumulate personal praise and status",
                                "To avoid all physical labor",
                                "To win arguments against opponents"
                            ),
                            correctOptionIndex = 0,
                            explanation = "They bridge inner wisdom with active compassion for the world."
                        )
                    )
                )
            ),
            Lesson(
                id = 27,
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
                    id = 27,
                    title = "Quiz: Tsongkhapa and The Six Perfections",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What major Buddhist tradition did Je Tsongkhapa found in Tibet?",
                            options = listOf(
                                "The Gelug ('Virtuous') tradition",
                                "The Zen school of sudden awakening",
                                "The Shingon esoteric lineage",
                                "The Theravada forest tradition"
                            ),
                            correctOptionIndex = 0,
                            explanation = "Tsongkhapa founded the Gelug school, emphasizing rigorous study, ethics, and meditation."
                        ),
                        Question(
                            id = 2,
                            text = "What is the core focus of Tsongkhapa's famous work, the Lamrim Chenmo?",
                            options = listOf(
                                "A military history of medieval Tibet",
                                "Structuring the entire path to enlightenment into clear, progressive stages",
                                "A collection of short folk tales and poetry",
                                "Strict architectural designs for monastic temples"
                            ),
                            correctOptionIndex = 1,
                            explanation = "The Lamrim Chenmo outlines the graded stages of the path to awakening for practitioners."
                        ),
                        Question(
                            id = 3,
                            text = "According to Tsongkhapa's teachings, what two elements must be unified in practice?",
                            options = listOf(
                                "Wealth and political power",
                                "Method (compassion/perfections) and wisdom (emptiness)",
                                "Physical endurance and fasting",
                                "Ancient ritual and modern science"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Tsongkhapa emphasized the indispensable union of method (compassion) and wisdom (emptiness)."
                        )
                    )
                )
            ),
            Lesson(
                id = 28,
                title = "Koan Study: Reciting Sutras",
                summary = "Exploring Koan 24 from Zen Flesh, Zen Bones to examine how spiritual practice encompasses all living beings rather than harboring petty exclusions.",
                content = """
                    The Koan Text:

                    "A farmer requested a Tendai priest to recite sutras for his wife, who had died. After the recitation was over the farmer asked: 'Do you think my wife will gain merit from this?'

                    'Not only your wife, but all sentient beings will benefit from the recitation of sutras,' answered the priest.

                    'If you say all sentient beings will benefit,' said the farmer, 'my wife may be very weak and others will take advantage of her, getting the benefit she should have. So please recite sutras just for her.'

                    The priest explained that it was the desire of a Buddhist to offer blessings and wish merit for every living being.

                    'That is a fine teaching,' concluded the farmer, 'but please make one exception. I have a neighbor who is rough and mean to me. Just exclude him from all those sentient beings.'"

                    The Illusion of Separation: The farmer's practical, conditioned worldview exposes how easily our compassion remains shackled by personal grudges and competitive framing. He views spiritual merit as a finite commodity subject to scarcity and exploitation.

                    Universal Compassion: The priest's teaching underscores that true spiritual practice cannot be partitioned or doled out exclusively. Awakening and dedication to the welfare of others embrace the entire web of existence without petty exclusions.
                """.trimIndent(),
                quiz = Quiz(
                    id = 28,
                    title = "Quiz: Koan Study: Reciting Sutras",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What was the farmer's main concern after the priest recited sutras for his deceased wife?",
                            options = listOf(
                                "That the priest charged too much money for the ceremony",
                                "That other sentient beings might take advantage of his wife and get the benefit instead",
                                "That the priest used the wrong scripture",
                                "That his wife would not remember him in the next life"
                            ),
                            correctOptionIndex = 1,
                            explanation = "The farmer worried that other beings would take advantage of his wife and dilute the merit she was supposed to receive."
                        ),
                        Question(
                            id = 2,
                            text = "What exception did the farmer finally ask the priest to make regarding the recitation?",
                            options = listOf(
                                "To exclude his troublesome and mean neighbor from the benefit",
                                "To recite the sutras twice as loud",
                                "To refund half of his donation",
                                "To include his farm animals in the prayers"
                            ),
                            correctOptionIndex = 0,
                            explanation = "The farmer asked the priest to make an exception and exclude his rough neighbor from all sentient beings."
                        )
                    )
                )
            ),
            Lesson(
                id = 29,
                title = "Śāntideva and Bodhicitta",
                summary = "Explore the life and teachings of the 8th-century master Śāntideva, focusing on how true bodhicitta transforms the mind and overcomes self-cherishing.",
                content = """
                    The Legend of Śāntideva: 
                    
                    In 8th-century India at the great monastic university of Nalanda, Śāntideva appeared lazy to his peers. 
                    
                    He ate, slept, and strolled the grounds without participating in debates or chanting, earning the mocking nickname "Bhusuku" (one who only eats, sleeps, and walks). 
                    
                    To test him and force him to leave, the monks asked him to give a public discourse from the high teaching throne. They expected him to humiliate himself. 
                    
                    Instead, Śāntideva sat down and delivered the "Bodhicaryāvatāra" (Engaging in the Bodhisattva's Deeds)—a masterpiece of philosophy and poetry that left the assembly utterly transfixed. 
                    
                    As he reached the wisdom chapter on emptiness, he levitated into the air and vanished, leaving behind only his voice chanting the final verses.

                    The Core Message: 
                    
                    Śāntideva’s teachings focus on the immense power of Bodhicitta (the mind of awakening) and the dismantling of self-cherishing. 
                    
                    He famously noted that all the suffering in the world comes from seeking happiness for oneself, while all joy comes from wishing happiness for others.

                    Destroying the Real Enemy: 
                    
                    Śāntideva taught that our true enemy is not an external person or circumstance, but inner mental afflictions like anger, pride, and ego. 
                    
                    By learning to guard the mind against these internal states, we develop unshakeable patience and compassion.

                    Joyful Perseverance: 
                    
                    True practice requires joyous effort—not a grim, exhausting struggle, but an uplifting enthusiasm born from recognizing the immense value of living a compassionate life.
                """.trimIndent(),
                quiz = Quiz(
                    id = 29,
                    title = "Quiz: Śāntideva and Bodhicitta",
                    questions = listOf(
                        Question(
                            id = 1,
                            text = "What text did Śāntideva famously compose and deliver at Nalanda Monastery?",
                            options = listOf(
                                "The Dhammapada",
                                "The Bodhicaryāvatāra (Engaging in the Bodhisattva's Deeds)",
                                "The Heart Sutra",
                                "The Lamrim Chenmo"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Śāntideva delivered the Bodhicaryāvatāra, a timeless guide to the Bodhisattva's path."
                        ),
                        Question(
                            id = 2,
                            text = "According to Śāntideva's teachings, what is identified as the root source of worldly suffering?",
                            options = listOf(
                                "External weather events",
                                "Self-cherishing and ego-driven focus",
                                "A lack of physical exercise",
                                "Unavoidable bad luck"
                            ),
                            correctOptionIndex = 1,
                            explanation = "Śāntideva emphasized that self-cherishing drives our emotional friction and distress."
                        )
                    )
                )
            )
        )
    }

    fun getLessonById(id: Int): Lesson? {
        return getCurriculum().find { it.id == id }
    }

    fun getLessonCount(): Int {
        return getCurriculum().size
    }
}
