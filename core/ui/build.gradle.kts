plugins {
    id("guardian.android.feature")
}

android {
    namespace = "br.com.bit.guardian.core.ui"
}
dependencies {
    implementation(libs.androidx.material3.adaptive.navigation.suite.android)
}
