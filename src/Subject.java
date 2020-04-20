public abstract class Subject {
    protected Observer observer;
    protected void _notify(boolean endMatches, boolean endGame){
        observer.update(endMatches, endGame);
    }
}
