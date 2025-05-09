import java.time.LocalDateTime;

public class AlerteModuleSuivieMediaNouveauProprietaire extends AlerteModuleSuivieMedia{

   public AlerteModuleSuivieMediaNouveauProprietaire(Media mediaSuivi, Personnalite personneAlerte, LocalDateTime date){
       super(mediaSuivi,personneAlerte,date);
   }

    public AlerteModuleSuivieMediaNouveauProprietaire(Media mediaSuivi, Organisation organisationAlerte, LocalDateTime date){
        super(mediaSuivi,organisationAlerte,date);
    }

    @Override
    public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append(super.toString())
               .append("ALERTE NOUVEAU PROPRIETAIRE!!!\n")
               .append("Nouveau proprietaire : ");


       if (personneAlerte!=null){
           sb.append("la personne : ")
                   .append(personneAlerte.getNomPersonnalite());
       }
       else{
           sb.append("l'organisation ")
                   .append(organisationAlerte.getNomOrganisation());
       }

       return sb.toString();
    }
}
