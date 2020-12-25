package utilities;

import io.github.sukgu.*;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WebDriverUtility {
    /*
    This is a WebDriver utility class that hels in interacting with the WebElements
     */

    private WebDriver wd;
    private WebDriverWait wdw;
    private Shadow s;

    public WebDriverUtility(WebDriver webdriver) {
        this.wd = webdriver;
        this.wdw = new WebDriverWait(webdriver, 45L);
        s = new Shadow(webdriver);
    }

    public void click(By locator) {
        try {
            this.wd.findElement(locator).click();
        } catch (StaleElementReferenceException var2) {
            this.forceWait(1);
        } catch (Exception var3) {
            System.out.println("Cannot click on element " + locator);
            throw var3;
        }
    }

    public void waitAndClick(By locator) {
        try {
            this.waitForElementVisible(locator);
            this.wd.findElement(locator).click();
        } catch (Exception var3) {
            System.out.println("Cannot click on element " + locator);
            throw var3;
        }
    }

    public void waitForElementVisible(By locator) {
        this.wdw.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.highlight(locator);
    }

    public void waitForElementNotVisible(By locator) {
        this.wdw.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public String getPageTitle() {
        String title = "";
        try {
            title = wd.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("Unable to fetch page title"));
        } finally {
            return title;
        }
    }

    public void highlight(By locator) {
        ((JavascriptExecutor) this.wd).executeScript("arguments[0].style.border='3px solid red'", new Object[]{this.wd.findElement(locator)});
    }

    public WebElement getElement(By locator) {
        return this.wd.findElement(locator);
    }

    public List< WebElement > getElementsFromAnotherElement(WebElement elementLocator, By locator) {
        return elementLocator.findElements(locator);
    }

    public void type(By locator, String var2) {
        try {
            this.wd.findElement(locator).sendKeys(new CharSequence[]{var2});
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public void forceWait(int seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public void switchToWindow(String windowName) {
        Iterator var2 = this.wd.getWindowHandles().iterator();

        while (var2.hasNext()) {
            String var3 = (String) var2.next();
            this.wd.switchTo().window(var3);
            if (this.wd.getCurrentUrl().contains(windowName)) {
                break;
            }
        }
    }

    public Boolean isDisplayed(By locator) {
        try {
            WebElement var3 = this.wd.findElement(locator);
            this.wd.manage().timeouts().implicitlyWait(40, SECONDS);
            return var3.isDisplayed();
        } catch (Exception var2) {
            this.wd.manage().timeouts().implicitlyWait(40L, SECONDS);
            var2.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean isNotDisplayed(By locator) {
        try {
            WebElement var3 = this.wd.findElement(locator);
            this.wd.manage().timeouts().implicitlyWait(15000, MILLISECONDS);
            forceWait(5);
            if (var3.isDisplayed()) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } catch (Exception var2) {
            this.wd.manage().timeouts().implicitlyWait(40L, SECONDS);
            var2.printStackTrace();
            return Boolean.TRUE;
        }
    }

    public String captureScreenshot() {
        try {
            File locator = (File) ((TakesScreenshot) this.wd).getScreenshotAs(OutputType.FILE);
            String var2 = UUID.randomUUID().toString() + ".jpg";
            String var3 = "./ScreenshotOutput/" + var2;
            File var5 = new File(var3);
            FileUtils.copyFile(locator, var5);
            return var5.getAbsolutePath();
        } catch (Exception var4) {
            System.out.println("Capture Screenshot Failed " + var4.getMessage());
            return "";
        }
    }

    public void navigateToURL(String url) {
        try {
            forceWait(15);
            this.wd.navigate().to(new URL(url));
            this.wd.manage().window().maximize();
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public void clear(By locator) {
        try {
            this.wd.findElement(locator).clear();
        } catch (Exception var3) {
            var3.printStackTrace();
            Assert.fail("Could not clear value " + locator);
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            if(wd.findElements(locator).size()!=0) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void moveToDefaultContent() {
        this.wd.switchTo().defaultContent();
    }

    public Boolean verifyTitle(String expectedPageTitle) {
        Boolean result = false;
        try {
            String actualPageTitle = getPageTitle();
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(actualPageTitle.equals(expectedPageTitle),"Page title verification");
            result = true;
        }
        catch (AssertionError a) {
            a.printStackTrace();
            System.out.println("Assertion failed");
            result = false;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to verify the page title");
        }
        finally {
            return result;
        }
    }

    public Object fluentWait(By locator) {
        Wait fWait = new FluentWait(wd).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        return fWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}