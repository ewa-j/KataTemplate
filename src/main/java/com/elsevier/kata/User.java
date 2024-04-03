package com.elsevier.kata;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class User {

  private List<Task> outstandingTasks;
  private List<Task> deletedTasks;
  private List<Task> completedTasks;
  private List<Task> allTasks;
  private String name;

  public User(String name) {
    this.name = name;
    this.outstandingTasks = new ArrayList<>();
    this.deletedTasks = new ArrayList<>();
    this.completedTasks = new ArrayList<>();
    this.allTasks = new ArrayList<>();
  }
}
