package controller.filebrowser;

import model.ApplicationContext;
import model.data.SortedFileList;
import view.ApplicationJFrame;
import view.filebrowser.FileBrowserJPanel;
import view.filebrowser.SortedFileBrowserJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SortedFileBrowserJPanelController {

    private ApplicationJFrame frame;
    private ApplicationContext context;
    private SortedFileList sortedFileList;
    private SortedFileBrowserJPanel panel;

    public SortedFileBrowserJPanelController(ApplicationJFrame frame, ApplicationContext context) {
        this.frame = frame;
        this.context = context;
        this.sortedFileList = new SortedFileList();
        this.panel = this.frame.getSortedFileBrowserJPanel();

        this.frame.getApplicationJMenuBar()
                .getSwapFileBrowserJMenuItem()
                .addActionListener(refreshList());
    }

    /**
     * Updates the list model of the file browser panel with the sorted list of file names.
     */
    public void renderSortedFileList() {
        this.sortedFileList.findAllFileNames(this.context.getSystemFiles());
        this.sortedFileList.sortFiles();
        this.panel.updateFileList(this.sortedFileList.getAbsoluteFilePaths());
    }

    /**
     * Returns an ActionListener that will refresh the list of files in the file browser panel.
     * This will swap the file browser view modes.
     * @return The ActionListener that will refresh the list of files in the file browser panel.
     */
    public ActionListener refreshList() {
        return e -> {
            JSplitPane temp = this.frame.getTopFileAndBrowserPane();
            if (temp.getLeftComponent() instanceof FileBrowserJPanel) {
                temp.removeAll();
                temp.add(this.panel, JSplitPane.LEFT);
                temp.add(this.frame.getFileDisplayJPanel(), JSplitPane.RIGHT);
                renderSortedFileList();
                this.panel.setVisible(true);
                this.frame.getFileBrowserJPanel().setVisible(false);
            } else if (temp.getLeftComponent() instanceof SortedFileBrowserJPanel) {
                temp.removeAll();
                temp.add(this.frame.getFileBrowserJPanel(), JSplitPane.LEFT);
                temp.add(this.frame.getFileDisplayJPanel(), JSplitPane.RIGHT);
                this.panel.setVisible(false);
                this.frame.getFileBrowserJPanel().setVisible(true);
            }
        };
    }
}
