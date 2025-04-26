import java.util.*;


public class Media implements Comparable<Media>{
    // Pas sur de l'attribut nomMedia finalement
    private String nomMedia;
    private String type;
    private String periodicite;
    private String echelle;
    private String prix;
    private String disparu;
    private List<Publication> listPublication;


    public Media(String nomMedia){
        this.nomMedia = nomMedia;
        this.type = "";
        this.periodicite = "";
        this.echelle = "";
        this.prix = "";
        this.disparu = "";
        this.listPublication = new ArrayList<>();
    }


    public Media(String nomMedia,String type, String periodicite, String echelle, String prix, String disparu){
        this.nomMedia = nomMedia;
        this.type = type;
        this.periodicite = periodicite;
        this.echelle = echelle;
        this.prix = prix;
        this.disparu = disparu;
        this.listPublication = new ArrayList<>();
    }

    public String getNomMedia(){
        return nomMedia;
    }

    public String getType(){
        return type;
    }

    public String getPeriodicite(){
        return periodicite;
    }

    public String getEchelle(){
        return echelle;
    }

    public String getPrix(){
        return prix;
    }

    public String getDisparu(){
        return disparu;
    }

    public List<Publication> getListPublication(){
        return listPublication;
    }




    // peut etre a changer car affichage par ordre lexicographique





    // TODO implementer les setter et demander a l'utilisateur d'entrer un mdp administrateur
    // Pour les Map modifier de sorte que les setters prennent un pourcentage et une
    // Organisation/Personnalite en argument et que si l'Organisation/Personnalite existe
    // modifier juste le pourcentage correspondant sinon rajouter une nouvelle entree dans la Map
    // Convertir les float en Float dans les setters

    // Pas sur d'implementer setNomMedia
    public void setNomMedia(String nomMedia){
        this.nomMedia = nomMedia;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setPeriodicite(String periodicite){
        this.periodicite = periodicite;
    }

    public void setEchelle(String echelle){
        this.echelle = echelle;
    }

    public void setPrix(String prix){
        this.prix = prix;
    }

    public void setDisparu(String disparu){
        this.disparu = disparu;
    }

    public void setListPublication(Publication publication){
        this.listPublication.add(publication);
    }

    public void afficheListPublication(){
        if (listPublication.isEmpty()){
            System.out.println("Le media " + nomMedia + " ne possede pas de publication.");
        }
        else{
            for (int i = 0; i < listPublication.size(); i++) {
                System.out.println(listPublication.get(i));
            }
        }
    }

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


    @Override
    public int compareTo(Media other) {
        // Compare les medias par leur nom
        return this.nomMedia.compareTo(other.nomMedia);
    }
}
