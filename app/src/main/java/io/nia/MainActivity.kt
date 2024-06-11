@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("DEPRECATED_ANNOTATION")

package io.nia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import io.nia.ui.theme.NiaTheme
import kotlinx.android.parcel.Parcelize

interface EmailRepository {
    fun getEmails(): List<Email>
}

@Immutable
data class Email(
    val id: String,
    val subject: String,
    val body: String,
    val sender: String,
    val timestamp: String, val recipients: List<String>,
)

// Screen
@Parcelize
data object InboxScreen : Screen {
    data class State(
        val emails: List<Email>
    ) : CircuitUiState
}

// Presenter
class InboxPresenter(private val emailRepository: EmailRepository) : Presenter<InboxScreen.State> {
    @Composable
    override fun present(): InboxScreen.State {
        val emails by produceState<List<Email>>(initialValue = emptyList()) {
            value = emailRepository.getEmails()
        }
        // Or a flow!
        // val emails by emailRepository.getEmailsFlow().collectAsState(initial = emptyList())
        return InboxScreen.State(emails)
    }
}


//
@Composable
fun Inbox(state: InboxScreen.State, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text("Inbox") }) }) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(state.emails) { email ->
                EmailItem(email)
            }
        }
    }
}

// Write one or use EmailItem from ui.kt
@Composable
private fun EmailItem(email: Email, modifier: Modifier = Modifier) {
    // ...
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NiaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NiaTheme {
        Greeting("Android")
    }
}