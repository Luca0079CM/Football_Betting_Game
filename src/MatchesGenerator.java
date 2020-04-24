import java.util.ArrayList;
import java.util.Collections;

public class MatchesGenerator {
    private ArrayList<Match> matches;
    private int nTeams;
    private ArrayList<Team> casa;
    private ArrayList<Team> trasferta;
    private static int alternator = 0;
    private static int code = 0;

    public MatchesGenerator(Championship championship){
        ArrayList<Team> teams = championship.getTeams();
        nTeams = teams.size();
        Collections.shuffle(teams);
        casa = new ArrayList<>();
        trasferta = new ArrayList<>();
        for (int i = 0; i < nTeams /2; i++) {
            casa.add(teams.get(i));
            trasferta.add(teams.get(teams.size()-1-i));
        }
    }

    //Algoritmo di Berger
    public ArrayList<Match> generateMatches(){
        matches = new ArrayList<>();
        if (alternator % 2 == 1) {
            for (int j = 0; j < nTeams /2 ; j++)
                matches.add(new Match(++code, trasferta.get(j), casa.get(j)));
        }else {
            for (int j = 0; j < nTeams /2 ; j++)
                matches.add(new Match(++code, casa.get(j), trasferta.get(j)));

        }
        trasferta.add(0, casa.get(1));
        Team riporto = trasferta.remove(trasferta.size()-1);
        casa.remove(1);
        casa.add(riporto);
        alternator++;
        return matches;
    }

    public void printMatches(){
        System.out.println("\nMatches:");
        for(Match m : matches)
            m.printMatch();
        System.out.println("\n");
    }
}

