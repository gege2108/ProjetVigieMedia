import java.time.LocalDateTime;

public abstract class AlerteModuleSuivieMedia extends  Alerte{
    protected final Media mediaSuivi;
    protected Personnalite personneAlerte;
    protected Organisation organisationAlerte;
    protected Media mediaAlerte;

    public AlerteModuleSuivieMedia(Media mediaSuivi, Personnalite personneAlerte, LocalDateTime date){
        this.mediaSuivi = mediaSuivi;
        this.personneAlerte = personneAlerte;
        organisationAlerte = null;
        mediaAlerte = null;
        this.date = date;
    }

    public AlerteModuleSuivieMedia(Media mediaSuivi,Organisation organisationAlerte,LocalDateTime date){
        this.mediaSuivi = mediaSuivi;
        personneAlerte = null;
        this.organisationAlerte = organisationAlerte;
        mediaAlerte = null;
        this.date = date;
    }

    public AlerteModuleSuivieMedia(Media mediaSuivi,Media mediaAlerte,LocalDateTime date){
        this.mediaSuivi = mediaSuivi;
        personneAlerte = null;
        organisationAlerte = null;
        this.mediaAlerte = mediaAlerte;
        this.date = date;
    }




    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("\n")
                .append("ALERTE VIGIE DU MODULE DE SUIVI DE TMC !!!\n");
        return sb.toString();
    }
}
