package objetos;

public class Rocket {
    private String rocket_name = "unknown";
    private String rocket_family = "unknown";
    private String rocket_length = "unknown";
    private String rocket_diameter = "unknown";
    private String rocket_launch_mass = "unknown";
    private String rocket_low_earth_orbit_capacity = "unknown";
    private String rocket_description = "unknown";
    private String agency_name = "unknown";

    public Rocket(String agency_name, String rocket_name, String rocket_family, String rocket_length, String rocket_diameter, String rocket_launch_mass,String rocket_low_earth_orbit_capacity, String rocket_description){
        this.agency_name = agency_name;
        this.rocket_name = rocket_name;
        this.rocket_family = rocket_family;
        this.rocket_length = rocket_length;
        this.rocket_diameter = rocket_diameter;
        this.rocket_launch_mass = rocket_launch_mass;
        this.rocket_low_earth_orbit_capacity = rocket_low_earth_orbit_capacity;
        this.rocket_description = rocket_description;
    }
}
