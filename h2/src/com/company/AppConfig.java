package com.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan("com.company")
public class AppConfig {
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "password";

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        return emf.createEntityManager();
    }

    @Bean
    public Connection connection() {
        try {
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Bean
    public CoursesDAO coursesJPADAO() {
        return new CoursesJPADAOImpl();
    }

    @Bean
    public CoursesDAO coursesJDBCDAO() {
        return new CoursesJDBCDAOImpl();
    }

    @Bean
    public NotificationService notificationService() {
        return new NotificationService();
    }
}
