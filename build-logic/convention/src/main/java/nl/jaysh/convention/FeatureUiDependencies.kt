package nl.jaysh.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.addFeatureUiDependencies(project: Project) {
    add("implementation", project(":core:ui"))
    add("implementation", project(":core:designsystem"))

    add("implementation", project.libs.findBundle("compose").get())
    add("implementation", project.libs.findLibrary("hilt.navigation").get())
    add("debugImplementation", project.libs.findBundle("compose.debug").get())
}
