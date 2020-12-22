package HLTV_Rankings;

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


    public void retrieveTop30Teams(String url) throws IOException {
        ArrayList<Team> teamsList = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements teams = doc.select("div[class=ranked-team standard-box]");
        for(Element teamElement : teams){
            Team team;
            try{
                team = new Team();
                team.setName(teamElement.select("div[class=teamLine sectionTeamPlayers ]").select("span[class=name]").get(0).wholeText());
                Elements playersElements = teamElement.select("div[class=rankingNicknames]").select("span");
                for(Element playerElement : playersElements){
                    Player player = new Player();
                    player.setName(playerElement.wholeText());
                    player.setTeam(team);
                    DatabaseManager.createPlayer(player);
                    team.addPlayer(player);
                }
                DatabaseManager.createTeam(team);
            }catch (Exception e){
                team = new Team();
                team.setName(teamElement.select("div[class=teamLine sectionTeamPlayers teamLineExpanded]").select("span[class=name]").get(0).wholeText());
                Elements playersElement = teamElement.select("div[class=rankingNicknames]").select("span").select("span");
                for(Element playerElement : playersElement){
                    Player player = new Player();
                    player.setName(playerElement.wholeText());
                    player.setTeam(team);
                    DatabaseManager.createPlayer(player);
                    team.addPlayer(player);
                }
            }
            DatabaseManager.createTeam(team);
            teamsList.add(team);
        }
    }

    public static List<Player> getTop20(String url) throws IOException {
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

}
