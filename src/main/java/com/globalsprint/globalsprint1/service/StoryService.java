package com.globalsprint.globalsprint1.service;

import com.globalsprint.globalsprint1.payload.request.CreateStoryRequest;
import com.globalsprint.globalsprint1.payload.request.GetStoryRequest;
import com.globalsprint.globalsprint1.payload.response.APIResponse;
import com.globalsprint.globalsprint1.payload.response.GetStoryResponse;

import java.util.List;

public interface StoryService {

    APIResponse createStory(CreateStoryRequest createStoryRequest);

    APIResponse deleteStory(Long id);

    List<GetStoryResponse> getStories(GetStoryRequest getStoryRequest);
}
