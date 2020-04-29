import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class ChampionshipBuilder {
    private Championship championship;
    private String fileLocation;

    ChampionshipBuilder(String fileLocation, String name){
        this.fileLocation = fileLocation;
        this.championship = new Championship(name);
    }

    void loadTeams() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(fileLocation));
        ArrayList<Team> teams = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            Team t = new Team(line, (int)(Math.random()*((100 - 25)+1)+25));
            teams.add(t);
        }
        championship.setTeams(teams);
    }

    Championship getChampionship(){
        return championship;
    }
}
