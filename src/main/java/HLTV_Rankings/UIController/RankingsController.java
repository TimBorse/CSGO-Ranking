package HLTV_Rankings.UIController;

import HLTV_Rankings.Database.DAOs.UserPlayerDao;
import HLTV_Rankings.Database.DatabaseManager;
import HLTV_Rankings.Database.Entities.Player;
import HLTV_Rankings.Database.Entities.User;
import HLTV_Rankings.Database.Entities.UserPlayer;
import HLTV_Rankings.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javax.persistence.Query;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class RankingsController implements Initializable {

    @FXML
    ChoiceBox<String> box1;

    @FXML
    ChoiceBox<String> box2;

    @FXML
    ChoiceBox<String> box3;

    @FXML
    ChoiceBox<String> box4;

    @FXML
    ChoiceBox<String> box5;

    @FXML
    ChoiceBox<String> box6;

    @FXML
    ChoiceBox<String> box7;

    @FXML
    ChoiceBox<String> box8;

    @FXML
    ChoiceBox<String> box9;

    @FXML
    ChoiceBox<String> box10;

    @FXML
    ChoiceBox<String> box11;

    @FXML
    ChoiceBox<String> box12;

    @FXML
    ChoiceBox<String> box13;

    @FXML
    ChoiceBox<String> box14;

    @FXML
    ChoiceBox<String> box15;

    @FXML
    ChoiceBox<String> box16;

    @FXML
    ChoiceBox<String> box17;

    @FXML
    ChoiceBox<String> box18;

    @FXML
    ChoiceBox<String> box19;

    @FXML
    ChoiceBox<String> box20;

    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    Label label4;
    @FXML
    Label label5;
    @FXML
    Label label6;
    @FXML
    Label label7;
    @FXML
    Label label8;
    @FXML
    Label label9;
    @FXML
    Label label10;
    @FXML
    Label label11;
    @FXML
    Label label12;
    @FXML
    Label label13;
    @FXML
    Label label14;
    @FXML
    Label label15;
    @FXML
    Label label16;
    @FXML
    Label label17;
    @FXML
    Label label18;
    @FXML
    Label label19;
    @FXML
    Label label20;
    @FXML
    Button save;

    ArrayList<ChoiceBox> choiceBoxes;
    ArrayList<Label> labels;

    MainController mainController;
    User user;

    public RankingsController(MainController mainController, User user){
        this.mainController = mainController;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxes = new ArrayList<>();
        labels = new ArrayList<>();
        choiceBoxes.add(box1);
        choiceBoxes.add(box2);
        choiceBoxes.add(box3);
        choiceBoxes.add(box4);
        choiceBoxes.add(box5);
        choiceBoxes.add(box6);
        choiceBoxes.add(box7);
        choiceBoxes.add(box8);
        choiceBoxes.add(box9);
        choiceBoxes.add(box10);
        choiceBoxes.add(box11);
        choiceBoxes.add(box12);
        choiceBoxes.add(box13);
        choiceBoxes.add(box14);
        choiceBoxes.add(box15);
        choiceBoxes.add(box16);
        choiceBoxes.add(box17);
        choiceBoxes.add(box18);
        choiceBoxes.add(box19);
        choiceBoxes.add(box20);
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);
        labels.add(label5);
        labels.add(label6);
        labels.add(label7);
        labels.add(label8);
        labels.add(label9);
        labels.add(label10);
        labels.add(label11);
        labels.add(label12);
        labels.add(label13);
        labels.add(label14);
        labels.add(label15);
        labels.add(label16);
        labels.add(label17);
        labels.add(label18);
        labels.add(label19);
        labels.add(label20);
        fillChoiceBoxes();
        readStoredData();
        setOnChangeListeners();
        save.setOnAction((event -> {
            if(!hasDuplicates()){
                System.out.println("KEINE DUPLIKATE");
                for(int i=0; i<choiceBoxes.size(); i++){
                    UserPlayer userPlayer = new UserPlayer();
                    userPlayer.setUser(user);
                    Player curPlayer = (Player)choiceBoxes.get(i).getValue();
                    userPlayer.setPlayer(curPlayer);
                    userPlayer.setRanking(i+1);
                    if(DatabaseManager.getAllUserPlayers(user).size() < 20){
                        DatabaseManager.createUserPlayer(userPlayer);
                        user.addUserPlayer(userPlayer);
                        if(curPlayer != null)
                            curPlayer.addUserPlayer(userPlayer);
                    }else{
                        UserPlayerDao upDao = new UserPlayerDao();
                        UserPlayer storedUP = upDao.findUserPlayer(user, i+1);
                        storedUP.setPlayer(curPlayer);
                        upDao.updateUserPlayer(storedUP);
                    }

                }
                mainController.goToMenu(user);

            }else{
                System.out.println("DUPLIKATE!");
            }
        }));
    }

    public void fillChoiceBoxes() {
        List<Player> players = Main.players;
        Collections.sort(players);
        for(ChoiceBox choiceBox : choiceBoxes) {
            ObservableList<Player> playerObservableList = FXCollections.observableArrayList(players);
            choiceBox.setItems(playerObservableList);
        }
    }

    public void setOnChangeListeners(){
        for(ChoiceBox choiceBox : choiceBoxes){
            choiceBox.setOnAction((event) -> {
                updateTeams();
            });
        }
    }

    public void readStoredData(){
        List<UserPlayer> userPlayers = user.getUserPlayers();
        if(userPlayers!=null){
            for(int i=0; i<choiceBoxes.size(); i++){
                choiceBoxes.get(i).setValue(userPlayers.get(i).getPlayer());
            }
            updateTeams();
        }

    }

    public void updateTeams(){
        for(int i=0;i<choiceBoxes.size();i++) {
            if(choiceBoxes.get(i).getValue()!=null) {
                Player player = (Player) choiceBoxes.get(i).getValue();
                labels.get(i).setText(player.getTeam().getName());

            }
        }
    }

    public boolean hasDuplicates() {
        for(int i=0;i<choiceBoxes.size();i++) {

            Player player1 = (Player)choiceBoxes.get(i).getValue();

            for(int j=i+1;j<choiceBoxes.size();j++) {
                if(choiceBoxes.get(j).getValue() !=null && choiceBoxes.get(j).getValue() == player1) {
                    return true;
                }
            }
        }
        return false;
    }

}
