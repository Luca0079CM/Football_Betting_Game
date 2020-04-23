public abstract class Subject {
    protected Observer observer;
    protected void _notify(){
        observer.update();
    }
}
