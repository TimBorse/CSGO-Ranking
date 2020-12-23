package HLTV_Rankings;

import HLTV_Rankings.Database.DAOs.TeamDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import HLTV_Rankings.Database.DAOs.PlayerDao;
import HLTV_Rankings.Database.DatabaseManager;
import HLTV_Rankings.Database.Entities.Player;
import HLTV_Rankings.Database.Entities.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HLTVCrawler {


    public static void retrieveTop30Teams(String url) throws IOException {
        TeamDao teamDao = new TeamDao();
        PlayerDao playerDao = new PlayerDao();
        ArrayList<Team> teamsList = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements teams = doc.select("div[class=ranked-team standard-box]");
        for(Element teamElement : teams){
            Team team;
            try{
                String teamName = teamElement.select("div[class=teamLine sectionTeamPlayers ]").select("span[class=name]").get(0).wholeText();
                team = createTeamWithPlayers(teamName, teamElement, teamDao, playerDao);
                //DatabaseManager.createTeam(team);
            }catch (Exception e){
                String teamName = teamElement.select("div[class=teamLine sectionTeamPlayers teamLineExpanded]").select("span[class=name]").get(0).wholeText();
                team = createTeamWithPlayers(teamName, teamElement, teamDao, playerDao);
            }
            //DatabaseManager.createTeam(team);
            teamsList.add(team);
        }
    }

    private static Team createTeamWithPlayers(String teamName, Element teamElement, TeamDao teamDao, PlayerDao playerDao){
        Team team;
        Team foundTeam = teamDao.findByName(teamName);
        if(foundTeam == null){
            team = new Team();
            team.setName(teamName);
        }else{
            team = foundTeam;
        }

        Elements playersElement = teamElement.select("div[class=rankingNicknames]").select("span").select("span");
        for(Element playerElement : playersElement){
            Player player;
            Player foundPlayer = playerDao.findByName(playerElement.wholeText());
            if(foundPlayer == null){
                player = new Player();
                player.setName(playerElement.wholeText());
                player.setTeam(team);
            }else{
                player = foundPlayer;
                player.setTeam(team);
            }
            DatabaseManager.createPlayer(player);
        }
        return team;
    }

    public static List<Player> getTop20Old(String url) throws IOException {
        List<Player> rankings = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        String test = doc.select("blockquote").get(1).select("p[class=news-block]").outerHtml();
        test = test.replace("<p class=\"news-block\">", "");
        String[] testArr = test.split("<br>");
        for(String ranking : testArr){
            String[] formattedRanking = ranking.replaceAll("(<[a-z, =, \", A-Z, \\d, \\/, ., :, -. _]+>)", "").split("\\s+");
            if(formattedRanking.length >= 3){
                PlayerDao playerDao = new PlayerDao();
                Player player = playerDao.findByName((formattedRanking[2].replace("\"", "")));
                int rank = Integer.parseInt(formattedRanking[0].replace(".", ""));
                rankings.add(player);
            }
        }
        return rankings;
    }

    public static List<Player> getTop20(String url) throws IOException{
        PlayerDao playerDao = new PlayerDao();
        List<Player> rankings = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        String text = doc.text();
        text = text.substring(text.indexOf("1. "));
        String[] ranks = text.split("([\\d]+[.])");
        for (int i = 1; i <= 20; i++) {
            String rank = ranks[i];
            rank = rank.substring(rank.indexOf("\""));
            rank = rank.split("\"")[1];
            Player player = playerDao.findByName(rank);
            rankings.add(player);
        }
        return rankings;
    }

}
