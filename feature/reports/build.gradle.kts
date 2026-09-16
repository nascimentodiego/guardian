plugins {
    id("guardian.android.feature")
    alias(libs.plugins.kotlin.serialization)
    id("guardian.android.library.test")
}

android {
    namespace = "br.com.bit.guardian.feature.reports"
    screenshotTests {
        imageDifferenceThreshold = 0.02f
    }
}
dependencies {
    implementation(libs.androidx.runtime)
    implementation(libs.kotlinx.collections.immutable)
    testImplementation(project(":core:test"))
}
