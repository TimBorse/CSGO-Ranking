package HLTV_Rankings.Database.DAOs;

import HLTV_Rankings.Database.DAOs.Interfaces.ITeamDao;
import HLTV_Rankings.Database.DAOs.Interfaces.IUserDao;
import HLTV_Rankings.Database.Entities.Team;
import HLTV_Rankings.Database.Entities.User;

import javax.persistence.Query;

public class UserDao extends JpaDao<User, Integer> implements IUserDao {

    @Override
    public User findByName(String name) {
        Query query = entityManager.createQuery("select u from User u where u.name = :name");
        query.setParameter("name", name);
        try{
            return (User) query.getResultList().get(0);
        }catch (Exception e){
            return null;
        }
    }
}
