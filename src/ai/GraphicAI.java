package ai;

import exception.GrilleNonCreeException;
import graphic.GraphicMain;
import jeux.Grille;
import jeux.Jeux;
import jeux.Place;

public class GraphicAI extends AI {

    private final GraphicMain graphicMain;

    public GraphicAI(GraphicMain graphicMain) {
        this.graphicMain = graphicMain;
    }

    @Override
    public void play(Jeux jeux) {
        super.play(jeux);
        Grille g = jeux.getOtherPlayer(this).getGrille();
        Place  p = g.getEnemyFire().get(g.getEnemyFire().size() - 1);
        graphicMain.getMap().get(p.getRow()).get(p.getColumnNumber()).setEnemyFire();
    }

    @Override
    public void obstruerCase(Jeux jeux) throws GrilleNonCreeException {
        super.obstruerCase(jeux);
        for (Place p : jeux.getObstrue()) {
            graphicMain.getMap().get(p.getRow()).get(p.getColumnNumber()).setObstrue();
        }
    }
}
