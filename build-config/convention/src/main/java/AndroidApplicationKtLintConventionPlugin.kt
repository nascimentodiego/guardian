import br.com.bit.guardian.convention.core.configureKtLint
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationKtLintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")

            val extension = extensions.getByType<KtlintExtension>()
            configureKtLint(extension)
        }
    }

}