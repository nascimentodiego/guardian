import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.getByType<LibraryExtension>()
                .experimentalProperties["android.experimental.enableScreenshotTest"] = true

            pluginManager.apply("com.android.compose.screenshot")

            extensions.configure<LibraryExtension> {
                testOptions {
                    unitTests.isReturnDefaultValues = true
                    unitTests.isIncludeAndroidResources = true
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("testImplementation", libs.findLibrary("junit4").get())
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
                add("testImplementation", project(":core:test"))
                add("screenshotTestImplementation", libs.findLibrary("screenshot-validation-api").get())
                add("screenshotTestImplementation", libs.findLibrary("androidx-ui-tooling").get())
            }
        }
    }
}
