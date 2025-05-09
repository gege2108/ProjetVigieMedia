import java.time.LocalDateTime;

public class AlerteModuleSuivieMediaSeuil extends AlerteModuleSuivieMedia{

    private float pourcentageMention;

    public AlerteModuleSuivieMediaSeuil(Media mediasuivi,Personnalite personneAlerte,LocalDateTime date,float pourcentageMention){
        super(mediasuivi,personneAlerte,date);
        this.pourcentageMention = pourcentageMention;
    }

    public AlerteModuleSuivieMediaSeuil(Media mediasuivi,Organisation organisationAlerte,LocalDateTime date,float pourcentageMention){
        super(mediasuivi,organisationAlerte,date);
        this.pourcentageMention = pourcentageMention;
    }

    public AlerteModuleSuivieMediaSeuil(Media mediasuivi,Media mediaAlerte,LocalDateTime date,float pourcentageMention){
        super(mediasuivi,mediaAlerte,date);
        this.pourcentageMention = pourcentageMention;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("ALERTE POURCENTAGE MENTION!!!\n");
        if (personneAlerte!=null){
            sb.append("La personne ").append(personneAlerte.getNomPersonnalite());
        }
        else if(organisationAlerte!=null){
            sb.append("L'organisation ").append(organisationAlerte.getNomOrganisation());
        }
        else{
            sb.append("Le media ").append(mediaAlerte.getNomMedia());
        }

        sb.append(" est mentionne à plus de 50 % dans les publications du media TMC.");

        return sb.toString();
    }
}
