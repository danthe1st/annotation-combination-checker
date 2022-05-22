# ReVanced annotation checking tool
> This is a community project and not made by [ReVanced](https://revanced.app)

This tool checks whether all necessary annotations are present in ReVanced patches.

If a class is annotated with `@Patch` but not with all of `Name`, `Description` and `Version`, there will be a compile-time error.

### How to use
- clone this repository
- run `gradlew publish` inside the cloned repository
- create a ReVanced patch (See [the ReVanced documentation](https://github.com/revanced/revanced-documentation) for details)
- add the following to the `plugins` section of your `build.gradle.kts`:
  ```kotlin
  id("com.google.devtools.ksp") version "1.7.0-RC-1.0.5"
  ```
- add the following to the `dependencies` section of your `build.gradle.kts`:
  ```kotlin
  ksp("io.github.danthe1st:revanced-annotation-checking-tool:1.0-SNAPSHOT")
  ```
