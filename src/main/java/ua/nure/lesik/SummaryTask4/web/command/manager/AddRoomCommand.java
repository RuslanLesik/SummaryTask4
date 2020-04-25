package ua.nure.lesik.SummaryTask4.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.lesik.SummaryTask4.entity.Room;
import ua.nure.lesik.SummaryTask4.entity.enums.RoomClasses;
import ua.nure.lesik.SummaryTask4.extractor.Extractor;
import ua.nure.lesik.SummaryTask4.extractor.RoomExtractor;
import ua.nure.lesik.SummaryTask4.service.RoomService;
import ua.nure.lesik.SummaryTask4.web.Path;
import ua.nure.lesik.SummaryTask4.web.command.Command;
import ua.nure.lesik.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * The class for create a new room
 *
 * @author Ruslan Lesik
 */
public class AddRoomCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AddRoomCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Command add_room start!");
        if ("GET".equals(request.getMethod())) {
            List<RoomClasses> roomClasses = Arrays.asList(RoomClasses.STANDART, RoomClasses.DE_LUXE, RoomClasses.LUXE);
            request.setAttribute("roomClasses", roomClasses);
            LOG.trace("Command add_room, method GET!");
            return Path.PAGE_ADD_ROOM;
        }

        LOG.debug("Command add_room, add a new room!");
        String image_name = saveImage(request);
        Extractor<Room> extractor = new RoomExtractor();
        Room room = extractor.extract(request);
        room.setImage_name(image_name);

        boolean validateRoom = validateRoom(room);
        if (!validateRoom) {
            request.setAttribute("errorMessage", "The price should be in the range of 1 to 10,000 and max length of description should be 200 symbols!");
            LOG.error("Command add_room, not valid price or description!");
            return Path.PAGE_ERROR_PAGE;
        }
        RoomService roomService = (RoomService) request.getServletContext().getAttribute("roomService");
        boolean isSuccessfully = roomService.createRoom(room);
        if (!isSuccessfully) {
            request.setAttribute("errorMessage", "Cannot create a new room!");
            LOG.error("Command add_room, cannot create a new room!");
            return Path.PAGE_ERROR_PAGE;
        }
        LOG.trace("Command add_room finished!");
        return Path.COMMAND_PROFILE;
    }

    private boolean validateRoom(Room room) {
        return Validator.isValidatePrice(room.getPrice()) && Validator.isValidateDescription(room.getDescription());
    }

    private String saveImage(HttpServletRequest request) throws IOException, ServletException {
        String savePath = request.getServletContext().getRealPath("/images");
        Part part = request.getPart("file");
        String fileName = extractFileName(part);
        part.write(savePath + File.separator + fileName);
        return fileName;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String str : items) {
            if (str.trim().startsWith("filename")) {
                return str.substring(str.indexOf("=") + 2, str.length() - 1);
            }
        }
        return "";
    }
}
