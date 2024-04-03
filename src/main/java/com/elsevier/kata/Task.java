package com.elsevier.kata;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Task {

  private String description;
  private boolean completed;
  private LocalDateTime dateAdded;
  private LocalDateTime dateModified;
  private LocalDateTime dateCompleted;
  private boolean deleted;
  private LocalDateTime dateDeleted;

}
