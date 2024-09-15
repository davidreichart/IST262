package model.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FileTagTest {

    private FileTag testFileTag;
    private String testName = "testing_name";
    private Color testColor = new Color(0x4e8552);

    @BeforeEach
    void init() {
        this.testFileTag = new FileTag(this.testName, this.testColor);
    }

    @Test
    void testToString() {
    }

    @Test
    void compareTo_identifiesSameTags() {
        FileTag sameTag = new FileTag(this.testName, this.testColor);
        int comparisonResult = this.testFileTag.compareTo(sameTag);
        assertEquals(0, comparisonResult);
    }

    @Test
    void compareTo_identifiesDifferentTags() {
        FileTag differentTag = new FileTag("someOtherName", new Color(0xFFFFFF));
        int comparisonResult = this.testFileTag.compareTo(differentTag);
        assertNotEquals(0, comparisonResult);
    }

    @Test
    void getName_returnsFileTagNameString() {
        assertEquals(this.testName, this.testFileTag.getName());
    }

    @Test
    void setName_changesFileTagNameString() {
        String differntName = "differentName";
        this.testFileTag.setName(differntName);
        assertNotEquals(this.testName, this.testFileTag.getName());
    }

    @Test
    void getColor_returnFileTagColorObject() {
        assertEquals(this.testColor, this.testFileTag.getColor());
    }

    @Test
    void setColor_changesFileTagColorObject() {
        Color differentColor = new Color(0x755762);
        this.testFileTag.setColor(differentColor);
        assertNotEquals(this.testColor, this.testFileTag.getColor());
    }
}