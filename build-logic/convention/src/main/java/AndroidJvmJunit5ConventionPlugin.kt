import nl.jaysh.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidJvmJunit5ConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("football.jvm.junit5")
            pluginManager.apply("de.mannodermaus.android-junit5")

            dependencies {
                add("androidTestImplementation", libs.findLibrary("junit5.api").get())
                add("androidTestImplementation", libs.findLibrary("junit5.params").get())
                add("androidTestImplementation", libs.findLibrary("junit5.android.test.compose").get())
                add("debugImplementation", libs.findLibrary("androidx.compose.ui.test.manifest").get())
                add("androidTestRuntimeOnly", libs.findLibrary("junit5.engine").get())

                add("androidTestImplementation", libs.findLibrary("assertk").get())
                add("androidTestImplementation", libs.findLibrary("kotlinx.coroutines.test").get())
                add("androidTestImplementation", libs.findLibrary("turbine").get())
            }
        }
    }
}
