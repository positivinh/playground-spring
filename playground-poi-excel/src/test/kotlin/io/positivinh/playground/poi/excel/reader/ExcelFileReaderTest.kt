package io.positivinh.playground.poi.excel.reader

import io.positivinh.playground.poi.excel.reader.test.ExcelFileFixtureFactory
import org.junit.jupiter.api.Test

class ExcelFileReaderTest {

    private val reader = ExcelFileReader()

    @Test
    fun readExcelFile() {

        reader.readExcelFile(ExcelFileFixtureFactory.defaultFixture())
    }

}
