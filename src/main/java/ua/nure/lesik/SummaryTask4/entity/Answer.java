package ua.nure.lesik.SummaryTask4.entity;

import java.util.Objects;

/**
 * The class describes entity answer.
 *
 * @author Ruslan Lesik
 */
public class Answer {

    private int id;
    private String user_login;
    private int room_id;

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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                room_id == answer.room_id &&
                Objects.equals(user_login, answer.user_login);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user_login, room_id);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", user_login='" + user_login + '\'' +
                ", room_id=" + room_id +
                '}';
    }
}
