package graphic;

import bateaux.*;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import exception.GrilleNonCreeException;
import graphic.listener.MousePlacementCaseListener;
import jeux.Grille;
import jeux.Jeux;
import jeux.Place;
import jeux.Tire;
import utils.Player;
import utils.SavedObject;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphicPlayer extends Player implements Serializable {

    /**
     * Le boolean en volatile (Permet de linker differents thread java (Cf javadoc principal, site oracle) <br>
     * Utilisation obligatoire dans notre cas, permet de linker le programme a des listeners sur des JPanel
     */
    private static volatile boolean wait;

    /**
     * La table principale
     */
    private Map<Integer, List<GraphicCase>> map;

    /**
     * La place courrante en cas de demande par listener.
     */
    private Place curentPlace;

    /**
     * Le listener a ajouter, change a chaque ajout.
     */
    private MousePlacementCaseListener listener;

    /**
     * La classe main , finale pour ne pas pouvoir la changer.
     */
    private final GraphicMain graphicMain;

    /**
     * Le constructeur principal.
     *
     * @param map         Table pour chaque lignes et ranger
     * @param graphicMain Instance de la classe princiaple pour les graphismes
     */
    public GraphicPlayer(Map<Integer, List<GraphicCase>> map, GraphicMain graphicMain) {
        this.graphicMain = graphicMain;
        this.map = map;
    }

    /**
     * {@inheritDoc}
     * Methode graphique <br>
     *
     * @param j le jeux
     * @throws GrilleNonCreeException throw si la grille est null
     */
    @Override
    public void placerBateau(Jeux j) throws GrilleNonCreeException {
        if (grille != null) {
            AtomicBoolean bool = new AtomicBoolean(false);
            graphicMain.setButtonAction("Placer aléatoirement !", e -> {
                try {
                    bool.set(true);
                    placerBateauRandom(j);
                }
                catch (GrilleNonCreeException ignored) {
                }
            });
            changeDrawState(2);
            try {
                boolean       b           = false;
                List<Bateaux> bateauxList = new ArrayList<>();
                Place[]       p           = getBateauPlace(5, j, b);
                Bateaux       b1          = new ContreTorpilleur(p, j, this, b);
                changeCaseState(p, b1);
                bateauxList.add(b1);
                this.grille.setListBateaux(bateauxList);
                Place[] p2 = getBateauPlace(4, j, b);
                Bateaux b2 = new PorteAvion(p, j, this, b);
                changeCaseState(p2, b2);
                bateauxList.add(b2);
                Place[] p3 = getBateauPlace(3, j, b);
                Bateaux b3 = new SousMarin(p, j, this, b);
                changeCaseState(p3, b3);
                bateauxList.add(b3);
                Place[] p4 = getBateauPlace(2, j, b);
                Bateaux b4 = new Torpilleur(p, j, this, b);
                changeCaseState(p4, b4);
                bateauxList.add(b4);
                Place[] p5 = getBateauPlace(1, j, b);
                Bateaux b5 = new Croiseur(p, j, this, b);
                changeCaseState(p5, b5);
                bateauxList.add(b5);
                graphicMain.setMessage("Vous avez ajouter tout vos bateaux");
            }
            catch (Exception e) {
                if (!bool.get()) {
                    throw new NullPointerException();
                }
                else {
                    for (Bateaux b : grille.getListBateaux()) {
                        for (Place p : b.getPlaces()) {
                            GraphicCase gc = graphicMain.getMap().get(p.getRow()).get(p.getColumnNumber());
                            gc.setB(b);
                            gc.changeLastState(1);
                        }
                    }
                }
            }
        }
        else {
            throw new GrilleNonCreeException();
        }
    }

    /**
     * {@inheritDoc}
     * Methode graphique <br>
     *
     * @param jeux le jeu
     */
    @Override
    public void play(Jeux jeux) {
        changeDrawState(1);
        graphicMain.setButtonAction("Arreter", e -> {
            try {
                saveAndExit(jeux);
            }
            catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(graphicMain, "Error while saving");
                System.exit(-1);
            }
        });
        Place       place = getUserTireInput();
        GraphicCase g     = map.get(place.getRow()).get(place.getColumnNumber());
        boolean     b     = g.getB() != null;
        Tire        t     = new Tire(b, place);
        if (b) {
            g.getB().toucher(t.getPlace());
        }
        g.setTire(t);
        g.drawLastState(g.getGraphics());
        this.grille.addNewTire(t);
        graphicMain.setMessage(b ? "Tu as touche un bateau a la place " + place + (
                g.getB().dead()
                        ? "Et vous l'avez coulé"
                        : ""
        ) : " Dans l'eau ");
    }

    /**
     * {@inheritDoc}
     * Method graphic <br>
     * Utilisation d'un AtomicBoolean pour ceci, permet l'utilisation d'un lambda (->) et d'un boolean simple (Remplacable par une variable static)
     *
     * @param jeux le jeux
     */
    public void obstruerCase(Jeux jeux) {
        graphicMain.setMessage("Selectioner les cases a Obstuer ou continuer");
        AtomicBoolean end = new AtomicBoolean(false);
        graphicMain.setButtonAction("Continuer", e -> {
            end.set(true);
            GraphicPlayer.setWait(false);
        });
        while (!end.get()) {
            listener = new MousePlacementCaseListener(this);
            curentPlace = null;
            wait = true;
            placerAllCase();
            while (wait) {
            }
            removePlaceListener();
            if (curentPlace != null) {
                map.get(curentPlace.getRow()).get(curentPlace.getColumnNumber()).setObstrue();
                jeux.addPlaceObstue(curentPlace);
            }
        }
    }

    /**
     * Permet d'ajouter la place dans la variable
     *
     * @param curentPlace la place occupee
     */
    public void setCurentPlace(Place curentPlace) {
        this.curentPlace = curentPlace;
    }

    /**
     * Permet de placer les listeners pour les bateaux, avec la methode {@link #placerPlaceListener()} <br>
     * la methode attends l'input user, avec la variable <code>wait</code> ajout la place dans <code>this.curentPlace</code>
     *
     * @param message le message a afficher
     * @return la place selectionner, (stocker dans curentPlace aussi )
     */
    public Place getUserBoatInput(String message) {
        listener = new MousePlacementCaseListener(this);
        curentPlace = null;
        wait = true;
        graphicMain.setMessage(message);
        placerPlaceListener();
        while (wait) {

        }
        removePlaceListener();
        return curentPlace;
    }

    /**
     * Permet de changer la state de tous les bateaux.
     *
     * @param state la state a metre, compris entre 0 et 2
     */
    public void changeDrawState(int state) {
        if (state < 3 && state > -1) {
            for (List<GraphicCase> gcl : map.values()) {
                for (GraphicCase gc : gcl) {
                    gc.changeLastState(state);
                    gc.repaint();
                }
            }
        }
    }

    /**
     * Permet de save et exit en même temps.
     *
     * @param jeux le jeux
     * @throws IOException exception throw par save
     * @see SavedObject#save()
     */
    private void saveAndExit(Jeux jeux) throws IOException {
        new SavedObject(jeux).save();
        System.exit(0);
    }

    /**
     * Attend l'imput user pour tirer.
     *
     * @return la place tire par l'utilisateur (stocker dans curentPlace aussi)
     */
    private Place getUserTireInput() {
        listener = new MousePlacementCaseListener(this);
        curentPlace = null;
        wait = true;
        placerPlaceTireListener();
        while (wait) {
        }
        removePlaceListener();
        return curentPlace;
    }

    /**
     * Permet de changer la 'state' des places donnée en parametre et de leur ajouter un bateau
     *
     * @param p  les places a changer
     * @param b1 le bateau a ajouter
     */
    private void changeCaseState(Place[] p, Bateaux b1) {
        for (Place place : p) {
            GraphicCase gc = map.get(place.getRow()).get(place.getColumnNumber());
            gc.setB(b1);
            gc.repaint();
        }
    }

    /**
     * demande l'input user pour avoir la place puis ouvre un dialog pour savoir l'orientation <br>
     * puis check si la place est bon avec la methode {@link Jeux#checkPlace(String, Grille, int, boolean)}<br>
     * Si il y a une erreur, l'user a un dialog qui s'ouvre pour lui dire, il devra recommencer
     *
     * @param taille la taille du bateau a placer
     * @param j      le jeux a link
     * @param b      le boolean de sortie
     * @return les places du bateau
     */
    private Place[] getBateauPlace(int taille, Jeux j, boolean b) throws Exception {
        while (true) {
            try {
                Place p = getUserBoatInput("Selectionnez la place en haut a gauche du bateau de taille " + taille);
                if (taille != 1) {
                    if (p != null) {
                        graphicMain.setMessage("Le bateau fais " + taille + " de longeur ! Choisir sont orientation");
                        String[] options = {"Vertical", "Horizontal"};
                        int      n       = graphicMain.askChoice("Vertical ou Horizontal ?", options);
                        b = options[n].equals("Horizontal");
                    }
                    else {
                        throw new Exception();
                    }
                }
                return j.checkPlace(p.toString(), grille, taille, b);
            }
            catch (BateauxStartPointInvalide | BateauxMembreInvalide e) {
                JOptionPane.showMessageDialog(
                        graphicMain,
                        "La position n'est pas bonne, merci de recommancer !",
                        null,
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    /**
     * Remove le listener (<code>this.listener</code>) sur toutes les places, ne remove pas le listener principal
     */
    private void removePlaceListener() {
        for (List<GraphicCase> lgc : map.values()) {
            for (GraphicCase gc : lgc) {
                gc.removeMouseListener(listener);
            }
        }
    }

    /**
     * Place <code>this.listener</code> sur toutes les cases présentes
     */
    private void placerAllCase() {
        for (List<GraphicCase> lgc : map.values()) {
            for (GraphicCase gc : lgc) {
                gc.addMouseListener(listener);
            }
        }
    }

    /**
     * Place <code>this.listener</code> sur toutes les cases ou il n'y a pas de bateau et que la case n'est pas obstuer
     */
    private void placerPlaceListener() {
        for (List<GraphicCase> lgc : map.values()) {
            for (GraphicCase gc : lgc) {
                if (gc.getB() == null && !gc.isObstue()) {
                    gc.addMouseListener(listener);
                }
            }
        }
    }

    /**
     * Place <code>this.listener</code> sur toutes les cases ou il n'y a pas de tire et que la case n'est pas obstuer
     */
    private void placerPlaceTireListener() {
        for (List<GraphicCase> lgc : map.values()) {
            for (GraphicCase gc : lgc) {
                if (gc.getTire() == null && !gc.isObstue()) {
                    gc.addMouseListener(listener);
                }
            }
        }
    }

    /**
     * Gere la variable wait, permet d'arrete l'attente du programme.
     *
     * @param wait la valeure a mettre
     */
    public static void setWait(boolean wait) {
        GraphicPlayer.wait = wait;
    }
}
