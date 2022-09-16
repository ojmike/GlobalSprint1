package com.globalsprint.globalsprint1.service.impl;

import com.globalsprint.globalsprint1.exception.BadRequestException;
import com.globalsprint.globalsprint1.model.Story;
import com.globalsprint.globalsprint1.model.StorySubscription;
import com.globalsprint.globalsprint1.model.User;
import com.globalsprint.globalsprint1.payload.request.CreateStoryRequest;
import com.globalsprint.globalsprint1.payload.request.GetStoryRequest;
import com.globalsprint.globalsprint1.payload.response.APIResponse;
import com.globalsprint.globalsprint1.payload.response.GetStoryResponse;
import com.globalsprint.globalsprint1.repository.StoryRepository;
import com.globalsprint.globalsprint1.repository.StorySubscriptionRepository;
import com.globalsprint.globalsprint1.service.StoryService;
import com.globalsprint.globalsprint1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;

    private final StorySubscriptionRepository storySubscriptionRepository;

    private final UserService userService;

    @Override
    public APIResponse createStory(CreateStoryRequest createStoryRequest) {

        Story story = Story.builder()
                .heading(createStoryRequest.getHeading())
                .body(createStoryRequest.getBody())
                .type(createStoryRequest.getType())
                .user(userService.getLoggedInUser())
                .build();

        story = storyRepository.save(story);

        if(!ObjectUtils.isEmpty(createStoryRequest.getShareWith())){
            for(Long id : createStoryRequest.getShareWith()){
                StorySubscription storySubscription = new StorySubscription();
                storySubscription.setUserId(id);
                storySubscription.setStory(story);
                storySubscriptionRepository.save(storySubscription);
            }

        }

        return  APIResponse.builder()
                .message("Story created successfully.")
                .body(story.getId())
                .build();

    }

    @Override
    public APIResponse deleteStory(Long id) {

        Story story = storyRepository.findById(id).orElseThrow();
        User user = userService.getLoggedInUser();
        if(story.getUser() != user) throw new BadRequestException("User has no permission to delete story");
        storyRepository.delete(story);

        return  APIResponse.builder()
                .message("Story deleted successfully.")
                .body(story.getId())
                .build();
    }

    @Override
    public List<GetStoryResponse> getStories(GetStoryRequest getStoryRequest) {
        User user = userService.getLoggedInUser();
        Pageable pageable = PageRequest.of(getStoryRequest.getStart() - 1, getStoryRequest.getCount(), Sort.by(Sort.Direction.DESC, "createdOn"));
        return storyRepository.getAllStories(pageable,user.getId()).getContent()
                .stream().map(GetStoryResponse::new)
                .collect(Collectors.toList());

    }

}
