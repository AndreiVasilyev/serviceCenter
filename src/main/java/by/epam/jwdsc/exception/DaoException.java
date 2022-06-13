package by.epam.jwdsc.exception;

/**
 * The type Dao exception.
 */
public class DaoException extends Exception {
    /**
     * Instantiates a new Dao exception.
     */
    public DaoException() {
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param cause the cause
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
}
