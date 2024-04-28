package io.positivinh.playground.spring.webmvc.repository

import io.positivinh.playground.spring.webmvc.dtos.Resource
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ResourceInMemoryRepository : InMemoryRepository<Resource, String>, CrudRepository<Resource, String> {

    private val inMemoryPlaygrounds = mutableListOf<Resource>()

    override fun <S : Resource> save(entity: S): S {

        inMemoryPlaygrounds.add(entity)

        return entity
    }

    override fun <S : Resource?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Optional<Resource> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<Resource> {

        return this.inMemoryPlaygrounds
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {

        this.inMemoryPlaygrounds.clear()
    }

    override fun deleteAll(entities: MutableIterable<Resource>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<String>) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Resource) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String) {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<String>): MutableIterable<Resource> {
        TODO("Not yet implemented")
    }

    fun findByName(name: String): Optional<Resource> {

        return this.inMemoryPlaygrounds.stream()
            .filter { it.name == name }
            .findAny()
    }

}
