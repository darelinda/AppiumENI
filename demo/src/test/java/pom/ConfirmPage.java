package pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ConfirmPage {

    @SuppressWarnings("unused")
    private AndroidDriver driver;


    public ConfirmPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Checkout Complete\"]")
    private WebElement confirmation_message;



    public boolean verifierConfirmationAchat () {
        //confirmation_message.isDisplayed();
        return confirmation_message.isDisplayed();
    }
    
    
}
