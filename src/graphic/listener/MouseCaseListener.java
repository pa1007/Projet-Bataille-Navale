package graphic.listener;

import graphic.GraphicCase;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

public class MouseCaseListener implements MouseListener, Serializable {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.<br>
     * Permet de savoir quand la sourie rentre dans la fenetre et de dessiner la selection
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        GraphicCase gc = ((GraphicCase) e.getSource());
        gc.drawMouseEnter();
    }

    /**
     * Invoked when the mouse exits a component.<br>
     * Permet de savoir quand la sourie sort dans la fenetre et de dessiner la derniere state
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
        GraphicCase gc = ((GraphicCase) e.getSource());
        gc.drawLastState(gc.getGraphics());
    }
}
