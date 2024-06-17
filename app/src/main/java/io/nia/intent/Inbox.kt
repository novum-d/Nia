@file:Suppress("DEPRECATED_ANNOTATION")

package io.nia.intent

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
