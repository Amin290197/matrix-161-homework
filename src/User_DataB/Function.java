package User_DataB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Function {
    static String user;
    static String url;
    static final String password = "";
    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    static {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // work
    public static void insertUser(String name, String surname, int age) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("insert into users (name, surname, age) values (?,?,?)");
        stmt.setString(1, name);
        stmt.setString(2, surname);
        stmt.setInt(3, age);
        closeConnection(conn);
        if (!stmt.execute()) {
            System.out.println("User dded successfully");
        } else {
            System.out.println("Failed to add user");
        }
    }

    // work
    public static User selectUserById(Integer id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(" select * from users where id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getInt("id"), rs.getString("name"),
                    rs.getString("surname"), rs.getInt("age"));
            closeConnection(conn);
            return user;
        }
        return new User(null, null, null, 0);
    }

    // work
    public static List<User> getUsersList() throws SQLException {
        List<User> users = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(" select * from users");
        while (rs.next()) {
            User user = new User(rs.getInt("id"), rs.getString("name"),
                    rs.getString("surname"), rs.getInt("age"));
            users.add(user);
            closeConnection(conn);
        }
        return users;
    }

    // work
    public static void updateUserById(Integer id, String name, String surname, Integer age) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("update users set name = ?, " +
                "surname = ?, age = ? where id = ?");
        stmt.setString(1, name);
        stmt.setString(2, surname);
        stmt.setInt(3, age);
        stmt.setInt(4, id);
        stmt.executeUpdate();
        closeConnection(conn);
    }

    // work
    public static void deleteUserById(Integer id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("delete from users where id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        closeConnection(conn);
    }

    public static void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    public static int getNum(String message) {
        System.out.println(message);
        int result = sc.nextInt();
        return result;
    }

    public static String getStr(String message) {
        System.out.println(message);
        String result = sc.next();
        return result;
    }
}
