public class Team implements Comparable <Team> {
    private String name;
    private int strength;
    private Integer points;


    Team(String name, int code, int strength){
        this.name = name;
        this.strength = strength;
        this.points = 0;
    }

    @Override
    public int compareTo(Team t) {
        return this.getPoints().compareTo(t.getPoints());
    }

    Integer getPoints(){
        return points;
    }

    void addPoints(int p){
        points += p;
    }

    String getName(){
        return name;
    }

    int getStrength (){
        return strength;
    }
}