package io.nia.feature.foryou

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp

@Composable
fun ForYouScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(StaggeredGridCells.Adaptive(300.dp)) {
            item {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

private fun LazyStaggeredGridScope.onboarding(
    onboardingUiState: OnboardingUiState,
) {
    when (onboardingUiState) {
        is OnboardingUiState.Shown -> {
            item {
                Column {
                    Text(
                        text = stringResource(R.string.feature_foryou_onboarding_guidance_title),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = stringResource(R.string.feature_foryou_onboarding_guidance_subtitle),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 24.dp, end = 24.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    TopicSelection(onboardingUiState)
                }
            }
        }

        else -> Unit
    }
}

@Composable
private fun TopicSelection(
    onboardingUiState: OnboardingUiState.Shown,
) {

    val lazyGridState = rememberLazyGridState()

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyHorizontalGrid(
            state = lazyGridState,
            rows = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier
                .heightIn(max = max(240.dp, with(LocalDensity.current) { 240.sp.toDp() }))
        ) {
            items(
                items = onboardingUiState.topics,
                key = { it.topic.id },
            ) {
                Text(text = "Vertical text that can be rescaled!")
            }
        }
    }
}


sealed interface OnboardingUiState {
    data object Loading : OnboardingUiState
    data object LoadFailed : OnboardingUiState
    data object NotShown : OnboardingUiState
    data class Shown(
        val topics: List<FollowableTopic>,
    ) : OnboardingUiState {
        /**
         * True if the onboarding can be dismissed.
         */
        val isDismissable: Boolean get() = topics.any { it.isFollowed }
    }
}

data class FollowableTopic(
    val topic: Topic,
    val isFollowed: Boolean,
)

data class Topic(
    val id: String,
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val url: String,
    val imageUrl: String,
)
