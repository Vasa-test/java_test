package ru.stqa.pft.addressbook.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@Entity
@Table(name = "addressbook")
public class ContactData {
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String secondname;

  @Transient
  private String group;

  @Column(name = "home")
  @Type(type = "text")
  private String homephone;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilephone;

  @Column(name = "work")
  @Type(type = "text")
  private String workphone;

  @Transient
  private File photo;

  public ContactData(){}

  public ContactData(int id, String firstname, String secondname, String group,
                     String homephone, String mobilephone, String workphone){
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

  public File getPhoto() {
    return photo;
  }
  // для того чтобы вызвать его как метод через точку после конструктора
  public ContactData setPhoto(File photo) {
    this.photo = photo;
    return this;
  }

}
