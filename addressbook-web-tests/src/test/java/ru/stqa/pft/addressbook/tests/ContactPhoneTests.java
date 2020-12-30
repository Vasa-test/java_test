package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactPhoneTests extends TestBase{

  @Test(enabled = false)
  public void testContactPhones(){
    app.getNavigationHelper().gotoHomePage();
    ContactData contact = app.getContactHelper().all().iterator().next();
  }
}
