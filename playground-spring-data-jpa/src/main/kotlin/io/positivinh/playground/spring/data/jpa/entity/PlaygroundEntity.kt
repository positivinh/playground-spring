package io.positivinh.playground.spring.data.jpa.entity

import jakarta.persistence.*

@Entity
@Table(name = "PLAYGROUND_ENTITY")
@SequenceGenerator(name = "playgroundEntityPkGenerator", sequenceName = "PK_PLAYGROUND_ENTITY_SEQ", allocationSize = 1)
class PlaygroundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playgroundEntityPkGenerator")
    var id: Long? = null

    var data: String? = null

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
