package ua.nure.lesik.SummaryTask4.entity;

import java.util.Objects;

/**
 * The class describes entity invoice.
 *
 * @author Ruslan Lesik
 */
public class Invoice {

    private int id;
    private int days_count;
    private int reserve_id;
    private int user_id;
    private double reckoning;
    private boolean active;

    public int getDays_count() {
        return days_count;
    }

    public void setDays_count(int days_count) {
        this.days_count = days_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReserve_id() {
        return reserve_id;
    }

    public void setReserve_id(int reserve_id) {
        this.reserve_id = reserve_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getReckoning() {
        return reckoning;
    }

    public void setReckoning(double reckoning) {
        this.reckoning = reckoning;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return days_count == invoice.days_count &&
                reserve_id == invoice.reserve_id &&
                user_id == invoice.user_id &&
                Double.compare(invoice.reckoning, reckoning) == 0 &&
                active == invoice.active;
    }

    @Override
    public int hashCode() {

        return Objects.hash(days_count, reserve_id, user_id, reckoning, active);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", days_count=" + days_count +
                ", reserve_id=" + reserve_id +
                ", user_id=" + user_id +
                ", reckoning=" + reckoning +
                ", active=" + active +
                '}';
    }
}
