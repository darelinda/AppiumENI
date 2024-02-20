package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;
import pom.*;
import utils.Config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;

public class TotoTeste {

    AppiumDriverLocalService service;
    AndroidDriver driver;

    @Test
    public void myFirstBAgs() throws MalformedURLException {
        startAppiumServer();
        startEmulatorSession();

        ProductPage pp = new ProductPage(driver);
        pp.selectionner_article();
        for(int i=0;i<12;i++){
            pp.ajouter_article_au_panier();
        }
        pp.selectionner_couleur_article();
        pp.ajouter_article_au_panier();

        //cartePage
        CartPage cp = new CartPage(driver);
        cp.aller_vers_panier();
        cp.cliquer_btn_checkout();

        //loginPage
        LoginPage lp = new LoginPage(driver);
        lp.seConnecter("bob@example.com", "10203040");

        //formePage
        FormPage fp = new FormPage(driver);
        fp.saisirNomComplet("bob");
        fp.saisirAddresse("20 rue de la joie");
        fp.saisirVille("Rennes");
        fp.saisirCodePostal("35200");
        fp.saisirPays("france");
        fp.cliquerBtnPayment();

        //payment
        PaymentPage payP = new PaymentPage(driver);
        payP.saisirNomComplet("bob");
        payP.saisirCB("325812657568789");
        payP.saisirExpDate("03/25");
        payP.saisirCode("123");
        payP.cliquerBtnReviewOrder();
        payP.cliquerBtnPlaceOrder();

        //confirmpage

        ConfirmPage confP = new ConfirmPage(driver);
        boolean result = confP.verifierConfirmationAchat();
        if (result){
            System.out.println("vos 12 arrivent tres tres vite");
        }else{
            System.out.println("verifier que vos n'etes pas sur un site pirate");
        }
    }

    public  void startEmulatorSession() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        // options.setUdid("ENUM630010");

        options.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.rn");
        options.setCapability("appium:appActivity", ".MainActivity");
        options.setCapability("platformName", "Android");
        options.setCapability("appium:platformVersion", "14");
        options.setCapability("appium:automationName", "UiAutomator2");
        options.setCapability("appium:deviceName", "emulator-5554");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public AppiumDriverLocalService startAppiumServer() {
        boolean running = isServerRunning(Config.appiumPort);
        if (!running) {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File(Config.appiumJSExecutor))
                    .withIPAddress(Config.appiumServer)
                    .withLogFile(new File(Config.appiumLog))
                    .withTimeout(Duration.ofSeconds(Config.appiumServerTimeOut))
                    .usingPort(Config.appiumPort).build();
            service.start();
        }
        return service;
    }
    public boolean isServerRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSock;
        try {
            serverSock = new ServerSocket(port);
            System.out.println("serverSock :"+ serverSock);
            serverSock.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSock = null;
        }
        return isServerRunning;
    }
}
