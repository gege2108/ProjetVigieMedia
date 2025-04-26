import java.time.LocalDateTime;
import java.util.List;

public class Article extends Publication{

    //Constructeur quand il y a des personnes et des organisations mentionnees
    public Article(String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(titre,listMentionPersonne,listMentionOrganisation,"article",datePublication);
    }


    //Constructeur quand il y a des personnes mentionnees mais pas des organisations
    public Article(String titre, List<Personnalite> listMentionPersonne, LocalDateTime datePublication){
        super(titre,listMentionPersonne,"article",datePublication);
    }

    //Constructeur quand il y a des organisations mentionnees mais pas des personnes
    public Article(List<Organisation> listMentionOrganisation, String titre , LocalDateTime datePublication){
        super(listMentionOrganisation,titre,"article",datePublication);
    }

    //Constructeur quand il n'y a ni organisations mentionnees ni personnes mentionnees
    public Article(String titre, LocalDateTime datePublication){
        super(titre,"article",datePublication);
    }

    //Constructeur quand il y a des medias mentionnees mais pas des organisations et pas de personnes
    //Dire dans le compte rendu que j'ai du faire ce trick pour ne  pas avoir d'erreur à la compilation
    public Article(List<Media> listMentionMedia, String titre,String typePublication, LocalDateTime datePublication){
        super(titre,"article",listMentionMedia,datePublication);
    }

    //Constructeur quand il y a des medias mentionnees et des organisations mais pas de personnes
    public Article(List<Media> listMentionMedia, String titre,String typePublication,List<Organisation> listOrganisation, LocalDateTime datePublication){
        super(listMentionMedia,titre,listOrganisation,"article",datePublication);
    }

    //Constructeur quand il y a des medias mentionnees et des personnes mais pas d'organisations
    public Article(List<Media> listMentionMedia, String titre,List<Personnalite> listMentionPersonne,String typePublication, LocalDateTime datePublication){
        super(listMentionMedia,titre,"article",listMentionPersonne,datePublication);
    }

    //Constructeur quand y a les 3
    public Article(List<Media> listMentionMedia, String titre,List<Personnalite> listMentionPersonne,List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(listMentionMedia,titre,"article",listMentionPersonne,listMentionOrganisation,datePublication);
    }

    //TODO faire aussi un constructeur pour quand il y a des organisations mentionnees mais pas des personnes et les implementer dans les classes reportage et Interview
}
