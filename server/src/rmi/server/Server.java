package rmi.server;

import rmi.commons.Espece;
import rmi.commons.IAnimal;
import rmi.commons.ICabinetVeterinaire;
import rmi.server.AnimalImpl;
import rmi.server.CabinetVeterinaireImpl;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
    public static void main(String[] args) {

        // Configuration de la politique de sécurité
        System.setProperty("java.security.policy", "/home/hadil/IdeaProjects/RMI_Project_ALD/security.policy");
        System.setSecurityManager(new SecurityManager());

        System.setProperty("java.rmi.server.codebase", "file:/home/hadil/IdeaProjects/RMI_Project_ALD/out/production/client/");




        try {
            // Démarre le registre RMI sur le port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Registre RMI démarré sur le port 1099.");

            // Création du cabinet vétérinaire
            ICabinetVeterinaire cabinet = new CabinetVeterinaireImpl();


            // Lier uniquement le cabinet vétérinaire dans le registre RMI
            registry.bind("CabinetVeterinaire", cabinet);
            System.out.println("Cabinet vétérinaire publié dans le registre.");

            // Test temporaire pour déclencher des alertes en ajoutant 100 animaux
            for (int i = 0; i < 600; i++) {
                cabinet.ajouterAnimal("AnimalTest" + i, "ProprietaireTest" + i, new Espece("Chien", 10), "RaceTest" + i);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
