package com.elsevier.kata;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.elsevier.kata.exception.DuplicateTaskException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;

class ToDoListServiceTest {

  @InjectMocks
  private ToDoListService toDoListService;

  @BeforeEach
  public void setUp() {
    openMocks(this);
  }

  void willCreateNewUser() {
    User newUser = new User("Ewa");
    assertEquals("Ewa", newUser.getName());
    assertEquals(List.of(), newUser.getOutstandingTasks());
  }

  void userCanAddTask() throws DuplicateTaskException {
    User user = new User("Ewa");
    Task task = toDoListService.addTask(user, "Bake a cake");
    assertEquals("Bake a cake", user.getOutstandingTasks().get(0).getDescription());
    assertEquals(1, user.getOutstandingTasks().size());
    assertEquals(1, user.getAllTasks().size());
    assertEquals(0, user.getDeletedTasks().size());
  }

  void userCanDeleteTask() throws DuplicateTaskException {
    User user = new User("Ewa");
    toDoListService.addTask(user, "Bake a cake");
    toDoListService.deleteTask(user, "Bake a cake");
    assertEquals("Bake a cake", user.getDeletedTasks().get(0).getDescription());
    assertEquals(0, user.getOutstandingTasks().size());
    assertEquals(1, user.getAllTasks().size());
    assertEquals(1, user.getDeletedTasks().size());
  }

  void userCanSeeCompletedTasks() throws DuplicateTaskException {
    User user = new User("Ewa");
    Task task = toDoListService.addTask(user, "Bake a cake");
    Task completedTask = toDoListService.completeTask(user, "Bake a cake");
    List<Task> completedTasks = toDoListService.listCompletedTasks(user);
    assertEquals(List.of(task), completedTasks);
  }



}