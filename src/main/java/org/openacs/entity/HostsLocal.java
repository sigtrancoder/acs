package org.openacs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.OneToOne;

@Entity
public class HostsLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
	
	String serialno;
	String url;
	String configname;
	String currentsoftware;
	Timestamp sfwupdtime;
	String sfwupdres;
	String cfgupdres;
	Timestamp lastcontact;
	String lastcrres;
	Timestamp cfgupdtime;
	String hardware;
	String cfgversion;
	byte[] props;
	Long hwid;
	String username;
	String password;
	Integer authtype;
	String customerid;
	String conrequser;
	String conreqpass;
	Boolean cfgforce;
	String profileName;
	Boolean forcePasswords;
	Boolean reboot;

	@ManyToOne
	DeviceProfileLocal devProfile;

    public HostsLocal(){
    	
    }
 
	// Getters and Setters 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getConfigname() {
		return configname;
	}

	public void setConfigname(String configname) {
		this.configname = configname;
	}

	public String getCurrentsoftware() {
		return currentsoftware;
	}

	public void setCurrentsoftware(String currentsoftware) {
		this.currentsoftware = currentsoftware;
	}

	public Timestamp getSfwupdtime() {
		return sfwupdtime;
	}

	public void setSfwupdtime(Timestamp sfwupdtime) {
		this.sfwupdtime = sfwupdtime;
	}

	public String getSfwupdres() {
		return sfwupdres;
	}

	public void setSfwupdres(String sfwupdres) {
		this.sfwupdres = sfwupdres;
	}

	public String getCfgupdres() {
		return cfgupdres;
	}

	public void setCfgupdres(String cfgupdres) {
		this.cfgupdres = cfgupdres;
	}

	public Timestamp getLastcontact() {
		return lastcontact;
	}

	public void setLastcontact(Timestamp lastcontact) {
		this.lastcontact = lastcontact;
	}

	public String getLastcrres() {
		return lastcrres;
	}

	public void setLastcrres(String lastcrres) {
		this.lastcrres = lastcrres;
	}

	public Timestamp getCfgupdtime() {
		return cfgupdtime;
	}

	public void setCfgupdtime(Timestamp cfgupdtime) {
		this.cfgupdtime = cfgupdtime;
	}

	public String getHardware() {
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public String getCfgversion() {
		return cfgversion;
	}

	public void setCfgversion(String cfgversion) {
		this.cfgversion = cfgversion;
	}

	public byte[] getProps() {
		return props;
	}

	public void setProps(byte[] props) {
		this.props = props;
	}

	public Long getHwid() {
		return hwid;
	}

	public void setHwid(Long hwid) {
		this.hwid = hwid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAuthtype() {
		return authtype;
	}

	public void setAuthtype(Integer authtype) {
		this.authtype = authtype;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getConrequser() {
		return conrequser;
	}

	public void setConrequser(String conrequser) {
		this.conrequser = conrequser;
	}

	public String getConreqpass() {
		return conreqpass;
	}

	public void setConreqpass(String conreqpass) {
		this.conreqpass = conreqpass;
	}

	public Boolean getCfgforce() {
		return cfgforce;
	}

	public void setCfgforce(Boolean cfgforce) {
		this.cfgforce = cfgforce;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Boolean getForcePasswords() {
		return forcePasswords;
	}

	public void setForcePasswords(Boolean forcePasswords) {
		this.forcePasswords = forcePasswords;
	}

	public Boolean getReboot() {
		return reboot;
	}

	public void setReboot(Boolean reboot) {
		this.reboot = reboot;
	}
}