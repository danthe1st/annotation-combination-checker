package io.github.danthe1st.annotation_combin_checker

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid

class AnnotationChecker(val env: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        if(env.options.isEmpty()){
            env.logger.error("no annotations to check present")
        }
        for ((k,v) in env.options) {
            val visitor = CheckerVisitor(env,k,v.split(";"))
            for (it in resolver.getSymbolsWithAnnotation(k)) {
                it.accept(visitor, Unit)
            }
        }
        return emptyList()
    }
}

class CheckerVisitor(val env: SymbolProcessorEnvironment,val requiringAnnotation: String, val requiredAnnotations: List<String>) : KSVisitorVoid() {
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        val traversed = mutableSetOf<KSAnnotation>()
        classDeclaration.annotations.forEach { traverseAnnotations(it, traversed) }
        val traversedNames = traversed.map { it.annotationType.resolve().declaration.qualifiedName?.asString() }.toSet()
        val annotationsLeft = requiredAnnotations.toMutableSet()
        annotationsLeft.removeAll(traversedNames)

        if (annotationsLeft.isNotEmpty()) {
            env.logger.error(
                "Class is annotated with $requiringAnnotation but the following annotations are missing: $annotationsLeft",
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
            toTraverse.annotationType.resolve().declaration.annotations.forEach { traverseAnnotations(it, traversed) }
        }
    }
}
