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

    /**
     * Constructor to set up the GUI. 
     * @param ctrl
     *      Controller for file operation handling
     */
    public SimpleGUIWithFileChooser(final Controller ctrl) {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        this.frame.setSize(sw / 4, sh / 4);

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
        fileChooserPanel.add(browseButton, BorderLayout.LINE_END);
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

    /**
     * Put GUI on screen.
     */
    public void start() {
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.start();
    }
}
