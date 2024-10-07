plugins {
    alias(libs.plugins.football.android.application.compose)
    alias(libs.plugins.football.android.junit5)
    alias(libs.plugins.football.hilt)
    alias(libs.plugins.football.jvm.ktor)
    alias(libs.plugins.football.arrow)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "nl.jaysh.football"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.feature.competition)
    implementation(projects.feature.standing)
    implementation(projects.feature.team)

    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.network)
    implementation(projects.core.ui)

    // Androidx
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.bundles.lifecycle)

    // Hilt [https://dagger.dev/hilt/]
    implementation(libs.hilt.navigation)

    // Kotlinx Coroutines [https://github.com/Kotlin/kotlinx.coroutines]
    implementation(libs.kotlinx.coroutines.core)

    // Kotlinx Serialization [https://github.com/Kotlin/kotlinx.serialization]
    implementation(libs.kotlinx.serialization.json)

    // Logging Napier [https://github.com/AAkira/Napier]
    implementation(libs.napier)

    debugImplementation(libs.androidx.ui.tooling)
}