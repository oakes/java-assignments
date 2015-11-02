import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zach on 10/12/15.
 */
public class ToDo {
    static void printTodos(ArrayList<ToDoItem> todos) {
        for (ToDoItem todo : todos) {
            String checkBox = "[ ]";
            if (todo.isDone) {
                checkBox = "[x]";
            }
            String line = String.format("%d. %s %s", todo.id, checkBox, todo.text);
            System.out.println(line);
        }
    }

    static void insertTodo(Connection conn, String text) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO todos VALUES (NULL, ?, false)");
        stmt.setString(1, text);
        stmt.execute();
    }

    static ArrayList<ToDoItem> selectTodos(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM todos");
        ArrayList<ToDoItem> todos = new ArrayList();
        while (results.next()) {
            int id = results.getInt("id");
            String text = results.getString("text");
            boolean isDone = results.getBoolean("is_done");
            ToDoItem item = new ToDoItem(id, text, isDone);
            todos.add(item);
        }
        return todos;
    }

    static void toggleTodo(Connection conn, int selectNum) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE todos SET is_done = NOT is_done WHERE id = ?");
        stmt.setInt(1, selectNum);
        stmt.execute();
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS todos (id IDENTITY, text VARCHAR, is_done BOOLEAN)");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            ArrayList<ToDoItem> todos = selectTodos(conn);
            printTodos(todos);

            System.out.println("Options:");
            System.out.println("[1] Create todo");
            System.out.println("[2] Mark todo as done or not done");

            String option = scanner.nextLine();
            int optionNum = Integer.valueOf(option);

            if (optionNum == 1) {
                System.out.println("Type a todo and hit enter");
                String todo = scanner.nextLine();
                insertTodo(conn, todo);
            }
            else if (optionNum == 2) {
                System.out.println("Type the number of the todo you want to toggle");
                String select = scanner.nextLine();
                try {
                    int selectNum = Integer.valueOf(select);
                    toggleTodo(conn, selectNum);
                } catch (Exception e) {
                    System.out.println("An error occurred.");
                }
            }
            else {
                System.out.println("Invalid number.");
            }
        }
    }
}
