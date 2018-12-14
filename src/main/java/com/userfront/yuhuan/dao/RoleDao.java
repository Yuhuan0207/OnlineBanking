package com.userfront.yuhuan.dao;

import com.userfront.yuhuan.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer>{
    Role findByName(String name);
}
