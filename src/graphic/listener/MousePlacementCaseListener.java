package graphic.listener;

import graphic.GraphicCase;
import graphic.GraphicPlayer;
import java.awt.event.MouseEvent;

public class MousePlacementCaseListener extends MouseCaseListener {

    /**
     * le joueur proprio
     */
    private GraphicPlayer player;

    /**
     * Constructeur
     *
     * @param p le joueur proprio
     */
    public MousePlacementCaseListener(GraphicPlayer p) {
        this.player = p;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component. <br>
     *
     * Permet d'avoir le message lorsque le click est fais
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        GraphicCase gc = (GraphicCase) e.getSource();
        this.player.setCurentPlace(gc.getPlace());
        GraphicPlayer.setWait(false);
        gc.repaint();
    }
}
