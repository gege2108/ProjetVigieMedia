import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;


/**
 * Représente le module de suivi d'un média spécifique(TMC), permettant maintient une liste du nombre de mentions
 * par personne / organisation / média. Il permet également de suivre
 * l'historique des rachats de médias et d'envoyer des alertes en cas de mentions importantes
 * ou de changements de propriétaires.
 *
 * Le module gère les alertes via la vigie (instance de surveillance) et envoie des notifications
 * si un seuil de pourcentage de mentions est atteint pour un média, une personnalité ou une organisation (pour cela il faut
 * aussi que TMC le mentionne au moins 3 fois).
 *
 */

public class ModuleSuivieMedia {/**
 * Le média suivi par ce module.
 */
private final Media mediaSuivi;

    /**
     * Map des mentions par média.
     */
    private Map<Media, Integer> nbMentionMedia;

    /**
     * Map des mentions par personnalité.
     */
    private Map<Personnalite, Integer> nbMentionPersonne;

    /**
     * Map des mentions par organisation.
     */
    private Map<Organisation, Integer> nbMentionOrganisation;

    /**
     * Historique des rachats de ce média.
     */
    private StringBuilder historiqueRachat;

    /**
     * Instance de la vigie utilisée pour les alertes.
     */
    private Vigie vigie;

    /**
     * Crée un nouveau module de suivi pour un média spécifique.
     *
     * @param mediaSuivi le média à suivre(TMC dans mon projet)
     */
    public ModuleSuivieMedia(Media mediaSuivi) {
        this.mediaSuivi = mediaSuivi;
        nbMentionMedia = new TreeMap<>();
        nbMentionPersonne = new TreeMap<>();
        nbMentionOrganisation = new TreeMap<>();
        historiqueRachat = new StringBuilder();
        vigie = Vigie.getInstance();
    }

    /**
     * Retourne l'instance de la vigie associée à ce module de suivi.
     *
     * @return l'instance de la vigie
     */
    public Vigie getVigie() {
        return vigie;
    }


    /**
     * Envoie une notification à la vigie en cas d'alerte générée par le module de suivi.
     *
     * @param alerteModuleSuivieMedia l'alerte générée
     */
    public void notificationVigie(AlerteModuleSuivieMedia alerteModuleSuivieMedia){
        System.out.println(alerteModuleSuivieMedia);
        vigie.setListAlerte(alerteModuleSuivieMedia);
    }


    /**
     * Met à jour le nombre de mentions d'un média spécifique et envoie une alerte si
     * un seuil de mentions et de pourcentage mentions est dépassé.
     *
     * @param mediaMentionne le média mentionné
     */
    public void setNbMentionMedia(Media mediaMentionne){
        if(nbMentionMedia.get(mediaMentionne)!=null){
            int r = nbMentionMedia.get(mediaMentionne) + 1;
            nbMentionMedia.put(mediaMentionne,r);
            //alerte à la vigie s'il y a au moins 3 mentions dun même media dans une publication de TMC
            if (r>2){
                if((float)r/ mediaSuivi.getListPublication().size() > 0.5f){
                    LocalDateTime date = LocalDateTime.now();
                    AlerteModuleSuivieMediaSeuil alerteModuleSuivieMediaSeuil = new AlerteModuleSuivieMediaSeuil(mediaSuivi, mediaMentionne,date,(float)r/ mediaSuivi.getListPublication().size());
                    notificationVigie(alerteModuleSuivieMediaSeuil);
                }
            }
        }
        else{
            nbMentionMedia.put(mediaMentionne,1);
        }
    }

    /**
     * Met à jour le nombre de mentions d'un média spécifique et envoie une alerte si
     * un seuil de mentions et de pourcentage mentions est dépassé.
     *
     * @param personneMentionnee la personnalité mentionnée
     */

    public void setNbMentionPersonne(Personnalite personneMentionnee){
        if(nbMentionPersonne.get(personneMentionnee)!=null){
            int r = nbMentionPersonne.get(personneMentionnee) + 1;
            nbMentionPersonne.put(personneMentionnee,r);
            //alerte à la vigie s'il y a au moins 3 mentions dune même personne dans une publication de TMC
            if (r>2){
                if((float)r/ mediaSuivi.getListPublication().size() > 0.5f){
                    LocalDateTime date = LocalDateTime.now();
                    AlerteModuleSuivieMediaSeuil alerteModuleSuivieMediaSeuil = new AlerteModuleSuivieMediaSeuil(mediaSuivi, personneMentionnee,date,(float)r/ mediaSuivi.getListPublication().size());
                    notificationVigie(alerteModuleSuivieMediaSeuil);
                }
            }
        }
        else{
            nbMentionPersonne.put(personneMentionnee,1);
        }
    }


    /**
     * Met à jour le nombre de mentions d'un média spécifique et envoie une alerte si
     * un seuil de mentions et de pourcentage mentions est dépassé.
     *
     * @param organisationMentionnee l'organisation mentionnée
     */
    public void setNbMentionOrganisation(Organisation organisationMentionnee){
        if(nbMentionOrganisation.get(organisationMentionnee)!=null){
            int r = nbMentionOrganisation.get(organisationMentionnee) + 1;
            nbMentionOrganisation.put(organisationMentionnee,r);
            //alerte à la vigie s'il y a au moins 3 mentions dune même organisation dans une publication de TMC
            if (r>2){
                if((float)r/ mediaSuivi.getListPublication().size() > 0.5f){
                    LocalDateTime date = LocalDateTime.now();
                    AlerteModuleSuivieMediaSeuil alerteModuleSuivieMediaSeuil = new AlerteModuleSuivieMediaSeuil(mediaSuivi, organisationMentionnee,date,(float)r/ mediaSuivi.getListPublication().size());
                    notificationVigie(alerteModuleSuivieMediaSeuil);
                }
            }
        }
        else{
            nbMentionOrganisation.put(organisationMentionnee,1);
        }
    }


    /**
     * Enregistre l'historique des rachats d'un média, en incluant une alerte si
     * un nouveau propriétaire est détecté.
     *
     * @param rachat             l'événement de rachat
     * @param nouveauProprietaire vrai si un nouveau propriétaire est détecté
     * @param personneAlerte     nouveau proprietaire si le nouveau proprietaire est une personne, null sinon
     * @param organisationAlerte nouveau proprietaire si le nouveau proprietaire est une organisation, null sinon
     */
    public void setHistoriqueRachat(String rachat,boolean nouveauProprietaire,Personnalite personneAlerte,Organisation organisationAlerte){
        historiqueRachat.append(rachat).append("\n");
        if (nouveauProprietaire){
            LocalDateTime date = LocalDateTime.now();
            if (personneAlerte!=null){
                AlerteModuleSuivieMediaNouveauProprietaire alerteModuleSuivieMediaNouveauProprietaire = new AlerteModuleSuivieMediaNouveauProprietaire(mediaSuivi,personneAlerte,date);
                notificationVigie(alerteModuleSuivieMediaNouveauProprietaire);
            }
            else if(organisationAlerte!=null){
                AlerteModuleSuivieMediaNouveauProprietaire alerteModuleSuivieMediaNouveauProprietaire = new AlerteModuleSuivieMediaNouveauProprietaire(mediaSuivi,organisationAlerte,date);
                notificationVigie(alerteModuleSuivieMediaNouveauProprietaire);
            }
        }
    }


    /**
     * Affiche les mentions d'un média, d'une personnalité et d'une organisation associée
     * sous forme de texte lisible.
     *
     * @return une chaîne de caractères présentant les mentions
     */
    public String afficheMention(){
        StringBuilder sb = new StringBuilder();
        sb.append(mediaSuivi.getNomMedia()).append(" mentionne :\n");
        if (!nbMentionMedia.isEmpty()){
            sb.append("les medias :\n");
            for (Media key : nbMentionMedia.keySet()) {
                sb.append(key.getNomMedia()).append(" : ").append(nbMentionMedia.get(key)).append(" mentions\n");

            }
        }

        if (!nbMentionOrganisation.isEmpty()){
            sb.append("les organisations :\n");
            for (Organisation key : nbMentionOrganisation.keySet()){
                sb.append(key.getNomOrganisation()).append(" : ").append(nbMentionOrganisation.get(key)).append(" mentions\n");
            }
        }

        if (!nbMentionPersonne.isEmpty()){
            sb.append("les personnes :\n");
            for (Personnalite key : nbMentionPersonne.keySet()){
                sb.append(key.getNomPersonnalite()).append(" : ").append(nbMentionPersonne.get(key)).append(" mentions\n");
            }
        }

        return sb.toString();
    }

    /**
     * Retourne l'historique des rachats du média suivi (TMC) sous forme de chaîne de caractères.
     *
     * @return l'historique des rachats
     */

    public String getHistoriqueRachat(){
        return historiqueRachat.toString();
    }

}
