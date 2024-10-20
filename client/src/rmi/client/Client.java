package rmi.client;


import rmi.commons.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // Configuration de la politique de sécurité
        System.setProperty("java.security.policy", "/home/hadil/IdeaProjects/RMI_Project_ALD/security.policy");
        System.setSecurityManager(new SecurityManager());

        try {
            // Connexion au registre RMI pour récupérer le cabinet vétérinaire
            Registry registry = LocateRegistry.getRegistry("localhost");
            ICabinetVeterinaire cabinet = (ICabinetVeterinaire) registry.lookup("CabinetVeterinaire");

            // Créer et exporter l'objet callback
            IClientCallback clientCallback = new CallbackImpl();

            // Vérifiez si l'objet est déjà exporté
            if (RemoteObject.toStub(clientCallback) == null) {
                // Si non exporté, alors l'exporter
                IClientCallback stubCallback = (IClientCallback) UnicastRemoteObject.exportObject(clientCallback, 0);
                // Enregistrer le client pour les alertes
                cabinet.registerCallback(stubCallback);
            } else {
                // Enregistrer l'objet déjà exporté pour les alertes
                cabinet.registerCallback(clientCallback);
            }

            System.out.println("Client enregistré pour les alertes.");

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                // Affichage du menu interactif
                System.out.println("=================================");
                System.out.println("   MENU INTERACTIF VÉTÉRINAIRE   ");
                System.out.println("=================================");
                System.out.println("1. Lister tous les animaux");
                System.out.println("2. Rechercher un animal");
                System.out.println("3. Ajouter un animal");
                System.out.println("4. Modifier un animal");
                System.out.println("5. Supprimer un animal");
                System.out.println("6. Gérer le dossier de suivi d'un animal");
                System.out.println("7. Quitter");
                System.out.print("Choisissez une option : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // Consomme le saut de ligne

                switch (choix) {
                    case 1:
                        // Lister tous les animaux
                        List<IAnimal> animaux = cabinet.getAnimaux();
                        if (animaux.isEmpty()) {
                            System.out.println("Aucun animal n'est actuellement enregistré dans le cabinet.");
                        } else {
                            System.out.println("===== LISTE DES ANIMAUX =====");
                            for (IAnimal animal : animaux) {
                                System.out.println("- " + animal.getNomAnimal() + " (Propriétaire: " + animal.getProprietaire() + ")");
                            }
                        }
                        break;

                    case 2:
                        // Rechercher un animal
                        System.out.println("Entrez le nom de l'animal à rechercher :");
                        String nomAnimal = scanner.nextLine();
                        IAnimal animal = cabinet.rechercherAnimalParNom(nomAnimal);
                        if (animal != null) {
                            System.out.println("Informations sur l'animal : ");
                            System.out.println("Nom de l'animal     : " + animal.getNomAnimal());
                            System.out.println("Propriétaire        : " + animal.getProprietaire());
                            System.out.println("Espèce              : " + animal.getEspece());
                            System.out.println("Race                : " + animal.getRace());
                            System.out.println("Dossier de suivi    : " + animal.getDossierSuivi());
                        } else {
                            System.out.println("L'animal '" + nomAnimal + "' n'a pas été trouvé.");
                        }
                        break;

                    case 3:
                        // Ajouter un nouvel animal avec choix d'espèce
                        System.out.println("Ajouter un nouvel animal.");
                        System.out.print("Nom de l'animal : ");
                        String nom = scanner.nextLine();
                        System.out.print("Propriétaire : ");
                        String proprietaire = scanner.nextLine();

                        // Choisir l'espèce
                        Espece espece = null;
                        while (espece == null) {
                            System.out.println("Choisissez l'espèce : 1. Chien 2. Chat");
                            int choixEspece = scanner.nextInt();
                            scanner.nextLine();  // Consomme le saut de ligne

                            if (choixEspece == 1) {
                                espece = new Espece("Chien", 12);  // Exemple de création d'une espèce Chien
                            } else if (choixEspece == 2) {
                                espece = new Espece("Chat", 15);   // Exemple de création d'une espèce Chat
                            } else {
                                System.out.println("Choix invalide, veuillez réessayer.");
                            }
                        }

                        System.out.print("Race : ");
                        String race = scanner.nextLine();

                        // Appel de la méthode distante pour ajouter l'animal au cabinet vétérinaire
                        cabinet.ajouterAnimal(nom, proprietaire, espece, race);
                        System.out.println("Animal ajouté avec succès !");
                        break;

                    case 4:
                        // Modifier un animal
                        System.out.println("Entrez le nom de l'animal à modifier : ");
                        String nomAModifier = scanner.nextLine();
                        IAnimal animalAModifier = cabinet.rechercherAnimalParNom(nomAModifier);
                        if (animalAModifier != null) {
                            System.out.println("Entrez les nouvelles informations pour l'animal.");
                            System.out.print("Nouveau nom de l'animal : ");
                            String nouveauNom = scanner.nextLine();
                            System.out.print("Nouveau propriétaire : ");
                            String nouveauProprietaire = scanner.nextLine();
                            System.out.print("Nouvelle espèce : ");
                            String nouvelleEspece = scanner.nextLine();
                            System.out.print("Durée de vie moyenne : ");
                            int nouvelleDureeVie = scanner.nextInt();
                            scanner.nextLine();  // Consomme le saut de ligne
                            System.out.print("Nouvelle race : ");
                            String nouvelleRace = scanner.nextLine();

                            // Modifier les attributs de l'animal existant
                            animalAModifier.setNomAnimal(nouveauNom);
                            animalAModifier.setProprietaire(nouveauProprietaire);

                            Espece nouvelleEspeceObjet = new Espece(nouvelleEspece, nouvelleDureeVie);
                            animalAModifier.setEspece(nouvelleEspeceObjet);

                            animalAModifier.setRace(nouvelleRace);

                            System.out.println("Animal modifié avec succès !");
                        } else {
                            System.out.println("L'animal '" + nomAModifier + "' n'a pas été trouvé.");
                        }
                        break;

                    case 5:
                        // Supprimer un animal
                        System.out.println("Entrez le nom de l'animal à supprimer : ");
                        String nomASupprimer = scanner.nextLine();
                        IAnimal animalASupprimer = cabinet.rechercherAnimalParNom(nomASupprimer);
                        if (animalASupprimer != null) {
                            cabinet.supprimerAnimal(nomASupprimer);
                            System.out.println("Animal '" + nomASupprimer + "' supprimé avec succès !");
                        } else {
                            System.out.println("L'animal '" + nomASupprimer + "' n'a pas été trouvé.");
                        }
                        break;

                    case 6:
                        // Gérer le dossier de suivi d'un animal
                        System.out.println("Entrez le nom de l'animal pour gérer son dossier de suivi :");
                        String nomAnimalSuivi = scanner.nextLine();
                        IAnimal animalSuivi = cabinet.rechercherAnimalParNom(nomAnimalSuivi);
                        if (animalSuivi != null) {
                            System.out.println("===== DOSSIER DE SUIVI =====");
                            System.out.println(animalSuivi.getDossierSuivi());

                            System.out.println("Voulez-vous ajouter une nouvelle note au dossier ? (oui/non) :");
                            String reponse = scanner.nextLine();
                            if (reponse.equalsIgnoreCase("oui")) {
                                System.out.println("Entrez la nouvelle note pour le dossier de suivi : ");
                                String nouvelleNote = scanner.nextLine();
                                animalSuivi.ajouterSuivi(nouvelleNote);
                                System.out.println("Nouvelle note ajoutée au dossier de suivi.");
                            }
                        } else {
                            System.out.println("L'animal '" + nomAnimalSuivi + "' n'a pas été trouvé.");
                        }
                        break;

                    case 7:
                        // Quitter l'application
                        System.out.println("Au revoir !");
                        running = false;
                        break;

                    default:
                        System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
