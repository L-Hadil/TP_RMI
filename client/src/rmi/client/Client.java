package rmi.client;

import rmi.commons.IAnimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {

        System.setProperty("java.security.policy", "/home/hadil/IdeaProjects/RMI_Project_ALD/security.policy");
        System.setSecurityManager(new SecurityManager());
        try {
            // Recherche de l'objet Animal dans le registre RMI
            Registry registry = LocateRegistry.getRegistry("localhost");
            IAnimal animal = (IAnimal) registry.lookup("Animal");

            // Appel de la méthode distante pour afficher les informations de l'animal
            System.out.println("Nom complet de l'animal : " + animal.getNomAnimal());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
