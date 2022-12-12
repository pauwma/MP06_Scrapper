import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.xml.bind.JAXBException;
import objetos.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import writers.WriterCSV;
import writers.WriterXML;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que gestiona la obtención de la información del scrapping.
 */
public class Scrapper {
    WebDriver driver;
    public Scrapper(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }

    /**
     * Obtiene todos los posts de la primera página.
     */
    public void getPosts() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        driver.get("https://www.spacelaunchschedule.com");
        List<WebElement> posts = driver.findElements(By.className("my-2"));
        // ? Recorre todos los posts cogiendo todos los URLS
        List<String> allURLs = getAllURL(posts);
        getInfoPost(allURLs);
    }

    /**
     * Obtiene todos los URL de todos los post de la primera página.
     */
    public List<String> getAllURL(List <WebElement> posts){
        List<String> URLs = new ArrayList<>();
        for(WebElement post : posts){
            URLs.add(post.findElement(By.className("stretched-link")).getAttribute("href"));
        }
        return URLs;
    }

    /**
     * Obtiene todos los CSV de 1 post.
     */
    public void getInfoPost(List<String> allURLs) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        // ? Creación de las listas de objetos.
        LaunchList launches_listJAXB = new LaunchList();
        List<Launch> launches_list = new ArrayList<>();
        List<Mission> missions_list = new ArrayList<>();
        List<Rocket> rockets_list = new ArrayList<>();
        List<Agency> agencys_list = new ArrayList<>();
        List<Location> locations_list = new ArrayList<>();

        // ? Obtención de la información
        for(String url : allURLs){
            driver.get(url);
            getAgency(agencys_list);
            getLaunch(launches_list);
            getLocation(locations_list);
            getMission(missions_list);
            getRocket(rockets_list);
        }

        // ? Listas a CSV.
        WriterCSV.agencyToCSV(agencys_list);
        WriterCSV.launchToCSV(launches_list);
        WriterCSV.locationToCSV(locations_list);
        WriterCSV.missionToCSV(missions_list);
        WriterCSV.rocketToCSV(rockets_list);


        // ? Listas a XML.
        WriterXML writerXML = new WriterXML(agencys_list,launches_list,locations_list,missions_list,rockets_list);

    }

    public void getAgency(List<Agency> agencys_list) throws IOException {
        String agency_name = "unknown", agency_type = "unknown", agency_abbreviation = "unknown", agency_administration = "unknown", agency_launchers = "unknown", agency_country = "unknown", agency_description = "none", agency_founded = "unknown", agency_spacecraft = "unknown";
        WebElement agency = driver.findElement(By.id("agency"));
        try {
            agency_name = agency.findElement(By.className("h5")).getText();
        } catch (Exception e) {}
        try {
            agency_type = driver.findElement(new By.ByXPath("//*[@id=\"agency\"]/p[1]")).getText();
            List<String> agency_list = new ArrayList<String>(Arrays.asList(agency_type.split("\n")));
            for(String s : agency_list){
                List<String> agency_list_detail = new ArrayList<>(Arrays.asList(s.split(":")));
                try {
                    switch (agency_list_detail.get(0).trim()) {
                        case "Type" -> agency_type = agency_list_detail.get(1).trim();
                        case "Abbreviation" -> agency_abbreviation = agency_list_detail.get(1).trim();
                        case "Administration" -> agency_administration = agency_list_detail.get(1)+": "+agency_list_detail.get(2);
                        case "Founded" -> agency_founded = agency_list_detail.get(1).trim();
                        case "Launchers" -> agency_launchers = agency_list_detail.get(1).trim();
                        case "Country" -> agency_country = agency_list_detail.get(1).trim();
                        case "Spacecraft" -> agency_spacecraft = agency_list_detail.get(1).trim();
                    }
                } catch (Exception e){}
            }
        } catch (Exception e) {}
        try {
            agency_description = agency.findElement(By.xpath("//*[@id=\"agency\"]/p[3]")).getText();
        } catch (Exception e) {}

        agencys_list.add(new Agency(agency_name, agency_type, agency_abbreviation, agency_administration, agency_founded, agency_launchers, agency_spacecraft, agency_country, agency_description));
    } // * ✓ Acabado
    public void getLaunch(List<Launch> launches_list) throws IOException {
        String launch_title = "", launch_status = "", launch_date = "", rocket_name = "", agency_name = "", location_name = "unknown";
        WebElement launch_details = driver.findElement(By.id("launch-details"));
        try {
            launch_title = launch_details.findElement(By.className("entry-title")).getText();
            launch_title.replace(",","");
        } catch (Exception e){}
        try {
            launch_status = launch_details.findElement(new By.ByXPath("//*[@id=\"launch-details\"]/div[2]/div/div[2]")).getText();
            launch_status = launch_status.replace("STATUS","").replace("LAUNCH","").replace("\n","").trim();
        } catch (Exception e){}
        try {
            launch_date = launch_details.findElement(By.className("launchDateTime")).getText();
            launch_date = launch_date.replace("\n"," - ").replace("•","").trim();
        } catch (Exception e){}
        try {
            rocket_name = launch_details.findElement(By.className("mt-2")).getText();
        } catch (Exception e){}
        try {
            agency_name = launch_details.findElement(By.className("pb-3")).getText();
        } catch (Exception e){}
        try {
            WebElement location = driver.findElement(By.id("location"));
            location_name = location.findElement(By.className("stretched-link")).getText();
        } catch (Exception e){}

        launches_list.add(new Launch(launch_title, launch_status, launch_date, rocket_name, agency_name, location_name));
    } // * ✓ Acabado
    public void getLocation(List<Location> locations_list) throws IOException {
        String launch_name = "unknown", location_name = "unknown", location_location = "unknown", rockets_launched = "unknown";
        WebElement location = driver.findElement(By.id("location"));
        try {
            launch_name = driver.findElement(By.xpath("//*[@id=\"launch-details\"]/h1")).getText();
        } catch (Exception e) {}
        try {
            location_name = location.findElement(By.tagName("h3")).getText();
        } catch (Exception e) {}
        try {
            location_location = location.findElement(By.className("h6")).getText().replace(","," -").trim();
        } catch (Exception e) {}
        try {
            String[] tmpLocation = location.findElement(By.className("mt-4")).getText().split(" ");
            rockets_launched = tmpLocation[0];
        } catch (Exception e) {}

        locations_list.add(new Location(launch_name,location_name,location_location,rockets_launched));
    } // * ✓ Acabado
    public void getMission(List<Mission> missions_list) throws IOException {
        String rocket_name = "unknown", mission_name = "unknown", mission_type = "unknown", mission_launch_cost = "unknown", mission_description = "unknown";
        try {WebElement mission = driver.findElement(By.id("mission"));
            try {
                rocket_name = driver.findElement(By.xpath("//*[@id=\"launch-details\"]/h1")).getText();
            } catch (Exception e) {}
            try {
                mission_name = mission.findElement(By.className("h5")).getText();
            } catch (Exception e) {}
            try {
                List<String> mission_info = new ArrayList<String>(Arrays.asList(mission.findElement(By.xpath("//*[@id=\"mission\"]/div/div/p[1]")).getText().split("\n")));
                for(String s : mission_info){
                    List<String> mission_info_detail = new ArrayList<>(Arrays.asList(s.split(":")));
                    switch (mission_info_detail.get(0).trim()) {
                        case "Type" -> mission_type = mission_info_detail.get(1).trim();
                        case "Launch Cost" -> mission_launch_cost = mission_info_detail.get(1).trim();
                    }
                }
            } catch (Exception e) {}
            try {
                mission_description = mission.findElement(By.xpath("//*[@id=\"mission\"]/div/div/p[2]")).getText();
            } catch (Exception e) {}
        } catch (Exception e) {}

        missions_list.add(new Mission(rocket_name, mission_name, mission_type, mission_launch_cost, mission_description));
    } // * ✓ Acabado
    public void getRocket(List<Rocket> rockets_list) throws IOException {
        String agency_name = "unknown", rocket_name = "unknown", rocket_family = "unknown", rocket_length = "unknown", rocket_diameter = "unknown", rocket_launch_mass = "unknown", rocket_low_earth_orbit_capacity = "unknown", rocket_description = "unknown";
        WebElement rocket = driver.findElement(By.id("rocket"));
        try {
            agency_name = driver.findElement(By.xpath("//*[@id=\"agency\"]/h3")).getText();
        } catch (Exception e) {}
        try {
            rocket_name = rocket.findElement(By.className("h5")).getText();
        } catch (Exception e) {}
        try {
            List<String> rocket_info = new ArrayList<String>(Arrays.asList(rocket.findElement(By.xpath("//*[@id=\"rocket\"]/p[1]")).getText().split("\n")));
            for(String s : rocket_info){
                List<String> rocket_info_detail = new ArrayList<>(Arrays.asList(s.split(":")));
                switch (rocket_info_detail.get(0).trim()) {
                    case "Family" -> rocket_family = rocket_info_detail.get(1).trim();
                    case "Length" -> rocket_length = rocket_info_detail.get(1).trim();
                    case "Diameter" -> rocket_diameter = rocket_info_detail.get(1).trim();
                    case "Launch Mass" -> rocket_launch_mass = rocket_info_detail.get(1).trim();
                    case "Low Earth Orbit Capacity" -> rocket_low_earth_orbit_capacity = rocket_info_detail.get(1).trim();
                }
            }
        } catch (Exception e) {}
        try {
            rocket_description = rocket.findElement(By.xpath("//*[@id=\"rocket\"]/p[2]")).getText();
        } catch (Exception e) {}

        rockets_list.add(new Rocket(agency_name, rocket_name, rocket_family, rocket_length, rocket_diameter, rocket_launch_mass, rocket_low_earth_orbit_capacity, rocket_description));
    } // * ✓ Acabado
}