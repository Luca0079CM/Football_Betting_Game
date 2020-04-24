import java.util.Timer;
import java.util.TimerTask;

public class Time extends Subject {
    private static Time time;
    private int matchTime;
    private static Timer timer;
    private static int delay;
    private static int period;

    public static Time createTimer(Game game){
        if(timer==null)
            time = new Time(game);
        return time;
    }

    private Time(Game game){
        observer = game;
        delay = 1000;
        period = 1000;
        resetTimer(121);
    }

    private void setTime() {
        if(matchTime%30 == 0){
            System.out.println("Rimangono "+matchTime+" secondi all'inizio della prossima giornata!");
        }
        if(matchTime == 20){
            System.out.println("Rimangono solo 20 secondi all'inizio della prossima giornata!\nAffrettati a completare le tue operazioni, ricorda che non verranno conteggiate dopo la fine del tempo");
        }
        if(matchTime == 1) {
            _notify();
        }
        matchTime--;
    }

    public void start(){
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                setTime();
            }
        };
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }

    public void resetTimer(int matchTime){
        this.matchTime = matchTime;
    }

    public void stop() {
        timer.cancel();
    }
}
