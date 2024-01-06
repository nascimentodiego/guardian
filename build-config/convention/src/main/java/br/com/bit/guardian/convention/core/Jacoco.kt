package br.com.bit.guardian.convention.core

import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

private val coverageExclusions = listOf(
    // Android
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*"
)

internal fun Project.configureJacoco(
    androidComponentsExtension: AndroidComponentsExtension<*, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacoco").get().toString()
        reportsDirectory.set(file("$buildDir/reports/coverage"))
    }

    val jacocoTestReport = tasks.create("jacocoIntegratedTestReport")

    androidComponentsExtension.onVariants { variant ->

        val variantName = variant.name.capitalized()
        val flavorName = variant.flavorName?.capitalized() ?: "demo"
        val buildTypeName = variant.buildType ?: "debug"

        if (buildTypeName != "debug" || flavorName == "Demo")
            return@onVariants

        val testTaskName = "test${variantName}UnitTest"
        val instrumentedTestTaskName = "create${variantName}CoverageReport"

        val reportTask = tasks.register(
            "jacoco${testTaskName.capitalized()}Report", JacocoReport::class,
        ) {
            group = "coverage-report"
            description = "Generate Jacoco Report to $variantName"

            dependsOn(testTaskName)
            //To enable Instrumentation report
//            dependsOn(testTaskName, instrumentedTestTaskName)

            reports {
                csv.required.set(false)
                xml.required.set(true)
                html.required.set(true)
                xml.outputLocation.set(
                    file("$buildDir/reports/coverage/jacoco/${variant.flavorName}/$buildTypeName.xml")
                )
            /*
                html.outputLocation
                    .set(
                        file(
                            "$buildDir/reports/coverage/unitTest/${variant.flavorName}/$buildTypeName"
                        )
                    )
            */

            }

            classDirectories.setFrom(
                fileTree("$buildDir/tmp/kotlin-classes/${variantName}") {
                    exclude(coverageExclusions)
                },
                fileTree("$buildDir/intermediates/classes/${variantName}") {
                    exclude(coverageExclusions)
                }
            )

            executionData.setFrom(
                files(
                    "$buildDir/jacoco/$testTaskName.exec",
                    "$buildDir/outputs/unit_test_code_coverage/${variantName}UnitTest/$testTaskName.exec",
                    "$buildDir/outputs/code_coverage/connected/*coverage.ec"
                )
            )

            sourceDirectories.setFrom(
                files(
                    "$projectDir/src/main/java",
                    "$projectDir/src/main/kotlin"
                )
            )
            additionalSourceDirs.setFrom(
                files(
                    "$projectDir/src/main/java",
                    "$projectDir/src/main/kotlin"
                )
            )
        }

        jacocoTestReport.dependsOn(reportTask)
    }

    tasks.withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            // Required for JaCoCo + Robolectric
            // https://github.com/robolectric/robolectric/issues/2230
            // TODO: Consider removing if not we don't add Robolectric
            isIncludeNoLocationClasses = true

            // Required for JDK 11 with the above
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = listOf("jdk.internal.*")
        }
    }
}