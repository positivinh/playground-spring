package io.unforgivinh.playground.spring.webmvc.repository

import io.unforgivinh.playground.spring.webmvc.dtos.Playground
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PlaygroundInMemoryRepository : InMemoryRepository<Playground, String>, CrudRepository<Playground, String> {

    private val inMemoryPlaygrounds = mutableListOf<Playground>()

    override fun <S : Playground> save(entity: S): S {

        inMemoryPlaygrounds.add(entity)

        return entity
    }

    override fun <S : Playground?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Optional<Playground> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<Playground> {

        return this.inMemoryPlaygrounds
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {

        this.inMemoryPlaygrounds.clear()
    }

    override fun deleteAll(entities: MutableIterable<Playground>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<String>) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Playground) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String) {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<String>): MutableIterable<Playground> {
        TODO("Not yet implemented")
    }

}
