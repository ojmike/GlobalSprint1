package com.globalsprint.globalsprint1.service;

import com.globalsprint.globalsprint1.exception.BadRequestException;
import com.globalsprint.globalsprint1.model.State;
import com.globalsprint.globalsprint1.payload.request.CreateStateRequest;
import com.globalsprint.globalsprint1.payload.request.UpdateStateRequest;
import com.globalsprint.globalsprint1.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;

    public String createState(CreateStateRequest createStateRequest){
    State state = new State();
    state.setCapital(createStateRequest.getCapital());
    state.setCountry(createStateRequest.getCountry());
    state.setName(createStateRequest.getName());
    stateRepository.save(state);
    return "State saved successfully";
    }


    public State getState(Long id){
        State state = stateRepository.findById(id).orElseThrow(() -> new BadRequestException(""));
        return state;
    }

    public List<State> getAllState(){
        return stateRepository.findAll();
    }


    public void updateState( UpdateStateRequest updateStateRequest){
        State oldState = stateRepository.findById(updateStateRequest.getId()).orElseThrow();
        oldState.setName(updateStateRequest.getName());
        oldState.setCapital(updateStateRequest.getCapital());
        oldState.setCountry(updateStateRequest.getCountry());
        stateRepository.save(oldState);

    }


    public void deleteState(Long id){
    stateRepository.deleteById(id);
    }
}
