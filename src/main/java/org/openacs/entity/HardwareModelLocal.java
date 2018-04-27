package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HardwareModelLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

    String oui;

    String hclass;
	
	String DisplayName;
	
	String manufacturer;
	
	String version;
	
	String profileToAssign;

    public HardwareModelLocal(){
    	
    }
    
    public HardwareModelLocal(Long id, String oui, String hclass, String DisplayName, String manufacturer, String version, String profileToAssign){
		this.id = id;
    	this.oui = oui;
    	this.hclass = hclass;
		this.DisplayName = DisplayName;
		this.manufacturer = manufacturer;
		this.version = version;
		this.profileToAssign = profileToAssign;
    }
 
     /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the oui
     */
    public String getOui() {
        return oui;
    }

    /**
     * @param oui the oui to set
     */
    public void setOui(String oui) {
        this.oui = oui;
    }

    /**
     * @return the hclass
     */
    public String getHclass() {
        return hclass;
    }

    /**
     * @param hclass the hclass to set
     */
    public void setHclass(String hclass) {
        this.hclass = hclass;
    }

    /**
     * @return the DisplayName
     */
    public String getDisplayName() {
        return DisplayName;
    }

    /**
     * @param DisplayName the DisplayName to set
     */
    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the profileToAssign
     */
    public String getProfileToAssign() {
        return profileToAssign;
    }

    /**
     * @param profileToAssign the profileToAssign to set
     */
    public void setProfileToAssign(String profileToAssign) {
        this.profileToAssign = profileToAssign;
    }
}