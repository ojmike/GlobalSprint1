package com.globalsprint.globalsprint1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StorySubscription extends BaseEntity{

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

}
