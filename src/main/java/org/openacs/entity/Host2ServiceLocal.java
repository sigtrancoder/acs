package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(Host2ServicePK.class)
public class Host2ServiceLocal {

    @Id
    Integer hostid;

    @Id
	Integer serviceid;

    @Id
	Integer instance;
	Integer parentServiceId;
	Integer parentServiceInstance;

    public Host2ServiceLocal(){

    }

	public Integer getHostid() {
		return hostid;
	}

	public void setHostid(Integer hostid) {
		this.hostid = hostid;
	}

	public Integer getServiceid() {
		return serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public Integer getInstance() {
		return instance;
	}

	public void setInstance(Integer instance) {
		this.instance = instance;
	}

	public Integer getParentServiceId() {
		return parentServiceId;
	}

	public void setParentServiceId(Integer parentServiceId) {
		this.parentServiceId = parentServiceId;
	}

	public Integer getParentServiceInstance() {
		return parentServiceInstance;
	}

	public void setParentServiceInstance(Integer parentServiceInstance) {
		this.parentServiceInstance = parentServiceInstance;
	}
}