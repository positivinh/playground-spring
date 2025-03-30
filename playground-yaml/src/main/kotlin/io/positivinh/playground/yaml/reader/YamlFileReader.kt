package io.positivinh.playground.yaml.reader

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.nio.file.Path

class YamlFileReader {

    fun <T> readYamlFile(yamlFile: Path, clazz: Class<T>): T {

        val objectMapper = ObjectMapper(YAMLFactory())

        objectMapper.findAndRegisterModules()

        return objectMapper.readValue(yamlFile.toFile(), clazz)
    }

    // TODO fix with generic type
    fun <T> readYamlFile(yamlFile: Path): T {

        val objectMapper = ObjectMapper(YAMLFactory())

        objectMapper.findAndRegisterModules()

        return objectMapper.readValue(yamlFile.toFile(), object : TypeReference<T>() {})
    }
}
