package objetos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mission")
public class Mission {
    private String rocket_name = "unknown";
    private String mission_name = "unknown";
    private String mission_type = "unknown";
    private String mission_launch_cost = "unknown";
    private String mission_description = "unknown";

    public Mission(String rocket_name, String mission_name, String mission_type, String mission_launch_cost, String mission_description) {
        this.rocket_name = rocket_name;
        this.mission_name = mission_name;
        this.mission_type = mission_type;
        this.mission_launch_cost = mission_launch_cost;
        this.mission_description = mission_description;
    }

    @XmlElement
    public String getRocket_name() {
        return rocket_name;
    }

    @XmlElement
    public String getMission_name() {
        return mission_name;
    }

    @XmlElement
    public String getMission_type() {
        return mission_type;
    }

    @XmlElement
    public String getMission_launch_cost() {
        return mission_launch_cost;
    }

    @XmlElement
    public String getMission_description() {
        return mission_description;
    }
}
