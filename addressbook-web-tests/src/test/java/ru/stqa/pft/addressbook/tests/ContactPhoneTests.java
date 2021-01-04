package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;


public class ContactPhoneTests extends TestBase{

  @Test(enabled = true)
  public void testContactPhones(){
    app.getNavigationHelper().gotoHomePage();
    ContactData contact = app.getContactHelper().all().iterator().next();
    ContactData contactInfoFormEditForm = app.getContactHelper().infoFromEditForm(contact);

    MatcherAssert.assertThat(contact.getHomephone(), equalTo(cleaned(contactInfoFormEditForm.getHomephone())));
    MatcherAssert.assertThat(contact.getMobilephone(), equalTo(cleaned(contactInfoFormEditForm.getMobilephone())));
    MatcherAssert.assertThat(contact.getWorkphone(), equalTo(cleaned(contactInfoFormEditForm.getWorkphone())));
  }

  public String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
