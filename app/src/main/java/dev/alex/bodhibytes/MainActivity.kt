package dev.alex.bodhibytes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.alex.bodhibytes.ui.navigation.BodhiBytesApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BodhiBytesApp()
        }
    }
}
