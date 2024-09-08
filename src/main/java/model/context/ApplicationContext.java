package model.context;

import model.files.FileTag;
import model.files.IImageFile;

import java.util.List;

public class ApplicationContext {

    private List<FileTag> definedFileTags;
    private List<GUIStyleTemplate> definedStyleTemplates;
    private List<String> knownDirectories;
    private List<IImageFile> knownImageFiles;
}
