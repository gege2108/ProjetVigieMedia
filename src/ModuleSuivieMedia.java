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

    public void setNbMentionMedia(Media mediaMentionnant){
        if(nbMentionMedia.get(mediaMentionnant)!=null){
            int r = nbMentionMedia.get(mediaMentionnant) + 1;
            nbMentionMedia.put(mediaMentionnant,r);
        }
        else{
            nbMentionMedia.put(mediaMentionnant,1);
        }
    }

    public void setNbMentionPersonne(Personnalite personneMentionnant){
        if(nbMentionPersonne.get(personneMentionnant)!=null){
            int r = nbMentionPersonne.get(personneMentionnant) + 1;
            nbMentionPersonne.put(personneMentionnant,r);
        }
        else{
            nbMentionPersonne.put(personneMentionnant,1);
        }
    }

    public void setNbMentionOrganisation(Organisation organisationMentionnant){
        if(nbMentionOrganisation.get(organisationMentionnant)!=null){
            int r = nbMentionOrganisation.get(organisationMentionnant) + 1;
            nbMentionOrganisation.put(organisationMentionnant,r);
        }
        else{
            nbMentionOrganisation.put(organisationMentionnant,1);
        }
    }

    public void setHistoriqueRachat(String rachat){
        historiqueRachat.append(rachat).append("\n");
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
