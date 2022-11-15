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
        getLaunchesCSV(allURLs);
    }

    // ? Escribe toda la información sobre Launch de todos los URLS.
    public void getLaunchesCSV(List<String> URLS) throws IOException {
        FileWriter fw = new FileWriter("out/launch.csv");
        BufferedWriter bf = new BufferedWriter(fw);
        bf.write("\"launch_name\",\"launch_status\",\"launch_date\",\"rocket_name\",\"agency_name\",\"location_name\"\n");
        for (String url : URLS){
            driver.get(url);
            getLaunchCSV(bf);
        }
        bf.close();
    }

    // ? Escribe toda la información sobre Launch de 1 URLS.
    public void getLaunchCSV(BufferedWriter bf) throws IOException {
        String launch_title = "", launch_status = "", launch_date = "", rocket_name = "", agency_name = "", location_name = "";
        WebElement launch_details = driver.findElement(By.id("launch-details"));
        try {
            launch_title = launch_details.findElement(By.className("entry-title")).getText();
            launch_title.replace(",","");
            System.out.println("INFO - launch_title");
        } catch (Exception e){}
        try {
            launch_status = launch_details.findElement(new By.ByXPath("//*[@id=\"launch-details\"]/div[2]/div/div[2]")).getText();
            launch_status = launch_status.replace("STATUS","").replace("LAUNCH","").replace("\n","").trim();
            System.out.println("INFO - launch_status");
        } catch (Exception e){}
        try {
            launch_date = launch_details.findElement(By.className("launchDateTime")).getText();
            launch_date = launch_date.replace("\n"," - ").replace("•","").trim();
            System.out.println("INFO - launch_date");
        } catch (Exception e){}
        try {
            rocket_name = launch_details.findElement(By.className("mt-2")).getText();
            System.out.println("INFO - rocket_name");
        } catch (Exception e){}
        try {
            agency_name = launch_details.findElement(By.className("pb-3")).getText();
            System.out.println("INFO - agency_name");
        } catch (Exception e){}
        try {
            WebElement location = driver.findElement(By.id("location"));
            location_name = location.findElement(By.className("stretched-link")).getText();
            System.out.println("INFO - location_name");
        } catch (Exception e){}
        bf.write("\"" + launch_title + "\",\"" + launch_status + "\",\"" + launch_date + "\",\"" + rocket_name + "\",\"" + agency_name + "\",\"" + location_name + "\"\n");
    }


}