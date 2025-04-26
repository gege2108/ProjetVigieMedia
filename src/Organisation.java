import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;


public class Organisation implements Comparable<Organisation>{
    private String nomOrganisation;
    private String qualificatifOrganisation;
    private String qualificatifMedia;
    private  Map<Organisation,Float> possedeOrganisation;
    private Map<Media,Float> possedeMedia;
    private String commentaire;
    //TODO implementer un attribut commentaire et modifier ainsi le toString et le parsing

    public Organisation(String nomOrganisation){
        this.nomOrganisation = nomOrganisation;
        this.possedeOrganisation = new TreeMap<>();
        this.possedeMedia = new TreeMap<>();
        this.commentaire = "";
        this.qualificatifOrganisation = "";
        this.qualificatifMedia = "";
    }
    public Organisation(String nomOrganisation,String commentaire){
        this.nomOrganisation = nomOrganisation;
        this.possedeOrganisation = new TreeMap<>();
        this.possedeMedia = new TreeMap<>();
        this.commentaire = commentaire;
        this.qualificatifOrganisation = "";
        this.qualificatifMedia = "";
    }

    public Organisation(String nomOrganisation, Map<Organisation,Float> possedeOrganisation,Map<Media,Float> possedeMedia,String commentaire,String qualificatifOrganisation,String qualificatifMedia){
        this.nomOrganisation = nomOrganisation;
        this.possedeOrganisation = possedeOrganisation;
        this.possedeMedia = possedeMedia;
        this.commentaire = commentaire;
        this.qualificatifOrganisation = qualificatifOrganisation;
        this.qualificatifMedia = qualificatifMedia;
    }

    //TODO definir les getters et les setters comme dans Media

    public String getNomOrganisation(){
        return nomOrganisation;
    }

    public Map<Organisation,Float> getPossedeOrganisation(){
        return possedeOrganisation;
    }

    public Map<Media,Float> getPossedeMedia(){
        return possedeMedia;
    }

    public String getCommentaire(String commentaire){
        return commentaire;
    }

    public String getQualificatifOrganisation(){
        return qualificatifOrganisation;
    }

    public String getQualificatifMedia(){
        return qualificatifMedia;
    }

    public void setNomOrganisation(String nomOrganisation){
        this.nomOrganisation = nomOrganisation;
    }

    public void setPossedeOrganisation(Organisation organisation, Float pourcentage){
        this.possedeOrganisation.put(organisation,pourcentage);
    }

    public void setPossedeMedia(Media media,Float pourcentage){
        this.possedeMedia.put(media,pourcentage);
    }

    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }

    public void setQualificatifOrganisation(String qualificatifOrganisation){
        this.qualificatifOrganisation = qualificatifOrganisation;
    }

    public void setQualificatifMedia(String qualificatifMedia){
        this.qualificatifMedia = qualificatifMedia;
    }

    @Override
    public int compareTo(Organisation other) {
        // Compare les organisations par leur nom
        return this.nomOrganisation.compareTo(other.nomOrganisation);
    }



    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("L'organisation ").append(nomOrganisation);

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
            sb.append(", ");
            if(qualificatifMedia.equals("égal à")){
                sb.append(" possède");
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

        if(!commentaire.isEmpty()){
            sb.append("\n commentaire : ").append(commentaire);
        }
        return sb.toString();
    }

    public Float conversionPourcentage(String pourcentage){
        if(pourcentage == ""){
            return null;
        }
        else{
            String temp = pourcentage.replace("%","");
            return Float.parseFloat(temp);
        }
    }

    public String afficheMediaPossede(Media media){
        StringBuilder sb = new StringBuilder();
        sb.append(nomOrganisation);
        for (Map.Entry<Media, Float> entry : possedeMedia.entrySet()) {
            if (entry.getKey().equals(media)){
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
        return sb.toString();
    }

    public String afficheOrganisationPossede(Organisation organisation){

        StringBuilder sb = new StringBuilder();
        sb.append("L'organisation ").append(nomOrganisation);
        boolean trouve = false;

        for (Map.Entry<Organisation, Float> entry : possedeOrganisation.entrySet()) {
            if (entry.getKey().equals(organisation)){
                trouve = true;
                sb.append(",").append(" ");
                if(qualificatifOrganisation.equals("égal à")){
                    sb.append("possède ").append(" ");
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
        sb.append(nomOrganisation);



        if(!possedeMedia.isEmpty()) {
            for (Map.Entry<Media, Float> entry : possedeMedia.entrySet()) {
                sb.append(",");
                if (qualificatifMedia.equals("égal à")) {
                    sb.append("possède ");
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


        return sb.toString();
    }

    public String afficheTouteOrganisationPossede(){

        StringBuilder sb = new StringBuilder();
        sb.append("L'organisation ").append(nomOrganisation);
        boolean trouve = false;

        if (possedeOrganisation != null && !possedeOrganisation.isEmpty()){
            for (Map.Entry<Organisation, Float> entry : possedeOrganisation.entrySet()) {
                if (entry.getKey()!=null){
                    sb.append(",");

                    if (entry.getValue()!=null){



                            if (entry.getValue()!=0.00f){
                                sb.append(" possède").append(" ");
                                if(entry.getKey()!=null){
                                    sb.append(entry.getKey().getNomOrganisation());
                                }
                                sb.append(" à ").append(entry.getValue()).append("%");
                                trouve = true;
                            }

                    }

                    else{

                        sb.append(" ").append("contrôle ").append(" ");
                        if(entry.getKey()!=null){
                            sb.append(entry.getKey().getNomOrganisation());
                        }
                        trouve = true;
                    }






                    if (entry.getKey()!=null){

                        if(!entry.getKey().getPossedeOrganisation().isEmpty()){

                            if (entry.getKey().getPossedeOrganisation() != null){
                                sb.append(" :\n");


                                for (Map.Entry<Organisation, Float> entry2 : entry.getKey().getPossedeOrganisation().entrySet()) {


                                    if (entry2.getValue()!=null){

                                        if (entry2.getValue() != 0.00f){
                                            sb.append(" qui ");
                                            sb.append("possède ");

                                            if(entry2.getKey()!=null){
                                                sb.append(entry2.getKey().getNomOrganisation());
                                            }

                                            sb.append(" à ").append(entry2.getValue()).append("%").append("\n");


                                            trouve = true;
                                        }

                                    }


                                    /*

                                    else{
                                        sb.append(" qui ");
                                        sb.append(entry.getKey().getQualificatifOrganisation()).append(" ");
                                        if(entry2.getKey()!=null){
                                            sb.append(entry2.getKey().getNomOrganisation());
                                        }
                                        trouve = true;
                                    }
                                    */






                                }

                            }


                        }

                    }

                }


                }

        }

        if (!trouve){
            StringBuilder sbPasTrouve = new StringBuilder();
            sbPasTrouve.append("L'organisation ").append(nomOrganisation).append(" ne possede aucune organisation.");
            return sbPasTrouve.toString();
        }

        return sb.toString();
    }
}
