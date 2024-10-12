import model.ApplicationContext;
import model.data.FileTag;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextTest {

    @Test
    public void addNewFileTag_addsNewTag() {
        // Arrange
        ApplicationContext context = new ApplicationContext(new TreeSet<>());
        FileTag tag = new FileTag("tag", Color.BLUE);

        // Act
        context.addNewFileTag(tag);

        // Assert
        assertTrue(context.getDefinedTags().contains(tag));
    }

    @Test
    public void removeExistingFileTag_removesTag() {
        // Arrange
        ApplicationContext context = new ApplicationContext(new TreeSet<>());
        FileTag tag = new FileTag("tag", Color.BLUE);
        context.addNewFileTag(tag);

        // Act
        context.removeExistingFileTag("tag");

        // Assert
        assertFalse(context.getDefinedTags().contains(tag));
    }

    @Test
    public void removeExistingFileTag_throwsException() {
        // Arrange
        ApplicationContext context = new ApplicationContext(new TreeSet<>());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> context.removeExistingFileTag("tag"));
    }

    @Test
    public void getDefinedTags_returnsTags() {
        // Arrange
        ApplicationContext context = new ApplicationContext(new TreeSet<>());
        FileTag tag = new FileTag("tag", Color.BLUE);
        context.addNewFileTag(tag);

        // Act
        TreeSet<FileTag> tags = context.getDefinedTags();

        // Assert
        assertTrue(tags.contains(tag));
    }

    @Test
    public void setDefinedTags_setsTags() {
        // Arrange
        ApplicationContext context = new ApplicationContext(new TreeSet<>());
        FileTag tag = new FileTag("tag", Color.BLUE);
        TreeSet<FileTag> tags = new TreeSet<>();
        tags.add(tag);

        // Act
        context.setDefinedTags(tags);

        // Assert
        assertTrue(context.getDefinedTags().contains(tag));
    }

    @Test
    public void getSystemDirectoryList_returnsDirectoryList() {
        // Arrange
        ApplicationContext context = new ApplicationContext(new TreeSet<>());

        // Act
        // Assert
        assertNotNull(context.getSystemDirectoryList());
    }
}
