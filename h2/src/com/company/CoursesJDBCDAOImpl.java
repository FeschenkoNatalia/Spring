package com.company;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursesJDBCDAOImpl implements CoursesDAO {

    @Autowired
    private Connection connection;

    public void setConnection(Connection conn) {
        connection = conn;
    }

    @Override
    public void addClient(Client c) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Clients (phone) VALUES(?)");
            try {
                ps.setString(1, c.getPhone());
                ps.executeUpdate();
            } finally {
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addGroup(Group g) {
        // ...
    }

    @Override
    public List<Client> getClients() {
        List<Client> list = new ArrayList<Client>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id, name, surname, email, phone FROM Clients");
            try {
                ResultSet rs = ps.executeQuery();
                Client c = new Client();

                while (rs.next()) {
                    c.setId(rs.getLong(1));
                    c.setName(rs.getString(2));
                    c.setSurname(rs.getString(3));
                    c.setEmail(rs.getString(4));
                    c.setPhone(rs.getString(5));

                    list.add(c);
                }
            } finally {
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
