plugins {
    id("guardian.android.feature")
    id("guardian.android.library.test")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
}

android {
    namespace = "br.com.bit.guardian.feature.reports"
    screenshotTests {
        imageDifferenceThreshold = 0.02f
    }
}
dependencies {
    implementation(project(":core:ui"))
    implementation(libs.androidx.runtime)
    implementation(libs.kotlinx.collections.immutable)
    testImplementation(project(":core:test"))
}
