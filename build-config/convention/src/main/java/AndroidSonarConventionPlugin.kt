import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.sonarqube.gradle.SonarExtension

class AndroidSonarConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.sonarqube")

            // Modules to skip entirely from Sonar analysis — these contain no
            // production code (testing infrastructure, convention plugins, etc.)
            val skippedModules = setOf(
                ":core:test",
                ":build-config:convention",
            )

            subprojects {
                // Skip configured modules entirely
                if (path in skippedModules) {
                    pluginManager.withPlugin("org.sonarqube") {
                        extensions.configure<SonarExtension> {
                            isSkipProject = true
                        }
                    }
                    return@subprojects
                }

                // Configure sonar.sources per Android submodule to support both
                // src/main/java (default AGP) and src/main/kotlin (used in some modules
                // like :feature:registration). Without this, modules with sources only
                // in src/main/kotlin show up as "no coverage" in the dashboard because
                // Sonar can't find the source files to associate with JaCoCo data.
                pluginManager.withPlugin("com.android.library") {
                    pluginManager.withPlugin("org.sonarqube") {
                        extensions.configure<SonarExtension> {
                            properties {
                                property("sonar.sources", "src/main/java,src/main/kotlin")
                            }
                        }
                    }
                }
                pluginManager.withPlugin("com.android.application") {
                    pluginManager.withPlugin("org.sonarqube") {
                        extensions.configure<SonarExtension> {
                            properties {
                                property("sonar.sources", "src/main/java,src/main/kotlin")
                            }
                        }
                    }
                }
            }

            extensions.configure<SonarExtension> {
                properties {
                    property("sonar.projectKey", "nascimentodiego_guardian")
                    property("sonar.organization", "nascimentodiego")
                    property("sonar.host.url", "https://sonarcloud.io")

                    property("sonar.androidVariant", "debug")

                    // Disable Android Lint import — project uses Detekt + Ktlint instead
                    property("sonar.androidLint.reportPaths", "")

                    // Paths are relative to each submodule — Sonar applies them per-module.
                    // Avoid glob patterns like "**/build/..." here, as they can produce
                    // unexpected results in multi-module projects.
                    property(
                        "sonar.coverage.jacoco.xmlReportPaths",
                        "build/reports/coverage/jacoco/debug.xml",
                    )
                    property(
                        "sonar.junit.reportPaths",
                        "build/test-results/testDebugUnitTest",
                    )

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
                            "**/src/test/**",
                            "**/src/androidTest/**",
                            "**/src/screenshotTest/**",
                            "**/src/screenshotTestDebug/**",
                        ).joinToString(","),
                    )

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
                            "**/src/test/**",
                            "**/src/androidTest/**",
                            "**/src/screenshotTest/**",
                            "**/src/screenshotTestDebug/**",
                        ).joinToString(","),
                    )

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
