plugins {
    alias(libs.plugins.football.jvm.library)
    alias(libs.plugins.football.jvm.junit5)
    alias(libs.plugins.football.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.napier)
}
