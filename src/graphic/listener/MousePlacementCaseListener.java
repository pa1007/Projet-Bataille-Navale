package graphic.listener;

import graphic.GraphicCase;
import graphic.GraphicPlayer;
import java.awt.event.MouseEvent;

public class MousePlacementCaseListener extends MouseCaseListener {

    private GraphicPlayer player;

    public MousePlacementCaseListener(GraphicPlayer p) {
        this.player = p;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        GraphicCase gc = (GraphicCase) e.getSource();
        this.player.setCurentPlace(gc.getPlace());
        GraphicPlayer.setWait(false);
    }
}
