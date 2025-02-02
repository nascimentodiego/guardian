plugins {
    id("guardian.android.feature")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
}

android {
    namespace = "br.com.bit.guardian.feature.reports"
}
dependencies {
    implementation(project(":core:ui"))
    implementation(libs.androidx.runtime)
    implementation(libs.kotlinx.collections.immutable)

}
