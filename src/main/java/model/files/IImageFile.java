package model.files;

import java.io.File;

public interface IImageFile {

    public void addFileTag(FileTag fileTag) throws UnsupportedOperationException;

    public void removeFileTag(String tagToRemove) throws IllegalArgumentException;

    File getFile();

    ImageStatistics getImageStatistics();
}
