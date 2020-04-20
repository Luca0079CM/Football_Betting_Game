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
        for (Team t : teams){
            System.out.println(t.getName()+" "+t.getPoints());
        }
    }

    public ArrayList<Team> getTeams(){
        return teams;
    }
}