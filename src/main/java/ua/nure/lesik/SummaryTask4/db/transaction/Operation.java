package ua.nure.lesik.SummaryTask4.db.transaction;

import java.sql.Connection;

/**
 * @author Ruslan Lesik
 */
@FunctionalInterface
public interface Operation<E> {

    E execute(Connection connection);
}

