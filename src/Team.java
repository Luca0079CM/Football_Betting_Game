public class Team implements Comparable <Team> {
    private String name;
    private int code;
    private int championshipCode;
    private int strength;
    private Integer points;

    public Team(String name, int code, int championshipCode, int strength){
        this.name = name;
        this.code = code;
        this.championshipCode = championshipCode;
        this.strength = strength;
        this.points = 0;
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

    public int getChampionshipCode(){
        return championshipCode;
    }
}
