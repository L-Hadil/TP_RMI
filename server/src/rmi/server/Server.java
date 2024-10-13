package rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

     public static void main(String[] args) {
         System.setProperty("java.security.policy", "/home/hadil/IdeaProjects/RMI_Project_ALD/security.policy");
         System.setSecurityManager(new SecurityManager());
         try {
             AnimalImpl animal = new AnimalImpl("laika","hadil","berger");

             Registry registry = LocateRegistry.createRegistry(1099);
             registry.bind("Animal", animal);
             System.out.println("Serveur prêt.");

         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}