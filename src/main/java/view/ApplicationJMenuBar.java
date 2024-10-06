package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ApplicationJMenuBar extends JMenuBar implements Renderable {

    private JMenuItem addDirectoryJMenuItem;
    private JTextField directoryPathJTextField;
    private JMenuItem closeApplicationJMenuItem;

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

    @Override
    public void addComponents() {
        add(addDirectoryJMenuItem);
        add(directoryPathJTextField);
        add(closeApplicationJMenuItem);
    }

    @Override
    public void buildComponents() {
        this.addDirectoryJMenuItem = createAddDirectoryJMenuItem();
        this.directoryPathJTextField = createDirectoryPathTextField();
        this.closeApplicationJMenuItem = createCloseApplicationJMenuItem();
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

    private JMenuItem createCloseApplicationJMenuItem() {
        JMenuItem menuItem = new JMenuItem("Close Application");
        addHoverEffect(menuItem);
        return menuItem;
    }

    public JMenuItem getAddDirectoryJMenuItem() {
        return addDirectoryJMenuItem;
    }

    public JTextField getDirectoryPathJTextField() {
        return directoryPathJTextField;
    }

    public JMenuItem getCloseApplicationJMenuItem() {
        return closeApplicationJMenuItem;
    }
}