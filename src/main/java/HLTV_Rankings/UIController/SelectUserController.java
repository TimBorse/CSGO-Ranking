package HLTV_Rankings.UIController;

import HLTV_Rankings.Database.DAOs.UserDao;
import HLTV_Rankings.Database.DatabaseManager;
import HLTV_Rankings.Database.Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectUserController implements Initializable {

    MainController mainController;
    @FXML
    ChoiceBox usersChoiceBox;
    @FXML
    TextField newUserText;
    @FXML
    Button addUserButton;
    @FXML
    Button selectUserButton;

    public SelectUserController(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<User> users = DatabaseManager.getAllUsers();
        ObservableList<User> userObservableList = FXCollections.observableArrayList(users);
        usersChoiceBox.setItems(userObservableList);
        usersChoiceBox.setValue(userObservableList.get(0));
        Image img = new Image("file:"+System.getProperty("user.dir")+"/icons/baseline_check_black_18dp.png");
        ImageView imgView = new ImageView(img);
        selectUserButton.setGraphic(imgView);
        selectUserButton.setOnAction((event -> {
            User user = (User) usersChoiceBox.getValue();
            mainController.goToMenu(user);
        }));

        Image img2 = new Image("file:"+System.getProperty("user.dir")+"/icons/baseline_add_black_18dp.png");
        ImageView imgView2 = new ImageView(img2);
        addUserButton.setGraphic(imgView2);
        addUserButton.setOnAction((event) -> {
            User newUser = new User();
            newUser.setName(newUserText.getText());
            try{
                DatabaseManager.createUser(newUser);
                UserDao userDao = new UserDao();
                mainController.goToMenu(userDao.findByName(newUser.getName()));
            }catch(Exception e){
                System.out.println("Name vergeben");
            }
        });

    }
}
