package ua.nure.lesik.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.db.mapper.Mapper;
import ua.nure.lesik.SummaryTask4.exeption.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of SQL queries
 *
 * @author Ruslan Lesik
 */
public class JdbcTemplate<E> {
    private static final Logger LOG = Logger.getLogger(JdbcTemplate.class);

    public List<E> getAll(Connection connection, String sql, Object[] arr, Mapper<E> mapper) {
        List<E> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < arr.length; i++) {
                ps.setObject(i + 1, arr[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    E object = mapper.extract(rs);
                    list.add(object);
                }
            }
        } catch (SQLException e) {
            LOG.warn("Cannot obtain list of objects!", e);
            throw new DBException(e);
        }
        return list;
    }

    public E get(Connection connection, String sql, Object[] arr, Mapper<E> mapper) {
        E object = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < arr.length; i++) {
                ps.setObject(i + 1, arr[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    object = mapper.extract(rs);
                }
            }
        } catch (SQLException e) {
            LOG.warn("Cannot obtain object!", e);
            throw new DBException(e);
        }
        return object;
    }

    public long update(Connection connection, String sql, Object[] arr) {
        long key = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < arr.length; i++) {
                ps.setObject(i + 1, arr[i]);
            }
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs != null && rs.next()) {
                    key = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            LOG.warn("Cannot update object!", e);
            throw new DBException(e);
        }
        return key;
    }
}

