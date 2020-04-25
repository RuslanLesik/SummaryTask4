package ua.nure.lesik.SummaryTask4.db.dao;

import ua.nure.lesik.SummaryTask4.entity.Invoice;

import java.sql.Connection;
import java.util.List;

/**
 * Interface defines methods for invoice dao
 *
 * @author Ruslan Lesik
 */
public interface InvoiceDao {

    long changeStatusInvoiceById(int id, Connection connection);

    long addInvoice(Invoice invoice, Connection connection);

    List<Invoice> getAllInvoicesByUserId(int user_id, Connection connection);
}
