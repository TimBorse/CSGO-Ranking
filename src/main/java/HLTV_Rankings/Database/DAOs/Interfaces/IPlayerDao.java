package HLTV_Rankings.Database.DAOs.Interfaces;

import HLTV_Rankings.Database.Entities.Player;

public interface IPlayerDao {

    public Player findByName(String name);
}
