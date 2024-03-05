import br.com.bit.guardian.convention.core.configureFeatureDependence
import br.com.bit.guardian.convention.core.configureFlavors
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("guardian.android.library")
                apply("guardian.android.library.compose")
                apply("guardian.android.library.jacoco")
            }

            extensions.configure<LibraryExtension> {
                configureFlavors(this)
                configureFeatureDependence(this)

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildTypes {
                    getByName("debug"){
                        enableAndroidTestCoverage = true
                        enableUnitTestCoverage = true
                    }
                }
            }
        }
    }
}