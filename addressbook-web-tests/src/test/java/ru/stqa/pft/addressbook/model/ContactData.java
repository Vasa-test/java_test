package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id;
  private String firstname;
  private String secondname;
  private String group;
  private String homephone;
  private String mobilephone;
  private String workphone;

  public ContactData(int id,String firstname,String secondname,String group,
                     String homephone,String mobilephone,String workphone){
    this.firstname = firstname;
    this.secondname = secondname;
    this.group = group;
    this.id = id;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.workphone = workphone;
  }
  public ContactData(String firstname,String secondname,String group){
    this.firstname = firstname;
    this.secondname = secondname;
    this.group = group;
    this.id = 0;
  }
  public String getFirstname(){
    return firstname;
  }
  public String getLastname(){
    return secondname;
  }
  public String getGroup(){
    return group;
  }
  public int getId(){
    return id;
  }
  public String getHomephone(){
    return homephone;
  }
  public String getMobilephone(){
    return mobilephone;
  }
  public String getWorkphone(){
    return workphone;
  }

}
