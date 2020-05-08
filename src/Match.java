import java.text.DecimalFormat;

class Match {
    static final int homeBonus = 10;
    private int code;
    private Team home;
    private int resultHome;
    private Team away;
    private int resultAway;
    private boolean simulated;
    private Bet bet;

    Match(int code, Team home, Team away){
        this.code = code;
        this.home = home;
        this.away = away;
        simulated = false;
        bet = new Bet(home.getStrength(), away.getStrength());
    }

    String simulateMatch(){
        simulated = true;
        for(int i=0;i<10;i++){
            if(home.getStrength() + homeBonus >=Math.random()*300)
                resultHome++;
            if(away.getStrength() - homeBonus >=Math.random()*300)
                resultAway++;
        }
        if(resultHome>resultAway) {
            home.addPoints(3);
            return "1";
        }
        else if(resultHome<resultAway) {
            away.addPoints(3);
            return "2";
        }
        else {
            home.addPoints(1);
            away.addPoints(1);
            return "X";
        }
    }

    void printMatch(){
        DecimalFormat df = new DecimalFormat("0.00");
        if(simulated)
            System.out.println("\nCodice "+" "+code+" "+home.getName()+" - "+away.getName()+" "+resultHome+"-"+resultAway);
        else {
            System.out.println("\nCodice" + " " + code + " " + home.getName() + " - " + away.getName());
            double[] tmp = bet.getQuotes();
            System.out.println("Quote: 1-"+df.format(tmp[0])+"  X-"+df.format(tmp[1])+"  2-"+df.format(tmp[2]));
        }
    }

    int getCode(){
        return code;
    }

    Bet getBet(){
        return bet;
    }
}
