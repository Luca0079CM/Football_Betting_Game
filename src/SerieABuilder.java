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
            Scanner scanner = new Scanner(new File("./Championship/seriea"));
            ArrayList teams = new ArrayList();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                teams.add(line);
            }
            championship.setTeams(teams);
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        }
    }

    @Override
    public void setStrenght() {

    }

    @Override
    public Championship getChampionship(){
        return  championship;
    }
}
