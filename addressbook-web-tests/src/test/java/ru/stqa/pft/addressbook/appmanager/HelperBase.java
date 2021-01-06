package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class HelperBase {
  protected WebDriver driver;

  public HelperBase(WebDriver driver) {
    this.driver = driver;
  }

  protected void click(By locator) {
    driver.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null){
      String existingText = driver.findElement(locator).getAttribute("value");
      if(! text.equals(existingText)){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {
    if (file != null){
        driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  protected void select(By locator,String text){
    new Select(driver.findElement(locator)).selectByVisibleText(text);
  }

  protected void setSizeWindow() {
    driver.manage().window().setSize(new Dimension(1440, 833));
  }

  protected boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex){
      return false;
    }
  }
}
