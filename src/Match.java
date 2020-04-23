public class Match {
    private int code;
    private Team home;
    private int resultHome;
    private Team away;
    private int resultAway;
    private boolean simulated;
    private Bet bet;

    public Match(int code, Team home, Team away){
        this.code = code;
        this.home = home;
        this.away = away;
        simulated = false;
        bet = new Bet(home.getStrength(), away.getStrength());
    }

    public void simulateMatch(){
        simulated = true;
        for(int i=0;i<10;i++){
            if(home.getStrength()>=Math.random()*300)
                resultHome++;
            if(away.getStrength()>=Math.random()*300)
                resultAway++;
        }
        if(resultHome>resultAway)
            home.addPoints(3);
        else if(resultHome<resultAway)
            away.addPoints(3);
        else {
            home.addPoints(1);
            away.addPoints(1);
        }
    }

    public void printMatch(){
        if(simulated)
            System.out.println("Codice "+" "+code+" "+home.getName()+" "+away.getName()+" "+resultHome+"-"+resultAway);
        else
            System.out.println("Codice"+" "+code+" "+home.getName()+" "+away.getName());
    }
}
