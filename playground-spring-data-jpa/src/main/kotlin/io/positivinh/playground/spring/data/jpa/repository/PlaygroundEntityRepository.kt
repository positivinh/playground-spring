package io.positivinh.playground.spring.data.jpa.repository

import io.positivinh.playground.spring.data.jpa.entity.PlaygroundEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaygroundEntityRepository: JpaRepository<PlaygroundEntity, Long> {

    fun findAllByCreatedBy(createdBy: String): List<PlaygroundEntity>
}
