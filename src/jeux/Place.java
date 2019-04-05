package jeux;

import java.io.Serializable;
import java.util.Objects;

public class Place implements Serializable {


    /**
     * Le nom de la place compose d'un charactere et d'un numero.
     */
    private String name;

    /**
     * Caractere de colonne.
     */
    private String column;

    /**
     * Numero de la ligne.
     */
    private int row;

    /**
     * Constructeur de la place. Avec une chaine de caratere.
     *
     * @param s chaine de caractere qui correspond a une place.
     */
    public Place(String s) {
        try {
            this.name = s.toUpperCase();
            generatePlace();
        }
        catch (RuntimeException e) {
            this.name = "A0";
            generatePlace();
        }
    }

    /**
     * Constructeur de la place. Avec un nom et une ligne.
     *
     * @param name charactere de la place.
     * @param row  entier de la place.
     */
    public Place(String name, int row) {
        this.column = name.toUpperCase();
        this.row = row;
        this.name = column + this.row;
    }

    /**
     * Constructeur de la place. Avec deux parametre entiers.
     *
     * @param verticale  position verticale de la place.
     * @param horizontal position horizontale de la place.
     */
    public Place(int verticale, int horizontal) {
        if (verticale > 0 && horizontal > 0 && verticale < 26) {
            char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            column = String.valueOf(alpha[verticale - 1]).toUpperCase();
            row = horizontal;
            this.name = column + this.row;
        }
        else {
            this.name = "A0";
            generatePlace();
        }

    }

    /**
     * @return Le caractere de la ligne.
     */
    public String getColumn() {
        return this.column;
    }

    /**
     * Initialise la variable <code>column</code>.
     *
     * @param column
     */
    public void setColumn(String column) {
        if (column.length() == 1) {
            this.name = this.name.replaceFirst(this.column, column);
            this.column = column;
        }
    }

    /**
     * @return Le numero de la ligne.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Initialise le numero de la <code>row</code>.
     *
     * @param row
     */
    public void setRow(int row) {
        this.name = this.name.replaceFirst(String.valueOf(this.row), String.valueOf(row));
        this.row = row;
    }

    /**
     * @return Nom de la place (entier et charactere).
     */
    public String getName() {
        return this.name;
    }

    /**
     * Initialise le <code>name</code> de la place.
     *
     * @param name Nom de la place (entier et charactere).
     */
    public void setName(String name) {
        try {
            this.name = name;
            generatePlace();
        }
        catch (RuntimeException e) {
            this.name = "A0";
            this.column = "A";
            this.row = 0;
        }
    }


    /**
     * @return le numero de la colonne commence a 0 et fini a 25.
     */
    public int getColumnNumber() {
        return column.toLowerCase().charAt(0) - 'a';
    }

    /**
     * Pour tester si c'est la meme place.
     *
     * @param p6 le string a tester
     * @return true si elle sont les memes ou false si non
     */
    public boolean is(String p6) {
        return name.equals(p6.toUpperCase());
    }

    /**
     * Pour tester si c'est la meme place.
     *
     * @param place la place a tester.
     * @return true si elle sont les memes ou false si non.
     */
    public boolean is(Place place) {
        return name.equalsIgnoreCase(place.name);
    }

    /**
     * Methode qui revoie une place qui correspond a la place initiale + les parametres.<br>
     * Les places des colonnes sont comprises entre "A" et "Z".
     *
     * @param row    de combien se deplacer sur la ligne.
     * @param column de combien se deplacer sue la colonne max 26.
     * @return une place qui a ete incremente des parametres, si elle est en dehors des limites (0, 26) retourne "A0".
     */
    public Place more(int row, int column) {
        int tempR = this.row + row;
        int tempC = (int) this.column.toLowerCase().charAt(0) + column;
        if (tempR < 1) {
            return new Place("A0");
        }
        if (tempC > 122 || tempC < 97) {
            return new Place("A0");
        }
        return new Place(String.valueOf(Character.valueOf((char) tempC)), tempR);
    }

    /**
     * Methode qui cree une place en fonction <code>this.name</code>.
     *
     * @throws RuntimeException si la premiere valeure est fausse.
     */
    private void generatePlace() {
        if (Character.isAlphabetic(name.toCharArray()[0])) {
            column = String.valueOf(name.charAt(0));
            row = Integer.valueOf(name.replaceFirst(column, ""));
        }
        else {
            throw new RuntimeException("Premiere valeur fausse");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Place place = (Place) o;

        if (row != place.row) {
            return false;
        }
        if (!Objects.equals(name, place.name)) {
            return false;
        }
        return Objects.equals(column, place.column);
    }

    /**
     * @return un String
     */
    @Override
    public String toString() {
        return name;
    }

}
