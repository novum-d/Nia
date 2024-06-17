package io.nia.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.nia.intent.InboxScreen
import io.nia.intent.InboxScreen.Event.EmailClicked


@Composable
fun InboxList(
    state: InboxScreen.State,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(state.emails, key = { i -> i.id }) { email ->
            EmailItem(
                email = email,
                onClick = { state.eventSink(EmailClicked(email.id)) },
            )
        }
    }
}
