package com.theironyard;

import jodd.json.JsonSerializer;
import spark.Spark;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS countries (id IDENTITY, name VARCHAR, abbrev VARCHAR)");
    }

    public static void insertCountry(Connection conn, String name, String abbrev) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO countries VALUES (NULL, ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, abbrev);
        stmt.execute();
    }

    public static Country selectCountry(Connection conn, int id) throws SQLException {
        Country country = null;
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM countries WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            country = new Country();
            country.id = results.getInt("id");
            country.name = results.getString("name");
            country.abbrev = results.getString("abbrev");
        }
        return country;
    }

    public static ArrayList<Country> selectCountries(Connection conn) throws SQLException {
        ArrayList<Country> countries = new ArrayList();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM countries");
        ResultSet results = stmt.executeQuery();
        while (results.next()) {
            Country country = new Country();
            country.id = results.getInt("id");
            country.name = results.getString("name");
            country.abbrev = results.getString("abbrev");
            countries.add(country);
        }
        return countries;
    }

    public static void main(String[] args) throws SQLException {
        // open database
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        createTables(conn);

        // serve external files
        Spark.externalStaticFileLocation("public");
        Spark.init();

        // insert test data
        if (selectCountries(conn).size() == 0) {
            insertCountry(conn, "United States", "US");
            insertCountry(conn, "Canada", "CA");
            insertCountry(conn, "Mexico", "MX");
        }

        // create routes for AJAX
        Spark.get(
                "/get-countries",
                ((request, response) -> {
                    JsonSerializer serializer = new JsonSerializer();
                    String json = serializer.serialize(selectCountries(conn));
                    return json;
                })
        );
        Spark.get(
                "/get-country",
                ((request, response) -> {
                    String id = request.queryParams("id");
                    try {
                        int idNum = Integer.valueOf(id);
                        JsonSerializer serializer = new JsonSerializer();
                        String json = serializer.serialize(selectCountry(conn, idNum));
                        return json;
                    } catch (Exception e) {

                    }
                    return "";
                })
        );
        Spark.post(
                "/add-country",
                ((request, response) -> {
                    String name = request.queryParams("name");
                    String abbrev = request.queryParams("abbrev");
                    if (name == null || abbrev == null) {
                        Spark.halt(403);
                    }
                    insertCountry(conn, name, abbrev);
                    return "";
                })
        );
    }
}
