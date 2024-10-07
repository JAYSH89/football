import nl.jaysh.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ArrowConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                val arrow = libs.findLibrary("arrow.stack").get()
                add("implementation", platform(arrow))
                add("ksp", libs.findLibrary("arrow.optics.ksp").get())
                add("implementation", libs.findBundle("arrow").get())
            }
        }
    }
}