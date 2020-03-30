package classes;

public class Country {

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

    public void setCode(String code) {

        this.code = code;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Continent getContinent() {

        return continent;
    }

    public float getSurfaceArea() {

        return surfaceArea;
    }

    public void setSurfaceArea(float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public void addCountry() {

    }
    public static class BuilderCountry{

        private String code;
        private String name;
        private Continent continent;
        private float surfaceArea;
        private String headOfState;

        public BuilderCountry(String code, String name, Continent continent, float surfaceArea, String headOfState){

            this.code = code;
            this.name = name;
            this.continent = continent;
            this.surfaceArea = surfaceArea;
            this.headOfState = headOfState;
        }

        public Country build(){
            return new Country(this);
        }

    }
}
