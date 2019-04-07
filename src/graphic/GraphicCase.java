package graphic;

import bateaux.Bateaux;
import graphic.listener.MouseCaseListener;
import jeux.Place;
import jeux.Tire;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public class GraphicCase extends JPanel implements Serializable {


    /**
     * Pour savoir quoi dessiner.
     */
    private int lastStateDraw;

    /**
     * Si la case est obstrue.
     */
    private boolean obstrue;

    /**
     * Le bateau present ou null si aucun bateau.
     */
    private Bateaux b;

    /**
     * Si il y a eu un tire, sinon null.
     */
    private Tire tire;

    /**
     * Le point qui correspond a la case.
     */
    private Place place;

    /**
     * La classe principale.
     */
    private final GraphicMain graphicMain;


    /**
     * La couleure de fond de base.
     */
    private Color emptyStateColor = Color.CYAN;

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     *
     * @param i           the i point
     * @param j           the j point
     * @param graphicMain the main class for graphic
     */
    public GraphicCase(int i, int j, GraphicMain graphicMain) {
        lastStateDraw = 0;
        place = new Place(i, j);
        this.graphicMain = graphicMain;
        this.addMouseListener(new MouseCaseListener());
        obstrue = false;
    }

    /**
     * Permet de dessiner au repaint();
     *
     * @param g les graphiques
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLastState(g);
    }

    /**
     * Initialise la case en "obstrue" et la redessine.
     */
    public void setObstrue() {
        obstrue = true;
        repaint();
    }

    /**
     * Dessine un tire si il n'est pas null.
     *
     * @param g le grpahics
     */
    public void drawTire(Graphics g) {
        drawEmptyState(g);
        if (tire != null) {
            if (tire.isTouche()) {
                drawBateau(g);
            }
            g.setColor(Color.RED);
            g.drawLine(0, 0, getWidth(), getHeight());
            g.drawLine(getWidth(), 0, 0, getHeight());
        }

    }

    /**
     * Permet de dessiner le bateau si non null, sinon dessine le fond.
     *
     * @param g les graphiques
     */
    public void drawBateau(Graphics g) {
        if (b != null) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        else {
            drawEmptyState(g);
        }
    }

    /**
     * Dessine lorsque la souris rentre dans un panel.
     */
    public void drawMouseEnter() {
        Graphics2D g = (Graphics2D) getGraphics();
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, getWidth(), getHeight());

    }

    /**
     * @return Le bateau si present ou null.
     */
    public Bateaux getB() {
        return b;
    }

    /**
     * Initialise le bateau.
     *
     * @param b Le bateau si present ou null.
     */
    public void setB(Bateaux b) {
        this.b = b;
    }

    /**
     * @return La place que la case represente.
     */
    public Place getPlace() {
        return place;
    }

    /**
     * Permet de dessiner le dernier etat selectionne.
     *
     * @param g les graphiques
     */
    public void drawLastState(Graphics g) {
        switch (lastStateDraw) {
            case 0:
                drawEmptyState(g);
                break;
            case 1:
                drawTire(g);
                break;
            case 2:
                drawBateau(g);
                break;
        }
    }

    /**
     * Change l'etat de la case en fonction de ce qui est mis en parametre.
     *
     * @param lastStateDraw l'etat, entre 0 et 3.
     */
    public void changeLastState(int lastStateDraw) {
        this.lastStateDraw = lastStateDraw;
    }


    /**
     * @return le tire si present ou null
     */
    public Tire getTire() {
        return this.tire;
    }

    /**
     * @param tire le tire si present ou null
     */
    public void setTire(Tire tire) {
        this.tire = tire;
    }

    /**
     * Savoir si la case est obstuer.
     *
     * @return true si obstuer, false si non
     */
    public boolean isObstue() {
        return obstrue;
    }

    /**
     * Change la couleure du fond de la case a l'excecution.
     */
    public void setEnemyFire() {
        emptyStateColor = Color.BLUE;
    }

    /**
     * Permet de dessiner la case vide ou l'obstruation de celle ci.
     *
     * @param g les graphiques.
     */
    private void drawEmptyState(Graphics g) {
        g.setColor(emptyStateColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawEdge(g);
        if (obstrue) {
            g.setColor(Color.WHITE);
            g.fillOval(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Dessine les bords de la case.
     *
     * @param g les graphiques
     */
    private void drawEdge(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, getWidth(), getHeight());
    }
}
