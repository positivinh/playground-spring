package io.unforgivinh.playground.spring.webmvc.service.impl

import com.crabshue.commons.kotlin.logging.getLogger
import io.unforgivinh.playground.spring.webmvc.dtos.Resource
import io.unforgivinh.playground.spring.webmvc.repository.ResourceInMemoryRepository
import io.unforgivinh.playground.spring.webmvc.service.ResourceService
import io.unforgivinh.playground.spring.webmvc.validator.ResourceValidator
import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ResourceServiceImpl(
    private val resourceInMemoryRepository: ResourceInMemoryRepository,
    private val resourceValidator: ResourceValidator
) : ResourceService {

    private val logger = getLogger()

    @PostConstruct
    fun playgroundDataMigration() {

        resourceInMemoryRepository.deleteAll()

        resourceInMemoryRepository.save(
            Resource()
                .name("Default playground created by migration")
        )

        logger.info("Finished data migration for playgrounds. Initial list of playgrounds: ${resourceInMemoryRepository.findAll()}")

    }

    override fun listResources(searchTerm: String?, pageable: Pageable?): Page<Resource> {

        val ret: List<Resource> = resourceInMemoryRepository.findAll().toList()
            .filter { resource -> searchTerm?.let { resource.name?.contains(it) } ?: true }

        return PageImpl(ret, pageable!!, ret.size.toLong())
    }

    override fun createResource(resource: Resource): Resource {

        logger.debug("Creating playground [$resource]")

        resourceValidator.validate(resource)

        resourceInMemoryRepository.save(resource)

        logger.info("Created playground [$resource]")

        return resource
    }
}
