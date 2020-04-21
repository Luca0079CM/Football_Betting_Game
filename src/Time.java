import java.util.Timer;
import java.util.TimerTask;

public class Time extends Subject {
    private static Time time;
    private static int matchTime;
    private static int totalTime;
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
        if (totalTime == 1) {
            timer.cancel();
            _notify(totalTime);
        }
        if(matchTime == 1) {
            _notify(totalTime);
            matchTime = 10;
        }
        --totalTime;
        return --matchTime;
    }

    public void start(){
        timer = new Timer();
        System.out.println(matchTime);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                setTime();
            }
        };
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }

    public void resetTimer(){
        totalTime = 30;
        matchTime = 10;
    }
}
