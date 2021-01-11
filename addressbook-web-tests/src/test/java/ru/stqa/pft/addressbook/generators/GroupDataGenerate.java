package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerate {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main (String[] args) throws IOException {

    GroupDataGenerate generate = new GroupDataGenerate();
    JCommander jCommander = new JCommander(generate);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generate.run();
  }

  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count);
    save(groups,new File(file));
  }

  private void save(List<GroupData> groups, File file) throws IOException {
    //System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (GroupData group:groups){
      writer.write(String.format("%s;%s;%s\n",group.getName(),group.getHeader(),group.getFooter()));
    }
    writer.close();
  }

  private List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++){
      groups.add(new GroupData("names","headers","footers"));
    }
    return groups;
  }
}
