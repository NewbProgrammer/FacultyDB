package root.tools;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alex on 17.08.14.
 *
 */
public class ConnectionManager implements Closeable{
    private Connection connection = null;
    private String url;
    private String userName;
    private String password;


    public ConnectionManager() {
        this.url = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql347243";
        this.userName = "sql347243";
        this.password = "aB3*nG5*";
    }

    public Connection getConnection() throws SQLException {
        if (connection==null) {
                connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection = null;
        }

    }
}
