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