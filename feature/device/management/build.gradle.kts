plugins {
    id("guardian.android.feature")
}

android {
    namespace = "br.com.bit.guardian.feature.management"
}
dependencies {
    implementation(project(":core:ui"))
}
