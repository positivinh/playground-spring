package io.positivinh.playground.yaml.reader

import io.positivinh.playground.yaml.model.Mapping
import io.positivinh.playground.yaml.reader.test.YamlFileFixtureFactory
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class YamlFileReaderTest {

    private val yamlReader = YamlFileReader()

    @Test
    fun readYamlFile() {

        val yamlFile = YamlFileFixtureFactory.defaultFixture()

        val res: Mapping = yamlReader.readYamlFile(yamlFile, Mapping::class.java)

        Assertions.assertThat(res).isNotNull
        Assertions.assertThat(res.fileType).isEqualTo("CRF")
        Assertions.assertThat(res.mappingRules).hasSize(1)

        val concept = res.mappingRules[0]

        Assertions.assertThat(concept).isNotNull
        Assertions.assertThat(concept.name).isEqualTo("Concept A")
        Assertions.assertThat(concept.className).isEqualTo("io.positivinh.playground.yaml.mappings.ConceptA")
        Assertions.assertThat(concept.properties).hasSize(1)

        val properties = concept.properties[0]
        Assertions.assertThat(properties.name).isEqualTo("Property A")
        Assertions.assertThat(properties.sheet).isEqualTo("Sheet1")
        Assertions.assertThat(properties.row).isEqualTo(1)
        Assertions.assertThat(properties.column).isEqualTo("C")
        Assertions.assertThat(properties.type).isEqualTo("string")

    }

}
