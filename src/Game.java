public class Game implements Observer{
    @Override
    public void update(boolean endGame, boolean endMatches) {

    }

    public void play(){
        Director d = new Director();
        Championship championship = d.chooseChampionship(1);
        championship.setRanking();
        MatchesGenerator matchesGenerator = new MatchesGenerator(championship);
        matchesGenerator.generateMatches();
        matchesGenerator.printMatches();
    }
}
