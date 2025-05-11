import java.util.Map;
import java.util.TreeMap;


import java.util.Map;
import java.util.TreeMap;

/**
 * Représente une personnalité publique qui peut posséder des parts dans des organisations et des médias.
 * Elle est comparable par ordre alphabétique de nom.
 */
public class Personnalite implements Comparable<Personnalite> {
    /** Nom de la personnalité */
    private String nomPersonnalite;

    /** Qualificatif utilisé pour décrire la possession d'organisations (ex: "égal à", "plus de") */
    private String qualificatifOrganisation;

    /** Qualificatif utilisé pour décrire la possession de médias */
    private String qualificatifMedia;

    /** Map des organisations possédées avec les pourcentages correspondants */
    private Map<Organisation, Float> possedeOrganisation;

    /** Map des médias possédés avec les pourcentages correspondants */
    private Map<Media, Float> possedeMedia;

    /**
     * Constructeur minimal avec seulement le nom.
     * @param nomPersonnalite nom de la personnalité
     */
    public Personnalite(String nomPersonnalite){
        this.nomPersonnalite = nomPersonnalite;
        this.qualificatifOrganisation = "";
        this.qualificatifMedia = "";
        this.possedeOrganisation = new TreeMap<>();
        this.possedeMedia = new TreeMap<>();
    }

    /**
     * Constructeur complet.
     * @param nomPersonnalite nom de la personnalité
     * @param qualificatifOrganisation qualificatif pour les organisations
     * @param qualificatifMedia qualificatif pour les médias
     * @param possedeOrganisation organisations possédées avec pourcentage
     * @param possedeMedia médias possédés avec pourcentage
     */
    public Personnalite(String nomPersonnalite, String qualificatifOrganisation, String qualificatifMedia,
                        Map<Organisation,Float> possedeOrganisation, Map<Media,Float> possedeMedia){
        this.nomPersonnalite = nomPersonnalite;
        this.qualificatifOrganisation = qualificatifOrganisation;
        this.qualificatifMedia = qualificatifMedia;
        this.possedeOrganisation = possedeOrganisation;
        this.possedeMedia = possedeMedia;
    }

    /**
     * Convertit une chaîne de type "12.5%" en Float.
     * @param pourcentage chaîne représentant un pourcentage
     * @return valeur float correspondante ou null si chaîne vide
     */
    public Float conversionPourcentage(String pourcentage){
        if(pourcentage.equals("")){
            return null;
        }
        else{
            String temp = pourcentage.replace("%","");
            return Float.parseFloat(temp);
        }
    }

    // Getters

    public String getNomPersonnalite(){
        return nomPersonnalite;
    }

    public String getQualificatifOrganisation(){
        return qualificatifOrganisation;
    }

    public String getQualificatifMedia(){
        return qualificatifMedia;
    }

    public Map<Organisation,Float> getPossedeOrganisation(){
        return possedeOrganisation;
    }

    public Map<Media,Float> getPossedeMedia(){
        return possedeMedia;
    }

    // Setters

    public void setNomPersonnalite(String nomPersonnalite) {
        this.nomPersonnalite = nomPersonnalite;
    }

    public void setQualificatifOrganisation(String qualificatifOrganisation){
        this.qualificatifOrganisation = qualificatifOrganisation;
    }

    public void setQualificatifMedia(String qualificatifMedia){
        this.qualificatifMedia = qualificatifMedia;
    }

    public void setPossedeOrganisation(Organisation organisation, Float pourcentage){
        this.possedeOrganisation.put(organisation,pourcentage);
    }

    public void setPossedeMedia(Media media, Float pourcentage){
        this.possedeMedia.put(media,pourcentage);
    }

    /**
     * Affiche la possession d'un média spécifique.
     * @param media média ciblé
     * @return chaîne descriptive
     */
    public String afficheMediaPossede(Media media){
        StringBuilder sb = new StringBuilder();
        sb.append(nomPersonnalite);
        boolean trouve = false;

        for (Map.Entry<Media, Float> entry : possedeMedia.entrySet()) {
            if (entry.getKey().equals(media)){
                trouve = true;
                sb.append(", ");
                if(qualificatifMedia.equals("égal à")){
                    sb.append("possède ");
                }
                else{
                    sb.append(qualificatifMedia).append(" ");
                }
                if(entry.getKey()!=null){
                    sb.append(entry.getKey().getNomMedia()).append(" ");
                }
                if(entry.getValue()!=null){
                    sb.append("à ").append(entry.getValue()).append("% ");
                }
            }
        }

        if (!trouve){
            sb.append(" ne possède pas ce média.");
        }

        return sb.toString();
    }

    /**
     * Affiche la possession d'une organisation spécifique.
     * @param organisation organisation ciblée
     * @return chaîne descriptive
     */
    public String afficheOrganisationPossede(Organisation organisation){
        StringBuilder sb = new StringBuilder();
        sb.append(nomPersonnalite);
        boolean trouve = false;
        for (Map.Entry<Organisation, Float> entry : possedeOrganisation.entrySet()) {
            if (entry.getKey().equals(organisation)){
                trouve = true;
                sb.append(", ");
                if (entry.getValue()!=null){
                    sb.append("possède ");
                }
                else if(qualificatifOrganisation.equals("égal à")){
                    sb.append("possède ");
                }
                else{
                    sb.append(qualificatifOrganisation).append(" ");
                }
                if(entry.getKey()!=null){
                    sb.append(entry.getKey().getNomOrganisation()).append(" ");
                }
                if(entry.getValue()!=null){
                    sb.append("à ").append(entry.getValue()).append("% ");
                }
            }
        }

        if (!trouve){
            sb.append(" ne possède pas cette organisation.");
        }

        return sb.toString();
    }

    /**
     * Affiche tous les médias possédés directement ou via des organisations.
     * @return chaîne descriptive
     */
    public String afficheToutMediaPossede(){
        StringBuilder sb = new StringBuilder();
        sb.append(nomPersonnalite);
        boolean trouve = false;

        if(!possedeMedia.isEmpty()) {
            trouve = true;
            for (Map.Entry<Media, Float> entry : possedeMedia.entrySet()) {
                sb.append(",");
                if (qualificatifMedia.equals("égal à")) {
                    sb.append("possède");
                } else {
                    sb.append(qualificatifMedia).append(" ");
                }
                if (entry.getKey() != null) {
                    sb.append(entry.getKey().getNomMedia());
                }
                if (entry.getValue() != null) {
                    sb.append(" à ").append(entry.getValue()).append("%");
                }
            }
        }

        for (Map.Entry<Organisation, Float> entry : possedeOrganisation.entrySet()) {
            if (!entry.getKey().getPossedeMedia().isEmpty()){
                if (!trouve){
                    trouve = true;
                }

                sb.append(",");
                if(qualificatifOrganisation.equals("égal à")){
                    sb.append("possède l'organisation ");
                }
                else{
                    sb.append(qualificatifOrganisation).append(" l'organisation ");
                }
                if(entry.getKey()!=null){
                    sb.append(entry.getKey().getNomOrganisation());
                }
                if(entry.getValue()!=null){
                    sb.append(" à ").append(entry.getValue()).append("%");
                }

                sb.append(" :\n");

                for (Map.Entry<Media, Float> entry2 : entry.getKey().getPossedeMedia().entrySet()) {
                    sb.append(" qui ");
                    if(entry.getKey().getQualificatifMedia().equals("égal à")){
                        sb.append("possède ");
                    }
                    else{
                        sb.append(entry.getKey().getQualificatifMedia()).append(" ");
                    }
                    if(entry2.getKey()!=null){
                        sb.append(entry2.getKey().getNomMedia());
                    }
                    if(entry2.getValue()!=null){
                        sb.append(" à ").append(entry2.getValue()).append("%").append("\n");
                    }
                }
            }
        }

        if (!trouve){
            sb.append(" ne possède aucun média.");
        }

        return sb.toString();
    }

    /**
     * Affiche toutes les organisations possédées, ainsi que celles que ces organisations possèdent.
     * @return chaîne descriptive
     */
    public String afficheTouteOrganisationPossede(){
        StringBuilder sb = new StringBuilder();
        sb.append(nomPersonnalite);
        boolean trouve = false;

        if (!possedeOrganisation.isEmpty()){
            trouve = true;
            for (Map.Entry<Organisation, Float> entry : possedeOrganisation.entrySet()) {
                sb.append(",");
                if (entry.getValue()!=null){
                    sb.append("possède ");
                }
                else if(qualificatifOrganisation.equals("égal à")){
                    sb.append("possède");
                }
                else{
                    sb.append(qualificatifOrganisation).append(" ");
                }
                if(entry.getKey()!=null){
                    sb.append(entry.getKey().getNomOrganisation());
                }
                if(entry.getValue()!=null){
                    sb.append(" à ").append(entry.getValue()).append("%");
                }

                if(!entry.getKey().getPossedeOrganisation().isEmpty()){
                    sb.append(" :\n");
                    for (Map.Entry<Organisation, Float> entry2 : entry.getKey().getPossedeOrganisation().entrySet()) {
                        sb.append(" qui ");
                        if(entry.getKey().getQualificatifOrganisation().equals("égal à")){
                            sb.append("possède ");
                        }
                        else{
                            sb.append(entry.getKey().getQualificatifOrganisation()).append(" ");
                        }
                        if(entry2.getKey()!=null){
                            sb.append(entry2.getKey().getNomOrganisation());
                        }
                        if(entry2.getValue()!=null){
                            sb.append(" à ").append(entry2.getValue()).append("%").append("\n");
                        }
                    }
                }
            }
        }

        if (!trouve){
            sb.append(" ne possède aucune organisation.");
        }

        return sb.toString();
    }

    /**
     * Représentation textuelle de la personnalité.
     * Affiche les possessions directes.
     * @return chaîne de caractères
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nomPersonnalite);

        for (Map.Entry<Organisation, Float> entry : possedeOrganisation.entrySet()) {
            sb.append(",");
            if(qualificatifOrganisation.equals("égal à")){
                sb.append(" possède");
            }
            else{
                sb.append(qualificatifOrganisation).append(" ");
            }
            if(entry.getKey()!=null){
                sb.append(entry.getKey().getNomOrganisation());
            }
            if(entry.getValue()!=null){
                sb.append(" à ").append(entry.getValue()).append("%");
            }
        }

        for (Map.Entry<Media, Float> entry : possedeMedia.entrySet()) {
            sb.append(",\t");
            if(qualificatifMedia.equals("égal à")){
                sb.append("possède");
            }
            else{
                sb.append(qualificatifMedia).append(" ");
            }
            if(entry.getKey()!=null){
                sb.append(entry.getKey().getNomMedia());
            }
            if(entry.getValue()!=null){
                sb.append(" à ").append(entry.getValue()).append("%");
            }
        }

        return sb.toString();
    }

    /**
     * Comparaison des personnalités par ordre alphabétique.
     * @param other autre personnalité
     * @return entier de comparaison
     */
    @Override
    public int compareTo(Personnalite other) {
        return this.nomPersonnalite.compareTo(other.nomPersonnalite);
    }
}
