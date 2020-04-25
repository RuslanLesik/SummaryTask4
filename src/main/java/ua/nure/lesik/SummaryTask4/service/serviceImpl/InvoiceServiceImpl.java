package ua.nure.lesik.SummaryTask4.service.serviceImpl;



import ua.nure.lesik.SummaryTask4.db.dao.InvoiceDao;
import ua.nure.lesik.SummaryTask4.db.dao.ReserveDao;
import ua.nure.lesik.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.lesik.SummaryTask4.entity.Invoice;
import ua.nure.lesik.SummaryTask4.service.InvoiceService;
import java.util.List;

/**
 * The class is a service for working with invoice
 *
 * @author Ruslan Lesik
 */
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceDao invoiceDao;
    private ReserveDao reserveDao;
    private TransactionManager transactionManager;

    public InvoiceServiceImpl(InvoiceDao invoiceDao, ReserveDao reserveDao, TransactionManager transactionManager) {
        this.invoiceDao = invoiceDao;
        this.reserveDao = reserveDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean createInvoice(Invoice invoice) {
        long key = transactionManager.executeOperation(connection -> invoiceDao.addInvoice(invoice, connection));
        return key != 0;
    }

    @Override
    public List<Invoice> getAllInvoicesByUserId(int user_id) {
        return transactionManager.executeOperation(connection -> invoiceDao.getAllInvoicesByUserId(user_id, connection));
    }

    @Override
    public boolean payInvoiceByIdChangeStatusReserve(int invoice_id, int reserve_id) {
        return transactionManager.executeOperation(connection ->
        {
            long key = invoiceDao.changeStatusInvoiceById(invoice_id, connection);
            reserveDao.changeReserveStatusById(reserve_id, connection);
            return key != 0;
        });
    }
}
