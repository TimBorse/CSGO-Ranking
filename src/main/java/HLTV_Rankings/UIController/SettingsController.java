package HLTV_Rankings.UIController;

import HLTV_Rankings.Database.DAOs.PlayerDao;
import HLTV_Rankings.Database.DAOs.UserDao;
import HLTV_Rankings.Database.DAOs.UserPlayerDao;
import HLTV_Rankings.Database.DatabaseManager;
import HLTV_Rankings.Database.Entities.Player;
import HLTV_Rankings.Database.Entities.User;
import HLTV_Rankings.Database.Entities.UserPlayer;
import HLTV_Rankings.FileWalker;
import HLTV_Rankings.HLTVCrawler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javax.persistence.Query;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    MainController mainController;
    User user;

    @FXML
    Button backButton;
    @FXML
    Button setUrlButton;
    @FXML
    TextField urlText;
    @FXML
    Button refreshTeamsButton;
    @FXML
    Button exportButton;
    @FXML
    Button importButton;
    @FXML
    Label confirmLabel;

    public SettingsController(MainController mainController, User user){
        this.mainController = mainController;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTeamsButton.setOnAction((event -> {
            try {
                HLTVCrawler.retrieveTop30Teams("https://www.hltv.org/ranking/teams/");
                confirmLabel.setTextFill(Color.web("GREEN"));
                confirmLabel.setText("Teams und Spieler wurden aktualisiert!");
            } catch (IOException e) {
                confirmLabel.setTextFill(Color.web("RED"));
                confirmLabel.setText("Aktualisierung fehlgeschlagen!");
                e.printStackTrace();
            }
        }));
        backButton.setOnAction((event -> {
            mainController.goToMenu(user);
        }));
        setUrlButton.setOnAction((event -> {
            DatabaseManager.setRankingUrl(urlText.getText());
            confirmLabel.setTextFill(Color.web("GREEN"));
            confirmLabel.setText("Neue URL wurde gesetzt!");
        }));
        exportButton.setOnAction((event -> {
            List<UserPlayer> userPlayerList = user.getUserPlayers();
            try {
                FileWriter fileWriter = new FileWriter(new File(System.getProperty("user.dir"))+"\\exports\\"+user.getName()+".txt");
                fileWriter.write("");
                fileWriter.append("HLTV-Ranking von: "+user.getName()+"\n");
                for(UserPlayer userPlayer : userPlayerList){
                    if(userPlayer != null && userPlayer.getPlayer() != null)
                        fileWriter.append(userPlayer.getRanking() +". "+userPlayer.getPlayer().getName()+"\n");
                }
                fileWriter.close();
                confirmLabel.setTextFill(Color.web("GREEN"));
                confirmLabel.setText("Export erfolgreich!");
            } catch (IOException e) {
                e.printStackTrace();
                confirmLabel.setTextFill(Color.web("RED"));
                confirmLabel.setText("Export fehlgeschlagen!");
            }

        }));
        importButton.setOnAction((event -> {
            importUser();
        }));



    }

    private void importUser(){
        UserDao userDao = new UserDao();
        PlayerDao playerDao = new PlayerDao();
        UserPlayerDao userPlayerDao = new UserPlayerDao();
        JFileChooser chooser = new JFileChooser();
        int returnValue = chooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            String username = "";
            File file = chooser.getSelectedFile();
            try {
                User user;
                List<UserPlayer> userPlayerList;
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                if(line.startsWith("HLTV-Ranking from:")){
                    username = line.split(": ")[1];
                    User foundUser = userDao.findByName(username);
                    if(foundUser == null){
                        user = new User();
                        user.setName(username);
                        userPlayerList = new ArrayList<>();
                    }else{
                        user = foundUser;
                        userPlayerList = user.getUserPlayers();
                        userPlayerList.clear();
                    }
                    line = reader.readLine();
                    for(int i = 1; i<= 20; i++){
                        int rank = i;
                        String playerName;
                        if(line == null)
                            playerName = "";
                        else if(rank == Integer.parseInt(line.split(". ")[0])){
                            playerName = line.split(" ")[1];
                            line = reader.readLine();
                        }else{
                            playerName = "";
                        }
                        UserPlayer up;
                        UserPlayer foundUp = userPlayerDao.findUserPlayer(user, i);
                        Player player = playerDao.findByName(playerName);
                        if(foundUp == null){
                            up = new UserPlayer();
                            up.setUser(user);
                            up.setPlayer(player);
                            up.setRanking(rank);
                        }else{
                            up = foundUp;
                            up.setPlayer(player);
                        }
                        userPlayerList.add(up);
                        DatabaseManager.createUserPlayer(up);
                    }
                    user.setUserPlayers(userPlayerList);
                }else{
                    reader.close();
                }
                confirmLabel.setTextFill(Color.web("GREEN"));
                confirmLabel.setText("Import erfolgreich!");

            }catch (Exception e){
                confirmLabel.setTextFill(Color.web("RED"));
                confirmLabel.setText("Import fehlgeschlagen!");
                e.printStackTrace();
            }


        }
    }


}
