package design;

import java.util.LinkedList;
import java.util.List;

public class ConnectionPool {
    private final List<Connection> availableConnections = new LinkedList<>();
    private final List<Connection> usedConnections = new LinkedList<>();

    private final String URL;
    private final String USERID;
    private final String PASSWORD;

    /**
     * Initialize all 5 Connections and put them in the Pool
     **/
    public ConnectionPool(String Url, String UserId, String password) {
        this.URL = Url;
        this.USERID = UserId;
        this.PASSWORD = password;

        int MAX_CONNECTIONS = 5;
        for (int count = 0; count < MAX_CONNECTIONS; count++) {
            availableConnections.add(this.createConnection());
        }
    }

    /**
     * Private function,
     * used by the Pool to create new connection internally
     **/

    private Connection createConnection() {
        return new Connection(this.URL, this.USERID, this.PASSWORD);
    }

    /**
     * Public function, used by us to get connection from Pool
     **/
    public Connection getConnection() {
        if (availableConnections.size() == 0) {
            System.out.println("All connections are Used !!");
            return null;
        } else {
            Connection con = availableConnections.remove(availableConnections.size() - 1);
            usedConnections.add(con);
            return con;
        }
    }

    /**
     * Public function, to return connection back to the Pool
     **/
    public boolean releaseConnection(Connection con) {
        if (null != con) {
            usedConnections.remove(con);
            availableConnections.add(con);
            return true;
        }
        return false;
    }

    /**
     * Utility function to check the number of Available Connections
     **/
    public int getFreeConnectionCount() {
        return availableConnections.size();
    }

    public class Connection {
        public Connection(String URL, String USERID, String PASSWORD) {
            System.out.println("new Connection: " + URL);
        }
    }
}
