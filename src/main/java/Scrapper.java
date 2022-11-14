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

    public void getPosts() throws IOException {
        driver.get("https://www.spacelaunchschedule.com");
        List<WebElement> posts = driver.findElements(By.className("my-2"));
        // ? Recorre todos los posts cogiendo todos los URLS
        List<String> allURLs = getAllURL(posts);
        getInfoPost(allURLs);
    }

    public List<String> getAllURL(List <WebElement> posts){
        List<String> URLs = new ArrayList<>();
        for(WebElement post : posts){
            URLs.add(post.findElement(By.className("stretched-link")).getAttribute("href"));
        }
        return URLs;
    }

    public void getInfoPost(List<String> allURLs) throws IOException {
        writerCSV(allURLs);
    }

    public void launchToCSV(BufferedWriter bf) throws IOException {
        WebElement mission = driver.findElement(By.id("mission"));
        String launch_title = "", launch_status = "", launch_date = "", rocket_name = "", agency_date = "", location_name = "";
        try {launch_title = mission.findElement(By.className("h5")).getText();} catch (Exception e){}
        try {launch_status = mission.findElement(By.xpath("//*[@id=\"launch-details\"]/div[2]/div/div[2]")).getText();} catch (Exception e){}
        try {launch_status = mission.findElement(By.xpath("//*[@id=\"launch-details\"]/div[3]/div/div[2]/time/text()[1]")).getText();} catch (Exception e){}

        bf.write("\n\"" + launch_title + "\",\"" + launch_status + "\",\"" + launch_date);
    }

    public void writerCSV(List<String> allURLs) throws IOException {
        FileWriter fw = new FileWriter("out/launch.csv");
        BufferedWriter bf = new BufferedWriter(fw);
        for (String url : allURLs){
            driver.get(url);
            launchToCSV(bf);
        }
        bf.close();

    }

}