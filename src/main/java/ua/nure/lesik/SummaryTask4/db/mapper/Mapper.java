package ua.nure.lesik.SummaryTask4.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Basic interface for all Mapper
 *
 * @author Ruslan Lesik
 */
public interface Mapper<E> {
    E extract(ResultSet rs) throws SQLException;
}
