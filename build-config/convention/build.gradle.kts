plugins {
    `kotlin-dsl`
}

group = "br.com.bit.guardian.buildconfig"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.ktlint.gradlePlugin)
    compileOnly(libs.deteKt.gradlePlugin)
}

gradlePlugin {
    plugins {

        register("androidApplicationKtlint") {
            id = "guardian.android.application.ktlint"
            implementationClass = "AndroidApplicationKtLintConventionPlugin"
        }

        register("androidApplicationDeteKt") {
            id = "guardian.android.application.detekt"
            implementationClass = "AndroidApplicationDeteKtConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "guardian.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = "guardian.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationJacoco") {
            id = "guardian.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }

        register("androidFeature") {
            id = "guardian.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidLibrary") {
            id = "guardian.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "guardian.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidLibraryJacoco") {
            id = "guardian.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
    }
}

