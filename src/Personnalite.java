import java.util.Map;
import java.util.TreeMap;

// Utiliser un Comparator avec les TreeMap pour afficher les personnes par ordre alphabetique

public class Personnalite {
    private String nomPersonnalite;
    private String qualificatifOrganisation;
    private String qualificatifMedia;
    private Map<Organisation,Float> possedeOrganisation;
    private Map<Media,Float> possedeMedia;

    public Personnalite(String nomPersonnalite){
        this.nomPersonnalite = nomPersonnalite;
        this.qualificatifOrganisation = "";
        this.qualificatifMedia = "";
        this.possedeOrganisation = new TreeMap<>();
        this.possedeMedia = new TreeMap<>();
    }

    public Personnalite(String nomPersonnalite, String qualificatifOrganisation, String qualificatifMedia, Map<Organisation,Float> possedeOrganisation, Map<Media,Float> possedeMedia){
        this.nomPersonnalite = nomPersonnalite;
        this.qualificatifOrganisation = qualificatifOrganisation;
        this.qualificatifMedia = qualificatifMedia;
        this.possedeOrganisation = possedeOrganisation;
        this.possedeMedia = possedeMedia;
    }

    // TODO implementer les getters et les setters comme dans les classes Media et Organisation

    public Float conversionPourcentage(String pourcentage){
        if(pourcentage == ""){
            return null;
        }
        else{
            String temp = pourcentage.replace("%","");
            return Float.parseFloat(temp);
        }
    }

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


    public String afficheMediaPossede(Media media){
        StringBuilder sb = new StringBuilder();
        sb.append(nomPersonnalite);
        boolean trouve = false;

        for (Map.Entry<Media, Float> entry : possedeMedia.entrySet()) {
            if (entry.getKey().equals(media)){
                trouve = true;
                sb.append(",").append(" ");
                if(qualificatifMedia.equals("égal à")){
                    sb.append("possède").append(" ");
                }
                else{
                    sb.append(qualificatifMedia).append(" ");
                }
                if(entry.getKey()!=null){
                    sb.append(entry.getKey().getNomMedia()).append(" ");
                }
                if(entry.getValue()!=null){
                    sb.append("à ").append(entry.getValue()).append("%").append(" ");
                }
            }
        }

        if (!trouve){
            sb.append(" ne possede pas ce media.");
        }

        return sb.toString();
    }

    public String afficheOrganisationPossede(Organisation organisation){
        StringBuilder sb = new StringBuilder();
        sb.append(nomPersonnalite);
        boolean trouve = false;
        for (Map.Entry<Organisation, Float> entry : possedeOrganisation.entrySet()) {
            if (entry.getKey().equals(organisation)){
                trouve = true;
                sb.append(",").append(" ");
                if (possedeOrganisation.get(organisation)!=null){
                    sb.append("possède").append(" ");
                }
                else if(qualificatifOrganisation.equals("égal à")){
                    sb.append("possède").append(" ");
                }
                else{
                    sb.append(qualificatifOrganisation).append(" ");
                }
                if(entry.getKey()!=null){
                    sb.append(entry.getKey().getNomOrganisation()).append(" ");
                }
                if(entry.getValue()!=null){
                    sb.append("à ").append(entry.getValue()).append("%").append(" ");
                }
            }
        }

        if (!trouve){
            sb.append(" ne possede pas cette organisation.");
        }

        return sb.toString();
    }

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
                sb.append(" ne possede aucun media.");
            }

            return sb.toString();
    }

    public String afficheTouteOrganisationPossede(){
        StringBuilder sb = new StringBuilder();
        sb.append(nomPersonnalite);
        boolean trouve = false;

        if (!possedeOrganisation.isEmpty()){
            trouve = true;
            for (Map.Entry<Organisation, Float> entry : possedeOrganisation.entrySet()) {
                sb.append(",");
                if (possedeOrganisation.get(entry.getKey())!=null){
                    sb.append("possède").append(" ");
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
            sb.append(" ne possede aucune organisation.");
        }

        return sb.toString();
    }



    //TODO ameliorer le toString, prendre en compte le cas où une personnalite ne possede pas d'organisation par exemple (si possedeOrganisation est vide), prendre en compte le cas où le pourcentage a pour valeure null
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
            sb.append(",").append('\t');
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

        // Supprimer la virgule et l'espace supplémentaires à la fin


        return sb.toString();
    }





}
