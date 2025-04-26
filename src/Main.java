import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        TSVParser parser = new TSVParser();
        List<Media> listMedia = parser.mediaParser();
        List<Organisation> listOrganisation = parser.organisationParser(listMedia);
        List<Personnalite> listPersonnalite = parser.personnalitesOrganisationParser(listMedia,listOrganisation);




       InteractionUtilisateur runner = new InteractionUtilisateur(listMedia,listPersonnalite,listOrganisation);
       runner.run();
    }
}


