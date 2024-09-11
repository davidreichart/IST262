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

    public Set<FileTag> getDefinedTags() {
        return definedTags;
    }

    public void setDefinedTags(Set<FileTag> definedTags) {
        this.definedTags = definedTags;
    }

    public List<String> getKnownDirectories() {
        return knownDirectories;
    }

    public void setKnownDirectories(List<String> knownDirectories) {
        this.knownDirectories = knownDirectories;
    }

    public List<ImageFile> getKnownImageFiles() {
        return knownImageFiles;
    }

    public void setKnownImageFiles(List<ImageFile> knownImageFiles) {
        this.knownImageFiles = knownImageFiles;
    }
}
