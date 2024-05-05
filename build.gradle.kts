import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.parcelize) apply false
    alias(libs.plugins.sentry) apply false
}

tasks.register<Detekt>("detektAll") {
    description = "Runs detekt build..."
    setSource(files(projectDir))
    config.setFrom(files("$rootDir/detekt.yml"))
    parallel = true
    reports {
        xml.required.set(false)
        txt.required.set(false)
        html {
            required.set(false)
            outputLocation.set(file("build/reports/detekt.html"))
        }
    }
    include("**/*.kt", "**/*.kts")
    exclude("resources/", "*/build/")
}

allprojects {
    apply { from(file("$rootDir/gradle/config.gradle")) }
    apply { from(file("$rootDir/gradle/version.gradle")) }
}

dependencies {
    detektPlugins(libs.detekt.formattingPlugin)
}
true // Needed to make the Suppress annotation work for the plugins block