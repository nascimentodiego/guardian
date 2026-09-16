import br.com.bit.guardian.convention.core.GuardianBuildType

plugins {
    id("guardian.android.application")
    id("guardian.android.application.compose")
    id("guardian.android.application.jacoco")
    id("jacoco")
    id("com.google.gms.google-services")
}

android {

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "br.com.bit.guardian.app"

        versionCode = (findProperty("versionCode") as String?)?.toInt() ?: 1
        versionName = (findProperty("versionName") as String?) ?: "1.0.0-beta1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // The homolog keystore only exists in CI (decoded from a GitHub secret). Locally, developers
    // don't have it, so the homolog build type falls back to the debug signing key.
    val homologKeystorePath = System.getenv("HOMOLOG_KEYSTORE_PATH")
    signingConfigs {
        if (!homologKeystorePath.isNullOrBlank()) {
            create("homolog") {
                storeFile = file(homologKeystorePath)
                storePassword = System.getenv("HOMOLOG_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("HOMOLOG_KEY_ALIAS")
                keyPassword = System.getenv("HOMOLOG_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = GuardianBuildType.DEBUG.applicationIdSuffix
            versionNameSuffix = GuardianBuildType.DEBUG.applicationIdSuffix

            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }

        val release by getting {
            isMinifyEnabled = true
            isShrinkResources = true
            applicationIdSuffix = GuardianBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
        }

        create("homolog") {
            initWith(release)
            applicationIdSuffix = GuardianBuildType.HOMOLOG.applicationIdSuffix
            versionNameSuffix = GuardianBuildType.HOMOLOG.applicationIdSuffix

            // Library modules only declare debug/release build types, so homolog resolves
            // their release variant.
            matchingFallbacks += listOf("release")

            signingConfig =
                signingConfigs.findByName("homolog") ?: signingConfigs.getByName("debug")
        }
    }

    testBuildType = "debug"

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    namespace = "br.com.bit.guardian.app"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(platform(libs.firebase.plataform.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.androidx.material3.adaptive.navigation.suite.android)

    implementation(project(":core:common"))
    implementation(project(":core:data:network"))
    implementation(project(":core:data:repository"))
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:registration"))
    implementation(project(":core:ui"))
    implementation(project(":feature:device:management"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:reports"))

    debugApi(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.material3)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.androidx.test.espresso.core)
}