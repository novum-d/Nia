package io.nia.core.model

data class UserSearchResult(
    val topics: List<FollowableTopic> = emptyList(),
    val newsResources: List<UserNewsResource> = emptyList(),
)
