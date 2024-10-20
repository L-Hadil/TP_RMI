package rmi.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DossierSuivi implements Serializable {
    private List<String> suivi;  // Liste des entrées de suivi

    public DossierSuivi(String noteInitiale) {
        this.suivi = new ArrayList<>();
        this.suivi.add(noteInitiale);  // Ajoute une première note de suivi
    }

    // Méthode pour ajouter une entrée au dossier de suivi
    public void ajouterSuivi(String nouvelleNote) {
        this.suivi.add(nouvelleNote);
    }

    // Méthode pour afficher l'intégralité du dossier de suivi
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Dossier de suivi:\n");
        for (String note : suivi) {
            sb.append("- ").append(note).append("\n");
        }
        return sb.toString();
    }
}
