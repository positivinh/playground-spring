package io.positivinh.playground.yaml.reader.test

import java.nio.file.Path
import java.nio.file.Paths

object YamlFileFixtureFactory {

    fun defaultFixture(): Path = Paths.get("src/test/resources/file.yaml")
}
