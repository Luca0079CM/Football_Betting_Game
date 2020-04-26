import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Game implements Observer {
    private Championship championship;
    private ArrayList<Match> currentMatches;
    private MatchesGenerator matchesGenerator;
    private int playedMatches;
    private Time time;
    private float money;
    private int moneyBet;
    private ArrayList<Result> pools;
    private DecimalFormat df = new DecimalFormat("0.00");
    private Semaphore mutex = new Semaphore(0);
    private boolean end = false;

    public Game() {
        time = Time.createTimer(this);
        pools = new ArrayList<>();
        money = 10;
        moneyBet = 0;
        chooseChampionship();
    }

    private void chooseChampionship(){
        System.out.println("Benvenuto nel gioco, scegli un campionato digitando il numero corrispondente");
        System.out.println("1 - Serie A (Italia)");
        System.out.println("2 - Premier League (Inghilterra)");
        System.out.println("3 - LaLiga (Spagna)");
        System.out.println("4 - Bundesliga (Germania)");
        System.out.println("5 - Ligue 1 (Francia)");
        System.out.println("6 - Serie B  (Italia)");
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        ChampionshipFactory championshipFactory;
        switch (c) {
            default:
                championshipFactory = null;
                break;
            case 1:
                championshipFactory = new ChampionshipFactory("./ChampionshipFiles/seriea");
                championshipFactory.setChampionshipName("Serie A");
                break;
            case 2:
                championshipFactory = new ChampionshipFactory("./ChampionshipFiles/premierleague");
                championshipFactory.setChampionshipName("Premier League");
                break;
            case 3:
                championshipFactory = new ChampionshipFactory("./ChampionshipFiles/laliga");
                championshipFactory.setChampionshipName("La Liga");
                break;
        }
        if (championshipFactory != null) {
            championshipFactory.loadTeams();
            championship = championshipFactory.getChampionship();
            System.out.println("\nBene! Hai scelto il campionato "+championship.getName()+" a cui partecipano "
                    +championship.getTeams().size()+" squadre");
            championship.setRanking();
            waitSec(10);
            time.start();
            matchesGenerator = new MatchesGenerator(championship);
        } else {
            System.out.println("Il numero inserito non corrisponde a nessun campionato");
            System.exit(0);
        }
    }

    @Override
    public void update() {
        int matchTime = time.getMatchTime();
        if(matchTime%30 == 0){
            System.out.println("Rimangono "+matchTime+" secondi all'inizio della prossima giornata!");
        }
        if(matchTime == 20){
            System.out.println("Rimangono solo 20 secondi all'inizio della prossima giornata!\nAffrettati a completare le tue operazioni, ricorda che non verranno conteggiate dopo la fine del tempo");
        }
        if (matchTime==1) {
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
                end = true;
                mutex.release();
            }else if(money < 1){
                System.out.println("---------HAI FINITO I SOLDI----------");
                time.stop();
                end = true;
                mutex.release();
            }else {
                waitSec(10);
                newMatches();
                time.resetTimer();
                mutex.release();
            }
        }
    }

    private void showMenu() throws InterruptedException{
        while (!end) {
            try {
                Scanner input = new Scanner(System.in);
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
                        printMatches();
                        break;
                    case 2:
                        bet();
                        break;
                    case 3:
                        float tmp = 1;
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
                        System.out.println("Ok! Attendi il prossimo turno");
                        System.out.println("----ATTENZIONE---- Non inserire altri comandi o il gioco terminerà a inizio dei nuovi match");
                        mutex.acquire();
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
            } catch (NoSuchElementException e) {
                System.out.println("\nOperazione non valida");
            }
        }
    }

    public void newMatches() {
        currentMatches = matchesGenerator.generateMatches();
        printMatches();
        try {
            if(playedMatches==0){
                showMenu();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private float checkPools(ArrayList<Result> results, ArrayList<Result> bets, float moneyBet){
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

    private void waitSec(int seconds){
        try {
            Thread.sleep(seconds*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void printMatches(){
        System.out.println("\nMatches:");
        for(Match m : currentMatches)
            m.printMatch();
        System.out.println("\n");
    }
}