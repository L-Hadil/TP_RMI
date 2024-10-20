package rmi.server;

import rmi.commons.Espece;
import rmi.commons.IAnimal;
import rmi.commons.ICabinetVeterinaire;
import rmi.commons.IClientCallback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CabinetVeterinaireImpl extends UnicastRemoteObject implements ICabinetVeterinaire {
    private HashMap<String, IAnimal> animaux;
    private List<IClientCallback> callbacks;

    public CabinetVeterinaireImpl() throws RemoteException {
        animaux = new HashMap<>();
        callbacks = new CopyOnWriteArrayList<>();
    }

    // Méthode pour enregistrer un client
    public void registerCallback(IClientCallback client) throws RemoteException {
        callbacks.add(client);
        System.out.println("Client enregistré pour les alertes.");
    }

    private void notifyClients(String message) throws RemoteException {
        for (IClientCallback client : callbacks) {
            System.out.println("Envoi de la notification: " + message);  // Log de débogage
            client.notify(message);  // Envoyer la notification au client
            System.out.println("Notification envoyée au client: " + client);  // Log après notification
        }
    }



    // Méthode pour vérifier les seuils et envoyer des notifications
    private void verifierEtNotifierSeuil() throws RemoteException {
        int nbAnimaux = animaux.size();
        System.out.println("Nombre d'animaux actuel : " + nbAnimaux);  // Débogage pour voir la taille actuelle
        if (nbAnimaux == 100 || nbAnimaux == 500 || nbAnimaux == 1000) {
            notifyClients("Le cabinet vétérinaire a atteint " + nbAnimaux + " animaux.");
        }
    }

    @Override
    public void ajouterAnimal(IAnimal animal) throws RemoteException {
        animaux.put(animal.getNomAnimal(), animal);
        System.out.println("Animal ajouté : " + animal.getNomAnimal());  // Message de confirmation
        verifierEtNotifierSeuil();  // Vérifier les seuils après ajout
    }

    @Override
    public IAnimal rechercherAnimalParNom(String nom) throws RemoteException {
        return animaux.get(nom);
    }

    @Override
    public List<IAnimal> getAnimaux() throws RemoteException {
        return new ArrayList<>(animaux.values());
    }

    @Override
    public void modifierAnimal(String nom, IAnimal animalModifie) throws RemoteException {
        if (animaux.containsKey(nom)) {
            animaux.put(nom, animalModifie);
            System.out.println("Animal modifié : " + nom);  // Ajout de message de débogage
        }
    }

    @Override
    public void supprimerAnimal(String nom) throws RemoteException {
        animaux.remove(nom);
        System.out.println("Animal supprimé : " + nom);  // Message de confirmation
        verifierEtNotifierSeuil();  // Vérifier les seuils après suppression
    }

    @Override
    public void ajouterAnimal(String nom, String proprietaire, Espece espece, String race) throws RemoteException {
        IAnimal nouvelAnimal = new AnimalImpl(nom, proprietaire, espece, race);
        animaux.put(nom, nouvelAnimal);
        System.out.println("Animal ajouté via paramètres : " + nom);  // Débogage pour confirmer l'ajout
        verifierEtNotifierSeuil();  // Vérifier les seuils après ajout
    }
}
