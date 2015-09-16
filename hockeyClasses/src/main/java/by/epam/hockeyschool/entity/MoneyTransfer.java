package by.epam.hockeyschool.entity;

import java.util.Date;

/**
 * Created by demonwtf on 16.08.2015.
 */
public class MoneyTransfer {

    private Date date;
    private int sum;
    private String operation;

    public MoneyTransfer() {
    }

    public MoneyTransfer(Date date, int sum, String operation) {
        this.date = date;
        this.sum = sum;
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyTransfer)) return false;

        MoneyTransfer that = (MoneyTransfer) o;

        if (sum != that.sum) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (operation != null ? !operation.equals(that.operation) : that.operation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + sum;
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        return result;
    }
}
