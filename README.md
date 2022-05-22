# ReVanced annotation checking tool
> This is a community project and not made by [ReVanced](https://revanced.app)

This tool checks whether all necessary annotations are present in ReVanced patches.

This tool raises a compile-time error for every class annotated with a specific annotation that misses another annotation.

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
- add the following to your `build.gradle.kts`:
  ```kotlin
  tasks{
    ksp{
        arg("app.revanced.patcher.patch.annotations.Patch",
                "app.revanced.patcher.annotation.Name;" +
                "app.revanced.patcher.annotation.Description;" +
                "app.revanced.patcher.annotation.Version;" +
                "app.revanced.patcher.annotation.Compatibility" )
    }
  }
  ```
  This configures `revanced-annotation-checking-tool` to raise an error for every class annotated with `@Patch` but not with at least one of `@Name`, `@Description`, `@Version` or `@Compatibility`.
