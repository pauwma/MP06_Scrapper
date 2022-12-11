package objetos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="launches")
public class LaunchList {
    public List<Launch> listLaunches;

    @XmlElement(name="launch")
    public void setListLaunches(List<Launch> listLaunches) {
        this.listLaunches = listLaunches;
    }

    public void add(Launch launch) {
        this.listLaunches.add(launch);
    }

}