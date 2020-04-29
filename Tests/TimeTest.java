import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    class TestObserver implements Observer{
        boolean called  = false;
        @Override
        public void update() {
            called = true;
        }
    }

    private TestObserver testObserver = new TestObserver();
    private Time testTime;

    @BeforeEach
    void createTimer() {
        testTime = Time.createTime(testObserver);
        testTime.start();
    }

    @Test
    void testSingleton(){
        Time testTime2 = Time.createTime(testObserver);
        assertEquals(testTime, testTime2);
    }

    @Test
    void resetTimer() {
        testTime.resetTimer();
        assertEquals(91, testTime.getMatchTime());
    }

    @Test
    void testCallUpdate(){
        testTime.observer = testObserver;
        testTime._notify();
        assertTrue(testObserver.called);
    }
}