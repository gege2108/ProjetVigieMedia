import java.util.List;
import java.util.Map;

public class AlerteModuleSuiviePersonne {
    private final Personnalite personneSuivie;
    private Publication publicationAlerte;
    private Media mediaAlerte;


    public AlerteModuleSuiviePersonne(Personnalite personneSuivie, Publication publicationAlerte, Media mediaAlerte){
        this.personneSuivie = personneSuivie;
        this.publicationAlerte = publicationAlerte;
        this.mediaAlerte = mediaAlerte;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ALERTE VIGIE !!! ").append("La publication ").append(publicationAlerte.getTitre()).append(" est faite par le media ").append(mediaAlerte.getNomMedia()).append(" qui est detenu par ").append(personneSuivie.getNomPersonnalite());
        return sb.toString();
    }

}
