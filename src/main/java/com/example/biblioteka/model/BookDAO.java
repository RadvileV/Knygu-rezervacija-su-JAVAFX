package com.example.biblioteka.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {


    public static List<Book> searchByName(String name) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "";
        if (name.isEmpty()) {
            querry = "SELECT * FROM `books`";
        } else {
            querry = "SELECT * FROM `books` WHERE `name` LIKE '%" + name + "%'";
        }
        ArrayList<Book> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("summary"),
                        resultSet.getString("ISBN"),
                        resultSet.getInt("bookPages"),
                        resultSet.getString("category"),
                        resultSet.getBoolean("inUse")
                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    public static void update(Book book) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "UPDATE `books` SET `name`= ?,`summary`= ?,`ISBN`= ?,`bookPages`= ?,`category`= ? WHERE `id`= ?";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getSummary());
            preparedStatement.setString(3, book.getISBN());
            preparedStatement.setInt(4, book.getBookPages());
            preparedStatement.setString(5, book.getCategory());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atnaujinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atnaujinti įrašą");
        }
    }

    public static boolean bookIsReserved(int id) {
            boolean status = false;
            try {
                String jdbcUrl = "jdbc:mysql://localhost:3306/db";
                String query = "SELECT * FROM `books` WHERE `id`=? AND `inUse` = 1";
                Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                status = resultSet.next();
                preparedStatement.close();
                connection.close();
                System.out.println("Knyga jau rezervuota");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Nepavyko rezervuoti knygos");
            }
            return status;
        }

    public static void reserveBook(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "UPDATE `books` SET `inUse`= 1 WHERE `id`=?";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko rezervuoti knygą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko rezervuoti knygos");
        }
    }

    public static String searchByReservation(boolean status) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "";
        querry = "SELECT * FROM `books` WHERE `inUse` = ?";

        String status2 = "";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setBoolean(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                status2 = resultSet.getBoolean("inUse") ? "Rezervuota" : "Laisva";
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status2;
    }



    public static void create(Book book) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "INSERT INTO `books`(`name`, `summary`, `ISBN`, `bookPages`, `category`) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getSummary());
            preparedStatement.setString(3, book.getISBN());
            preparedStatement.setInt(4, book.getBookPages());
            preparedStatement.setString(5, book.getCategory());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteById(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "DELETE FROM `books` WHERE `id` = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko ištrinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Įrašo ištrinti nepavyko");
        }
    }
}

