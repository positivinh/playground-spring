package io.unforgivinh.playgound.spring.commons.validation

import org.springframework.validation.Errors

fun interface SimpleValidator<T> {

    fun validate(entity: T, errors: Errors)
}
