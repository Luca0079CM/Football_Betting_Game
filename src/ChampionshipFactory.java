import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChampionshipFactory {
    private Championship championship;
    private String fileLocation;

    public ChampionshipFactory(String fileLocation){
        this.fileLocation = fileLocation;
        this.championship = new Championship();
    }

    public void setChampionshipName(String name){
        championship.setName(name);
    }

    public void loadTeams(){
        try {
            Scanner scanner = new Scanner(new File(fileLocation));
            ArrayList<Team> teams = new ArrayList<>();
            int i = 0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                Team t = new Team(line, i, (int)(Math.random()*((100 - 25)+1)+25));
                teams.add(t);
                i++;
            }
            championship.setTeams(teams);
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        }
    }
    public Championship getChampionship(){
        return championship;
    }
}
