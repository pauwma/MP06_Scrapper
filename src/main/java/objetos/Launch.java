package objetos;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "launch")
public class Launch {
    private String launch_title = "unknown";

    private String launch_status = "unknown";

    private String launch_date = "unknown";

    private String rocket_name = "unknown";

    private String agency_name = "unknown";

    private String location_name = "unknown";

    public Launch(String launch_title, String launch_status, String launch_date, String rocket_name, String agency_name, String location_name){
        this.launch_title = launch_title;
        this.launch_status = launch_status;
        this.launch_date = launch_date;
        this.rocket_name = rocket_name;
        this.agency_name = agency_name;
        this.location_name = location_name;
    }

    @XmlElement
    public String getLaunch_title() {
        return launch_title;
    }

    @XmlElement
    public String getLaunch_status() {
        return launch_status;
    }

    @XmlElement
    public String getLaunch_date() {
        return launch_date;
    }

    @XmlElement
    public String getRocket_name() {
        return rocket_name;
    }

    @XmlElement
    public String getAgency_name() {
        return agency_name;
    }

    @XmlElement
    public String getLocation_name() {
        return location_name;
    }
}