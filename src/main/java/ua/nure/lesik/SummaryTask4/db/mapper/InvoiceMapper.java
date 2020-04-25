package ua.nure.lesik.SummaryTask4.db.mapper;

import ua.nure.lesik.SummaryTask4.entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Invoice object retrieves
 *
 * @author Ruslan Lesik
 */
public class InvoiceMapper implements Mapper<Invoice> {
    @Override
    public Invoice extract(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt(Fields.ID));
        invoice.setDays_count(rs.getInt(Fields.DAYS_COUNT));
        invoice.setReserve_id(rs.getInt(Fields.RESERVE_ID));
        invoice.setUser_id(rs.getInt(Fields.USER_ID));
        invoice.setReckoning(rs.getDouble(Fields.RECKONING));
        invoice.setActive(rs.getBoolean(Fields.IS_ACTIVE));
        return invoice;
    }
}
