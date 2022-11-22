package objetos;

public class Mission {
    private String launch_name = "unknown";
    private String mission_name = "unknown";
    private String mission_type = "unknown";
    private String mission_launch_cost = "unknown";
    private String mission_description = "unknown";

    public Mission(String launch_name, String mission_name, String mission_type, String mission_launch_cost, String mission_description) {
        this.launch_name = launch_name;
        this.mission_name = mission_name;
        this.mission_type = mission_type;
        this.mission_launch_cost = mission_launch_cost;
        this.mission_description = mission_description;
    }
}
