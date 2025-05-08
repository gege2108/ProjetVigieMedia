import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class ModuleSuiviePersonne {
    private final Personnalite personneSuivie;
    private List<Media> listMedia;
    private Map<Media,Float> pourcentageMentionMedia;
    private List<Publication> listPublicationConcernee;
    private Vigie vigie;


    public ModuleSuiviePersonne(Personnalite personneSuivie, List<Media> listMedia){
        this.personneSuivie = personneSuivie;
        this.listMedia = listMedia;
        pourcentageMentionMedia = new TreeMap<>();
        listPublicationConcernee = new ArrayList<>();
        vigie = Vigie.getInstance();
    }

    public Map<Media, Float> getPourcentageMentionMedia() {
        return pourcentageMentionMedia;
    }

    public List<Publication> getListPublicationConcernee() {
        return listPublicationConcernee;
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
            float r = ((float)nbMention/listPublication.size()) * 100;
            pourcentageMentionMedia.put(media,r);
        }
        else{
            pourcentageMentionMedia.put(media,0.f);
        }
    }


    public void setListPublicationConcerne(Media mediaPossedantPublication,Publication publication) {

        //Verifie que la publication mentionne bien la personne suivie
        boolean trouve = false;
        for (int i = 0; i < publication.getMentionPersonne().size(); i++) {
            if (publication.getMentionPersonne().get(i).equals(personneSuivie)) {
                trouve = true;
            }
        }

        if (trouve) {
            //envoie une alerte à la vigie si la personne suivie possede le media qui detient la publication
            if (!personneSuivie.afficheMediaPossede(mediaPossedantPublication).equals(personneSuivie.getNomPersonnalite() + " ne possede pas ce media.")) {
                AlerteModuleSuiviePersonne alerteModuleSuiviePersonne = new AlerteModuleSuiviePersonne(personneSuivie, publication, mediaPossedantPublication, LocalDateTime.now());
                notificationVigie(alerteModuleSuiviePersonne);
            } else {
                //Verifie si la personne possede des organisation possedant le media
                for (Map.Entry<Organisation, Float> entry : personneSuivie.getPossedeOrganisation().entrySet()) {
                    if (entry.getKey().getPossedeMedia().containsKey(mediaPossedantPublication)) {
                        AlerteModuleSuiviePersonne alerteModuleSuiviePersonne = new AlerteModuleSuiviePersonne(personneSuivie, publication, mediaPossedantPublication,LocalDateTime.now());
                        notificationVigie(alerteModuleSuiviePersonne);
                    }
                }
            }

            listPublicationConcernee.add(publication);
        }
    }

            public void notificationVigie(AlerteModuleSuiviePersonne alerteModuleSuiviePersonne){
                System.out.println(alerteModuleSuiviePersonne);
                vigie.setListAlerteModuleSuiviePersonne(alerteModuleSuiviePersonne);
            }


            public String afficheHistoriquePublication() {
                StringBuilder sb = new StringBuilder();
                sb.append("Les publications : \n");
                for (int i = 0; i < listPublicationConcernee.size(); i++) {
                    sb.append(listPublicationConcernee.get(i).getTitre()).append("\n");
                }
                sb.append("mentionnent " + personneSuivie.getNomPersonnalite());

                return sb.toString();
            }


            public String affichePourcentageMentionMedia (Media media){
                StringBuilder sb = new StringBuilder();
                if (pourcentageMentionMedia.get(media) != null) {
                    sb.append("Le pourcentage de mention de " + personneSuivie.getNomPersonnalite() + " dans le media " + media.getNomMedia() + " est de " + pourcentageMentionMedia.get(media) + "%");
                } else {
                    sb.append("Le media " + media.getNomMedia() + " ne mentionne " + personneSuivie.getNomPersonnalite() + " dans aucune de ses publications.");
                }

                return sb.toString();
            }



    }