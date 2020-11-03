package ru.stqa.pft.addressbook.tests;

import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("test_name2","test_surname2",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
