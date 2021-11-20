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
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

}
