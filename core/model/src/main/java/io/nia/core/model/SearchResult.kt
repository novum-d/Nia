package io.nia.core.model

data class SearchResult(
    val topics: List<Topic> = emptyList(),
    val newsResources: List<NewsResource> = emptyList(),
)
