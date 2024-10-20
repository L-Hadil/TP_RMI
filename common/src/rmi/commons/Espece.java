package rmi.commons;

import java.io.Serializable;

public class Espece implements Serializable {
    private String nomEspece;
    private int dureeVieMoyenne;  // En années, par exemple

    public Espece(String nomEspece, int dureeVieMoyenne) {
        this.nomEspece = nomEspece;
        this.dureeVieMoyenne = dureeVieMoyenne;
    }

    public String getNomEspece() {
        return nomEspece;
    }

    public int getDureeVieMoyenne() {
        return dureeVieMoyenne;
    }

    @Override
    public String toString() {
        return "Espèce: " + nomEspece + ", Durée de vie moyenne: " + dureeVieMoyenne + " ans";
    }
}
