plugins {
    kotlin("jvm") version "1.7.0-RC"
    id("com.google.devtools.ksp") version "1.7.0-RC-1.0.5"
}

group = "io.github.danthe1st"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
    mavenLocal()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    //local maven repository
    //ksp("io.github.danthe1st:annotation-combination-checker:1.0-SNAPSHOT")
    //jitpack
    ksp("io.github.danthe1st:annotation-combination-checker:-SNAPSHOT")
}

tasks{
    ksp{
        arg("io.github.danthe1st.annotation_checker.example.RequiresAB",
            "io.github.danthe1st.annotation_checker.example.A;" +
                    "io.github.danthe1st.annotation_checker.example.B")
        arg("io.github.danthe1st.annotation_checker.example.RequiresX",
            "io.github.danthe1st.annotation_checker.example.X")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}