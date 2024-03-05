package br.com.bit.guardian.convention.core

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

internal fun Project.configureFeatureDependence(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {

            add("implementation", project(":core:common"))
            add("implementation", project(":core:data:network"))
            add("implementation", project(":core:data:repository"))
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:designsystem"))

//            add("implementation ", libs.findLibrary("firebase-plataform-bom").get())
//            add("implementation ", libs.findLibrary("firebase-auth-kt").get())
        }
    }
}
