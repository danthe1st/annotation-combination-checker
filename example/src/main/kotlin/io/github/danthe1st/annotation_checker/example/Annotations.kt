package io.github.danthe1st.annotation_checker.example

/**
 * every type annotated with this annotation also needs to be annotated with [A] and [B].
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
annotation class RequiresAB
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
annotation class A
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
annotation class B


/**
 * every type annotated with this annotation also needs to be annotated with [X].
 */
@Target(AnnotationTarget.CLASS)
annotation class RequiresX
@Target(AnnotationTarget.CLASS,AnnotationTarget.ANNOTATION_CLASS)
annotation class X

/**
 * Since this annotation itself is annotated with [RequiresX], it is valid to annotate classes with [RequiresX] and [Y] but not explicitly annotate the said class with [X]
 */
@Target(AnnotationTarget.CLASS)
@X
annotation class Y