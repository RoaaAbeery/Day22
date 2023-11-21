package com.example.trackersystem.Controller;

import com.example.trackersystem.tracker.Tracker;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/Tracker")
public class TrackerController {

    ArrayList<Tracker> tracks =new ArrayList<>();
    @PostMapping("/addtracker")
    public ResponseEntity addStudent(@Valid @RequestBody Tracker track, Errors errors){
        if(errors.hasErrors()){
            String messagee=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messagee);
        }
        tracks.add(track);
        return ResponseEntity.status(HttpStatus.OK).body("user add");
    }

    @GetMapping("/Display")
    public ResponseEntity Display(){

        return ResponseEntity.status(HttpStatus.OK).body(tracks);
    }

    @PutMapping("/update/{index}")
    public ResponseEntity update (@PathVariable int index , @Valid@RequestBody Tracker track,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        tracks.set(index,track);
        return ResponseEntity.status(HttpStatus.OK).body("project updated");
    }
    @DeleteMapping("/Delete/{index}")
    public ResponseEntity delete(@PathVariable int index ){
        tracks.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("project deleted");

    }
    @PutMapping("/Status/{index}")
    public ResponseEntity status(@PathVariable int index ){
        Tracker tracke=tracks.get(index);
        if(tracke.getStatus().equals("Started")){
            tracke.setStatus("Progress");
            tracks.set(index,tracke);
        }
        if(tracke.getStatus().equals("Progress")){
            tracke.setStatus("Completed");
            tracks.set(index,tracke);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Status changed");
    }

    @GetMapping("/Search/{title}")
    public ResponseEntity search (@PathVariable String title){
        ArrayList<Tracker> newlist1=new ArrayList<>();
        for (Tracker tracke: tracks){
            if(tracke.getTitle().equals(title))
                newlist1.add(tracke);
        }
        return ResponseEntity.status(HttpStatus.OK).body(newlist1);
    }
    @GetMapping("/CompanyName/{name}")
    public ResponseEntity companyName(@PathVariable String name){
        ArrayList<Tracker> newlist=new ArrayList<>();
        for (Tracker tracke:tracks){
            if (tracke.getCompanyName().equals(name))
                newlist.add(tracke);
        }
        return ResponseEntity.status(HttpStatus.OK).body(newlist);
    }
}
