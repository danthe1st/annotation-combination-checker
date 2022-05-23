# Annotation combination checker

This tool checks whether everything annotated with a specific Kotlin annotation
is also annotated with another annotation.

### How to use
- clone this repository
- run `gradlew publish` inside the cloned repository
- add the following to the `plugins` section of your `build.gradle.kts`:
  ```kotlin
  id("com.google.devtools.ksp") version "1.7.0-RC-1.0.5"
  ```
- add the following to the `dependencies` section of your `build.gradle.kts`:
  ```kotlin
  ksp("io.github.danthe1st:annotation-combination-checker:1.0-SNAPSHOT")
  ```
- add the following to your `build.gradle.kts`:
  ```kotlin
  tasks{
    ksp{
        arg("com.yourpackage.RequiringAnnotation",
                "com.yourpackage.RequiredAnnotationA;" +
                "com.yourpackage.RequiredAnnotationB;" +
                "com.yourpackage.RequiredAnnotationC;")
    }
  }
  ```
  This configures Annotation combination checker to raise an error for every class annotated with `@Patch` but not with at least one of `@Name`, `@Description`, `@Version` or `@Compatibility`.
