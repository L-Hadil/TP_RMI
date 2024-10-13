package rmi.commons;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {

    String getNomAnimal() throws RemoteException;
    String getDossierSuivi() throws RemoteException;
    void setDossierSuivi(String dossier) throws RemoteException;
}
