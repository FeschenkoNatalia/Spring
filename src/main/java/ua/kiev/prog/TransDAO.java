package ua.kiev.prog;

import java.util.List;

/**
 * Created by User on 12.09.2015.
 */
public interface TransDAO {
    List<Transactions> list();
    List<Transactions> list(String pattern);
    void add(Transactions trans);
    void delete(long id);
}
