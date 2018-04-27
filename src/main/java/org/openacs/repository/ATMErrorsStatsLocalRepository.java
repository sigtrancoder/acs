package org.openacs.repository;

import java.sql.Timestamp;
import java.util.Collection;

import org.openacs.entity.ATMErrorsStatsPK;
import org.openacs.entity.ATMErrorsStatsLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface ATMErrorsStatsLocalRepository extends CrudRepository<ATMErrorsStatsLocal, ATMErrorsStatsPK> {
	//ATMErrorsStatsLocal findByPrimaryKey(ATMErrorsStatsPK key);
	Collection<ATMErrorsStatsLocal> findByHostid(Long hostid);
	Collection<ATMErrorsStatsLocal> findByTimeBeforeAndHostid(Timestamp time, Long hostid);
}
