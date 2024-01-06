plugins {
    id("guardian.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "br.com.bit.guardian.datasource"
}

dependencies{
    implementation(project(":core:data:network"))
}