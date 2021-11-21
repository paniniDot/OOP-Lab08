package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private final List<String> history;
    private String current;

    public ControllerImpl() {
        this.history = new ArrayList<>();
        this.current = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStringToprint(final String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        this.history.add(str);
        this.current = str;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStringToPrint() {
        if (this.current == null) {
            throw new IllegalStateException();
        }
        return this.current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getStringsHistory() {
        return this.history;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printCurrentString() {
        if (this.current == null) {
            throw new IllegalStateException();
        }
        System.out.println(this.current);
    }

}
