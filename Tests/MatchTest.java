import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    private Match testMatch;

    @BeforeEach
    void setTestMatch(){
        Team testTeam1 = new Team("Test Team 1", 100);
        Team testTeam2 = new Team("Test Team 2", 50);
        testMatch = new Match(1, testTeam1, testTeam2);
    }

    @Test
    void simulateMatch() {
        assertTrue(testMatch.simulateMatch().equals("1") ||
                testMatch.simulateMatch().equals("2")||
                testMatch.simulateMatch().equals("X"));
    }

    @Test
    void getCode() {
        assertEquals(1, testMatch.getCode());
    }

    @Test
    void getBet() {
        Bet testBet = new Bet(100 ,50);
        assertEquals(testBet.getQuotes()[0], testMatch.getBet().getQuotes()[0]);
        assertEquals(testBet.getQuotes()[1], testMatch.getBet().getQuotes()[1]);
        assertEquals(testBet.getQuotes()[2], testMatch.getBet().getQuotes()[2]);
    }
}