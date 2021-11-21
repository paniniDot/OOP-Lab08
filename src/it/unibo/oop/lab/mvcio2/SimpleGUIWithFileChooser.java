package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser(final Controller ctrl) {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        this.frame.setSize(sw / 2, sh / 2);

        frame.setLocationByPlatform(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        this.frame.setContentPane(contentPanel);

        final JPanel fileChooserPanel = new JPanel();
        fileChooserPanel.setLayout(new BorderLayout());
        final JTextField currFile = new JTextField("Current file: " + ctrl.getCurrFilePath());
        currFile.setEditable(false);
        final JButton browseButton = new JButton("Browse...");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooseFile = new JFileChooser("Choose where to save");
                if (chooseFile.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    ctrl.setCurrFile(chooseFile.getSelectedFile().toString());
                    currFile.setText("Current file: " + ctrl.getCurrFilePath());
                } else {
                    JOptionPane.showMessageDialog(browseButton, "Operation quitted");
                }
            }
        });
        fileChooserPanel.add(currFile, BorderLayout.CENTER);
        fileChooserPanel.add(browseButton, BorderLayout.SOUTH);
        contentPanel.add(fileChooserPanel, BorderLayout.NORTH);

        final JPanel editorPanel = new JPanel();
        editorPanel.setLayout(new BorderLayout());
        final JTextArea editorArea = new JTextArea();
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ctrl.writeOnCurrentFile(editorArea.getText());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        editorPanel.add(editorArea, BorderLayout.CENTER);
        editorPanel.add(save, BorderLayout.SOUTH);
        contentPanel.add(editorPanel, BorderLayout.CENTER);
    }

    public void start() {
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.start();
    }

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

}
