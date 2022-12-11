package objetos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="rockets")
public class RocketList {
    public List<Rocket> listRockets;

    @XmlElement(name="rocket")
    public void setListRockets(List<Rocket> listRockets) {
        this.listRockets = listRockets;
    }

    public void add(Rocket rocket) {
        this.listRockets.add(rocket);
    }

}