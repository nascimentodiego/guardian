plugins {
    id("guardian.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "br.com.bit.guardian.core.test"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))

//    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
    api(libs.robolectric)
    // Compose testing
    implementation(libs.androidx.compose.ui.test.junit4)
}