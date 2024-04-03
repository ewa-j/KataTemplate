package com.elsevier.kata;

import com.elsevier.kata.exception.DuplicateTaskException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ToDoListService {

  public User createUser(String name) {
    return new User(name);
  }

  public Task addTask(User user, String toDo) throws DuplicateTaskException {
    Optional<Task> taskToAdd = user.getOutstandingTasks().stream()
        .filter(task -> task.getDescription().toLowerCase().equals(toDo.toLowerCase())).findAny();
    if (taskToAdd.isPresent()) {
      Task newTask = Task.builder()
          .description(toDo)
          .dateAdded(LocalDateTime.now())
          .completed(false)
          .deleted(false)
          .build();
      user.getOutstandingTasks().add(newTask);
      user.getAllTasks().add(newTask);
      return newTask;
    } else {
      throw new DuplicateTaskException("Task already exists");
    }
  }

  public Task deleteTask(User user, String toDo) {
    Optional<Task> taskToDelete = user.getOutstandingTasks().stream()
        .filter(task -> task.getDescription().toLowerCase().equals(toDo.toLowerCase())).findAny();
    if (taskToDelete.isPresent()) {
      taskToDelete.get().setDateDeleted(LocalDateTime.now());
      user.getOutstandingTasks().remove(taskToDelete.get());
      user.getDeletedTasks().add(taskToDelete.get());
      return taskToDelete.get();
    }
    return null;
  }

  public Task completeTask(User user, String toDo) {
    Optional<Task> taskToComplete = user.getOutstandingTasks().stream()
        .filter(task -> task.getDescription().toLowerCase().equals(toDo.toLowerCase())).findAny();
    if (taskToComplete.isPresent()) {
      taskToComplete.get().setDateCompleted(LocalDateTime.now());
      user.getOutstandingTasks().remove(taskToComplete.get());
      user.getCompletedTasks().add(taskToComplete.get());
      return taskToComplete.get();
    }
    return null;
  }

  public Task modifyTask(User user, String toDo, String descriptionToModify) {
    Optional<Task> taskToModify = user.getOutstandingTasks().stream()
        .filter(task -> task.getDescription().toLowerCase().equals(toDo.toLowerCase())).findAny();
    if (taskToModify.isPresent()) {
      taskToModify.get().setDateModified(LocalDateTime.now());
      taskToModify.get().setDescription(descriptionToModify);
    }
    return null;
  }

  public List<Task> listAllTasks(User user) {
    return user.getAllTasks();
  }

  public List<Task> listOutstandingTasks(User user) {
    return user.getOutstandingTasks();
  }

  public List<Task> listDeletedTasks(User user) {
    return user.getDeletedTasks();
  }

  public List<Task> listCompletedTasks(User user) {
    return user.getCompletedTasks();
  }

}
