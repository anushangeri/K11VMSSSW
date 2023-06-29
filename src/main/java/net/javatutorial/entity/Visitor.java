package net.javatutorial.entity;

import java.sql.Timestamp;

public class Visitor {
	private String vmsId;
    private String name;
    private String companyName;
    private String site;
    private String idType;
    private String idNo;
    private String mobileNo;
    private String vehicleNo;
    private String hostName;
    private String hostNo;
    private String visitorCardId;
    private String covidDeclare;
    private String remarks;
    private String visitPurpose;
    private String temperature;
    private String approvingOfficer;
    private Timestamp timeInDt;
    private Timestamp timeOutDt;
    private Timestamp archivedDt;
    private String createdBy;
    private Timestamp createdByDt;
    private String lastModifiedBy;
    private Timestamp lastModifiedByDt;
	/**
	 * @param vmsId
	 * @param name
	 * @param companyName
	 * @param site
	 * @param idType
	 * @param idNo
	 * @param mobileNo
	 * @param vehicleNo
	 * @param hostName
	 * @param hostNo
	 * @param visitorCardId
	 * @param covidDeclare
	 * @param remarks
	 * @param visitPurpose
	 * @param temperature
	 * @param approvingOfficer
	 * @param timeInDt
	 * @param createdBy
	 * @param createdByDt
	 * @param lastModifiedBy
	 * @param lastModifiedByDt
	 */
	public Visitor(String vmsId, String name, String companyName, String site, String idType, String idNo,
			String mobileNo, String vehicleNo, String hostName, String hostNo, String visitorCardId,
			String covidDeclare, String remarks, String visitPurpose, String temperature, String approvingOfficer,
			Timestamp timeInDt, String createdBy, Timestamp createdByDt, String lastModifiedBy,
			Timestamp lastModifiedByDt) {
		super();
		this.vmsId = vmsId;
		this.name = name;
		this.companyName = companyName;
		this.site = site;
		this.idType = idType;
		this.idNo = idNo;
		this.mobileNo = mobileNo;
		this.vehicleNo = vehicleNo;
		this.hostName = hostName;
		this.hostNo = hostNo;
		this.visitorCardId = visitorCardId;
		this.covidDeclare = covidDeclare;
		this.remarks = remarks;
		this.visitPurpose = visitPurpose;
		this.temperature = temperature;
		this.approvingOfficer = approvingOfficer;
		this.timeInDt = timeInDt;
		this.createdBy = createdBy;
		this.createdByDt = createdByDt;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedByDt = lastModifiedByDt;
	}
	/**
	 * @param vmsId
	 * @param name
	 * @param companyName
	 * @param site
	 * @param idType
	 * @param idNo
	 * @param mobileNo
	 * @param vehicleNo
	 * @param hostName
	 * @param hostNo
	 * @param visitorCardId
	 * @param covidDeclare
	 * @param remarks
	 * @param visitPurpose
	 * @param temperature
	 * @param approvingOfficer
	 * @param timeInDt
	 * @param timeOutDt
	 * @param createdBy
	 * @param createdByDt
	 * @param lastModifiedBy
	 * @param lastModifiedByDt
	 */
	public Visitor(String vmsId, String name, String companyName, String site, String idType, String idNo,
			String mobileNo, String vehicleNo, String hostName, String hostNo, String visitorCardId,
			String covidDeclare, String remarks, String visitPurpose, String temperature, String approvingOfficer,
			Timestamp timeInDt, Timestamp timeOutDt, String createdBy, Timestamp createdByDt, String lastModifiedBy,
			Timestamp lastModifiedByDt) {
		super();
		this.vmsId = vmsId;
		this.name = name;
		this.companyName = companyName;
		this.site = site;
		this.idType = idType;
		this.idNo = idNo;
		this.mobileNo = mobileNo;
		this.vehicleNo = vehicleNo;
		this.hostName = hostName;
		this.hostNo = hostNo;
		this.visitorCardId = visitorCardId;
		this.covidDeclare = covidDeclare;
		this.remarks = remarks;
		this.visitPurpose = visitPurpose;
		this.temperature = temperature;
		this.approvingOfficer = approvingOfficer;
		this.timeInDt = timeInDt;
		this.timeOutDt = timeOutDt;
		this.createdBy = createdBy;
		this.createdByDt = createdByDt;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedByDt = lastModifiedByDt;
	}
	
	
	/**
	 * @param vmsId
	 * @param name
	 * @param companyName
	 * @param site
	 * @param idType
	 * @param idNo
	 * @param mobileNo
	 * @param vehicleNo
	 * @param hostName
	 * @param hostNo
	 * @param visitorCardId
	 * @param covidDeclare
	 * @param remarks
	 * @param visitPurpose
	 * @param temperature
	 * @param approvingOfficer
	 * @param timeInDt
	 * @param timeOutDt
	 * @param archivedDt
	 * @param createdBy
	 * @param createdByDt
	 * @param lastModifiedBy
	 * @param lastModifiedByDt
	 */
	public Visitor(String vmsId, String name, String companyName, String site, String idType, String idNo,
			String mobileNo, String vehicleNo, String hostName, String hostNo, String visitorCardId,
			String covidDeclare, String remarks, String visitPurpose, String temperature, String approvingOfficer,
			Timestamp timeInDt, Timestamp timeOutDt, Timestamp archivedDt, String createdBy, Timestamp createdByDt,
			String lastModifiedBy, Timestamp lastModifiedByDt) {
		super();
		this.vmsId = vmsId;
		this.name = name;
		this.companyName = companyName;
		this.site = site;
		this.idType = idType;
		this.idNo = idNo;
		this.mobileNo = mobileNo;
		this.vehicleNo = vehicleNo;
		this.hostName = hostName;
		this.hostNo = hostNo;
		this.visitorCardId = visitorCardId;
		this.covidDeclare = covidDeclare;
		this.remarks = remarks;
		this.visitPurpose = visitPurpose;
		this.temperature = temperature;
		this.approvingOfficer = approvingOfficer;
		this.timeInDt = timeInDt;
		this.timeOutDt = timeOutDt;
		this.archivedDt = archivedDt;
		this.createdBy = createdBy;
		this.createdByDt = createdByDt;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedByDt = lastModifiedByDt;
	}
	/**
	 * @return the vmsId
	 */
	public String getVmsId() {
		return vmsId;
	}
	/**
	 * @param vmsId the vmsId to set
	 */
	public void setVmsId(String vmsId) {
		this.vmsId = vmsId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}
	/**
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}
	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}
	/**
	 * @param idNo the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return the vehicleNo
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}
	/**
	 * @param vehicleNo the vehicleNo to set
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}
	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	/**
	 * @return the hostNo
	 */
	public String getHostNo() {
		return hostNo;
	}
	/**
	 * @param hostNo the hostNo to set
	 */
	public void setHostNo(String hostNo) {
		this.hostNo = hostNo;
	}
	/**
	 * @return the visitorCardId
	 */
	public String getVisitorCardId() {
		return visitorCardId;
	}
	/**
	 * @param visitorCardId the visitorCardId to set
	 */
	public void setVisitorCardId(String visitorCardId) {
		this.visitorCardId = visitorCardId;
	}
	/**
	 * @return the covidDeclare
	 */
	public String getCovidDeclare() {
		return covidDeclare;
	}
	/**
	 * @param covidDeclare the covidDeclare to set
	 */
	public void setCovidDeclare(String covidDeclare) {
		this.covidDeclare = covidDeclare;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the visitPurpose
	 */
	public String getVisitPurpose() {
		return visitPurpose;
	}
	/**
	 * @param visitPurpose the visitPurpose to set
	 */
	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}
	/**
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}
	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	/**
	 * @return the approvingOfficer
	 */
	public String getApprovingOfficer() {
		return approvingOfficer;
	}
	/**
	 * @param approvingOfficer the approvingOfficer to set
	 */
	public void setApprovingOfficer(String approvingOfficer) {
		this.approvingOfficer = approvingOfficer;
	}
	/**
	 * @return the timeInDt
	 */
	public Timestamp getTimeInDt() {
		return timeInDt;
	}
	/**
	 * @param timeInDt the timeInDt to set
	 */
	public void setTimeInDt(Timestamp timeInDt) {
		this.timeInDt = timeInDt;
	}
	/**
	 * @return the timeOutDt
	 */
	public Timestamp getTimeOutDt() {
		return timeOutDt;
	}
	/**
	 * @param timeOutDt the timeOutDt to set
	 */
	public void setTimeOutDt(Timestamp timeOutDt) {
		this.timeOutDt = timeOutDt;
	}
	/**
	 * @return the archivedDt
	 */
	public Timestamp getArchivedDt() {
		return archivedDt;
	}
	/**
	 * @param archivedDt the archivedDt to set
	 */
	public void setArchivedDt(Timestamp archivedDt) {
		this.archivedDt = archivedDt;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdByDt
	 */
	public Timestamp getCreatedByDt() {
		return createdByDt;
	}
	/**
	 * @param createdByDt the createdByDt to set
	 */
	public void setCreatedByDt(Timestamp createdByDt) {
		this.createdByDt = createdByDt;
	}
	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	/**
	 * @return the lastModifiedByDt
	 */
	public Timestamp getLastModifiedByDt() {
		return lastModifiedByDt;
	}
	/**
	 * @param lastModifiedByDt the lastModifiedByDt to set
	 */
	public void setLastModifiedByDt(Timestamp lastModifiedByDt) {
		this.lastModifiedByDt = lastModifiedByDt;
	}
	@Override
	public String toString() {
		return "Visitor [vmsId=" + vmsId + ", name=" + name + ", companyName=" + companyName + ", site=" + site
				+ ", idType=" + idType + ", idNo=" + idNo + ", mobileNo=" + mobileNo + ", vehicleNo=" + vehicleNo
				+ ", hostName=" + hostName + ", hostNo=" + hostNo + ", visitorCardId=" + visitorCardId
				+ ", covidDeclare=" + covidDeclare + ", remarks=" + remarks + ", visitPurpose=" + visitPurpose
				+ ", temperature=" + temperature + ", approvingOfficer=" + approvingOfficer + ", timeInDt=" + timeInDt
				+ ", timeOutDt=" + timeOutDt + ", archivedDt=" + archivedDt + ", createdBy=" + createdBy
				+ ", createdByDt=" + createdByDt + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedByDt="
				+ lastModifiedByDt + "]";
	}
    

	
}
