import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ChampionshipBuilderTest {
    private ChampionshipBuilder testChampionshipBuilder;

    @BeforeEach
    void setTestChampionshipFactory(){
        testChampionshipBuilder = new ChampionshipBuilder("./ChampionshipFiles/seriea", "Serie A");
        try {
            testChampionshipBuilder.loadTeams();
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
            assertEquals(testTeams.get(i).getName(), testChampionshipBuilder.getChampionship().getTeams().get(i).getName());
        }
    }

    @Test
    void getChampionship() {
        assertEquals("Serie A", testChampionshipBuilder.getChampionship().getName());
    }

    @Test
    void testEveryTeamHasLessThanMaxStrenght(){
        int max = 100;
        for(Team t : testChampionshipBuilder.getChampionship().getTeams())
            assertTrue(t.getStrength() <= max);
    }



    @Test
    void testFailingLoad() {
        ChampionshipBuilder testChampionshipBuilder2 = new ChampionshipBuilder("./Championship/nothing", " ");
        Throwable exception = assertThrows(Exception.class, testChampionshipBuilder2::loadTeams);
        assertEquals("./Championship/nothing (File o directory non esistente)", exception.getMessage());
    }
}