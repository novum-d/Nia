@file:Suppress("DEPRECATED_ANNOTATION")

package io.nia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import io.nia.intent.DetailPresenter
import io.nia.intent.DetailScreen
import io.nia.intent.InboxPresenter
import io.nia.intent.InboxScreen
import io.nia.ui.component.AppBar
import io.nia.ui.theme.NiaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val emailRepository = EmailRepositoryImpl()

        setContent {
            val backStack = rememberSaveableBackStack(root = InboxScreen)
            val navigator = rememberCircuitNavigator(backStack)
            val presenter = InboxPresenter(
                navigator = navigator,
                emailRepository = emailRepository
            )
            val circuit = Circuit.Builder()
                .addPresenter<InboxScreen, InboxScreen.State>(presenter)
                .addUi<InboxScreen, InboxScreen.State> { state, modifier -> AppBar(state, navigator, modifier) }
                .addPresenterFactory(DetailPresenter.Factory(emailRepository))
                .addUi<DetailScreen, DetailScreen.State> { state, modifier -> AppBar(state, navigator, modifier) }
                .build()
            CircuitCompositionLocals(circuit) {
                NiaTheme {
                    NavigableCircuitContent(navigator, backStack)
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