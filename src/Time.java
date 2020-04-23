import java.util.Timer;
import java.util.TimerTask;

public class Time extends Subject {
    private static Time time;
    private static int matchTime;
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
        resetTimer();
    }

    private int setTime() {
        if(matchTime == 1) {
            _notify();
            matchTime = 5;
        }
        return --matchTime;
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

    public void resetTimer(){
        matchTime = 5;
    }

    public void stop() {
        timer.cancel();
    }
}
