import java.util.ArrayList;
import java.util.Collections;

class MatchesBuilder {
    private int nTeams;
    private ArrayList<Team> home;
    private ArrayList<Team> away;
    private static int alternator = 0;
    private static int code = 0;

    MatchesBuilder(Championship championship){
        ArrayList<Team> teams = championship.getTeams();
        nTeams = teams.size();
        Collections.shuffle(teams);
        home = new ArrayList<>();
        away = new ArrayList<>();
        for (int i = 0; i < nTeams /2; i++) {
            home.add(teams.get(i));
            away.add(teams.get(teams.size()-1-i));
        }
    }

    //Algoritmo di Berger
    ArrayList<Match> generateMatches(){
        ArrayList<Match> matches = new ArrayList<>();
        if (alternator % 2 == 1) {
            for (int j = 0; j < nTeams /2 ; j++)
                matches.add(new Match(++code, away.get(j), home.get(j)));
        }else {
            for (int j = 0; j < nTeams /2 ; j++)
                matches.add(new Match(++code, home.get(j), away.get(j)));

        }
        away.add(0, home.get(1));
        Team riporto = away.remove(away.size()-1);
        home.remove(1);
        home.add(riporto);
        alternator++;
        return matches;
    }
}

