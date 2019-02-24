package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String URL
            = "jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=UTF-8";

    private Connection connection;

    public Database() {
        // zaladuj klase drivera do bazy do pamieci JVM
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Brak drivera");
            System.exit(-1);
        }
    }

    public Connection getConnection() throws SQLException {

        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }

        return connection;
    }
}
