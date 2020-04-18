public class Team implements Comparable{
    private String name;
    private int code;
    private float strength;
    private int position;

    public Team(String name, int code, float strength){
        this.name = name;
        this.code = code;
        this.strength = strength;
        this.position = 0;
    }

    public int getPosition(){
        return position;
    }

    public void setPosition(int p){
        position = p;
    }

    @Override
    public int compareTo(Object o) {
        return getPosition();
    }
}
