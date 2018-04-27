package org.openacs.repository;

import org.openacs.entity.HardwareModelLocal;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anonymous on 2/1/16.
 */
public interface HardwareModelLocalRepository extends CrudRepository<HardwareModelLocal, Long> {
    HardwareModelLocal findByOui(String oui);
    HardwareModelLocal findByOuiAndHclassAndVersion(String oui, String hclass , String version);
    HardwareModelLocal findByOuiAndHclass(String oui, String hclass);
}
