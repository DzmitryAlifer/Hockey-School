package by.epam.hockeyschool.exception;

/**
 * Created by Dmitry Olifer on 18.06.2015.
 *
 * The class {@code TechnicalException} is a form of {@code Exception}
 * that indicates conditions that a reasonable application might
 * want to catch if there are any technical errors occurs.
 */
public class TechnicalException extends Exception {

    /**
     * Constructs a new technical exception with {@code null} as its detail
     * message. The cause is not initialized, and may subsequently be
     * initialized by a call to {@code initCause}.
     */
    public TechnicalException() {
    }

    /**
     * Constructs a new technical exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@code #initCause}.
     * @param   message  the detail message. The detail message is saved for
     *          later retrieval by the {@code getMessage()} method.
     */
    public TechnicalException(String message) {
        super(message);
    }

    /**
     * Constructs a new technical exception with the specified detail
     * message and cause. Note that the detail message associated with
     * {@code cause} is not automatically incorporated in this exception's
     * detail message.
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@code getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@code getCause()} method).  A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new technical exception with the specified cause and a
     * typical detail message. This constructor is useful for exceptions that
     * are little more than wrappers for other exceptions.
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@code getCause()} method).
     */
    public TechnicalException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new technical exception with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())} which
     * typically contains the class and detail message of {@code cause}.
     * This constructor is useful for exceptions that are little more than
     * wrappers for other exceptions.
     * @param  cause the cause which is saved for later retrieval by the
     *         {@code getCause()} method.
     */
    public TechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
