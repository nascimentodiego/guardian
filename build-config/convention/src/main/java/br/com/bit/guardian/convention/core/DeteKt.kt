package br.com.bit.guardian.convention.core

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project

internal fun Project.configureDetekt(
    commonExtension: DetektExtension,
) {
    commonExtension.apply {
        this.config.setFrom(files(file("$rootDir/tools/detekt/config.yml")))

        reports {
            xml.required.set(true)

            html.required.set(true)
            html.outputLocation.set(file("build/reports/detekt/detekt.html"))

            txt.required.set(true)

            sarif.required.set(true)

            md.required.set(true)
        }
    }
}
