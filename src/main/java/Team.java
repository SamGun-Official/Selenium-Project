import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Team {
    WebDriver driver;
    WebDriverWait wait;

    public Team(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void UpdateTeamSection() throws InterruptedException {
        // REDIRECT TO TEAM
        driver.get("https://gruplm.com/user/team_section?language=en");
        HelperFunctions.waitDomReady(driver, wait);

        // INPUT TEAM SECTION TITLE
        WebElement title = driver.findElement(By.xpath("//input[@id='team_section_title']"));
        title.clear();
        title.sendKeys("KELOMPOK 2");

        // INPUT TEAM SECTION SUBTITLE
        WebElement subtitle = driver.findElement(By.xpath("//input[@id='team_section_subtitle']"));
        subtitle.clear();
        subtitle.sendKeys("PROJECT SOFTWARE TESTING");

        // UPDATE
        subtitle.sendKeys(Keys.RETURN);

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }
}
