package seminar12.extra;

import seminar11.MyDBInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {

    private static ConnectionPool instance; // Utilizing Singleton pattern
    private Stack<Connection> pool;
    private int CONNECTION_LIMIT = 10; // TODO

    private ConnectionPool() throws ClassNotFoundException {
        // Driver registration (static blocks)
        Class.forName("com.mysql.jdbc.Driver");

        // Connection pool safestack
        pool = new Stack<>();
    }

    public static ConnectionPool getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnectionFromPool() throws SQLException {
        Connection connection;
        if (pool.size() > 0) {
            connection = pool.pop();
        } else {
            connection = createConnection();
        }

        return connection;
    }

    public void returnConnectionToPool(Connection con) {
        pool.push(con);
    }

    public void close() throws SQLException {
        boolean error = false;
        for (Connection aPool : pool) {
            try {
                aPool.close();
            } catch (SQLException e) {
                e.printStackTrace();
                error = true;
            }
        }

        if (error) {
            throw new SQLException("Some connections could not be closed");
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://"
                + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);

    }

}
