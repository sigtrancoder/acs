package org.openacs.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class DeviceProfileLocal {

    @Id
    String name;

 	Integer informinterval;
	Integer dayskeepstats;
	Boolean savestats;
	Boolean saveLog;
	Boolean saveParamValues;
	Integer saveParamValuesInterval;
	Boolean saveParamValuesOnChange;
	Boolean saveParamValuesOnBoot;
	String scriptname;
	String baseprofile;

    public DeviceProfileLocal(){
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getInforminterval() {
		return informinterval;
	}

	public void setInforminterval(Integer informinterval) {
		this.informinterval = informinterval;
	}

	public Integer getDayskeepstats() {
		return dayskeepstats;
	}

	public void setDayskeepstats(Integer dayskeepstats) {
		this.dayskeepstats = dayskeepstats;
	}

	public Boolean getSavestats() {
		return savestats;
	}

	public void setSavestats(Boolean savestats) {
		this.savestats = savestats;
	}

	public Boolean getSaveLog() {
		return saveLog;
	}

	public void setSaveLog(Boolean saveLog) {
		this.saveLog = saveLog;
	}

	public Boolean getSaveParamValues() {
		return saveParamValues;
	}

	public void setSaveParamValues(Boolean saveParamValues) {
		this.saveParamValues = saveParamValues;
	}

	public Integer getSaveParamValuesInterval() {
		return saveParamValuesInterval;
	}

	public void setSaveParamValuesInterval(Integer saveParamValuesInterval) {
		this.saveParamValuesInterval = saveParamValuesInterval;
	}

	public Boolean getSaveParamValuesOnChange() {
		return saveParamValuesOnChange;
	}

	public void setSaveParamValuesOnChange(Boolean saveParamValuesOnChange) {
		this.saveParamValuesOnChange = saveParamValuesOnChange;
	}

	public Boolean getSaveParamValuesOnBoot() {
		return saveParamValuesOnBoot;
	}

	public void setSaveParamValuesOnBoot(Boolean saveParamValuesOnBoot) {
		this.saveParamValuesOnBoot = saveParamValuesOnBoot;
	}

	public String getScriptname() {
		return scriptname;
	}

	public void setScriptname(String scriptname) {
		this.scriptname = scriptname;
	}

	public String getBaseprofile() {
		return baseprofile;
	}

	public void setBaseprofile(String baseprofile) {
		this.baseprofile = baseprofile;
	}

	@OneToMany(mappedBy="devProfile")
	Set<HostsLocal> hosts = new HashSet<HostsLocal>();

    public Set<HostsLocal> getHosts(){
    	return hosts;
	}

	@OneToMany(mappedBy="devProfile")
	Set<ProfilePropertyLocal> properties = new HashSet<ProfilePropertyLocal>();
 
	public Set<ProfilePropertyLocal> getProperties(){
		return properties;
	}
}