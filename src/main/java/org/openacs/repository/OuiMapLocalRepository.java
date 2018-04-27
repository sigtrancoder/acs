package org.openacs.repository;

import org.openacs.entity.OuiMapLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface OuiMapLocalRepository extends CrudRepository<OuiMapLocal, String> {
    OuiMapLocal findByOui(String oui);
}
