package ua.nure.lesik.SummaryTask4.entity;

import java.util.Objects;

/**
 * The class describes entity reserve.
 *
 * @author Ruslan Lesik
 */
public class Reserve {

    private int id;
    private int room_id;
    private int user_id;
    private String date_create;
    private String check_in;
    private String check_out;
    private double price;
    private boolean status_reserve;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus_reserve() {
        return status_reserve;
    }

    public void setStatus_reserve(boolean status_reserve) {
        this.status_reserve = status_reserve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserve reserve = (Reserve) o;
        return room_id == reserve.room_id &&
                user_id == reserve.user_id &&
                Double.compare(reserve.price, price) == 0 &&
                Objects.equals(date_create, reserve.date_create) &&
                Objects.equals(check_in, reserve.check_in) &&
                Objects.equals(check_out, reserve.check_out);
    }

    @Override
    public int hashCode() {

        return Objects.hash(room_id, user_id, date_create, check_in, check_out, price);
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", user_id=" + user_id +
                ", date_create='" + date_create + '\'' +
                ", check_in='" + check_in + '\'' +
                ", check_out='" + check_out + '\'' +
                ", price=" + price +
                ", status_reserve=" + status_reserve +
                '}';
    }
}
