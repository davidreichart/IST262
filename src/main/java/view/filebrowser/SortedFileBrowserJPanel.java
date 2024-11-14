package view.filebrowser;

import model.data.filetypes.SystemDirectory;
import model.data.filetypes.SystemDirectoryListListener;
import view.Renderable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class SortedFileBrowserJPanel extends JPanel implements Renderable, SystemDirectoryListListener {

    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton printButton;

    public SortedFileBrowserJPanel() {
        setAttributes();
        buildComponents();
        addComponents();

        setVisible(true);
    }

    /**
     * Method to be called when the list of SystemDirectories is updated.
     * Any logic that needs to respond to the list of SystemDirectories being updated should be placed here.
     *
     * @param systemDirectories The updated list of SystemDirectories.
     */
    @Override
    public void systemDirectoriesListUpdated(HashSet<SystemDirectory> systemDirectories) {

    }

    /**
     * Sets the default style and behavior of this Renderable object.
     */
    @Override
    public void setAttributes() {
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setLayout(new BorderLayout());
    }

    /**
     * Attaches JComponent objects to this Renderable object.
     * {@link #buildComponents()} should be before this method.
     */
    @Override
    public void addComponents() {
        add(new JScrollPane(fileList), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(addItemButton);
        buttons.add(removeItemButton);
        buttons.add(searchButton);

        JPanel search = new JPanel();
        search.add(searchField);

        JPanel controls = new JPanel();
        controls.setLayout(new BorderLayout());
        controls.add(search, BorderLayout.NORTH);
        controls.add(buttons, BorderLayout.SOUTH);

        add(controls, BorderLayout.NORTH);
        add(printButton, BorderLayout.SOUTH);
    }

    /**
     * Builds the JComponent objects attached to this Renderable object.
     * {@link #addComponents()} should be called after this method.
     */
    @Override
    public void buildComponents() {
        this.listModel = new DefaultListModel<>();
        this.fileList = new JList<>(listModel);
        this.addItemButton = new JButton("Add Item");
        this.removeItemButton = new JButton("Remove Item");
        this.searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 20));
        setHint(searchField, "Enter the absolute file path to search/add/remove");
        this.searchButton = new JButton("Search");
        this.printButton = new JButton("Print to Console");
    }

    /**
     * Appends all the file names to the list model.
     * This method expects to receive a list of file names that are already sorted and will not alter the order.
     * @param sortedFileNames The list of file names to append to the list model.
     */
    public void updateFileList(ArrayList<String> sortedFileNames) {
        listModel.clear();

        for (String fileName : sortedFileNames) {
            listModel.addElement(fileName);
        }
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
     * Returns the JList object that displays the sorted file names.
     * @return The JList object that displays the sorted file names.
     */
    public JList<String> getFileList() {
        return fileList;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getRemoveItemButton() {
        return removeItemButton;
    }

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public JButton getPrintButton() {
        return printButton;
    }
}
