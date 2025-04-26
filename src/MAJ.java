import java.util.List;

public class MAJ {
    private List<Media> listMedia;
    private List<Organisation> listOrganisation;
    private List<Personnalite>  listPersonnalite;

    public MAJ(List<Media> listMedia, List<Organisation> listOrganisation, List<Personnalite> listPersonnalite){
        this.listMedia = listMedia;
        this.listOrganisation = listOrganisation;
        this.listPersonnalite = listPersonnalite;
    }

    public List<Media> getListMedia(){
        return listMedia;
    }

    public List<Organisation> getListOrganisation(){
        return listOrganisation;
    }

    public List<Personnalite> getListPersonnalite(){
        return listPersonnalite;
    }

    //Cas où une personne rachete un media à une autre personne
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


    //Cas où une personne rachete un media à une organisation
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






    //Cas où une organisation rachete un media à une organisation
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





    //Cas où une organisation rachete un media à une personne
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







    //cas où une personne rachete des parts d'une organisations à une autre personne
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







    //cas où une personne rachete des parts d'une organisations à une autre personne
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





    //cas où une personne rachete des parts d'une organisations à une autre personne
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









    //cas où une personne rachete des parts d'une organisations à une autre personne
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
