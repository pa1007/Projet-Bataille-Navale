package ai;

import jeux.Jeux;
import utils.Player;
import java.io.Serializable;

public class AI extends Player implements Serializable {

    public AI() {
        super();
        setAI(true);
    }

    public void placerBateau(Jeux jeux) {
    }

}
