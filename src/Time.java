import java.util.Timer;
import java.util.TimerTask;

class Time extends Subject {
    private static Time time;
    private static int matchTime;
    private static Timer timer;
    private static int delay;
    private static int period;

    static Time createTime(Observer observer){
        if(time==null)
            time = new Time(observer);
        return time;
    }

    private Time(Observer observer){
        this.observer = observer;
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
        matchTime = 91;
    }

    void stop() {
        timer.cancel();
    }

    int getMatchTime(){
        return matchTime;
    }
}
