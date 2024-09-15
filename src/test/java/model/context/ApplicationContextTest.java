package model.context;

import model.data.FileTag;
import model.data.images.ImageFile;
import model.data.images.JPEGImage;
import model.data.images.PNGImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextTest {

    public ApplicationContext applicationContext;

    @BeforeEach
    void init() {
        TreeSet<FileTag> fileTagSet = generateTestFileTagTreeSet();
        ArrayList<String> directoryList = generateTestDirectoryArrayList();
        ArrayList<ImageFile> imageFileList = generateTestImageFileArrayList();
        this.applicationContext = new ApplicationContext(fileTagSet, directoryList, imageFileList);
    }

    @Test
    void testToString_returnsAccurateString() {
        String expectedOutput =
                "All known directories: \n" +
                "directory0\n" + "directory1\n" + "directory2\n" + "directory3\n" + "directory4\n" +
                "All defined file tags: \n" +
                "tag0\n" + "tag1\n" + "tag2\n" + "tag3\n" + "tag4\n" +
                "All known Image File objects by name & path: \n" +
                "textFile.txt @ src\\test\\resources\\textFile.txt\n" +
                "textFile.txt @ src\\test\\resources\\textFile.txt\n" +
                "textFile.txt @ src\\test\\resources\\textFile.txt\n" +
                "textFile.txt @ src\\test\\resources\\textFile.txt\n" +
                "textFile.txt @ src\\test\\resources\\textFile.txt\n";
        assertEquals(expectedOutput, this.applicationContext.toString());
    }

    @Test
    void getDefinedTags_returnsCorrectHashSet() {
        assertEquals(generateTestFileTagTreeSet(), this.applicationContext.getDefinedTags());
    }

    @Test
    void setDefinedTags_updatesTreeSet() {
        TreeSet<FileTag> differentSet = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            FileTag tag = new FileTag("tag" + i * 2, new Color(0xFFFFFF));
            differentSet.add(tag);
        }
        this.applicationContext.setDefinedTags(differentSet);
        assertEquals(differentSet, this.applicationContext.getDefinedTags());
    }

    @Test
    void getKnownDirectories_returnsCorrectArrayList() {
        assertEquals(generateTestDirectoryArrayList(), this.applicationContext.getKnownDirectories());
    }

    @Test
    void setKnownDirectories_updatesArrayList() {
        ArrayList<String> differentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            differentList.add("src/test/resources/greenJPEG.jpeg");
        }
        this.applicationContext.setKnownDirectories(differentList);
        assertEquals(differentList, this.applicationContext.getKnownDirectories());
    }

    @Test
    void getKnownImageFiles_returnsCorrectArrayList() {
        //todo: failing test
        ArrayList<ImageFile> expected = generateTestImageFileArrayList();
        assertEquals(expected, this.applicationContext.getKnownImageFiles());
    }

    @Test
    void setKnownImageFiles_updatesArrayList() {
        ArrayList<ImageFile> differentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageFile imageFile = new JPEGImage(new File("src/test/resources/greenJPEG.jpeg"));
            differentList.add(imageFile);
        }
        this.applicationContext.setKnownImageFiles(differentList);
        assertEquals(differentList, this.applicationContext.getKnownImageFiles());
    }

    private TreeSet<FileTag> generateTestFileTagTreeSet() {
        TreeSet<FileTag> testFileTagSet = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            FileTag tag = new FileTag("tag" + i, new Color(0x000000));
            testFileTagSet.add(tag);
        }
        return testFileTagSet;
    }

    private ArrayList<String> generateTestDirectoryArrayList() {
        ArrayList<String> testDirectoryList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            testDirectoryList.add("directory" + i);
        }
        return testDirectoryList;
    }

    private ArrayList<ImageFile> generateTestImageFileArrayList() {
        ArrayList<ImageFile> testImageFileList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageFile imageFile = new PNGImage(new File("src/test/resources/textFile.txt"));
            testImageFileList.add(imageFile);
        }
        return testImageFileList;
    }
}