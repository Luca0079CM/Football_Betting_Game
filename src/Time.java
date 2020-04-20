import java.util.Timer;
import java.util.TimerTask;

public class Time {
    private static Time time;
    private static int interval;
    private static Timer timer;

    public static Time createTimer(){
        if(timer==null)
            time = new Time();
        return time;
    }

    private Time(){
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = 10;
        System.out.println(interval);
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println(setInterval());
            }
        }, delay, period);
    }

    private static int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
}
