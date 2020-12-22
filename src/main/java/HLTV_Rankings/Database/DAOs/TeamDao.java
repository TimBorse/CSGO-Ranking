package HLTV_Rankings.Database.DAOs;

import HLTV_Rankings.Database.DAOs.Interfaces.ITeamDao;
import HLTV_Rankings.Database.Entities.Team;

import javax.persistence.Query;

public class TeamDao extends JpaDao<Team, Integer> implements ITeamDao {

    @Override
    public Team findByName(String name) {
        Query query = entityManager.createQuery("select t from Team t where t.name = :name");
        query.setParameter("name", name);
        try{
            return (Team) query.getResultList().get(0);
        }catch (Exception e){
            return null;
        }
    }
}
