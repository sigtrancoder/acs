package org.openacs.service;

import java.util.List;

import org.openacs.entity.OuiMapLocal;
import org.openacs.repository.HardwareModelLocalRepository;
import org.openacs.repository.OuiMapLocalRepository;
import org.openacs.repository.HostsLocalRepository;
import org.openacs.repository.ScriptLocalRepository;
import org.openacs.repository.ServicePropertyLocalRepository;
import org.openacs.repository.DSLStatsLocalRepository;
import org.openacs.repository.ATMErrorsStatsLocalRepository;
import org.openacs.repository.SoftwareDetailLocalRepository;
import org.openacs.repository.HostPropertyLocalRepository;
import org.openacs.repository.ProfilePropertyLocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DaoService {

    @Autowired
    OuiMapLocalRepository ouiMapRepository;

    @Autowired
    HardwareModelLocalRepository hwModelRepository;
 
    @Autowired
    HostsLocalRepository hostsRepository;

    @Autowired
    ScriptLocalRepository scriptRepository;

    @Autowired
    DSLStatsLocalRepository dSLStatsRepository;

    @Autowired
    ATMErrorsStatsLocalRepository atmErrorsStatsRepository;

    @Autowired
    SoftwareDetailLocalRepository softwareDetailRepository;

    @Autowired
    HostPropertyLocalRepository hostPropertyRepository;

    @Autowired
    ProfilePropertyLocalRepository profilePropertyRepository;

    @Autowired
    ServicePropertyLocalRepository servicePropertyRepository;

    public OuiMapLocalRepository getOuiMapRepository() {
    	return ouiMapRepository;
    }

    public HardwareModelLocalRepository getHWModelRepository() {
    	return hwModelRepository;
    }

    public HostsLocalRepository getHostsRepository() {
    	return hostsRepository;
    }
 
    public ScriptLocalRepository getScriptRepository() {
    	return scriptRepository;
    }

    public DSLStatsLocalRepository getdSLStatsRepository() {
    	return dSLStatsRepository;
    }

    public ATMErrorsStatsLocalRepository getATMErrorsStatsRepository() {
    	return atmErrorsStatsRepository;
    }

    public SoftwareDetailLocalRepository getSoftwareDetailLocalRepository() {
    	return softwareDetailRepository;
    }

    public HostPropertyLocalRepository getHostPropertyLocalRepository() {
    	return hostPropertyRepository;
    }

    public ProfilePropertyLocalRepository getProfilePropertyLocalRepository() {
    	return profilePropertyRepository;
    }

	public ServicePropertyLocalRepository getServicePropertyLocalRepository() {
		return servicePropertyRepository;
	}
}