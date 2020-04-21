import java.util.ArrayList;
import java.util.Collections;

public class Championship {
    private String name;
    private ArrayList<Team> teams;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setTeams(ArrayList teams){
        this.teams = teams;
    }

    public void setRanking(){
        Collections.sort(teams);
        System.out.println("Squadre del campionato:");
        for (Team t : teams)
            System.out.println(t.getName()+" forza:"+t.getStrength()+" punti:"+t.getPoints());
    }

    public ArrayList<Team> getTeams(){
        return teams;
    }
}