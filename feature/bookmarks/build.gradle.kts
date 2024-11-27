plugins {
    alias(libs.plugins.nia.android.feature)
    alias(libs.plugins.nia.android.library.compose)
    alias(libs.plugins.nia.android.library.jacoco)
}

android {
    namespace = "io.nia.feature.bookmarks"
}

dependencies {
    // implementation(projects.core.data)

    // testImplementation(projects.core.testing)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    // androidTestImplementation(projects.core.testing)
}
