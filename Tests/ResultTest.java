import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {
    private Result testResult;

    @BeforeEach
    void setTestResult(){
        testResult = new Result(1, "X");
    }

    @Test
    void getMatchCode() {
        assertEquals(1, testResult.getMatchCode());
    }

    @Test
    void getResult() {
        assertEquals("X", testResult.getResult());
    }
}