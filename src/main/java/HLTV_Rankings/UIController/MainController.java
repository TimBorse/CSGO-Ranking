package HLTV_Rankings.UIController;

import java.net.URL;

import HLTV_Rankings.Database.Entities.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController{

    private Stage stage;

    public MainController() {
        this.stage = null;
    }

    public MainController(Stage stage) {
        this.stage = stage;
    }




    public void goToSelectUser() {
        try {
            replaceSceneContent("selectUser.fxml", new SelectUserController(this));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void goToMenu(User user) {
        try {
            replaceSceneContent("menu.fxml", new MenuController(this, user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToRanking(User user) {
        try {
            replaceSceneContent("rankings.fxml", new RankingsController(this, user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void goToVersus(User user) {
        try {
            replaceSceneContent("versus.fxml", new VersusController(this, user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replaceSceneContent(String path, Initializable controller) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        String property = System.getProperty("user.dir");
        String ressource = "file:"+property+"\\fxml\\"+path;
        URL url = new URL(ressource);
        loader.setLocation(url);
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }



}
