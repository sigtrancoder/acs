package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
	String description;
	String type;
	String defaultparentservice;

    public ServiceLocal(){

    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefaultparentservice() {
		return defaultparentservice;
	}

	public void setDefaultparentservice(String defaultparentservice) {
		this.defaultparentservice = defaultparentservice;
	}

}