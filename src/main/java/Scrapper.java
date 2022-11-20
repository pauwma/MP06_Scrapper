import objetos.Agency;
import objetos.Launch;
import objetos.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import writers.WriterCSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
     public void getPosts() throws IOException {
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
    public void getInfoPost(List<String> allURLs) throws IOException {
        List<Launch> launches_list = new ArrayList<>();
        List<Agency> agencys_list = new ArrayList<>();
        List<Location> locations_list = new ArrayList<>();

        for(String url : allURLs){
            driver.get(url);
            getLaunchCSV(launches_list);
            WriterCSV.launchToCSV(launches_list);
            //getAgencyCSV(agencys_list);
            getLocationCSV(locations_list);
            WriterCSV.locationToCSV(locations_list);
        }
    }

    // ? Escribe toda la información sobre Launch de 1 URLS.
    public void getLaunchCSV(List<Launch> launches_list) throws IOException {
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
    }
    public void getAgencyCSV(List<Agency> agencys_list) throws IOException {
        String agency_name = "unknown", agency_type = "unknown", agency_abbreviation = "unknown", agency_administration = "unknown", agency_launchers = "unknown", agency_country = "unknown", agency_description = "none";
        int agency_founded = 0;
        WebElement agency = driver.findElement(By.id("agency"));
        try {
            agency_name = agency.findElement(By.className("h5")).getText();
        } catch (Exception e) {}
        try {
            agency_type = driver.findElement(new By.ByXPath("//*[@id=\"agency\"]/p[1]")).getText();
            List<String> agency_list = new ArrayList<String>(Arrays.asList(agency_type.split("\n")));
            for(String s : agency_list){
                List<String> agency_list_detail = new ArrayList<>(Arrays.asList(s.split(":")));
                switch (agency_list_detail.get(0).trim()) {
                    case "Type" -> agency_type = agency_list_detail.get(1).trim();
                    case "Abbreviation" -> agency_abbreviation = agency_list_detail.get(1).trim();
                    case "Administration" -> agency_administration = agency_list_detail.get(1)+": "+agency_list_detail.get(2);
                    case "Founded" -> agency_founded = Integer.parseInt(agency_list_detail.get(1).trim());
                    case "Launchers" -> agency_launchers = agency_list_detail.get(1).trim();
                    case "Country" -> agency_country = agency_list_detail.get(1).trim();
                }
            }
        } catch (Exception e) {}
        try {
            agency_description = agency.findElement(By.xpath("//*[@id=\"agency\"]/p[3]")).getText();
        } catch (Exception e) {}

        agencys_list.add(new Agency(agency_name, agency_type, agency_abbreviation, agency_administration, agency_founded, agency_launchers, agency_country, agency_description));
    }
    public void getLocationCSV(List<Location> locations_list) throws IOException {
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
            System.out.println(location_location);
        } catch (Exception e) {}
        try {
            rockets_launched = location.findElement(By.className("h6")).getText();
        } catch (Exception e) {}

        locations_list.add(new Location(launch_name,location_name,location_location,rockets_launched));
    } // ! Arreglar rockets_launched
}