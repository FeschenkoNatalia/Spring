package com.company;

import java.util.List;

public interface CoursesDAO {
    void addClient(Client c);
    void addGroup(Group g);
    List<Client> getClients();
    // other methods
}
