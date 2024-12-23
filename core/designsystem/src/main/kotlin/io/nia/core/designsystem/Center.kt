package io.nia.core.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun Center(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}


class TogglePreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = PreviewParameterData.toggle
}

object PreviewParameterData {
    val toggle = sequenceOf(true, false)
}
