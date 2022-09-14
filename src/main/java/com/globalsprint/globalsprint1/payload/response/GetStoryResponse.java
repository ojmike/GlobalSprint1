package com.globalsprint.globalsprint1.payload.response;

import com.globalsprint.globalsprint1.model.Story;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GetStoryResponse {

    private String header;

    private String body;

    public GetStoryResponse(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public GetStoryResponse(Story story) {
        this.header = story.getHeading();
        this.body = story.getBody();
    }
}
