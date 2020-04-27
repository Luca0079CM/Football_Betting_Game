import java.util.ArrayList;
import java.util.Comparator;

class Championship {
    private String name;
    private ArrayList<Team> teams;

    Championship(String name){
        this.name = name;
    }

    String getName(){
        return name;
    }

    void setTeams(ArrayList<Team> teams){
        this.teams = teams;
    }

    void setRanking(){
        teams.sort(Comparator.reverseOrder());
        System.out.println("\nSquadre del campionato:");
        for (Team t : teams)
            System.out.println(t.getName()+"-forza:"+t.getStrength()+"-punti:"+t.getPoints());
    }

    ArrayList<Team> getTeams(){
        return teams;
    }
}