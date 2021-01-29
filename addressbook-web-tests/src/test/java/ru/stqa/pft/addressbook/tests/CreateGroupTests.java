package ru.stqa.pft.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;

public class CreateGroupTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    // для формата csv
    //List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      // для формата csv
      //String[] split = line.split(";");
      //list.add(new Object[]{new GroupData(split[0],split[1],split[2])});
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
    return groups.stream().map((groupData) -> new Object[] {groupData}).collect(Collectors.toList()).iterator();
    // для формата csv
    //return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testCreateGroup(GroupData group) {
    app.getNavigationHelper().gotoGroup();
    Groups before = app.getGroupHelper().allGroups();
    app.getGroupHelper().createGroup(group);
    Assert.assertEquals(app.getGroupHelper().getGroupCount(),before.size() +1);
    Groups after = app.getGroupHelper().allGroups();
    group.setId(after.stream().mapToInt(GroupData::getId).max().orElse(0));
    MatcherAssert.assertThat(after, equalTo(before.withAdded(group)));
  }

  @DataProvider
  public Iterator<Object[]> novalidGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{"badname1'","header1","footer1"});
    list.add(new Object[]{"name2","badheader2'","footer2"});
    return list.iterator();
  }

  @Test(dataProvider = "novalidGroups")
  public void testBadCreateGroup(String name,String header,String footer) {
    app.getNavigationHelper().gotoGroup();
    Groups before = app.getGroupHelper().allGroups();
    GroupData group = new GroupData(name, header, footer);
    app.getGroupHelper().createGroup(group);
    Assert.assertEquals(app.getGroupHelper().getGroupCount(),before.size());
    Groups after = app.getGroupHelper().allGroups();
    MatcherAssert.assertThat(after, equalTo(before));
  }

}
