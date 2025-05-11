import java.util.*;


/**
 * La classe Media représente un média (presse, télévision, site, radio, etc.)
 * avec des informations associées.
 * Implémente l'interface Comparable pour permettre un tri par ordre alphabétique des médias
 * selon leur nom.
 */
public class Media implements Comparable<Media> {

    /** Nom du média*/
    private String nomMedia;

    /** Type de média (TV, site, presse, radio)*/
    private String type;

    /** Périodicité de publication (quotidien, hebdo, etc.)*/
    private String periodicite;

    /** Échelle géographique du média (France, Europe, etc.)*/
    private String echelle;

    /** Gratuit ou payant*/
    private String prix;

    /**"checked" si le média a disparu, chaîne vide sinon*/
    private String disparu;

    /** Liste des publications associées*/
    private List<Publication> listPublication;

    /**
     * Constructeur minimal avec uniquement le nom du média.
     * Les autres champs sont initialisés à une chaîne vide.
     *
     * @param nomMedia Nom du média
     */
    public Media(String nomMedia) {
        this.nomMedia = nomMedia;
        this.type = "";
        this.periodicite = "";
        this.echelle = "";
        this.prix = "";
        this.disparu = "";
        this.listPublication = new ArrayList<>();
    }

    /**
     * Constructeur complet permettant d'initialiser tous les champs.
     *
     * @param nomMedia    Nom du média
     * @param type        Type du média
     * @param periodicite Périodicité de publication
     * @param echelle     Échelle géographique
     * @param prix        Prix (gratuit ou payant)
     * @param disparu     Statut de disparition ("checked" ou vide)
     */
    public Media(String nomMedia, String type, String periodicite, String echelle, String prix, String disparu) {
        this.nomMedia = nomMedia;
        this.type = type;
        this.periodicite = periodicite;
        this.echelle = echelle;
        this.prix = prix;
        this.disparu = disparu;
        this.listPublication = new ArrayList<>();
    }

    // ------------------- Getters -------------------

    /** @return le nom du média */
    public String getNomMedia() { return nomMedia; }

    /** @return le type du média */
    public String getType() { return type; }

    /** @return la périodicité du média */
    public String getPeriodicite() { return periodicite; }

    /** @return l'échelle géographique du média */
    public String getEchelle() { return echelle; }

    /** @return le prix du média */
    public String getPrix() { return prix; }

    /** @return le statut de disparition du média */
    public String getDisparu() { return disparu; }

    /** @return la liste des publications du média */
    public List<Publication> getListPublication() { return listPublication; }

    // ------------------- Setters -------------------

    /** Définit le nom du média  */
    public void setNomMedia(String nomMedia) { this.nomMedia = nomMedia; }

    /** Définit le type de média */
    public void setType(String type) { this.type = type; }

    /** Définit la périodicité du média */
    public void setPeriodicite(String periodicite) { this.periodicite = periodicite; }

    /** Définit l'échelle géographique du média */
    public void setEchelle(String echelle) { this.echelle = echelle; }

    /** Définit le prix (gratuit ou payant) */
    public void setPrix(String prix) { this.prix = prix; }

    /** Définit le statut de disparition du média */
    public void setDisparu(String disparu) { this.disparu = disparu; }

    /**
     * Ajoute une publication à la liste des publications du média.
     *
     * @param publication La publication à ajouter
     */
    public void setListPublication(Publication publication) {
        this.listPublication.add(publication);
    }

    /**
     * Affiche dans la console toutes les publications associées au média.
     * Si la liste est vide, un message indique l'absence de publication.
     */
    public void afficheListPublication() {
        if (listPublication.isEmpty()) {
            System.out.println("Le media " + nomMedia + " ne possède pas de publication.");
        } else {
            for (int i = 0; i < listPublication.size(); i++) {
                System.out.println(listPublication.get(i));
            }
        }
    }

    /**
     * Retourne une description textuelle complète du média,
     * en précisant son type, périodicité, échelle, prix, et disparition.
     *
     * @return une chaîne représentant le média
     */

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Le media : ").append(nomMedia).append('\n').append('\t');
        if(!type.isEmpty()){
            if(type.equals("Télévision")){
                sb.append("est disponible à la television\n\t");
            }
            else if (type.equals("Site")){
                sb.append("est disponible depuis un site\n\t");
            }
            else if (type.equals("Presse (généraliste politique économique)")){
                sb.append("est disponible depuis la presse \n\t");
            }
            else if(type.equals("Radio")){
                sb.append("est disponible à la radio\n\t");
            }
        }
        if(!periodicite.isEmpty()){
            if(periodicite.equals("Quotidien")){
                sb.append("a une periodicite quotidienne\n\t");
            }
            else if(periodicite.equals("Hebdomadaire")){
                sb.append("a une periodicite hebdomadaire\n\t");
            }
            else if(periodicite.equals("Mensuel")){
                sb.append("a une periodicite mensuel\n\t");
            }
            else{
                sb.append("a une periodicite bimestrielle\n\t");
            }
        }

        if(!echelle.isEmpty()){
            sb.append("est un media ");
            if(echelle.equals("Europe")){
                sb.append("europeen");
            }
            else if (echelle.equals("Monaco")){
                sb.append("de Monaco");
            }
            else{
                sb.append(echelle);
            }
            sb.append("\n\t");
        }

        if(!prix.isEmpty()){

            if (prix.equals("Gratuit")){
                sb.append("est gratuit");
            }
            else if(prix.equals("Payant")){
                sb.append("est payant");
            }

            sb.append("\n\t");
        }

        if(!disparu.isEmpty()){
            if(disparu.equals("checked")){
                sb.append("a disparu");
            }
        }

        return sb.toString();
    }



    /**
     * Permet de comparer deux objets Media en fonction du nom du média.
     *
     * @param other le média à comparer
     * @return un entier négatif, nul ou positif selon l'ordre lexicographique
     */
    @Override
    public int compareTo(Media other) {
        // Compare les medias par leur nom
        return this.nomMedia.compareTo(other.nomMedia);
    }
}
