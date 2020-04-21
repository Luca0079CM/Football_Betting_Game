public abstract class Subject {
    protected Observer observer;
    protected void _notify(int totalTime){
        observer.update(totalTime);
    }
}
