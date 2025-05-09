import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class ModuleSuivieMedia {
    private final Media mediaSuivi;
    private Map<Media,Integer> nbMentionMedia;
    private Map<Personnalite,Integer> nbMentionPersonne;
    private Map<Organisation,Integer> nbMentionOrganisation;
    private StringBuilder historiqueRachat;
    private Vigie vigie;

    public ModuleSuivieMedia(Media mediaSuivi){
        this.mediaSuivi = mediaSuivi;
        nbMentionMedia = new TreeMap<>();
        nbMentionPersonne = new TreeMap<>();
        nbMentionOrganisation = new TreeMap<>();
        historiqueRachat = new StringBuilder();
        vigie = Vigie.getInstance();
    }

    public Vigie getVigie(){
        return vigie;
    }

    public void notificationVigie(AlerteModuleSuivieMedia alerteModuleSuivieMedia){
        System.out.println(alerteModuleSuivieMedia);
        vigie.setListAlerte(alerteModuleSuivieMedia);
    }

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

    //TODO fonction qui envoie une alerte si une nouvelle personne/organisation rachete un nouveau media + creer l'alerte


    public String afficheMention(){
        StringBuilder sb = new StringBuilder();
        sb.append(mediaSuivi.getNomMedia()).append(" est mentionne par :\n");
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

    public String getHistoriqueRachat(){
        return historiqueRachat.toString();
    }

}
