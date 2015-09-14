package com.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    enum DatabaseEngine {JPA_Hibernate, JDBC};
    static DatabaseEngine engine = DatabaseEngine.JDBC;  // change this

    public static void main(String[] args) {
        // #1
        System.out.println("Test #1:");

        CoursesDAO dao = null;

        if (engine == DatabaseEngine.JPA_Hibernate) {
            dao = new CoursesJPADAOImpl();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
            EntityManager em = emf.createEntityManager();

            ((CoursesJPADAOImpl)dao).setEntityManager(em);
        } else if (engine == DatabaseEngine.JDBC) {
            dao = new CoursesJDBCDAOImpl();

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(AppConfig.DB_CONNECTION, AppConfig.DB_USER, AppConfig.DB_PASSWORD);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return;
            }

            ((CoursesJDBCDAOImpl)dao).setConnection(connection);
        } else {
            // ...
        }

        NotificationService ns = new NotificationService();
        ns.setCoursesDAO(dao);
        ns.notifyClientsByEmail("Happy new year!");

        // #2
        System.out.println("Test #2:");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext ();
        ctx.register(AppConfig.class);
        ctx.refresh();

        NotificationService notificationService = (NotificationService)ctx.getBean("notificationService");
        notificationService.notifyClientsByEmail("Happy new year!");

        // #3
        System.out.println("Test #3:");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-config.xml");

        notificationService = (NotificationService)applicationContext.getBean("notificationService");
        notificationService.notifyClientsByEmail("Happy new year!");
    }
}
