package ua.nure.lesik.SummaryTask4.exeption;

/**
 * An exception that provides error information when working with a database
 *
 * @author Ruslan Lesik
 */
public class DBException extends RuntimeException {


    public DBException(Throwable e) {
        super(e);
    }
}

