package org.openacs.repository;

import org.openacs.entity.ServicePropertyPK;
import org.openacs.entity.ServicePropertyLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface ServicePropertyLocalRepository extends CrudRepository<ServicePropertyLocal, ServicePropertyPK> {
	Collection<ServicePropertyLocal> findByServiceid(Integer serviceid);
}
