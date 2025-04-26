import java.time.LocalDateTime;
import java.util.List;

public class Reportage extends Publication{

    //Constructeur quand il y a des personnes et des organisations mentionnees mais pas de medias
    public Reportage(String titre, List<Personnalite> listMentionPersonne, List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(titre,listMentionPersonne,listMentionOrganisation,"reportage",datePublication);
    }


    //Constructeur quand il y a des personnes mentionnees mais pas des organisations et pas de media
    public Reportage(String titre, List<Personnalite> listMentionPersonne,LocalDateTime datePublication){
        super(titre,listMentionPersonne,"reportage",datePublication);
    }

    //Constructeur quand il y a des organisations mentionnees mais pas des organisations et pas de media
    public Reportage(List<Organisation> listMentionOrganisation, String titre, LocalDateTime datePublication){
        super(listMentionOrganisation,titre,"reportage",datePublication);
    }

    //Constructeur quand il n'y a ni organisations mentionnees ni personnes mentionnees mais pas de media
    public Reportage(String titre, LocalDateTime datePublication){
        super(titre,"reportage",datePublication);
    }

    //Constructeur quand il y a des medias mentionnees mais pas des organisations et pas de personnes
    //Dire dans le compte rendu que j'ai du faire ce trick pour ne  pas avoir d'erreur à la compilation
    public Reportage(List<Media> listMentionMedia, String titre,String typePublication, LocalDateTime datePublication){
        super(titre,"reportage",listMentionMedia,datePublication);
    }

    //Constructeur quand il y a des medias mentionnees et des organisations mais pas de personnes
    public Reportage(List<Media> listMentionMedia, String titre,String typePublication,List<Organisation> listOrganisation, LocalDateTime datePublication){
        super(listMentionMedia,titre,listOrganisation,"reportage",datePublication);
    }

    //Constructeur quand il y a des medias mentionnees et des personnes mais pas d'organisations
    public Reportage(List<Media> listMentionMedia, String titre,List<Personnalite> listMentionPersonne,String typePublication, LocalDateTime datePublication){
        super(listMentionMedia,titre,"reportage",listMentionPersonne,datePublication);
    }

    //Constructeur quand y a les 3
    public Reportage(List<Media> listMentionMedia, String titre,List<Personnalite> listMentionPersonne,List<Organisation> listMentionOrganisation, LocalDateTime datePublication){
        super(listMentionMedia,titre,"reportage",listMentionPersonne,listMentionOrganisation,datePublication);
    }

}
