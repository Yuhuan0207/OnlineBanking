package com.userfront.yuhuan.Dao;

import com.userfront.yuhuan.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer>{
    Role findByName(String name);
}
