import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
public class Home {
    WebDriver driver;
    public Home(WebDriver driver) {
        this.driver = driver;
    }

    public void AddHeroSection() throws InterruptedException {
        // REDIRECT TO Hero section
        driver.get("https://gruplm.com/user/home_page/hero/static_version?language=en");

        // add image
        driver.findElement(By.id("image")).sendKeys(System.getProperty("user.dir") + "/src/images/nih_babi.jpg");
        Thread.sleep(500);

        // add title
        driver.findElement(By.xpath("//input[@placeholder='Enter title']")).sendKeys("ADIputra");
        // add subtitle
        driver.findElement(By.xpath("//input[@placeholder='Enter subtitle']")).sendKeys("odisamgunanderson");
        // add Hero text
        driver.findElement(By.xpath("//textarea[@placeholder='Enter text']")).sendKeys("SoftwareTesting");
        // add Button Name
        driver.findElement(By.xpath("//input[@placeholder='Enter button name']")).sendKeys("hehe");
        // add Button url
        driver.findElement(By.xpath("//input[@placeholder='Enter button url']")).sendKeys("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        // submit
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void AddHomeSection() throws InterruptedException {
        // REDIRECT TO Homesection
        driver.get("https://gruplm.com/user/home-page-text/edit?language=en");

        // add portofolio section
        driver.findElement(By.xpath("//input[@placeholder='Enter portfolio title']")).sendKeys("ini portfolio title");
        driver.findElement(By.xpath("//input[@placeholder='Enter Portfolio Subtitle']")).sendKeys("ini portfolio subtitle");
        // add testimonial section
        driver.findElement(By.xpath("//input[@name='testimonial_title']")).sendKeys("ini testimonial title");
        driver.findElement(By.xpath("//input[@name='testimonial_subtitle']")).sendKeys("ini testimonial subtitle");
        // add blog section
        driver.findElement(By.xpath("//input[@placeholder='Enter blog keyword']")).sendKeys("ini blog keyword");
        driver.findElement(By.xpath("//input[@placeholder='Enter blog title']")).sendKeys("ini blog title");
        driver.findElement(By.xpath("//input[@placeholder='Enter view all blog text']")).sendKeys("ini blog text");
        // add FAQ section
        driver.findElement(By.xpath("//input[@placeholder='Enter faq section title']")).sendKeys("ini faq title");
        driver.findElement(By.xpath("//input[@placeholder='Enter faq section subtitle']")).sendKeys("ini faq subtitle");
        // add Quotes Section
        driver.findElement(By.xpath("//input[@placeholder='Enter quote section title']")).sendKeys("ini Quotes title");
        driver.findElement(By.xpath("//input[@placeholder='Enter quote section subtitle']")).sendKeys("ini Quotes subtitle");
        // submit
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
    }

    public void AddVideo() throws InterruptedException {
        // REDIRECT TO Videp section
        driver.get("https://gruplm.com/user/home-page/video?language=en");

        // add background img
        driver.findElement(By.id("image")).sendKeys(System.getProperty("user.dir") + "/src/images/tuhkan_babi.jpg");
        // add url
        driver.findElement(By.xpath("//input[@name='video_section_url']")).sendKeys("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        // submit
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }


}
