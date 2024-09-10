plugins {
    alias(libs.plugins.nia.android.feature)
    alias(libs.plugins.nia.android.library.compose)
    alias(libs.plugins.nia.android.library.jacoco)
}

android {
    namespace = "com.google.samples.apps.nowinandroid.feature.settings"
}

dependencies {
    implementation(libs.androidx.appcompat)
    // implementation(libs.google.oss.licenses)
    // implementation(projects.core.data)

    // testImplementation(projects.core.testing)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
}
