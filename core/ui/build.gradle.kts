plugins {
    alias(libs.plugins.football.android.library.compose)
    alias(libs.plugins.football.android.junit5)
}

android {
    namespace = "nl.jaysh.football.core.ui"
}

dependencies {
    api(projects.core.designsystem)
    api(projects.core.domain)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.bundles.lifecycle)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.napier)
}
