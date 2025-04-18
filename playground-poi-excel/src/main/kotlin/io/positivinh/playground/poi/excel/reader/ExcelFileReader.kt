package io.positivinh.playground.poi.excel.reader

import com.crabshue.commons.kotlin.logging.getLogger
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.nio.file.Path

class ExcelFileReader {

    private val log = getLogger()

    fun readExcelFile(excelFile: Path) {

        InputFileValidator.validateInputFile(excelFile)

        val workbook = XSSFWorkbook(excelFile.toFile())

        log.info("Reading excel file [$excelFile], work book type [${workbook.workbookType}]")

        readCellContent(workbook, "Sheet1", 12, "AB")
    }

    private fun readCellContent(workbook: XSSFWorkbook, sheetName: String, rowIndex: Int, columnName: String): Any {

        val sheet = workbook.getSheet(sheetName)

        val colIndex = CellReference.convertColStringToIndex(columnName)

        val rawValue = sheet.getRow(rowIndex - 1).getCell(colIndex).rawValue

        log.info("$rawValue")

        return rawValue
    }


}
