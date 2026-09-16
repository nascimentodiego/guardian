package br.com.bit.guardian.convention.core

import org.gradle.api.Project
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

internal fun Project.configureKtLint(
    commonExtension: KtlintExtension,
) {
    commonExtension.apply{
        android.set(true)
        ignoreFailures.set(false)
        additionalEditorconfig.set(
            mapOf(
                "ktlint_standard_final-newline" to "disabled",
                "ktlint_standard_import-ordering" to "disabled",
            )
        )
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.CHECKSTYLE)
            reporter(ReporterType.HTML)
            reporter(ReporterType.SARIF)
        }
    }
}
