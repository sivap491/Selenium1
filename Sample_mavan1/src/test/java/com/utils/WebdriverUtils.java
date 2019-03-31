package com.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WebdriverUtils {

    //<editor-fold desc="Variables">

    public static WebDriver driver = null;

    private static String testName;
    private static String sessionId;

    public static String getTestName() {
        return testName;
    }

    public static void setTestName(String methodName) {
        testName = methodName;
    }

    //</editor-fold>

    //<editor-fold desc="Browser Methods">

    public static WebDriver openBrowser() throws Exception {

        String sBrowserName;
        DesiredCapabilities capabilities;
        try {
            Log.info("Executing Open Browser Action", true);
            System.out.println("DEBUG INFO Values Browsers");
            //System.out.println("Env value: " + System.getenv("browser"));
            System.out.println("Properties File: " + utilities.getPropertyValue("browser"));

            testName = getTestName();
            sBrowserName = utilities.getPropertyValue("browser");
            Log.info("Browser: " + sBrowserName, true);
            BrowserEnum currentBrowser = BrowserEnum.valueOf(sBrowserName.toUpperCase());

            switch (currentBrowser) {

                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;

                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    System.setProperty("webdriver.chrome.driver", Constant.vChromeBrowserPath);
                    options.addArguments("--test-type");
                    options.addArguments("--start-maximized");
                    options.addArguments("--lang=en");
                    driver = new ChromeDriver(options);
                    break;

                case IE10:
                    capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setCapability(CapabilityType.VERSION, "10");
                    System.setProperty("webdriver.ie.driver", Constant.vIEBrowserPath);
                    driver = new InternetExplorerDriver(capabilities);
                    break;

                default:
                    String errorMessage = "The browser '" + sBrowserName + "' is not valid.";
                    Log.error(errorMessage);
                    throw new Exception(errorMessage);
            }
            maximizeWindow();
            Log.info("Browser was opened successfully", true);
        } catch (Exception ex) {
            Log.error("Class WebdriverUtils | Method openBrowser | Exception desc : " + ex.getMessage(), true);
            throw new Exception("Class WebdriverUtils | Method openBrowser | Exception desc : " + ex.getMessage());
        }
        return driver;
    }

    public static WebElement waitForElementToBeClickable(WebElement element, String name) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Log.info("Wait for element '" + name + "' is performed");
            return element;
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static WebElement waitForElementToBeClickable(WebElement element, String name, int waitSeconds) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Log.info("Wait for element '" + name + "' is performed");
            return element;
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    //</editor-fold>

    //<editor-fold desc="Wait Methods">

    public static WebElement waitForElementToBeClickable(By by) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            Log.info("Wait for element '" + by.toString() + "' is performed");
            return element;
        }
        catch(Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void waitForAllElementsLocatedBy(By by) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            Log.info("Wait for all elements located in " + by.toString() + " is performed");
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForAllElementsLocatedBy | Exception occurred while waiting for presence of all elements located by " + by.toString() + ". "
                    + e.getMessage());
            throw e;
        }
    }

    public static void waitForInvisibilityOfElementLocated(By by) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + by.toString() + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static void waitForInvisibilityOfElementLocated(By by, int waitTimeSec) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + by.toString() + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static void waitForVisibilityOfElementLocated(By by) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + by.toString() + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static void waitUntilElementIsDisplayed(By elementLocator) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + elementLocator.toString() + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static void waitUntilElementIsDisplayed(By elementLocator, int waitTimeSec) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + elementLocator.toString() + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static void waitUntilElementIsDisplayed(By elementLocator, WebElement parentElement) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.visibilityOf(parentElement.findElement(elementLocator)));
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + elementLocator.toString() + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static void waitUntilElementIsDisplayed(By elementLocator, WebElement parentElement, int waitTimeSec) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.visibilityOf(parentElement.findElement(elementLocator)));
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + elementLocator.toString() + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static void waitUntilElementIsDisplayed(WebElement wElement) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.visibilityOf(wElement));
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + wElement.toString() + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static void waitUntilElementIsDisplayed(WebElement wElement, int waitTimeSec) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.visibilityOf(wElement));
        } catch (Exception e) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
                    + wElement.toString() + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static void waitUntilElementIsEnabled(final By elementlocator) throws Exception {
        try {
            new WebDriverWait(driver, Constant.WaitingSeconds) {
            }.until(new ExpectedCondition<Boolean>() {
                //@Override
                public Boolean apply(WebDriver driver) {
                    return (driver.findElement(elementlocator).isEnabled());
                }
            });
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void waitUntilInstancesEqual(final By elementLocator, final int instances) throws Exception {
        try {
            new WebDriverWait(driver, Constant.WaitingSeconds) {
            }.until(new ExpectedCondition<Boolean>() {
                //@Override
                public Boolean apply(WebDriver driver) {
                    return (driver.findElements(elementLocator).size() == instances);
                }
            });
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void waitUntilInstancesEqual(final By elementLocator, final WebElement wParent, final int instances) throws Exception {
        try {
            new WebDriverWait(driver, Constant.WaitingSeconds) {
            }.until(new ExpectedCondition<Boolean>() {
                //@Override
                public Boolean apply(WebDriver driver) {
                    return (wParent.findElements(elementLocator).size() == instances);
                }
            });
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void waitUntilInstancesAreMoreThan(final By elementLocator, final int instances) throws Exception {
        try {
            new WebDriverWait(driver, Constant.WaitingSeconds) {
            }.until(new ExpectedCondition<Boolean>() {
                //@Override
                public Boolean apply(WebDriver driver) {
                    return (driver.findElements(elementLocator).size() > instances);
                }
            });
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void waitUntilInstancesAreMoreThan(final By elementLocator, final WebElement wParent, final int instances) throws Exception {
        try {
            new WebDriverWait(driver, Constant.WaitingSeconds) {
            }.until(new ExpectedCondition<Boolean>() {
                //@Override
                public Boolean apply(WebDriver driver) {
                    return (wParent.findElements(elementLocator).size() > instances);
                }
            });
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void waitUntilInstancesAreMoreThan(final By elementLocator, final WebElement wParent, final int instances, final int waitTimeSec) throws Exception {
        try {
            new WebDriverWait(driver, waitTimeSec) {
            }.until(new ExpectedCondition<Boolean>() {
                //@Override
                public Boolean apply(WebDriver driver) {
                    return (wParent.findElements(elementLocator).size() > instances);
                }
            });
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void waitUntilTextPresentInElement(By by, String text) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
            Log.info("Wait Until text Present in Element " + by.toString() + " is performed");
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitUntilTextPresentInElement | Exception occurred while waiting for presence of all elements located by " + by.toString() + ". "
                    + ex.getMessage());
            throw ex;
        }
    }

    public static void waitUntilValuePresentInElement(By by, String text) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementValue(by, text));
            Log.info("Wait Until text Present in Element " + by.toString() + " is performed");
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitUntilTextPresentInElement | Exception occurred while waiting for presence of all elements located by " + by.toString() + ". "
                    + ex.getMessage());
            throw ex;
        }
    }

    public static void waitUntilValuePresentInElement(WebElement wElement, String text) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementValue(wElement, text));
            Log.info("Wait Until text Present in Element " + wElement.toString() + " is performed");
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method waitUntilTextPresentInElement | Exception occurred while waiting for presence of all elements located by " + wElement.toString() + ". "
                    + ex.getMessage());
            throw ex;
        }
    }

    public static void normalizeWaitingTimeForElement() throws Exception {
        try {
            driver.manage().timeouts().implicitlyWait(Constant.WaitingSeconds, TimeUnit.SECONDS);
            Log.info("Set the implicit waiting time to " + Constant.WaitingSeconds
                    + " seconds");
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void waitForElementAfterClickMethod(By waitForLocator, By clickLocator, int ms, int retry) throws Exception {
        try {
            int count = 0;
            WebElement invoiceGrid = findElement(waitForLocator);
            while (invoiceGrid == null) {
                pauseTime(ms);
                invoiceGrid = findElement(waitForLocator);
                count++;
                if (count == retry) {
                    break;
                }
                WebdriverUtils.clickElement(findElement(clickLocator));
            }
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void waitForElementAfterClickMethod(By waitForLocator, WebElement wElement, int ms, int retry) throws Exception {
        try {
            int count = 0;
            WebElement invoiceGrid = findElement(waitForLocator);
            while (invoiceGrid == null) {
                pauseTime(ms);
                invoiceGrid = findElement(waitForLocator);
                count++;
                if (count == retry) {
                    break;
                }
                WebdriverUtils.clickElement(wElement);
            }
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void waitForElementAfterClickThroughJsMethod(By waitForLocator, WebElement wElement, int ms, int retry) throws Exception {
        try {
            int count = 0;
            WebElement invoiceGrid = findElement(waitForLocator);
            while (invoiceGrid == null) {
                pauseTime(ms);
                invoiceGrid = findElement(waitForLocator);
                count++;
                if (count == retry) {
                    break;
                }
                WebdriverUtils.clickButtonThroughJS(wElement, "Click Element");
            }
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void waitForElementAfterClickThroughJsMethod(By waitForLocator, By clickElementLocator, int ms, int retry) throws Exception {
        try {
            int count = 0;
            WebElement invoiceGrid = findElement(waitForLocator);
            while (invoiceGrid == null) {
                pauseTime(ms);
                invoiceGrid = findElement(waitForLocator);
                count++;
                if (count == retry) {
                    break;
                }
                WebElement clickElement = findElement(clickElementLocator);
                if (clickElement != null) {
                    WebdriverUtils.clickButtonThroughJS(clickElement, "Click Element");
                }
            }
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void implicitWait(int ms) throws Exception {
        try {
            driver.manage().timeouts().implicitlyWait(ms, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    //</editor-fold>

    //<editor-fold desc="Click Methods">

    public static void clickElement(WebElement element, String controlName) throws Exception {
        try {
            element.click();
            Log.info("Click action is performed on '" + controlName + "' button");
        }
        catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    
    public static void Page_Zoomin(int zoomlevel)
    {
    	for(int i=0; i<zoomlevel; i++){   
    		  driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
    		  }
    }
    
    public static void Page_ZoomOut(int zoomlevel)
    {
    	//To zoom out page 4 time using CTRL and - keys.
    	  for(int i=0; i<zoomlevel; i++){
    	   driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
    	  }
    }
    public static void clickElement(WebElement element) throws Exception {
        try {
            element.click();
            Log.info("Click action is performed...");
        }
        catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void clickElement(By by) throws Exception {
        try {
            driver.findElement(by).click();
            Log.info("Click action is performed...");
        }
        catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void clickAction(WebElement element) throws Exception {
        try {
            Actions action = new Actions(driver);
            action.click(element).perform();
        }
        catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void doubleClick(WebElement element) throws Exception {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).doubleClick().perform();
        }
        catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }


    public static void clickElementActions(WebElement element) throws Exception {
        try {
            Actions action = new Actions(driver);
            action.click(element).perform();
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void clickButtonThroughJS(WebElement element, String controlName) throws Exception {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            Log.info("Click action through javascript is performed on '" + controlName + "' button");
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void doubleClickButtonThroughJS(WebElement element, String controlName) throws Exception {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String event = "var event=new MouseEvent(\"dblclick\",{view:window,bubbles:!0,cancelable:!0});document.querySelector(\"div[id='InProcessGrid']>div>table>tbody>tr.rowselected>td:nth-child(1)\").dispatchEvent(event);";
            //executor.executeScript("arguments[0].dispatchEvent(" + event + ");", element);
            executor.executeScript(event);
            Log.info("Click action through javascript is performed on '" + controlName + "' button");
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }


    //</editor-fold>

    //<editor-fold desc="SendKeys Methods">

    public static void sendKeys(By locator, String text) throws Exception {
        try {
            Log.info("Sendkeys " + text);
            WebElement wElement = findElement(locator);
            if (wElement != null)
                wElement.sendKeys(text);
            else
                throw new Exception("Can't find the Element, Locator: " + locator.toString());
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void sendKeys(WebElement wElement, String text) throws Exception {
        try {
            wElement.sendKeys(text);
            Log.info("Sendkeys " + text);
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void sendKeysWithClear(WebElement wElement, String text) throws Exception {
        try {
            wElement.clear();
            wElement.sendKeys(text);
            Log.info("Sendkeys with Clear ");
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void sendKeys(WebElement wElement, Keys key) throws Exception {
        try {
            wElement.sendKeys(key);
            Log.info("Sendkeys " + key);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void sendKeys(By locator, Keys key) throws Exception {
        try {
            driver.findElement(locator).sendKeys(key);
            Log.info("Sendkeys " + key);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    /*public static void sendKeys(int rowNumber, WebElement element, String columnName) throws Exception {
        try {
            sendKeys(rowNumber, element, columnName, columnName);
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }*/

    /*public static void sendKeys(int rowNumber, WebElement element, String excelColumnName, String elementName) throws Exception {
        try {
            String dataValue = ExcelUtils.getCellData(rowNumber, excelColumnName);
            if (!dataValue.isEmpty())
                WebdriverUtils.sendKeys(element, elementName, dataValue);
            Log.info("Sendkeys " + dataValue);
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }*/

    public static void sendKeys(WebElement element, String elementName, String dataValue) throws Exception {
        try {
            element.clear();
            element.sendKeys(dataValue);
            Log.info("'" + dataValue + "' is entered in '" + elementName + "' input");
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    /*public static void sendKeysThroughJS(int rowNumber, WebElement element, String columnName) throws Exception {
        try {
            sendKeysThroughJS(rowNumber, element, columnName, columnName);
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }*/

/*    public static void sendKeysThroughJS(int rowNumber, WebElement element, String columnName, String fieldName) throws Exception {
        try {
            String dataValue = ExcelUtils.getCellData(rowNumber, columnName);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", element, dataValue);
            Log.info("'" + dataValue + "' is entered in '" + fieldName + "' input", true);
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }
*/
    public static void sendKeysThroughJS(String dataValue, WebElement element, String fieldName) throws Exception {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", element, dataValue);
            Log.info("'" + dataValue + "' is entered in '" + fieldName + "' input", true);
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void editHTMLText(String dataValue, WebElement element) throws Exception {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = arguments[1]", element, dataValue);
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void editValueThroughJS(String dataValue, WebElement element) throws Exception {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", element, dataValue);
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void sendKeysThroughActions(WebElement element, String dataValue) throws Exception {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).sendKeys(dataValue).perform();
        }catch (Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    //</editor-fold>

    //<editor-fold desc="Check/Uncheck Methods">

    public static void checkElement(By locator) throws Exception {
        try {
            WebElement checkBox = driver.findElement(locator);
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                throw new Exception("This elementLocator is not a checkbox!");
            }
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void checkElement(WebElement checkBox) throws Exception {
        try {
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                throw new Exception("This elementLocator is not a checkbox!");
            }
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void unCheckElement(By locator) throws Exception {
        try {
            WebElement checkBox = driver.findElement(locator);
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                throw new Exception("This elementLocator is not a checkbox!");
            }
            if (checkBox.isSelected()) {
                checkBox.click();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void unCheckElement(WebElement checkBox) throws Exception {
        try {
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                throw new Exception("This elementLocator is not a checkbox!");
            }
            if (checkBox.isSelected()) {
                checkBox.click();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static boolean isChecked(By locator) throws Exception {
        try {
            WebElement checkBox = driver.findElement(locator);
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                throw new Exception("This elementLocator is not a checkbox!");
            }
            if (checkBox.getAttribute("checked").equals("checked")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            return false;
        }
    }

    /*public static void Check(int rowNumber, WebElement element, String columnName) throws Exception {
        try {
            Check(rowNumber, element, columnName, columnName);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }*/

    /*public static void Check(int rowNumber, WebElement element, String columnName, String fieldName) throws Exception {
        String dataValue = ExcelUtils.getCellData(rowNumber, columnName);
        if ("Yes".equalsIgnoreCase(dataValue)) {
            if (!element.isSelected())
                element.click();
            Log.info("It is checked '" + fieldName + "' checkElement box");
        } else {
            if (element.isSelected())
                element.click();
            Log.info("It is not checked '" + fieldName + "' checkElement box");
        }
    }

*/    //</editor-fold>

    //<editor-fold desc="SelectOption Methods">

    /*public static void selectOption(int rowNumber, WebElement element, String columnName) throws Exception {
        try {
            selectOption(rowNumber, element, columnName, columnName);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void selectOption(int rowNumber, WebElement element, String columnName, String fieldName) throws Exception {
        Boolean founded = false;
        String fullText = "";
        String dataValue = ExcelUtils.getCellData(rowNumber, columnName);
        Select select = new Select(element);
        List<WebElement> list = select.getOptions();
        for (WebElement option : list) {
            fullText = option.getText();
            if (fullText.contains(dataValue)) {
                select.selectByVisibleText(fullText);
                founded = true;
                break;
            }
        }

        if (founded)
            Log.info(String.format("'" + fullText + "' is selected in '" + fieldName + "' drop down list", dataValue));
        else {
            Log.error("Option '" + fullText + "' is not found in '" + fieldName + "' drop down list");
            // I put this line intentionally to generate an exception.
            (new Select(element)).selectByVisibleText(dataValue);
        }
    }
*/
    public static void selectByValue(By locator, String value) throws Exception {
        try {
            Select select = new Select(driver.findElement(locator));
            select.selectByValue(value);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void selectByValue(WebElement welement, String value) throws Exception {
        try {
            Select select = new Select(welement);
            select.selectByValue(value);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void selectByIndex(By locator, int index) throws Exception {
        try {
            Select select = new Select(driver.findElement(locator));
            select.selectByIndex(index);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void selectByIndex(WebElement webElement, int index) throws Exception {
        try {
            Select select = new Select(webElement);
            select.selectByIndex(index);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void selectByVisibleText(By locator, String text) throws Exception {
        try {
            Select select = new Select(driver.findElement(locator));
            select.selectByVisibleText(text);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void selectByVisibleText(WebElement welement, String text) throws Exception {
        try {
            Select select = new Select(welement);
            select.selectByVisibleText(text);
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static boolean doesElementExist(By locator) throws Exception {
        try {
            if (driver.findElements(locator).size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            return false;
        }

    }

    //</editor-fold>

    //<editor-fold desc="Element Methods">

    public static WebElement findElement(By locator) throws Exception{
        try {
            if (driver.findElements(locator).size() > 0) {
                return driver.findElement(locator);
            } else {
                return null;
            }
        }catch(Exception ex){
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            return null;
        }
    }

    public static List<WebElement> findElements(By locator) throws Exception{
        try{
            if (driver.findElements(locator).size() > 0) {
                return driver.findElements(locator);
            } else {
                return null;
            }
        }catch (Exception ex)
        {
            Log.error("Class WebdriverUtils | Method pauseTime | Exception occurred: Exception: " + ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static boolean isElementDisplayed(By locator) throws Exception {
        try {
            if (doesElementExist(locator)) {
                return driver.findElement(locator).isDisplayed();
            } else {
                return false;
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static int getElementCount(By locator) throws Exception {
        try {
            java.util.List elementsFound = driver.findElements(locator);
            return elementsFound.size();
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static Boolean existsElement(By by, Boolean extraTime) throws Exception {
        Boolean found = false;
        try {
            WebElement element = WebdriverUtils.findElement(by);
            if (element != null)
                found = true;

            if (extraTime)
                normalizeWaitingTimeForElement();

        } catch (Exception e) {
            Log.error(e.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            if (extraTime)
                normalizeWaitingTimeForElement();
        }
        return found;
    }

    public static void clickAndHold(By locator) throws Exception {
        try {
            WebElement webElement = driver.findElement(locator);

            if (webElement != null) {
                new Actions(driver).clickAndHold(webElement).perform();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void clickAndHold(WebElement webElement) throws Exception {
        try {
            if (webElement != null) {
                new Actions(driver).clickAndHold(webElement).perform();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }
    public static void releaseElement(By locator) throws Exception {
        try {
            WebElement webElement = driver.findElement(locator);

            if (webElement != null) {
                new Actions(driver).release(webElement).perform();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }
    
   
    public static void releaseElement(WebElement webElement) throws Exception {
        try {
            if (webElement != null) {
                new Actions(driver).release(webElement).perform();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void clickAt(By locator, int x, int y) throws Exception {
        try {
            WebElement webElement = driver.findElement(locator);

            if (webElement != null) {
                Actions builder = new Actions(driver);
                builder.moveToElement(webElement).moveByOffset(x, y).click().perform();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void clickAt(WebElement webElement, int x, int y) throws Exception {
        try {
            if (webElement != null) {
                Actions builder = new Actions(driver);
                builder.moveToElement(webElement).moveByOffset(x, y).click().perform();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void submitElement(By locator) throws Exception {
        try {
            WebElement webElement = driver.findElement(locator);

            if (webElement != null) {
                webElement.submit();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void submitElement(WebElement webElement) throws Exception {
        try {
            if (webElement != null) {
                webElement.submit();
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static WebElement getElement(By by, String name, String type, String place) throws Exception {
        return getElement(by, name, type, place, null);
    }

    public static WebElement getElement(By by, String name, String type, String place, WebElement father) throws Exception {
        WebElement element;
        String messageSuccess = "Found - Element: '" + name + "', Type: '" + type + "', In: '" + place + "'";
        String messageError = "Not Found - Element: '" + name + "', Type: '" + type + "', In: '" + place + "'";

        try {
            if (father != null)
                element = father.findElement(by);
            else
                element = driver.findElement(by);
            Log.info(messageSuccess);
        } catch (Exception ex) {
            Log.error(messageError);
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
        return element;
    }

    public static String getCssValue(WebElement webElement, String value) throws Exception {
        try {
            if (webElement != null) {
                return webElement.getCssValue(value);
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
        return null;
    }

    public static void goToURL(String url) throws Exception {
        try {
            Log.info("Executing Goto method...");
            //String fullUrl = Constant.URL + url;
      
            Log.info(url);
            if (driver == null) {
                Log.error("Driver is null");
                throw new Exception("Driver is null");
            }
            if(url==null){
            	url=utilities.getPropertyValue("url");
            }
            System.out.println(url);
            driver.get(url);
            WebdriverUtils.pauseTime(2000);
            Log.info("Going to " + url, true);
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method goToURL | Exception occurred while going to a page : "
                    + ex.getMessage());
            throw ex;
        }
    }

    //</editor-fold>

    //<editor-fold desc="Windows Methods">

    public static void displayWindow(WebElement button, String buttonName, By window) throws Exception {
        try {
            
            WebdriverUtils.clickElement(button, buttonName);
            WebdriverUtils.pauseTime(3000);
            // Check if the Dialog is displayed
            if (!WebdriverUtils.existsElement(window, false)) {
                Thread.sleep(3000);
                WebdriverUtils.clickElement(button); // If the Add dialog does not appear, click add button again
                Log.info("Click action is performed on '" + buttonName + "' button by second time");
                WebdriverUtils.waitForAllElementsLocatedBy(window);
            }
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method displayWindow | Exception: " + ex.getMessage());
            throw ex;
        }
    }

    public static void scrollToElement(By locator) throws Exception {
        try {
            Locatable scrollToItem = (Locatable) driver.findElement(locator);
            int y = scrollToItem.getCoordinates().inViewPort().getY();
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + y + ");");
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void scrollToElement(WebElement wElement) throws Exception {
        try {
            Locatable scrollToItem = (Locatable) wElement;
            int y = scrollToItem.getCoordinates().inViewPort().getY();
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + y + ");");
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void moveToElementByXpath(String xpath) throws Exception {
        try {
            WebElement mapObject = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mapObject);
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void moveToElementByCssSelector(String selector) throws Exception {
        try {
            WebElement mapObject = driver.findElement(By.cssSelector(selector));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mapObject);
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void moveToElement(WebElement wElement) throws Exception {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wElement);
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void moveToElementByActions(By locator) throws Exception {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element);
            actions.perform();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void moveToElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wElement);
            actions.perform();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static Boolean switchBetweenWindows() throws Exception {
        try {
            Set listOfWindows = driver.getWindowHandles();
            if (listOfWindows.size() != 2) {
                if (listOfWindows.size() > 2) {
                    throw new Exception("Too many Windows");
                } else {
                    throw new Exception("Only 1 window is available");
                }
            }
            String currentWindow = driver.getWindowHandle();
            for (Iterator i = listOfWindows.iterator(); i.hasNext(); ) {
                String selectedWindowHandle = i.next().toString();
                if (!selectedWindowHandle.equals(currentWindow)) {
                    driver.switchTo().window(selectedWindowHandle);
                    return true;
                }
            }
            // Just in case something goes wrong
            throw new Exception("Unable to switch windows!");

        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            return null;
        }
    }

    public static Boolean switchToWindowTitled(String windowTitle) throws Exception {
        try {
            driver.switchTo().window(windowTitle);
            return true;
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            return null;
        }
    }

    public static Boolean switchToFrame(WebElement element) throws Exception {
        try {
            driver.switchTo().frame(element);
            return true;
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            return null;
        }
    }
    
    //to switch out of frame
    public static Boolean switchToDefault(WebElement element) throws Exception {
        try {
            driver.switchTo().defaultContent();
            return true;
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            return null;
        }
    }

    public static void maximizeWindow() throws Exception {
        try {
            driver.manage().window().maximize();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static boolean isAlertPresent() throws Exception {
        boolean alertPresent = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constant.WaitingSeconds);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            alertPresent = true;
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
        return alertPresent;
    }

    public static void dismissAlert() throws Exception {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static String getPageTitle() throws Exception {
        String title = "";
        try {
            title = driver.getTitle();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            return null;
        }
        return title;
    }

    public static String getWindowURL() throws Exception {
        String url;
        try {
            url = driver.getCurrentUrl();
            return url;
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            return null;
        }
    }

    public static void closeBrowser() throws Exception {
        try {
            driver.close();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void quitBrowser() throws Exception {
        try {
            driver.quit();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void getBackInBrowser() throws Exception {
        try {
            driver.navigate().back();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }

    }

    public static void getForwardInBrowser() throws Exception {
        try {
            driver.navigate().forward();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void deleteAllCookies() throws Exception {
        try {
            driver.manage().deleteAllCookies();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void refreshPage() throws Exception {
        try {
            driver.navigate().refresh();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error(ex.getMessage());
            throw ex;
        }
    }

    public static void executeJS(String jsCode) throws Exception {
        try {
            ((JavascriptExecutor) driver).executeScript(jsCode);
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method executeJS | Exception occurred while executing javascript. Code: "
                    + jsCode + "   Exception: " + ex.getMessage());
            throw ex;
        }
    }

    //</editor-fold>

    //<editor-fold desc="Java Script Methods">

    public static String generateTimestamp() throws Exception {
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmssSS");
            String value = simpleDateFormat.format(new Date());
            if (value.length() < 17) {
                value = simpleDateFormat.format(new Date());
            }
            return value;
        } catch (Exception ex) {
            Log.error("Class WebdriverUtils | Method generateTimestamp | Exception occurred: Exception: " + ex.getMessage());
            throw ex;
        }
    }

    //</editor-fold>

    //<editor-fold desc="WebdriverUtils Methods">

    public static void pauseTime(int timeToSleep) throws Exception {
        try{
            Thread.sleep(timeToSleep);
        }catch (Exception ex)
        {
            Log.error("Class WebdriverUtils | Method pauseTime | Exception occurred: Exception: " + ex.getMessage());
            throw ex;
        }
    }

    public static String getTestCaseName(String sTestCase) throws Exception {
        String value = sTestCase;
        try {
            int posi = value.indexOf("@");
            value = value.substring(0, posi);
            posi = value.lastIndexOf(".");
            value = value.substring(posi + 1);
            return value;
        } catch (Exception ex) {
            Log.error("Class WebdriverUtils | Method getTestCaseName | Exception desc : "
                    + ex.getMessage());
            throw ex;
        }
    }

    public static void captureScreenShot(WebDriver driver, String methodName)throws Exception{
        try {
            //logger.info("Take Screenshot");
            DateFormat dfn = new SimpleDateFormat("yyyy/MM/dd");
            DateFormat df = new SimpleDateFormat("MMM . dd . yyyy _HH:mm:ss");

            String formattedDate = df.format(new Date());
            String folderName = dfn.format(new Date());

            //ScreenShots will be saved in different levels in folders, i.e, /#YEAR/#MONTH/#DAY.jpg
            formattedDate = formattedDate.replace(" ", "");
            formattedDate = formattedDate.replace(":", "");
            String filePath = "src/test/java/screenShots/" + folderName.replace("/", "") + "/" + WebdriverUtils.getTestName() + "/" + methodName + "_" + formattedDate + ".jpg";

            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, new File(filePath));
        } catch (Exception ex) {
            Log.error("Class WebdriverUtils | Method Capture Screenshot | Exception desc : "
                    + ex.getMessage());
            throw ex;
        }
    }

    public static String getDate(int days) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        Date tmpDate = calendar.getTime();
        String finalDate = dateFormat.format(tmpDate);
        return finalDate;
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        Date tmpDate = calendar.getTime();
        String finalDate = dateFormat.format(tmpDate);
        return finalDate;
    }

    //</editor-fold>

    //<editor-fold desc="Grid Methods">

    public static int getColumnIndex(String gridName, String columnHeader) throws Exception {
        String jsCode = "";
        try {
            jsCode = " var columnHeader = '" + columnHeader + "'; "
                    + " var mygrid = $('" + gridName + "').grid; "
                    + " for (var i = 0; i < mygrid.getColumnsNum(); i++) {  "
                    + " var colLabel = mygrid.getColLabel(i).trim();  "
                    + " if( colLabel.toUpperCase() == columnHeader.toUpperCase() ) { "
                    + " return i;  "
                    + " }  "
                    + " }  "
                    + " return -1;";
            int columnIndex = ((Long) ((JavascriptExecutor) driver).executeScript(jsCode)).intValue();
            if (columnIndex < 0) {
                throw new Exception("Column " + columnHeader + " not found");
            }

            return columnIndex;
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method getColumnIndex | Exception occurred while executing javascript. Code: "
                    + jsCode + "   Exception: " + ex.getMessage());
            throw ex;
        }
    }

    public static WebElement getTxtFilter(String gridName, String columnHeader) throws Exception {
        String jsCode = "";
        int index = getColumnIndex(gridName, columnHeader);

        try {
            jsCode = "return $('" + gridName + "').grid.getFilterElement(" + index + ");";
            WebElement filter = (WebElement) ((JavascriptExecutor) driver).executeScript(jsCode);
            if (filter == null) {
                throw new Exception("Filter textbox is not found. Grid: " + gridName + ", ColumnHeader: " + columnHeader);
            }
            return filter;
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method getTxtFilter | Exception occurred while executing javascript. Code: "
                    + jsCode + "   Exception: " + ex.getMessage());
            throw ex;
        }
    }

    public static String getGridCellValue(String gridName, String columnHeader, int moveFromIndex) throws Exception {
        String jsCode = "";
        try {

            int columnIndex = getColumnIndex(gridName, columnHeader) + moveFromIndex;

            jsCode = "return $('" + gridName + "').grid.cells($('" + gridName + "').grid.getSelectedRowId()," + columnIndex + ").getValue(); ";
            String value = (String) ((JavascriptExecutor) driver).executeScript(jsCode);
            return value;

        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method getGridCellValue | Exception occurred while executing javascript. Code: "
                    + jsCode + "   Exception: " + ex.getMessage());
            throw ex;
        }
    }

    public static int getGridRowNumber(String gridName) throws Exception {
        int count = -1;
        String jsCode = "return $('" + gridName + "').grid.getRowsNum();";
        try {
            count = ((Long) ((JavascriptExecutor) driver).executeScript(jsCode)).intValue();
        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method getGridRowNumber | Exception occurred while executing javascript. Code: "
                    + jsCode + "   Exception: " + ex.getMessage());
            throw ex;
        }
        return count;
    }

    
    public static void selectGridRow(String gridName, int rowIndex) throws Exception {
        // http://docs.dhtmlx.com/api__dhtmlxgrid_selectrow.html
        String jsCode = "";
        try {
            jsCode = "$('" + gridName + "').grid.selectRow(" + rowIndex + ",true,false,true);";
            ((JavascriptExecutor) driver).executeScript(jsCode);
            Log.info("Row index " + rowIndex + " is selected in Grid '" + gridName + "'");

        } catch (Exception ex) {
            captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            Log.error("Class WebdriverUtils | Method selectGridRow | Exception occurred while executing javascript. Code: "
                    + jsCode + "   Exception: " + ex.getMessage());
            throw ex;
        }
    }

   
    
    

    public enum BrowserEnum {
        FIREFOX,
        CHROME,
        IE11,
        IE10,
        IE9,
        SAFARI,
        SAUCELABS
    }

    //</editor-fold>
}
