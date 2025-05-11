import java.time.LocalDateTime;
import java.util.List;

/**
 * La classe {@code Reportage} représente une publication de type reportage.
 * Elle hérite de la classe {@code Publication} et permet de gérer les informations
 * spécifiques à un reportage, notamment les personnes, organisations et médias mentionnés.
 */
public class Reportage extends Publication {

    /**
     * Constructeur de la classe {@code Reportage} pour une publication mentionnant des personnes et des organisations, mais pas de médias.
     *
     * @param titre                Le titre de la publication.
     * @param listMentionPersonne  La liste des personnes mentionnées dans la publication.
     * @param listMentionOrganisation La liste des organisations mentionnées dans la publication.
     * @param datePublication      La date et l'heure de publication.
     */
    public Reportage(String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(titre, listMentionPersonne, listMentionOrganisation, "reportage", datePublication);
    }

    /**
     * Constructeur de la classe {@code Reportage} pour une publication mentionnant uniquement des personnes, sans organisations ni médias.
     *
     * @param titre                Le titre de la publication.
     * @param listMentionPersonne  La liste des personnes mentionnées dans la publication.
     * @param datePublication      La date et l'heure de publication.
     */
    public Reportage(String titre, List<Personnalite> listMentionPersonne, LocalDateTime datePublication){
        super(titre, listMentionPersonne, "reportage", datePublication);
    }

    /**
     * Constructeur de la classe {@code Reportage} pour une publication mentionnant uniquement des organisations, sans personnes ni médias.
     *
     * @param listMentionOrganisation La liste des organisations mentionnées dans la publication.
     * @param titre                Le titre de la publication.
     * @param datePublication      La date et l'heure de publication.
     */
    public Reportage(List<Organisation> listMentionOrganisation, String titre, LocalDateTime datePublication){
        super(listMentionOrganisation, titre, "reportage", datePublication);
    }

    /**
     * Constructeur de la classe {@code Reportage} pour une publication sans mention de personnes ni d'organisations, mais avec un titre et une date.
     *
     * @param titre                Le titre de la publication.
     * @param datePublication      La date et l'heure de publication.
     */
    public Reportage(String titre, LocalDateTime datePublication){
        super(titre, "reportage", datePublication);
    }

    /**
     * Constructeur de la classe {@code Reportage} pour une publication mentionnant uniquement des médias, sans personnes ni organisations.
     * Ce constructeur a été ajouté pour éviter des erreurs de compilation.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans la publication.
     * @param titre                Le titre de la publication.
     * @param typePublication      Le type de la publication, qui sera "reportage".
     * @param datePublication      La date et l'heure de publication.
     */
    public Reportage(List<Media> listMentionMedia, String titre, String typePublication, LocalDateTime datePublication){
        super(titre, "reportage", listMentionMedia, datePublication);
    }

    /**
     * Constructeur de la classe {@code Reportage} pour une publication mentionnant des médias et des organisations, sans personnes.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans la publication.
     * @param titre                Le titre de la publication.
     * @param typePublication      Le type de la publication, qui sera "reportage".
     * @param listOrganisation     La liste des organisations mentionnées dans la publication.
     * @param datePublication      La date et l'heure de publication.
     */
    public Reportage(List<Media> listMentionMedia, String titre, String typePublication, List<Organisation> listOrganisation, LocalDateTime datePublication){
        super(listMentionMedia, titre, listOrganisation, "reportage", datePublication);
    }

    /**
     * Constructeur de la classe {@code Reportage} pour une publication mentionnant des médias et des personnes, sans organisations.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans la publication.
     * @param titre                Le titre de la publication.
     * @param listMentionPersonne  La liste des personnes mentionnées dans la publication.
     * @param typePublication      Le type de la publication, qui sera "reportage".
     * @param datePublication      La date et l'heure de publication.
     */
    public Reportage(List<Media> listMentionMedia, String titre, List<Personnalite> listMentionPersonne, String typePublication, LocalDateTime datePublication){
        super(listMentionMedia, titre, "reportage", listMentionPersonne, datePublication);
    }

    /**
     * Constructeur de la classe {@code Reportage} pour une publication mentionnant des médias, des personnes et des organisations.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans la publication.
     * @param titre                Le titre de la publication.
     * @param listMentionPersonne  La liste des personnes mentionnées dans la publication.
     * @param listMentionOrganisation La liste des organisations mentionnées dans la publication.
     * @param datePublication      La date et l'heure de publication.
     */
    public Reportage(List<Media> listMentionMedia, String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(listMentionMedia, titre, "reportage", listMentionPersonne, listMentionOrganisation, datePublication);
    }
}
