import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * La classe TSVParser permet de parser plusieurs fichiers TSV (Tab-Separated Values)
 * afin de créer des objets
 */

public class TSVParser {

    /**
     * Constructeur par défaut.
     */
    public TSVParser(){
    }


    /**
     * Parse le fichier medias.tsv pour générer une liste d'objets {@link Media}.
     * Gère les cas où certaines colonnes sont manquantes ou mal remplies.
     *
     * @return une liste d'objets {@link Media}.
     */
    public List<Media> mediaParser(){
        //remplacer par l'adresse où vous avez enregistrer le projet
        String pathMedia = "C:\\Users\\germa\\OneDrive\\Documents\\Travail\\ET3\\S6\\Java\\projetJava\\data\\medias.tsv";

        // Liste pour stocker les colonnes
        List<String> ListNom = new ArrayList<>();
        List<String> ListType = new ArrayList<>();
        List<String> ListPeriodicite = new ArrayList<>();
        List<String> ListEchelle = new ArrayList<>();
        List<String> ListPrix = new ArrayList<>();
        List<String> ListDisparu = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathMedia))) {
            String ligne;
            int compteur = 1;
            while ((ligne = br.readLine()) != null && compteur < 220) {
                String[] colonnesMedia = ligne.split("\t");

                // Déclarez des tableaux temporaires pour chaque type de colonne
                String[] colonneNom = new String[1];
                String[] colonneType = new String[1];
                String[] colonnePeriodicite = new String[1];
                String[] colonneEchelle = new String[1];
                String[] colonnePrix = new String[1];
                String[] colonneDisparu = new String[1];

                // Assurez-vous qu'il y a au moins 6 colonnes
                if (colonnesMedia.length < 6) {
                    // Ajouter des valeurs par défaut pour les colonnes manquantes
                    String[] colonnesMediaAjustees = new String[6];
                    System.arraycopy(colonnesMedia, 0, colonnesMediaAjustees, 0, colonnesMedia.length);
                    for (int i = colonnesMedia.length; i < 6; i++) {
                        colonnesMediaAjustees[i] = "N/A";
                    }
                    colonnesMedia = colonnesMediaAjustees;
                }



                //valeurs pour les tableaux temporaires
                colonneNom[0] = colonnesMedia[0];
                colonneType[0] = colonnesMedia[1];
                if(!colonnesMedia[1].isEmpty()){
                    if (!colonnesMedia[1].equals("Télévision") && !colonnesMedia[1].equals("Site") && !colonnesMedia[1].equals("Radio")){
                        colonneType[0] = "Presse (généraliste politique économique)";
                    }
                }
                colonnePeriodicite[0] = colonnesMedia[2];
                colonneEchelle[0] = colonnesMedia[3];
                colonnePrix[0] = colonnesMedia[4];
                colonneDisparu[0] = colonnesMedia[5];

                // Ajoutez les valeurs aux listes
                ListNom.add(colonneNom[0]);
                ListType.add(colonneType[0]);
                ListPeriodicite.add(colonnePeriodicite[0]);
                ListEchelle.add(colonneEchelle[0]);
                ListPrix.add(colonnePrix[0]);
                ListDisparu.add(colonneDisparu[0]);

                compteur++;
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            e.printStackTrace();
        }

        List<Media> listMedia = new ArrayList<>();

        for (int i = 1; i < ListNom.size(); i++) {
            Media media = new Media(ListNom.get(i), ListType.get(i), ListPeriodicite.get(i), ListEchelle.get(i), ListPrix.get(i), ListDisparu.get(i));

            listMedia.add(media);
        }
        return listMedia;
    }

    /**
     * Parse les fichiers organisations.tsv, organisation-media.tsv et organisation-organisation.tsv.
     *
     * Cette méthode crée les objets {@link Organisation} à partir des données,
     * puis établit les relations de possession entre organisations et médias,
     * ainsi que les relations hiérarchiques entre les organisations.
     *
     * Si une organisation n'existe pas encore dans les listes passées,
     * elle est ajouté·e.
     * Même chose pour les médias.
     *
     * @param listMedia la liste des médias existants, utilisée pour associer les relations.
     * @return une liste complète d'objets {@link Organisation}, avec leurs liens établis.
     */
    public List<Organisation> organisationParser(List<Media> listMedia){

        //remplacer par l'adresse où vous avez enregistrer le projet
        String pathOrganisation = "C:\\Users\\germa\\OneDrive\\Documents\\Travail\\ET3\\S6\\Java\\projetJava\\data\\organisations.tsv";

        List<String> ListNomOrganisation = new ArrayList<>();
        List<String> ListCommentaire = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathOrganisation))) {
            String ligne;
            int compteur = 1;
            while ((ligne = br.readLine()) != null && compteur < 80) {
                String[] colonnesOrganisation = ligne.split("\t");

                String[] colonneNom = new String[1];
                String[] colonneCommentaire = new String[1];


                if (colonnesOrganisation.length < 2) {
                    String[] colonnesOrganisationAjustees = new String[2];
                    System.arraycopy(colonnesOrganisation, 0, colonnesOrganisationAjustees, 0, colonnesOrganisation.length);
                    for (int i = colonnesOrganisation.length; i < 2; i++) {
                        colonnesOrganisationAjustees[i] = "Pas de commentaire specifier";
                    }
                    colonnesOrganisation = colonnesOrganisationAjustees;
                }



                colonneNom[0] = colonnesOrganisation[0];
                colonneCommentaire[0] = colonnesOrganisation[1];

                ListNomOrganisation.add(colonneNom[0]);
                ListCommentaire.add(colonneCommentaire[0]);

                compteur++;
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            e.printStackTrace();
        }


        List<Organisation> listOrganisation = new ArrayList<>();

        for (int i = 1; i < ListNomOrganisation.size(); i++) {
            Organisation organisation = new Organisation(ListNomOrganisation.get(i), ListCommentaire.get(i));
            listOrganisation.add(organisation);
        }

        //remplacer par l'adresse où vous avez enregistrer le projet
        String pathPersonneOrganisation = "C:\\Users\\germa\\OneDrive\\Documents\\Travail\\ET3\\S6\\Java\\projetJava\\data\\organisation-media.tsv";

        List<String> origineOrganisationMedia = new ArrayList<>();
        List<String> qualificatifOrganisationMedia = new ArrayList<>();
        List<String> valeurOrganisationMedia = new ArrayList<>();
        List<String> cibleOrganisationMedia = new ArrayList<>();

        //Parsing
        try (BufferedReader br = new BufferedReader(new FileReader(pathPersonneOrganisation))) {
            String lignePersonneMedia;
            int compteurPersonneMedia = 1;
            while ((lignePersonneMedia = br.readLine()) != null && compteurPersonneMedia < 228) {
                // Division de la ligne en colonnes en utilisant le caractère de tabulation
                String[] colonnesPersonneMedia = lignePersonneMedia.split("\t");
                if (colonnesPersonneMedia.length > 4) {
                    // Ajout de la première colonne à la liste
                    origineOrganisationMedia.add(colonnesPersonneMedia[1]);
                    qualificatifOrganisationMedia.add(colonnesPersonneMedia[2]);
                    valeurOrganisationMedia.add(colonnesPersonneMedia[3]);
                    cibleOrganisationMedia.add(colonnesPersonneMedia[4]);
                }
                compteurPersonneMedia++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int l, m;
        for (int i = 1; i < origineOrganisationMedia.size(); i++) {
            l = 0;
            // Trouver l'organisation dans listOrganisation
            while (l < listOrganisation.size() && !listOrganisation.get(l).getNomOrganisation().equals(origineOrganisationMedia.get(i))) {
                l++;
            }

            m = 0;
            // Trouver le media dans listMedia
            while (m < (listMedia.size() - 1) && !listMedia.get(m).getNomMedia().equals(cibleOrganisationMedia.get(i))) {
                m++;
            }

            // Si le media n'est pas présent dans listMedia, l'ajouter
            if (!listMedia.get(m).getNomMedia().equals(cibleOrganisationMedia.get(i))) {
                Media newMedia = new Media(cibleOrganisationMedia.get(i));
                Organisation organisation = new Organisation(origineOrganisationMedia.get(i));
                organisation.setQualificatifMedia(qualificatifOrganisationMedia.get(i));
                Float pourcentage = organisation.conversionPourcentage(valeurOrganisationMedia.get(i));
                organisation.setPossedeMedia(newMedia, pourcentage);
                listOrganisation.add(organisation);
                listMedia.add(newMedia);

            }
            else {
                // Mettre à jour l'organisation existante
                listOrganisation.get(l).setQualificatifMedia(qualificatifOrganisationMedia.get(i));
                Float pourcentage = listOrganisation.get(l).conversionPourcentage(valeurOrganisationMedia.get(i));
                listOrganisation.get(l).setPossedeMedia(listMedia.get(m), pourcentage);
            }
        }

        //Parsing pour implementer les instances de possedeOrganisation
        //remplacer par l'adresse où vous avez enregistrer le projet
        String pathOrganisationOrganisation = "C:\\Users\\germa\\OneDrive\\Documents\\Travail\\ET3\\S6\\Java\\projetJava\\data\\organisation-organisation.tsv";

        // Liste pour stocker chaques colonnes
        List<String> origineOrganisationOrganisation = new ArrayList<>();
        List<String> qualificatifOrganisationOrganisation = new ArrayList<>();
        List<String> valeurOrganisationOrganisation = new ArrayList<>();
        List<String> cibleOrganisationOrganisation = new ArrayList<>();

        //Parsing
        try (BufferedReader br = new BufferedReader(new FileReader(pathOrganisationOrganisation))) {
            String ligne;
            int compteurOrganisationOrganisation = 1;
            while ((ligne = br.readLine()) != null && compteurOrganisationOrganisation < 80) {
                // Division de la ligne en colonnes en utilisant le caractère de tabulation
                String[] colonnes2 = ligne.split("\t");
                if (colonnes2.length > 4) {
                    // Ajout de la première colonne à la liste
                    origineOrganisationOrganisation.add(colonnes2[1]);
                    qualificatifOrganisationOrganisation.add(colonnes2[2]);
                    valeurOrganisationOrganisation.add(colonnes2[3]);
                    cibleOrganisationOrganisation.add(colonnes2[4]);
                }
                compteurOrganisationOrganisation++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Implementation des instances
        int j,k;
        for(int i = 1; i < 44;i++){
            j = 0;
            while (!listOrganisation.get(j).getNomOrganisation().equals(origineOrganisationOrganisation.get(i))){
                j++;
            }
            k = 0;
            while (!listOrganisation.get(k).getNomOrganisation().equals(cibleOrganisationOrganisation.get(i))){
                k++;
            }

            // Implementation de la map possedeOrganisation pour chaque instance de la classe Personnalite
            listOrganisation.get(j).setQualificatifOrganisation(qualificatifOrganisationOrganisation.get(i));
            Float pourcentage = listOrganisation.get(j).conversionPourcentage(valeurOrganisationOrganisation.get(i));
            listOrganisation.get(j).setPossedeOrganisation(listOrganisation.get(k),pourcentage);
        }

        return listOrganisation;
    }



    /**
     * Parse les fichiers personnalites.tsv, personnalite-organisation.tsv et personnalite-media.tsv.
     *
     * Cette méthode permet de créer des objets {@link Personnalite} et d'établir si
     * elles possèdent des médias ou des organisations.
     *
     *
     * @param listMedia        la liste des médias utilisés pour créer les liens.
     * @param listOrganisation la liste des organisations utilisées pour créer les liens.
     * @return une liste d'objets {@link Personnalite} avec leurs relations renseignées.
     */
    public List<Personnalite> personnalitesOrganisationParser(List<Media> listMedia,List<Organisation> listOrganisation){
        // Chemin vers le fichier TSV
        //remplacer par l'adresse où vous avez enregistrer le projet
        String path = "C:\\Users\\germa\\OneDrive\\Documents\\Travail\\ET3\\S6\\Java\\projetJava\\data\\personnes.tsv";

        // Liste pour stocker la première colonne
        List<String> premiereColonnePersonnalite = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String ligne;
            int compteur = 1;
            while ((ligne = br.readLine()) != null && compteur < 40) {
                // Division de la ligne en colonnes en utilisant le caractère de tabulation
                String[] colonnes = ligne.split("\t");
                if (colonnes.length > 0) {
                    // Ajout de la première colonne à la liste
                    premiereColonnePersonnalite.add(colonnes[0]);
                }
                compteur++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<Personnalite> listPersonnalites = new ArrayList<>();


        for (int i = 1; i < premiereColonnePersonnalite.size(); i++) {
            Personnalite personne = new Personnalite(premiereColonnePersonnalite.get(i));
            listPersonnalites.add(personne);
        }




        //Parsing pour implementer les instances de possedeOrganisation pour chaque personnalite
        //remplacer par l'adresse où vous avez enregistrer le projet
        String path2 = "C:\\Users\\germa\\OneDrive\\Documents\\Travail\\ET3\\S6\\Java\\projetJava\\data\\personne-organisation.tsv";

        // Liste pour stocker chaques colonnes
        List<String> origine = new ArrayList<>();
        List<String> qualificatif = new ArrayList<>();
        List<String> valeur = new ArrayList<>();
        List<String> cible = new ArrayList<>();

        //Parsing
        try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
            String ligne;
            int compteur2 = 1;
            while ((ligne = br.readLine()) != null && compteur2 < 43) {
                // Division de la ligne en colonnes en utilisant le caractère de tabulation
                String[] colonnes2 = ligne.split("\t");
                if (colonnes2.length > 4) {
                    // Ajout de la première colonne à la liste
                    origine.add(colonnes2[1]);
                    qualificatif.add(colonnes2[2]);
                    valeur.add(colonnes2[3]);
                    cible.add(colonnes2[4]);
                }
                compteur2++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Implementation des instances
        int j,k;
        for(int i = 1; i < 40;i++){
            j = 0;
            while (!listPersonnalites.get(j).getNomPersonnalite().equals(origine.get(i))){
                j++;
            }
            k = 0;
            while (!listOrganisation.get(k).getNomOrganisation().equals(cible.get(i))){
                k++;
            }

            // Implementation de la map possedeOrganisation pour chaque instance de la classe Personnalite
            listPersonnalites.get(j).setQualificatifOrganisation(qualificatif.get(i));
            Float pourcentage = listPersonnalites.get(j).conversionPourcentage(valeur.get(i));
            listPersonnalites.get(j).setPossedeOrganisation(listOrganisation.get(k),pourcentage);
        }




        //Parsing pour implementer les instances de possedeMedia pour chaque personnalite
        //remplacer par l'adresse où vous avez enregistrer le projet
        String pathPersonneMedia = "C:\\Users\\germa\\OneDrive\\Documents\\Travail\\ET3\\S6\\Java\\projetJava\\data\\personne-media.tsv";

        // Liste pour stocker chaques colonnes
        List<String> originePersonneMedia = new ArrayList<>();
        List<String> qualificatifPersonneMedia = new ArrayList<>();
        List<String> valeurPersonneMedia = new ArrayList<>();
        List<String> ciblePersonneMedia = new ArrayList<>();

        //Parsing
        try (BufferedReader br = new BufferedReader(new FileReader(pathPersonneMedia))) {
            String lignePersonneMedia;
            int compteurPersonneMedia = 1;
            while ((lignePersonneMedia = br.readLine()) != null && compteurPersonneMedia < 43) {
                // Division de la ligne en colonnes en utilisant le caractère de tabulation
                String[] colonnesPersonneMedia = lignePersonneMedia.split("\t");
                if (colonnesPersonneMedia.length > 4) {
                    // Ajout de la première colonne à la liste
                    originePersonneMedia.add(colonnesPersonneMedia[1]);
                    qualificatifPersonneMedia.add(colonnesPersonneMedia[2]);
                    valeurPersonneMedia.add(colonnesPersonneMedia[3]);
                    ciblePersonneMedia.add(colonnesPersonneMedia[4]);
                }
                compteurPersonneMedia++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int l,m;
        for(int i = 1; i < originePersonneMedia.size();i++){
            l = 0;
            while (!listPersonnalites.get(l).getNomPersonnalite().equals(originePersonneMedia.get(i)) && l < listPersonnalites.size()){
                l++;
            }
            m = 0;
            while (!listMedia.get(m).getNomMedia().equals(ciblePersonneMedia.get(i)) && m < listMedia.size()){
                m++;
            }

            // Implementation de la map possedeMedia pour chaque instance de la classe Personnalite
            listPersonnalites.get(l).setQualificatifMedia(qualificatifPersonneMedia.get(i));
            Float pourcentage = listPersonnalites.get(l).conversionPourcentage(valeurPersonneMedia.get(i));
            listPersonnalites.get(l).setPossedeMedia(listMedia.get(m),pourcentage);
        }
        return listPersonnalites;
    }
}

