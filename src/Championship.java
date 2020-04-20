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
        for (int i = 0; i<teams.size(); i++){
            System.out.println(teams.get(i).getName()+" "+teams.get(i).getPoints());
        }
    }

}