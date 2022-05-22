package io.github.danthe1st.revanced_annotation_checker

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid


val requiredAnnotations = listOf(
    "app.revanced.patcher.annotation.Name",
    "app.revanced.patcher.annotation.Description",
    "app.revanced.patcher.annotation.Version",
    "app.revanced.patcher.annotation.Compatibility"
)

class AnnotationChecker(val env: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val visitor = CheckerVisitor(env)
        resolver.getSymbolsWithAnnotation("app.revanced.patcher.patch.annotations.Patch")
            .forEach { it.accept(visitor, Unit) }

        return emptyList()
    }
}

class CheckerVisitor(val env: SymbolProcessorEnvironment) : KSVisitorVoid() {
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        val traversed = mutableSetOf<KSAnnotation>()
        classDeclaration.annotations.forEach { traverseAnnotations(it, traversed) }
        val traversedNames = traversed.map { it.annotationType.resolve().declaration.qualifiedName?.asString() }.toSet()
        val annotationsLeft = requiredAnnotations.toMutableSet()
        annotationsLeft.removeAll(traversedNames)
        if (annotationsLeft.isNotEmpty()) {
            env.logger.error(
                "Class is annotated with @Patch but the following annotations are missing: $annotationsLeft",
                classDeclaration
            )
        }
        super.visitClassDeclaration(classDeclaration, data)
    }

    private fun traverseAnnotations(
        toTraverse: KSAnnotation,
        traversed: MutableSet<KSAnnotation>,
    ) {
        if (!traversed.contains(toTraverse)) {
            traversed.add(toTraverse)
            toTraverse.annotationType.annotations.forEach { traverseAnnotations(it, traversed) }
        }
    }
}
