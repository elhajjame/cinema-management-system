
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        final String DB_URL = "jdbc:mysql://localhost/cinema";
        final String USER = "root";
        final String PASS = "0676890880";
        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
