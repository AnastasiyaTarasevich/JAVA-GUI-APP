module com.example.kursach_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.kursach_3 to javafx.fxml;
    exports com.example.kursach_3;
    exports com.example.kursach_3.Tables;
    opens com.example.kursach_3.Tables to javafx.fxml;
}