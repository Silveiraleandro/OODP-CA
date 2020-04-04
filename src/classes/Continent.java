/*
@Author: Leandro Silveira
 */
package classes;

public enum Continent {

    /*
    Enum class for classes.Continent since the continents won't ever change
     */
    Europe("Europe"),
    Asia("Asia"),
    NorthAmerica("North America"),
    SouthAmerica("South America"),
    Africa("Africa"),
    Oceania("Oceania"),
    Antarctica("Antarctica");

    private final String continent;

    /*
    Enum constructor
     */
    Continent(String continent) {
        this.continent = continent;
    }

    /*
    getter
     */
    public String getContinent() {
        return continent;
    }

    /*
    implementation of the toString method
     */
    @Override
    public String toString() {
        return "Continent{" +
                "continent='" + continent + '\'' +
                '}';
    }
}
