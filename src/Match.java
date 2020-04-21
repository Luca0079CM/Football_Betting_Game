public class Match {
    private int code;
    private Team home;
    private int resultHome;
    private Team away;
    private int resultAway;
    private boolean simulated;

    public Match(int code, Team home, Team away){
        this.code = code;
        this.home = home;
        this.away = away;
        simulated = false;
    }

    public void simulateMatch(){
        simulated = true;
        for(int i=0;i<10;i++){
            if(home.getStrength()>=Math.random()*300)
                resultHome++;
            if(away.getStrength()>=Math.random()*300)
                resultAway++;
        }
    }

    public void printMatch(){
        if(simulated)
            System.out.println("Codice "+" "+code+" "+home.getName()+" "+away.getName()+" "+resultHome+"-"+resultAway);
        else
            System.out.println("Codice"+" "+code+" "+home.getName()+" "+away.getName());
    }
}
