package ru.stqa.pft.addressbook;

import org.junit.Test;

public class CreateGroupTests extends TestBase {

  @Test
  public void testCreateGroup() {
    gotoGroup();
    initGroupCreation();
    fillGroupForm(new GroupData("test6", "test6", "test6"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
