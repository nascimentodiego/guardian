package br.com.bit.guardian.convention.core

@Suppress("unused")
enum class GuardianBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    TEST(".test"),
    RELEASE,
    BENCHMARK(".benchmark")
}
