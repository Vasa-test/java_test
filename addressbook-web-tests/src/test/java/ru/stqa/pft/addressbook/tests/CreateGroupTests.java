package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class CreateGroupTests extends TestBase {

  @Test
  public void testCreateGroup() {
    app.getNavigationHelper().gotoGroup();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test6", null, null));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before +1);
  }

}
