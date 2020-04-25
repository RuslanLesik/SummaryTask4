package ua.nure.lesik.SummaryTask4.entity;

import java.util.Objects;

/**
 * The class describes entity comment.
 *
 * @author Ruslan Lesik
 */
public class Comment {

    private int id;
    private int room_id;
    private String user_login;
    private String comment;
    private String date_create;

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

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return room_id == comment1.room_id &&
                Objects.equals(user_login, comment1.user_login) &&
                Objects.equals(comment, comment1.comment) &&
                Objects.equals(date_create, comment1.date_create);
    }

    @Override
    public int hashCode() {

        return Objects.hash(room_id, user_login, comment, date_create);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", user_login='" + user_login + '\'' +
                ", comment='" + comment + '\'' +
                ", date_create='" + date_create + '\'' +
                '}';
    }
}
