package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class CreateGroupTests extends TestBase {

  @Test
  public void testCreateGroup() {
    app.getNavigationHelper().gotoGroup();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test12", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size() +1);

    //int max = 0;
    //for(GroupData g : after){
      //if(g.getId() > max){
        //max = g.getId();
      //}
    //}

    //group.setId(after.stream().max(((o1, o2) -> Integer.compare(o1.getId(),o2.getId()))).get().getId());
    group.setId(after.stream().max((Comparator.comparingInt(GroupData::getId))).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
