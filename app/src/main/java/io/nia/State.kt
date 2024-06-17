package io.nia

import androidx.compose.runtime.Immutable

// Stable object
@Immutable
data class Email(
    val id: String,
    val subject: String,
    val body: String,
    val sender: String,
    val timestamp: String,
    val recipients: List<String>,
)
