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

    /**
     * Returns a list of all Image Files known by the application. The ImageFile objects in this list contain all data
     * (File, Statistics, Tags) known about each file.
     * @return A list of all known Image File objects on the user's system.
     */
    public List<ImageFile> getKnownFiles() {
        return knownFiles;
    }

    /**
     * Replaces the currently stored list of ImageFile objects known by the application.
     * The ImageFile objects in this list contain all data (File, Statistics, Tags) known about each file.
     * @param knownFiles A list of File Objects. File Objects should all point to items on the user's system.
     */
    public void setKnownFiles(List<ImageFile> knownFiles) {
        this.knownFiles = knownFiles;
    }

    /**
     * Returns a Set containing all file tags that have either been defined by the user, or pre-included with the
     * application.
     * @return A set containing all stored file tags.
     */
    public Set<FileTag> getDefinedTags() {
        return definedTags;
    }

    /**
     * Replaces the currently known list of file tags with a new Set of tags. All previous tags are lost unless included
     * in the provided set.
     * @param definedTags The new set of FileTags to replace the current set known by the application.
     */
    public void setDefinedTags(Set<FileTag> definedTags) {
        this.definedTags = definedTags;
    }

    /**
     * Returns a list of all GUI style templates stored by the application. GUI style templates define how swing GUI
     * elements should appear, primarily concerning their color.
     * @return A list of all GUIStyleTemplates stored by the application.
     */
    public List<GUIStyleTemplate> getDefinedGUIStyles() {
        return definedGUIStyles;
    }

    /**
     * Replaces the currently stored list of GUI Style Templates stored by the application. GUI style templates define how swing GUI
     * elements should appear, primarily concerning their color. Existing GUI style templates will be lost unless
     * included in the new list given to this method.
     * @param definedGUIStyles The new list of GUIStyleTemplates to be stored by the application.
     */
    public void setDefinedGUIStyles(List<GUIStyleTemplate> definedGUIStyles) {
        this.definedGUIStyles = definedGUIStyles;
    }

    /**
     * Returns the GUIStyleTemplate being used by the application to define its visual appearance for the user.
     * @return the GUIStyleTemplate being used by the application to define its visual appearance for the user
     */
    public GUIStyleTemplate getActiveStyleTemplate() {
        return activeStyleTemplate;
    }

    /**
     * Informs the application which GUIStyleTemplate should be used to define visual appearances of swing GUI elements.
     * The previously active style template will still be stored in a list, so it will not be lost and can be switched back to.
     * @param activeStyleTemplate The GUIStyleTemplate that should define swing GUI elements' appearances.
     */
    public void setActiveStyleTemplate(GUIStyleTemplate activeStyleTemplate) {
        this.activeStyleTemplate = activeStyleTemplate;
    }
}