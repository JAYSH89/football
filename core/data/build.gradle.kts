plugins {
    alias(libs.plugins.football.android.library)
    alias(libs.plugins.football.jvm.junit5)
    alias(libs.plugins.football.arrow)
    alias(libs.plugins.football.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "nl.jaysh.football.core.data"
}

dependencies {
    api(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.datastore)
    implementation(projects.core.domain)
    implementation(libs.napier)
}
