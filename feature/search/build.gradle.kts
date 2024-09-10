plugins {
    alias(libs.plugins.nia.android.feature)
    alias(libs.plugins.nia.android.library.compose)
    alias(libs.plugins.nia.android.library.jacoco)
}

android {
    namespace = "io.nia.feature.search"
}

dependencies {
    // implementation(projects.core.data)
    // implementation(projects.core.domain)

    // testImplementation(projects.core.testing)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    // androidTestImplementation(projects.core.testing)
}
