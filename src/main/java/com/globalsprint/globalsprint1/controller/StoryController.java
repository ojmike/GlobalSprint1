package com.globalsprint.globalsprint1.controller;

import com.globalsprint.globalsprint1.model.Story;
import com.globalsprint.globalsprint1.payload.request.CreateStoryRequest;
import com.globalsprint.globalsprint1.payload.request.GetStoryRequest;
import com.globalsprint.globalsprint1.payload.response.APIResponse;
import com.globalsprint.globalsprint1.payload.response.GetStoryResponse;
import com.globalsprint.globalsprint1.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/story")
public class StoryController {

    private final StoryService storyService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse createUser(@Validated @RequestBody CreateStoryRequest createStoryRequest){
        return storyService.createStory(createStoryRequest);
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse deleteStory(@RequestParam(name = "storyId") long storyId){
        return storyService.deleteStory(storyId);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<GetStoryResponse> getStories(@Validated @RequestBody GetStoryRequest getStoryRequest){
        return storyService.getStories(getStoryRequest);
    }
}
