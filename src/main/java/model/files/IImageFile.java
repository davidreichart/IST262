package model.data;

import java.io.File;

public interface IImageFile {

    boolean validateFileType(File file);

    File getFile();
}
