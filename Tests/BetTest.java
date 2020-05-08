import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetTest {
    private Bet testBet;

    @BeforeEach
    void setTestBet(){
        testBet = new Bet(80, 40);
    }

    @Test
    void testGetQuotes() {
        assertTrue(testBet.getQuotes()[0] > 1.6 && testBet.getQuotes()[0] < 1.7);
        assertTrue( testBet.getQuotes()[1] > 6.4 && testBet.getQuotes()[1] < 6.5 );
        assertTrue(testBet.getQuotes()[2] > 7.5 && testBet.getQuotes()[2] < 7.6);
    }
}