import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AlerteModuleSuiviePersonne extends Alerte{
    private final Personnalite personneSuivie;
    private Publication publicationAlerte;
    private Media mediaAlerte;


    public AlerteModuleSuiviePersonne(Personnalite personneSuivie, Publication publicationAlerte, Media mediaAlerte, LocalDateTime date){
        this.personneSuivie = personneSuivie;
        this.publicationAlerte = publicationAlerte;
        this.mediaAlerte = mediaAlerte;
        this.date = date;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ALERTE VIGIE DU MODULE DE SUIVI DE VINCENT BOLORE !!! ");
        sb.append(super.toString());
        sb.append("\n")
                .append("La publication ")
                .append(publicationAlerte.getTitre())
                .append(" est faite par le media ")
                .append(mediaAlerte.getNomMedia())
                .append(" qui est detenu par ")
                .append(personneSuivie.getNomPersonnalite());
        return sb.toString();
    }

}
