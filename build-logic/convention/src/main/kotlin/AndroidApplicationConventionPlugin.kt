import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import io.nia.configureGradleManagedDevices
import io.nia.configureKotlinAndroid
import io.nia.configurePrintApksTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("nia.android.lint")
                // apply("com.dropbox.dependency-guard")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                @Suppress("UnstableApiUsage")
                testOptions.animationsDisabled = true
                configureGradleManagedDevices(this)
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configurePrintApksTask(this)
                // configureBadgingTasks(extensions.getByType<BaseExtension>(), this)
            }
        }
    }

}
