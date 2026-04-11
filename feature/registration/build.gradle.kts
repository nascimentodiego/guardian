plugins {
    id("guardian.android.feature")
    id("guardian.android.library.test")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.compose")
    kotlin("plugin.serialization")
}

android {
    namespace = "br.com.bit.guardian.feature.registration"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:data:datasource"))
}
