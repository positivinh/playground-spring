package io.positivinh.playground.poi.excel.reader.test

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.FileAttribute
import java.nio.file.attribute.PosixFilePermissions


object ExcelFileFixtureFactory {

    fun defaultFixture(): Path = Paths.get("src/test/resources/input/input.xlsm")

    fun notExistsFixture(): Path = Paths.get("src/test/resources/input/not-exists.xlsm")

    fun notReadableFixture(): Path {

        val notReadablePath = Paths.get("target/not-readable.xlsm")
        val notReadablePermissions = PosixFilePermissions.fromString("---------")
        val permissions: FileAttribute<*> = PosixFilePermissions.asFileAttribute(notReadablePermissions)

        return Files.createFile(notReadablePath, permissions)
    }
}
