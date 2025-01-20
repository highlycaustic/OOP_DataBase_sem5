package org.hcl.oop_database_sem5;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private ObservableList<FineItem> finesList = FXCollections.observableArrayList();   // Список с элементами таблицы
    private SimpleStringProperty status = new SimpleStringProperty();         // Статус
    private SQLConnector currDBConn;

    public DataBase() {
        currDBConn = new SQLConnector();
    }

    public void createDB() {
        currDBConn.createDB();
    }

    public void loadDB() {
        finesList.clear();
        finesList.addAll(currDBConn.loadDB());
    }

    public void saveDB() {
        ArrayList<FineItem> toSaveList = new ArrayList<FineItem>(finesList);
        currDBConn.saveDB(toSaveList);
    }

    public ObservableList<FineItem> getFinesList() {
        return this.finesList;
    }

    public SimpleStringProperty getStatus() {
        return this.status;
    }

    public void appendFine() {
        FineItem f = new FineItem("01.01.2001", "Иванов Иван Иванович", "Скорость", "0000 000000", 10.0);
        if (finesList.isEmpty()) {
            f.setId(1);
        }
        else {
            f.setId(finesList.getLast().getId() + 1);
        }
        finesList.add(f);
        status.set("Запись добавлена");
    }

    public void deleteFine(int i) {
//        finesList.remove(item.getId() - 1);
        finesList.remove(i);
    }
}
