import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    String username;
    String password;
    String code;

    public LoginPage(WebDriver driver,String username, String password, String  code) {
        PageFactory.initElements(driver, this);
        this.username = username;
        this.password = password;
        this.code = code;
    }

    @FindBy(xpath = "//*[@name =\"username\"]")
    public WebElement loginField;

    @FindBy(xpath = "//*[@name =\"password\"]")
    public WebElement passwordField;

    @FindBy(xpath = "//*[@type =\"submit\"]")
    public WebElement logInButton;

    @FindBy(xpath = "//*[@name = \"verificationCode\"]")
    public WebElement codeInput;

    @FindBy(xpath = "//button[text()='backup codes']")
    public WebElement codeTypeToggle;

    @FindBy(xpath = "//button[text()='Confirm']")
    public WebElement confirmButton;

    @FindBy(xpath = "//button[text()=\"Not Now\"]")
    public WebElement cancelButton;


    public void login() {
        loginField.sendKeys(username);
        passwordField.sendKeys(password);
        logInButton.click();
    }

    public void enterTheCode(){
        codeTypeToggle.click();
        codeInput.sendKeys(code);
        confirmButton.click();
    }

    public void closeBanner(){
        cancelButton.click();
    }
}

