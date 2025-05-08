import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Modules : observers, Vigie : observable ou l'inverse jsp

public class ModuleSuiviePersonne {
    private final Personnalite personneSuivie;
    private List<Media> listMedia;
    private Map<Media,Float> pourcentageMentionMedia;
    private List<Publication> listPublicationConcerne;

    public ModuleSuiviePersonne(Personnalite personneSuivie, List<Media> listMedia){
        this.personneSuivie = personneSuivie;
        this.listMedia = listMedia;
        pourcentageMentionMedia = new TreeMap<>();
        listPublicationConcerne = new ArrayList<>();
    }

    public Map<Media, Float> getPourcentageMentionMedia() {
        return pourcentageMentionMedia;
    }

    public List<Publication> getListPublicationConcerne() {
        return listPublicationConcerne;
    }


    public void setPourcentageMentionMedia(Media media) {
        List<Publication> listPublication = media.getListPublication();
        int nbMention = 0;
        for (int i = 0; i < listPublication.size(); i++) {
            for (int j = 0; j < listPublication.get(i).getMentionPersonne().size(); j++) {
                if(listPublication.get(i).getMentionPersonne().get(j).equals(personneSuivie)){
                    nbMention++;
                }
            }
        }
        if (nbMention!=0){
            float r = listPublication.size()/ (float) nbMention;
            pourcentageMentionMedia.put(media,r);
        }
        else{
            pourcentageMentionMedia.put(media,0.f);
        }
    }

    public void setListPublicationConcerne(Publication publication){
        listPublicationConcerne.add(publication);
    }




    //TODO envoyer des alertes à la vigie
}
