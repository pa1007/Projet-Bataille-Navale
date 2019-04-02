package ai;

import exception.GrilleNonCreeException;
import graphic.GraphicMain;
import jeux.Grille;
import jeux.Jeux;
import jeux.Place;

public class GraphicAI extends AI {

    /**
     * L'instance graphique main
     */
    private final GraphicMain graphicMain;

    /**
     * Constructeur qui appel selui de AI, et permet de lier les graphisme
     *
     * @param graphicMain L'instance graphique main
     */
    public GraphicAI(GraphicMain graphicMain) {
        super();
        this.graphicMain = graphicMain;
    }

    /**
     * @param jeux jeux a lancer.
     */
    @Override
    public void play(Jeux jeux) {
        super.play(jeux);
        Grille g = jeux.getOtherPlayer(this).getGrille();
        Place  p = g.getEnemyFire().get(g.getEnemyFire().size() - 1);
        graphicMain.getMap().get(p.getRow()).get(p.getColumnNumber()).setEnemyFire();
    }

    /**
     * {@inheritDoc} <br>
     * Permet de refress les possition pour les afficher chez tout le monde
     *
     * @param jeux jeux auxquel ajouter un cas obstrue.
     * @throws GrilleNonCreeException si la grille est null
     */
    @Override
    public void obstruerCase(Jeux jeux) throws GrilleNonCreeException {
        super.obstruerCase(jeux);
        for (Place p : jeux.getObstrue()) {
            graphicMain.getMap().get(p.getRow()).get(p.getColumnNumber()).setObstrue();
        }
    }
}
