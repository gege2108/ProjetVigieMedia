import java.util.ArrayList;
import java.util.List;

public class Vigie {

    //TODO rajouter la date et l'heure
    private final List<Alerte> listAlerte;

    private Vigie(){
        listAlerte = new ArrayList<>();
    }

    // Classe interne qui contient l’unique instance
    private static class Holder {
        private static final Vigie INSTANCE = new Vigie();
    }

    // Méthode publique pour accéder à l’instance
    public static Vigie getInstance() {
        return Holder.INSTANCE;
    }

    public void setListAlerte(Alerte alerte){
        listAlerte.add(alerte);
    }



    //TODO fonction toString
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listAlerte.size(); i++) {
            sb.append(listAlerte.get(i).toString()).append("\n");
        }
        return  sb.toString();
    }

}
