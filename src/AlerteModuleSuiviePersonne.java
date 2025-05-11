import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;

/**
 * Représente une alerte générée par le module de suivi lorsqu'une publication
 * provient d’un média détenu par Vincent Bolloré.
 *
 * Cette classe permet de détecter les publications où Vincent Bolloré
 * est mentionnee dans le cas où la publication est faite par un média que Vincent Bolloré possède.

 */
public class AlerteModuleSuiviePersonne extends Alerte {

    /**
     * La personnalité suivie, potentiellement propriétaire du média concerné(Vincent Bollore dans mon projet).
     */
    private final Personnalite personneSuivie;

    /**
     * La publication à l’origine de l’alerte.
     */
    private Publication publicationAlerte;

    /**
     * Le média ayant publié l’article.
     */
    private Media mediaAlerte;

    /**
     * Construit une alerte à partir d’une personnalité suivie, d’une publication,
     * d’un média et d’une date.
     *
     * @param personneSuivie     la personnalité surveillée (Bolloré)
     * @param publicationAlerte  la publication ayant déclenché l’alerte
     * @param mediaAlerte        le média ayant publié la publication
     * @param date               la date à laquelle l’alerte est déclenchée
     */
    public AlerteModuleSuiviePersonne(Personnalite personneSuivie, Publication publicationAlerte, Media mediaAlerte, LocalDateTime date) {
        this.personneSuivie = personneSuivie;
        this.publicationAlerte = publicationAlerte;
        this.mediaAlerte = mediaAlerte;
        this.date = date;
    }

    /**
     * Retourne une représentation textuelle de l’alerte, incluant
     * la publication, le média émetteur, et la personnalité surveillée.
     *
     * @return une chaîne de caractères décrivant l’alerte
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ALERTE VIGIE DU MODULE DE SUIVI DE VINCENT BOLORE !!! ");
        sb.append(super.toString());
        sb.append("\n")
                .append("La publication ")
                .append(publicationAlerte.getTitre())
                .append(" est faite par le media ")
                .append(mediaAlerte.getNomMedia())
                .append(" qui est détenu par ")
                .append(personneSuivie.getNomPersonnalite());
        return sb.toString();
    }
}

