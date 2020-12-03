package ru.stqa.pft.addressbook.tests;// Generated by Selenium IDE


import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().gotoGroup();
    if(app.getGroupHelper().all().size() == 0){
      app.getGroupHelper().createGroup(new GroupData("test6", null, null));
    }
  }

  @Test
  public void testGroupDeletion() {
    Set<GroupData> before = app.getGroupHelper().all();
    GroupData deletedGroup = before.iterator().next();
    app.getGroupHelper().deleteGroup(deletedGroup);
    Assert.assertEquals(app.getGroupHelper().getGroupCount(),before.size() -1);
    Set<GroupData> after = app.getGroupHelper().all();

    before.remove(deletedGroup);
    MatcherAssert.assertThat(after, equalTo(before));
  }

}
