module org.hcl.oop_database_sem5 {
    requires javafx.fxml;
    requires java.sql;
    requires com.pixelduke.fxthemes;

    opens org.hcl.oop_database_sem5 to javafx.fxml;
    exports org.hcl.oop_database_sem5;
}