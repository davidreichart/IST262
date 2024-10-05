import model.data.PixelColor;
import model.util.ImageInspector;
import view.BrowserUIFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        runUITest();
    }

    public static void runUITest() {
        BrowserUIFrame browserUIFrame = new BrowserUIFrame();
    }
}
