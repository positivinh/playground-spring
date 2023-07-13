package io.unforgivinh.playgound.spring.commons.validation

import com.crabshue.commons.kotlin.logging.getLogger
import io.unforgivinh.playgound.spring.commons.validation.exceptions.EntityErrorContext
import io.unforgivinh.playgound.spring.commons.validation.exceptions.EntityErrorType
import io.unforgivinh.playgound.spring.commons.validation.exceptions.ValidationException
import org.springframework.validation.Errors
import org.springframework.validation.Validator

abstract class AbstractEntityValidator<T : Any>(private val validator: Validator) : EntityValidator<T> {

    private val logger = getLogger()

    override fun validate(entity: T) {

        val errors = provideNewErrors(entity)

        validateEntity(entity, errors)

        this.customValidators()
            .forEach { validator -> validator.validate(entity, errors) }

        if (errors.hasErrors()) {
            logger.error("Entity [$entity] is invalid [${errors.allErrors}]")
            throw ValidationException(EntityErrorType.ENTITY_INVALID, errors)
                .addContextValue(EntityErrorContext.ENTITY, entity)
        }

        logger.info("Validation [${entity::class.java.simpleName}] OK [$entity]")
    }

    private fun validateEntity(entity: T, errors: Errors) {

        validator.validate(entity, errors)
    }

}
