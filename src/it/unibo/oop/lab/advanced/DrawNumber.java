package it.unibo.oop.lab.advanced;

/**
 * This class models a draw number game.
 *
 */
public interface DrawNumber {

    /**
     * resets the game.
     */
    void reset();

    /**
     * Guesses a number.
     * 
     * @param n
     *            the guess
     * @return the result of the guess
     * @throws AttemptsLimitReachedException
     *             in case the game is lost
     */
    DrawResult attempt(int n) throws AttemptsLimitReachedException;

    /**
     * Displays a possible error occurred while playing.
     * 
     * @param message
     *            error message to let the GUI shows
     */
    void displayError(String message);
}
