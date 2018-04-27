package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@IdClass(ProfilePropertyPK.class)
public class ProfilePropertyLocal {

    @Id
    String profilename;

    @Id
    String name;

 	String value;

    public ProfilePropertyLocal(){
    }

    public ProfilePropertyLocal(String profilename, String name, String Value){
    	this.profilename = profilename;
    	this.name = name;
    	this.value = value;
    }

	public String getProfilename() {
		return profilename;
	}

	public void setProfilename(String profilename) {
		this.profilename = profilename;
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

	@ManyToOne
	DeviceProfileLocal devProfile;

	DeviceProfileLocal getProfile(){
		return devProfile;
	}

    void setProfile(DeviceProfileLocal profile){
    	devProfile = profile;
	}

}