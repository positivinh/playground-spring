package io.unforgivinh.playgound.spring.commons.validation

import org.springframework.validation.DirectFieldBindingResult
import org.springframework.validation.Errors

interface EntityValidator<T : Any> {

    fun validate(entity: T)

    fun customValidators(): List<SimpleValidator<T>> {

        return listOf()
    }

    fun provideNewErrors(entity: T): Errors {

        return DirectFieldBindingResult(entity, entity::class.java.simpleName)
    }
}
