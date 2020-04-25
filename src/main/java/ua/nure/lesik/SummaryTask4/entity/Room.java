package ua.nure.lesik.SummaryTask4.entity;

import ua.nure.lesik.SummaryTask4.entity.enums.RoomClasses;
import ua.nure.lesik.SummaryTask4.entity.enums.Status;

import java.util.Objects;

/**
 * The class describes entity room.
 *
 * @author Ruslan Lesik
 */
public class Room {

    private int id;
    private int number_of_places;
    private double price;
    private RoomClasses room_classes;
    private Status room_status;
    private String description;
    private String image_name;

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Status getRoom_status() {
        return room_status;
    }

    public void setRoom_status(Status room_status) {
        this.room_status = room_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_of_places() {
        return number_of_places;
    }

    public void setNumber_of_places(int number_of_places) {
        this.number_of_places = number_of_places;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomClasses getRoom_classes() {
        return room_classes;
    }

    public void setRoom_classes(RoomClasses room_classes) {
        this.room_classes = room_classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number_of_places == room.number_of_places &&
                room_classes == room.room_classes &&
                Objects.equals(image_name, room.image_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number_of_places, room_classes, image_name);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number_of_places=" + number_of_places +
                ", price=" + price +
                ", room_classes=" + room_classes +
                ", room_status=" + room_status +
                ", description='" + description + '\'' +
                ", image_name='" + image_name + '\'' +
                '}';
    }
}
