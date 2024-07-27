package io.positivinh.playground.spring.data.jpa.entity

import jakarta.persistence.*

@Entity
@Table(name = "PLAYGROUND_ENTITY_RECORD")
@SequenceGenerator(name = "playgroundEntityRecordPkGenerator", sequenceName = "PK_PLAYGROUND_ENTITY_RECORD_SEQ", allocationSize = 1)
class PlaygroundEntityRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playgroundEntityRecordPkGenerator")
    var id: Long? = null

    @JoinColumn(name = "PLAYGROUND_ENTITY_ID")
    @ManyToOne
    var playgroundEntity: PlaygroundEntity? = null

    @Column(name = "RECORD_DATA")
    var recordData: String? = null
}
