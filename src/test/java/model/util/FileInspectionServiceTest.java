package model.util;

import model.FileInspectionService;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileInspectionServiceTest {

    private FileInspectionService fileInspectionService;
    private File testPngFile = new File("src/test/resources/bluePNG.png");
    private File testJpegFile = new File("src/test/resources/greenJPEG.jpeg");
    private File testTxtFile = new File("src/test/resources/textFile.txt");

    @Test
    void testToString_returnsActiveFileInService() {
        this.fileInspectionService = new FileInspectionService(this.testPngFile);
        assertEquals("Currently held file: " + this.testPngFile.getAbsolutePath(), this.fileInspectionService.toString());
    }

    @Test
    void getFileType_identifiesJpegImageFileType() {
        this.fileInspectionService = new FileInspectionService(this.testJpegFile);
        assertEquals("image/jpeg", this.fileInspectionService.getFileType());
    }

    @Test
    void getFileType_identifiesTextFileType() {
        this.fileInspectionService = new FileInspectionService(this.testTxtFile);
        assertEquals("text/plain", this.fileInspectionService.getFileType());
    }

    @Test
    void getFileExtension_identifiesPngFileExtension() {
        this.fileInspectionService = new FileInspectionService(this.testPngFile);
        assertEquals("png", this.fileInspectionService.getFileExtension());
    }

    @Test
    void getFileExtension_identifiesTxtFileExtension() {
        this.fileInspectionService = new FileInspectionService(this.testTxtFile);
        assertEquals("txt", this.fileInspectionService.getFileExtension());
    }

    @Test
    void getFileToInspect_returnsCurrentlyHeldFileObject() {
        this.fileInspectionService = new FileInspectionService(this.testPngFile);
        assertEquals(this.testPngFile, this.fileInspectionService.getFileToInspect());
    }

    @Test
    void setFileToInspect_replacesCurrentlyHeldFileObject() {
        this.fileInspectionService = new FileInspectionService(this.testTxtFile);
        this.fileInspectionService.setFileToInspect(this.testPngFile);
        assertEquals(this.testPngFile, this.fileInspectionService.getFileToInspect());
    }
}