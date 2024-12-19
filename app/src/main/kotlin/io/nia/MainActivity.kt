package io.nia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.nia.core.designsystem.theme.NiaTheme
import io.nia.ui.theme.NiaApp
import io.nia.ui.theme.rememberNiaAppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContent {
            NiaTheme {
                val appState = rememberNiaAppState()
                NiaApp(appState)
            }
        }
    }
}