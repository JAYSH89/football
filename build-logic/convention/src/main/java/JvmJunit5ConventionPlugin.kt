import nl.jaysh.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class JvmJunit5ConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
//            pluginManager.apply("jacoco")

            tasks.withType<Test>() {
                useJUnitPlatform()
//                extensions.getByType<JacocoTaskExtension>().apply {
//                    isIncludeNoLocationClasses = true
//                }
            }

//            extensions.configure<JacocoPluginExtension> {
//                toolVersion = "0.8.12"
//            }
//
//            tasks.named("jacocoTestReport", JacocoReport::class.java) {
//                dependsOn(":$path:test")
//                reports.apply {
//                    xml.required.set(true)
//                    html.required.set(true)
//                }
//            }

            dependencies {
                add("testImplementation", libs.findLibrary("junit5.api").get())
                add("testImplementation", libs.findLibrary("junit5.params").get())
                add("testRuntimeOnly", libs.findLibrary("junit5.engine").get())

                add("testImplementation", libs.findLibrary("assertk").get())
                add("testImplementation", libs.findLibrary("turbine").get())
                add("testImplementation", libs.findLibrary("mockk").get())
            }
        }
    }
}
