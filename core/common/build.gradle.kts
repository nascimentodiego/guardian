plugins {
    id("guardian.android.library")
}

android {
    namespace = "br.com.bit.guardian.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}