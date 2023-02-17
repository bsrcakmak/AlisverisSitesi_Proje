package siteproject.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class JSUtils {
    // TestBase'i abstract yapmamizin sebebi bu sinifin objesini olusturmak istemiyoruz
    // TestBase testbase = new TestBase ==> YAPILAMAZ
    // Amacimic bu sinifi extend etmek ve icindeki hazie methodlari kullanmak

    // driver objesini olustur. Driver ya public yada protected olmali.
    // Sebepi child classlarda gorulebilir olmasi
    protected static WebDriver driver;

    // setUp
    @Before
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Thread.sleep(20);  --> hard wait , tam 20 sn bekler, Java'dan gelir
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // 20 sn'ye kadar bekler, acildiginda kalan zamani beklemez, Selenium'dan gelir
    }

    //tearDown
    @After
    public void tearDown(){
        waitFor(5);
        // driver.close();
    }

/*
    MULTIPLE WINDOW :
  1 parametre alir String olarak : Gecis yapmak istedigimiz sayfanin title'i
Ornek:
  driver.get("https://testcenter.techproeducation.com/index.php?page=iframe");
  switchToWindow("New Window");
  switchToWindow("The Internet")
 */

    public static void switchToWindow(String targetTitle) {   // title'ini aldigi pencereye otomatik gecis yapar
        String origin = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(targetTitle)) {
                return;
            }
        }
        driver.switchTo().window(origin);
    }

    //windowNumber sıfır (0)'dan başlıyor.
    // index numarasini parametre olarak alir ve o index'li pencereye gecis yapar
    public static void switchToWindow(int windowNumber){
        List<String> list = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(list.get(windowNumber));
    }

    /*   HARD WAIT:
    @param : second
    */
    public static void waitFor(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //    ACTIONS_RIGHT CLICK  --> saga tikla methodu
    public static void rightClickOnElementActions(WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    //ACTIONS_DOUBLE CLICK  --> cift tiklama methodu
    public static void doubleClick(WebElement element) {
        new Actions(driver).doubleClick(element).build().perform();
    }

    //    ACTIONS_HOVER_OVER   --> bir webwlwmntin uzerine mouseu goturmek icin
    public static void hoverOverOnElementActions(WebElement element) {
        //        Actions actions = new Actions(driver);
        new Actions(driver).moveToElement(element).perform();
    }

    //    ACTIONS_SCROLL_DOWN
    public static void scrollDownActions() {
        //        Actions actions = new Actions(driver);
        new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
    }


    //    ACTIONS_SCROLL_UP
    public static void scrollUpActions() {
        //        Actions actions = new Actions(driver);
        new Actions(driver).sendKeys(Keys.PAGE_UP).perform();
    }

    //    ACTIONS_SCROLL_RIGHT
    public static void scrollRightActions(){
        new Actions(driver).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).perform();
    }

    //    ACTIONS_SCROLL_LEFT
    public static void scrollLeftActions(){
        new Actions(driver).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT).perform();
    }

    //    ACTIONS_DRAG_AND_DROP
    public static void dragAndDropActions(WebElement source, WebElement target) {
        //        Actions actions = new Actions(driver);
        new Actions(driver).dragAndDrop(source,target).perform();
    }

    //    ACTIONS_DRAG_AND_DROP_BY  --> istedigin yere elementi al ve surukle
    public static void dragAndDropActions(WebElement source, int x, int y) {
        //        Actions actions = new Actions(driver);
        new Actions(driver).dragAndDropBy(source,x,y).perform();
    }


    //          DYNAMIC SELENIUM WAITS:
    //=============== Explicit Wait ==============//
    public static WebElement waitForVisibility(WebElement element, int timeout) {   // beklemek istedigimiz eleman ve max bekleme suresi variable'lar
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {  // bir locater icin su kadar surede bekle
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeout) { // bir elemanin tiklanabilirligini su kadar sure dene
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) { // bir locater'in tiklanabilirligini su kadar sure dene
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void clickWithTimeOut(WebElement element, int timeout) { // bir elemanin tiklanabilirligine hard wait olarak bakan method
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }

    //    This can be used when a new page opens --> yeni sayfaya gecislerde kullanilabilir
    public static void waitForPageToLoad(long timeout) {  // sayfanin tamamen dolup dolmadigini kontrol icin kullanilir
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }

    // ============ Fluent Wait =============
    // params : xpath of teh element , max timeout in seconds, polling in second
    public static WebElement fluentWait(String xpath, int withTimeout, int pollingEvery) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(withTimeout))//Wait 3 second each time
                .pollingEvery(Duration.ofSeconds(pollingEvery))//Check for the element every 1 second
                .withMessage("Ignoring No Such Element Exception")
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element;
    }


    //   SCREENSHOTS
    public static void takeScreenShotOfPage() throws IOException {
//        1. Take screenshot
        File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

//       2. Save screenshot
//        getting the current time as string to use in teh screenshot name, previous screenshots will be kept
        String currentTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

//        Path of screenshot save folder               folder / folder    /file name
        String path = System.getProperty("user.dir") + "/test-output/Screenshots/" + currentTime + "image.png";
        FileUtils.copyFile(image, new File(path));
    }


    //    SCREENSHOT
//    @params: WebElement
//    takes screenshot
    public static void takeScreenshotOfElement(WebElement element) throws IOException {
//        1. take screenshot
        File image = element.getScreenshotAs(OutputType.FILE);
//        2. save screenshot
//        path
        String currentTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String path = System.getProperty("user.dir")+"/test-output/Screenshots/"+currentTime+"image.png";
        FileUtils.copyFile(image,new File(path));
    }


    // SCROLLINTOVIEW-JS METHOD'U
    public static void scrollIntoViewJS(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }


    // SAYFANIN EN ALTINA IN
    // Bu method ile sayfanin en altina inebiliriz
    public static void scrollEndJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        // sayfanin uzunlugu boyunca asagi in
    }

    // SAYFANIN EN USTUNE CIK
    // Bu method ile sayfanin en ustune cikabiliriz
    public static void scrollTopJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
        // sayfa yuksekliginin tersi yonunde git yani en uste cik (o nedenle onune - aldi)
    }

    // Bu methoid ile belirli bir elemente JS executor ile tiklanabilir
    public static void clickByJS(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",element);
    }

    // Girmis oldugumuz metni elemente yazdirir
    // bu method sendKeys method'una bir alternatiftir
    // sendKeys her zaman oncelikli tercihimizdir
    public static void typeWithJS(WebElement element, String metin){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value','"+metin+"');",element);
    }


    // input elementindeki degerleri al
    // return
    //document HTML e git
    // .getElementById('"+idOfElement+"') id si verilen elementi bul
    // .value")
    // .toString()
    public static void getValueByJS(String idOfElement){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String metin =js.executeScript("return document.getElementById('"+idOfElement+"').value").toString();
        System.out.println("Kutudaki deger: "+metin);

        // NOT: document.querySelector("p").value;  -> TAG KULLANILABILIR
        //      document.querySelector(".example").value; -> CSS DEGERI KULLANILABILIR
        //      document.querySelector("#example").value; -> CSS DEGERI KULLANILABILIR

    }

}
