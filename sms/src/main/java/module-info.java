module sms {
    requires javafx.controls;
    requires javafx.fxml;

    opens sms to javafx.fxml;
    exports sms;
}
