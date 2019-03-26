package graphic;

import bateaux.Bateaux;
import graphic.listener.MouseCaseListener;
import jeux.Place;
import jeux.Tire;
import javax.swing.JPanel;
import java.awt.*;
import java.io.Serializable;

public class GraphicCase extends JPanel implements Serializable {

    private Point p;

    private int lastStateDraw;

    private Bateaux b;

    private Tire tire;

    private       Place       place;
    private final GraphicMain graphicMain;

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
        p = new Point(i, j);
        place = new Place(i, j);
        this.graphicMain = graphicMain;
        this.addMouseListener(new MouseCaseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLastState(g);
    }

    public void drawTire(Graphics g) {
        drawEmptyState(g);
        System.out.println("draw");
        if (tire != null) {
            if (tire.isTouche()) {
                drawBateau(g);
            }
            g.setColor(Color.RED);
            g.drawLine(0, 0, getWidth(), getHeight());
            g.drawLine(getWidth(), 0, 0, getHeight());

        }
    }

    public void drawBateau(Graphics g) {
        if (b != null) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        else {
            drawEmptyState(g);
        }
    }

    public void drawMouseEnter() {
        Graphics2D g = (Graphics2D) getGraphics();
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, getWidth(), getHeight());

    }

    public Bateaux getB() {
        return b;
    }

    public void setB(Bateaux b) {
        this.b = b;
    }

    public Place getPlace() {
        return place;
    }

    public void drawEmptyState(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawEdge(g);
    }

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

    public void changeLastState(int lastStateDraw) {
        this.lastStateDraw = lastStateDraw;
    }

    public GraphicMain getGraphicMain() {
        return graphicMain;
    }

    public Tire getTire() {
        return this.tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    private void drawEdge(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, getWidth(), getHeight());
    }
}
