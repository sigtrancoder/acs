package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Properties;

@Entity
@IdClass(SoftwareDetailPK.class)
public class SoftwareDetailLocal {

    @Id
    Long hwid;

    @Id
	String version;
	
	byte[] paramNames;
	byte[] methods;
	byte[] voicecaps;
	
    public SoftwareDetailLocal(){

    }

	public Long getHwid() {
		return hwid;
	}

	public void setHwid(Long hwid) {
		this.hwid = hwid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public byte[] getParamNames() {
		return paramNames;
	}

	public void setParamNames(byte[] paramNames) {
		this.paramNames = paramNames;
	}

	public byte[] getMethods() {
		return methods;
	}

	public void setMethods(byte[] methods) {
		this.methods = methods;
	}

	public byte[] getVoicecaps() {
		return voicecaps;
	}

	public void setVoicecaps(byte[] voicecaps) {
		this.voicecaps = voicecaps;
	}

}