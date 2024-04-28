package io.positivinh.playground.spring.webmvc.validator.impl

import io.positivinh.playgound.spring.commons.validation.SimpleValidator
import io.positivinh.playground.spring.webmvc.dtos.Resource
import io.positivinh.playground.spring.webmvc.repository.ResourceInMemoryRepository
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class ResourceUniquenessValidator(private val playgroundRepository: ResourceInMemoryRepository) : SimpleValidator<Resource> {

    override fun validate(entity: Resource, errors: Errors) {

        playgroundRepository.findByName(entity.name)
            .ifPresent { errors.reject("Playground already exists") }
    }


}
