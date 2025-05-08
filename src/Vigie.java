import java.util.ArrayList;
import java.util.List;

public class Vigie {

    //TODO rajouter la date et l'heure
    private final List<AlerteModuleSuiviePersonne> listAlerteModuleSuiviePersonne;

    private Vigie(){
        listAlerteModuleSuiviePersonne = new ArrayList<>();
    }

    // Classe interne qui contient l’unique instance
    private static class Holder {
        private static final Vigie INSTANCE = new Vigie();
    }

    // Méthode publique pour accéder à l’instance
    public static Vigie getInstance() {
        return Holder.INSTANCE;
    }

    public void setListAlerteModuleSuiviePersonne(AlerteModuleSuiviePersonne alerteModuleSuiviePersonne){
        listAlerteModuleSuiviePersonne.add(alerteModuleSuiviePersonne);
    }



    //TODO fonction toString

}
