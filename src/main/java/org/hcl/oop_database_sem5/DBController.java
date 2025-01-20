package org.hcl.oop_database_sem5;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class DBController implements Initializable {

    DataBase db = new DataBase();

    @FXML
    private Label statusText;

    @FXML
    private TextField searchField;


    @FXML
    private TableView<FineItem> table;

    private TableColumn<FineItem, String> col_date = new TableColumn<>("Дата");
    private TableColumn<FineItem, String> col_name = new TableColumn<>("ФИО");
    private TableColumn<FineItem, String> col_violation = new TableColumn<>("Нарушение");
    private TableColumn<FineItem, String> col_passport = new TableColumn<>("Паспорт №");
    private TableColumn<FineItem, Double> col_fine = new TableColumn<>("Штраф");

    @FXML
    private ChoiceBox searchChoiceBox = new ChoiceBox();
// TODO: сделать выбор файла
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Оборачиваем оригинальный список в фильтруемый список
        FilteredList<FineItem> filteredFinesList = new FilteredList<>(db.getFinesList(), p -> true);

        // Организация поиска по колонкам
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFinesList.setPredicate(fineItem -> {
                String sel = searchChoiceBox.getValue().toString();
                if (searchField.getText().isEmpty()) {return true;}
                if (sel.equals("Дата")) {
                    if (fineItem.getDate().equals(searchField.getText())) {return true;}
                }
                if (sel.equals("ФИО")) {
                    if (fineItem.getName().equals(searchField.getText())) {return true;}
                }
                if (sel.equals("Нарушение")) {
                    if (fineItem.getViolation().equals(searchField.getText())) {return true;}
                }
                if (sel.equals("Паспорт №")) {
                    if (fineItem.getPassport().equals(searchField.getText())) {return true;}
                }
                if (sel.equals("Штраф")) {
                    if (String.valueOf(fineItem.getFine()).equals(searchField.getText())) {return true;}
                }
                return false;
            });
        });

        // Заворачиваем отфильтрованный список в сортируемый, для сортировки
        SortedList<FineItem> sortedFinesList = new SortedList<>(filteredFinesList);
        sortedFinesList.comparatorProperty().bind(table.comparatorProperty()); // бинд компараторов для сброса поиска

        // Инициализация редактируемых ячеек
        col_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        col_date.setCellFactory(
                TextFieldTableCell.forTableColumn());
        col_date.setOnEditCommit(
                (TableColumn.CellEditEvent<FineItem, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDate(t.getNewValue()) // Здесь прописываем сеттер для поля
        );

        col_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col_name.setCellFactory(
                TextFieldTableCell.forTableColumn());
        col_name.setOnEditCommit(
                (TableColumn.CellEditEvent<FineItem, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setName(t.getNewValue())
        );

        col_violation.setCellValueFactory(new PropertyValueFactory<>("Violation"));
        col_violation.setCellFactory(
                TextFieldTableCell.forTableColumn());
        col_violation.setOnEditCommit(
                (TableColumn.CellEditEvent<FineItem, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setViolation(t.getNewValue())
        );

        col_passport.setCellValueFactory(new PropertyValueFactory<>("Passport"));
        col_passport.setCellFactory(
                TextFieldTableCell.forTableColumn());
        col_passport.setOnEditCommit(
                (TableColumn.CellEditEvent<FineItem, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPassport(t.getNewValue())
        );

        col_fine.setCellValueFactory(new PropertyValueFactory<>("Fine"));
        col_fine.setCellFactory(
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        col_fine.setOnEditCommit(
                (TableColumn.CellEditEvent<FineItem, Double> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setFine(t.getNewValue())
        );

        // добавляем колонки в таблицу
        table.getColumns().add(col_date);
        table.getColumns().add(col_name);
        table.getColumns().add(col_violation);
        table.getColumns().add(col_passport);
        table.getColumns().add(col_fine);

        table.setItems(sortedFinesList);

        // Добавляем пункты в список поиска по колонкам
        searchChoiceBox.getItems().add("Дата");
        searchChoiceBox.getItems().add("ФИО");
        searchChoiceBox.getItems().add("Нарушение");
        searchChoiceBox.getItems().add("Паспорт №");
        searchChoiceBox.getItems().add("Штраф");
        // Дефолтное значение
        searchChoiceBox.setValue("ФИО");

        // бинд статусбара к полю статуса в DataBase
        statusText.textProperty().bind(db.getStatus());

    }

    @FXML
    protected void onAppendButtonClick() {
        db.appendFine();
    }

    @FXML
    protected void onDeleteButtonClick() {
        db.deleteFine(table.getSelectionModel().getSelectedIndex());
    }

    @FXML
    protected void onCreateDBButtonClick(){
        db.createDB();
    }

    @FXML
    protected void onLoadDBButtonClick(){
        db.loadDB();
    }

    @FXML
    protected void onSaveDBButtonClick() {
        db.saveDB();
    }

}