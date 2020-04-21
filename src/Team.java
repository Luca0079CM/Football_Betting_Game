import java.util.ArrayList;

public class Team implements Comparable <Team> {
    private String name;
    private int code;
    private int strength;
    private Integer points;
    private ArrayList<Integer> teamsFaced = new ArrayList<>();
    private boolean matched;

    public Team(String name, int code, int strength){
        this.name = name;
        this.code = code;
        this.strength = strength;
        this.points = 0;
        this.matched = false;
    }

    @Override
    public int compareTo(Team t) {
        return this.getPoints().compareTo(t.getPoints());
    }

    public Integer getPoints(){
        return points;
    }

    public void setPoints(int p){
        points = p;
    }

    public String getName(){
        return name;
    }

    public int getStrength (){
        return strength;
    }

    public int getCode(){
        return code;
    }

    public boolean getMatched(){
        return matched;
    }

    public void setMatched(boolean b){
        matched = b;
    }

    public ArrayList<Integer> getTeamsFaced(){
        return teamsFaced;
    }

    public void addTeamsFaced(int codeTeam){
        teamsFaced.add(codeTeam);
    }
}
