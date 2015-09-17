package ua.kiev.prog;

import javax.persistence.*;

@Entity
@Table(name = "Transactionstypes")
public class Transactionstypes {
    @Id
    @GeneratedValue
    private long id;

    private String type;

    public Transactionstypes() {}

    public Transactionstypes(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

