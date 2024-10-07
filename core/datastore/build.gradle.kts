plugins {
    alias(libs.plugins.football.android.library)
    alias(libs.plugins.football.android.junit5)
    alias(libs.plugins.football.hilt)
    alias(libs.plugins.football.arrow)
}

android {
    namespace = "nl.jaysh.football.core.datastore"
}

dependencies {
    api(projects.core.common)
    api(projects.core.domain)

    implementation(libs.androidx.dataStore)
    implementation(libs.napier)
}
