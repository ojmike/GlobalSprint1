package com.globalsprint.globalsprint1.controller;

import com.globalsprint.globalsprint1.model.State;
import com.globalsprint.globalsprint1.payload.request.CreateStateRequest;
import com.globalsprint.globalsprint1.payload.request.UpdateStateRequest;
import com.globalsprint.globalsprint1.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class StateController {

    // Autowire my service class

    private final StateService stateService;

    @PostMapping()
    String createState(@RequestBody CreateStateRequest createStateRequest){
        return stateService.createState(createStateRequest);
    }

    @GetMapping("/{id}")
    State getState(@PathVariable Long id){
        return stateService.getState(id);
    }

    @GetMapping("")
    List<State> getAllState(){
        return stateService.getAllState();
    }

    @PutMapping("")
    void updateState( @RequestBody UpdateStateRequest updateStateRequest){
        stateService.updateState(updateStateRequest);
    }

    @DeleteMapping("")
    void deleteState(@RequestParam(name= "id") Long id){
        stateService.deleteState(id);
    }
}
