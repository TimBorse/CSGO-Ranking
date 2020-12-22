package HLTV_Rankings;

import HLTV_Rankings.UIController.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import HLTV_Rankings.Database.DatabaseManager;
import HLTV_Rankings.Database.Entities.Player;
import HLTV_Rankings.Database.Entities.Team;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    public static List<Team> teams;
    public static List<Player> players;
    FXMLLoader loader;


    @Override
    public void start(Stage primaryStage) throws Exception{
        MainController mainController = new MainController(primaryStage);
        mainController.goToSelectUser();
    }


    public static void main(String[] args) throws IOException {
        DatabaseManager.createEM();
        HLTVCrawler crawler = new HLTVCrawler();
        //crawler.retrieveTop30Teams("https://www.hltv.org/ranking/teams/");
        teams = DatabaseManager.getAllTeams();
        players = DatabaseManager.getAllPlayers();
        launch(args);
        //ToDo: Settings (Teams neu laden, Ranking URL, Import/Export User)
    }
}
