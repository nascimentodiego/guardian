
import br.com.bit.guardian.convention.core.configureDetekt
import dev.detekt.gradle.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationDeteKtConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("dev.detekt")

            val extension = extensions.getByType<DetektExtension>()
            configureDetekt(extension)
        }
    }

}
