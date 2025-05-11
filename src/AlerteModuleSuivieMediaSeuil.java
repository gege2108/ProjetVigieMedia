import java.time.LocalDateTime;

/**
 * Représente une alerte générée par le module de suivi lorsqu'une personnalité, une organisation,
 * ou un autre média est mentionné dans plus de 50 % des publications de TMC(si TMC a au moins 3 publications mentionnant la personne
 * /organisation/media).
 */
public class AlerteModuleSuivieMediaSeuil extends AlerteModuleSuivieMedia {

    /**
     * Pourcentage de mentions détecté, utilisé pour générer l'alerte.
     */
    private float pourcentageMention;

    /**
     * Constructeur de l'alerte quand la mention concerne une personnalité.
     *
     * @param mediasuivi         le média surveillé
     * @param personneAlerte     la personnalité mentionnée
     * @param date               la date de l'alerte
     * @param pourcentageMention le pourcentage de mentions déclenchant l'alerte
     */
    public AlerteModuleSuivieMediaSeuil(Media mediasuivi, Personnalite personneAlerte, LocalDateTime date, float pourcentageMention) {
        super(mediasuivi, personneAlerte, date);
        this.pourcentageMention = pourcentageMention;
    }

    /**
     * Constructeur de l'alerte quand la mention concerne une organisation.
     *
     * @param mediasuivi         le média surveillé
     * @param organisationAlerte l'organisation mentionnée
     * @param date               la date de l'alerte
     * @param pourcentageMention le pourcentage de mentions déclenchant l'alerte
     */
    public AlerteModuleSuivieMediaSeuil(Media mediasuivi, Organisation organisationAlerte, LocalDateTime date, float pourcentageMention) {
        super(mediasuivi, organisationAlerte, date);
        this.pourcentageMention = pourcentageMention;
    }

    /**
     * Constructeur de l'alerte quand la mention concerne un autre média.
     *
     * @param mediasuivi         le média surveillé
     * @param mediaAlerte        le média mentionné
     * @param date               la date de l'alerte
     * @param pourcentageMention le pourcentage de mentions déclenchant l'alerte
     */
    public AlerteModuleSuivieMediaSeuil(Media mediasuivi, Media mediaAlerte, LocalDateTime date, float pourcentageMention) {
        super(mediasuivi, mediaAlerte, date);
        this.pourcentageMention = pourcentageMention;
    }

    /**
     * Retourne une représentation textuelle de l'alerte, incluant le type de mention concerné
     * (personnalité, organisation ou média) et le message d'alerte si le seuil est dépassé.
     *
     * @return une chaîne décrivant l'alerte
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("ALERTE POURCENTAGE MENTION!!!\n");

        if (personneAlerte != null) {
            sb.append("La personne ").append(personneAlerte.getNomPersonnalite());
        } else if (organisationAlerte != null) {
            sb.append("L'organisation ").append(organisationAlerte.getNomOrganisation());
        } else {
            sb.append("Le media ").append(mediaAlerte.getNomMedia());
        }

        sb.append(" est mentionné à plus de 50 % dans les publications du média TMC.");

        return sb.toString();
    }
}
