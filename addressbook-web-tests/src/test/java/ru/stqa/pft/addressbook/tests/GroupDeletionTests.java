package ru.stqa.pft.addressbook.tests;// Generated by Selenium IDE


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().gotoGroup();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test6", null, null));
    }
  }

  @Test
  public void testGroupDeletion() {
    Set<GroupData> before = app.getGroupHelper().all();
    GroupData deletedGroup = before.iterator().next();
    app.getGroupHelper().deleteGroup(deletedGroup);
    Set<GroupData> after = app.getGroupHelper().all();
    org.junit.Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(deletedGroup);
      Assert.assertEquals(after,before);
  }

}
