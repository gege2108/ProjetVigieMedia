import java.util.ArrayList;
import java.util.List;


/**
 * La classe {@code Main} gère le traitement des données en lisant des fichiers TSV et
 * en exécutant les interactions avec l'utilisateur.
 *
 * Elle instancie et initialise les objets nécessaires pour parser les données relatives aux médias, organisations et personnalités,
 * puis lance l'interaction utilisateur pour permettre à ce dernier de manipuler et visualiser ces informations.
 */

public class Main {
    /**
     * Méthode principale du programme. Elle est utilisée pour démarrer l'application, effectuer le parsing des données TSV,
     * puis exécuter les interactions avec l'utilisateur.
     *
     * @param args Arguments de ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args){

        // Création du parseur pour traiter les fichiers TSV
        TSVParser parser = new TSVParser();
        //Parsing des objets à partir des fichier TSV
        List<Media> listMedia = parser.mediaParser();
        List<Organisation> listOrganisation = parser.organisationParser(listMedia);
        List<Personnalite> listPersonnalite = parser.personnalitesOrganisationParser(listMedia,listOrganisation);

        // Initialisation de l'interaction avec l'utilisateur
        InteractionUtilisateur runner = new InteractionUtilisateur(listMedia,listPersonnalite,listOrganisation);
        // Lancement de l'interaction avec l'utilisateur
       runner.run();
    }
}


