package graphic;

import ai.GraphicAI;
import exception.GrilleNonCreeException;
import jeux.Grille;
import jeux.Jeux;
import jeux.ModeDeJeux;
import utils.Player;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphicMain extends JFrame implements Serializable {

    /**
     * la taille en horizontale et verticale
     */
    private int h, v;

    /**
     * Une instance du jeu
     */
    private Jeux j;

    /**
     * Les JPanel presents
     */
    private JPanel leftPanel, rightPanel, contentPane;

    /**
     * affichage du text
     */
    private JLabel mainText;

    /**
     * Le stockage des cases, table qui lie l'horizontale et la verticale
     */
    private Map<Integer, List<GraphicCase>> map;

    /**
     * Le bouton present
     */
    private JButton mainButton;

    /**
     * Construit la JFrame princiaple
     */
    public GraphicMain() {
        map = new HashMap<>();
        this.setTitle("Bataile Navale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGame();
        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 20));
        setPreferredSize(new Dimension(1400, 900));
        createPanels();
        placeLetter();
        for (int i = 1; i < v + 1; i++) {
            List<GraphicCase> caseList = new ArrayList<>();
            for (int j = 0; j < h + 1; j++) {
                if (j == 0) {
                    JLabel jl = new JLabel(String.valueOf(i), JLabel.CENTER);
                    jl.setPreferredSize(new Dimension(30, 30));
                    rightPanel.add(jl);
                }
                else {
                    GraphicCase g = new GraphicCase(j, i, this);
                    rightPanel.add(g);
                    caseList.add(g);
                }

            }
            map.put(i, caseList);
        }
        contentPane.add(leftPanel);
        contentPane.add(rightPanel);
        setContentPane(contentPane);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    /**
     * Lance le jeu
     *
     * @throws GrilleNonCreeException si la grille n'a pas été mise pour l'un des joueur
     */
    public void launchGame() throws GrilleNonCreeException {
        j.lancerPartieGraph(this);
    }

    /**
     * @return une table avec pour chaque int 0 jusqu'a horisontale -1 une liste de 0 a verticale -1 de case
     */
    public Map<Integer, List<GraphicCase>> getMap() {
        return map;
    }

    /**
     * @return l'instance du jeu
     */
    public Jeux getJeux() {
        return j;
    }

    /**
     * Permet de metre un message dans le texte principale
     *
     * @param s le message a mêtre
     */
    public void setMessage(String s) {
        mainText.setText(s);
    }

    /**
     * Creation facile de demande de rentrer utilisateur
     *
     * @param title le titre de la fenetre
     * @param o     Les choix dispo
     * @return le numero du choix, -1 si quiter
     */
    public int askChois(String title, String[] o) {
        return JOptionPane.showOptionDialog(
                this,
                title,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                o,
                o[0]
        );
    }

    /**
     * Permet d'ajouter un text sur le bouton et une action
     *
     * @param s              le texte a ajouter
     * @param actionListener l'action a effectuer
     */
    public void setButtonAction(String s, ActionListener actionListener) {
        mainButton.setText(s);
        mainButton.addActionListener(actionListener);
        mainButton.setVisible(true);
    }

    /**
     * Permet de reset le bouton
     */
    public void resetMainButton() {
        mainButton.setText("");
        try {
            mainButton.removeActionListener(mainButton.getActionListeners()[0]);
        }
        catch (ArrayIndexOutOfBoundsException ignored) {
        }
        mainButton.setVisible(false);
    }

    /**
     * Crée les panels
     */
    private void createPanels() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        mainText = new JLabel("LeftPanel", JLabel.CENTER);
        leftPanel.add(mainText);
        mainButton = new JButton("");
        mainButton.setVisible(false);
        leftPanel.add(mainButton);
        rightPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(v + 1, h + 1, 0, 0);
        rightPanel.setLayout(gridLayout);
    }

    /**
     * Initialise le jeux
     */
    private void initGame() {
        while (h < 10 || h > 26) {
            try {
                String s = JOptionPane.showInputDialog("Nombre de case horizontales (entre 10 et 26) : ");
                h = Integer.valueOf(s);
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "L'entee n'est pas un nombre");
            }
        }
        while (v < 10 || v > 26) {
            try {
                String s = JOptionPane.showInputDialog("Nombre de case verticale (entre 10 et 26) : ");
                v = Integer.valueOf(s);
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "L'entee n'est pas un nombre");
            }
        }
        String[] s = {"MonoPLayer", "VS_AI"};
        int      r = askChois("Choix du mode de jeu", s);
        r = s[r].equals("MonoPLayer") ? ModeDeJeux.MONO_JOUEUR : ModeDeJeux.IA;
        List<Player> p = new ArrayList<>();
        p.add(new GraphicPlayer(map, this));
        if (r != ModeDeJeux.MONO_JOUEUR) {
            p.add(new GraphicAI(this));
        }
        j = new Jeux(r, p);
        for (Player pl : p) {
            pl.setGrille(new Grille(h, v, j, pl));
        }
    }

    /**
     * Place les letres sur le grid
     */
    private void placeLetter() {
        char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i = 0; i < h + 1; i++) {
            if (i != 0) {
                JLabel j = new JLabel(String.valueOf(alpha[i - 1]).toUpperCase(), JLabel.CENTER);
                j.setPreferredSize(new Dimension(30, 30));
                rightPanel.add(j);
            }
            else {
                JLabel j = new JLabel("Jeux", JLabel.CENTER);
                j.setPreferredSize(new Dimension(30, 30));
                rightPanel.add(j);
            }
        }


    }

}
