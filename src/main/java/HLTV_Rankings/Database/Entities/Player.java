package HLTV_Rankings.Database.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player implements Comparable<Player>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @ManyToOne(cascade = CascadeType.ALL)
    Team team;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
    List<UserPlayer> userPlayers;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UserPlayer> getUserPlayers() {
        return userPlayers;
    }

    public void setUserPlayers(List<UserPlayer> userPlayers) {
        this.userPlayers = userPlayers;
    }

    public void addUserPlayer(UserPlayer userPlayer){
        if(userPlayers == null)
            userPlayers = new ArrayList<>();
        userPlayers.add(userPlayer);
    }


    public String toString(){
        return this.name;
    }

    @Override
    public int compareTo(Player player) {
        return this.name.toLowerCase().compareTo(player.getName().toLowerCase());
    }
}
