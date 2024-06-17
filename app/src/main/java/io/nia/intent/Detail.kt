@file:Suppress("DEPRECATED_ANNOTATION")

package io.nia.intent

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import io.nia.Email
import io.nia.EmailRepository
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailScreen(val emailId: String) : Screen {
    data class State(
        val email: Email,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object BackClicked : Event
    }
}

internal class DetailPresenter(
    private val screen: DetailScreen,
    private val navigator: Navigator,
    private val emailRepository: EmailRepository,
) : Presenter<DetailScreen.State> {

    @Composable
    override fun present(): DetailScreen.State {
        val email = emailRepository.fetchEmail(screen.emailId)
        return DetailScreen.State(email) { event ->
            when (event) {
                DetailScreen.Event.BackClicked -> navigator.pop()
            }
        }
    }

    internal class Factory(private val emailRepository: EmailRepository) : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext
        ): Presenter<*>? {
            return when (screen) {
                is DetailScreen -> return DetailPresenter(screen, navigator, emailRepository)
                else -> null
            }
        }
    }
}
