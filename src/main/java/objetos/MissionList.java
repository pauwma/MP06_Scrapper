package objetos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase MissionList
 */
@XmlRootElement(name="missions")
public class MissionList {
    public List<Mission> listMissions;

    @XmlElement(name="mission")
    public void setListMissions(List<Mission> listMissions) {
        this.listMissions = listMissions;
    }

    public void add(Mission mission) {
        this.listMissions.add(mission);
    }

}