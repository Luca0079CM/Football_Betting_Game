import java.util.Timer;
import java.util.TimerTask;

public class Time {
    static int interval;
    static Timer timer;

    public static void main(String[] args) {
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

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
}
