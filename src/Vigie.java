import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code Vigie} implémente un modèle Singleton permettant de centraliser les alertes concernant les publications
 * qui mentionnent une personne et un media suivies.
 * Cette classe permet de maintenir une liste d'alertes et de les récupérer sous forme de chaîne de caractères.
 */
public class Vigie {

    /** La liste des alertes gérées par la vigie. */
    private final List<Alerte> listAlerte;

    /**
     * Constructeur privé de la classe {@code Vigie}, empêche la création d'instances supplémentaires.
     */
    private Vigie() {
        listAlerte = new ArrayList<>();
    }

    /**
     * Classe interne qui contient l'instance unique de la classe {@code Vigie}.
     * Elle est utilisée pour appliquer le modèle Singleton.
     */
    private static class Holder {
        /** Instance unique de la vigie. */
        private static final Vigie INSTANCE = new Vigie();
    }

    /**
     * Méthode publique permettant d'accéder à l'instance unique de la vigie.
     *
     * @return L'instance unique de la classe {@code Vigie}.
     */
    public static Vigie getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Ajoute une alerte à la liste des alertes gérées par la vigie.
     *
     * @param alerte L'alerte à ajouter à la liste.
     */
    public void setListAlerte(Alerte alerte) {
        listAlerte.add(alerte);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de toutes les alertes contenues dans la vigie.
     * Chaque alerte est affichée sur une nouvelle ligne.
     *
     * @return Une chaîne de caractères représentant toutes les alertes.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listAlerte.size(); i++) {
            sb.append(listAlerte.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
