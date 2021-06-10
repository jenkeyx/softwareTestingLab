import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FeedPage {
    public WebDriver driver;

    public FeedPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@href='/explore/']")
    public WebElement explore;

    @FindBy(xpath = "//a[@href='/direct/inbox/']")
    public WebElement directMessages;

    @FindBy(xpath = "//div[@class='_7WGDw']/div/div/div[1]/a")
    public WebElement firstChat;

    @FindBy(xpath = "//div[@class='K6yM_ ']/div/div[1]/div[1]/div/a")
    public WebElement somePost;

    public void selectChat(){
        directMessages.click();
        firstChat.click();
    }

}
