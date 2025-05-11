import java.time.LocalDateTime;


/**
 * Représente une alerte déclenchée lorsqu'un {@link Media} a un nouveau propriétaire.
 * Le nouveau propriétaire peut être une {@link Personnalite} ou une {@link Organisation}.
 * Cette classe hérite de {@link AlerteModuleSuivieMedia}.
 * Exemple d'affichage :

 */

public class AlerteModuleSuivieMediaNouveauProprietaire extends AlerteModuleSuivieMedia{

    /**
     * Crée une alerte signalant qu'une {@link Personnalite} est devenue propriétaire d'un média suivi.
     *
     * @param mediaSuivi       le média concerné
     * @param personneAlerte   la personnalité devenue propriétaire
     * @param date             la date de l'alerte
     */

   public AlerteModuleSuivieMediaNouveauProprietaire(Media mediaSuivi, Personnalite personneAlerte, LocalDateTime date){
       super(mediaSuivi,personneAlerte,date);
   }

    /**
     * Crée une alerte signalant qu'une {@link Organisation} est devenue propriétaire d'un média suivi.
     *
     * @param mediaSuivi         le média concerné
     * @param organisationAlerte l'organisation devenue propriétaire
     * @param date               la date de l'alerte
     */

    public AlerteModuleSuivieMediaNouveauProprietaire(Media mediaSuivi, Organisation organisationAlerte, LocalDateTime date){
        super(mediaSuivi,organisationAlerte,date);
    }


    /**
     * Retourne une représentation textuelle de l'alerte,
     * incluant le type d'alerte et le nom du nouveau propriétaire.
     *
     * @return une chaîne représentant cette alerte
     */
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
