module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    
    opens com.app.entity to javafx.base;

    opens com.app to javafx.fxml;
    exports com.app;
}
