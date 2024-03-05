import br.com.bit.guardian.convention.core.GuardianBuildType

plugins {
    id("guardian.android.application")
    id("guardian.android.application.compose")
    id("guardian.android.application.jacoco")
    id("jacoco")
    id("com.google.gms.google-services")
}

android {
    defaultConfig {
        applicationId = "br.com.bit.guardian.app"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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

    implementation(project(":core:common"))
    implementation(project(":core:data:network"))
    implementation(project(":core:data:repository"))
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:login"))

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