package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;

public class CreateGroupTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{"name1","header1","footer1"});
    list.add(new Object[]{"name2","header2","footer2"});
    list.add(new Object[]{"name3","header3","footer3"});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testCreateGroup(String name,String header,String footer) {
    app.getNavigationHelper().gotoGroup();
    Groups before = app.getGroupHelper().allGroups();
    GroupData group = new GroupData(name, header, footer);
    app.getGroupHelper().createGroup(group);
    Assert.assertEquals(app.getGroupHelper().getGroupCount(),before.size() +1);
    Groups after = app.getGroupHelper().allGroups();
    group.setId(after.stream().mapToInt(GroupData::getId).max().orElse(0));
    MatcherAssert.assertThat(after, equalTo(before.withAdded(group)));
  }

  @Test
  public void testBadCreateGroup() {
    app.getNavigationHelper().gotoGroup();
    Groups before = app.getGroupHelper().allGroups();
    GroupData group = new GroupData("test22'", null, null);
    app.getGroupHelper().createGroup(group);
    Assert.assertEquals(app.getGroupHelper().getGroupCount(),before.size());
    Groups after = app.getGroupHelper().allGroups();
    MatcherAssert.assertThat(after, equalTo(before));
  }

}
