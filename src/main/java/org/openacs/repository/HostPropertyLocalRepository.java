package org.openacs.repository;

import org.openacs.entity.HostPropertyPK;
import org.openacs.entity.HostPropertyLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface HostPropertyLocalRepository extends CrudRepository<HostPropertyLocal, HostPropertyPK> {
	//Collection<HostPropertyLocal> findByHostid(Long hostid);
}
