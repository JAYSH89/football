plugins {
    alias(libs.plugins.football.android.library)
    alias(libs.plugins.football.jvm.junit5)
    alias(libs.plugins.football.jvm.ktor)
    alias(libs.plugins.football.hilt)
    alias(libs.plugins.football.arrow)
}

android {
    namespace = "nl.jaysh.football.core.network"
}

dependencies {
    api(projects.core.common)
    api(projects.core.domain)

    implementation(libs.napier)

    testImplementation(libs.ktor.client.mock)
}
