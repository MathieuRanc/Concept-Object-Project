module com.example.conceptobjectproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.conceptobjectproject to javafx.fxml;
    exports com.example.conceptobjectproject;
    exports Enums;
    opens Enums to javafx.fxml;
}