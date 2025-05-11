import java.time.LocalDateTime;

/**
 * Classe abstraite représentant une alerte déclenchée par le module de suivi d'un média particulier d'un certain media (j'ai choisi que ce serait TMC).
 * Une alerte concerne le media TMC
 * Cette classe constitue une base commune pour les types d'alertes spécifiques,
 * telles que celles déclenchées par un dépassement de seuil de mentions.
 *
 */
public abstract class AlerteModuleSuivieMedia extends Alerte {

    /**
     * Le média surveillé à l'origine de l'alerte (TMC).
     */
    protected final Media mediaSuivi;

    /**
     * Personne concernee par l'alerte
     * Peut être {@code null} si l'alerte ne concerne pas une personne.
     */
    protected Personnalite personneAlerte;

    /**
     * Organisation concerenee par l'alerte
     * Peut être {@code null} si l'alerte ne concerne pas une organisation.
     */
    protected Organisation organisationAlerte;

    /**
     * Média concernee par l'alerte
     * Peut être {@code null} si l'alerte ne concerne pas un média.
     */
    protected Media mediaAlerte;

    /**
     * Constructeur quand une personne est concernee.
     *
     * @param mediaSuivi       le média surveillé
     * @param personneAlerte   la personnalité concernee
     * @param date             la date de l'alerte
     */
    public AlerteModuleSuivieMedia(Media mediaSuivi, Personnalite personneAlerte, LocalDateTime date) {
        this.mediaSuivi = mediaSuivi;
        this.personneAlerte = personneAlerte;
        this.organisationAlerte = null;
        this.mediaAlerte = null;
        this.date = date;
    }

    /**
     * Constructeur quand une organisation est concernee.
     *
     * @param mediaSuivi         le média surveillé
     * @param organisationAlerte l'organisation concernee
     * @param date               la date de l'alerte
     */
    public AlerteModuleSuivieMedia(Media mediaSuivi, Organisation organisationAlerte, LocalDateTime date) {
        this.mediaSuivi = mediaSuivi;
        this.personneAlerte = null;
        this.organisationAlerte = organisationAlerte;
        this.mediaAlerte = null;
        this.date = date;
    }

    /**
     * Constructeur quand un media est concernee.
     *
     * @param mediaSuivi     le média surveillé
     * @param mediaAlerte    le média concerne
     * @param date           la date de l'alerte
     */
    public AlerteModuleSuivieMedia(Media mediaSuivi, Media mediaAlerte, LocalDateTime date) {
        this.mediaSuivi = mediaSuivi;
        this.personneAlerte = null;
        this.organisationAlerte = null;
        this.mediaAlerte = mediaAlerte;
        this.date = date;
    }

    /**
     * @return une chaîne représentant l'alerte
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("\n")
                .append("ALERTE VIGIE DU MODULE DE SUIVI DE TMC !!!\n");
        return sb.toString();
    }
}
