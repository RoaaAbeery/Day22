package com.example.eventvalidation.Controller;

import com.example.eventvalidation.event.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/event")
public class EventController {
    ArrayList<Event> events =new ArrayList<>();
    @PostMapping("/add")
    public ResponseEntity addTasks(@Valid @RequestBody Event event, Errors errors) {
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body("Event add");
    }
    @GetMapping("/Display")
    public ResponseEntity getTasks() {

        return ResponseEntity.status(HttpStatus.OK).body(events);
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateTasks(@PathVariable int index, @Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.set(index, event);
        return ResponseEntity.status(HttpStatus.OK).body("Event updated");
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteTasks(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("event deleted");
    }

    @PutMapping("/change/{index}")
    public ResponseEntity change(@PathVariable int index,@RequestBody int newCapacity) {
        events.get(index).setCapacity(newCapacity);
        return ResponseEntity.status(HttpStatus.OK).body("Capacity changed") ;
    }

    @GetMapping("/search/{id}")
    public ResponseEntity search(@PathVariable String id) {
        ArrayList<Event> newlist=new ArrayList<>();
        for (Event event : events) {
            if (event.getId().equals(id))
                return ResponseEntity.status(HttpStatus.OK).body(newlist);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not Found");

    }
}
