package connection;

import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private static String USER_NAME = "root";
    private static String USER_PASSWORD = "straightedge";
    private static String URL = "jdbc:mysql://localhost:3306/magazine_shop?serverTimezone=UTC";

    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        DOMConfigurator.configure("C:/Users/Otstoy/IdeaProjects/Magazine_shop/LoggerConfig.xml");
        Class.forName ("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection (URL, USER_NAME, USER_PASSWORD);
    }
}
