package ua.kiev.prog;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Sourcetypes")
public class Sourcetypes {
    @Id
    @GeneratedValue
    private long id;

    private String type;

    @Column(name = "source_sum")
    private double source_sum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;

    public Sourcetypes() {
    }

    public Sourcetypes(String type, double source_sum, Users users) {
        this.type = type;
        this.source_sum = source_sum;
        this.users = users;
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

    public double getSource_sum() {
        return source_sum;
    }

    public void setSource_sum(double source_sum) {
        this.source_sum = source_sum;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}


