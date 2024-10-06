package view;

/**
 * Renderable objects are swing framework components that can be rendered on the screen.
 */
public interface Renderable {

    /**
     * Attaches JComponent objects to this Renderable object.
     * {@link #buildComponents()} should be before this method.
     */
    public void addComponents();

    /**
     * Builds the JComponent objects attached to this Renderable object.
     * {@link #addComponents()} should be called after this method.
     */
    public void buildComponents();
}
