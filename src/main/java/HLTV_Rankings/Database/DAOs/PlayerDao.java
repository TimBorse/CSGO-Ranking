package HLTV_Rankings.Database.DAOs;

import HLTV_Rankings.Database.DAOs.Interfaces.IPlayerDao;
import HLTV_Rankings.Database.Entities.Player;

import javax.persistence.Query;

public class PlayerDao extends JpaDao<Player, Integer> implements IPlayerDao {
    @Override
    public Player findByName(String name) {
        Query query = entityManager.createQuery("select p from Player p where p.name = :name");
        query.setParameter("name", name);
        try{
            return (Player) query.getResultList().get(0);
        }catch (Exception e){
            return null;
        }

    }
}
