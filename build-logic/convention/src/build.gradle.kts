import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.google.samples.apps.nowinandroid.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    // compileOnly(libs.android.gradlePlugin)
    // compileOnly(libs.android.tools.common)
    // compileOnly(libs.compose.gradlePlugin)
    // compileOnly(libs.firebase.crashlytics.gradlePlugin)
    // compileOnly(libs.firebase.performance.gradlePlugin)
    // compileOnly(libs.kotlin.gradlePlugin)
    // compileOnly(libs.ksp.gradlePlugin)
    // compileOnly(libs.room.gradlePlugin)
    // implementation(libs.truth)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

// gradlePlugin {
//     plugins {
//         register("androidApplicationCompose") {
//             id = "nowinandroid.android.application.compose"
//             implementationClass = "AndroidApplicationComposeConventionPlugin"
//         }
//         register("androidApplication") {
//             id = "nowinandroid.android.application"
//             implementationClass = "AndroidApplicationConventionPlugin"
//         }
//         register("androidApplicationJacoco") {
//             id = "nowinandroid.android.application.jacoco"
//             implementationClass = "AndroidApplicationJacocoConventionPlugin"
//         }
//         register("androidLibraryCompose") {
//             id = "nowinandroid.android.library.compose"
//             implementationClass = "AndroidLibraryComposeConventionPlugin"
//         }
//         register("androidLibrary") {
//             id = "nowinandroid.android.library"
//             implementationClass = "AndroidLibraryConventionPlugin"
//         }
//         register("androidFeature") {
//             id = "nowinandroid.android.feature"
//             implementationClass = "AndroidFeatureConventionPlugin"
//         }
//         register("androidLibraryJacoco") {
//             id = "nowinandroid.android.library.jacoco"
//             implementationClass = "AndroidLibraryJacocoConventionPlugin"
//         }
//         register("androidTest") {
//             id = "nowinandroid.android.test"
//             implementationClass = "AndroidTestConventionPlugin"
//         }
//         register("hilt") {
//             id = "nowinandroid.hilt"
//             implementationClass = "HiltConventionPlugin"
//         }
//         register("androidRoom") {
//             id = "nowinandroid.android.room"
//             implementationClass = "AndroidRoomConventionPlugin"
//         }
//         register("androidFirebase") {
//             id = "nowinandroid.android.application.firebase"
//             implementationClass = "AndroidApplicationFirebaseConventionPlugin"
//         }
//         register("androidFlavors") {
//             id = "nowinandroid.android.application.flavors"
//             implementationClass = "AndroidApplicationFlavorsConventionPlugin"
//         }
//         register("androidLint") {
//             id = "nowinandroid.android.lint"
//             implementationClass = "AndroidLintConventionPlugin"
//         }
//         register("jvmLibrary") {
//             id = "nowinandroid.jvm.library"
//             implementationClass = "JvmLibraryConventionPlugin"
//         }
//     }
// }
