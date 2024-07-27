package io.positivinh.playground.spring.data.jpa.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "PLAYGROUND_ENTITY")
@SequenceGenerator(name = "playgroundEntityPkGenerator", sequenceName = "PK_PLAYGROUND_ENTITY_SEQ", allocationSize = 1)
@EntityListeners(AuditingEntityListener::class) // https://docs.spring.io/spring-data/jpa/reference/auditing.html#jpa.auditing.configuration
class PlaygroundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playgroundEntityPkGenerator")
    var id: Long? = null

    var data: String? = null

    @JoinColumn(name = "PLAYGROUND_ENTITY_ID")
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var records: MutableSet<PlaygroundEntityRecord> = HashSet()

    @CreatedDate
    @Column(name = "CREATION_TIME")
    var creationTime: LocalDateTime? = null

    @CreatedBy
    @Column(name= "CREATED_BY")
    var createdBy: String? = null

    @LastModifiedDate
    @Column(name = "UPDATE_TIME")
    var updateTime: LocalDateTime? = null

    @LastModifiedBy
    @Column(name= "UPDATED_BY")
    var updatedBy: String? = null

    fun addRecord(playgroundEntityRecord: PlaygroundEntityRecord): PlaygroundEntity {

        playgroundEntityRecord.playgroundEntity = this
        this.records.add(playgroundEntityRecord)
        return this
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlaygroundEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }


}
