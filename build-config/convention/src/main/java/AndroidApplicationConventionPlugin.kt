import br.com.bit.guardian.convention.core.configureFlavors
import br.com.bit.guardian.convention.core.configureKotlinAndroid
import br.com.bit.guardian.convention.core.configureLintAndroid
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("guardian.android.application.ktlint")
                apply("guardian.android.application.jacoco")
                apply("guardian.android.application.detekt")
                apply("guardian.android.hilt")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureLintAndroid(this)
                defaultConfig.targetSdk = 34

                configureFlavors(this)
            }
        }
    }
}