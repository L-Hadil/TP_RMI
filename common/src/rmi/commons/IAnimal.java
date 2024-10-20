package rmi.commons;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {

    String getNomAnimal() throws RemoteException;
    String getNomComplet() throws RemoteException;
    String getProprietaire() throws RemoteException;
    String getRace() throws RemoteException;
    Espece getEspece() throws RemoteException;
    DossierSuivi getDossierSuivi() throws RemoteException;

    // Méthodes de modification
    void setNomAnimal(String nom) throws RemoteException;
    void setProprietaire(String proprietaire) throws RemoteException;
    void setEspece(Espece espece) throws RemoteException;

    void setRace(String race) throws RemoteException;  // Nouvelle méthode

    // Méthode pour ajouter du suivi
    void setDossierSuivi(DossierSuivi dossier) throws RemoteException;
    void ajouterSuivi(String nouveauSuivi) throws RemoteException;
}
