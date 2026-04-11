plugins {
    id("guardian.android.feature")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
}

android {
    namespace = "br.com.bit.guardian.feature.settings"
}
dependencies {
    implementation(project(":core:ui"))
}
