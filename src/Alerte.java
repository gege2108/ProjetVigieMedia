import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * Classe abstraite {@code Alerte} représentant une alerte qui sera lancee à la vigie.
 */
public abstract class Alerte {
    /**
     * La date et l'heure de l'alerte.
     */
    protected LocalDateTime date;

    /**
     * @return une représentation textuelle de l'alerte, incluant la date formatée en français.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'à' HH'h'mm", Locale.FRENCH);
        return "Date de l'alerte : " + date.format(formatter);
    }

}
