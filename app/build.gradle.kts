plugins {
    alias(libs.plugins.nia.android.application)
    alias(libs.plugins.nia.android.application.compose)
    alias(libs.plugins.nia.android.application.flavors)
    alias(libs.plugins.nia.android.application.jacoco)
    // alias(libs.plugins.nia.android.application.firebase)
    alias(libs.plugins.nia.hilt)
    // id("com.google.android.gms.oss-licenses-plugin")
    // alias(libs.plugins.baselineprofile)
    // alias(libs.plugins.roborazzi)
    alias(libs.plugins.kotlin.serialization)
}

android {
    defaultConfig {
        applicationId = "io.nia"
        versionCode = 0
        versionName = "0.1.2" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        // Custom test runner to set up Hilt dependency graph
        testInstrumentationRunner = "io.nia.core.testing.NiaTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            //applicationIdSuffix = NiaBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = true
            //applicationIdSuffix = NiaBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.named("debug").get()
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "io.nia"
}

dependencies {
    implementation(projects.feature.interests)
    implementation(projects.feature.foryou)
    implementation(projects.feature.bookmarks)
    implementation(projects.feature.topic)
    implementation(projects.feature.search)
    implementation(projects.feature.settings)

    // implementation(projects.core.common)
    // implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    // implementation(projects.core.data)
    // implementation(projects.core.model)
    // implementation(projects.core.analytics)
    // implementation(projects.sync.work)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    // implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.core.ktx)
    // implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    // implementation(libs.androidx.profileinstaller)
    // implementation(libs.androidx.tracing.ktx)
    // implementation(libs.androidx.window.core)
    // implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)
    implementation(libs.kotlinx.serialization.json)

    ksp(libs.hilt.compiler)

    debugImplementation(libs.androidx.compose.ui.testManifest)
    // debugImplementation(projects.uiTestHiltManifest)

    kspTest(libs.hilt.compiler)

    // testImplementation(projects.core.dataTest)
    testImplementation(libs.hilt.android.testing)
    // testImplementation(projects.sync.syncTest)

    // testDemoImplementation(libs.robolectric)
    // testDemoImplementation(libs.roborazzi)
    // testDemoImplementation(projects.core.screenshotTesting)

    androidTestImplementation(kotlin("test"))
    // androidTestImplementation(projects.core.testing)
    // androidTestImplementation(projects.core.dataTest)
    // androidTestImplementation(projects.core.datastoreTest)
    // androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.hilt.android.testing)

    // baselineProfile(projects.benchmarks)
}

// baselineProfile {
//     // Don't build on every iteration of a full assemble.
//     // Instead enable generation directly for the release build variant.
//     automaticGenerationDuringBuild = false
// }

// dependencyGuard {
//     configuration("prodReleaseRuntimeClasspath")
// }