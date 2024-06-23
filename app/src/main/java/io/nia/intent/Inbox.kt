@file:Suppress("DEPRECATED_ANNOTATION")

package io.nia.intent

import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import io.nia.Email
import io.nia.EmailRepository
import kotlinx.android.parcel.Parcelize

@Parcelize
data object InboxScreen : Screen {
    data class State(
        val emails: List<Email>,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        @JvmInline
        value class EmailClicked(val id: String) : Event
    }
}

sealed interface StyleA {
    data class Text(val title: String) : StyleA

    data class Icon(
        val title: String,
        val onClick: () -> Unit,
    ) : StyleA
}


@Composable
fun Main() {
    Component(style = StyleA.Text(""))
    Component(style = StyleA.Icon("") {})
}

@Composable
fun Component(
    style: StyleA,
) {
    Variants(style)
}


@Composable
private fun Variants(style: StyleA) = when (style) {
    is StyleA.Text -> Text(text = style.title)
    is StyleA.Icon -> {
        IconButton(onClick = style.onClick) {
            Text(text = style.title)
        }
    }
}


// class Content {
//     @Composable
//     operator fun invoke(e: InboxScreen.Event): @Composable () -> Unit {
//         return when (e) {
//             is InboxScreen.Event.EmailClicked -> { { Text("") } }
//         }
//     }
// }


class InboxPresenter(
    private val navigator: Navigator,
    private val emailRepository: EmailRepository,
) : Presenter<InboxScreen.State> {
    @Composable
    override fun present(): InboxScreen.State {
        val emails by produceState<List<Email>>(initialValue = emptyList()) {
            value = emailRepository.fetchEmails()
        }
        // Or a flow!
        // val emails by emailRepository.getEmailsFlow().collectAsState(initial = emptyList())
        return InboxScreen.State(emails) { event ->
            when (event) {
                is InboxScreen.Event.EmailClicked -> navigator.goTo(DetailScreen(event.id))
            }
        }
    }
}
