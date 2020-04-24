import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game implements Observer {
    private Championship championship;
    private ArrayList<Match> currentMatches;
    private MatchesGenerator matchesGenerator;
    private int playedMatches;
    private Time time;
    private boolean timeUp = false;
    private float money;
    private int moneyBet;
    private ArrayList<Result> pools;
    private DecimalFormat df = new DecimalFormat("0.00");

    public Game() {
        time = Time.createTimer(this);
        chooseChampionship(1);
        championship.setRanking();
        time.start();
        pools = new ArrayList<>();
        money = 10;
        moneyBet = 0;
    }

    public void chooseChampionship(int c) {
        ChampionshipBuilder championshipBuilder;
        switch (c) {
            default:
                championshipBuilder = null;
                break;
            case 1:
                championshipBuilder = new SerieABuilder();
                break;
        }
        if (championshipBuilder != null) {
            championshipBuilder.setName();
            championshipBuilder.loadTeams();
        } else {
            System.out.println("Il numero inserito non corrisponde a nessun campionato");
        }
        championship = championshipBuilder.getChampionship();
        matchesGenerator = new MatchesGenerator(championship);
    }

    @Override
    public void update() {
        timeUp = true;
        ArrayList<Result> results = new ArrayList<>();
        for (Match m : currentMatches) {
            results.add(new Result(m.getCode(), m.simulateMatch()));
            m.printMatch();
        }
        money += checkPools(results, pools, moneyBet);
        playedMatches++;
        championship.setRanking();
        moneyBet = 0;
        pools.clear();
        if (playedMatches == championship.getTeams().size() - 1) {
            System.out.println("---------FINE CAMPIONATO----------");
            System.out.println("Classifica finale:");
            championship.setRanking();
            time.stop();
        }else if(money < 1){
            System.out.println("---------HAI FINITO I SOLDI----------");
            time.stop();
        }else {
            newMatches();
            timeUp = false;
            showMenu();
        }
    }

    private void showMenu(){
        boolean wait = false;
        while (!timeUp && !wait) {
            try {
                Scanner input = new Scanner(System.in);
                input.reset();
                System.out.println("\nPortafoglio:"+df.format(money)+" euro");
                System.out.println("Che operazione vuoi fare? (Digita il numero corrispondente)");
                System.out.println("1-Vedi i match della giornata n°" + (playedMatches + 1));
                System.out.println("2-Scommetti su un match della giornata");
                System.out.println("3-Vedi la schedina");
                System.out.println("4-Attendi i match");
                System.out.println("5-Inserisci l'importo da scommettere");
                if(!pools.isEmpty() && moneyBet==0){
                    System.out.println("\nATTENZIONE sono presenti scommesse ma non l'importo, premi 5 per inserirlo");
                }
                int c = input.nextInt();
                switch (c) {
                    default:
                        System.out.println("\nNon c'è nessun operazione corrispondente al numero inserito");
                        break;
                    case 1:
                        matchesGenerator.printMatches();
                        break;
                    case 2:
                        bet();
                        break;
                    case 3:
                        int tmp = 1;
                        if (!pools.isEmpty()) {
                            System.out.println("--Schedina--");
                            for(Result b : pools) {
                                for(Match m : currentMatches){
                                    if(m.getCode() == b.getMatchCode()) {
                                        System.out.println();
                                        m.printMatch();
                                        System.out.println("Scommessa: "+b.getResult());
                                        if(b.getResult().equals("1")){
                                            tmp *= m.getBet().getQuotes()[0];
                                        }else if(b.getResult().equals("X")){
                                            tmp *= m.getBet().getQuotes()[1];
                                        }else{
                                            tmp *= m.getBet().getQuotes()[2];
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("La schedina è vuota");
                            break;
                        }
                        System.out.println("\nScommessi "+df.format(moneyBet)+" euro");
                        System.out.println("Potenziale vincita "+df.format(moneyBet*tmp)+" euro");
                        break;
                    case 4:
                        wait = true;
                        System.out.println("Ok! Attendi il prossimo turno");
                        System.out.println("----ATTENZIONE---- Non inserire altri comandi o il gioco terminerà a inizio dei nuovi match");
                        break;
                    case 5:
                        if(moneyBet != 0){
                            System.out.println("\nHai già inserito la scommessa");
                            break;
                        }
                        System.out.println("\nSono accettati solo importi interi");
                        Scanner sc = new Scanner(System.in);
                        int tmp1 = sc.nextInt();
                        if(tmp1 > 0 && tmp1 <= money){
                            moneyBet = tmp1;
                            money -= moneyBet;
                        }else{
                            System.out.println("L'importo inserito non è valido");
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nOperazione non valida");
            }
        }
    }

    public void newMatches() {
        currentMatches = matchesGenerator.generateMatches();
        matchesGenerator.printMatches();
        showMenu();
    }

    private int checkPools(ArrayList<Result> results, ArrayList<Result> bets, int moneyBet){
        boolean win = true;
        if(bets.size()==0){
            System.out.println("\nNon avevi scommesso niente");
            return 0;
        }
        for (Result b : bets) {
            for(Result r : results){
                if(b.getMatchCode() == r.getMatchCode() && !b.getResult().equals(r.getResult())) {
                    win = false;
                    System.out.println("\nPeccato, non hai vinto");
                    break;
                }
            }
        }
        if(win){
            for (Result b : bets) {
                for(Match m : currentMatches){
                    if(b.getMatchCode() == m.getCode()) {
                        String s = b.getResult();
                        if(s.equals("1"))
                            moneyBet*=m.getBet().getQuotes()[0];
                        else if(s.equals("X"))
                            moneyBet*=m.getBet().getQuotes()[1];
                        else if(s.equals("2"))
                            moneyBet*=m.getBet().getQuotes()[2];
                    }
                }
            }
            System.out.println("\nComplimenti!! Hai vinto "+df.format(moneyBet)+" euro");
        }else
            moneyBet = 0;

        return moneyBet;
    }

    private void bet(){
        System.out.println("Inserisci il codice della partita su cui vuoi scommettere:");
        Scanner sc = new Scanner(System.in);
        int code = sc.nextInt();
        boolean found = false;
        for (Match m : currentMatches){
            if (code == m.getCode()){
                found = true;
                System.out.println("Match trovato! Inserisci la scommessa:");
                break;
            }
        }
        if (!found){
            System.out.println("Il codice inserito non corrisponde a nessun match della giornata, riprova");
        }else{
            String s = sc.next();
            if (s.equals("1")||s.equals("X")||s.equals("2")){
                pools.add(new Result(code, s));
            }else {
                System.out.println("Scommessa non valida!");
            }
        }
    }
}