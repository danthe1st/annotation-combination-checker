package io.github.danthe1st.annotation_checker.example

@RequiresAB
@A
@B
class ABAnnotated {
    //removing A or B yields a compile-time-error
}

@RequiresX
@Y//Y itself is annotated with X
class XYAnnotated{
    //removing X yields a compile-time-error
}

