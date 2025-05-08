import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class Alerte {
    protected LocalDateTime date;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'à' HH'h'mm", Locale.FRENCH);
        return "Date de l'alerte : " + date.format(formatter);
    }

}
