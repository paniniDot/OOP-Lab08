package it.unibo.oop.lab.mvcio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 */
public class Controller {

    private static final String SEP = System.getProperty("file.separator");
    private static final String USER_HOME = System.getProperty("user.home");

    private File currFile;

    public Controller(final File file) {
        this.currFile = file;
    }

    public Controller() {
        this(new File(USER_HOME + SEP + "output.txt"));
    }

    /**
     * 
     * @return the current considered file
     */
    public File getCurrFile() {
        return currFile;
    }

    /**
     * 
     * @param file 
     *      file to be setted as current
     */
    public void setCurrFile(final File file) {
        this.currFile = file;
    }

    /**
     * 
     * @return the canonical path (as a String) of the current considered file
     * @throws IOException
     */
    public String getCurrFilePath() throws IOException {
        return  currFile.getCanonicalPath();
    }

    private boolean existsAndIsWrittable() {
        return this.currFile.exists() && this.currFile.canWrite();
    }

    /**
     * 
     * @param str
     *      String to be written inside current file
     * @throws IOException
     */
    public void writeOnCurrentFile(final String str) throws IOException {
           if (this.existsAndIsWrittable()) {
               try (BufferedWriter bw = new BufferedWriter(new FileWriter(currFile))) {
                   bw.write(str);
               }
           } else {
               throw new IOException();
           }
    }

}
