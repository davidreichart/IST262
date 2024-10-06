import model.data.metadata.ImageMetadata;
import model.data.metadata.Metadata;
import model.data.metadata.TextMetadata;
import model.data.metadata.VideoMetadata;

import java.util.ArrayList;

public class TestHarness {
    public TestHarness() {
        PolymorphismTestHarness polymorphismTestHarness = new PolymorphismTestHarness();
    }

    public static void main(String[] args) {
        TestHarness testHarness = new TestHarness();
    }

    /**
     * This class is intended to test the polymorphism of the Metadata class and its sub-classes.
     * M04-A01: Implementing Inheritance
     */
    private static class PolymorphismTestHarness {

        private ArrayList<Metadata> metadataList = new ArrayList<>();

        public PolymorphismTestHarness() {
            Metadata imageMetadata = ImageMetadata.builder("src/test/resources/bluePNG.png").build();
            // currently there is no test video so this will have default values
            Metadata videoMetadata = VideoMetadata.builder("src/test/resources/shortVideo.mp4").build();
            Metadata textMetadata = TextMetadata.builder("src/test/resources/textFile.txt").build();

            metadataList.add(imageMetadata);
            metadataList.add(videoMetadata);
            metadataList.add(textMetadata);

            for (Metadata metadata : metadataList) {
                System.out.println(metadata.getContentTypeMetadata());
            }
        }
    }
}
