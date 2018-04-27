package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;

@Entity
@IdClass(ServicePropertyPK.class)
public class ServicePropertyLocal {

    @Id
    Integer serviceid;

    @Id
	String name;
	
	String value;
	
	Boolean isparam;

    public ServicePropertyLocal(){

    }

    public ServicePropertyLocal(Integer serviceid, String name, String value){
    	this.serviceid = serviceid;
    	this.name = name;
    	this.value = value;
    }

	public Integer getServiceid() {
		return serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
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

	public Boolean getIsparam() {
		return isparam;
	}

	public void setIsparam(Boolean isparam) {
		this.isparam = isparam;
	}
}