/*Code structure provided by our Lecturer Amilcar Aponte
 *Code adapted and modified by @Author = Leandro Silveira
 *Code can be checked on: https://moodle.cct.ie/course/view.php?id=1505
 * or viewed on https://www.youtube.com/watch?v=3d2XaZsyhvo
 */
package classes;


import java.sql.SQLException;

public class Country {
    /*
    encapsulated attributes of the class
     */
    private String code;
    private String name;
    private Continent continent;
    private float surfaceArea;
    private String headOfState;

    /*Defining a constructor that have the object builder bringing
    all the attributes that the Country has
     */
    private Country(BuilderCountry builder) {

        this.code = builder.code;
        this.name = builder.name;
        this.continent = builder.continent;
        this.surfaceArea = builder.surfaceArea;
        this.headOfState = builder.headOfState;


    }

    /*
    method to show the actual values of the data in the terminal
     */
    @Override
    public String toString() {
        return "Country=" + '\'' +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent=" + continent +
                ", surfaceArea=" + surfaceArea +
                ", headOfState='" + headOfState + '\n' + '\n' +
                '}';
    }

    /*
    Getters
     */
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

    public String getHeadOfState() {
        return headOfState;
    }

    /*
    this method is the one in charge of creating all instances of the Country Class
     */
    public static class BuilderCountry {

        private String code;
        private String name;
        private Continent continent;
        private float surfaceArea;
        private String headOfState;


        /*
        creates the objects builder
         */
        public BuilderCountry(String code, String name, Continent continent, float surfaceArea, String headOfState) {

            this.code = code;
            this.name = name;
            this.continent = continent;
            this.surfaceArea = surfaceArea;
            this.headOfState = headOfState;

        }

        /*instance of the builder class in the builder i can define surface area
        using this setter and return the very same instance
         */
        public BuilderCountry setSurfaceArea(float surfaceArea) {
            this.surfaceArea = surfaceArea;
            return this;
        }

        /*instance of the builder class in the builder i can define the Head of State
        using this setter and return the very same instance
         */
        public BuilderCountry setHeadOfState(String headOfState) {
            return this;
        }

        /*
        setting and returning an instance of the class from the builder
         */
        public Country build() {
            return new Country(this);
        }

    }
}
