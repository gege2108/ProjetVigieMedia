import java.time.LocalDateTime;
import java.util.List;

/**
 * La classe {@code Interview} représente une publication de type interview.
 * Elle hérite de la classe {@code Publication} et permet de gérer les informations
 * spécifiques à une interview, notamment les personnes, organisations et médias mentionnés.
 */
public class Interview extends Publication {

    /**
     * Constructeur de la classe {@code Interview} pour une publication mentionnant des personnes et des organisations.
     *
     * @param titre                   Le titre de l'interview.
     * @param listMentionPersonne    La liste des personnalités mentionnées dans l'interview.
     * @param listMentionOrganisation La liste des organisations mentionnées dans l'interview.
     * @param datePublication        La date et l'heure de publication de l'interview.
     */
    public Interview(String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation, LocalDateTime datePublication) {
        super(titre, listMentionPersonne, listMentionOrganisation, "interview", datePublication);
    }

    /**
     * Constructeur de la classe {@code Interview} pour une publication mentionnant uniquement des personnes.
     *
     * @param titre                   Le titre de l'interview.
     * @param listMentionPersonne    La liste des personnalités mentionnées dans l'interview.
     * @param datePublication        La date et l'heure de publication de l'interview.
     */
    public Interview(String titre, List<Personnalite> listMentionPersonne, LocalDateTime datePublication) {
        super(titre, listMentionPersonne, "interview", datePublication);
    }

    /**
     * Constructeur de la classe {@code Interview} pour une publication mentionnant uniquement des organisations.
     *
     * @param listMentionOrganisation La liste des organisations mentionnées dans l'interview.
     * @param titre                   Le titre de l'interview.
     * @param datePublication         La date et l'heure de publication de l'interview.
     */
    public Interview(List<Organisation> listMentionOrganisation, String titre, LocalDateTime datePublication) {
        super(listMentionOrganisation, titre, "interview", datePublication);
    }

    /**
     * Constructeur de la classe {@code Interview} pour une publication sans personne ni organisation mentionnées.
     *
     * @param titre            Le titre de l'interview.
     * @param datePublication  La date et l'heure de publication de l'interview.
     */
    public Interview(String titre, LocalDateTime datePublication) {
        super(titre, "interview", datePublication);
    }

    /**
     * Constructeur de la classe {@code Interview} pour une publication mentionnant uniquement des médias,
     * sans personnes ni organisations. Ce constructeur a été ajouté comme "trick" pour éviter les erreurs de compilation.
     *
     * @param listMentionMedia  La liste des médias mentionnés dans l'interview.
     * @param titre             Le titre de l'interview.
     * @param typePublication   Le type de publication (doit être "interview").
     * @param datePublication   La date et l'heure de publication de l'interview.
     */
    public Interview(List<Media> listMentionMedia, String titre, String typePublication, LocalDateTime datePublication) {
        super(titre, "interview", listMentionMedia, datePublication);
    }

    /**
     * Constructeur de la classe {@code Interview} pour une publication mentionnant des médias et des organisations, sans personnes.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans l'interview.
     * @param titre                Le titre de l'interview.
     * @param typePublication      Le type de publication (doit être "interview").
     * @param listOrganisation     La liste des organisations mentionnées dans l'interview.
     * @param datePublication      La date et l'heure de publication de l'interview.
     */
    public Interview(List<Media> listMentionMedia, String titre, String typePublication, List<Organisation> listOrganisation, LocalDateTime datePublication) {
        super(listMentionMedia, titre, listOrganisation, "interview", datePublication);
    }

    /**
     * Constructeur de la classe {@code Interview} pour une publication mentionnant des médias et des personnes, sans organisations.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans l'interview.
     * @param titre                Le titre de l'interview.
     * @param listMentionPersonne  La liste des personnalités mentionnées dans l'interview.
     * @param typePublication      Le type de publication (doit être "interview").
     * @param datePublication      La date et l'heure de publication de l'interview.
     */
    public Interview(List<Media> listMentionMedia, String titre, List<Personnalite> listMentionPersonne, String typePublication, LocalDateTime datePublication) {
        super(listMentionMedia, titre, "interview", listMentionPersonne, datePublication);
    }

    /**
     * Constructeur de la classe {@code Interview} pour une publication mentionnant des médias, des personnes et des organisations.
     *
     * @param listMentionMedia      La liste des médias mentionnés dans l'interview.
     * @param titre                 Le titre de l'interview.
     * @param listMentionPersonne   La liste des personnalités mentionnées dans l'interview.
     * @param listMentionOrganisation La liste des organisations mentionnées dans l'interview.
     * @param datePublication       La date et l'heure de publication de l'interview.
     */
    public Interview(List<Media> listMentionMedia, String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation, LocalDateTime datePublication) {
        super(listMentionMedia, titre, "interview", listMentionPersonne, listMentionOrganisation, datePublication);
    }
}
