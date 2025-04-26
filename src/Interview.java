import java.time.LocalDateTime;
import java.util.List;

public class Interview extends Publication{
    //Constructeur quand il y a des personnes et des organisations mentionnees
    public Interview(String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(titre,listMentionPersonne,listMentionOrganisation,"interview",datePublication);
    }


    //Constructeur quand il y a des personnes mentionnees mais pas des organisations
    public Interview(String titre, List<Personnalite> listMentionPersonne, LocalDateTime datePublication){
        super(titre,listMentionPersonne,"interview",datePublication);
    }

    //Constructeur quand il y a des organisations mentionnees mais pas des organisations
    public Interview(List<Organisation> listMentionOrganisation, String titre , LocalDateTime datePublication){
        super(listMentionOrganisation,titre,"interview",datePublication);
    }

    //Constructeur quand il n'y a ni organisations mentionnees ni personnes mentionnees
    public Interview(String titre, LocalDateTime datePublication){
        super(titre,"interview",datePublication);
    }

    //Constructeur quand il y a des medias mentionnees mais pas des organisations et pas de personnes
    //Dire dans le compte rendu que j'ai du faire ce trick pour ne  pas avoir d'erreur à la compilation
    public Interview(List<Media> listMentionMedia, String titre,String typePublication, LocalDateTime datePublication){
        super(titre,"interview",listMentionMedia,datePublication);
    }

    //Constructeur quand il y a des medias mentionnees et des organisations mais pas de personnes
    public Interview(List<Media> listMentionMedia, String titre,String typePublication,List<Organisation> listOrganisation, LocalDateTime datePublication){
        super(listMentionMedia,titre,listOrganisation,"interview",datePublication);
    }

    //Constructeur quand il y a des medias mentionnees et des personnes mais pas d'organisations
    public Interview(List<Media> listMentionMedia, String titre,List<Personnalite> listMentionPersonne,String typePublication, LocalDateTime datePublication){
        super(listMentionMedia,titre,"interview",listMentionPersonne,datePublication);
    }

    //Constructeur quand y a les 3
    public Interview(List<Media> listMentionMedia, String titre,List<Personnalite> listMentionPersonne,List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(listMentionMedia,titre,"interview",listMentionPersonne,listMentionOrganisation,datePublication);
    }
}
