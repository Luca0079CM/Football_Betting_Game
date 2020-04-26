import java.util.Timer;
import java.util.TimerTask;

class Time extends Subject {
    private static Time time;
    private static int matchTime;
    private static Timer timer;
    private static int delay;
    private static int period;

    static Time createTimer(Game game){
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

    private void setTime() {
        _notify();
        matchTime--;
    }

    void start(){
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                setTime();
            }
        };
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }

    void resetTimer(){
        matchTime = 41;
    }

    void stop() {
        timer.cancel();
    }

    int getMatchTime(){
        return matchTime;
    }
}
