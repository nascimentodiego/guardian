plugins {
    id("guardian.android.library")
}

android {
    namespace = "br.com.bit.guardian.core.test"
}

dependencies {
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
}