package rmi.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DossierSuivi implements Serializable {
    private List<String> suivi;

    public DossierSuivi(String noteInitiale) {
        this.suivi = new ArrayList<>();
        this.suivi.add(noteInitiale);
    }


    public void ajouterSuivi(String nouvelleNote) {
        this.suivi.add(nouvelleNote);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Dossier de suivi:\n");
        for (String note : suivi) {
            sb.append("- ").append(note).append("\n");
        }
        return sb.toString();
    }
}
