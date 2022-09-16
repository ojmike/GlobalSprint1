package com.globalsprint.globalsprint1.model;

import com.globalsprint.globalsprint1.payload.enums.StoryType;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Story extends BaseEntity{

    private String heading;

    private String body;

    private StoryType type;

    @CreatedBy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User user;

}
