import java.time.LocalDateTime;
import java.util.List;

/**
 * La classe {@code Article} représente une publication de type article.
 * Elle hérite de la classe {@code Publication} et permet de gérer les informations
 * spécifiques à un article, notamment les personnes, organisations et médias mentionnés.
 */
public class Article extends Publication {

    /**
     * Constructeur de la classe {@code Article} pour une publication mentionnant des personnes et des organisations.
     *
     * @param titre                Le titre de l'article.
     * @param listMentionPersonne  La liste des personnes mentionnées dans l'article.
     * @param listMentionOrganisation La liste des organisations mentionnées dans l'article.
     * @param datePublication      La date et l'heure de publication de l'article.
     */
    public Article(String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(titre, listMentionPersonne, listMentionOrganisation, "article", datePublication);
    }

    /**
     * Constructeur de la classe {@code Article} pour une publication mentionnant uniquement des personnes, sans organisations.
     *
     * @param titre                Le titre de l'article.
     * @param listMentionPersonne  La liste des personnes mentionnées dans l'article.
     * @param datePublication      La date et l'heure de publication de l'article.
     */
    public Article(String titre, List<Personnalite> listMentionPersonne, LocalDateTime datePublication){
        super(titre, listMentionPersonne, "article", datePublication);
    }

    /**
     * Constructeur de la classe {@code Article} pour une publication mentionnant uniquement des organisations, sans personnes.
     *
     * @param listMentionOrganisation La liste des organisations mentionnées dans l'article.
     * @param titre                Le titre de l'article.
     * @param datePublication      La date et l'heure de publication de l'article.
     */
    public Article(List<Organisation> listMentionOrganisation, String titre , LocalDateTime datePublication){
        super(listMentionOrganisation, titre, "article", datePublication);
    }

    /**
     * Constructeur de la classe {@code Article} pour une publication sans mention de personnes ni d'organisations, mais avec un titre et une date.
     *
     * @param titre                Le titre de l'article.
     * @param datePublication      La date et l'heure de publication de l'article.
     */
    public Article(String titre, LocalDateTime datePublication){
        super(titre, "article", datePublication);
    }

    /**
     * Constructeur de la classe {@code Article} pour une publication mentionnant uniquement des médias, sans personnes ni organisations.
     * Ce constructeur a été ajouté pour éviter des erreurs de compilation.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans l'article.
     * @param titre                Le titre de l'article.
     * @param typePublication      Le type de la publication, qui sera "article".
     * @param datePublication      La date et l'heure de publication de l'article.
     */
    public Article(List<Media> listMentionMedia, String titre, String typePublication, LocalDateTime datePublication){
        super(titre, "article", listMentionMedia, datePublication);
    }

    /**
     * Constructeur de la classe {@code Article} pour une publication mentionnant des médias et des organisations, sans personnes.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans l'article.
     * @param titre                Le titre de l'article.
     * @param typePublication      Le type de la publication, qui sera "article".
     * @param listOrganisation     La liste des organisations mentionnées dans l'article.
     * @param datePublication      La date et l'heure de publication de l'article.
     */
    public Article(List<Media> listMentionMedia, String titre, String typePublication, List<Organisation> listOrganisation, LocalDateTime datePublication){
        super(listMentionMedia, titre, listOrganisation, "article", datePublication);
    }

    /**
     * Constructeur de la classe {@code Article} pour une publication mentionnant des médias et des personnes, sans organisations.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans l'article.
     * @param titre                Le titre de l'article.
     * @param listMentionPersonne  La liste des personnes mentionnées dans l'article.
     * @param typePublication      Le type de la publication, qui sera "article".
     * @param datePublication      La date et l'heure de publication de l'article.
     */
    public Article(List<Media> listMentionMedia, String titre, List<Personnalite> listMentionPersonne, String typePublication, LocalDateTime datePublication){
        super(listMentionMedia, titre, "article", listMentionPersonne, datePublication);
    }

    /**
     * Constructeur de la classe {@code Article} pour une publication mentionnant des médias, des personnes et des organisations.
     *
     * @param listMentionMedia     La liste des médias mentionnés dans l'article.
     * @param titre                Le titre de l'article.
     * @param listMentionPersonne  La liste des personnes mentionnées dans l'article.
     * @param listMentionOrganisation La liste des organisations mentionnées dans l'article.
     * @param datePublication      La date et l'heure de publication de l'article.
     */
    public Article(List<Media> listMentionMedia, String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(listMentionMedia, titre, "article", listMentionPersonne, listMentionOrganisation, datePublication);
    }


}
