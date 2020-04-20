import java.util.Timer;
import java.util.TimerTask;

public class Time extends Subject {
    private static Time time;
    private static int matchTime;
    private static int totalTime;
    private static Timer timer;
    private static int delay;
    private static int period;

    public static Time createTimer(){
        if(timer==null)
            time = new Time();
        return time;
    }

    private Time(){
        delay = 1000;
        period = 1000;
        matchTime = 10;
        totalTime = 30;
        run();
    }

    private int setInterval() {
        if (totalTime == 1) {
            timer.cancel();
            _notify(true, true);
        }
        if(matchTime == 1)
            _notify(true, false);
        --totalTime;
        return --matchTime;
    }

    private void run(){
        timer = new Timer();
        System.out.println(matchTime);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(setInterval());
            }
        };
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }
}
