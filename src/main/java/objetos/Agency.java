package objetos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "agency")
public class Agency {
    private String agency_name = "unknown";
    private String agency_type = "unknown";
    private String agency_abbreviation = "unknown";
    private String agency_administration = "agency_spacecraft";
    private String agency_founded = "agency_spacecraft";
    private String agency_launchers = "unknown";
    private String agency_spacecraft = "unknown";
    private String agency_country = "unknown";
    private String agency_description = "none";

    public Agency(String agency_name, String agency_type, String agency_abbreviation, String agency_administration, String agency_founded, String agency_launchers, String agency_spacecraft, String agency_country, String agency_description) {
        this.agency_name = agency_name;
        this.agency_type = agency_type;
        this.agency_abbreviation = agency_abbreviation;
        this.agency_administration = agency_administration;
        this.agency_founded = agency_founded;
        this.agency_launchers = agency_launchers;
        this.agency_spacecraft = agency_spacecraft;
        this.agency_country = agency_country;
        this.agency_description = agency_description;
    }

    public Agency(){}

    @XmlElement
    public String getAgency_name() {
        return agency_name;
    }

    @XmlElement
    public String getAgency_type() {
        return agency_type;
    }

    @XmlElement
    public String getAgency_abbreviation() {
        return agency_abbreviation;
    }

    @XmlElement
    public String getAgency_administration() {
        return agency_administration;
    }

    @XmlElement
    public String getAgency_founded() {
        return agency_founded;
    }

    @XmlElement
    public String getAgency_launchers() {
        return agency_launchers;
    }

    @XmlElement
    public String getAgency_spacecraft() {
        return agency_spacecraft;
    }

    @XmlElement
    public String getAgency_country() {
        return agency_country;
    }

    @XmlElement
    public String getAgency_description() {
        return agency_description;
    }
}