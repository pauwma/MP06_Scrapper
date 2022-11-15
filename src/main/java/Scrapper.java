import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scrapper {
    WebDriver driver;
    public Scrapper(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }

    // ? Obtiene todos los posts de la primera página.
    public void getPosts() throws IOException {
        driver.get("https://www.spacelaunchschedule.com");
        List<WebElement> posts = driver.findElements(By.className("my-2"));
        // ? Recorre todos los posts cogiendo todos los URLS
        List<String> allURLs = getAllURL(posts);
        getInfoPost(allURLs);
    }

    // ? Obtiene todos los CSV de todos los post.
    public List<String> getAllURL(List <WebElement> posts){
        List<String> URLs = new ArrayList<>();
        for(WebElement post : posts){
            URLs.add(post.findElement(By.className("stretched-link")).getAttribute("href"));
        }
        return URLs;
    }

    // ? Obtiene todos los CSV de 1 post.
    public void getInfoPost(List<String> allURLs) throws IOException {
        FileWriter launchCSV_FileWriter = new FileWriter("out/launch.csv");
        BufferedWriter launchCSV_BufferedWriter = new BufferedWriter(launchCSV_FileWriter);
        launchCSV_BufferedWriter.write("\"launch_name\",\"launch_status\",\"launch_date\",\"rocket_name\",\"agency_name\",\"location_name\"\n");
        for(String url : allURLs){
            driver.get(url);
            getLaunchCSV(launchCSV_BufferedWriter);
            getAgencyCSV(launchCSV_BufferedWriter);
        }
        launchCSV_BufferedWriter.close();

    }

    // ? Escribe toda la información sobre Launch de 1 URLS.
    public void getLaunchCSV(BufferedWriter bf) throws IOException {
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
        bf.write("\"" + launch_title + "\",\"" + launch_status + "\",\"" + launch_date + "\",\"" + rocket_name + "\",\"" + agency_name + "\",\"" + location_name + "\"\n");
    }
    public void getAgencyCSV(BufferedWriter bf) throws IOException {
        String agency_name = "unknown", agency_type = "unknown", agency_abbreviation = "unknown", agency_administration = "unknown", agency_founded = "unknown", agency_launchers = "unknown", agency_country = "unknown", agency_description = "unknown";
        WebElement launch_details = driver.findElement(By.id("launch-details"));
        try {
            launch_title = launch_details.findElement(By.className("entry-title")).getText();
            launch_title.replace(",","");
        } catch (Exception e){}

        bf.write("\"" + agency_name + "\",\"" + agency_type + "\",\"" + agency_abbreviation + "\",\"" + agency_administration + "\",\"" + agency_name + "\",\"" + agency_founded + "\"\n");



}