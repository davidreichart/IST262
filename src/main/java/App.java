import controller.ApplicationController;
import model.ApplicationContext;
import model.Serializer;
import model.data.FileTag;
import model.data.filetypes.SystemDirectory;
import view.ApplicationJFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TreeSet;

public class App implements Runnable {

    private ApplicationContext applicationContext;
    private ApplicationJFrame applicationJFrame;
    private ApplicationController applicationController;

    public App() {

    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        this.applicationJFrame = new ApplicationJFrame();
        this.applicationContext = Serializer.loadState();
        if (this.applicationContext == null) {
            this.applicationContext = new ApplicationContext(new TreeSet<FileTag>());
        }
        this.applicationController = new ApplicationController(this.applicationJFrame, this.applicationContext);
        try {
            this.applicationContext.getSystemDirectoryList().addDirectory(new SystemDirectory("src/test/resources"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        this.applicationJFrame.repaint();

        this.applicationJFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit the program?", "Exit Program Message Box",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    Serializer.saveState(applicationContext);
                    applicationJFrame.dispose();
                    System.exit(0);
                }
            }
        });
    }
}
