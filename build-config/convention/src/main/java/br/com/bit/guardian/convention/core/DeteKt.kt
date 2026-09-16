package br.com.bit.guardian.convention.core

import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

internal fun Project.configureDetekt(
    commonExtension: DetektExtension,
) {
    commonExtension.apply {
        this.config.setFrom(files(file("$rootDir/tools/detekt/config.yml")))
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            checkstyle.required.set(true)

            html.required.set(true)
            html.outputLocation.set(file("build/reports/detekt/detekt.html"))

            sarif.required.set(true)

            markdown.required.set(true)
        }
    }
}
