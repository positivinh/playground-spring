package io.positivinh.playground.spring.data.jpa.repository

import io.positivinh.playground.spring.data.jpa.entity.PlaygroundEntityRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaygroundEntityRecordRepository : JpaRepository<PlaygroundEntityRecord, Long> {
}
