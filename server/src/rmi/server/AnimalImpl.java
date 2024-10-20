package rmi.server;

import rmi.commons.DossierSuivi;
import rmi.commons.Espece;
import rmi.commons.IAnimal;
import rmi.commons.ICabinetVeterinaire;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AnimalImpl extends UnicastRemoteObject implements IAnimal {

    private String nom;
    private String proprietaire;
    private String race;
    private Espece espece;
    private DossierSuivi dossierSuivi;




    public AnimalImpl(String nom, String proprietaire, Espece espece,String race) throws RemoteException {

        this.nom = nom;
        this.proprietaire = proprietaire;
        this.espece = espece;
        this.race=race;
        this.dossierSuivi = new DossierSuivi("Suivi initial");

    }

    @Override
    public String getNomAnimal() throws RemoteException {
        return nom;
    }

    @Override
    public String getNomComplet() throws RemoteException {
        return nom + " - " + proprietaire;
    }

    @Override
    public String getProprietaire() throws RemoteException {
        return proprietaire;
    }

    @Override
    public Espece getEspece() throws RemoteException {
        return new Espece(espece.getNomEspece(), espece.getDureeVieMoyenne());  // Retourne une copie
    }

    @Override
    public DossierSuivi getDossierSuivi() throws RemoteException {
        return dossierSuivi;
    }

    @Override
    public void setDossierSuivi(DossierSuivi dossier) throws RemoteException {
        this.dossierSuivi = dossier;
    }

    @Override
    public String getRace() throws RemoteException {
        return race;  // Retourne la race
    }
    @Override
    public void setNomAnimal(String nom) throws RemoteException {
        this.nom = nom;
    }

    @Override
    public void setProprietaire(String proprietaire) throws RemoteException {
        this.proprietaire = proprietaire;
    }


    @Override
    public void setEspece(Espece espece) throws RemoteException {
        this.espece = espece;
    }



    @Override
    public void ajouterSuivi(String nouveauSuivi) throws RemoteException {
        this.dossierSuivi.ajouterSuivi(nouveauSuivi);
    }


    @Override
    public void setRace(String race) throws RemoteException {
        this.race = race;
    }

}
