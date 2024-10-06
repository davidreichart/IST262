package view;

/**
 * Renderable objects are swing framework components that can be rendered on the screen.
 */
public interface Renderable {

    /**
     * Sets the default style and behavior of this Renderable object.
     */
    void setAttributes();

    /**
     * Attaches JComponent objects to this Renderable object.
     * {@link #buildComponents()} should be before this method.
     */
    void addComponents();

    /**
     * Builds the JComponent objects attached to this Renderable object.
     * {@link #addComponents()} should be called after this method.
     */
    void buildComponents();
}
