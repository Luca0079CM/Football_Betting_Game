abstract class Subject {
    Observer observer;
    void _notify(){
        observer.update();
    }
}
