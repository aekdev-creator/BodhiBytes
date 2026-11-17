package dev.alex.bodhibytes.data

import android.content.Context
import android.content.SharedPreferences

class UserProgressManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("bodhi_bytes_progress", Context.MODE_PRIVATE)

    fun saveQuizResult(quizId: Int, score: Int, totalQuestions: Int) {
        val editor = prefs.edit()
        
        // Save best or latest score for this specific quiz
        val previousScore = prefs.getInt("quiz_score_$quizId", 0)
        if (score > previousScore) {
            editor.putInt("quiz_score_$quizId", score)
        }
        
        editor.putInt("quiz_total_$quizId", totalQuestions)
        editor.putBoolean("quiz_completed_$quizId", true)

        // Accumulate total lifetime score
        val lifetimeScore = prefs.getInt("lifetime_score", 0) + score
        editor.putInt("lifetime_score", lifetimeScore)
        
        editor.apply()
    }

    fun getLifetimeScore(): Int = prefs.getInt("lifetime_score", 0)

    fun isQuizCompleted(quizId: Int): Boolean = prefs.getBoolean("quiz_completed_$quizId", false)

    fun getQuizScore(quizId: Int): Pair<Int, Int> {
        val score = prefs.getInt("quiz_score_$quizId", 0)
        val total = prefs.getInt("quiz_total_$quizId", 0)
        return Pair(score, total)
    }
}
