import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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

        for (WebElement post: posts){
            String nombreMision = post.findElement(By.className("entry-title")).getText();
            nombreMision = nombreMision.split("\n")[0];
            nombreMision.replace("\n", "").replace("\r", "");
            String coheteMision = post.findElement(By.className("mt-2")).getText();
            coheteMision.replace("\n", "").replace("\r", "");
            String compañiaMision = post.findElement(By.className("mb-3")).getText();
            compañiaMision.replace("\n", "").replace("\r", "");

            System.out.println(nombreMision + " - " + coheteMision + " - " + compañiaMision);
        }
    }
}