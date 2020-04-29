import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ChampionshipFactoryTest {
    private ChampionshipFactory testChampionshipFactory;

    @BeforeEach
    void setTestChampionshipFactory(){
        testChampionshipFactory = new ChampionshipFactory("./ChampionshipFiles/seriea", "Serie A");
        try {
            testChampionshipFactory.loadTeams();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLoadTeams() throws FileNotFoundException {
        Championship testChampionship = new Championship("Serie A");
        Scanner scanner = new Scanner(new File("./ChampionshipFiles/seriea"));
        ArrayList<Team> testTeams = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            Team t = new Team(line, 100);
            testTeams.add(t);
        }
        testChampionship.setTeams(testTeams);

        for(int i = 0; i<testTeams.size(); i++){
            assertEquals(testTeams.get(i).getName(), testChampionshipFactory.getChampionship().getTeams().get(i).getName());
        }
    }

    @Test
    void getChampionship() {
        assertEquals("Serie A", testChampionshipFactory.getChampionship().getName());
    }

    @Test
    void testEveryTeamHasLessThanMaxStrenght(){
        int max = 100;
        for(Team t : testChampionshipFactory.getChampionship().getTeams())
            assertTrue(t.getStrength() <= max);
    }



    @Test
    void testFailingLoad() {
        ChampionshipFactory testChampionshipFactory2 = new ChampionshipFactory("./Championship/nothing", " ");
        Throwable exception = assertThrows(Exception.class, testChampionshipFactory2::loadTeams);
        assertEquals("./Championship/nothing (File o directory non esistente)", exception.getMessage());
    }
}