package pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage {

    @SuppressWarnings("unused")
    private AndroidDriver driver;

    public ProductPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"store item text\" and @text=\"Sauce Labs Backpack\"]")
    private WebElement productItem;

    @CacheLookup
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"red circle\"]/android.view.ViewGroup")
    private WebElement product_color;

    @CacheLookup
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView")
    private WebElement btn_plus_one;

    @CacheLookup
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Add To Cart button\"]")
    private WebElement btn_AddToCart;       

	
    public void selectionner_article(){
        productItem.click();
    }       
    public void augmenter_quantité_article(){
        btn_plus_one.click();
    }       
    public void selectionner_couleur_article(){
        product_color.click();
    }       
    public void ajouter_article_au_panier(){
        btn_AddToCart.click();
    }

        // public void typePassword(String password) {
    //     passwordElement.sendKeys(password);
    // }  
  
    
}
