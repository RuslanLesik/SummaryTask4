package ua.nure.lesik.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.Invoice;
import ua.nure.lesik.SummaryTask4.entity.User;
import ua.nure.lesik.SummaryTask4.service.InvoiceService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class for obtain and processing invoices
 *
 * @author Ruslan Lesik
 */
public class InvoiceCommand implements Command {

    private static final Logger LOG = Logger.getLogger(InvoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command invoice start!");
        InvoiceService invoiceService = (InvoiceService) request.getServletContext().getAttribute("invoiceService");

        if ("GET".equals(request.getMethod())) {
            User user = (User) request.getSession(false).getAttribute("user");
            List<Invoice> invoiceList = invoiceService.getAllInvoicesByUserId(user.getId());
            request.setAttribute("invoiceList", invoiceList);
            LOG.debug("Command invoice, method GET!");
            return Path.PAGE_INVOICE;
        }

        String reserve_id = request.getParameter("reserve_id");
        String invoice_id = request.getParameter("invoice_id");
        if (reserve_id != null && invoice_id != null) {
            int reserveId = Integer.parseInt(reserve_id);
            int invoiceId = Integer.parseInt(invoice_id);
            invoiceService.payInvoiceByIdChangeStatusReserve(invoiceId, reserveId);
            LOG.debug("Command invoice, paid bill!");
        }
        LOG.trace("Command invoice finished!");
        return Path.COMMAND_INVOICE;
    }
}
