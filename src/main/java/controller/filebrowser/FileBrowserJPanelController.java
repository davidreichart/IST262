package controller.filebrowser;

import model.context.ApplicationContext;
import view.ApplicationJFrame;

import javax.swing.event.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class FileBrowserJPanelController {

    private ApplicationJFrame frame;
    private ApplicationContext context;
    int lastCheckedDirectoryCount;


    public FileBrowserJPanelController(ApplicationJFrame frame, ApplicationContext context) {
        this.frame = frame;
        this.context = context;
        this.lastCheckedDirectoryCount = 0;

        frame.getFileBrowserJPanel()
                .getExpandAllJButton()
                .addActionListener(expandAllDirectories());
    }

    public ActionListener expandAllDirectories() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < frame.getFileBrowserJPanel().getFileTree().getRowCount(); i++) {
                    frame.getFileBrowserJPanel().getFileTree().expandRow(i);
                }
            }
        };
    }
}
