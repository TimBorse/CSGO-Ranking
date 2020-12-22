package HLTV_Rankings.Database.DAOs;

import HLTV_Rankings.Database.DAOs.Interfaces.IUserPlayerDao;
import HLTV_Rankings.Database.Entities.User;
import HLTV_Rankings.Database.Entities.UserPlayer;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UserPlayerDao extends JpaDao<UserPlayer, Integer> implements IUserPlayerDao {
    @Override
    public UserPlayer findUserPlayer(User user, int ranking) {
        Query query = entityManager.createQuery("select up from UserPlayer up where up.user = :user AND up.ranking = :ranking");
        query.setParameter("user", user);
        query.setParameter("ranking", ranking);
        try{
            return (UserPlayer) query.getResultList().get(0);
        }catch (Exception e){
            return null;
        }
    }

    public void updateUserPlayer(UserPlayer userPlayer){
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(userPlayer);
        tx.commit();
    }
}
