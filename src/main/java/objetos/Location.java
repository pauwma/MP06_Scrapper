package objetos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Clase Location
 */
@XmlRootElement(name = "location")
public class Location {
    private String launch_name = "unknown";
    private String location_name = "unknown";
    private String location_location = "unknown";
    private String rockets_launched = "unknown";

    public Location(String launch_name, String location_name, String location_location, String rockets_launched) {
        this.launch_name = launch_name;
        this.location_name = location_name;
        this.location_location = location_location;
        this.rockets_launched = rockets_launched;
    }

    public Location(){}

    @XmlElement
    public String getLaunch_name() {
        return launch_name;
    }

    @XmlElement
    public String getLocation_name() {
        return location_name;
    }

    @XmlElement
    public String getLocation_location() {
        return location_location;
    }

    @XmlElement
    public String getRockets_launched() {
        return rockets_launched;
    }
}