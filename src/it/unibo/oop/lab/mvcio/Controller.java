package it.unibo.oop.lab.mvcio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    private static final String SEP = System.getProperty("file.separator");
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String DEFAULT = "output.txt";

    private File currFile;

    /**
     * 
     * @param file
     *      file to set as current
     */
    public Controller(final File file) {
        this.currFile = file;
    }

    /**
     *  set the default file as current.
     */
    public Controller() {
        this(new File(USER_HOME + SEP + DEFAULT));
    }

    /**
     * 
     * @return the current considered file
     */
    public File getCurrentFile() {
        return currFile;
    }

    /**
     * 
     * @param file 
     *      file to be setted as current
     */
    public void setCurrFile(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            this.currFile = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }

    /**
     * 
     * @param fileName
     *      string format for file to be opened
     */
    public void setCurrFile(final String fileName) {
        this.setCurrFile(new File(fileName));
    }

    /**
     * @return path (as a String) of the current considered file
     */
    public String getCurrFilePath() {
        return  currFile.getPath();
    }

    /**
     * 
     * write some text on the current file.
     * 
     * @param str
     *      String to be written inside current file
     * @throws IOException
     *      if the writing fails
     */
    public void writeOnCurrentFile(final String str) throws IOException {
        try (PrintStream ps = new PrintStream(this.currFile)) {
           ps.println(str);
        }
    }

}
