package ua.nure.lesik.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.web.command.client.DeleteAnswerCommand;
import ua.nure.lesik.SummaryTask4.web.command.client.InvoiceCommand;
import ua.nure.lesik.SummaryTask4.web.command.client.RegistrationCommand;
import ua.nure.lesik.SummaryTask4.web.command.client.RequestClientCommand;
import ua.nure.lesik.SummaryTask4.web.command.client.ReservationCommand;
import ua.nure.lesik.SummaryTask4.web.command.client.ReservationListCommand;
import ua.nure.lesik.SummaryTask4.web.command.client.SubmitReservationCommand;
import ua.nure.lesik.SummaryTask4.web.command.manager.AddRoomCommand;
import ua.nure.lesik.SummaryTask4.web.command.manager.ChangeRoomCommand;
import ua.nure.lesik.SummaryTask4.web.command.manager.RequestListCommand;
import ua.nure.lesik.SummaryTask4.web.command.manager.UserListCommand;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands
 *
 * @author Ruslan Lesik
 */
public final class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);
    private static Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("index", new IndexCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("error", new ErrorCommand());
        commands.put("change_language", new LanguageCommand());
        commands.put("comment", new CommentCommand());

        commands.put("add_room", new AddRoomCommand());
        commands.put("user_list", new UserListCommand());
        commands.put("change_room", new ChangeRoomCommand());
        commands.put("request_list", new RequestListCommand());

        commands.put("client_request", new RequestClientCommand());
        commands.put("reservation", new ReservationCommand());
        commands.put("submit_reservation", new SubmitReservationCommand());
        commands.put("invoice", new InvoiceCommand());
        commands.put("delete_answer", new DeleteAnswerCommand());
        commands.put("list_reservation", new ReservationListCommand());

    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found:  " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
