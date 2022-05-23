# Annotation combination checker

[![Release](https://jitpack.io/v/danthe1st/annotation-combination-checker.svg)](https://jitpack.io/#danthe1st/annotation-combination-checker)

This tool checks whether everything annotated with a specific Kotlin annotation
is also annotated with another annotation.

![annotation-combination-checker](https://user-images.githubusercontent.com/34687786/169851818-3eb33919-acc5-4647-9acc-6f72eb864b1f.png)

### How to use
- Add the following to the `repositories` section of your `build.gradle.kts`:
  ```kotlin
  maven {
      url = uri("https://jitpack.io")
  }
  ```
  Alternatively, you could use `mavenLocal()` and build Annotation combination checker yourself using `./gradlew build`
- add the following to the `plugins` section of your `build.gradle.kts`:
  ```kotlin
  id("com.google.devtools.ksp") version "1.7.0-RC-1.0.5"
  ```
- add the following to the `dependencies` section of your `build.gradle.kts`:
  ```kotlin
  ksp("io.github.danthe1st:annotation-combination-checker:1.0-SNAPSHOT")
  ```
  If JitPack is used, the group id is `com.github.danthe1st` instead of `io.github.danthe1st`. 
- add the following to your `build.gradle.kts`:
  ```kotlin
  tasks{
      ksp{
          arg("com.yourpackage.RequiringAnnotation",
                  "com.yourpackage.RequiredAnnotationA;" +
                  "com.yourpackage.RequiredAnnotationB;" +
                  "com.yourpackage.RequiredAnnotationC")
      }
  }
  ```
  This configures Annotation combination checker to raise an error for every class
annotated with `@RequiringAnnotation` but missing at least one of 
`@RequiredAnnotationA`, `@RequiredAnnotationB`, or `@RequiredAnnotationC`.

### Limitiations
- Annotated properties, fields and functions are not checked
- This project is (not yet) available on Maven Central.
However, it is possible to get preview builds [on Jitpack](https://jitpack.io/#danthe1st/annotation-combination-checker)
by adding the following to the `build.gradle.kts`:
  ```kotlin
  maven {
      url = uri("https://jitpack.io")
  }
  ```
