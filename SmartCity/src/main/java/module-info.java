module org.example.smartcity {
    requires javafx.controls;
    requires javafx.fxml;
    requires c3p0;
    requires java.sql;
    requires java.desktop;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.naming;
    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.db;
    opens org.example.db to javafx.fxml;
}