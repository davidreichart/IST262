package controller;

import model.ApplicationContext;
import model.data.filetypes.SystemDirectory;
import view.ApplicationJFrame;

import javax.swing.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationJMenuBarController {

    ApplicationJFrame frame;
    ApplicationContext context;

    public ApplicationJMenuBarController(ApplicationJFrame applicationJFrame, ApplicationContext applicationContext) {
        this.frame = applicationJFrame;
        this.context = applicationContext;

        // add directory needs to take input from the text field and add it to the known directories when clicked
        frame.getApplicationJMenuBar()
                .getAddDirectoryJMenuItem()
                .addActionListener(addDirectory(frame
                        .getApplicationJMenuBar()
                        .getDirectoryPathJTextField()));

        // close application needs to close the application when clicked
        frame.getApplicationJMenuBar()
                .getCloseApplicationJMenuItem()
                .addActionListener(closeApplication());
    }

    /**
     * Creates an action listener that adds a directory to the list of known directories.
     * If the directory path is empty, invalid, or already known by the application, an error message will be displayed.
     * @param directoryPathJTextField The text field containing the directory path to add.
     * @return an action listener that adds a directory to the list of known directories.
     */
    public ActionListener addDirectory(JTextField directoryPathJTextField) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = directoryPathJTextField.getText();
                // don't accept empty input
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a directory path.");
                    return;
                }

                // don't accept an invalid directory path
                if (!Files.isDirectory(Path.of(input))) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid directory path.");
                    return;
                }

                // directory path is valid and not empty
                try {
                    context.getSystemDirectoryList().addDirectory(new SystemDirectory(input));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        };
    }

    /**
     * Creates an action listener that closes the application when the menu item is clicked.
     * @return an action listener that closes the application.
     */
    public ActionListener closeApplication() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
    }
}
