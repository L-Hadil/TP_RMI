package rmi.server;

import rmi.commons.IAnimal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AnimalImpl extends UnicastRemoteObject implements IAnimal {

    private String nom;
    private String proprietaire;
    private String espece;




    public AnimalImpl(String nom, String proprietaire, String espece) throws RemoteException {

        this.espece=espece;
        this.nom=nom;
        this.proprietaire=proprietaire;

    }

    @Override
    public String getNomAnimal() throws RemoteException {
         return nom + " - " + proprietaire;
    }

    @Override
    public String getDossierSuivi() throws RemoteException {
        return "";
    }

    @Override
    public void setDossierSuivi(String dossier) throws RemoteException {

    }
}
