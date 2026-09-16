plugins {
    id("guardian.android.library")
    alias(libs.plugins.kotlin.serialization)
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