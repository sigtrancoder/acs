package org.openacs.repository;

import org.openacs.entity.SystemUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anonymous on 2/1/16.
 */
public interface SystemUserRepository extends CrudRepository<SystemUser, Long> {
    SystemUser findByUser(String user);
}
