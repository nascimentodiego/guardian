package br.com.bit.guardian.convention.core

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureLintAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        lint {
            // Turns off checks for the issue IDs you specify.
            disable += "TypographyFractions" + "TypographyQuotes"

            // If set to true, turns off analysis progress reporting by lint.
            quiet = false

            // If set to true (default), stops the build if errors are found.
            abortOnError = true

            // If set to true, lint only reports errors.
            ignoreWarnings = false

            // If set to true, lint also checks all dependencies as part of its analysis.
            // Recommended for projects consisting of an app with library dependencies.
            checkDependencies = true
        }

    }
}
