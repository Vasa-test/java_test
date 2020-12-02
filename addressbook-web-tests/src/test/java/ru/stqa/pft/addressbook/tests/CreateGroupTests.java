package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;

public class CreateGroupTests extends TestBase {

  @Test
  public void testCreateGroup() {
    app.getNavigationHelper().gotoGroup();
    Groups before = app.getGroupHelper().allGroups();
    GroupData group = new GroupData("test22", null, null);
    app.getGroupHelper().createGroup(group);
    Groups after = app.getGroupHelper().allGroups();
    Assert.assertEquals(after.size(),before.size() +1);

    //int max = 0;
    //for(GroupData g : after){
      //if(g.getId() > max){
        //max = g.getId();
      //}
    //}

    //group.setId(after.stream().max(((o1, o2) -> Integer.compare(o1.getId(),o2.getId()))).get().getId());
    //group.setId(after.stream().max((Comparator.comparingInt(GroupData::getId))).get().getId());

    group.setId(after.stream().mapToInt(GroupData::getId).max().orElse(0));
    //before.add(group);
    //Assert.assertEquals(before, after);
    MatcherAssert.assertThat(after, equalTo(before.withAdded(group)));
  }

}
