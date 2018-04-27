package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OuiMapLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String oui;

    String mappedoui;

    public OuiMapLocal(){
    	
    }
    
    public OuiMapLocal(String oui, String mappedoui){
    	this.oui = oui;
    	this.mappedoui = mappedoui;
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
     * @return the mappedoui
     */
    public String getMappedoui() {
        return mappedoui;
    }

    /**
     * @param mappedoui the mappedoui to set
     */
    public void setMappedoui(String mappedoui) {
        this.mappedoui = mappedoui;
    }
}