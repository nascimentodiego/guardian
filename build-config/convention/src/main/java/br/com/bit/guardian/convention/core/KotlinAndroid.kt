package br.com.bit.guardian.convention.core

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = 34

        defaultConfig {
            minSdk = 29
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            // Unnecessary to AGP 8.2.1 without minSDK
            // isCoreLibraryDesugaringEnabled = true
        }

        kotlinOptions {
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.Experimental",
            )

            // Set JVM target to 17
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        // Unnecessary to AGP 8.2.1
       // add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
        add("implementation", libs.findLibrary("kotlinx.datetime").get())
    }
}

fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}