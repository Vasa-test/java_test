package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver driver) {
    super(driver);
  }
  public void initContactCreation(){
    click(By.linkText("add new"));
  }
  public void fillContactForm(ContactData contactData, boolean creation){
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    attach(By.name("photo"),contactData.getPhoto());

    if(creation){
      select(By.name("new_group"),contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation(){
    click(By.name("submit"));
  }
  public void returnToHomePage(){
    click(By.linkText("home"));
  }
  public void initContactModification(){
    click(By.cssSelector("img[alt='Edit']"));
  }
  public void submitContactModification(){
    click(By.name("update"));
  }

  public Set<ContactData> all(){
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = driver.findElements(By.name("entry"));
    for (WebElement row:rows){
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contacts.add(new ContactData(id,firstname,lastname,null,phones[0],phones[1],phones[2]));
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact){
    initContactModificationById(contact.getId());
    String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
    String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    driver.navigate().back();
    return new ContactData(contact.getId(),firstname,lastname,null,home,mobile,work);
  }

  private void initContactModificationById(int id){
    WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }


}
