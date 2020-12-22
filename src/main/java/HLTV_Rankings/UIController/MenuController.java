package HLTV_Rankings.UIController;

import HLTV_Rankings.Database.Entities.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    MainController mainController;
    User user;

    @FXML
    Button rankingsButton;
    @FXML
    Button versusButton;
    @FXML
    Button settingsButton;
    @FXML
    Label helloLabel;

    public MenuController(MainController mainController, User user){
        this.mainController = mainController;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helloLabel.setText("Hallo "+user.getName()+"!");
        rankingsButton.setOnAction((event -> {
            mainController.goToRanking(user);
        }));
        versusButton.setOnAction((event -> {
            mainController.goToVersus(user);
        }));
    }
}
