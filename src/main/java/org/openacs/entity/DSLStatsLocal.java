package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;

@Entity
@IdClass(DSLStatsPK.class)
public class DSLStatsLocal {

    @Id
    Long hostid;

    @Id
	Timestamp time;

    Integer downstreamAttenuation;
    Integer downstreamCurrRate;
    Integer downstreamMaxRate;
    Integer downstreamNoiseMargin;
    Integer downstreamPower;
    Integer upstreamAttenuation;
    Integer upstreamCurrRate;
    Integer upstreamMaxRate;
    Integer upstreamNoiseMargin;
    Integer upstreamPower;
	String status;
	String modulationType;

    public DSLStatsLocal(){

    }

	public Long getHostid() {
		return hostid;
	}

	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getDownstreamAttenuation() {
		return downstreamAttenuation;
	}

	public void setDownstreamAttenuation(Integer downstreamAttenuation) {
		this.downstreamAttenuation = downstreamAttenuation;
	}

	public Integer getDownstreamCurrRate() {
		return downstreamCurrRate;
	}

	public void setDownstreamCurrRate(Integer downstreamCurrRate) {
		this.downstreamCurrRate = downstreamCurrRate;
	}

	public Integer getDownstreamMaxRate() {
		return downstreamMaxRate;
	}

	public void setDownstreamMaxRate(Integer downstreamMaxRate) {
		this.downstreamMaxRate = downstreamMaxRate;
	}

	public Integer getDownstreamNoiseMargin() {
		return downstreamNoiseMargin;
	}

	public void setDownstreamNoiseMargin(Integer downstreamNoiseMargin) {
		this.downstreamNoiseMargin = downstreamNoiseMargin;
	}

	public Integer getDownstreamPower() {
		return downstreamPower;
	}

	public void setDownstreamPower(Integer downstreamPower) {
		this.downstreamPower = downstreamPower;
	}

	public Integer getUpstreamAttenuation() {
		return upstreamAttenuation;
	}

	public void setUpstreamAttenuation(Integer upstreamAttenuation) {
		this.upstreamAttenuation = upstreamAttenuation;
	}

	public Integer getUpstreamCurrRate() {
		return upstreamCurrRate;
	}

	public void setUpstreamCurrRate(Integer upstreamCurrRate) {
		this.upstreamCurrRate = upstreamCurrRate;
	}

	public Integer getUpstreamMaxRate() {
		return upstreamMaxRate;
	}

	public void setUpstreamMaxRate(Integer upstreamMaxRate) {
		this.upstreamMaxRate = upstreamMaxRate;
	}

	public Integer getUpstreamNoiseMargin() {
		return upstreamNoiseMargin;
	}

	public void setUpstreamNoiseMargin(Integer upstreamNoiseMargin) {
		this.upstreamNoiseMargin = upstreamNoiseMargin;
	}

	public Integer getUpstreamPower() {
		return upstreamPower;
	}

	public void setUpstreamPower(Integer upstreamPower) {
		this.upstreamPower = upstreamPower;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModulationType() {
		return modulationType;
	}

	public void setModulationType(String modulationType) {
		this.modulationType = modulationType;
	}
}