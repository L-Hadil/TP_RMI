package rmi.commons;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICabinetVeterinaire extends Remote {
    void ajouterAnimal(IAnimal animal) throws RemoteException;
    IAnimal rechercherAnimalParNom(String nom) throws RemoteException;
    List<IAnimal> getAnimaux() throws RemoteException;
    void modifierAnimal(String nom, IAnimal animalModifie) throws RemoteException;
    void supprimerAnimal(String nom) throws RemoteException;
    void ajouterAnimal(String nom, String proprietaire, Espece espece, String race) throws RemoteException;

    // Nouvelle méthode pour enregistrer les clients pour les alertes
    void registerCallback(IClientCallback client) throws RemoteException;
}

