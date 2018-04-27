package org.openacs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;

@Entity
@IdClass(ATMErrorsStatsPK.class)
public class ATMErrorsStatsLocal {

    @Id
    Long hostid;

    @Id
	Timestamp time;
	
	@Id
	int type;

	Timestamp IntervalStart;

	public static final int TYPE_TOTAL = 1;
    public static final int TYPE_SHOWTIME = 2;
    public static final int TYPE_CURRENTDAY = 3;
    public static final int TYPE_QUARTERHOUR = 4;
    public static final int TYPE_LASTSHOWTIME = 5;

    Long ATUCCRCErrors;
    Long ATUCFECErrors;
    Long ATUCHECErrors;
    Long CellDelin;
    Long CRCErrors;
    Long ErroredSecs;
    Long FECErrors;
    Long HECErrors;
    Long InitErrors;
    Long InitTimeouts;
	Long LinkRetrain;
	Long LossOfFraming;
	Long ReceiveBlocks;
	Long SeverelyErroredSecs;
	Long TransmitBlocks;
	Long LossOfPower;
	Long LossOfSignal;

    public ATMErrorsStatsLocal(){

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Timestamp getIntervalStart() {
		return IntervalStart;
	}

	public void setIntervalStart(Timestamp intervalStart) {
		IntervalStart = intervalStart;
	}

	public Long getATUCCRCErrors() {
		return ATUCCRCErrors;
	}

	public void setATUCCRCErrors(Long aTUCCRCErrors) {
		ATUCCRCErrors = aTUCCRCErrors;
	}

	public Long getATUCFECErrors() {
		return ATUCFECErrors;
	}

	public void setATUCFECErrors(Long aTUCFECErrors) {
		ATUCFECErrors = aTUCFECErrors;
	}

	public Long getATUCHECErrors() {
		return ATUCHECErrors;
	}

	public void setATUCHECErrors(Long aTUCHECErrors) {
		ATUCHECErrors = aTUCHECErrors;
	}

	public Long getCellDelin() {
		return CellDelin;
	}

	public void setCellDelin(Long cellDelin) {
		CellDelin = cellDelin;
	}

	public Long getCRCErrors() {
		return CRCErrors;
	}

	public void setCRCErrors(Long cRCErrors) {
		CRCErrors = cRCErrors;
	}

	public Long getErroredSecs() {
		return ErroredSecs;
	}

	public void setErroredSecs(Long erroredSecs) {
		ErroredSecs = erroredSecs;
	}

	public Long getFECErrors() {
		return FECErrors;
	}

	public void setFECErrors(Long fECErrors) {
		FECErrors = fECErrors;
	}

	public Long getHECErrors() {
		return HECErrors;
	}

	public void setHECErrors(Long hECErrors) {
		HECErrors = hECErrors;
	}

	public Long getInitErrors() {
		return InitErrors;
	}

	public void setInitErrors(Long initErrors) {
		InitErrors = initErrors;
	}

	public Long getInitTimeouts() {
		return InitTimeouts;
	}

	public void setInitTimeouts(Long initTimeouts) {
		InitTimeouts = initTimeouts;
	}

	public Long getLinkRetrain() {
		return LinkRetrain;
	}

	public void setLinkRetrain(Long linkRetrain) {
		LinkRetrain = linkRetrain;
	}

	public Long getLossOfFraming() {
		return LossOfFraming;
	}

	public void setLossOfFraming(Long lossOfFraming) {
		LossOfFraming = lossOfFraming;
	}

	public Long getReceiveBlocks() {
		return ReceiveBlocks;
	}

	public void setReceiveBlocks(Long receiveBlocks) {
		ReceiveBlocks = receiveBlocks;
	}

	public Long getSeverelyErroredSecs() {
		return SeverelyErroredSecs;
	}

	public void setSeverelyErroredSecs(Long severelyErroredSecs) {
		SeverelyErroredSecs = severelyErroredSecs;
	}

	public Long getTransmitBlocks() {
		return TransmitBlocks;
	}

	public void setTransmitBlocks(Long transmitBlocks) {
		TransmitBlocks = transmitBlocks;
	}

	public Long getLossOfPower() {
		return LossOfPower;
	}

	public void setLossOfPower(Long lossOfPower) {
		LossOfPower = lossOfPower;
	}

	public Long getLossOfSignal() {
		return LossOfSignal;
	}

	public void setLossOfSignal(Long lossOfSignal) {
		LossOfSignal = lossOfSignal;
	}

}