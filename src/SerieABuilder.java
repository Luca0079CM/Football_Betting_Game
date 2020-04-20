import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SerieABuilder implements ChampionshipBuilder {
    private Championship championship;

    SerieABuilder(){
        this.championship = new Championship();
    }

    @Override
    public void setName() {
        championship.setName("SerieA");
    }

    @Override
    public void loadTeams(){
        try {
            Scanner scanner = new Scanner(new File("./ChampionshipFiles/seriea"));
            ArrayList<Team> teams = new ArrayList<>();
            int i = 0;
            while(scanner.hasNextLine()){
                i++;
                String line = scanner.nextLine();
                Team t = new Team(line, i, 0, (int)(Math.random()*50));
                teams.add(t);
            }
            championship.setTeams(teams);
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        }
    }

    @Override
    public Championship getChampionship(){
        return  championship;
    }
}
