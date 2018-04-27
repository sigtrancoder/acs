package org.openacs.repository;

import org.openacs.entity.HostsLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface HostsLocalRepository extends CrudRepository<HostsLocal, Long> {

	HostsLocal findById(Long id);

    Collection<HostsLocal> findBySerialno(String serialno);
    
    //Collection<HostsLocal> findByPartSerial(String serialno);

    Collection<HostsLocal> findByUrl(String url);

    //Collection<HostsLocal> findByPartialSN(Integer hwid, String snprefix);

    //org.openacs.HostsLocal findByIp(java.lang.String ip);

    HostsLocal findByHwidAndSerialno(Long hwid, String sn);

    //Collection findByIpM(String ip) throws FinderException;

    Collection<HostsLocal> findByCustomerid(String customerId);
}
