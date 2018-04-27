package org.openacs.repository;

import java.sql.Timestamp;
import java.util.Collection;

import org.openacs.entity.SoftwareDetailPK;
import org.openacs.entity.SoftwareDetailLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface SoftwareDetailLocalRepository extends CrudRepository<SoftwareDetailLocal, SoftwareDetailPK> {
	Collection<SoftwareDetailLocal> findByHwid(Long hwid);
}
