import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.sonarqube.gradle.SonarExtension

class AndroidSonarConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.sonarqube")

            extensions.configure<SonarExtension> {
                properties {
                    property("sonar.projectKey", "nascimentodiego_guardian")
                    property("sonar.organization", "nascimentodiego")
                    property("sonar.host.url", "https://sonarcloud.io")

                    property("sonar.androidVariant", "debug")

                    property(
                        "sonar.coverage.jacoco.xmlReportPaths",
                        "**/build/reports/coverage/jacoco/**/*.xml",
                    )
                    property(
                        "sonar.junit.reportPaths",
                        "**/build/test-results/**/TEST-*.xml",
                    )

                    // ── Source exclusions ─────────────────────────────────
                    // Test source sets are NOT counted as production code at all
                    property(
                        "sonar.exclusions",
                        listOf(
                            "**/build/**",
                            "**/*.generated.*",
                            "**/R.class",
                            "**/R\$*.class",
                            "**/BuildConfig.*",
                            "**/Manifest*.*",
                            "**/*_Hilt*.class",
                            "**/*Dagger*.class",
                            "**/*_Factory*.class",
                            "**/*_MembersInjector*.class",
                            "**/*Module_Provide*.class",
                            "**/*Component*.class",
                            "**/databinding/**",
                            "**/di/module/**",
                            "**/build-config/**",
                            // Test source sets
                            "**/src/test/**",
                            "**/src/androidTest/**",
                            "**/src/screenshotTest/**",
                            "**/src/screenshotTestDebug/**",
                        ).joinToString(","),
                    )

                    // ── Coverage exclusions ────────────────────────────────
                    // Code that should not be measured for test coverage
                    property(
                        "sonar.coverage.exclusions",
                        listOf(
                            "**/R.class",
                            "**/R\$*.class",
                            "**/BuildConfig.*",
                            "**/Manifest*.*",
                            "**/*_Hilt*",
                            "**/*Dagger*",
                            "**/*_Factory*",
                            "**/*_MembersInjector*",
                            "**/*Module_Provide*",
                            "**/*Component*",
                            "**/databinding/**",
                            "**/di/module/**",
                            "**/build-config/**",
                            // Test source sets
                            "**/src/test/**",
                            "**/src/androidTest/**",
                            "**/src/screenshotTest/**",
                            "**/src/screenshotTestDebug/**",
                        ).joinToString(","),
                    )

                    // ── Test inclusions ───────────────────────────────────
                    // Tells Sonar where test files live (for proper categorization)
                    property(
                        "sonar.tests",
                        listOf(
                            "src/test",
                            "src/androidTest",
                            "src/screenshotTest",
                            "src/screenshotTestDebug",
                        ).joinToString(","),
                    )

                    // ── Duplication exclusions ────────────────────────────
                    property(
                        "sonar.cpd.exclusions",
                        listOf(
                            "**/databinding/**",
                            "**/*Binding.*",
                            "**/build-config/**",
                            "**/src/test/**",
                            "**/src/androidTest/**",
                            "**/src/screenshotTest/**",
                            "**/src/screenshotTestDebug/**",
                        ).joinToString(","),
                    )

                    property("sonar.qualitygate.wait", "true")
                    property("sonar.qualitygate.timeout", "300")
                }
            }
        }
    }
}
