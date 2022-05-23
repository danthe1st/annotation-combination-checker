# Annotation combination checker - example

This gradle project shows how to use Annotation combination checker.

In the file `build.gradle.kts`,
Annotation combination checker is configured in a way so that
all symbols annotated with `@RequiresAB` also need to be annotated with `@A` and `@B`.
Similarly, it is configured so that everything annotated with `@RequiresX` also needs to be annotated with `@X`.
However, the annotation `Y` is annotated with `@X` so annotating something with `@Y` satisfies the `@RequiresX` constraint.
