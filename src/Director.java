public class Director {
    public Championship chooseChampionship(int c){
        ChampionshipBuilder championshipBuilder;
        switch (c){
            default:
                championshipBuilder = null;
            case 1:
               championshipBuilder = new SerieABuilder();
        }
        if(championshipBuilder != null) {
            championshipBuilder.setName();
            championshipBuilder.loadTeams();
        }
        else {
            System.out.println("Il numero inserito non corrisponde a nessun campionato");
        }
        return championshipBuilder.getChampionship();
    }
}
