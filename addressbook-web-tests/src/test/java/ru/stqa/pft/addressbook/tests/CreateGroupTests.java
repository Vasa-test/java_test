package ru.stqa.pft.addressbook.tests;

import org.junit.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class CreateGroupTests extends TestBase {

  @Test
  public void testCreateGroup() {
    app.gotoGroup();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test6", "test6", "test6"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
