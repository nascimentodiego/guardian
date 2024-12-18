plugins {
    id("guardian.android.library")
    id("guardian.android.library.compose")
}

android {
    namespace = "br.com.bit.guardian.core.designsystem"
}

dependencies {
    api(libs.shimmer)
    implementation(libs.androidx.adaptive.android)
}