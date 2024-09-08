package model.context;

import model.files.FileTag;
import model.files.IImageFile;

import java.util.List;

public class ApplicationContext {

    private List<FileTag> definedFileTags;
    //private List<GUIStyleTemplate> definedStyleTemplates;
    private List<String> knownDirectories;
    private List<IImageFile> knownImageFiles;
/*
    public ApplicationContext(List<FileTag> definedFileTags, List<GUIStyleTemplate> definedStyleTemplates,
                              List<String> knownDirectories, List<IImageFile> knownImageFiles) {
        this.definedFileTags = definedFileTags;
        this.definedStyleTemplates = definedStyleTemplates;
        this.knownDirectories = knownDirectories;
        this.knownImageFiles = knownImageFiles;
    }

 */
}
