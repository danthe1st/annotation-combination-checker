import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0-RC"
    `maven-publish`
}

group = "io.github.danthe1st"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://maven.pkg.github.com/revanced/multidexlib2")
        credentials {
            // DO NOT set these variables in the project's gradle.properties.
            // Instead, you should set them in:
            // Windows: %homepath%\.gradle\gradle.properties
            // Linux: ~/.gradle/gradle.properties
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR") // DO NOT CHANGE!
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN") // DO NOT CHANGE!
        }
    }
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.7.0-RC-1.0.5")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    repositories{
        mavenLocal()
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}