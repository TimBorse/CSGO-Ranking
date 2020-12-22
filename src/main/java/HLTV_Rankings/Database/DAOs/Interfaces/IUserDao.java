package HLTV_Rankings.Database.DAOs.Interfaces;

import HLTV_Rankings.Database.Entities.User;

public interface IUserDao {

    public User findByName(String name);

}
