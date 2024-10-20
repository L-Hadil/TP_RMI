// Cat.java (dans le module client)
package rmi.client;

import rmi.commons.Espece;
import java.io.Serializable;

public class Chat extends Espece implements Serializable {
    public Chat() {
        super("Chat", 15);  // Nom de l'espèce et durée de vie moyenne
    }
}
