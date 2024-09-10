plugins {
    alias(libs.plugins.nia.android.feature)
    alias(libs.plugins.nia.android.library.compose)
    alias(libs.plugins.nia.android.library.jacoco)
    // alias(libs.plugins.roborazzi)
}

android {
    namespace = "io.nia.feature.foryou"
}

dependencies {
    // implementation(libs.accompanist.permissions)
    // implementation(projects.core.data)
    // implementation(projects.core.domain)
    // implementation(project(":core:notifications"))

    testImplementation(libs.hilt.android.testing)
    // testImplementation(libs.robolectric)
    // testImplementation(projects.core.testing)
    // testDemoImplementation(projects.core.screenshotTesting)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    // androidTestImplementation(projects.core.testing)
}
