package com.theironyard;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by zach on 3/2/16.
 */
public class MainTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./test");
        Main.createTables(conn);
        return conn;
    }

    public void endConnection(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE messages");
        conn.close();
    }

    @Test
    public void testMessages() throws SQLException {
        Connection conn = startConnection();
        Main.insertMessage(conn, "Alice", "Hello world!");
        Main.insertMessage(conn, "Bob", "Hey");
        ArrayList<Message> messages = Main.selectMessages(conn);
        endConnection(conn);
        assertTrue(messages.size() == 2);
    }
}