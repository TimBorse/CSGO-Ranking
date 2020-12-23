package HLTV_Rankings.UIController;

import HLTV_Rankings.Database.DAOs.UserDao;
import HLTV_Rankings.Database.DatabaseManager;
import HLTV_Rankings.Database.Entities.Player;
import HLTV_Rankings.Database.Entities.User;
import HLTV_Rankings.Database.Entities.UserPlayer;
import HLTV_Rankings.HLTVCrawler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VersusController implements Initializable {

    MainController mainController;

    @FXML
    Label user1;
    @FXML
    Label user2;
    @FXML
    Label user3;
    @FXML
    Label user4;
    @FXML
    Label user5;
    @FXML
    Label user6;
    @FXML
    Label user7;
    @FXML
    Label user8;
    @FXML
    Label user9;
    @FXML
    Label user10;
    @FXML
    Label user11;
    @FXML
    Label user12;
    @FXML
    Label user13;
    @FXML
    Label user14;
    @FXML
    Label user15;
    @FXML
    Label user16;
    @FXML
    Label user17;
    @FXML
    Label user18;
    @FXML
    Label user19;
    @FXML
    Label user20;

    @FXML
    Label enemy1;
    @FXML
    Label enemy2;
    @FXML
    Label enemy3;
    @FXML
    Label enemy4;
    @FXML
    Label enemy5;
    @FXML
    Label enemy6;
    @FXML
    Label enemy7;
    @FXML
    Label enemy8;
    @FXML
    Label enemy9;
    @FXML
    Label enemy10;
    @FXML
    Label enemy11;
    @FXML
    Label enemy12;
    @FXML
    Label enemy13;
    @FXML
    Label enemy14;
    @FXML
    Label enemy15;
    @FXML
    Label enemy16;
    @FXML
    Label enemy17;
    @FXML
    Label enemy18;
    @FXML
    Label enemy19;
    @FXML
    Label enemy20;

    @FXML
    Label result1;
    @FXML
    Label result2;
    @FXML
    Label result3;
    @FXML
    Label result4;
    @FXML
    Label result5;
    @FXML
    Label result6;
    @FXML
    Label result7;
    @FXML
    Label result8;
    @FXML
    Label result9;
    @FXML
    Label result10;
    @FXML
    Label result11;
    @FXML
    Label result12;
    @FXML
    Label result13;
    @FXML
    Label result14;
    @FXML
    Label result15;
    @FXML
    Label result16;
    @FXML
    Label result17;
    @FXML
    Label result18;
    @FXML
    Label result19;
    @FXML
    Label result20;
    @FXML
    Label error;

    @FXML
    Button back;

    @FXML
    Label userScore;
    @FXML
    Label enemyScore;
    @FXML
    Label userName;
    @FXML
    Label enemyName;
    @FXML
    ChoiceBox enemyChoice;

    User user;
    User enemy;

    Label[] userList;
    Label[] enemyList;
    Label[] resultList;

    List<Player> results;
    List<Player> userRanking;
    List<Player> enemyRanking;

    public VersusController(MainController mainController, User user) {
        this.mainController = mainController;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int userScore = 0;
        int enemyScore = 0;
        userList = new Label[20];
        enemyList = new Label[20];
        resultList = new Label[20];
        userRanking = new ArrayList<>();
        List<UserPlayer> userRankingTemp = user.getUserPlayers();
        for(UserPlayer up : userRankingTemp){
            userRanking.add(up.getPlayer());
        }
        try {
            results = HLTVCrawler.getTop20(DatabaseManager.getRankingUrl());
        } catch (Exception e) {
            error.setTextFill(Color.web("RED"));
            error.setText("Angegebene URL konnte nicht geladen werden!");
            e.printStackTrace();
        }

        userList[0] = user1;
        userList[1] = user2;
        userList[2] = user3;
        userList[3] = user4;
        userList[4] = user5;
        userList[5] = user6;
        userList[6] = user7;
        userList[7] = user8;
        userList[8] = user9;
        userList[9] = user10;
        userList[10] = user11;
        userList[11] = user12;
        userList[12] = user13;
        userList[13] = user14;
        userList[14] = user15;
        userList[15] = user16;
        userList[16] = user17;
        userList[17] = user18;
        userList[18] = user19;
        userList[19] = user20;

        enemyList[0] = enemy1;
        enemyList[1] = enemy2;
        enemyList[2] = enemy3;
        enemyList[3] = enemy4;
        enemyList[4] = enemy5;
        enemyList[5] = enemy6;
        enemyList[6] = enemy7;
        enemyList[7] = enemy8;
        enemyList[8] = enemy9;
        enemyList[9] = enemy10;
        enemyList[10] = enemy11;
        enemyList[11] = enemy12;
        enemyList[12] = enemy13;
        enemyList[13] = enemy14;
        enemyList[14] = enemy15;
        enemyList[15] = enemy16;
        enemyList[16] = enemy17;
        enemyList[17] = enemy18;
        enemyList[18] = enemy19;
        enemyList[19] = enemy20;

        resultList[0] = result1;
        resultList[1] = result2;
        resultList[2] = result3;
        resultList[3] = result4;
        resultList[4] = result5;
        resultList[5] = result6;
        resultList[6] = result7;
        resultList[7] = result8;
        resultList[8] = result9;
        resultList[9] = result10;
        resultList[10] = result11;
        resultList[11] = result12;
        resultList[12] = result13;
        resultList[13] = result14;
        resultList[14] = result15;
        resultList[15] = result16;
        resultList[16] = result17;
        resultList[17] = result18;
        resultList[18] = result19;
        resultList[19] = result20;

        List<User> users = DatabaseManager.getAllUsers();
        ObservableList<User> obsUsers = FXCollections.observableArrayList(users);
        enemyChoice.setItems(obsUsers);
        enemyChoice.setOnAction((event -> {
            UserDao userDao = new UserDao();
            enemyRanking = new ArrayList<>();
            this.enemy = userDao.findByName(((User) enemyChoice.getValue()).getName());
            List<UserPlayer> enemyRankingTemp = enemy.getUserPlayers();
            for(UserPlayer up : enemyRankingTemp){
                enemyRanking.add(up.getPlayer());
            }
            if (enemyRanking.size() > 0) {
                for (int i = 0; i < enemyList.length; i++) {
                    Player player = enemyRanking.get(i);
                    if (player != null)
                        enemyList[i].setText(player.getName());
                }
            } else {
                for (Label label : enemyList) {
                    label.setText("");
                }
            }
            for(Label label : enemyList){
                label.setTextFill(Color.web("BLACK"));
            }

            for(Label label : userList){
                label.setTextFill(Color.web("BLACK"));
            }

            calculatePoints();
        }));

        for (int i = 0; i < userList.length; i++) {
            Player player = userRanking.get(i);
            if (player != null)
                userList[i].setText(player.getName());
        }

        if(results != null){
            for (int i = 0; i < resultList.length; i++) {
                Player player = results.get(i);
                if (player != null)
                    resultList[i].setText(player.getName());
            }
        }


        this.back.setOnAction((event)-> {
            mainController.goToMenu(user);
        });
    }

    private void calculatePoints() {
        int userScore = 0;
        int enemyScore = 0;
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) != null) {
                int indexPlayer = userRanking.indexOf(results.get(i));
                int indexEnemy = enemyRanking.indexOf(results.get(i));
                int multi = 1;
                boolean userContainsPlayer;
                boolean enemyContainsPlayer;

                if(i == 0)
                    multi = 3;
                else if(i == 1 || i == 2)
                    multi = 2;
                else
                    multi = 1;

                if(indexPlayer == -1)
                    indexPlayer = 100;
                if(indexEnemy == -1)
                    indexEnemy = 100;
                if(indexPlayer == i && indexEnemy != i){
                    userScore += 3*multi;
                    if(indexPlayer<=20)
                        userList[indexPlayer].setTextFill(Color.web("GREEN"));
                }
                else if(indexEnemy == i && indexPlayer != i){
                    enemyScore += 3*multi;
                    if(indexEnemy<=20)
                        enemyList[indexEnemy].setTextFill(Color.web("GREEN"));
                }
                else if(Math.abs(i-indexPlayer) < Math.abs(i-indexEnemy)){
                    userScore += 1*multi;
                    if(indexPlayer<=20)
                        userList[indexPlayer].setTextFill(Color.web("BLUE"));
                }
                else if(Math.abs(i-indexEnemy) < Math.abs(i-indexPlayer)){
                    enemyScore += 1*multi;
                    if(indexEnemy<=20)
                        enemyList[indexEnemy].setTextFill(Color.web("BLUE"));
                }
            }
        }
        this.userScore.setText(Integer.toString(userScore));
        this.enemyScore.setText(Integer.toString(enemyScore));
        System.out.println();
    }
}
