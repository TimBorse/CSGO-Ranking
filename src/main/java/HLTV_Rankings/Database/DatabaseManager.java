package HLTV_Rankings.Database;

import HLTV_Rankings.Database.DAOs.PlayerDao;
import HLTV_Rankings.Database.DAOs.TeamDao;
import HLTV_Rankings.Database.Entities.*;

import javax.persistence.*;
import java.util.List;

public class DatabaseManager {

    public static EntityManager entityManager;

    public static void createEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ranking");
        entityManager = emf.createEntityManager();
    }

    public static void createPlayer(Player player) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(player);
        tx.commit();
    }

    private static boolean playerExistsInDB(Player player) {
        PlayerDao playerDao = new PlayerDao();
        Player foundPlayer = playerDao.findByName(player.getName());
        if (foundPlayer != null) {
            return true;
        }
        return false;
    }

    public static void createTeam(Team team) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(team);
        tx.commit();

    }

    private static boolean teamExistsInDB(Team team) {
        TeamDao teamDao = new TeamDao();
        Team foundTeam = teamDao.findByName(team.getName());
        if (foundTeam != null) {
            return true;
        }
        return false;
    }

    public static void createUser(User user) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(user);
        tx.commit();
    }

    public static void createUserPlayer(UserPlayer userPlayer) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(userPlayer);
        tx.commit();
    }

    public static void setRankingUrl(String url){
        RankingURL foundUrl = entityManager.find(RankingURL.class, 1);
        RankingURL rankingUrl;
        if(foundUrl == null){
            rankingUrl = new RankingURL();
            rankingUrl.setId(1);
            rankingUrl.setUrl(url);
        }else{
            rankingUrl = foundUrl;
            rankingUrl.setUrl(url);
        }


        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(rankingUrl);
        tx.commit();
    }

    public static String getRankingUrl(){
        RankingURL url = (RankingURL)entityManager.find(RankingURL.class, 1);
        return url.getUrl();
    }

    public static List<Team> getAllTeams() {
        Query query = entityManager.createQuery("SELECT t FROM Team t");
        return query.getResultList();
    }

    public static List<User> getAllUsers() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    public static List<Player> getAllPlayers() {
        Query query = entityManager.createQuery("SELECT p FROM Player p");
        return query.getResultList();
    }

    public static List<UserPlayer> getAllUserPlayers() {
        Query query = entityManager.createQuery("SELECT up FROM UserPlayer up");
        return query.getResultList();
    }

    public static List<UserPlayer> getAllUserPlayers(User user) {
        Query query = entityManager.createQuery("SELECT up FROM UserPlayer up WHERE up.user = :user");
        query.setParameter("user", user);
        return query.getResultList();
    }

}
