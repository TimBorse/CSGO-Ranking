package HLTV_Rankings.Database.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPlayer> userPlayers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
