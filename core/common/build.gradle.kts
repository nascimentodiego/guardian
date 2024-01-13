plugins {
    id("guardian.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "br.com.bit.guardian.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit.core)
    api(libs.kotlinx.serialization.json)
    api(libs.retrofit.kotlin.serialization)
}