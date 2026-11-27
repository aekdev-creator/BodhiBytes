package dev.alex.bodhibytes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import dev.alex.bodhibytes.data.LessonRepository
import dev.alex.bodhibytes.ui.navigation.BodhiBytesApp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            LessonRepository.getCurriculum(applicationContext)
        }
        setContent {
            BodhiBytesApp()
        }
    }
}
