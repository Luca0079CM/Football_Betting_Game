import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class ChampionshipTest {
    private Championship testChampionship;
    private ArrayList<Team> testTeamArrayList;

    @BeforeEach
    void setChampionshipTest(){
        testChampionship = new Championship("Test Championship");
        testTeamArrayList = new ArrayList<>();
        for(int i = 0; i<10; i++){
            testTeamArrayList.add(new Team("Team "+i, i+1));
        }
    }

    @Test
    void getName() {
        assertEquals("Test Championship", testChampionship.getName());
    }

    @Test
    void getTeams() {
        int i = 0;
        for(Team t : testTeamArrayList) {
            assertEquals("Team "+i, t.getName());
            assertEquals(i+1, t.getStrength());
            i++;
        }
    }
}