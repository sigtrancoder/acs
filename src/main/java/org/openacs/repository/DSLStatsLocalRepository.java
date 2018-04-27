package org.openacs.repository;

import java.sql.Timestamp;
import java.util.Collection;

import org.openacs.entity.DSLStatsPK;
import org.openacs.entity.DSLStatsLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface DSLStatsLocalRepository extends CrudRepository<DSLStatsLocal, DSLStatsPK> {
	//DSLStatsLocal findByPrimaryKey(DSLStatsPK key);
	Collection<DSLStatsLocal> findByHostid(Long hostid);
	Collection<DSLStatsLocal> findByTimeBeforeAndHostid(Timestamp time, Long hostid);
}
