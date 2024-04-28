package io.positivinh.playground.spring.webmvc.validator

import io.positivinh.playgound.spring.commons.validation.AbstractEntityValidator
import io.positivinh.playgound.spring.commons.validation.SimpleValidator
import io.positivinh.playground.spring.webmvc.dtos.Resource
import io.positivinh.playground.spring.webmvc.validator.impl.ResourceUniquenessValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Validator

@Component
class ResourceValidator(
    validator: Validator,
    private val resourceUniquenessValidator: ResourceUniquenessValidator
) : AbstractEntityValidator<Resource>(validator) {

    override fun customValidators(): List<SimpleValidator<Resource>> {

        return listOf(resourceUniquenessValidator)
    }


}
