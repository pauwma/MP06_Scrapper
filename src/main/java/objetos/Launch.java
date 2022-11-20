package objetos;

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

    @Override
    public String toString(){
        StringBuilder dataBuilder = new StringBuilder();
        appendFieldValue(dataBuilder, launch_title);
        appendFieldValue(dataBuilder, launch_status);
        appendFieldValue(dataBuilder, launch_date);
        appendFieldValue(dataBuilder, rocket_name);
        appendFieldValue(dataBuilder, agency_name);
        appendFieldValue(dataBuilder, location_name);


        return dataBuilder.toString();
    }
    private void appendFieldValue(StringBuilder dataBuilder, String fieldValue) {
        if(fieldValue != null) {
            dataBuilder.append(fieldValue).append(",");
        } else {
            dataBuilder.append("").append(",");
        }
    }
}
