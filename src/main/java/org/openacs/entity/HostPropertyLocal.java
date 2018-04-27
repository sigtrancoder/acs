package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@IdClass(HostPropertyPK.class)
public class HostPropertyLocal {

    @Id
    Integer parentId;

    @Id
    String name;

 	String value;

    @OneToOne(targetEntity = HostsLocal.class)
    @JoinColumn(name = "host_id",referencedColumnName = "id")
	HostsLocal host;

    public HostPropertyLocal(){
    }

    public HostPropertyLocal(Integer parentId, String name, String Value){
    	this.parentId = parentId;
    	this.name = name;
    	this.value = value;
    }

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public HostsLocal getHost() {
		return host;
	}

	public void setHost(HostsLocal host) {
		this.host = host;
	}
}