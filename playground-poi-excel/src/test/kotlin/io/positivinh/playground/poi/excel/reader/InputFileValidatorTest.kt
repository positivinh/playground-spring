package io.positivinh.playground.poi.excel.reader

import io.positivinh.playground.poi.excel.reader.test.ExcelFileFixtureFactory
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class InputFileValidatorTest {

    @Test
    fun validateInputFile() {

        Assertions.assertThatCode { InputFileValidator.validateInputFile(ExcelFileFixtureFactory.defaultFixture()) }
            .doesNotThrowAnyException()
    }

    @Test
    fun validateInputFile_fileNotExists_reject() {

        val exception = Assertions.catchException { InputFileValidator.validateInputFile(ExcelFileFixtureFactory.notExistsFixture()) }

        Assertions.assertThat(exception).isNotNull
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun validateInputFile_fileNotReadable_reject() {

        val exception = Assertions.catchException { InputFileValidator.validateInputFile(ExcelFileFixtureFactory.notReadableFixture()) }

        Assertions.assertThat(exception).isNotNull
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
    }
}
