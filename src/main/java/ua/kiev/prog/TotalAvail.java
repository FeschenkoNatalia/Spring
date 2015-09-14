package ua.kiev.prog;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TotalAvail")
public class TotalAvail {
    @Id
    @GeneratedValue
    private long id;

    private Date avail_date;

    private double avail_sum;

    public TotalAvail() {}

    public TotalAvail (Date avail_date, double avail_sum) {
        this.avail_date = avail_date;
        this.avail_sum = avail_sum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getAvail_date() {
        return avail_date;
    }

    public void setAvail_date(Date avail_date) {
        this.avail_date = avail_date;
    }

    public double getAvail_sum() {
        return avail_sum;
    }

    public void setAvail_sum(double avail_sum) {
        this.avail_sum = avail_sum;
    }
}

