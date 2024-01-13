plugins {
    id("guardian.android.library")
}

android {
    namespace = "br.com.bit.guardian.core.data.repository"
}

dependencies {
    implementation(project(":core:data:datasource"))
    implementation(project(":core:common"))
}