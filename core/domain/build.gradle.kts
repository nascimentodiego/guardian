plugins {
    id("guardian.android.library")
}

android {
    namespace = "br.com.bit.guardian.core.domain"
}

dependencies {
    implementation(project(":core:data:repository"))
}