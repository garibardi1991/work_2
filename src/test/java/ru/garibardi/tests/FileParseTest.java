package ru.garibardi.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import ru.garibardi.models.Teacher;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FileParseTest {
    ClassLoader cl = FileParseTest.class.getClassLoader();


    @SneakyThrows
    @Test
    void pdfTest () {
        open("https://junit.org/junit5/docs/current/user-guide");
        File downloadedFile = $("a[href*='junit-user-guide-5.9.2.pdf']").download();
        var pdf = new PDF(downloadedFile);
        assertThat(pdf.author).contains("Sam Brannen");
    }

    @SneakyThrows
    @Test
    void xlsTest () {
        InputStream is = cl.getResourceAsStream("Лист Microsoft Excel.xlsx");
        var xls = new XLS(is);
        assertThat(xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue())
                .isEqualTo("Гарри Поттер");
    }

    @SneakyThrows
    @Test
    void csvTest ()  {
        InputStream is = cl.getResourceAsStream("excel.csv");
        var reader = new CSVReader(new InputStreamReader(is));
        List<String[]> content = reader.readAll();
        String[] row = content.get(1);
        assertThat(row[0]).isEqualTo("Tuchs");
        assertThat(row[1]).isEqualTo("JUnit5");
    }

    @SneakyThrows
    @Test
    void zipTest ()  {
        InputStream is = cl.getResourceAsStream("ZipArchiv.zip");
        var zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null); {
            String entryName = entry.getName();

        }
    }

    @SneakyThrows
    @Test
    void jsonTest ()  {
        InputStream is = cl.getResourceAsStream("teacher.json");
        var gson = new Gson();
        JsonObject jsonObject = gson.fromJson(new InputStreamReader(is), JsonObject.class);
        assertThat(jsonObject.get("Name").getAsString()).isEqualTo("Dmitrii");
        assertThat(jsonObject.get("IsGoodTeacher").getAsBoolean()).isTrue();
        assertThat(jsonObject.get("passport").getAsJsonObject().get("number").getAsInt()).isEqualTo(123456);

    }

    @SneakyThrows
    @Test
    void jsonTestWhitModel ()  {
        InputStream is = cl.getResourceAsStream("teacher.json");
        var gson = new Gson();
        Teacher teacher = gson.fromJson(new InputStreamReader(is), Teacher.class);
        assertThat(teacher.Name).isEqualTo("Dmitrii");
        assertThat(teacher.IsGoodTeacher).isTrue();
        assertThat(teacher.passport.number).isEqualTo(123456);

    }


}
