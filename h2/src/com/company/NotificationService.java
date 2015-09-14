package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class NotificationService {

    @Autowired
    @Qualifier("coursesJPADAO") // if two or more similar beans defined
    private CoursesDAO coursesDAO;

    public void setCoursesDAO(CoursesDAO dao) {
        coursesDAO = dao;
    }

    public void notifyClientsByEmail(String text) {
        for (Client c : coursesDAO.getClients())
            sendEmail(c, text);
    }

    public void notifyClientsBySMS(String text) {
        for (Client c : coursesDAO.getClients())
            sendSMS(c, text);
    }

    private void sendEmail(Client c, String text) {
        System.out.println(text + " e-mail sent to " + c.getEmail());
        // a lot of code here
    }

    private void sendSMS(Client c, String text) {
        System.out.println(text + " SMS sent to " + c.getPhone());
        // a lot of code here
    }
}
