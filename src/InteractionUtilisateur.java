import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class InteractionUtilisateur {
    private List<Media> listMedia;
    private List<Organisation> listOrganisation;
    private List<Personnalite> listPersonnalite;

    public InteractionUtilisateur(List<Media> listMedia, List<Personnalite> listPersonnalite, List<Organisation> listOrganisation) {
        this.listMedia = listMedia;
        this.listPersonnalite = listPersonnalite;
        this.listOrganisation = listOrganisation;
    }

    public int run() {
        Scanner scanner = new Scanner(System.in);
        String infoInstances = "";
        MAJ MiseAJour = new MAJ(listMedia,listOrganisation,listPersonnalite);
        Personnalite initBolore = null;
        Media initTMC = null;

        //initialisation du module de suivi de Vincent Bollore
        for (int i = 0; i < listPersonnalite.size(); i++) {
            if (listPersonnalite.get(i).getNomPersonnalite().equals("Vincent Bolloré")){
                initBolore = listPersonnalite.get(i);
            }
        }
        ModuleSuiviePersonne moduleSuivieVincentBollore = new ModuleSuiviePersonne(initBolore,listMedia);


        //initialisation du module de suivi de TMC
        for (int i = 0; i < listMedia.size() ; i++) {
            if (listMedia.get(i).getNomMedia().equals("TMC")){
                initTMC = listMedia.get(i);
            }
        }
        ModuleSuivieMedia moduleSuivieTMC = new ModuleSuivieMedia(initTMC);


        //pour la fin, il faudra le convertir en Float
        float newPourcentage;
        while (!infoInstances.equals("quitter")) {
            Collections.sort(listMedia);
            Collections.sort(listOrganisation);

            System.out.println("Tapez Info pour obtenir des informations au sein de la base de donnee, tapez Modification pour la modifier, tapez Suivi pour consulter les modules de suivie specialises \t");
            infoInstances = scanner.nextLine();
            //Cas où l'utilisateur veut des informations
            if (infoInstances.equals("Info")) {
                System.out.println("Media (taper Media), Organisation (taper Organisation) ou Personne (taper Personne) ?\t");
                infoInstances = scanner.nextLine();
                if (infoInstances.equals("Media")) {
                    System.out.println("Tapez Qui le possede pour savoir qui possede le media, tapez Publication pour obtenir des informations sur les publications du media, taper Info Generale pour obtenir des informations plus generale \t");
                    infoInstances = scanner.nextLine();
                    //inforamtions generales pour les medias
                    if (infoInstances.equals("Info Generale")) {
                        System.out.println("Voici la liste des noms des medias de la base de donnee");
                        for (int i = 0; i < listMedia.size(); i++) {
                            System.out.println(listMedia.get(i).getNomMedia());
                        }
                        System.out.println("A propos de quel media ?\t");
                        infoInstances = scanner.nextLine();
                        if (infoInstances.equals("Tous")) {
                            for (int i = 0; i < listMedia.size(); i++) {
                                System.out.println(listMedia);
                            }
                        } else {
                            int i = 0;
                            while (i < listMedia.size()) {
                                if (listMedia.get(i).getNomMedia().equals(infoInstances)) {
                                    System.out.println(listMedia.get(i));
                                    break;
                                }
                                i++;
                            }
                            if (i == listMedia.size()) {
                                System.out.println("Le media n'a pas ete trouve");
                            }
                        }
                    } else if (infoInstances.equals("Qui le possede")) {
                        System.out.println("Voulez-vous savoir quelle personne (taper Personne) possède le media ou quelle organisation possede le media (taper Organisation) ?\t");
                        infoInstances = scanner.nextLine();
                        //L'utilisateur veut des info sur quelle personnalite possede quel media
                        if (infoInstances.equals("Personne")) {
                            System.out.println("Voici la liste des noms des medias de la base de donnee");
                            for (int i = 0; i < listMedia.size(); i++) {
                                System.out.println(listMedia.get(i).getNomMedia());
                            }
                            System.out.println("Quel media ?\t");
                            infoInstances = scanner.nextLine();

                            //recherche du media pour voir s'il existe
                            Media tempMedia = null;
                            boolean trouve = false;

                            for (int i = 0; i < listMedia.size(); i++) {
                                if (listMedia.get(i).getNomMedia().equals(infoInstances)) {
                                    tempMedia = listMedia.get(i);
                                    trouve = true;
                                }
                            }

                            if (!trouve) {
                                System.out.println("Le media n'a pas ete trouve");
                            } else {


                                trouve = false;

                                for (Personnalite personnalite : listPersonnalite) {
                                    for (Map.Entry<Organisation, Float> entry : personnalite.getPossedeOrganisation().entrySet()) {
                                        if (entry.getKey().getPossedeMedia().containsKey(tempMedia)) {
                                            System.out.println(personnalite.getNomPersonnalite() + " possede des organisations possedant "  + tempMedia.getNomMedia() + " : ");
                                            System.out.println("L'organisation " + entry.getKey().afficheMediaPossede(tempMedia));
                                            if (!trouve){
                                                trouve = true;
                                            }
                                        }


                                    }
                                }

                                if (trouve){
                                    System.out.println("\n");
                                }


                                int j = 0;

                                while (j < listPersonnalite.size()) {
                                    if (listPersonnalite.get(j).getPossedeMedia().containsKey(tempMedia)) {

                                        System.out.println(listPersonnalite.get(j).afficheMediaPossede(tempMedia));
                                        trouve = true;
                                    }
                                    j++;
                                }

                                if (!trouve) {
                                    System.out.println("Aucune Personne ne possede ce media");
                                }
                            }
                        } else if (infoInstances.equals("Organisation")) {
                            System.out.println("Voici la liste des noms des medias de la base de donnee");
                            for (int i = 0; i < listMedia.size(); i++) {
                                System.out.println(listMedia.get(i).getNomMedia());
                            }
                            System.out.println("Quel media ?\t");
                            infoInstances = scanner.nextLine();

                            //recherche du media pour voir s'il existe
                            Media tempMedia = null;
                            boolean trouve = false;

                            for (int i = 0; i < listMedia.size(); i++) {
                                if (listMedia.get(i).getNomMedia().equals(infoInstances)) {
                                    tempMedia = listMedia.get(i);
                                    trouve = true;
                                }
                            }

                            if (!trouve) {
                                System.out.println("Le media n'a pas ete trouve");
                            }


                            else {

                                trouve = false;

                                for (Organisation organisation : listOrganisation) {
                                    for (Map.Entry<Organisation, Float> entry : organisation.getPossedeOrganisation().entrySet()) {
                                        if (entry.getKey().getPossedeMedia().containsKey(tempMedia)) {
                                            System.out.println(organisation.getNomOrganisation() + " possede des organisations possedant "  + tempMedia.getNomMedia() + " : ");
                                            if (!trouve){
                                                trouve = true;
                                            }
                                        }


                                    }
                                }

                                int j = 0;

                                while (j < listOrganisation.size()) {
                                    if (listOrganisation.get(j).getPossedeMedia().containsKey(tempMedia)) {
                                        System.out.println(listOrganisation.get(j).afficheMediaPossede(tempMedia));
                                        trouve = true;
                                    }

                                    j++;
                                }

                                if (!trouve) {
                                    System.out.println("Aucune Organisation ne possede ce media");
                                }
                            }
                        }
                    }


                    else if(infoInstances.equals("Publication")){
                        System.out.println("Voici la liste des medias");
                        for (int i = 0; i < listMedia.size(); i++) {
                            System.out.println(listMedia.get(i).getNomMedia());
                        }
                        System.out.println("Choisissez le media pour lequel vous voulez connaître les publications : ");
                        infoInstances = scanner.nextLine();
                        Media mediaConnaitrePublication = null;
                        for (int i = 0; i < listMedia.size() ; i++) {
                            if (listMedia.get(i).getNomMedia().equals(infoInstances)){
                                mediaConnaitrePublication = listMedia.get(i);
                            }
                        }
                        if (mediaConnaitrePublication == null){
                            System.out.println("Le media " + infoInstances + " n'est pas dans la base de donnee");
                        }
                        else {
                            mediaConnaitrePublication.afficheListPublication();
                        }
                    }
                }



                //L'utilisateur veut des inforamations sur les organisations
                else if (infoInstances.equals("Organisation")) {

                    System.out.println("Quel information voulez-vous avoir (qui la possède (taper Qui la possede), quel media possede-t'elle (taper Possede quel media), quelle organisation possede-t'elle (taper Possede quelle organisation) ou des informations plus generale(taper Info Generale) ?\t");
                    infoInstances = scanner.nextLine();
                    //inforamtions generales pour les medias
                    if (infoInstances.equals("Info Generale")) {
                        System.out.println("Voici la liste des noms des organisations de la base de donnee");
                        for (int i = 0; i < listOrganisation.size(); i++) {
                            System.out.println(listOrganisation.get(i).getNomOrganisation());
                        }
                        System.out.println("A propos de quelle organisation ?\t");
                        infoInstances = scanner.nextLine();
                        if (infoInstances.equals("Tous")) {
                            //changer
                            for (int i = 0; i < listOrganisation.size(); i++) {
                                System.out.println(listOrganisation);
                            }
                        } else {
                            int i = 0;
                            while (i < listOrganisation.size()) {
                                if (listOrganisation.get(i).getNomOrganisation().equals(infoInstances)) {
                                    System.out.println(listOrganisation.get(i));
                                    break;
                                }
                                i++;
                            }
                            if (i == listOrganisation.size()) {
                                System.out.println("L'organisation n'a pas ete trouve");
                            }
                        }
                    } else if (infoInstances.equals("Qui la possede")) {
                        System.out.println("Voulez-vous savoir quelle personne (taper Personne) possède l'organisation ou quelle organisation possede l'organisation (taper Organisation) ?\t");
                        infoInstances = scanner.nextLine();
                        //L'utilisateur veut des info sur quelle personnalite possede quelle organisation
                        if (infoInstances.equals("Personne")) {
                            System.out.println("Voici la liste des noms des organisations de la base de donnee");
                            for (int i = 0; i < listOrganisation.size(); i++) {
                                System.out.println(listOrganisation.get(i).getNomOrganisation());
                            }
                            System.out.println("Quelle organisation ?\t");
                            infoInstances = scanner.nextLine();

                            //recherche de l'organisation pour voir si elle existe
                            Organisation tempOrganisation = null;
                            boolean trouve = false;

                            for (int i = 0; i < listOrganisation.size(); i++) {
                                if (listOrganisation.get(i).getNomOrganisation().equals(infoInstances)) {
                                    tempOrganisation = listOrganisation.get(i);
                                    trouve = true;
                                }
                            }

                            if (!trouve) {
                                System.out.println("L'organisation n'a pas ete trouvee");
                            } else {



                                Personnalite tempPersonnalite = null;
                                trouve = false;
                                for (Personnalite personnalite : listPersonnalite) {
                                    for (Map.Entry<Organisation, Float> entry : personnalite.getPossedeOrganisation().entrySet()) {
                                        if (entry.getKey().getPossedeOrganisation().containsKey(tempOrganisation)) {
                                            System.out.println(personnalite.getNomPersonnalite() + " possede des organisations possedant " + tempOrganisation.getNomOrganisation() + " : ");
                                            System.out.println("L'organisation " + entry.getKey().afficheOrganisationPossede(tempOrganisation));
                                            if (!trouve) {
                                                trouve = true;
                                            }
                                        }


                                    }
                                }

                                if (trouve) {
                                    System.out.println("\n");
                                }


                                int j = 0;
                                while (j < listPersonnalite.size()) {
                                    if (listPersonnalite.get(j).getPossedeOrganisation().containsKey(tempOrganisation)) {
                                        System.out.println(listPersonnalite.get(j).afficheOrganisationPossede(tempOrganisation));
                                        trouve = true;
                                    }
                                    j++;
                                }

                                if (!trouve) {
                                    System.out.println("Aucune Personne ne possede cette organisation");
                                }




                            }
                        } else if (infoInstances.equals("Organisation")) {
                            System.out.println("Voici la liste des noms des organisations de la base de donnee");
                            for (int i = 0; i < listOrganisation.size(); i++) {
                                System.out.println(listOrganisation.get(i).getNomOrganisation());
                            }
                            System.out.println("De quelle organisation voulez-vous connaître le proprietaire ?\t");
                            infoInstances = scanner.nextLine();

                            //recherche de l'organisation pour voir si elle existe
                            Organisation tempOrganisation = null;
                            boolean trouve = false;

                            for (int i = 0; i < listOrganisation.size(); i++) {
                                if (listOrganisation.get(i).getNomOrganisation().equals(infoInstances)) {
                                    tempOrganisation = listOrganisation.get(i);
                                    trouve = true;
                                }
                            }

                            if (!trouve) {
                                System.out.println("L'organisation n'a pas ete trouve");
                            } else {


                                for (Organisation organisation : listOrganisation) {
                                    for (Map.Entry<Organisation, Float> entry : organisation.getPossedeOrganisation().entrySet()) {
                                        if (entry.getKey().getPossedeOrganisation().containsKey(tempOrganisation)) {
                                            System.out.println(organisation.getNomOrganisation() + " possede des organisations possedant " + tempOrganisation.getNomOrganisation() + " : ");
                                            System.out.println("L'organisation " + entry.getKey().afficheOrganisationPossede(tempOrganisation));
                                            if (!trouve) {
                                                trouve = true;
                                            }
                                        }


                                    }
                                }

                                if (trouve) {
                                    System.out.println("\n");
                                }



                                int j = 0;
                                trouve = false;

                                while (j < listOrganisation.size()) {
                                    if (listOrganisation.get(j).getPossedeOrganisation().containsKey(tempOrganisation)) {
                                        System.out.println(listOrganisation.get(j).afficheOrganisationPossede(tempOrganisation));
                                        trouve = true;
                                    }

                                    j++;
                                }

                                if (!trouve) {
                                    System.out.println("Aucune Organisation ne possede ce media");
                                }
                            }
                        }
                    }else if (infoInstances.equals("Possede quel media")) {
                        System.out.println("Voici la liste des noms des organisations de la base de donnee");
                        for (int i = 0; i < listOrganisation.size(); i++) {
                            System.out.println(listOrganisation.get(i).getNomOrganisation());
                        }

                        System.out.println("Quelle organisation ?\t");
                        infoInstances = scanner.nextLine();

                        Organisation tempOrganisation = null;
                        boolean trouve = false;

                        for (int i = 0; i < listOrganisation.size(); i++) {
                            if (listOrganisation.get(i).getNomOrganisation().equals(infoInstances)) {
                                tempOrganisation = listOrganisation.get(i);
                                trouve = true;
                            }
                        }

                        if (!trouve) {
                            System.out.println("L'organisation n'a pas ete trouvee");
                        }

                        else{
                            System.out.println(tempOrganisation.afficheToutMediaPossede());
                        }
                    }

                    else if (infoInstances.equals("Possede quelle organisation")) {
                        System.out.println("Voici la liste des noms des organisations de la base de donnee");
                        for (int i = 0; i < listOrganisation.size(); i++) {
                            System.out.println(listOrganisation.get(i).getNomOrganisation());
                        }

                        System.out.println("Quelle organisation (qui possede d'autre(s) organisations) ?\t");
                        infoInstances = scanner.nextLine();

                        Organisation tempOrganisation = null;
                        boolean trouve = false;

                        for (int i = 0; i < listOrganisation.size(); i++) {
                            if (listOrganisation.get(i).getNomOrganisation().equals(infoInstances)) {
                                tempOrganisation = listOrganisation.get(i);
                                trouve = true;
                            }
                        }

                        if (!trouve) {
                            System.out.println("L'organisation n'a pas ete trouvee");
                        }

                        else{
                            System.out.println(tempOrganisation.afficheTouteOrganisationPossede());
                        }
                    }


                }


                //L'utilisateur veut des informations sur les personnes
                else if (infoInstances.equals("Personne")) {

                    System.out.println("Quel information voulez-vous avoir (quel media possede-t'elle (taper Possede quel media), quelle organisation possede-t'elle (taper Possede quelle organisation) ?\t");
                    infoInstances = scanner.nextLine();


                    if (infoInstances.equals("Possede quel media")) {
                        System.out.println("Voici la liste des noms des personnes de la base de donnee");
                        for (int i = 0; i < listPersonnalite.size(); i++) {
                            System.out.println(listPersonnalite.get(i).getNomPersonnalite());
                        }

                        System.out.println("Quelle personne ?\t");
                        infoInstances = scanner.nextLine();

                        Personnalite tempPersonnalite = null;
                        boolean trouve = false;

                        for (int i = 0; i < listPersonnalite.size(); i++) {
                            if (listPersonnalite.get(i).getNomPersonnalite().equals(infoInstances)) {
                                tempPersonnalite = listPersonnalite.get(i);
                                trouve = true;
                            }
                        }

                        if (!trouve) {
                            System.out.println("La personne n'a pas ete trouvee");
                        }

                        else{
                            System.out.println(tempPersonnalite.afficheToutMediaPossede());
                        }
                    }

                    else if (infoInstances.equals("Possede quelle organisation")) {
                        System.out.println("Voici la liste des noms des personnes de la base de donnee");
                        for (int i = 0; i < listPersonnalite.size(); i++) {
                            System.out.println(listPersonnalite.get(i).getNomPersonnalite());
                        }

                        System.out.println("Quelle personne ?\t");
                        infoInstances = scanner.nextLine();

                        Personnalite tempPersonnalite = null;
                        boolean trouve = false;

                        for (int i = 0; i < listPersonnalite.size(); i++) {
                            if (listPersonnalite.get(i).getNomPersonnalite().equals(infoInstances)) {
                                tempPersonnalite = listPersonnalite.get(i);
                                trouve = true;
                            }
                        }

                        if (!trouve) {
                            System.out.println("La personne n'a pas ete trouvee");
                        }

                        else{
                            System.out.println(tempPersonnalite.afficheTouteOrganisationPossede()   );
                        }
                    }
                }
            }



            else if(infoInstances.equals("Modification")){
                System.out.println("Entrez le mot de passe administrateur : ");
                infoInstances = scanner.nextLine();
                if(infoInstances.equals("polytechlover")){
                    System.out.println("Tapez Rachat pour mettre à jour les rachats, tapez Publier pour ajouter une publication à un media : ");
                    infoInstances = scanner.nextLine();
                    if (infoInstances.equals("Rachat")){
                        LocalDateTime dateRachatTMC = null;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        String acheteur,vendeur,vendu,pourcentage = "";
                        System.out.println("Tapez Personne si l'acheteur est une personne, tapez Organisation si l'acheteur est une organisation");
                        infoInstances = scanner.nextLine();

                        if (infoInstances.equals("Personne")){
                            System.out.println("Voici la liste des personnes : ");
                            for (int i = 0; i < listPersonnalite.size(); i++) {
                                System.out.println(listPersonnalite.get(i).getNomPersonnalite());
                            }

                            System.out.println("Ecrivez le nom de l'acheteur : ");
                            acheteur = scanner.nextLine();

                            System.out.println("Taper Personne si le vendeur est une personne, taper Organisation si le vendeur est une organisation ");
                            infoInstances = scanner.nextLine();

                            if (infoInstances.equals("Personne")){

                                System.out.println("Ecrivez le nom du vendeur : ");
                                vendeur = scanner.nextLine();

                                System.out.println("Est-ce le rachat d'un media (taper Media) ou d'une organisation (taper Organisation) : ");
                                infoInstances = scanner.nextLine();



                                if (infoInstances.equals("Media")){
                                    System.out.println("Voici la liste des medias");
                                    for (int i = 0; i < listMedia.size(); i++) {
                                        System.out.println(listMedia.get(i).getNomMedia());
                                    }
                                    System.out.println("Indiquez le media qui a ete rachete : ");
                                    vendu = scanner.nextLine();
                                    System.out.println("Indiquez le pourcentage du rachat : ");
                                    pourcentage = scanner.nextLine();
                                    float pourcentageFloat = Float.parseFloat(pourcentage);

                                    MiseAJour.rachatPersonnalitePersonnaliteMedia(acheteur,vendeur,vendu,pourcentageFloat);
                                    if (vendu.equals("TMC")){
                                        dateRachatTMC = LocalDateTime.now();
                                        moduleSuivieTMC.setHistoriqueRachat("La personne " + acheteur + " rachete " + pourcentageFloat + " % de TMC à la personne " + vendeur + " à la date " + dateRachatTMC.format(formatter) );
                                    }
                                }


                                else if(infoInstances.equals("Organisation")){
                                    System.out.println("Voici la liste des organisations");
                                    for (int i = 0; i < listOrganisation.size(); i++) {
                                        System.out.println(listOrganisation.get(i).getNomOrganisation());
                                    }
                                    System.out.println("Indiquez l'organisation qui a ete rachete : ");
                                    vendu = scanner.nextLine();
                                    System.out.println("Indiquez le pourcentage du rachat : ");
                                    pourcentage = scanner.nextLine();
                                    float pourcentageFloat = Float.parseFloat(pourcentage);

                                    MiseAJour.rachatPersonnalitePersonnaliteOrganisation(acheteur,vendeur,vendu,pourcentageFloat);
                                }
                            }



                            else if (infoInstances.equals("Organisation")){


                                System.out.println("Voici la liste des organisations : ");
                                for (int i = 0; i < listOrganisation.size(); i++) {
                                    System.out.println(listOrganisation.get(i).getNomOrganisation());
                                }
                                System.out.println("Indiquez le nom de l'organisation vendeuse : ");
                                vendeur = scanner.nextLine();

                                System.out.println("Est-ce le rachat d'un media (taper Media) ou d'une organisation (taper Organisation) : ");
                                infoInstances = scanner.nextLine();


                                if (infoInstances.equals("Media")){

                                    System.out.println("Voici la liste des medias");
                                    for (int i = 0; i < listMedia.size(); i++) {
                                        System.out.println(listMedia.get(i).getNomMedia());
                                    }
                                    System.out.println("Indiquez le media qui a ete rachete : ");
                                    vendu = scanner.nextLine();
                                    System.out.println("Indiquez le pourcentage du rachat : ");
                                    pourcentage = scanner.nextLine();
                                    float pourcentageFloat = Float.parseFloat(pourcentage);

                                    MiseAJour.rachatPersonnaliteOrganisationMedia(acheteur,vendeur,vendu,pourcentageFloat);
                                    if (vendu.equals("TMC")){
                                        dateRachatTMC = LocalDateTime.now();
                                        moduleSuivieTMC.setHistoriqueRachat("La personne " + acheteur + " rachete " + pourcentageFloat + " % de TMC à l'organisation " + vendeur + " à la date " + dateRachatTMC.format(formatter));
                                    }

                                }

                                else if(infoInstances.equals("Organisation")){
                                    System.out.println("Voici la liste des organisations");
                                    for (int i = 0; i < listOrganisation.size(); i++) {
                                        System.out.println(listOrganisation.get(i).getNomOrganisation());
                                    }
                                    System.out.println("Indiquez l'organisation qui a ete rachete : ");
                                    vendu = scanner.nextLine();
                                    System.out.println("Indiquez le pourcentage du rachat : ");
                                    pourcentage = scanner.nextLine();
                                    float pourcentageFloat = Float.parseFloat(pourcentage);

                                    MiseAJour.rachatPersonnaliteOrganisationOrganisation(acheteur,vendeur,vendu,pourcentageFloat);
                                }

                            }
                        }


                        else if(infoInstances.equals("Organisation")){
                            System.out.println("Voici la liste des organisations : ");
                            for (int i = 0; i < listOrganisation.size(); i++) {
                                System.out.println(listOrganisation.get(i).getNomOrganisation());
                            }

                            System.out.println("Ecrivez le nom de l'acheteur : ");
                            acheteur = scanner.nextLine();

                            System.out.println("Taper Personne si le vendeur est une personne, taper Organisation si le vendeur est une organisation ");
                            infoInstances = scanner.nextLine();

                            if (infoInstances.equals("Personne")){

                                System.out.println("Ecrivez le nom du vendeur : ");
                                vendeur = scanner.nextLine();

                                System.out.println("Est-ce le rachat d'un media (taper Media) ou d'une organisation (taper Organisation) : ");
                                infoInstances = scanner.nextLine();



                                if (infoInstances.equals("Media")){
                                    System.out.println("Voici la liste des medias");
                                    for (int i = 0; i < listMedia.size(); i++) {
                                        System.out.println(listMedia.get(i).getNomMedia());
                                    }
                                    System.out.println("Indiquez le media qui a ete rachete : ");
                                    vendu = scanner.nextLine();
                                    System.out.println("Indiquez le pourcentage du rachat : ");
                                    pourcentage = scanner.nextLine();
                                    float pourcentageFloat = Float.parseFloat(pourcentage);

                                    MiseAJour.rachatOrganisationPersonnaliteMedia(acheteur,vendeur,vendu,pourcentageFloat);
                                    if (vendu.equals("TMC")){
                                        dateRachatTMC = LocalDateTime.now();
                                        moduleSuivieTMC.setHistoriqueRachat("L'organisation " + acheteur + " rachete " + pourcentageFloat + " % de TMC à la personne " + vendeur + " à la date " + dateRachatTMC.format(formatter));
                                    }
                                }


                                else if(infoInstances.equals("Organisation")){
                                    System.out.println("Voici la liste des organisations");
                                    for (int i = 0; i < listOrganisation.size(); i++) {
                                        System.out.println(listOrganisation.get(i).getNomOrganisation());
                                    }
                                    System.out.println("Indiquez l'organisation qui a ete rachete : ");
                                    vendu = scanner.nextLine();
                                    System.out.println("Indiquez le pourcentage du rachat : ");
                                    pourcentage = scanner.nextLine();
                                    float pourcentageFloat = Float.parseFloat(pourcentage);

                                    MiseAJour.rachatOrganisationPersonnaliteOrganisation(acheteur,vendeur,vendu,pourcentageFloat);
                                }
                            }



                            else if (infoInstances.equals("Organisation")){


                                System.out.println("Voici la liste des organisations : ");
                                for (int i = 0; i < listOrganisation.size(); i++) {
                                    System.out.println(listOrganisation.get(i).getNomOrganisation());
                                }
                                System.out.println("Indiquez le nom de l'organisation vendeuse : ");
                                vendeur = scanner.nextLine();

                                System.out.println("Est-ce le rachat d'un media (taper Media) ou d'une organisation (taper Organisation) : ");
                                infoInstances = scanner.nextLine();


                                if (infoInstances.equals("Media")){

                                    System.out.println("Voici la liste des medias");
                                    for (int i = 0; i < listMedia.size(); i++) {
                                        System.out.println(listMedia.get(i).getNomMedia());
                                    }
                                    System.out.println("Indiquez le media qui a ete rachete : ");
                                    vendu = scanner.nextLine();
                                    System.out.println("Indiquez le pourcentage du rachat : ");
                                    pourcentage = scanner.nextLine();
                                    float pourcentageFloat = Float.parseFloat(pourcentage);

                                    MiseAJour.rachatOrganisationOrganisationMedia(acheteur,vendeur,vendu,pourcentageFloat);
                                    if (vendu.equals("TMC")){
                                        dateRachatTMC = LocalDateTime.now();
                                        moduleSuivieTMC.setHistoriqueRachat("L'organisation " + acheteur + " rachete " + pourcentageFloat + " % de TMC à l'organisation " + vendeur + " à la date " + dateRachatTMC.format(formatter));
                                    }

                                }

                                else if(infoInstances.equals("Organisation")){
                                    System.out.println("Voici la liste des organisations");
                                    for (int i = 0; i < listOrganisation.size(); i++) {
                                        System.out.println(listOrganisation.get(i).getNomOrganisation());
                                    }
                                    System.out.println("Indiquez l'organisation qui a ete rachete : ");
                                    vendu = scanner.nextLine();
                                    System.out.println("Indiquez le pourcentage du rachat : ");
                                    pourcentage = scanner.nextLine();
                                    float pourcentageFloat = Float.parseFloat(pourcentage);

                                    MiseAJour.rachatOrganisationOrganisationOrganisation(acheteur,vendeur,vendu,pourcentageFloat);
                                }

                            }
                        }
                    }


                    //L'utilisateur veut mettre a jour les publications de la base de donnee
                    else if(infoInstances.equals("Publier")){
                        List<Personnalite> listMentionPersonne = new ArrayList<>();
                        List<Organisation> listMentionOrganisation = new ArrayList<>();
                        List<Media> listMentionMedia = new ArrayList<>();
                        Personnalite personneMentionnee = null;
                        Organisation organisationMentionnee = null;
                        Media mediaMentione = null;
                        LocalDateTime datePublication = null;
                        boolean isTMC = false;
                        boolean mentionVincentBollore = false;
                        System.out.println("Voici la liste des medias : ");
                        for (int i = 0; i < listMedia.size(); i++) {
                            System.out.println(listMedia.get(i).getNomMedia());
                        }

                        System.out.println("Sur quel media voulez vous publier ? ");
                        infoInstances = scanner.nextLine();

                        Media mediaPublication = null;
                        for (int i = 0; i < listMedia.size(); i++) {
                            if (listMedia.get(i).getNomMedia().equals(infoInstances)){
                                mediaPublication = listMedia.get(i);
                            }
                        }

                        if (mediaPublication == null){
                            System.out.println("Ce media n'est pas dans la base de donnee");
                        }

                        else{
                            if (mediaPublication.getNomMedia().equals("TMC")){
                                isTMC = true;
                            }
                            //Ecrire dans le rapport que j'ai considere que les sites ne pouvaient publier que des articles ou des interviews
                            if (mediaPublication.getType().equals("Télévision") || mediaPublication.getType().equals("Radio")){
                                System.out.println("Tapez Reportage si la publication est un reportage, tapez Interview si la publication est une interview : ");
                                infoInstances = scanner.nextLine();

                                if (infoInstances.equals("Reportage")){
                                    Reportage nouveauReportage;
                                    System.out.println("Quel est le titre de ce reportage ? ");
                                    String titre = scanner.nextLine();

                                    System.out.println("Tapez Personne s'il y a des personnes mentionnees dans le reportage, sinon vous pouvez taper ce que vous voulez : ");
                                    infoInstances = scanner.nextLine();
                                    if (infoInstances.equals("Personne")){

                                        System.out.println("Voici la liste des personnes : ");
                                        for (int i = 0; i < listPersonnalite.size(); i++) {
                                            System.out.println(listPersonnalite.get(i).getNomPersonnalite());
                                        }

                                        while (!infoInstances.equals("Fin")){
                                            System.out.println("Pour ajouter une personne aux mentions du reportage ecrivez son prenom et son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les personnes mentionnees tapez Fin");
                                            infoInstances = scanner.nextLine();
                                            personneMentionnee = null;
                                            for (int i = 0; i < listPersonnalite.size(); i++) {
                                                if (infoInstances.equals(listPersonnalite.get(i).getNomPersonnalite())){
                                                    personneMentionnee = listPersonnalite.get(i);
                                                    if (personneMentionnee.getNomPersonnalite().equals("Vincent Bolloré")){
                                                        mentionVincentBollore = true;
                                                    }
                                                }
                                            }

                                            if (personneMentionnee == null && !infoInstances.equals("Fin")){
                                                System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                            }
                                            else{
                                                for (int i = 0; i < listMentionPersonne.size(); i++) {
                                                    if (listMentionPersonne.get(i).equals(personneMentionnee)){
                                                        System.out.println(personneMentionnee.getNomPersonnalite() + " a deja ete ajoutee !");
                                                        personneMentionnee = null;
                                                    }
                                                }



                                                if (personneMentionnee!=null){
                                                    listMentionPersonne.add(personneMentionnee);
                                                    System.out.println(personneMentionnee.getNomPersonnalite() + " a ete ajoutee aux mentions");
                                                    moduleSuivieTMC.setNbMentionPersonne(personneMentionnee);
                                                    personneMentionnee = null;
                                                }
                                            }
                                        }
                                    }

                                    System.out.println("Tapez Organisation s'il y a des organisations mentionnees dans le reportage, sinon vous pouvez taper ce que vous voulez");
                                    infoInstances = scanner.nextLine();
                                    if (infoInstances.equals("Organisation")){

                                        System.out.println("Voici la liste des organisations : ");
                                        for (int i = 0; i < listOrganisation.size(); i++) {
                                            System.out.println(listOrganisation.get(i).getNomOrganisation());
                                        }

                                        while (!infoInstances.equals("Fin")){
                                            System.out.println("Pour ajouter une organisation aux mentions du reportage ecrivez son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les organisations mentionnees tapez Fin");
                                            infoInstances = scanner.nextLine();
                                            for (int i = 0; i < listOrganisation.size(); i++) {
                                                if (infoInstances.equals(listOrganisation.get(i).getNomOrganisation())){
                                                    organisationMentionnee = listOrganisation.get(i);
                                                }
                                            }

                                            if (organisationMentionnee == null && !infoInstances.equals("Fin")){
                                                System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                            }
                                            else{
                                                for (int i = 0; i < listMentionOrganisation.size(); i++) {
                                                    if (listMentionOrganisation.get(i).equals(organisationMentionnee)){
                                                        System.out.println(organisationMentionnee.getNomOrganisation() + " a deja ete ajoutee !");
                                                        organisationMentionnee = null;
                                                    }
                                                }



                                                if (organisationMentionnee!=null){
                                                    listMentionOrganisation.add(organisationMentionnee);
                                                    System.out.println(organisationMentionnee.getNomOrganisation() + " a ete ajoutee aux mentions");
                                                    moduleSuivieTMC.setNbMentionOrganisation(organisationMentionnee);
                                                    organisationMentionnee = null;
                                                }
                                            }


                                        }
                                    }

                                    System.out.println("Tapez Media s'il y a des medias mentionnes dans le reportage, sinon vous pouvez taper ce que vous voulez");
                                    infoInstances = scanner.nextLine();
                                    if (infoInstances.equals("Media")){

                                        System.out.println("Voici la liste des medias : ");
                                        for (int i = 0; i < listMedia.size(); i++) {
                                            System.out.println(listMedia.get(i).getNomMedia());
                                        }

                                        while (!infoInstances.equals("Fin")){
                                            System.out.println("Pour ajouter un media aux mentions du reportage ecrivez son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les organisations mentionnees tapez Fin");
                                            infoInstances = scanner.nextLine();
                                            for (int i = 0; i < listMedia.size(); i++) {
                                                if (infoInstances.equals(listMedia.get(i).getNomMedia())){
                                                    mediaMentione = listMedia.get(i);
                                                }
                                            }

                                            if (mediaMentione == null && !infoInstances.equals("Fin")){
                                                System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                            }
                                            else{
                                                for (int i = 0; i < listMentionMedia.size(); i++) {
                                                    if (listMentionMedia.get(i).equals(mediaMentione)){
                                                        System.out.println(mediaMentione.getNomMedia() + " a deja ete ajoutee !");
                                                        mediaMentione = null;
                                                    }
                                                }



                                                if (mediaMentione!=null){
                                                    listMentionMedia.add(mediaMentione);
                                                    System.out.println(mediaMentione.getNomMedia() + " a ete ajoutee aux mentions");
                                                    moduleSuivieTMC.setNbMentionMedia(mediaMentione);
                                                    mediaMentione = null;
                                                }
                                            }


                                        }
                                    }

                                    datePublication = LocalDateTime.now();

                                    //Verification que des personnes ont bien ete ajoutee
                                    if(listMentionPersonne.isEmpty()){

                                        if (listMentionOrganisation.isEmpty()){
                                            //Cas où aucune personne, aucune organisation et aucun media ne sont mentionnees
                                            if (listMentionMedia.isEmpty()){
                                                nouveauReportage = new Reportage(titre,datePublication);
                                            }
                                            //Cas où seuls sont media mentiones
                                            else{
                                                nouveauReportage = new Reportage(listMentionMedia,titre,"reportage",datePublication);
                                            }

                                        }
                                        else{
                                            if (listMentionMedia.isEmpty()){
                                                //Cas où aucune personne et aucun media n'est mentionnee mais où une organisation est mentionnee
                                                nouveauReportage = new Reportage(listMentionOrganisation,titre,datePublication);
                                            }
                                            else{
                                                //Cas où aucune personne n'est mentionnee mais où une organisation et un media sont mentionnes
                                                nouveauReportage = new Reportage(listMentionMedia,titre,"reportage",listMentionOrganisation,datePublication);
                                            }

                                        }
                                    }
                                    else{
                                        if (listMentionOrganisation.isEmpty()){
                                            if (listMentionMedia.isEmpty()){
                                                //Cas où une personne est mentionnee mais pas d'organisation ni de media
                                                nouveauReportage = new Reportage(titre,listMentionPersonne,datePublication);
                                            }
                                            else{
                                                //Cas où une personne et un media sont mentionnees mais pas d'organisation
                                                nouveauReportage = new Reportage(listMentionMedia,titre,listMentionPersonne,"reportage",datePublication);
                                            }

                                        }
                                        else{
                                            if (listMentionMedia.isEmpty()){
                                                //Cas où une personne et une organisation sont mentionnees
                                                nouveauReportage = new Reportage(titre,listMentionPersonne,listMentionOrganisation,datePublication);
                                            }
                                            else{
                                                //Cas où les 3 sont mentionnes
                                                nouveauReportage = new Reportage(listMentionMedia,titre,listMentionPersonne,listMentionOrganisation,datePublication);
                                            }

                                        }

                                        if (mentionVincentBollore){
                                            moduleSuivieVincentBollore.setListPublicationConcerne(mediaPublication,nouveauReportage);
                                            mentionVincentBollore = false;
                                        }
                                        
                                    }

                                    mediaPublication.setListPublication(nouveauReportage);
                                    System.out.println("Le reportage " + nouveauReportage.getTitre() + " a ete ajoute au media " + mediaPublication.getNomMedia());


                                }

                                else  if(infoInstances.equals("Interview")){

                                    Interview nouvelleInterview;
                                    System.out.println("Quel est le titre de cette interview ? ");
                                    String titre = scanner.nextLine();

                                    System.out.println("Tapez Personne s'il y a des personnes mentionnees dans l'interview, sinon vous pouvez taper ce que vous voulez : ");
                                    infoInstances = scanner.nextLine();
                                    if (infoInstances.equals("Personne")){

                                        System.out.println("Voici la liste des personnes : ");
                                        for (int i = 0; i < listPersonnalite.size(); i++) {
                                            System.out.println(listPersonnalite.get(i).getNomPersonnalite());
                                        }

                                        while (!infoInstances.equals("Fin")){
                                            System.out.println("Pour ajouter une personne aux mentions de l'interview ecrivez son prenom et son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les personnes mentionnees tapez Fin");
                                            infoInstances = scanner.nextLine();
                                            personneMentionnee = null;
                                            for (int i = 0; i < listPersonnalite.size(); i++) {
                                                if (infoInstances.equals(listPersonnalite.get(i).getNomPersonnalite())){
                                                    personneMentionnee = listPersonnalite.get(i);
                                                    if (personneMentionnee.getNomPersonnalite().equals("Vincent Bolloré")){
                                                        mentionVincentBollore = true;
                                                    }
                                                }

                                            }

                                            if (personneMentionnee == null && !infoInstances.equals("Fin")){
                                                System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                            }
                                            else{
                                                for (int i = 0; i < listMentionPersonne.size(); i++) {
                                                    if (listMentionPersonne.get(i).equals(personneMentionnee)){
                                                        System.out.println(personneMentionnee.getNomPersonnalite() + " a deja ete ajoutee !");
                                                        personneMentionnee = null;
                                                    }
                                                }



                                                if (personneMentionnee!=null){
                                                    listMentionPersonne.add(personneMentionnee);
                                                    System.out.println(personneMentionnee.getNomPersonnalite() + " a ete ajoutee aux mentions");
                                                    moduleSuivieTMC.setNbMentionPersonne(personneMentionnee);
                                                    personneMentionnee = null;
                                                }
                                            }
                                        }
                                    }

                                    System.out.println("Tapez Organisation s'il y a des organisations mentionnees dans l'interview, sinon vous pouvez taper ce que vous voulez");
                                    infoInstances = scanner.nextLine();
                                    if (infoInstances.equals("Organisation")){

                                        System.out.println("Voici la liste des organisations : ");
                                        for (int i = 0; i < listOrganisation.size(); i++) {
                                            System.out.println(listOrganisation.get(i).getNomOrganisation());
                                        }

                                        while (!infoInstances.equals("Fin")){
                                            System.out.println("Pour ajouter une organisation aux mentions de l'interview ecrivez son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les organisations mentionnees tapez Fin");
                                            infoInstances = scanner.nextLine();
                                            for (int i = 0; i < listOrganisation.size(); i++) {
                                                if (infoInstances.equals(listOrganisation.get(i).getNomOrganisation())){
                                                    organisationMentionnee = listOrganisation.get(i);
                                                }
                                            }

                                            if (organisationMentionnee == null && !infoInstances.equals("Fin")){
                                                System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                            }
                                            else{
                                                for (int i = 0; i < listMentionOrganisation.size(); i++) {
                                                    if (listMentionOrganisation.get(i).equals(organisationMentionnee)){
                                                        System.out.println(organisationMentionnee.getNomOrganisation() + " a deja ete ajoutee !");
                                                        organisationMentionnee = null;
                                                    }
                                                }



                                                if (organisationMentionnee!=null){
                                                    listMentionOrganisation.add(organisationMentionnee);
                                                    System.out.println(organisationMentionnee.getNomOrganisation() + " a ete ajoutee aux mentions");
                                                    moduleSuivieTMC.setNbMentionOrganisation(organisationMentionnee);
                                                    organisationMentionnee = null;
                                                }
                                            }


                                        }
                                    }

                                    System.out.println("Tapez Media s'il y a des medias mentionnees dans l'interview, sinon vous pouvez taper ce que vous voulez");
                                    infoInstances = scanner.nextLine();
                                    if (infoInstances.equals("Media")){

                                        System.out.println("Voici la liste des medias : ");
                                        for (int i = 0; i < listMedia.size(); i++) {
                                            System.out.println(listMedia.get(i).getNomMedia());
                                        }

                                        while (!infoInstances.equals("Fin")){
                                            System.out.println("Pour ajouter un media aux mentions du reportage ecrivez son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les organisations mentionnees tapez Fin");
                                            infoInstances = scanner.nextLine();
                                            for (int i = 0; i < listMedia.size(); i++) {
                                                if (infoInstances.equals(listMedia.get(i).getNomMedia())){
                                                    mediaMentione = listMedia.get(i);
                                                }
                                            }

                                            if (mediaMentione == null && !infoInstances.equals("Fin")){
                                                System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                            }
                                            else{
                                                for (int i = 0; i < listMentionMedia.size(); i++) {
                                                    if (listMentionMedia.get(i).equals(mediaMentione)){
                                                        System.out.println(mediaMentione.getNomMedia() + " a deja ete ajoutee !");
                                                        mediaMentione = null;
                                                    }
                                                }



                                                if (mediaMentione!=null){
                                                    listMentionMedia.add(mediaMentione);
                                                    System.out.println(mediaMentione.getNomMedia() + " a ete ajoutee aux mentions");
                                                    moduleSuivieTMC.setNbMentionMedia(mediaMentione);
                                                    mediaMentione = null;
                                                }
                                            }


                                        }
                                    }

                                    datePublication = LocalDateTime.now();

                                    //Verification que des personnes ont bien ete ajoutee
                                    if(listMentionPersonne.isEmpty()){

                                        if (listMentionOrganisation.isEmpty()){
                                            //Cas où aucune personne, aucune organisation et aucun media ne sont mentionnees
                                            if (listMentionMedia.isEmpty()){
                                                nouvelleInterview = new Interview(titre,datePublication);
                                            }
                                            //Cas où seuls media mentione
                                            else{
                                                nouvelleInterview = new Interview(listMentionMedia,titre,"interview",datePublication);
                                            }

                                        }
                                        else{
                                            if (listMentionMedia.isEmpty()){
                                                //Cas où aucune personne et aucun media n'est mentionnee mais où une organisation est mentionnee
                                                nouvelleInterview = new Interview(listMentionOrganisation,titre,datePublication);
                                            }
                                            else{
                                                //Cas où aucune personne n'est mentionnee mais où une organisation et un media sont mentionnes
                                                nouvelleInterview = new Interview(listMentionMedia,titre,"interview",listMentionOrganisation,datePublication);
                                            }

                                        }
                                    }
                                    else{
                                        if (listMentionOrganisation.isEmpty()){
                                            if (listMentionMedia.isEmpty()){
                                                //Cas où une personne est mentionnee mais pas d'organisation ni de media
                                                nouvelleInterview = new Interview(titre,listMentionPersonne,datePublication);
                                            }
                                            else{
                                                //Cas où une personne et un media sont mentionnees mais pas d'organisation
                                                nouvelleInterview = new Interview(listMentionMedia,titre,listMentionPersonne,"interview",datePublication);
                                            }

                                        }
                                        else{
                                            if (listMentionMedia.isEmpty()){
                                                //Cas où une personne et une organisation sont mentionnees
                                                nouvelleInterview = new Interview(titre,listMentionPersonne,listMentionOrganisation,datePublication);
                                            }
                                            else{
                                                //Cas où les 3 sont mentionnes
                                                nouvelleInterview = new Interview(listMentionMedia,titre,listMentionPersonne,listMentionOrganisation,datePublication);
                                            }

                                        }

                                        if (mentionVincentBollore){
                                            moduleSuivieVincentBollore.setListPublicationConcerne(mediaPublication,nouvelleInterview);
                                            mentionVincentBollore = false;
                                        }

                                    }

                                    mediaPublication.setListPublication(nouvelleInterview);
                                    System.out.println("L'interview " + nouvelleInterview.getTitre() + " a ete ajoute au media " + mediaPublication.getNomMedia());




                                }
                            }

                            else{
                                {
                                    System.out.println("Tapez Article si la publication est un article, tapez Interview si la publication est une interview : ");
                                    infoInstances = scanner.nextLine();

                                    if (infoInstances.equals("Article")){
                                        Article nouvelArticle;
                                        System.out.println("Quel est le titre de cet article ? ");
                                        String titre = scanner.nextLine();

                                        System.out.println("Tapez Personne s'il y a des personnes mentionnees dans l'article, sinon vous pouvez taper ce que vous voulez : ");
                                        infoInstances = scanner.nextLine();
                                        if (infoInstances.equals("Personne")){

                                            System.out.println("Voici la liste des personnes : ");
                                            for (int i = 0; i < listPersonnalite.size(); i++) {
                                                System.out.println(listPersonnalite.get(i).getNomPersonnalite());
                                            }

                                            while (!infoInstances.equals("Fin")){
                                                System.out.println("Pour ajouter une personne aux mentions de l'article ecrivez son prenom et son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les personnes mentionnees tapez Fin");
                                                infoInstances = scanner.nextLine();
                                                personneMentionnee = null;
                                                for (int i = 0; i < listPersonnalite.size(); i++) {
                                                    if (infoInstances.equals(listPersonnalite.get(i).getNomPersonnalite())){
                                                        personneMentionnee = listPersonnalite.get(i);
                                                        if (personneMentionnee.getNomPersonnalite().equals("Vincent Bolloré")){
                                                            mentionVincentBollore = true;
                                                        }
                                                    }

                                                }

                                                if (personneMentionnee == null && !infoInstances.equals("Fin")){
                                                    System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                                }
                                                else{
                                                    for (int i = 0; i < listMentionPersonne.size(); i++) {
                                                        if (listMentionPersonne.get(i).equals(personneMentionnee)){
                                                            System.out.println(personneMentionnee.getNomPersonnalite() + " a deja ete ajoutee !");
                                                            personneMentionnee = null;
                                                        }
                                                    }



                                                    if (personneMentionnee!=null){
                                                        listMentionPersonne.add(personneMentionnee);
                                                        System.out.println(personneMentionnee.getNomPersonnalite() + " a ete ajoutee aux mentions");
                                                        moduleSuivieTMC.setNbMentionPersonne(personneMentionnee);
                                                        personneMentionnee = null;
                                                    }
                                                }
                                            }
                                        }

                                        System.out.println("Tapez Organisation s'il y a des organisations mentionnees dans l'article, sinon vous pouvez taper ce que vous voulez");
                                        infoInstances = scanner.nextLine();
                                        if (infoInstances.equals("Organisation")){

                                            System.out.println("Voici la liste des organisations : ");
                                            for (int i = 0; i < listOrganisation.size(); i++) {
                                                System.out.println(listOrganisation.get(i).getNomOrganisation());
                                            }

                                            while (!infoInstances.equals("Fin")){
                                                System.out.println("Pour ajouter une organisation aux mentions du reportage ecrivez son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les organisations mentionnees tapez Fin");
                                                infoInstances = scanner.nextLine();
                                                for (int i = 0; i < listOrganisation.size(); i++) {
                                                    if (infoInstances.equals(listOrganisation.get(i).getNomOrganisation())){
                                                        organisationMentionnee = listOrganisation.get(i);
                                                    }
                                                }

                                                if (organisationMentionnee == null && !infoInstances.equals("Fin")){
                                                    System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                                }
                                                else{
                                                    for (int i = 0; i < listMentionOrganisation.size(); i++) {
                                                        if (listMentionOrganisation.get(i).equals(organisationMentionnee)){
                                                            System.out.println(organisationMentionnee.getNomOrganisation() + " a deja ete ajoutee !");
                                                            organisationMentionnee = null;
                                                        }
                                                    }



                                                    if (organisationMentionnee!=null){
                                                        listMentionOrganisation.add(organisationMentionnee);
                                                        System.out.println(organisationMentionnee.getNomOrganisation() + " a ete ajoutee aux mentions");
                                                        moduleSuivieTMC.setNbMentionOrganisation(organisationMentionnee);
                                                        organisationMentionnee = null;
                                                    }
                                                }


                                            }
                                        }

                                        System.out.println("Tapez Media s'il y a des medias mentionnees dans l'article, sinon vous pouvez taper ce que vous voulez");
                                        infoInstances = scanner.nextLine();
                                        if (infoInstances.equals("Media")){

                                            System.out.println("Voici la liste des medias : ");
                                            for (int i = 0; i < listMedia.size(); i++) {
                                                System.out.println(listMedia.get(i).getNomMedia());
                                            }

                                            while (!infoInstances.equals("Fin")){
                                                System.out.println("Pour ajouter un media aux mentions de l'article ecrivez son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les organisations mentionnees tapez Fin");
                                                infoInstances = scanner.nextLine();
                                                for (int i = 0; i < listMedia.size(); i++) {
                                                    if (infoInstances.equals(listMedia.get(i).getNomMedia())){
                                                        mediaMentione = listMedia.get(i);
                                                    }
                                                }

                                                if (mediaMentione == null && !infoInstances.equals("Fin")){
                                                    System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                                }
                                                else{
                                                    for (int i = 0; i < listMentionMedia.size(); i++) {
                                                        if (listMentionMedia.get(i).equals(mediaMentione)){
                                                            System.out.println(mediaMentione.getNomMedia() + " a deja ete ajoutee !");
                                                            mediaMentione = null;
                                                        }
                                                    }



                                                    if (mediaMentione!=null){
                                                        listMentionMedia.add(mediaMentione);
                                                        System.out.println(mediaMentione.getNomMedia() + " a ete ajoutee aux mentions");
                                                        moduleSuivieTMC.setNbMentionMedia(mediaMentione);
                                                        mediaMentione = null;
                                                    }
                                                }


                                            }
                                        }

                                        datePublication = LocalDateTime.now();

                                        //Verification que des personnes ont bien ete ajoutee
                                        if(listMentionPersonne.isEmpty()){

                                            if (listMentionOrganisation.isEmpty()){
                                                //Cas où aucune personne, aucune organisation et aucun media ne sont mentionnees
                                                if (listMentionMedia.isEmpty()){
                                                    nouvelArticle = new Article(titre,datePublication);
                                                }
                                                //Cas où seuls media mentione
                                                else{
                                                    nouvelArticle = new Article(listMentionMedia,titre,"article",datePublication);
                                                }

                                            }
                                            else{
                                                if (listMentionMedia.isEmpty()){
                                                    //Cas où aucune personne et aucun media n'est mentionnee mais où une organisation est mentionnee
                                                    nouvelArticle = new Article(listMentionOrganisation,titre,datePublication);
                                                }
                                                else{
                                                    //Cas où aucune personne n'est mentionnee mais où une organisation et un media sont mentionnes
                                                    nouvelArticle = new Article(listMentionMedia,titre,"article",listMentionOrganisation,datePublication);
                                                }

                                            }
                                        }
                                        else{
                                            if (listMentionOrganisation.isEmpty()){
                                                if (listMentionMedia.isEmpty()){
                                                    //Cas où une personne est mentionnee mais pas d'organisation ni de media
                                                    nouvelArticle = new Article(titre,listMentionPersonne,datePublication);
                                                }
                                                else{
                                                    //Cas où une personne et un media sont mentionnees mais pas d'organisation
                                                    nouvelArticle = new Article(listMentionMedia,titre,listMentionPersonne,"article",datePublication);
                                                }

                                            }
                                            else{
                                                if (listMentionMedia.isEmpty()){
                                                    //Cas où une personne et une organisation sont mentionnees
                                                    nouvelArticle = new Article(titre,listMentionPersonne,listMentionOrganisation,datePublication);
                                                }
                                                else{
                                                    //Cas où les 3 sont mentionnes
                                                    nouvelArticle = new Article(listMentionMedia,titre,listMentionPersonne,listMentionOrganisation,datePublication);
                                                }

                                            }

                                            if (mentionVincentBollore){
                                                moduleSuivieVincentBollore.setListPublicationConcerne(mediaPublication,nouvelArticle);
                                                mentionVincentBollore = false;
                                            }

                                        }

                                        mediaPublication.setListPublication(nouvelArticle);
                                        System.out.println("L'article " + nouvelArticle.getTitre() + " a ete ajoute au media " + mediaPublication.getNomMedia());


                                    }

                                    else  if(infoInstances.equals("Interview")){

                                        Interview nouvelleInterview;
                                        System.out.println("Quel est le titre de cette interview ? ");
                                        String titre = scanner.nextLine();

                                        System.out.println("Tapez Personne s'il y a des personnes mentionnees dans l'interview, sinon vous pouvez taper ce que vous voulez : ");
                                        infoInstances = scanner.nextLine();
                                        if (infoInstances.equals("Personne")){

                                            System.out.println("Voici la liste des personnes : ");
                                            for (int i = 0; i < listPersonnalite.size(); i++) {
                                                System.out.println(listPersonnalite.get(i).getNomPersonnalite());
                                            }

                                            while (!infoInstances.equals("Fin")){
                                                System.out.println("Pour ajouter une personne aux mentions de l'interview ecrivez son prenom et son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les personnes mentionnees tapez Fin");
                                                infoInstances = scanner.nextLine();
                                                personneMentionnee = null;
                                                for (int i = 0; i < listPersonnalite.size(); i++) {
                                                    if (infoInstances.equals(listPersonnalite.get(i).getNomPersonnalite())){
                                                        personneMentionnee = listPersonnalite.get(i);
                                                        if (personneMentionnee.getNomPersonnalite().equals("Vincent Bolloré")){
                                                            mentionVincentBollore = true;
                                                        }
                                                    }
                                                }

                                                if (personneMentionnee == null && !infoInstances.equals("Fin")){
                                                    System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                                }
                                                else{
                                                    for (int i = 0; i < listMentionPersonne.size(); i++) {
                                                        if (listMentionPersonne.get(i).equals(personneMentionnee)){
                                                            System.out.println(personneMentionnee.getNomPersonnalite() + " a deja ete ajoutee !");
                                                            personneMentionnee = null;
                                                        }
                                                    }



                                                    if (personneMentionnee!=null){
                                                        listMentionPersonne.add(personneMentionnee);
                                                        System.out.println(personneMentionnee.getNomPersonnalite() + " a ete ajoutee aux mentions");
                                                        moduleSuivieTMC.setNbMentionPersonne(personneMentionnee);
                                                        personneMentionnee = null;
                                                    }
                                                }
                                            }
                                        }

                                        System.out.println("Tapez Organisation s'il y a des organisations mentionnees dans l'interview, sinon vous pouvez taper ce que vous voulez");
                                        infoInstances = scanner.nextLine();
                                        if (infoInstances.equals("Organisation")){

                                            System.out.println("Voici la liste des organisations : ");
                                            for (int i = 0; i < listOrganisation.size(); i++) {
                                                System.out.println(listOrganisation.get(i).getNomOrganisation());
                                            }

                                            while (!infoInstances.equals("Fin")){
                                                System.out.println("Pour ajouter une organisation aux mentions de l'interview ecrivez son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les organisations mentionnees tapez Fin");
                                                infoInstances = scanner.nextLine();
                                                for (int i = 0; i < listOrganisation.size(); i++) {
                                                    if (infoInstances.equals(listOrganisation.get(i).getNomOrganisation())){
                                                        organisationMentionnee = listOrganisation.get(i);
                                                    }
                                                }

                                                if (organisationMentionnee == null && !infoInstances.equals("Fin")){
                                                    System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                                }
                                                else{
                                                    for (int i = 0; i < listMentionOrganisation.size(); i++) {
                                                        if (listMentionOrganisation.get(i).equals(organisationMentionnee)){
                                                            System.out.println(organisationMentionnee.getNomOrganisation() + " a deja ete ajoutee !");
                                                            organisationMentionnee = null;
                                                        }
                                                    }



                                                    if (organisationMentionnee!=null){
                                                        listMentionOrganisation.add(organisationMentionnee);
                                                        System.out.println(organisationMentionnee.getNomOrganisation() + " a ete ajoutee aux mentions");
                                                        moduleSuivieTMC.setNbMentionOrganisation(organisationMentionnee);
                                                        organisationMentionnee = null;
                                                    }
                                                }


                                            }
                                        }

                                        System.out.println("Tapez Media s'il y a des medias mentionnees dans l'interview, sinon vous pouvez taper ce que vous voulez");
                                        infoInstances = scanner.nextLine();
                                        if (infoInstances.equals("Media")){

                                            System.out.println("Voici la liste des medias : ");
                                            for (int i = 0; i < listMedia.size(); i++) {
                                                System.out.println(listMedia.get(i).getNomMedia());
                                            }

                                            while (!infoInstances.equals("Fin")){
                                                System.out.println("Pour ajouter un media aux mentions du reportage ecrivez son nom puis appuyez sur la touche entree, lorsque vous avez ajouter toutes les organisations mentionnees tapez Fin");
                                                infoInstances = scanner.nextLine();
                                                for (int i = 0; i < listMedia.size(); i++) {
                                                    if (infoInstances.equals(listMedia.get(i).getNomMedia())){
                                                        mediaMentione = listMedia.get(i);
                                                    }
                                                }

                                                if (mediaMentione == null && !infoInstances.equals("Fin")){
                                                    System.out.println(infoInstances + " n'est pas dans la base de donnee");
                                                }
                                                else{
                                                    for (int i = 0; i < listMentionMedia.size(); i++) {
                                                        if (listMentionMedia.get(i).equals(mediaMentione)){
                                                            System.out.println(mediaMentione.getNomMedia() + " a deja ete ajoutee !");
                                                            mediaMentione = null;
                                                        }
                                                    }



                                                    if (mediaMentione!=null){
                                                        listMentionMedia.add(mediaMentione);
                                                        System.out.println(mediaMentione.getNomMedia() + " a ete ajoute aux mentions");
                                                        moduleSuivieTMC.setNbMentionMedia(mediaMentione);
                                                        mediaMentione = null;
                                                    }
                                                }


                                            }
                                        }

                                        datePublication = LocalDateTime.now();

                                        //Verification que des personnes ont bien ete ajoutee
                                        if(listMentionPersonne.isEmpty()){

                                            if (listMentionOrganisation.isEmpty()){
                                                //Cas où aucune personne, aucune organisation et aucun media ne sont mentionnees
                                                if (listMentionMedia.isEmpty()){
                                                    nouvelleInterview = new Interview(titre,datePublication);
                                                }
                                                //Cas où seuls media mentione
                                                else{
                                                    nouvelleInterview = new Interview(listMentionMedia,titre,"interview",datePublication);
                                                }

                                            }
                                            else{
                                                if (listMentionMedia.isEmpty()){
                                                    //Cas où aucune personne et aucun media n'est mentionnee mais où une organisation est mentionnee
                                                    nouvelleInterview = new Interview(listMentionOrganisation,titre,datePublication);
                                                }
                                                else{
                                                    //Cas où aucune personne n'est mentionnee mais où une organisation et un media sont mentionnes
                                                    nouvelleInterview = new Interview(listMentionMedia,titre,"interview",listMentionOrganisation,datePublication);
                                                }

                                            }
                                        }
                                        else{
                                            if (listMentionOrganisation.isEmpty()){
                                                if (listMentionMedia.isEmpty()){
                                                    //Cas où une personne est mentionnee mais pas d'organisation ni de media
                                                    nouvelleInterview = new Interview(titre,listMentionPersonne,datePublication);
                                                }
                                                else{
                                                    //Cas où une personne et un media sont mentionnees mais pas d'organisation
                                                    nouvelleInterview = new Interview(listMentionMedia,titre,listMentionPersonne,"interview",datePublication);
                                                }

                                            }
                                            else{
                                                if (listMentionMedia.isEmpty()){
                                                    //Cas où une personne et une organisation sont mentionnees
                                                    nouvelleInterview = new Interview(titre,listMentionPersonne,listMentionOrganisation,datePublication);
                                                }
                                                else{
                                                    //Cas où les 3 sont mentionnes
                                                    nouvelleInterview = new Interview(listMentionMedia,titre,listMentionPersonne,listMentionOrganisation,datePublication);
                                                }
                                            }
                                            if (mentionVincentBollore){
                                                moduleSuivieVincentBollore.setListPublicationConcerne(mediaPublication,nouvelleInterview);
                                                mentionVincentBollore = false;
                                            }
                                        }

                                        mediaPublication.setListPublication(nouvelleInterview);
                                        System.out.println("L'interview " + nouvelleInterview.getTitre() + " a ete ajoute au media " + mediaPublication.getNomMedia());




                                    }
                                }
                            }
                        }
                    }
                }

            } else if (infoInstances.equals("Suivi")) {
                System.out.println("Tapez SuiviPersonne si vous voulez consulter le module de suivi de Vincent Bolloré, tapez SuiviMedia si vous voulez consulter le module de suivi du media (mettre le nom du media), tapez Vigie si vous voulez consulter la vigie : ");
                infoInstances = scanner.nextLine();
                if (infoInstances.equals("SuiviPersonne")){
                    System.out.println("Tapez Historique si vous voulez consulter l'historique des publications qui la concernent, tapez Pourcentage si vous vouler afficher le pourcentage de mentions par un certain média");
                    infoInstances = scanner.nextLine();

                    if (infoInstances.equals("Historique")){
                        System.out.println(moduleSuivieVincentBollore.afficheHistoriquePublication());
                    }
                    else if (infoInstances.equals("Pourcentage")){
                        System.out.println("Voici la liste des medias : ");
                        for (int i = 0; i < listMedia.size(); i++) {
                            System.out.println(listMedia.get(i).getNomMedia());
                        }
                        System.out.println("Pour quel media voulez-vous afficher le pourcentage de mentions de Vincent Bolloré ?");
                        infoInstances = scanner.nextLine();
                        Media mediaPourcentage = null;
                        for (int i = 0; i < listMedia.size(); i++) {
                            if (listMedia.get(i).getNomMedia().equals(infoInstances)){
                                mediaPourcentage = listMedia.get(i);
                            }
                        }

                        if (mediaPourcentage == null){
                            System.out.println("Ce media n'est pas dans la base de donnee");
                        }

                        else{
                            moduleSuivieVincentBollore.setPourcentageMentionMedia(mediaPourcentage);
                            System.out.println(moduleSuivieVincentBollore.affichePourcentageMentionMedia(mediaPourcentage));
                        }
                    }
                }
                else if (infoInstances.equals("SuiviMedia")) {
                    System.out.println("Tapez Mention si vous voulez consulter la liste du nombre de mentions de TMC, tapez Historique si vous voulez consulter un historique de l'évolution des rachats de parts de TMC");
                    infoInstances = scanner.nextLine();
                    if (infoInstances.equals("Mention")){
                        System.out.println(moduleSuivieTMC.afficheMention());
                    }
                    else if(infoInstances.equals("Historique")){
                        System.out.println(moduleSuivieTMC.getHistoriqueRachat());
                    }
                }

                else if(infoInstances.equals("Vigie")){
                    System.out.println(moduleSuivieTMC.getVigie().toString());
                }
            }
        }
        return 0;
    }
}
