module lab6.compulsory {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens lab6.compulsory to javafx.fxml;
    exports lab6.compulsory;
}