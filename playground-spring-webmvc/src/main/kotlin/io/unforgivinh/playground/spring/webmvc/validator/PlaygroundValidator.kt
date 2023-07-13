package io.unforgivinh.playground.spring.webmvc.validator

import io.unforgivinh.playgound.spring.commons.validation.AbstractEntityValidator
import io.unforgivinh.playgound.spring.commons.validation.SimpleValidator
import io.unforgivinh.playground.spring.webmvc.dtos.Playground
import io.unforgivinh.playground.spring.webmvc.validator.impl.PlaygroundUniquenessValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Validator

@Component
class PlaygroundValidator(
    validator: Validator,
    private val playgroundUniquenessValidator: PlaygroundUniquenessValidator
) : AbstractEntityValidator<Playground>(validator) {

    override fun customValidators(): List<SimpleValidator<Playground>> {

        return listOf(playgroundUniquenessValidator)
    }


}
