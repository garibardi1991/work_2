package ru.garibardi.tests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class HomeZip {
    ClassLoader cl = FileParseTest.class.getClassLoader();

    @SneakyThrows
    @Test
    void zipXlsx()  {
        InputStream is = cl.getResourceAsStream("ZipArchiv.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String name = entry.getName();
            long size = entry.getSize();
            System.out.printf("File name: %s \t File size: %d \n", name, size);
            assertThat(entry.getName()).isEqualTo("ZipArchiv.xlsx");
            assertThat(entry.getSize()).isEqualTo(6185);
        }
    }


    @SneakyThrows
    @Test
    void zipCsv()  {
        InputStream is = cl.getResourceAsStream("ZipArchiv2.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String name = entry.getName();
            long size = entry.getSize();
            System.out.printf("File name: %s \t File size: %d \n", name, size);
            assertThat(entry.getName()).isEqualTo("ZIpArchiv.csv");
            assertThat(entry.getSize()).isEqualTo(6185);
        }
    }

    @SneakyThrows
    @Test
    void zipPdf()  {
        InputStream is = cl.getResourceAsStream("ZipArchiv3.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String name = entry.getName();
            long size = entry.getSize();
            System.out.printf("File name: %s \t File size: %d \n", name, size);
            assertThat(entry.getName()).isEqualTo("ZIpArchiv.pdf");
            assertThat(entry.getSize()).isEqualTo(0);
        }
    }
}
