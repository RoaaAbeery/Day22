package com.example.eventvalidation.event;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty(message = "Id should not be empty")
    @Size(min = 2,message = "Id Should be more than 2 ")
    private String id;
    @NotEmpty(message = "description should not be empty")
    @Size(min = 15,message = "description Should be more than 15 ")
    private String description;
    @NotEmpty(message = "capacity should not be empty")
    @Min(value = 25,message = "capacity Should be more than 25")
    //@Digits()
    private int capacity;
    @Pattern(regexp = "yyyy:MM:DD")
    @FutureOrPresent(message = "startDate should be in future or present ")
    private String startDate;
    @Pattern(regexp = "yyyy:MM:DD")
    @FutureOrPresent(message = "endDate should be in past or present ")
    private String endDate;
}
