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

            pluginManager.apply("guardian.android.library.jacoco")
            
            extensions.getByType<LibraryExtension>()
                .experimentalProperties["android.experimental.enableScreenshotTest"] = true

            pluginManager.apply("com.android.compose.screenshot")

            extensions.configure<LibraryExtension> {
                testOptions {
                    unitTests.isReturnDefaultValues = true
                    unitTests.isIncludeAndroidResources = true
                    unitTests.all { test ->
                        test.jvmArgs(
                            "-Xverify:none",
                            "--add-opens", "java.base/java.lang=ALL-UNNAMED",
                            "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED",
                            "--add-opens", "java.base/java.io=ALL-UNNAMED",
                            "--add-opens", "java.base/java.util=ALL-UNNAMED",
                        )
                    }
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("testImplementation", libs.findLibrary("junit4").get())
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
                add("testImplementation", libs.findLibrary("robolectric").get())
                add("testImplementation", project(":core:test"))
                add(
                    "screenshotTestImplementation",
                    libs.findLibrary("screenshot-validation-api").get()
                )
                add("screenshotTestImplementation", libs.findLibrary("androidx-ui-tooling").get())
            }
        }
    }
}
