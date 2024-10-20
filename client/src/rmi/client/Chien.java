// Dog.java (dans le module client)
package rmi.client;

import rmi.commons.Espece;
import java.io.Serializable;

public class Chien extends Espece implements Serializable {
    public Chien() {
        super("Chien", 12);  // Nom de l'espèce et durée de vie moyenne
    }
}

