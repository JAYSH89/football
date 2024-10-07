plugins {
    alias(libs.plugins.football.android.feature)
}

android {
    namespace = "nl.jaysh.football.feature.competition"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(libs.bundles.coil)
    implementation(libs.napier)
}