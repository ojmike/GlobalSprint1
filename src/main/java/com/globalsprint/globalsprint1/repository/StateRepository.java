package com.globalsprint.globalsprint1.repository;

import com.globalsprint.globalsprint1.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {
}
