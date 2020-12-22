package HLTV_Rankings.Database.DAOs.Interfaces;

import HLTV_Rankings.Database.Entities.Team;

public interface ITeamDao {

    public Team findByName(String name);
}
