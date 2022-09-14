package com.globalsprint.globalsprint1.model;

import com.globalsprint.globalsprint1.payload.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp createdOn;

    private Timestamp lastModifiedOn;

    private Status status;
    private boolean active;

    @PrePersist
    public void prePersist(){
        createdOn = Timestamp.from(Calendar.getInstance().toInstant());
        lastModifiedOn = Timestamp.from(Calendar.getInstance().toInstant());
        active = true;
        status = Status.ACTIVE;
    }

    @PreUpdate
    public void preUpdate(){
        lastModifiedOn = Timestamp.from(Calendar.getInstance().toInstant());
    }
}
