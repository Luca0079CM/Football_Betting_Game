public class Game {
    public void play(){
        Director d = new Director();
        Championship serieA = d.chooseChampionship(1);
        serieA.setRanking();
        MatchesGenerator matchesGenerator = new MatchesGenerator(serieA);
        matchesGenerator.generateMatches();
        matchesGenerator.printMatches();
    }
}
