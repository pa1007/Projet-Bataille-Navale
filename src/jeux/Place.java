package jeux;

import java.util.Objects;

public class Place {


    /**
     * The name of the place composed of a Letter and a number.
     *
     * @since 1.0
     */
    private String name;

    /**
     * The column character.
     *
     * @since 1.0
     */
    private String column;

    /**
     * The row number.
     *
     * @since 1.0
     */
    private int row;


    public Place(String s) {
        this.name = s.toUpperCase();
        generatePlace();
    }

    public Place(String name, int row) {
        this.column = name.toUpperCase();
        this.row = row;
        this.name = column + this.row;
    }

    public Place(int verticale, int horizontal) {

    }

    /**
     * @return The column character.
     * @since 1.0
     */
    public String getColumn() {
        return this.column;
    }

    /**
     * Sets the <code>column</code> field.
     *
     * @param column The column character.
     * @since 1.0
     */
    public void setColumn(String column) {
        this.name = this.name.replaceFirst(this.column, column);
        this.column = column;
    }

    /**
     * @return The row number.
     * @since 1.0
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Sets the <code>row</code> field.
     *
     * @param row The row number.
     * @since 1.0
     */
    public void setRow(int row) {
        this.name = this.name.replaceFirst(String.valueOf(this.row), String.valueOf(row));
        this.row = row;
    }

    /**
     * @return The name of the place composed of a Letter and a number.
     * @since 1.0
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the <code>name</code> field.
     *
     * @param name The name of the place composed of a Letter and a number.
     * @since 1.0
     */
    public void setName(String name) {
        this.name = name;
        generatePlace();
    }


    /**
     * @return
     */
    public int getColumnNumber() {
        return column.toLowerCase().charAt(0) - 'a';
    }

    /**
     * Pour tester si c'est la même
     *
     * @param p6 le string a tester
     * @return true si elle sont les mêmes ou false si non
     */
    public boolean is(String p6) {
        return name.equals(p6.toUpperCase());
    }

    /**
     * Pour tester si c'est la même
     *
     * @param place la place a tester
     * @return true si elle sont les mêmes ou false si non
     */
    public boolean is(Place place) {
        return name.equalsIgnoreCase(place.name);
    }

    /**
     * This method give you the current place with the moved of row and column add in parameters <br>
     * The column is between "A" and "Z",
     *
     * @param row    the amount of row to move from
     * @param column the amount of column to move from max 26
     * @return a new place with the moved column and row or A0 if the place is outside the board
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
     *
     */
    private void generatePlace() {
        column = String.valueOf(name.charAt(0));
        row = Integer.valueOf(name.replace(column, ""));
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
     * @return a string
     */
    @Override
    public String toString() {
        return name;
    }

}
