package writers;

import objetos.Launch;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="launches")
public class LaunchList {

    public List<Launch> listLaunches = new ArrayList<Launch>();

    @XmlElement(name = "launch")
    public List<Launch> getListLaunches() {
        return listLaunches;
    }

    public void setListLaunches(List<Launch> listLaunches) {
        this.listLaunches = listLaunches;
    }
}
