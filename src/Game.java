import java.util.ArrayList;

public class Game implements Observer{
    private Championship championship;
    private MatchesGenerator matchesGenerator;
    private ArrayList<Match> currentMatches;

    public Game(){
        Time time = Time.createTimer(this);
        chooseChampionship(1);
        championship.setRanking();
        matchesGenerator = new MatchesGenerator(championship);
        time.start();
    }

    @Override
    public void update(int totalTime) {
        if (totalTime == 1) {
            System.out.println("Fine");
        }
        for(Match m : currentMatches){
            m.simulateMatch();
            m.printMatch();
        }
        newMatches();
    }

    public void newMatches(){
        currentMatches = matchesGenerator.generateMatches();
        matchesGenerator.printMatches();
    }

    public void chooseChampionship(int c){
        ChampionshipBuilder championshipBuilder;
        switch (c){
            default:
                championshipBuilder = null;
            case 1:
                championshipBuilder = new SerieABuilder();
        }
        if(championshipBuilder != null) {
            championshipBuilder.setName();
            championshipBuilder.loadTeams();
        }
        else {
            System.out.println("Il numero inserito non corrisponde a nessun campionato");
        }
        championship = championshipBuilder.getChampionship();
    }
}
