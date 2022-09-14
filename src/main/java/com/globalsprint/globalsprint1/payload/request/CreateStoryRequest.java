package com.globalsprint.globalsprint1.payload.request;

import com.globalsprint.globalsprint1.payload.enums.StoryType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
public class CreateStoryRequest {

    private String heading;

    private String body;

    private StoryType type;

    private List<Long> shareWith;
}
