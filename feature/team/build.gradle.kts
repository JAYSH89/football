plugins {
    alias(libs.plugins.football.android.feature)
    alias(libs.plugins.football.android.junit5)
}

android {
    namespace = "nl.jaysh.football.feature.team"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(libs.bundles.coil)
    implementation(libs.napier)
}