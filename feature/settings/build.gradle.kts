plugins {
    id("guardian.android.feature")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "br.com.bit.guardian.feature.settings"
}
