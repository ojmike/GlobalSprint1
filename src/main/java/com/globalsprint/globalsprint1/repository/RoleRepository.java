package com.globalsprint.globalsprint1.repository;

import com.globalsprint.globalsprint1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
