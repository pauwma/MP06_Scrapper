package objetos;

public class Agency {
    private String agency_name = "unknown";
    private String agency_type = "unknown";
    private String agency_abbreviation = "unknown";
    private String agency_administration = "unknown";
    private int agency_founded = 0;
    private String agency_launchers = "unknown";
    private String agency_country = "unknown";
    private String agency_description = "none";

    public Agency(String agency_name, String agency_type, String agency_abbreviation, String agency_administration, int agency_founded, String agency_launchers, String agency_country, String agency_description) {
        this.agency_name = agency_name;
        this.agency_type = agency_type;
        this.agency_abbreviation = agency_abbreviation;
        this.agency_administration = agency_administration;
        this.agency_founded = agency_founded;
        this.agency_launchers = agency_launchers;
        this.agency_country = agency_country;
        this.agency_description = agency_description;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "agency_name='" + agency_name + '\'' +
                ", agency_type='" + agency_type + '\'' +
                ", agency_abbreviation='" + agency_abbreviation + '\'' +
                ", agency_administration='" + agency_administration + '\'' +
                ", agency_founded=" + agency_founded +
                ", agency_launchers='" + agency_launchers + '\'' +
                ", agency_country='" + agency_country + '\'' +
                ", agency_description='" + agency_description + '\'' +
                '}';
    }
}
