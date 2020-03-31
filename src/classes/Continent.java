package classes;

public enum Continent {

//Enum class for classes.Continent since the continents won't ever change
    Europe("Europe"),
    Asia("Asia"),
    NorthAmerica("North America"),
    SouthAmerica("South America"),
    Africa("Africa"),
    Oceania("Oceania"),
    Antarctica("Antarctica");

    private final String continent;

    //constructor
    private Continent(String continent){
        this.continent = continent;
    }


    @Override
    public String toString() {
        return "Continent{" +
                "continent='" + continent + '\'' +
                '}';
    }
}
