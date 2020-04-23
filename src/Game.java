import java.util.ArrayList;

public class Game implements Observer {
    private Championship championship;
    private ArrayList<Match> currentMatches;
    private MatchesGenerator matchesGenerator;
    private int playedMatches;
    private Time time;
    private int money;

    public Game() {
        time = Time.createTimer(this);
        chooseChampionship(1);
        championship.setRanking();
        time.start();
    }

    public void chooseChampionship(int c) {
        ChampionshipBuilder championshipBuilder;
        switch (c) {
            default:
                championshipBuilder = null;
            case 1:
                championshipBuilder = new SerieABuilder();
        }
        if (championshipBuilder != null) {
            championshipBuilder.setName();
            championshipBuilder.loadTeams();
        } else {
            System.out.println("Il numero inserito non corrisponde a nessun campionato");
        }
        championship = championshipBuilder.getChampionship();
        matchesGenerator = new MatchesGenerator(championship);
    }

    @Override
    public void update() {
        for (Match m : currentMatches) {
            m.simulateMatch();
            m.printMatch();
        }
        playedMatches++;
        championship.setRanking();

        if (playedMatches == championship.getTeams().size() - 1) {
            System.out.println("---------FINE CAMPIONATO----------");
            System.out.println("Classifica finale:");
            championship.setRanking();
            time.stop();
        }else
            newMatches();
    }

    public void newMatches() {
        currentMatches = matchesGenerator.generateMatches();
        matchesGenerator.printMatches();
    }
}