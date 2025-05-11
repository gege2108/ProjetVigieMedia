import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;


/**
 * Classe abstraite représentant une publication pouvant mentionner
 * des personnalités, des organisations et des médias.
 * Chaque publication possède un titre, un type, une date de publication.
 */

public abstract class Publication {
    /** Liste des personnalités mentionnées dans la publication */
    private List<Personnalite> listMentionPersonne;

    /** Liste des organisations mentionnées dans la publication */
    private List<Organisation> listMentionOrganisation;

    /** Liste des médias mentionnés dans la publication */
    private List<Media> listMentionMedia;

    /** Titre de la publication */
    private String titre;

    /** Type de publication (ex : article, interview, communiqué...) */
    private String typePublication;

    /** Date et heure de publication */
    private LocalDateTime datePublication;

    // === CONSTRUCTEURS ===

    /**
     * Constructeur de base avec titre et date.
     */
    public Publication(String titre,LocalDateTime datePublication) {
        this.titre = titre;
        this.typePublication = "";
        this.listMentionPersonne = new ArrayList<>();
        this.listMentionOrganisation = new ArrayList<>();
        this.listMentionMedia = new ArrayList<>();
        this.datePublication = datePublication;
    }

    /**
     * Constructeur avec titre, type et date.
     */
    public Publication(String titre, String typePublication,LocalDateTime datePublication) {
        this.titre = titre;
        this.typePublication = typePublication;
        this.listMentionPersonne = new ArrayList<>();
        this.listMentionOrganisation = new ArrayList<>();
        this.listMentionMedia = new ArrayList<>();
        this.datePublication = datePublication;
    }


    /**
     * Constructeur avec personnalités et organisations mentionnées.
     */
    public Publication(String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation,String typePublication,LocalDateTime datePublication) {
        this.listMentionPersonne = listMentionPersonne;
        this.listMentionOrganisation = listMentionOrganisation;
        this.titre = titre;
        this.typePublication = typePublication;
        this.listMentionMedia = new ArrayList<>();
        this.datePublication = datePublication;
    }


    /**
     * Constructeur avec organisations uniquement.
     */
    public Publication(List<Organisation> listMentionOrganisation, String titre, String typePublication,LocalDateTime datePublication) {
        this.listMentionPersonne = new ArrayList<>();
        this.listMentionOrganisation = listMentionOrganisation;
        this.titre = titre;
        this.typePublication = typePublication;
        this.listMentionMedia = new ArrayList<>();
        this.datePublication = datePublication;
    }


    /**
     * Constructeur avec personnalités uniquement.
     */
    public Publication(String titre, List<Personnalite> listMentionPersonne,String typePublication,LocalDateTime datePublication) {
        this.listMentionPersonne = listMentionPersonne;
        this.listMentionOrganisation = new ArrayList<>();
        this.titre = titre;
        this.typePublication = typePublication;
        this.listMentionMedia = new ArrayList<>();
        this.datePublication = datePublication;
    }



    /**
     * Constructeur avec médias uniquement.
     */
    public Publication(String titre,String typePublication,List<Media> listMentionMedia,LocalDateTime datePublication) {
        this.listMentionPersonne = new ArrayList<>();
        this.listMentionOrganisation = new ArrayList<>();
        this.titre = titre;
        this.typePublication = typePublication;
        this.listMentionMedia = listMentionMedia;
        this.datePublication = datePublication;
    }

    /**
     * Constructeur avec médias et organisations.
     */
    public Publication(List<Media> listMentionMedia,String titre, List<Organisation> listMentionOrganisation,String typePublication,LocalDateTime datePublication) {
        this.listMentionPersonne = new ArrayList<>();
        this.listMentionOrganisation = listMentionOrganisation;
        this.titre = titre;
        this.typePublication = typePublication;
        this.listMentionMedia = listMentionMedia;
        this.datePublication = datePublication;
    }

    /**
     * Constructeur avec médias et personnalités.
     */
    public Publication(List<Media> listMentionMedia,String titre,String typePublication, List<Personnalite> listMentionPersonne,LocalDateTime datePublication) {
        this.listMentionPersonne = listMentionPersonne;
        this.listMentionOrganisation = new ArrayList<>();
        this.titre = titre;
        this.typePublication = typePublication;
        this.listMentionMedia = listMentionMedia;
        this.datePublication = datePublication;
    }

    /**
     * Constructeur avec médias, personnalités et organisations.
     */
    public Publication(List<Media> listMentionMedia,String titre,String typePublication, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation,LocalDateTime datePublication) {
        this.listMentionPersonne = listMentionPersonne;
        this.listMentionOrganisation = listMentionOrganisation;
        this.titre = titre;
        this.typePublication = typePublication;
        this.listMentionMedia = listMentionMedia;
        this.datePublication = datePublication;
    }


    // === GETTERS ===

    public List<Personnalite> getMentionPersonne() {
        return listMentionPersonne;
    }

    public List<Organisation> getMentionOrganisation() {
        return listMentionOrganisation;
    }

    public String getTitre() {
        return titre;
    }

    public String getTypePublication(){
        return typePublication;
    }

    public List<Media> getListMentionMedia(){
        return listMentionMedia;
    }

    public LocalDateTime getDatePublication(){
        return datePublication;
    }

    // === SETTERS ===

    public void setMentionPersonne(List<Personnalite> listMentionPersonne) {
        this.listMentionPersonne = listMentionPersonne;
    }

    public void setMentionPersonne(Personnalite mentionPersonne) {
        this.listMentionPersonne.add(mentionPersonne);
    }

    public void setMentionOrganisation(List<Organisation> listMentionOrganisation) {
        this.listMentionOrganisation = listMentionOrganisation;
    }

    public void setListMentionOrganisation(Organisation mentionOrganisation) {
        this.listMentionOrganisation.add(mentionOrganisation);
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTypePublication(String typePublication){
        this.typePublication = typePublication;
    }

    public void setListMentionMedia(List<Media> listMentionMedia){
        this.listMentionMedia = listMentionMedia;
    }

    public void setListMentionMedia(Media mentionMedia){
        this.listMentionMedia.add(mentionMedia);
    }

    public void setDatePublication(LocalDateTime datePublication){
        this.datePublication = datePublication;
    }


    // === AUTRES MÉTHODES ===

    /**
     * Retourne une chaîne décrivant la publication et les entités mentionnées.
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean trouve = false;

        if (typePublication.equals("interview")){
            sb.append("\nLa publication ").append(titre).append(" est une ").append(typePublication + "\n");
        } else{
            sb.append("\nLa publication ").append(titre).append("est un ").append(typePublication + "\n");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dateHeureFormatee = datePublication.format(formatter);


        sb.append("Date de publication " + dateHeureFormatee + "\n");




        if (!listMentionPersonne.isEmpty()) {
            trouve = true;
            sb.append("\nCette publication mentionne : ");
            if (listMentionPersonne.size() > 1) {
                sb.append("\nles personnes : ");
                for (int i = 0; i < listMentionPersonne.size(); i++) {
                    sb.append("\n" + listMentionPersonne.get(i).getNomPersonnalite());
                }
            } else {
                sb.append(("\nla personne : ")).append("\n" + listMentionPersonne.get(0).getNomPersonnalite());
            }
        }

        if(!listMentionOrganisation.isEmpty()){
            trouve = true;
            sb.append("\nCette publication mentionne : ");
            if (listMentionOrganisation.size() > 1){
                sb.append("\nles organisations : ");
                for (int i = 0; i < listMentionOrganisation.size(); i++) {
                    sb.append("\n" + listMentionOrganisation.get(i).getNomOrganisation());
                }
            }
            else{
                sb.append(("\nl'organisation : ")).append(listMentionOrganisation.get(0).getNomOrganisation());
            }
        }

        if (!listMentionMedia.isEmpty()) {
            trouve = true;
            sb.append("\nCette publication mentionne : ");
            if (listMentionMedia.size() > 1) {
                sb.append("\nles medias : ");
                for (int i = 0; i < listMentionMedia.size(); i++) {
                    sb.append("\n" + listMentionMedia.get(i).getNomMedia());
                }
            } else {
                sb.append(("\nle media : ")).append(listMentionMedia.get(0).getNomMedia());
            }
        }

        if(!trouve){
            sb.append("\nCette publication ne mentionne aucune personne, aucune organisation et aucun media de la base de donnee.");
        }

        return sb.toString();
    }
}



