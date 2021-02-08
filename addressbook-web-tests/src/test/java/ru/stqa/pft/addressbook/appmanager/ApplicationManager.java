package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.browser.Browser;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver driver;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  JavascriptExecutor js;
  private Map<String, Object> vars;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

    //dbHelper = new DbHelper();

    if(browser.equals(BrowserType.FIREFOX)){
      driver = new FirefoxDriver();
    } else if(browser.equals(BrowserType.CHROME)){
      driver = new ChromeDriver();
    } else if (browser.equals(BrowserType.EDGE)){
      driver = new EdgeDriver();
    }
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    driver.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    contactHelper = new ContactHelper(driver);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

  }


  public void stop() {
    driver.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public DbHelper db(){
    return dbHelper;
  }
}
