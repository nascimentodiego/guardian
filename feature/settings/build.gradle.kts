plugins {
    id("guardian.android.feature")
}


android {
    namespace = "br.com.bit.guardian.feature.settings"
}
dependencies {
    implementation(project(":core:ui"))
}
