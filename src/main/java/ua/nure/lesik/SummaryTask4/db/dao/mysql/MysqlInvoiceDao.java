package ua.nure.lesik.SummaryTask4.db.dao.mysql;

import ua.nure.lesik.SummaryTask4.db.dao.InvoiceDao;
import ua.nure.lesik.SummaryTask4.db.dao.JdbcTemplate;
import ua.nure.lesik.SummaryTask4.db.mapper.InvoiceMapper;
import ua.nure.lesik.SummaryTask4.entity.Invoice;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object for Invoice
 *
 * @author Ruslan Lesik
 */
public class MysqlInvoiceDao implements InvoiceDao {

    private static final String GET_ALL_INVOICE = "SELECT * FROM invoice WHERE user_id=?;";
    private static final String CHANGE_INVOICE = "UPDATE invoice SET isActive=true WHERE id=?;";
    private static final String INSERT_INVOICE = "INSERT INTO invoice (days_count, reserve_id, user_id, reckoning)"
            + " VALUES (?, ?, ?, ?)";

    private JdbcTemplate<Invoice> jdbcTemplate;

    public MysqlInvoiceDao(JdbcTemplate<Invoice> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long changeStatusInvoiceById(int id, Connection connection) {
        return jdbcTemplate.update(connection, CHANGE_INVOICE, new Object[]{id});
    }

    @Override
    public long addInvoice(Invoice invoice, Connection connection) {
        return jdbcTemplate.update(connection, INSERT_INVOICE, new Object[]{
               invoice.getDays_count(), invoice.getReserve_id(), invoice.getUser_id(), invoice.getReckoning()
        });
    }

    @Override
    public List<Invoice> getAllInvoicesByUserId(int user_id, Connection connection) {
        return jdbcTemplate.getAll(connection, GET_ALL_INVOICE, new Object[]{user_id}, new InvoiceMapper());
    }
}
