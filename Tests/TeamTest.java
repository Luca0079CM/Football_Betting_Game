import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private Team testTeam;

    @BeforeEach
    void setTeam(){
        testTeam = new Team("Test Team", 100);
    }

    @Test
    void compareTo() {
        Team testTeam2 = new Team("Test Team 2", 50);
        testTeam.addPoints(3);
        assertTrue(testTeam.compareTo(testTeam2)>0);
        assertTrue(testTeam2.compareTo(testTeam)<0);
        testTeam2.addPoints(3);
        assertEquals(0, testTeam.compareTo(testTeam2));
    }

    @Test
    void getPoints() {
        assertEquals(0, testTeam.getPoints());
    }

    @Test
    void addPoints() {
        int tmp = testTeam.getPoints();
        testTeam.addPoints(3);
        assertEquals(tmp + 3, testTeam.getPoints());
    }

    @Test
    void getName() {
        assertEquals("Test Team", testTeam.getName());
    }

    @Test
    void getStrength() {
        assertEquals(100, testTeam.getStrength());
    }
}