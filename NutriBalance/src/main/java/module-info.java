module main.nutribalance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens main.nutribalance to javafx.fxml;
    exports main.nutribalance;
}