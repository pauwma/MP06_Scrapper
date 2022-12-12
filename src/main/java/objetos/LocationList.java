package objetos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase LocationList
 */
@XmlRootElement(name="locations")
public class LocationList {
    public List<Location> listLocations;

    @XmlElement(name="agency")
    public void setListLocations(List<Location> listLocations) {
        this.listLocations = listLocations;
    }

    public void add(Location location) {
        this.listLocations.add(location);
    }

}