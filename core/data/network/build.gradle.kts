plugins {
    id("guardian.android.library")
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "br.com.bit.guardian.core.network"
    buildFeatures {
        buildConfig = true
    }
}

secrets {
    defaultPropertiesFileName = "./tools/secrets/secrets.defaults.properties"
}

dependencies{
    implementation(libs.okhttp.logging)
    api(libs.retrofit.core)
    api(libs.kotlinx.serialization.json)
    api(libs.retrofit.kotlin.serialization)
}