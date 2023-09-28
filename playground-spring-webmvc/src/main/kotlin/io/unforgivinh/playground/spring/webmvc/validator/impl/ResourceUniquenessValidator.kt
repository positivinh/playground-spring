package io.unforgivinh.playground.spring.webmvc.validator.impl

import io.unforgivinh.playgound.spring.commons.validation.SimpleValidator
import io.unforgivinh.playground.spring.webmvc.dtos.Resource
import io.unforgivinh.playground.spring.webmvc.repository.ResourceInMemoryRepository
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class ResourceUniquenessValidator(private val playgroundRepository: ResourceInMemoryRepository) : SimpleValidator<Resource> {

    override fun validate(entity: Resource, errors: Errors) {

        playgroundRepository.findByName(entity.name)
            .ifPresent { errors.reject("Playground already exists") }
    }


}
