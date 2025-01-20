module org.hcl.oop_database_sem5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.hcl.oop_database_sem5 to javafx.fxml;
    exports org.hcl.oop_database_sem5;
}