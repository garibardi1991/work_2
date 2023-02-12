package ru.garibardi.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FileParseTest {
    ClassLoader cl = FileParseTest.class.getClassLoader();


    @Test
    void pdfTest () throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide");
        File downloadedFile = $("a[href*='junit-user-guide-5.9.2.pdf']").download();
        PDF pdf = new PDF(downloadedFile);
        assertThat(pdf.author).contains("Sam Brannen");
    }

    @Test
    void xlsTest () throws Exception {
        InputStream is = cl.getResourceAsStream("Лист Microsoft Excel.xlsx");
        XLS xls = new XLS(is);
        assertThat(xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue())
                .isEqualTo("Гарри Поттер");
    }
}
