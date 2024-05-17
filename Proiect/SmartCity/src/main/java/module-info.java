module org.example.smartcity {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example to javafx.fxml;
    exports org.example;
}