import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * La classe ModuleSuiviePersonne permet de mainetenir un historique des publications / diffusions qui concerne la personne
 * suivie (Vincent Bollore dans mon projet), calcule le pourcentage de mentions par média de cette personne.
 * Elle envoie également des alertes à la
 * vigie concernant les publications où la personnalité est mentionnée.
 */
public class ModuleSuiviePersonne {

    /** La personnalité suivie par ce module. */
    private final Personnalite personneSuivie;

    /** La liste des médias surveillés par ce module. */
    private List<Media> listMedia;

    /** La map qui associe chaque média au pourcentage de mentions de la personnalité dans ce média. */
    private Map<Media, Float> pourcentageMentionMedia;

    /** La liste des publications où la personnalité a été mentionnée. */
    private List<Publication> listPublicationConcernee;

    /** L'instance de la vigie pour envoyer des alertes. */
    private Vigie vigie;

    /**
     * Constructeur de la classe ModuleSuiviePersonne.
     *
     * @param personneSuivie La personnalité suivie par ce module.
     * @param listMedia La liste des médias à surveiller pour cette personnalité.
     */
    public ModuleSuiviePersonne(Personnalite personneSuivie, List<Media> listMedia) {
        this.personneSuivie = personneSuivie;
        this.listMedia = listMedia;
        this.pourcentageMentionMedia = new TreeMap<>();
        this.listPublicationConcernee = new ArrayList<>();
        this.vigie = Vigie.getInstance();
    }

    /**
     * Retourne la map des pourcentages de mentions de la personnalité dans les différents médias suivis.
     *
     * @return La map des pourcentages de mentions par média.
     */
    public Map<Media, Float> getPourcentageMentionMedia() {
        return pourcentageMentionMedia;
    }

    /**
     * Retourne la liste des publications concernées par les mentions de la personnalité suivie.
     *
     * @return La liste des publications concernées.
     */
    public List<Publication> getListPublicationConcernee() {
        return listPublicationConcernee;
    }

    /**
     * Calcule et met à jour le pourcentage de mentions de la personnalité dans un média donné.
     *
     * @param media Le média pour lequel le pourcentage de mentions est calculé.
     */
    public void setPourcentageMentionMedia(Media media) {
        List<Publication> listPublication = media.getListPublication();
        int nbMention = 0;
        for (int i = 0; i < listPublication.size(); i++) {
            for (int j = 0; j < listPublication.get(i).getMentionPersonne().size(); j++) {
                if (listPublication.get(i).getMentionPersonne().get(j).equals(personneSuivie)) {
                    nbMention++;
                }
            }
        }
        if (nbMention != 0) {
            float r = ((float) nbMention / listPublication.size()) * 100;
            pourcentageMentionMedia.put(media, r);
        } else {
            pourcentageMentionMedia.put(media, 0.f);
        }
    }

    /**
     * Ajoute une publication à l'historique des publications concernées si elle mentionne la personnalité suivie.
     * Envoie une alerte à la vigie si la publication est détenue par un média possédé par la personnalité ou une organisation qu'elle possède.
     *
     * @param mediaPossedantPublication Le média possédant la publication.
     * @param publication La publication mentionnant la personnalité suivie.
     */
    public void setListPublicationConcerne(Media mediaPossedantPublication, Publication publication) {
        boolean trouve = false;
        for (int i = 0; i < publication.getMentionPersonne().size(); i++) {
            if (publication.getMentionPersonne().get(i).equals(personneSuivie)) {
                trouve = true;
            }
        }

        if (trouve) {
            if (!personneSuivie.afficheMediaPossede(mediaPossedantPublication).equals(personneSuivie.getNomPersonnalite() + " ne possede pas ce media.")) {
                AlerteModuleSuiviePersonne alerteModuleSuiviePersonne = new AlerteModuleSuiviePersonne(personneSuivie, publication, mediaPossedantPublication, LocalDateTime.now());
                notificationVigie(alerteModuleSuiviePersonne);
            } else {
                for (Map.Entry<Organisation, Float> entry : personneSuivie.getPossedeOrganisation().entrySet()) {
                    if (entry.getKey().getPossedeMedia().containsKey(mediaPossedantPublication)) {
                        AlerteModuleSuiviePersonne alerteModuleSuiviePersonne = new AlerteModuleSuiviePersonne(personneSuivie, publication, mediaPossedantPublication, LocalDateTime.now());
                        notificationVigie(alerteModuleSuiviePersonne);
                    }
                }
            }

            listPublicationConcernee.add(publication);
        }
    }

    /**
     * Envoie une alerte à la vigie pour une publication mentionnant la personnalité suivie.
     *
     * @param alerteModuleSuiviePersonne L'alerte contenant les informations sur la publication.
     */
    public void notificationVigie(AlerteModuleSuiviePersonne alerteModuleSuiviePersonne) {
        System.out.println(alerteModuleSuiviePersonne);
        vigie.setListAlerte(alerteModuleSuiviePersonne);
    }

    /**
     * Affiche l'historique des publications mentionnant la personnalité suivie.
     *
     * @return Une chaîne de caractères représentant l'historique des publications.
     */
    public String afficheHistoriquePublication() {
        StringBuilder sb = new StringBuilder();
        sb.append("Les publications : \n");
        for (int i = 0; i < listPublicationConcernee.size(); i++) {
            sb.append(listPublicationConcernee.get(i).getTitre()).append("\n");
        }
        sb.append("mentionnent " + personneSuivie.getNomPersonnalite());

        return sb.toString();
    }

    /**
     * Affiche le pourcentage de mentions de la personnalité dans un média donné.
     *
     * @param media Le média pour lequel le pourcentage est affiché.
     * @return Une chaîne de caractères indiquant le pourcentage de mentions ou un message si aucune mention n'a été trouvée.
     */
    public String affichePourcentageMentionMedia(Media media) {
        StringBuilder sb = new StringBuilder();
        if (pourcentageMentionMedia.get(media) != null) {
            sb.append("Le pourcentage de mention de " + personneSuivie.getNomPersonnalite() + " dans le media " + media.getNomMedia() + " est de " + pourcentageMentionMedia.get(media) + "%");
        } else {
            sb.append("Le media " + media.getNomMedia() + " ne mentionne " + personneSuivie.getNomPersonnalite() + " dans aucune de ses publications.");
        }

        return sb.toString();
    }
}
