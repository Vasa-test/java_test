package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); // List<GroupData>.class
    return groups.stream().map((groupData) -> new Object[] {groupData}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData(split[0],split[1],split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
    return groups.stream().map((groupData) -> new Object[] {groupData}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroupsFromJson")
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
