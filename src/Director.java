public class Director {
    public Championship chooseChampionship(int c){
        ChampionshipBuilder championshipBuilder;
        switch (c){
            default:
                championshipBuilder = null;
            case 1:
               championshipBuilder = new SerieABuilder();
        }
        if(championshipBuilder!=null) {
            championshipBuilder.setName();
            championshipBuilder.loadTeams();
            championshipBuilder.setStrenght();
        }
        return championshipBuilder.getChampionship();
    }
}
