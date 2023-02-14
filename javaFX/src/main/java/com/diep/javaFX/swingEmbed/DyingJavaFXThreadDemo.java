package com.diep.javaFX.swingEmbed;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * https://stackoverflow.com/questions/25193198/prevent-javafx-thread-from-dying-with-jfxpanel-swing-interop
 * https://www.jensd.de/wordpress/?p=1843
 *
 * FX Platform Thread is implicitly started in the constructor of JFXPanel by initFX()
 * JFXPanel overrides addNotify() from Component where a finishListener is added to the FX-Platform
 * ( PlatformImpl.addListener(finishListener)).
 * Later on once the JFXPanel has a parent component its addNotify() called, which calls registerFinishListener(),
 * which registers a PlatformImpl.FinishListener() with PlatformImpl.
 * The act of registering the FinishListener prevents the JavaFX thread from dying when PlatformImpl.checkIdle() is called.
 * When a JFXPanel is no longer visible its removeNotify method is call which calls deregisterFinishListener()
 * When instanceCount is zero the FinishListener is removed which causes PlatformImpl to call PlatformImpl.tkExit,
 * causing the JavaFX thread to die
 *
 * Creating another JFXPanel after the JavaFX thread dies will not start another JavaFX thread
 * and thus the JFXPanel will be blank (i.e. Platform.runLater() does not execute to create and show scene)
 * ==================================================================================================================
 * In this demo, we simulate such a situation
 * We have the main frame with a button "What Time?"
 * Once click, it opens a JDialog with JFXPanel embedded showing the current time
 * When user closes dialog, it is disposed
 * It works fine for the first time user clicks on "What Time?". The dialog shows properly
 * From the second time on, the dialog opens but show nothing in content.
 *
 * This is due to FX Platform thread has exited after the first dialog is closed and disposed.
 * Later jobs passed to this thread are not executed, leaving blank JFXPanel
 * ==================================================================================================================
 * The fix is call (somewhere early in main())
 * Platform.setImplicitExit(false);
 * This fix requires a call the Platform.exit() when the application exits otherwise the JavaFX thread will prevent
 * the process from stopping ???
 * Java FX thread is also closed by System.exit()
 */
public class DyingJavaFXThreadDemo {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static void initAndShowGUI() {
        // This method is invoked on the EDT thread
        JFrame frame = new JFrame("Time Telling");
        frame.add(createMainPanel(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    // Create a panel with single button that can trigger opening and closing embedded FX view multiple times
    private static JPanel createMainPanel(JFrame frame) {
        JPanel panel = new JPanel(new BorderLayout());
        JButton button = new JButton("What Time?");
        button.setFont(button.getFont().deriveFont(java.awt.Font.PLAIN, 50));
        button.addActionListener(e -> showFXEmbeddedDialog(frame));
        panel.add(button, BorderLayout.CENTER);
        return panel;
    }

    private static void showFXEmbeddedDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Now");
        final JFXPanel fxPanel = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });
        dialog.setContentPane(fxPanel);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setSize(300, 200);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private static void initFX(JFXPanel fxPanel) {
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private static Scene createScene() {
        Group root = new  Group();
        Scene scene = new  Scene(root, Color.ALICEBLUE);
        Text text  =  new  Text();

        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText(LocalDateTime.now().format(dtf));

        root.getChildren().add(text);

        return (scene);
    }

    public static void main(String[] args) {
        // Comment below line to see the effect without setting this
        Platform.setImplicitExit(false);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });
    }
}
