plugins {
    alias(libs.plugins.football.android.library)
    alias(libs.plugins.football.jvm.junit5)
    alias(libs.plugins.football.hilt)
    alias(libs.plugins.football.arrow)
}

android {
    namespace = "nl.jaysh.football.core.testing"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.network)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.test)

    implementation(libs.junit5.api)
}
