package io.unforgivinh.playground.spring.webmvc.validator.impl

import io.unforgivinh.playgound.spring.commons.validation.SimpleValidator
import io.unforgivinh.playground.spring.webmvc.dtos.Playground
import io.unforgivinh.playground.spring.webmvc.repository.PlaygroundInMemoryRepository
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class PlaygroundUniquenessValidator(private val playgroundRepository: PlaygroundInMemoryRepository) : SimpleValidator<Playground> {

    override fun validate(entity: Playground, errors: Errors) {

        playgroundRepository.findByName(entity.name)
            .ifPresent { errors.reject("Playground already exists") }
    }


}
