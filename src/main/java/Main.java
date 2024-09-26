import view.BrowserUIFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        BrowserUIFrame browserUIFrame = new BrowserUIFrame();

        browserUIFrame.addComponentListener(new ComponentAdapter() {
            private int previousWidth = browserUIFrame.getWidth();
            private int previousHeight = browserUIFrame.getHeight();
            private int previousHorizontalSPLocation = browserUIFrame.getHorizontalSplitPane().getDividerLocation();
            private int previousVerticalSPLocation = browserUIFrame.getVerticalSplitPane().getDividerLocation();
            private int delay = 100;
            private Timer delayTimer;

            @Override
            public void componentResized(ComponentEvent componentEvent) {
                if (delayTimer != null && delayTimer.isRunning()) {
                    delayTimer.stop();
                }
                delayTimer = new Timer(delay, actionEvent -> {
                    int newWidth = componentEvent.getComponent().getWidth();
                    int newHeight = componentEvent.getComponent().getHeight();

                    int widthChange = previousWidth - newWidth; // # of pixels changed, absolute value
                    int heightChange = previousHeight - newHeight;

                    int pWidthOnePercent = previousWidth / 100; // 1% of the previous size
                    int pHeightOnePercent = previousHeight / 100;

                    int widthPercentChange = widthChange / pWidthOnePercent; // by what % did this change?
                    int heightPercentChange = heightChange / pHeightOnePercent;

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
