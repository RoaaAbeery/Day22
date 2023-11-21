package com.example.trackersystem.tracker;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tracker {
    @NotEmpty(message = "should be not empty")
    @Size(min = 2,message =" Id Should be more than 2")
    private String id;
    @NotEmpty(message = "Title should be not empty")
    @Size(min = 8,message ="Title should be more than 8")
    private String title;
    @NotEmpty(message = "Description should be not empty")
    @Size(min = 15,message ="Description should be more than 15")
    private String description;
    @NotEmpty(message = " Status should be not empty")
    @Pattern(regexp = "^(Started|In Progress|Completed)$", message = "Invalid status value. Must be Not Started, In Progress, or Completed.")
    private String status;
    @NotEmpty(message = "company Name should be not empty")
    @Size(min = 6,message ="company Name should be more than 6")
    private String companyName;
}
