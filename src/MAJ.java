import java.util.List;

/**
 * La classe {@code MAJ} met à jour les personnes/organisations possedant des organisations/medias en gerant les rachats de parts.
 * Elle gère aussi les erreurs eventuelle lors des rachats (pourcentage de rachat superieur à celui que possede le vendeur, pourcentage de rachat negatif ou nul etc...)
 */

public class MAJ {
    /** liste des medias disponibles dans la base de donnee*/
    private List<Media> listMedia;

    /** listOrganisation liste des organsisations dans la base de donnee*/
    private List<Organisation> listOrganisation;

    /** liste des personnes disponibles dans la base de donnee*/
    private List<Personnalite>  listPersonnalite;


    /**
     * Seul constructeur de la classe, prend en paramètre la liste de chacune des entites
     * utiles pour le rachat ( Media, Organisation et Personne)
     * @param listMedia liste des medias disponibles dans la base de donnee
     * @param listOrganisation liste des organsisations dans la base de donnee
     * @param listPersonnalite liste des personnes disponibles dans la base de donnee
     */
    public MAJ(List<Media> listMedia, List<Organisation> listOrganisation, List<Personnalite> listPersonnalite){
        this.listMedia = listMedia;
        this.listOrganisation = listOrganisation;
        this.listPersonnalite = listPersonnalite;
    }

    // --------------------- GETTERS -----------------------

    public List<Media> getListMedia(){
        return listMedia;
    }

    public List<Organisation> getListOrganisation(){
        return listOrganisation;
    }

    public List<Personnalite> getListPersonnalite(){
        return listPersonnalite;
    }


    // --------------------- RACHATS -----------------------

    /**
     *Cas où une personne rachète des parts d'un media à une autre personne
     * @param nomAcheteur String représentant le nom de la personne acheteuse
     * @param nomVendeur String représentant le nom de la personne vendeuse
     * @param nomMedia String représentant le nom du media acheté
     * @param pourcentageRachat nombre représentant le pourcentage de rachat du média
     */
    public void rachatPersonnalitePersonnaliteMedia(String nomAcheteur, String nomVendeur, String nomMedia, float pourcentageRachat){
        if(pourcentageRachat<=0.0){
            System.out.println("Le pourcentage doit etre positif !");
        }

        else if(pourcentageRachat>100.0){
            System.out.println("Le pourcentage ne peut pas etre superieur a 100 !");
        }

        else{


            //Verfication que l'acheteur existe
            Personnalite acheteur = null;
            for (int i = 0; i < listPersonnalite.size(); i++) {
                if(listPersonnalite.get(i).getNomPersonnalite().equals(nomAcheteur)){
                    acheteur = listPersonnalite.get(i);
                }
            }


            //Verfication que le vendeur existe
            Personnalite vendeur = null;
            for (int i = 0; i < listPersonnalite.size(); i++) {
                if(listPersonnalite.get(i).getNomPersonnalite().equals(nomVendeur)){
                    vendeur = listPersonnalite.get(i);
                }
            }

            //Verfication que le media existe
            Media media = null;
            for (int i = 0; i < listMedia.size(); i++) {
                if(listMedia.get(i).getNomMedia().equals(nomMedia)){
                    media = listMedia.get(i);
                }
            }


            if (acheteur == null){
                System.out.println("L'acheteur " + nomAcheteur + " n'est pas present au sein de la base de donnee");
            } else if (vendeur == null) {
                System.out.println("Le vendeur " + nomVendeur + " n'est pas present au sein de la base de donnee");
            } else if (media == null) {
                System.out.println("Le media " + nomMedia + " n'est pas present au sein de la base de donnee");
            }

            else{

                if(vendeur.getPossedeMedia().get(media) == null){
                    if(vendeur.getQualificatifOrganisation().equals("contrôle")){
                        System.out.println("L'organisation " + vendeur.getNomPersonnalite() + " contrôle le media " + media.getNomMedia() + ", le rachat est donc impossible" );
                    }
                }


                //Verification que le vendeur possede bien le media
                else if(vendeur.afficheMediaPossede(media).equals(vendeur.getNomPersonnalite() + " ne possede pas ce media.")){
                    System.out.println(vendeur.afficheMediaPossede(media));
                }

                else{
                    //cas où l'acheteur veut racheter plus de part que ce que possede le vendeur
                    if(vendeur.getPossedeMedia().get(media) < pourcentageRachat){
                        System.out.println(vendeur.getNomPersonnalite() + " possede moins de " + pourcentageRachat + "% du media " + media.getNomMedia());
                        System.out.println("Rachat impossible");
                    }

                    else {

                        float ancienneValeur;
                        //Cas où l'acheteur possede deja des parts dans le media
                        if(acheteur.getPossedeMedia().containsKey(media)){
                            ancienneValeur = acheteur.getPossedeMedia().get(media);
                            acheteur.setPossedeMedia(media,ancienneValeur + pourcentageRachat);
                        }

                        else{
                            acheteur.setPossedeMedia(media,pourcentageRachat);
                        }


                        ancienneValeur = vendeur.getPossedeMedia().get(media);
                        vendeur.setPossedeMedia(media,ancienneValeur - pourcentageRachat);
                        System.out.println(nomAcheteur + " rachete " + pourcentageRachat + "% du media " + nomMedia + " à " + nomVendeur);

                    }

                }


            }


        }


    }


    /**
     *Cas où une personne rachète des parts d'un media à une organisation
     * @param nomAcheteur String représentant le nom de la personne acheteuse
     * @param nomVendeur String représentant le nom de l'organisation vendeuse
     * @param nomMedia String représentant le nom du media acheté
     * @param pourcentageRachat nombre représentant le pourcentage de rachat du média
     */
    public void rachatPersonnaliteOrganisationMedia(String nomAcheteur, String nomVendeur, String nomMedia, float pourcentageRachat){



        if(pourcentageRachat<=0.0){
            System.out.println("Le pourcentage doit etre positif !");
        }

        else if(pourcentageRachat>100.0){
            System.out.println("Le pourcentage ne peut pas etre superieur a 100 !");
        }


        else{

            //Verfication que l'acheteur existe
            Personnalite acheteur = null;
            for (int i = 0; i < listPersonnalite.size(); i++) {
                if(listPersonnalite.get(i).getNomPersonnalite().equals(nomAcheteur)){
                    acheteur = listPersonnalite.get(i);
                }
            }


            //Verfication que le vendeur existe
            Organisation vendeur = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomVendeur)){
                    vendeur = listOrganisation.get(i);
                }
            }

            //Verfication que le media existe
            Media media = null;
            for (int i = 0; i < listMedia.size(); i++) {
                if(listMedia.get(i).getNomMedia().equals(nomMedia)){
                    media = listMedia.get(i);
                }
            }


            if (acheteur == null){
                System.out.println("L'acheteur " + nomAcheteur + " n'est pas present au sein de la base de donnee");
            } else if (vendeur == null) {
                System.out.println("Le vendeur " + nomVendeur + " n'est pas present au sein de la base de donnee");
            } else if (media == null) {
                System.out.println("Le media " + nomMedia + " n'est pas present au sein de la base de donnee");
            }

            else{


                if(vendeur.getPossedeMedia().get(media) == null){
                    if(vendeur.getQualificatifOrganisation().equals("contrôle")){
                        System.out.println("L'organisation " + vendeur.getNomOrganisation() + " contrôle le media " + media.getNomMedia() + ", le rachat est donc impossible" );
                    }
                }

                //Verification que le vendeur possede bien le media
                else if(vendeur.afficheMediaPossede(media).equals(vendeur.getNomOrganisation() + " ne possede pas ce media.")){
                    System.out.println(vendeur.afficheMediaPossede(media));
                }

                else{

                    //rajouter d'autre message d'erreur si le pourcentage est superieur à 100% ou inferieur ou egale à 0%

                    //cas où l'acheteur veut racheter plus de part que ce que possede le vendeur
                    if(vendeur.getPossedeMedia().get(media) < pourcentageRachat){
                        System.out.println(vendeur.getNomOrganisation() + " possede moins de " + pourcentageRachat + "% du media " + media.getNomMedia());
                        System.out.println("Rachat impossible");
                    }

                    else {

                        float ancienneValeur;
                        //Cas où l'acheteur possede deja des parts dans le media
                        if(acheteur.getPossedeMedia().containsKey(media)){
                            ancienneValeur = acheteur.getPossedeMedia().get(media);
                            acheteur.setPossedeMedia(media,ancienneValeur + pourcentageRachat);
                        }

                        else{
                            acheteur.setPossedeMedia(media,pourcentageRachat);
                        }


                        ancienneValeur = vendeur.getPossedeMedia().get(media);
                        vendeur.setPossedeMedia(media,ancienneValeur - pourcentageRachat);
                        System.out.println(nomAcheteur + " rachete " + pourcentageRachat + "% du media " + nomMedia + " à l'organisation " + nomVendeur);

                    }

                }


            }



        }


    }






    /**
     *Cas où une organisation rachète des parts d'un media à une autre organisation
     * @param nomAcheteur String représentant le nom de l'organisation acheteuse
     * @param nomVendeur String représentant le nom de l'organisation vendeuse
     * @param nomMedia String représentant le nom du media acheté
     * @param pourcentageRachat nombre représentant le pourcentage de rachat du média
     */
    public void rachatOrganisationOrganisationMedia(String nomAcheteur, String nomVendeur, String nomMedia, float pourcentageRachat){



        if(pourcentageRachat<=0.0){
            System.out.println("Le pourcentage doit etre positif !");
        }

        else if(pourcentageRachat>100.0){
            System.out.println("Le pourcentage ne peut pas etre superieur a 100 !");
        }



        else{

            //Verfication que l'acheteur existe
            Organisation acheteur = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomAcheteur)){
                    acheteur = listOrganisation.get(i);
                }
            }


            //Verfication que le vendeur existe
            Organisation vendeur = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomVendeur)){
                    vendeur = listOrganisation.get(i);
                }
            }

            //Verfication que le media existe
            Media media = null;
            for (int i = 0; i < listMedia.size(); i++) {
                if(listMedia.get(i).getNomMedia().equals(nomMedia)){
                    media = listMedia.get(i);
                }
            }


            if (acheteur == null){
                System.out.println("L'acheteur " + nomAcheteur + " n'est pas present au sein de la base de donnee");
            } else if (vendeur == null) {
                System.out.println("Le vendeur " + nomVendeur + " n'est pas present au sein de la base de donnee");
            } else if (media == null) {
                System.out.println("Le media " + nomMedia + " n'est pas present au sein de la base de donnee");
            }

            else{

                if(vendeur.getPossedeMedia().get(media) == null){
                    if(vendeur.getQualificatifOrganisation().equals("contrôle")){
                        System.out.println("L'organisation " + vendeur.getNomOrganisation() + " contrôle le media " + media.getNomMedia() + ", le rachat est donc impossible" );
                    }
                }


                //Verification que le vendeur possede bien le media
                else if(vendeur.afficheMediaPossede(media).equals(vendeur.getNomOrganisation() + " ne possede pas ce media.")){
                    System.out.println(vendeur.afficheMediaPossede(media));
                }

                else{

                    //rajouter d'autre message d'erreur si le pourcentage est superieur à 100% ou inferieur ou egale à 0%

                    //cas où l'acheteur veut racheter plus de part que ce que possede le vendeur
                    if(vendeur.getPossedeMedia().get(media) < pourcentageRachat){
                        System.out.println(vendeur.getNomOrganisation() + " possede moins de " + pourcentageRachat + "% du media " + media.getNomMedia());
                        System.out.println("Rachat impossible");
                    }

                    else {

                        float ancienneValeur;
                        //Cas où l'acheteur possede deja des parts dans le media
                        if(acheteur.getPossedeMedia().containsKey(media)){
                            ancienneValeur = acheteur.getPossedeMedia().get(media);
                            acheteur.setPossedeMedia(media,ancienneValeur + pourcentageRachat);
                        }

                        else{
                            acheteur.setPossedeMedia(media,pourcentageRachat);
                        }


                        ancienneValeur = vendeur.getPossedeMedia().get(media);
                        vendeur.setPossedeMedia(media,ancienneValeur - pourcentageRachat);
                        System.out.println("L'organisation " + nomAcheteur + " rachete " + pourcentageRachat + "% du media " + nomMedia + " à l'organisation " + nomVendeur);

                    }

                }


            }

        }



    }





    /**
     *Cas où une personne rachète des parts d'un media à une organisation
     * @param nomAcheteur String représentant le nom de l'organisation acheteuse
     * @param nomVendeur String représentant le nom de la personne vendeuse
     * @param nomMedia String représentant le nom du media acheté
     * @param pourcentageRachat nombre représentant le pourcentage de rachat du média
     */
    public void rachatOrganisationPersonnaliteMedia(String nomAcheteur, String nomVendeur, String nomMedia, float pourcentageRachat){



        if(pourcentageRachat<=0.0){
            System.out.println("Le pourcentage doit etre positif !");
        }

        else if(pourcentageRachat>100.0){
            System.out.println("Le pourcentage ne peut pas etre superieur a 100 !");
        }



        else{

            //Verfication que l'acheteur existe
            Organisation acheteur = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomAcheteur)){
                    acheteur = listOrganisation.get(i);
                }
            }


            //Verfication que le vendeur existe
            Personnalite vendeur = null;
            for (int i = 0; i < listPersonnalite.size(); i++) {
                if(listPersonnalite.get(i).getNomPersonnalite().equals(nomVendeur)){
                    vendeur = listPersonnalite.get(i);
                }
            }

            //Verfication que le media existe
            Media media = null;
            for (int i = 0; i < listMedia.size(); i++) {
                if(listMedia.get(i).getNomMedia().equals(nomMedia)){
                    media = listMedia.get(i);
                }
            }


            if (acheteur == null){
                System.out.println("L'acheteur " + nomAcheteur + " n'est pas present au sein de la base de donnee");
            } else if (vendeur == null) {
                System.out.println("Le vendeur " + nomVendeur + " n'est pas present au sein de la base de donnee");
            } else if (media == null) {
                System.out.println("Le media " + nomMedia + " n'est pas present au sein de la base de donnee");
            }

            else{

                if(vendeur.getPossedeMedia().get(media) == null){
                    if(vendeur.getQualificatifOrganisation().equals("contrôle")){
                        System.out.println(vendeur.getNomPersonnalite() + " contrôle le media " + media.getNomMedia() + ", le rachat est donc impossible" );
                    }
                }


                //Verification que le vendeur possede bien le media
                else if(vendeur.afficheMediaPossede(media).equals(vendeur.getNomPersonnalite() + " ne possede pas ce media.")){
                    System.out.println(vendeur.afficheMediaPossede(media));
                }

                else{

                    //rajouter d'autre message d'erreur si le pourcentage est superieur à 100% ou inferieur ou egale à 0%

                    //cas où l'acheteur veut racheter plus de part que ce que possede le vendeur
                    if(vendeur.getPossedeMedia().get(media) < pourcentageRachat){
                        System.out.println(vendeur.getNomPersonnalite() + " possede moins de " + pourcentageRachat + "% du media " + media.getNomMedia());
                        System.out.println("Rachat impossible");
                    }

                    else {

                        float ancienneValeur;
                        //Cas où l'acheteur possede deja des parts dans le media
                        if(acheteur.getPossedeMedia().containsKey(media)){
                            ancienneValeur = acheteur.getPossedeMedia().get(media);
                            acheteur.setPossedeMedia(media,ancienneValeur + pourcentageRachat);
                        }

                        else{
                            acheteur.setPossedeMedia(media,pourcentageRachat);
                        }


                        ancienneValeur = vendeur.getPossedeMedia().get(media);
                        vendeur.setPossedeMedia(media,ancienneValeur - pourcentageRachat);
                        System.out.println("L'organisation " + nomAcheteur + " rachete " + pourcentageRachat + "% du media " + nomMedia + " à " + nomVendeur);

                    }

                }


            }

        }



    }







    /**
     *Cas où une personne rachète des parts d'une organisation à une personne
     * @param nomAcheteur String représentant le nom de la personne acheteuse
     * @param nomVendeur String représentant le nom de la personne vendeuse
     * @param nomOrganisation String représentant le nom de l'organisation achetée
     * @param pourcentageRachat nombre représentant le pourcentage de rachat de l'organisation
     */
    public void rachatPersonnalitePersonnaliteOrganisation(String nomAcheteur, String nomVendeur, String nomOrganisation, float pourcentageRachat){


        if(pourcentageRachat<=0.0){
            System.out.println("Le pourcentage doit etre positif !");
        }

        else if(pourcentageRachat>100.0){
            System.out.println("Le pourcentage ne peut pas etre superieur a 100 !");
        }



        else{


            //Verfication que l'acheteur existe
            Personnalite acheteur = null;
            for (int i = 0; i < listPersonnalite.size(); i++) {
                if(listPersonnalite.get(i).getNomPersonnalite().equals(nomAcheteur)){
                    acheteur = listPersonnalite.get(i);
                }
            }


            //Verfication que le vendeur existe
            Personnalite vendeur = null;
            for (int i = 0; i < listPersonnalite.size(); i++) {
                if(listPersonnalite.get(i).getNomPersonnalite().equals(nomVendeur)){
                    vendeur = listPersonnalite.get(i);
                }
            }

            //Verfication que le media existe
            Organisation organisation = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomOrganisation)){
                    organisation = listOrganisation.get(i);
                }
            }


            if (acheteur == null){
                System.out.println("L'acheteur " + nomAcheteur + " n'est pas present au sein de la base de donnee");
            } else if (vendeur == null) {
                System.out.println("Le vendeur " + nomVendeur + " n'est pas present au sein de la base de donnee");
            } else if (organisation == null) {
                System.out.println("L'organisation " + nomOrganisation + " n'est pas present au sein de la base de donnee");
            }

            else{


                //Verification que le vendeur possede bien le media
                if(vendeur.afficheOrganisationPossede(organisation).equals(vendeur.getNomPersonnalite() + " ne possede pas cette organisation.")){
                    System.out.println(vendeur.afficheOrganisationPossede(organisation));
                }

                else{

                    if(vendeur.getPossedeOrganisation().get(organisation) == null){
                        if(vendeur.getQualificatifOrganisation().equals("contrôle")){
                            System.out.println(vendeur.getNomPersonnalite() + " contrôle l'organisation " + organisation.getNomOrganisation() + ", le rachat est donc impossible" );
                        }
                        else if(vendeur.getQualificatifOrganisation().equals("participe")){
                            System.out.println(vendeur.getNomPersonnalite() + " a des participations dans l'organisation " + organisation.getNomOrganisation() + ", le rachat est donc impossible" );
                        }
                    }


                    //cas où l'acheteur veut racheter plus de part que ce que possede le vendeur
                    else if(vendeur.getPossedeOrganisation().get(organisation) < pourcentageRachat){
                        System.out.println(vendeur.getNomPersonnalite() + " possede moins de " + pourcentageRachat + "% de l'organisation " + organisation.getNomOrganisation());
                        System.out.println("Rachat impossible");
                    }

                    else {

                        float ancienneValeur;
                        //Cas où l'acheteur possede deja des parts dans le media
                        if(acheteur.getPossedeOrganisation().containsKey(organisation)){
                            ancienneValeur = acheteur.getPossedeOrganisation().get(organisation);
                            acheteur.setPossedeOrganisation(organisation,ancienneValeur + pourcentageRachat);
                        }

                        else{
                            acheteur.setPossedeOrganisation(organisation,pourcentageRachat);
                        }


                        ancienneValeur = vendeur.getPossedeOrganisation().get(organisation);
                        vendeur.setPossedeOrganisation(organisation,ancienneValeur - pourcentageRachat);
                        System.out.println(nomAcheteur + " rachete " + pourcentageRachat + "% de l'organisation " + nomOrganisation + " à " + nomVendeur);

                    }

                }


            }


        }


    }







    /**
     *Cas où une personne rachète des parts d'une organisation à une organisation
     * @param nomAcheteur String représentant le nom de la personne acheteuse
     * @param nomVendeur String représentant le nom de l'organisation vendeuse
     * @param nomOrganisation String représentant le nom de l'organisation achetée
     * @param pourcentageRachat nombre représentant le pourcentage de rachat de l'organisation
     */
    public void rachatPersonnaliteOrganisationOrganisation(String nomAcheteur, String nomVendeur, String nomOrganisation, float pourcentageRachat){


        if(pourcentageRachat<=0.0){
            System.out.println("Le pourcentage doit etre positif !");
        }

        else if(pourcentageRachat>100.0){
            System.out.println("Le pourcentage ne peut pas etre superieur a 100 !");
        }



        else{

            //Verfication que l'acheteur existe
            Personnalite acheteur = null;
            for (int i = 0; i < listPersonnalite.size(); i++) {
                if(listPersonnalite.get(i).getNomPersonnalite().equals(nomAcheteur)){
                    acheteur = listPersonnalite.get(i);
                }
            }


            //Verfication que le vendeur existe
            Organisation vendeur = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomVendeur)){
                    vendeur = listOrganisation.get(i);
                }
            }

            //Verfication que le media existe
            Organisation organisation = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomOrganisation)){
                    organisation = listOrganisation.get(i);
                }
            }


            if (acheteur == null){
                System.out.println("L'acheteur " + nomAcheteur + " n'est pas present au sein de la base de donnee");
            } else if (vendeur == null) {
                System.out.println("Le vendeur " + nomVendeur + " n'est pas present au sein de la base de donnee");
            } else if (organisation == null) {
                System.out.println("L'organisation " + nomOrganisation + " n'est pas present au sein de la base de donnee");
            }

            else{

                if(vendeur.getPossedeOrganisation().get(organisation) == null){
                    if(vendeur.getQualificatifOrganisation().equals("contrôle")){
                        System.out.println("L'organisation " + vendeur.getNomOrganisation() + " contrôle l'organisation " + organisation.getNomOrganisation() + ", le rachat est donc impossible" );
                    }
                    else if(vendeur.getQualificatifOrganisation().equals("participe")){
                        System.out.println("L'organisation " + vendeur.getNomOrganisation() + " a des participations dans l'organisation " + organisation.getNomOrganisation() + ", le rachat est donc impossible" );
                    }
                }


                //Verification que le vendeur possede bien le media
                else if(vendeur.afficheOrganisationPossede(organisation).equals("L'organisation " + vendeur.getNomOrganisation() + " ne possede pas cette organisation.")){
                    System.out.println(vendeur.afficheOrganisationPossede(organisation));
                }

                else{
                    //cas où l'acheteur veut racheter plus de part que ce que possede le vendeur
                    if(vendeur.getPossedeOrganisation().get(organisation) < pourcentageRachat){
                        System.out.println(vendeur.getNomOrganisation() + " possede moins de " + pourcentageRachat + "% de l'organisation " + organisation.getNomOrganisation());
                        System.out.println("Rachat impossible");
                    }

                    else {

                        float ancienneValeur;
                        //Cas où l'acheteur possede deja des parts dans le media
                        if(acheteur.getPossedeOrganisation().containsKey(organisation)){
                            ancienneValeur = acheteur.getPossedeOrganisation().get(organisation);
                            acheteur.setPossedeOrganisation(organisation,ancienneValeur + pourcentageRachat);
                        }

                        else{
                            acheteur.setPossedeOrganisation(organisation,pourcentageRachat);
                        }


                        ancienneValeur = vendeur.getPossedeOrganisation().get(organisation);
                        vendeur.setPossedeOrganisation(organisation,ancienneValeur - pourcentageRachat);
                        System.out.println(nomAcheteur + " rachete " + pourcentageRachat + "% de l'organisation " + nomOrganisation + " à l'organisation " + nomVendeur);

                    }

                }


            }


        }


    }





    /**
     *Cas où une organisation rachète des parts d'une organisation à une autre organisation
     * @param nomAcheteur String représentant le nom de l'organisation acheteuse
     * @param nomVendeur String représentant le nom de l'organisation vendeuse
     * @param nomOrganisation String représentant le nom de l'organisation achetée
     * @param pourcentageRachat nombre représentant le pourcentage de rachat de l'organisation
     */
    public void rachatOrganisationOrganisationOrganisation(String nomAcheteur, String nomVendeur, String nomOrganisation, float pourcentageRachat){



        if(pourcentageRachat<=0.0){
            System.out.println("Le pourcentage doit etre positif !");
        }

        else if(pourcentageRachat>100.0){
            System.out.println("Le pourcentage ne peut pas etre superieur a 100 !");
        }



        else{


            //Verfication que l'acheteur existe
            Organisation acheteur = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomAcheteur)){
                    acheteur = listOrganisation.get(i);
                }
            }


            //Verfication que le vendeur existe
            Organisation vendeur = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomVendeur)){
                    vendeur = listOrganisation.get(i);
                }
            }

            //Verfication que le media existe
            Organisation organisation = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomOrganisation)){
                    organisation = listOrganisation.get(i);
                }
            }


            if (acheteur == null){
                System.out.println("L'acheteur " + nomAcheteur + " n'est pas present au sein de la base de donnee");
            } else if (vendeur == null) {
                System.out.println("Le vendeur " + nomVendeur + " n'est pas present au sein de la base de donnee");
            } else if (organisation == null) {
                System.out.println("L'organisation " + nomOrganisation + " n'est pas present au sein de la base de donnee");
            }

            else{


                //Verification que le vendeur possede bien le media
                if(vendeur.afficheOrganisationPossede(organisation).equals("L'organisation " + vendeur.getNomOrganisation() + " ne possede pas cette organisation.")){
                    System.out.println(vendeur.afficheOrganisationPossede(organisation));
                }

                else{

                    if(vendeur.getPossedeOrganisation().get(organisation) == null){
                        if(vendeur.getQualificatifOrganisation().equals("contrôle")){
                            System.out.println("L'organisation " + vendeur.getNomOrganisation() + " contrôle l'organisation " + organisation.getNomOrganisation() + ", le rachat est donc impossible" );
                        }
                    }
                    //cas où l'acheteur veut racheter plus de part que ce que possede le vendeur
                    else if(vendeur.getPossedeOrganisation().get(organisation) < pourcentageRachat){
                        System.out.println(vendeur.getNomOrganisation() + " possede moins de " + pourcentageRachat + "% de l'organisation " + organisation.getNomOrganisation());
                        System.out.println("Rachat impossible");
                    }

                    else {

                        float ancienneValeur;
                        //Cas où l'acheteur possede deja des parts dans le media
                        if(acheteur.getPossedeOrganisation().containsKey(organisation)){
                            ancienneValeur = acheteur.getPossedeOrganisation().get(organisation);
                            acheteur.setPossedeOrganisation(organisation,ancienneValeur + pourcentageRachat);
                        }

                        else{
                            acheteur.setPossedeOrganisation(organisation,pourcentageRachat);
                        }


                        ancienneValeur = vendeur.getPossedeOrganisation().get(organisation);
                        vendeur.setPossedeOrganisation(organisation,ancienneValeur - pourcentageRachat);
                        System.out.println("L'organisation " + nomAcheteur + " rachete " + pourcentageRachat + "% de l'organisation " + nomOrganisation + " à l'organisation " + nomVendeur);

                    }

                }


            }

        }



    }









    /**
     *Cas où une organisation rachète des parts d'une organisation à une personne
     * @param nomAcheteur String représentant le nom de l'organisation acheteuse
     * @param nomVendeur String représentant le nom de la personne vendeuse
     * @param nomOrganisation String représentant le nom de l'organisation achetée
     * @param pourcentageRachat nombre représentant le pourcentage de rachat de l'organisation
     */
    public void rachatOrganisationPersonnaliteOrganisation(String nomAcheteur, String nomVendeur, String nomOrganisation, float pourcentageRachat){



        if(pourcentageRachat<=0.0){
            System.out.println("Le pourcentage doit etre positif !");
        }

        else if(pourcentageRachat>100.0){
            System.out.println("Le pourcentage ne peut pas etre superieur a 100 !");
        }



        else{

            //Verfication que l'acheteur existe
            Organisation acheteur = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomAcheteur)){
                    acheteur = listOrganisation.get(i);
                }
            }


            //Verfication que le vendeur existe
            Personnalite vendeur = null;
            for (int i = 0; i < listPersonnalite.size(); i++) {
                if(listPersonnalite.get(i).getNomPersonnalite().equals(nomVendeur)){
                    vendeur = listPersonnalite.get(i);
                }
            }

            //Verfication que le media existe
            Organisation organisation = null;
            for (int i = 0; i < listOrganisation.size(); i++) {
                if(listOrganisation.get(i).getNomOrganisation().equals(nomOrganisation)){
                    organisation = listOrganisation.get(i);
                }
            }


            if (acheteur == null){
                System.out.println("L'acheteur " + nomAcheteur + " n'est pas present au sein de la base de donnee");
            } else if (vendeur == null) {
                System.out.println("Le vendeur " + nomVendeur + " n'est pas present au sein de la base de donnee");
            } else if (organisation == null) {
                System.out.println("L'organisation " + nomOrganisation + " n'est pas present au sein de la base de donnee");
            }

            else{

                if(vendeur.getPossedeOrganisation().get(organisation) == null){
                    if(vendeur.getQualificatifOrganisation().equals("contrôle")){
                        System.out.println(vendeur.getNomPersonnalite() + " contrôle l'organisation " + organisation.getNomOrganisation() + ", le rachat est donc impossible" );
                    }
                    else if(vendeur.getQualificatifOrganisation().equals("participe")){
                        System.out.println(vendeur.getNomPersonnalite() + " a des participations dans l'organisation " + organisation.getNomOrganisation() + ", le rachat est donc impossible" );
                    }
                }


                //Verification que le vendeur possede bien le media
                else if(vendeur.afficheOrganisationPossede(organisation).equals("L'organisation " + vendeur.getNomPersonnalite() + " ne possede pas cette organisation.")){
                    System.out.println(vendeur.afficheOrganisationPossede(organisation));
                }

                else{
                    //cas où l'acheteur veut racheter plus de part que ce que possede le vendeur
                    if(vendeur.getPossedeOrganisation().get(organisation) < pourcentageRachat){
                        System.out.println(vendeur.getNomPersonnalite() + " possede moins de " + pourcentageRachat + "% de l'organisation " + organisation.getNomOrganisation());
                        System.out.println("Rachat impossible");
                    }

                    else {

                        float ancienneValeur;
                        //Cas où l'acheteur possede deja des parts dans le media
                        if(acheteur.getPossedeOrganisation().containsKey(organisation)){
                            ancienneValeur = acheteur.getPossedeOrganisation().get(organisation);
                            acheteur.setPossedeOrganisation(organisation,ancienneValeur + pourcentageRachat);
                        }

                        else{
                            acheteur.setPossedeOrganisation(organisation,pourcentageRachat);
                        }


                        ancienneValeur = vendeur.getPossedeOrganisation().get(organisation);
                        vendeur.setPossedeOrganisation(organisation,ancienneValeur - pourcentageRachat);
                        System.out.println("L'organisation " + nomAcheteur + " rachete " + pourcentageRachat + "% de l'organisation " + nomOrganisation + " à " + nomVendeur);

                    }

                }


            }
        }


    }





}
