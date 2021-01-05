package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

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

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

  // функция для склейки номеров в случае если мы получем массив где какойто номер отсутствует
  private String mergePhones(ContactData contact){
    return Arrays.asList(contact.getHomephone(),contact.getMobilephone(),contact.getWorkphone())
            .stream().filter((s -> ! s.equals("")))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }
}
