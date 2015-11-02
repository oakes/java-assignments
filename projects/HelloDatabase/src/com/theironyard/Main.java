package com.theironyard;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS players (name VARCHAR, health DOUBLE, score INT, is_alive BOOLEAN)");
        stmt.execute("INSERT INTO players VALUES ('Alice', 100, 10, true)");
        stmt.execute("INSERT INTO players VALUES ('Bob', 95, 10, true)");
        stmt.execute("UPDATE players SET health = 50 WHERE name = 'Alice'");
        stmt.execute("DELETE FROM players WHERE name ='Bob'");

        // BAD:
        //String input = "', 0, 0, true); DROP TABLE players; --";
        //stmt.execute(String.format("INSERT INTO players VALUES ('%s', 100, 10, true)", input));
        // GOOD:
        String input = "Charlie";
        PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO players VALUES (?, 100, 10, true)");
        stmt2.setString(1, input);
        stmt2.execute();

        ResultSet results = stmt.executeQuery("SELECT * FROM players");
        while (results.next()) {
            String name = results.getString("name");
            double health = results.getDouble("health");
            int score = results.getInt("score");
            boolean isAlive = results.getBoolean("is_alive");
            System.out.println(String.format("%s %s %s %s", name, health, score, isAlive));
        }

        stmt.execute("DROP TABLE players");

        conn.close();
    }
}
