package HLTV_Rankings.Database.DAOs.Interfaces;

import HLTV_Rankings.Database.Entities.User;
import HLTV_Rankings.Database.Entities.UserPlayer;

import java.util.List;

public interface IUserPlayerDao {

    public UserPlayer findUserPlayer(User user, int ranking);

}
