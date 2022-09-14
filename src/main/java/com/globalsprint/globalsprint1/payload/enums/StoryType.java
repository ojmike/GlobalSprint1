package com.globalsprint.globalsprint1.payload.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StoryType {
    PUBLIC,
    PRIVATE,
    RESTRICTED;

    @JsonCreator
    public static StoryType jsonDecode(final String storyType){
        return valueOf(storyType);
    }

    @JsonValue
    public String jsonEncode(){
        return name();
    }
}
