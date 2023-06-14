import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Team {
    WebDriver driver;

    public Team(WebDriver driver) {
        this.driver = driver;
    }

    public void UpdateTeamSection() throws InterruptedException {
        // REDIRECT TO TEAM
        driver.get("https://gruplm.com/user/team_section?language=en");

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
        Thread.sleep(1000);
    }
}
