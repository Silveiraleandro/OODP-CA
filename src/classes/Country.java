package classes;

public class Country {
    //encapsulated attributes of the class
    private String code;
    private String name;
    private Continent continent;
    private float surfaceArea;
    private String headOfState;

    //Defining a constructor that have the object builder bringing
    //all the attributes that the Country has
    private Country(BuilderCountry builder) {

        this.code = builder.code;
        this.name = builder.name;
        this.continent = builder.continent;
        this.surfaceArea = builder.surfaceArea;
        this.headOfState = builder.headOfState;

    }

    public String getCode() {

        return code;
    }

    public String getName() {

        return name;
    }

    public Continent getContinent() {

        return continent;
    }

    public float getSurfaceArea() {

        return surfaceArea;
    }

    //method to show the actual values of the data in the terminal
    @Override
    public String toString() {
        return "Country{" +'\n'+
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent=" + continent +
                ", surfaceArea=" + surfaceArea +
                ", headOfState='" + headOfState + '\'' +
                '}';
    }


    public String getHeadOfState() {
        return headOfState;
    }
    //setter left in case a user wants to set a head of state
    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }
    //this method is the one in charge of creating all instances of the Country Class
    public static class BuilderCountry {

        private String code;
        private String name;
        private Continent continent;
        private float surfaceArea;
        private String headOfState;
        //creates the objects builder
        public BuilderCountry(String code, String name, Continent continent, float surfaceArea, String headOfState) {

            this.code = code;
            this.name = name;
            this.continent = continent;
            this.surfaceArea = surfaceArea;
            this.headOfState = headOfState;
        }

        public Country build() {
            return new Country(this);
        }

    }
}
