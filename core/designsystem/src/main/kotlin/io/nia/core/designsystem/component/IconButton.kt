package io.nia.core.designsystem.component

import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.nia.core.designsystem.TogglePreviewParameterProvider
import io.nia.core.designsystem.icon.NiaIcons
import io.nia.core.designsystem.theme.NiaTheme

@Composable
fun NiaIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    checkedIcon: @Composable () -> Unit = icon,
) {
    FilledIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconToggleButtonColors(
            checkedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            checkedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = if (checked) {
                MaterialTheme.colorScheme.onBackground.copy(
                    alpha = NiaIconButtonDefaults.DISABLED_ICON_BUTTON_ALPHA
                )
            } else {
                Color.Transparent
            },
        ),
    ) {
        if (checked) checkedIcon() else icon()
    }
}

@ThemePreviews
@Composable
fun IconButtonPreview(
    @PreviewParameter(TogglePreviewParameterProvider::class) checked: Boolean,
) {
    NiaTheme {
        NiaIconToggleButton(
            checked = checked,
            onCheckedChange = { },
            icon = {
                Icon(
                    imageVector = NiaIcons.BookmarkBorder,
                    contentDescription = null,
                )
            },
            checkedIcon = {
                Icon(
                    imageVector = NiaIcons.Bookmark,
                    contentDescription = null,
                )
            },
        )
    }
}

object NiaIconButtonDefaults {
    const val DISABLED_ICON_BUTTON_ALPHA = 0.12f
}