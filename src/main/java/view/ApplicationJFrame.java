package view;

import javax.swing.*;

public class ApplicationJFrame extends JFrame implements Renderable {

    private ApplicationJMenuBar applicationJMenuBar;

    public ApplicationJFrame() {
        setAttributes();
        buildComponents();
        addComponents();
        setVisible(true);
    }

    private void setAttributes() {
        setTitle("IST 261");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void addComponents() {
        setJMenuBar(applicationJMenuBar);
    }

    @Override
    public void buildComponents() {
        this.applicationJMenuBar = new ApplicationJMenuBar();
    }

    public ApplicationJMenuBar getApplicationJMenuBar() {
        return this.applicationJMenuBar;
    }
}
