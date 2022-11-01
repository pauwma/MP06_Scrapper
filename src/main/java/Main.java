import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.lang.model.util.Elements;
import java.io.File;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Scrapper scrapper = new Scrapper();

    scrapper.getPosts();

  }
}