package io.nia.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import io.nia.core.designsystem.R

@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(R.drawable.core_designsystem_ic_placeholder_default),
) {
    val iconTint = LocalTintTheme.current.iconTint
    val (isLoading, setLoading) = remember { mutableStateOf(true) }
    val (isError, setError) = remember { mutableStateOf(true) }
    val imageLoader = rememberAsyncImagePainter(
        model = imageUrl,
        onState = { state ->
            setLoading(state is AsyncImagePainter.State.Loading)
            setError(state is AsyncImagePainter.State.Error)
        },
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading && isLocalInspection.not()) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
            )
        }

        Image(
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && isLocalInspection.not()) imageLoader else placeholder,
            contentDescription = contentDescription,
            colorFilter = if (iconTint != Color.Unspecified) ColorFilter.tint(iconTint) else null,
        )
    }
}

/**
 * A class to model background color and tonal elevation values for Now in Android.
 */
@Immutable
data class TintTheme(
    val iconTint: Color = Color.Unspecified,
)

/**
 * A composition local for [TintTheme].
 */
val LocalTintTheme = staticCompositionLocalOf { TintTheme() }

val LocalTintThemeSwitcher = staticCompositionLocalOf<(TintTheme) -> Unit> { {} }
