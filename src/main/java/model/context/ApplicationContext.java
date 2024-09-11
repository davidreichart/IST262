package model.context;

import model.data.FileTag;
import model.data.images.ImageFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationContext {

    Set<FileTag> definedTags;
    List<String> knownDirectories;
    List<ImageFile> knownImageFiles;

    public ApplicationContext(HashSet<FileTag> definedTags, ArrayList<String> knownDirectories, ArrayList<ImageFile> knownImageFiles) {
        this.definedTags = definedTags;
        this.knownDirectories = knownDirectories;
        this.knownImageFiles = knownImageFiles;
    }
}
