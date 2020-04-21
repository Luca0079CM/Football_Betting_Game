import java.util.ArrayList;

public class MatchesGenerator {
    private ArrayList<Match> matches;
    private ArrayList<Team> teams;

    public MatchesGenerator(Championship championship){
        teams = championship.getTeams();
    }

    public ArrayList<Match> generateMatches(){
        for(Team t : teams){
            t.setMatched(false);
        }
        matches = new ArrayList<>();
        int i = 0;
        while(i < teams.size()/2) {
            int challenger = (int)(Math.random()*(teams.size()));
            if(!teams.get(challenger).getMatched()) {
                Team cg = teams.get(challenger);
                int opponent;
                do {
                    opponent = (int) (Math.random() * (teams.size()));
                } while (challenger == opponent ||
                        cg.getTeamsFaced().contains(opponent)||
                        teams.get(opponent).getMatched());
                Team op = teams.get(opponent);
                cg.setMatched(true);
                op.setMatched(true);
                cg.addTeamsFaced(opponent);
                op.addTeamsFaced(challenger);
                matches.add(new Match(i, cg, op));
                i ++;
            }
        }
        return matches;
    }

    public void printMatches(){
        System.out.println("\nMatches:");
        for(Match m : matches)
            m.printMatch();
        System.out.println("\n");
    }
 }

