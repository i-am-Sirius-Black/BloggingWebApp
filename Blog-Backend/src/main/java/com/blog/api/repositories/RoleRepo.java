package com.blog.api.repositories;

import com.blog.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository <Role, Integer>{
}
