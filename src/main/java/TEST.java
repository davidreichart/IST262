import model.data.filetypes.ImageFile;

public class TEST {
    public static void main(String[] args) {
        ImageFile imageFile = new ImageFile("C:\\Users\\Snaxx\\Pictures\\gw058.jpg");
        System.out.println(imageFile.METADATA().absoluteFilePath());
        System.out.println(imageFile.IMAGE_METADATA().width());
        System.out.println(imageFile.IMAGE_METADATA().height());
        System.out.println(imageFile.METADATA().byteCount());
        System.out.println(imageFile.IMAGE_METADATA().pixelCount());
        System.out.println(imageFile.METADATA().kilobyteCount());
        System.out.println(imageFile.METADATA().megabyteCount());
        System.out.println(imageFile.METADATA().gigabyteCount());
        System.out.println(imageFile.IMAGE_METADATA().roughColorHistogram().size());
        System.out.println(imageFile.IMAGE_METADATA().preciseColorHistogram().size());
    }
}
