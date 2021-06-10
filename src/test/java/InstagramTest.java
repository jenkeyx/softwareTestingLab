import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InstagramTest {
    public static WebDriver driver = new ChromeDriver();
    LoginPage loginPage;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://www.instagram.com");
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void loginError(){
        String username = "wrongUsername";
        String password = "wrongPassword";
        String code = null;
        LoginPage loginPage = new LoginPage(driver,username,password,null);

        loginPage.login();

        {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@data-testid = \"login-error-message\"]")
                    )
            );
        }

        assertNotNull(driver.findElement(By.xpath("//*[@data-testid = \"login-error-message\"]")));
    }

    @Test
    public void loginSuccess(){
        String username="rightUsername";
        String password="rightPassword";
        String code = "00000000";
        loginPage = new LoginPage(driver,username,password,code);
        WebDriverWait wait = new WebDriverWait(driver, 25);

        loginPage.login();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@aria-label = \"Security Code\"]")));
        loginPage.enterTheCode();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()=\"Not Now\"]")));
        loginPage.closeBanner();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"piCib\"]")));
        loginPage.closeBanner();

        assertEquals(driver.getCurrentUrl(), "https://www.instagram.com/");
    }

    @Test
    public void messages(){
        String username="rightUsername";
        String password="rightPassword";
        String code = "00000000";
        loginPage = new LoginPage(driver,username,password,code);
        FeedPage feedPage = new FeedPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 25);

        loginPage.login();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@aria-label = \"Security Code\"]")));
        loginPage.codeTypeToggle.click();
        loginPage.codeInput.sendKeys(code);
        loginPage.confirmButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()=\"Not Now\"]")));
        loginPage.closeBanner();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"piCib\"]")));
        loginPage.closeBanner();

        feedPage.selectChat();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='uueGX']")));
        feedPage.selectChat();

        assertEquals(driver.getCurrentUrl(),"https://www.instagram.com/direct/t/340282366841710300949128143776518363024");
    }

    @Test
    public void checkPost(){
        String username="rightUsername";
        String password="rightPassword";
        String code = "00000000";
        loginPage = new LoginPage(driver,username,password,code);
        FeedPage feedPage = new FeedPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 25);

        loginPage.login();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@aria-label = \"Security Code\"]")));
        loginPage.enterTheCode();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()=\"Not Now\"]")));
        loginPage.closeBanner();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"piCib\"]")));
        loginPage.closeBanner();

        feedPage.explore.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='K6yM_ ']")));
        feedPage.somePost.click();
    }
}
