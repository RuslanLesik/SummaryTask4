package ua.nure.lesik.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.Invoice;
import ua.nure.lesik.SummaryTask4.entity.Reserve;
import ua.nure.lesik.SummaryTask4.extractor.Extractor;
import ua.nure.lesik.SummaryTask4.extractor.ReserveExtractor;
import ua.nure.lesik.SummaryTask4.service.InvoiceService;
import ua.nure.lesik.SummaryTask4.service.ReserveService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * The class for submit reservation
 *
 * @author Ruslan Lesik
 */
public class SubmitReservationCommand implements Command {

    private static final Logger LOG = Logger.getLogger(SubmitReservationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command submit_reservation start!");
        ReserveService reserveService = (ReserveService) request.getServletContext().getAttribute("reserveService");
        InvoiceService invoiceService = (InvoiceService) request.getServletContext().getAttribute("invoiceService");
        Extractor<Reserve> reserveExtractor = new ReserveExtractor();
        Reserve reserve = reserveExtractor.extract(request);
        LocalDate check_in;
        LocalDate check_out;
        try {
            check_in = LocalDate.parse(reserve.getCheck_in());
            check_out = LocalDate.parse(reserve.getCheck_out());
        } catch (DateTimeParseException e) {
            request.setAttribute("errorMessage", "Please select a date, the field cannot be empty!");
            LOG.error("Command submit_reservation, incorrect date!");
            return Path.PAGE_ERROR_PAGE;
        }
        if (!isFreeDateForReserve(check_in, check_out, reserve, reserveService)) {
            request.setAttribute("errorMessage", "The selected date is already booked, please see the list of booked dates");
            LOG.error("Command submit_reservation, date is already booked!");
            return Path.PAGE_ERROR_PAGE;
        }
        long days_count = ChronoUnit.DAYS.between(check_in, check_out);
        if (days_count == 0) {
            request.setAttribute("errorMessage", "Minimum booking period is 1 day");
            LOG.error("Command submit_reservation, days_count less than 1!");
            return Path.PAGE_ERROR_PAGE;
        }
        double price_room = days_count * reserve.getPrice();
        reserveService.createReserve(reserve);
        Reserve reserve_for_id = reserveService.getReserveByDateUserAndRoomID(check_in.toString(), check_out.toString(), reserve.getUser_id(), reserve.getRoom_id());
        Invoice invoice = setInvoice(reserve_for_id, days_count, price_room);
        boolean isSuccessfully = invoiceService.createInvoice(invoice);
        if (!isSuccessfully) {
            request.setAttribute("errorMessage", "Cannot create a new invoice!");
            LOG.error("Command submit_reservation, cannot create a new invoice!");
            return Path.PAGE_ERROR_PAGE;
        }
        LOG.trace("Command submit_reservation finished!");
        return Path.COMMAND_PROFILE;
    }

    private Invoice setInvoice(Reserve reserve_for_id, long days_count, double price_room) {
        Invoice invoice = new Invoice();
        invoice.setDays_count((int) days_count);
        invoice.setReserve_id(reserve_for_id.getId());
        invoice.setUser_id(reserve_for_id.getUser_id());
        invoice.setReckoning(price_room);
        return invoice;
    }

    private boolean isFreeDateForReserve(LocalDate check_in, LocalDate check_out, Reserve reserve, ReserveService reserveService) {
        List<Reserve> reserveList = reserveService.getAllReservesByRoomId(reserve.getRoom_id());
        for (Reserve res : reserveList) {
            LocalDate in = LocalDate.parse(res.getCheck_in());
            LocalDate out = LocalDate.parse(res.getCheck_out());
            if (
                    (check_in.isEqual(in) || check_in.isEqual(out)) ||
                            (check_out.isEqual(in) || check_out.isEqual(out)) ||
                            (check_in.isAfter(in) && check_in.isBefore(out)) ||
                            (check_out.isAfter(in) && check_out.isBefore(out)) ||
                            (check_in.isBefore(in) && check_out.isAfter(out))
                    ) {
                return false;
            }
        }
        return true;
    }
}
