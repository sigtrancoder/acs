package org.openacs.repository;

import org.openacs.entity.ScriptLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface ScriptLocalRepository extends CrudRepository<ScriptLocal, String> {

	ScriptLocal findByName(String name);
}
