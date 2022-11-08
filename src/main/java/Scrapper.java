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

    public void getPosts(){
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

    public void getInfoPost(List<String> allURLs){
        for (String url: allURLs){
            driver.get(url);

        }
    }

    public void launchToCSV() throws IOException {
        FileWriter fw = new FileWriter("out/launch.csv");
        BufferedWriter bf = new BufferedWriter(fw);

        bf.write("\"launch_name\",\"launch_status\",\"launch_date\",\"mision_name\",\"rocket_name\",\"agency_name\",\"location_name\"");
        bf.write("\n\"a\",\"a\",\"a\",\"a\",\"a\",\"a\",\"a\",\"a\",\"a\"");
        bf.close();

    }

}