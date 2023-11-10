package sms;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainMenu {
    @FXML
    private void switchAdmin() throws IOException {
        App.setRoot("adminLogin");
    }
}
