package ua.kiev.prog;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue
    private int id;

    private String transaction_date;

    private double transaction_sum;

    private String transaction_desc;

    /*@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "sourcetypes_id")
    private Sourcetypes sourcetypes;*/

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "transactionstypes_id")
    private Transactionstypes transactionstypes;

    public Transactions() {}

    public Transactions(String transaction_date, double transaction_sum, String transaction_desc, Transactionstypes transactionstypes) {
        this.transaction_date = transaction_date;
        this.transaction_sum = transaction_sum;
        this.transaction_desc = transaction_desc;
       // this.sourcetypes = sourcetypes;
        this.transactionstypes = transactionstypes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public double getTransaction_sum() {
        return transaction_sum;
    }

    public void setTransaction_sum(double transaction_sum) {
        this.transaction_sum = transaction_sum;
    }

    public String getTransaction_desc() {
        return transaction_desc;
    }

    public void setTransaction_desc(String transaction_desc) {
        this.transaction_desc = transaction_desc;
    }

    /*public Sourcetypes getSourcetypes() {
    return sourcetypes;
    }

    public void setSourcetypes(Sourcetypes sourcetypes) {
      this.sourcetypes = sourcetypes;
    }*/

    public Transactionstypes getTransactionstypes() {
        return transactionstypes;
    }

    public void setTransactionstypes(Transactionstypes transactionstypes) {
        this.transactionstypes = transactionstypes;
    }
}

