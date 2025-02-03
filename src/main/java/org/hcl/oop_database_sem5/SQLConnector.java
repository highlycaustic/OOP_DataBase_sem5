package org.hcl.oop_database_sem5;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class SQLConnector {
    Connection db_conn;
    String conn_str = "jdbc:sqlite:sqlite_database.db";

    public SQLConnector() {
        try {
            db_conn = DriverManager.getConnection(conn_str);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createDB() {
        try {
            Statement create = db_conn.createStatement();
            create.execute("CREATE TABLE IF NOT EXISTS fines (" +
                    "id INTEGER CONSTRAINT fines_pk PRIMARY KEY AUTOINCREMENT" +
                    ",date         TEXT" +
                    ",name         TEXT" +
                    ",violation    TEXT" +
                    ",passport     TEXT" +
                    ",fine         REAL" +
                    ",isDeleted    INTEGER DEFAULT 0);");
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<FineItem> loadDB() {
        ArrayList<FineItem> dbList = new ArrayList<>();
        try {
            Statement loadSt = db_conn.createStatement();
            ResultSet resultSet = loadSt.executeQuery("SELECT * FROM fines " +
                                                           "WHERE isDeleted = 0;");

            System.out.println(resultSet);
            while (resultSet.next()) {
                // получение данных из полей
                System.out.print(resultSet.getInt(1) + "; ");     // нумерация полей начинается с единицы
                System.out.print(resultSet.getString(2) + "; ");
                System.out.print(resultSet.getString(3) + "; ");
                System.out.print(resultSet.getString(4) + "; ");
                System.out.print(resultSet.getString(5) + "; ");
                System.out.println(resultSet.getDouble(6));
                FineItem result = new FineItem(resultSet.getString(2),
                                               resultSet.getString(3),
                                               resultSet.getString(4),
                                               resultSet.getString(5),
                                               resultSet.getDouble(6));
                result.setId(resultSet.getInt(1));
                dbList.add(result);
            }
            return dbList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDB(ArrayList<FineItem> dbList) {
        try {
            Statement saveSt = db_conn.createStatement();
            saveSt.execute("DELETE FROM fines;");
// TODO: сделать умное обновление полей при сохранении
        for (FineItem item : dbList) {
//                saveSt.executeQuery("IF EXISTS (SELECT * FROM fines WHERE id= \"" + item.getId() + "\" " +
//                                        "THEN UPDATE fines SET date= \"" + item.getDate() + "\", name= \"" + item.getName() +
//                                        "\", violation= \"" + item.getViolation() + "\", passport= \"" + item.getPassport() +
//                                        "\", fine= \"" + item.getFine() + "\"");
//                saveSt.execute("REPLACE INTO fines(id, date, name, violation, passport, fine) " +
//                                        "VALUES(" + item.getId() + ", \"" + item.getDate() + "\", \"" + item.getName() +
//                                        "\", \"" + item.getViolation() + "\", \"" + item.getPassport() + "\", " + item.getFine() + ");");
                saveSt.execute("INSERT INTO fines(id, date, name, violation, passport, fine) " +
                        "VALUES(" + item.getId() + ", \"" + item.getDate() + "\", \"" + item.getName() +
                        "\", \"" + item.getViolation() + "\", \"" + item.getPassport() + "\", " + item.getFine() + ");");
            }

        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
