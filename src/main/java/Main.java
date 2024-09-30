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

        browserUIFrame.addComponentListener(new ComponentAdapter() {
            private int previousWidth = browserUIFrame.getWidth();
            private int previousHeight = browserUIFrame.getHeight();
            private int previousHorizontalSPLocation = browserUIFrame.getHorizontalSplitPane().getDividerLocation();
            private int previousVerticalSPLocation = browserUIFrame.getVerticalSplitPane().getDividerLocation();
            private int delay = 100;
            private Timer delayTimer;

            //todo: trying to make the splits retain their shape as the window is resized, not currently working as desired
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                if (delayTimer != null && delayTimer.isRunning()) {
                    delayTimer.stop();
                }
                delayTimer = new Timer(delay, actionEvent -> {
                    int newWidth = componentEvent.getComponent().getWidth(); // the height that this frame is now
                    int newHeight = componentEvent.getComponent().getHeight(); // the width that this frame is now

                    int widthChange = previousWidth - newWidth; // the frame changed by this many pixels in width
                    int heightChange = previousHeight - newHeight; // the frame changed by this many pixels in height

                    int pWidthOnePercent = previousWidth / 100; // 1% of the previous width
                    int pHeightOnePercent = previousHeight / 100; // 1% of the previous height

                    int widthPercentChange = widthChange / pWidthOnePercent; // by what % did this change?
                    int heightPercentChange = heightChange / pHeightOnePercent;


                    // how many pixels did the frame increase/decrease by?
                    int pixelDifferenceHeight = newHeight - previousHeight;
                    int pixelDifferenceWidth = newWidth - previousWidth;

                    int onePercentPreviousHeight = previousHeight / 100;
                    int onePercentPreviousWidth = previousWidth / 100;





                    /**************************/

                    int hPercentMakeup = previousHorizontalSPLocation / pWidthOnePercent;
                    int vPercentMakeup = previousVerticalSPLocation / pHeightOnePercent;

                    System.out.printf("prevHeight: %d  prevWid: %d    newHeight: %d newWid: %d    heightCh: %d widCh: %d\n",
                            previousHeight, previousWidth, newHeight, newWidth, heightPercentChange, widthPercentChange);
                    System.out.printf("hPercentMakeup: %d  vPercentMakeup: %d\n",
                            hPercentMakeup, vPercentMakeup);
                    previousWidth = newWidth;
                    previousHeight = newHeight;
                    delayTimer.stop();
                });
                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        });
    }
}
