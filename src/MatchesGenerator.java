import java.util.ArrayList;

public class MatchesGenerator {
    private Championship championship;
    private String[][] matches;

    public MatchesGenerator(Championship championship){
        this.championship = championship;
    }

    public void generateMatches(){
        ArrayList<Team> teams = championship.getTeams();
        for(Team t : teams){
            t.setMatched(false);
        }
        matches = new String[teams.size()][2];
        int i = 0;
        while(i < teams.size()/2) {
            int challenger = (int)(Math.random()*(teams.size()));
            if(!teams.get(challenger).getMatched()) {
                int opponent;
                do {
                    opponent = (int) (Math.random() * (teams.size()));
                } while (challenger == opponent ||
                        teams.get(challenger).getTeamsFaced().contains(opponent)||
                        teams.get(opponent).getMatched());
                teams.get(challenger).setMatched(true);
                teams.get(opponent).setMatched(true);
                matches[i][0] = teams.get(challenger).getName();
                matches[i][1] = teams.get(opponent).getName();
                i ++;
            }
        }
    }

    public void printMatches(){
        System.out.println("\nMatches:");
        for(int i = 0; i < championship.getTeams().size()/2; i++){
            System.out.println(matches[i][0]+"-"+matches[i][1]);
        }
        System.out.println("\n");
    }

 }

