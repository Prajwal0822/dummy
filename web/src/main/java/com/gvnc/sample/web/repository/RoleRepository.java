package com.gvnc.sample.web.repository;

import com.gvnc.sample.web.model.user.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}

