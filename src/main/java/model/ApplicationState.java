package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The ApplicationState class holds persistent data needed by the application. Information stored in this object
 * allows the application to remember user preferences, file locations, and file details (without the need to reexamine
 * each individual file on application launch). State is stored as a byte stream file on disk. <br><br><hr><br>
 * Any interaction with the ApplicationState instance should be done through the ApplicationStateHandler class's static methods.
 */
//todo: this should probably be a singleton
public final class ApplicationState implements Serializable {
    private List<ImageFile> knownFiles;
    private Set<FileTag> definedTags;
    private List<GUIStyleTemplate> definedGUIStyles;
    private GUIStyleTemplate activeStyleTemplate;

    public ApplicationState() {

    }

    /**
     * Resets the application's state. The application will look and function as if it had just been installed.
     * Note that ALL information (known files/directories, user tags, app styles) are lost when this method is called.
     */
    public void recallDefaultState() {
        //todo: are array Lists really the right approach for storing any of this? they're all pretty unique items, so maybe sets?
        this.knownFiles = new ArrayList<>();
        this.definedTags = new HashSet<>();
        this.definedGUIStyles = new ArrayList<>();
        this.activeStyleTemplate.recallDefaultGUIState();
    }

    /**********
     * Class accessors and mutators
     * currently, all class variables have get & set methods.
     ***********/

    public List<ImageFile> getKnownFiles() {
        return knownFiles;
    }

    public void setKnownFiles(List<ImageFile> knownFiles) {
        this.knownFiles = knownFiles;
    }

    public Set<FileTag> getDefinedTags() {
        return definedTags;
    }

    public void setDefinedTags(Set<FileTag> definedTags) {
        this.definedTags = definedTags;
    }

    public List<GUIStyleTemplate> getDefinedGUIStyles() {
        return definedGUIStyles;
    }

    public void setDefinedGUIStyles(List<GUIStyleTemplate> definedGUIStyles) {
        this.definedGUIStyles = definedGUIStyles;
    }

    public GUIStyleTemplate getActiveStyleTemplate() {
        return activeStyleTemplate;
    }

    public void setActiveStyleTemplate(GUIStyleTemplate activeStyleTemplate) {
        this.activeStyleTemplate = activeStyleTemplate;
    }
}