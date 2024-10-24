package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A JMenuBar that contains menu items for adding a directory, entering a directory path, and closing the application.
 */
public class ApplicationJMenuBar extends JMenuBar implements Renderable {

    private JMenuItem addDirectoryJMenuItem;
    private JTextField directoryPathJTextField;
    private JMenuItem closeApplicationJMenuItem;
    private JProgressBar progressBar;

    public ApplicationJMenuBar() {
        setAttributes();
        buildComponents();
        addComponents();
    }

    /**
     * Sets the default style and behavior of this Renderable object.
     */
    @Override
    public void setAttributes() {
    }

    /**
     * Attaches JComponent objects to this Renderable object.
     * {@link #buildComponents()} should be before this method.
     */
    @Override
    public void addComponents() {
        add(addDirectoryJMenuItem);
        add(directoryPathJTextField);
        add(closeApplicationJMenuItem);
        add(progressBar);
    }

    /**
     * Builds the JComponent objects that will be attached to this Renderable object.
     */
    @Override
    public void buildComponents() {
        this.addDirectoryJMenuItem = createAddDirectoryJMenuItem();
        this.directoryPathJTextField = createDirectoryPathTextField();
        this.closeApplicationJMenuItem = createCloseApplicationJMenuItem();
        this.progressBar = createProgressBar();
    }

    /**
     * Adds a highlight effect and changes the cursor to a hand cursor when the user hovers over the menu item.
     * @param menuItem the menu item to add hover effect to
     */
    private void addHoverEffect(JMenuItem menuItem) {
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuItem.setBackground(Color.LIGHT_GRAY);
                menuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuItem.setBackground(Color.WHITE);
            }
        });
    }

    /**
     * Creates a JMenuItem that, when clicked, adds the directory path entered by the user to the list of directories.
     * The directory path added is the text entered in the {@link #directoryPathJTextField}.
     * @return a JMenuItem that allows the user to add a directory
     */
    private JMenuItem createAddDirectoryJMenuItem() {
        JMenuItem menuItem = new JMenuItem("Add Directory");
        addHoverEffect(menuItem);
        return menuItem;
    }

    /**
     * Creates a JTextField that allows the user to enter a directory path.
     * The directory path entered is added to the list of directories when the user clicks the {@link #addDirectoryJMenuItem}.
     * @return a JTextField that allows the user to enter a directory path
     */
    private JTextField createDirectoryPathTextField() {
        JTextField textField = new JTextField();
        setHint(textField, "Enter directory path");
        return textField;
    }

    /**
     * Sets a hint for a text field that disappears when the user clicks on the text field.
     * @param textField the text field to set a hint for.
     * @param hint The hint text to display in the text field.
     */
    private void setHint(final JTextField textField, final String hint) {
        textField.setText(hint);
        textField.setForeground(Color.GRAY);

        // Add focus listener to remove a hint when the user clicks on text field
        // Hint will reappear if the user does not enter any text
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(hint)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setText(hint);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    /**
     * Creates a JMenuItem that, when clicked, closes the application.
     * @return a JMenuItem that allows the user to close the application
     */
    private JMenuItem createCloseApplicationJMenuItem() {
        JMenuItem menuItem = new JMenuItem("Close Application");
        addHoverEffect(menuItem);
        return menuItem;
    }

    /**
     * Creates a JProgressBar that is indeterminate and not visible.
     * This will show the progress of a background task that adds a directory.
     * @return a JProgressBar that shows the progress of a background task
     */
    private JProgressBar createProgressBar() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        return progressBar;
    }

    /**
     * Gets the JMenuItem that allows the user to add a directory.
     * This control references the contents of the {@link #directoryPathJTextField} for the directory path to add.
     * @return a JMenuItem that allows the user to add a directory
     */
    public JMenuItem getAddDirectoryJMenuItem() {
        return addDirectoryJMenuItem;
    }

    /**
     * Gets the JTextField that allows the user to enter a directory path.
     * The directory path entered is added to the list of directories when the user clicks the {@link #addDirectoryJMenuItem}.
     * @return a JTextField that allows the user to enter a directory path
     */
    public JTextField getDirectoryPathJTextField() {
        return directoryPathJTextField;
    }

    /**
     * Gets the JMenuItem that allows the user to close the application.
     * @return a JMenuItem that allows the user to close the application
     */
    public JMenuItem getCloseApplicationJMenuItem() {
        return closeApplicationJMenuItem;
    }

    /**
     * Gets the JProgressBar that shows the progress of a background task.
     * This progress bar is indeterminate and not visible by default.
     * Be sure to set it to visible when a background task is running and set it to invisible when the task is complete.
     * @return a JProgressBar that shows the progress of a background task
     */
    public JProgressBar getProgressBar() {
        return progressBar;
    }
}