package model;

import java.awt.*;

/**
 * An GUIStyleTemplate is a data structure that contains Color objects. These Color objects are called upon by
 * the swing GUI to allow for customization of GUI elements.
 */
public class GUIStyleTemplate {
    private Color backgroundColor;
    private Color accentColorOne;
    private Color textColor;

    /**
     * Creates a new ApplicationStyleTemplate when all possible customization elements are known. This style template
     * is automatically stored in the application's state object at time of instantiation.
     * @param backgroundColor The background color covers the most physical space of the GUI.
     * @param accentColorOne Color of borders in GUI elements.
     * @param textColor The color of all text in the GUI.
     */
    public GUIStyleTemplate(Color backgroundColor, Color accentColorOne, Color textColor) {
        this.backgroundColor = backgroundColor;
        this.accentColorOne = accentColorOne;
        this.textColor = textColor;
    }

    /**
     * Resets all variables defining visual styling of swing GUI elements to the default styles the application ships with.
     */
    public void recallDefaultGUIState() {
        //todo: 1. adjust these during the GUI design process
        //todo: 2. there should probably be an instance of this default state stored in the list of templates that the user isn't allowed to edit
        this.backgroundColor = new Color(0X1e1f22);
        this.accentColorOne = new Color(0x3f4148);
        this.textColor = new Color(0xc3c9e0);
    }

    /**
     * Saves a new Application Style Template to the program's list of known style templates. This allows the user to
     * swap to this newly created template at any time without needing to enter all elements over again.
     * @param backgroundColor The background color covers the most physical space of the GUI.
     * @param accentColorOne Color of borders in GUI elements.
     * @param textColor The color of all text in the GUI.
     */
    private void saveNewStyleTemplate(Color backgroundColor, Color accentColorOne, Color textColor) {
        ApplicationStateHandler.addNewStyleTemplate(
                backgroundColor, accentColorOne, textColor
        );
    }
}
