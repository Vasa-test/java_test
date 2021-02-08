package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().gotoGroup();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test6", null, null));
    }
  }


  @Test
  public void testGroupModification (){
    // теперь данные мы получаем из БД и работаем с ними как и раньше
    // Groups before = app.db().groups();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getId(),"test33", "test7", "test7");
    app.getGroupHelper().modifyGroup(index, group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(index);
    before.add(group);

    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
