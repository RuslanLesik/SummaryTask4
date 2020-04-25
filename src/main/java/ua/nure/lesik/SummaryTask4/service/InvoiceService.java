package ua.nure.lesik.SummaryTask4.service;

import ua.nure.lesik.SummaryTask4.entity.Invoice;

import java.util.List;

/**
 * Interface defines methods for invoice service
 *
 * @author Ruslan Lesik
 */
public interface InvoiceService {

    /**
     * Create invoice
     *
     * @param invoice Invoice
     *
     * @return true if invoice has been created
     */
    boolean createInvoice(Invoice invoice);

    /**
     * Find list of invoices by user_id
     *
     * @param user_id int
     *
     * @return List of invoices
     */
    List<Invoice> getAllInvoicesByUserId(int user_id);

    /**
     * Pay invoice by invoice_id and change status reserve
     *
     * @param invoice_id int
     * @param reserve_id int
     *
     * @return true if transaction has been successfully
     */
    boolean payInvoiceByIdChangeStatusReserve(int invoice_id, int reserve_id);
}
