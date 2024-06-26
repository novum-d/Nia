package io.nia.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import io.nia.intent.DetailScreen
import io.nia.intent.InboxScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    state: CircuitUiState,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    val title = when (state) {
                        is InboxScreen.State -> "Inbox"
                        is DetailScreen.State -> "Detail"
                        else -> error("Expected screen is not exists.")
                    }
                    Text(title)
                },
                navigationIcon = {
                    if (navigator.peekBackStack().size > 1) {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        when (state) {
            is InboxScreen.State -> InboxList(state, modifier.padding(innerPadding))
            is DetailScreen.State -> EmailDetail(state.email, modifier.padding(innerPadding))
        }
    }
}
