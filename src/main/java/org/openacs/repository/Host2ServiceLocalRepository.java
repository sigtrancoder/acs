package org.openacs.repository;

import java.util.Collection;

import org.openacs.entity.Host2ServicePK;
import org.openacs.entity.Host2ServiceLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface Host2ServiceLocalRepository extends CrudRepository<Host2ServiceLocal, Host2ServicePK> {
	//Host2ServiceLocal findByPrimaryKey(Host2ServicePK key);
	//Host2ServiceLocal findByHostidAndServiceidAndInstance(Integer Hostid, Integer serviceid, Integer instance);
	Collection<Host2ServiceLocal> findByHostid(Integer hostid);
}
