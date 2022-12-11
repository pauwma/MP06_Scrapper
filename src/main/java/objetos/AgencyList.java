package objetos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="agencys")
public class AgencyList {
    public List<Agency> listAgencys;

    @XmlElement(name="agency")
    public void setListAgencys(List<Agency> listAgencys) {
        this.listAgencys = listAgencys;
    }

    public void add(Agency agency) {
        this.listAgencys.add(agency);
    }

}