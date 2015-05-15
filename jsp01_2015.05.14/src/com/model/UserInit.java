package com.model;

import java.util.ArrayList;
import java.util.List;

public class UserInit {
  public static List<User> initUser(){
	  List<User> users =new ArrayList<User>();
	  users.add(new User(1,"wukong","Îò¿Õ"));
	  users.add(new User(2,"bajie","°Ë½ä"));
	  users.add(new User(3,"shaseng","É³É®"));
	  
	  return users;
  }
}
