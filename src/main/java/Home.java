import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    WebDriver driver;
    WebDriverWait wait;

    public Home(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void AddHeroSection() throws InterruptedException {
        // REDIRECT TO HERO SECTION
        driver.get("https://gruplm.com/user/home_page/hero/static_version?language=en");
        HelperFunctions.waitDomReady(driver, wait);

        // ADD IMAGE
        driver.findElement(By.id("image")).sendKeys(System.getProperty("user.dir") + "/src/images/nih_babi.jpg");

        // ADD TITLE
        driver.findElement(By.xpath("//input[@placeholder='Enter title']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter title']")).sendKeys("PT Adiputro Wirasejati");

        // ADD SUBTITLE
        driver.findElement(By.xpath("//input[@placeholder='Enter subtitle']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter subtitle']")).sendKeys("odisamgunanderson");

        // ADD HERO TEXT
        driver.findElement(By.xpath("//textarea[@placeholder='Enter text']")).clear();
        driver.findElement(By.xpath("//textarea[@placeholder='Enter text']")).sendKeys("SoftwareTesting");

        // ADD BUTTON NAME
        driver.findElement(By.xpath("//input[@placeholder='Enter button name']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter button name']")).sendKeys("Hehe OK");

        // ADD BUTTON URL
        driver.findElement(By.xpath("//input[@placeholder='Enter button url']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter button url']")).sendKeys("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        // SUBMIT
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }

    public void AddHomeSection() throws InterruptedException {
        // REDIRECT TO HOME SECTION
        driver.get("https://gruplm.com/user/home-page-text/edit?language=en");
        HelperFunctions.waitDomReady(driver, wait);

        // ADD PORTOFOLIO SECTION
        driver.findElement(By.xpath("//input[@placeholder='Enter portfolio title']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter portfolio title']")).sendKeys("ini portfolio title");
        driver.findElement(By.xpath("//input[@placeholder='Enter Portfolio Subtitle']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter Portfolio Subtitle']")).sendKeys("ini portfolio subtitle");

        // ADD TESTIMONIAL SECTION
        driver.findElement(By.xpath("//input[@name='testimonial_title']")).clear();
        driver.findElement(By.xpath("//input[@name='testimonial_title']")).sendKeys("ini testimonial title");
        driver.findElement(By.xpath("//input[@name='testimonial_subtitle']")).clear();
        driver.findElement(By.xpath("//input[@name='testimonial_subtitle']")).sendKeys("ini testimonial subtitle");

        // ADD BLOG SECTION
        driver.findElement(By.xpath("//input[@placeholder='Enter blog keyword']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter blog keyword']")).sendKeys("ini blog keyword");
        driver.findElement(By.xpath("//input[@placeholder='Enter blog title']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter blog title']")).sendKeys("ini blog title");
        driver.findElement(By.xpath("//input[@placeholder='Enter view all blog text']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter view all blog text']")).sendKeys("ini blog text");

        // ADD FAQ SECTION
        driver.findElement(By.xpath("//input[@placeholder='Enter faq section title']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter faq section title']")).sendKeys("ini faq title");
        driver.findElement(By.xpath("//input[@placeholder='Enter faq section subtitle']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter faq section subtitle']")).sendKeys("ini faq subtitle");

        // ADD QUOTES SECTION
        driver.findElement(By.xpath("//input[@placeholder='Enter quote section title']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter quote section title']")).sendKeys("ini quotes title");
        driver.findElement(By.xpath("//input[@placeholder='Enter quote section subtitle']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Enter quote section subtitle']")).sendKeys("ini quotes subtitle");

        // SUBMIT
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }

    public void AddVideo() throws InterruptedException {
        // REDIRECT TO VIDEO SECTION
        driver.get("https://gruplm.com/user/home-page/video?language=en");
        HelperFunctions.waitDomReady(driver, wait);

        // ADD BACKGROUND IMG
        driver.findElement(By.id("image")).sendKeys(System.getProperty("user.dir") + "/src/images/tuhkan_babi.jpg");

        // ADD URL
        driver.findElement(By.xpath("//input[@name='video_section_url']")).clear();
        driver.findElement(By.xpath("//input[@name='video_section_url']")).sendKeys("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        // SUBMIT
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }
}
