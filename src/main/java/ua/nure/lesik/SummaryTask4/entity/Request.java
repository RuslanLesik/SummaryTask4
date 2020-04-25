package ua.nure.lesik.SummaryTask4.entity;

import ua.nure.lesik.SummaryTask4.entity.enums.RoomClasses;

import java.util.Objects;


/**
 * The class describes entity request.
 *
 * @author Ruslan Lesik
 */
public class Request {

    private int id;
    private String user_login;
    private int numbers_of_places;
    private RoomClasses room_classes;
    private int number_of_days;
    private boolean status_request;


    public boolean isStatus_request() {
        return status_request;
    }

    public void setStatus_request(boolean status_request) {
        this.status_request = status_request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public int getNumbers_of_places() {
        return numbers_of_places;
    }

    public void setNumbers_of_places(int numbers_of_places) {
        this.numbers_of_places = numbers_of_places;
    }

    public RoomClasses getRoom_classes() {
        return room_classes;
    }

    public void setRoom_classes(RoomClasses room_classes) {
        this.room_classes = room_classes;
    }

    public int getNumber_of_days() {
        return number_of_days;
    }

    public void setNumber_of_days(int number_of_days) {
        this.number_of_days = number_of_days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return numbers_of_places == request.numbers_of_places &&
                number_of_days == request.number_of_days &&
                status_request == request.status_request &&
                Objects.equals(user_login, request.user_login) &&
                room_classes == request.room_classes;
    }

    @Override
    public int hashCode() {

        return Objects.hash(user_login, numbers_of_places, room_classes, number_of_days, status_request);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", user_login='" + user_login + '\'' +
                ", numbers_of_places=" + numbers_of_places +
                ", room_classes=" + room_classes +
                ", number_of_days=" + number_of_days +
                ", status_request=" + status_request +
                '}';
    }
}
