plugins {
    id("guardian.android.library")
    id("guardian.android.library.compose")
}

android {
    namespace = "br.com.bit.guardian.core.ui"
}
dependencies {
    implementation(project(":core:designsystem"))
    implementation(libs.androidx.material3.adaptive.navigation.suite.android)
    api(libs.kotlinx.serialization.json)
}
