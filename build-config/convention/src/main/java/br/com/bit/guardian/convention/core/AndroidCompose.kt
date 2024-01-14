package br.com.bit.guardian.convention.core

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.io.File

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion("androidxComposeCompiler").get().toString()
        }

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))


            add(
                "api",
                libs.findLibrary("androidx-appcompat").get()
            )
            add(
                "api",
                libs.findLibrary("androidx-core-ktx").get()
            )
            add(
                "api",
                libs.findLibrary("androidx-lifecycle-runtime-ktx").get()
            )

            add(
                "debugApi",
                libs.findLibrary("androidx-compose-ui-tooling").get()
            )

            add(
                "debugApi",
                libs.findLibrary("androidx-compose-ui-testManifest").get()
            )

            add(
                "api",
                libs.findLibrary("androidx-compose-ui-tooling-preview").get()
            )
            add(
                "api",
                libs.findLibrary("androidx-compose-ui-util").get()
            )
            add(
                "api",
                libs.findLibrary("androidx-compose-runtime").get()
            )
            add(
                "api",
                libs.findLibrary("androidx-compose-runtime").get()
            )
            add(
                "api",
                libs.findLibrary("androidx-compose-material3").get()
            )
            add(
                "api",
                libs.findLibrary("androidx-activity-compose").get()
            )
            add(
                "testApi",
                libs.findLibrary("androidx-compose-ui-test").get()
            )
            add(
                "androidTestApi",
                libs.findLibrary("androidx-compose-ui-test").get()
            )
            add(
                "androidTestApi",
                libs.findLibrary("androidx-test-espresso-core").get()
            )
        }
    }
}

private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = File(project.buildDir, "compose-metrics")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = File(project.buildDir, "compose-reports")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}
