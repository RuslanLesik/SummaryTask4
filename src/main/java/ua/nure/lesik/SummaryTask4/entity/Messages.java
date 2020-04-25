package ua.nure.lesik.SummaryTask4.entity;

import java.util.Objects;

/**
 * The class describes entity messages.
 *
 * @author Ruslan Lesik
 */
public class Messages {

    private int id;
    private String user_login;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messages messages = (Messages) o;
        return Objects.equals(user_login, messages.user_login) &&
                Objects.equals(message, messages.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user_login, message);
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", user_login='" + user_login + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
