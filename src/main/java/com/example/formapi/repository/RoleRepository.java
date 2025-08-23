package com.example.formapi.repository;

import com.example.formapi.entity.Role;
import com.example.formapi.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleEnum roleEnum);
}
