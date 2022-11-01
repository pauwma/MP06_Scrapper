import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import javax.swing.text.Element;
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
        getInfoPostInside(posts);
    }


    public void getInfoPostInside(List<WebElement> posts){
        for (WebElement post: posts){
            String urlPost = post.findElement(By.className("stretched-link")).getAttribute("href");
            driver.navigate().to(urlPost);

            String nombreMision = driver.findElement(By.className("entry-title")).getText();
            String coheteMision = driver.findElement(By.className("mt-2")).getText();
            String compañiaMision = driver.findElement(By.className("mb-3")).getText();
            String rocketInfo = driver.findElement(By.id("rocket")).getText();

            System.out.println("--------------------------\n"
                    + nombreMision + " - " + coheteMision + " - " + compañiaMision + "\n" +
                    rocketInfo);
            driver.navigate().to("https://www.spacelaunchschedule.com");
        }
    }

    public void getInfoPost(List<WebElement> posts){
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