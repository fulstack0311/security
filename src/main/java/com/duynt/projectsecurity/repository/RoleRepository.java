package com.duynt.projectsecurity.repository;

import com.duynt.projectsecurity.model.ERole;
import com.duynt.projectsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole role);
}
