package org.openacs.repository;

import org.openacs.entity.ProfilePropertyPK;
import org.openacs.entity.ProfilePropertyLocal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
/**
 * Created by anonymous on 2/1/16.
 */
@Repository 
public interface ProfilePropertyLocalRepository extends CrudRepository<ProfilePropertyLocal, ProfilePropertyPK> {
	Collection<ProfilePropertyLocal> findByProfilename(String profilename);
}
