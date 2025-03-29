package io.positivinh.playground.poi.excel.reader

import java.nio.file.Path
import kotlin.io.path.isReadable
import kotlin.io.path.notExists

object InputFileValidator {

    fun validateInputFile(inputFile: Path) {

        if (inputFile.notExists()) {
            throw IllegalArgumentException("Input file does not exist: $inputFile")
        }

        if (!inputFile.isReadable()) {
            throw IllegalArgumentException("Input file is not readable: $inputFile")
        }
    }
}
