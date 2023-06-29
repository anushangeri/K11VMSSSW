package net.javatutorial.entity;

import java.sql.Timestamp;

public class Vehicle {
	private String vehicleId;
    private String name;
    private String companyName;
    private String idType;
    private String idNo;
    private String mobileNo;
    private String primeMoverNo;
    private String containerNo;
    private String loadedNoLoaded;
    private String covidDeclare;
    private String lorryChetNumber;
    private String deliveryNoticeNumber;
    private String visitPurpose;
    private String temperature;
    private String sealNo;
    private String containerSize;
    private String remarks;
    private Integer warehouseLevel; 
    private String site; //aka warehouse name
    private String warehouseApprover; //ID/Username of warehouse 
    private Timestamp timeInDt;
    private Timestamp timeOutDt;
    private Timestamp archivedDt;
    private String createdBy;
    private Timestamp createdByDt;
    private String lastModifiedBy;
    private Timestamp lastModifiedByDt;
	/**
	 * @param vehicleId
	 * @param name
	 * @param companyName
	 * @param idType
	 * @param idNo
	 * @param mobileNo
	 * @param primeMoverNo
	 * @param containerNo
	 * @param loadedNoLoaded
	 * @param covidDeclare
	 * @param lorryChetNumber
	 * @param deliveryNoticeNumber
	 * @param visitPurpose
	 * @param temperature
	 * @param sealNo
	 * @param containerSize
	 * @param remarks
	 * @param warehouseLevel
	 * @param site
	 * @param warehouseApprover
	 * @param timeInDt
	 * @param createdBy
	 * @param createdByDt
	 * @param lastModifiedBy
	 * @param lastModifiedByDt
	 */
	public Vehicle(String vehicleId, String name, String companyName, String idType, String idNo, String mobileNo,
			String primeMoverNo, String containerNo, String loadedNoLoaded, String covidDeclare, String lorryChetNumber,
			String deliveryNoticeNumber, String visitPurpose, String temperature, String sealNo, String containerSize,
			String remarks, Integer warehouseLevel, String site, String warehouseApprover, Timestamp timeInDt,
			String createdBy, Timestamp createdByDt, String lastModifiedBy, Timestamp lastModifiedByDt) {
		super();
		this.vehicleId = vehicleId;
		this.name = name;
		this.companyName = companyName;
		this.idType = idType;
		this.idNo = idNo;
		this.mobileNo = mobileNo;
		this.primeMoverNo = primeMoverNo;
		this.containerNo = containerNo;
		this.loadedNoLoaded = loadedNoLoaded;
		this.covidDeclare = covidDeclare;
		this.lorryChetNumber = lorryChetNumber;
		this.deliveryNoticeNumber = deliveryNoticeNumber;
		this.visitPurpose = visitPurpose;
		this.temperature = temperature;
		this.sealNo = sealNo;
		this.containerSize = containerSize;
		this.remarks = remarks;
		this.warehouseLevel = warehouseLevel;
		this.site = site;
		this.warehouseApprover = warehouseApprover;
		this.timeInDt = timeInDt;
		this.createdBy = createdBy;
		this.createdByDt = createdByDt;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedByDt = lastModifiedByDt;
	}
	/**
	 * @param vehicleId
	 * @param name
	 * @param companyName
	 * @param idType
	 * @param idNo
	 * @param mobileNo
	 * @param primeMoverNo
	 * @param containerNo
	 * @param loadedNoLoaded
	 * @param covidDeclare
	 * @param lorryChetNumber
	 * @param deliveryNoticeNumber
	 * @param visitPurpose
	 * @param temperature
	 * @param sealNo
	 * @param containerSize
	 * @param remarks
	 * @param warehouseLevel
	 * @param site
	 * @param warehouseApprover
	 * @param timeInDt
	 * @param timeOutDt
	 * @param createdBy
	 * @param createdByDt
	 * @param lastModifiedBy
	 * @param lastModifiedByDt
	 */
	public Vehicle(String vehicleId, String name, String companyName, String idType, String idNo, String mobileNo,
			String primeMoverNo, String containerNo, String loadedNoLoaded, String covidDeclare, String lorryChetNumber,
			String deliveryNoticeNumber, String visitPurpose, String temperature, String sealNo, String containerSize,
			String remarks, Integer warehouseLevel, String site, String warehouseApprover, Timestamp timeInDt,
			Timestamp timeOutDt, String createdBy, Timestamp createdByDt, String lastModifiedBy,
			Timestamp lastModifiedByDt) {
		super();
		this.vehicleId = vehicleId;
		this.name = name;
		this.companyName = companyName;
		this.idType = idType;
		this.idNo = idNo;
		this.mobileNo = mobileNo;
		this.primeMoverNo = primeMoverNo;
		this.containerNo = containerNo;
		this.loadedNoLoaded = loadedNoLoaded;
		this.covidDeclare = covidDeclare;
		this.lorryChetNumber = lorryChetNumber;
		this.deliveryNoticeNumber = deliveryNoticeNumber;
		this.visitPurpose = visitPurpose;
		this.temperature = temperature;
		this.sealNo = sealNo;
		this.containerSize = containerSize;
		this.remarks = remarks;
		this.warehouseLevel = warehouseLevel;
		this.site = site;
		this.warehouseApprover = warehouseApprover;
		this.timeInDt = timeInDt;
		this.timeOutDt = timeOutDt;
		this.createdBy = createdBy;
		this.createdByDt = createdByDt;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedByDt = lastModifiedByDt;
	}
	/**
	 * @param vehicleId
	 * @param name
	 * @param companyName
	 * @param idType
	 * @param idNo
	 * @param mobileNo
	 * @param primeMoverNo
	 * @param containerNo
	 * @param loadedNoLoaded
	 * @param covidDeclare
	 * @param lorryChetNumber
	 * @param deliveryNoticeNumber
	 * @param visitPurpose
	 * @param temperature
	 * @param sealNo
	 * @param containerSize
	 * @param remarks
	 * @param warehouseLevel
	 * @param site
	 * @param warehouseApprover
	 * @param timeInDt
	 * @param timeOutDt
	 * @param archivedDt
	 * @param createdBy
	 * @param createdByDt
	 * @param lastModifiedBy
	 * @param lastModifiedByDt
	 */
	public Vehicle(String vehicleId, String name, String companyName, String idType, String idNo, String mobileNo,
			String primeMoverNo, String containerNo, String loadedNoLoaded, String covidDeclare, String lorryChetNumber,
			String deliveryNoticeNumber, String visitPurpose, String temperature, String sealNo, String containerSize,
			String remarks, Integer warehouseLevel, String site, String warehouseApprover, Timestamp timeInDt,
			Timestamp timeOutDt, Timestamp archivedDt, String createdBy, Timestamp createdByDt, String lastModifiedBy,
			Timestamp lastModifiedByDt) {
		super();
		this.vehicleId = vehicleId;
		this.name = name;
		this.companyName = companyName;
		this.idType = idType;
		this.idNo = idNo;
		this.mobileNo = mobileNo;
		this.primeMoverNo = primeMoverNo;
		this.containerNo = containerNo;
		this.loadedNoLoaded = loadedNoLoaded;
		this.covidDeclare = covidDeclare;
		this.lorryChetNumber = lorryChetNumber;
		this.deliveryNoticeNumber = deliveryNoticeNumber;
		this.visitPurpose = visitPurpose;
		this.temperature = temperature;
		this.sealNo = sealNo;
		this.containerSize = containerSize;
		this.remarks = remarks;
		this.warehouseLevel = warehouseLevel;
		this.site = site;
		this.warehouseApprover = warehouseApprover;
		this.timeInDt = timeInDt;
		this.timeOutDt = timeOutDt;
		this.archivedDt = archivedDt;
		this.createdBy = createdBy;
		this.createdByDt = createdByDt;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedByDt = lastModifiedByDt;
	}
	
	
	/**
	 * @param vehicleId
	 * @param name
	 * @param companyName
	 * @param idType
	 * @param idNo
	 * @param mobileNo
	 * @param primeMoverNo
	 * @param containerNo
	 * @param loadedNoLoaded
	 * @param covidDeclare
	 * @param lorryChetNumber
	 * @param deliveryNoticeNumber
	 * @param visitPurpose
	 * @param temperature
	 * @param sealNo
	 * @param containerSize
	 * @param remarks
	 * @param warehouseLevel
	 * @param site
	 * @param timeInDt
	 * @param createdBy
	 * @param createdByDt
	 * @param lastModifiedBy
	 * @param lastModifiedByDt
	 */
	public Vehicle(String vehicleId, String name, String companyName, String idType, String idNo, String mobileNo,
			String primeMoverNo, String containerNo, String loadedNoLoaded, String covidDeclare, String lorryChetNumber,
			String deliveryNoticeNumber, String visitPurpose, String temperature, String sealNo, String containerSize,
			String remarks, Integer warehouseLevel, String site, Timestamp timeInDt, String createdBy,
			Timestamp createdByDt, String lastModifiedBy, Timestamp lastModifiedByDt) {
		super();
		this.vehicleId = vehicleId;
		this.name = name;
		this.companyName = companyName;
		this.idType = idType;
		this.idNo = idNo;
		this.mobileNo = mobileNo;
		this.primeMoverNo = primeMoverNo;
		this.containerNo = containerNo;
		this.loadedNoLoaded = loadedNoLoaded;
		this.covidDeclare = covidDeclare;
		this.lorryChetNumber = lorryChetNumber;
		this.deliveryNoticeNumber = deliveryNoticeNumber;
		this.visitPurpose = visitPurpose;
		this.temperature = temperature;
		this.sealNo = sealNo;
		this.containerSize = containerSize;
		this.remarks = remarks;
		this.warehouseLevel = warehouseLevel;
		this.site = site;
		this.timeInDt = timeInDt;
		this.createdBy = createdBy;
		this.createdByDt = createdByDt;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedByDt = lastModifiedByDt;
	}
	/**
	 * @return the vehicleId
	 */
	public String getVehicleId() {
		return vehicleId;
	}
	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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
	 * @return the primeMoverNo
	 */
	public String getPrimeMoverNo() {
		return primeMoverNo;
	}
	/**
	 * @param primeMoverNo the primeMoverNo to set
	 */
	public void setPrimeMoverNo(String primeMoverNo) {
		this.primeMoverNo = primeMoverNo;
	}
	/**
	 * @return the containerNo
	 */
	public String getContainerNo() {
		return containerNo;
	}
	/**
	 * @param containerNo the containerNo to set
	 */
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	/**
	 * @return the loadedNoLoaded
	 */
	public String getLoadedNoLoaded() {
		return loadedNoLoaded;
	}
	/**
	 * @param loadedNoLoaded the loadedNoLoaded to set
	 */
	public void setLoadedNoLoaded(String loadedNoLoaded) {
		this.loadedNoLoaded = loadedNoLoaded;
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
	 * @return the lorryChetNumber
	 */
	public String getLorryChetNumber() {
		return lorryChetNumber;
	}
	/**
	 * @param lorryChetNumber the lorryChetNumber to set
	 */
	public void setLorryChetNumber(String lorryChetNumber) {
		this.lorryChetNumber = lorryChetNumber;
	}
	/**
	 * @return the deliveryNoticeNumber
	 */
	public String getDeliveryNoticeNumber() {
		return deliveryNoticeNumber;
	}
	/**
	 * @param deliveryNoticeNumber the deliveryNoticeNumber to set
	 */
	public void setDeliveryNoticeNumber(String deliveryNoticeNumber) {
		this.deliveryNoticeNumber = deliveryNoticeNumber;
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
	 * @return the sealNo
	 */
	public String getSealNo() {
		return sealNo;
	}
	/**
	 * @param sealNo the sealNo to set
	 */
	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}
	/**
	 * @return the containerSize
	 */
	public String getContainerSize() {
		return containerSize;
	}
	/**
	 * @param containerSize the containerSize to set
	 */
	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
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
	 * @return the warehouseLevel
	 */
	public Integer getWarehouseLevel() {
		return warehouseLevel;
	}
	/**
	 * @param warehouseLevel the warehouseLevel to set
	 */
	public void setWarehouseLevel(Integer warehouseLevel) {
		this.warehouseLevel = warehouseLevel;
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
	 * @return the warehouseApprover
	 */
	public String getWarehouseApprover() {
		return warehouseApprover;
	}
	/**
	 * @param warehouseApprover the warehouseApprover to set
	 */
	public void setWarehouseApprover(String warehouseApprover) {
		this.warehouseApprover = warehouseApprover;
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
		return "Vehicle [vehicleId=" + vehicleId + ", name=" + name + ", companyName=" + companyName + ", idType="
				+ idType + ", idNo=" + idNo + ", mobileNo=" + mobileNo + ", primeMoverNo=" + primeMoverNo
				+ ", containerNo=" + containerNo + ", loadedNoLoaded=" + loadedNoLoaded + ", covidDeclare="
				+ covidDeclare + ", lorryChetNumber=" + lorryChetNumber + ", deliveryNoticeNumber="
				+ deliveryNoticeNumber + ", visitPurpose=" + visitPurpose + ", temperature=" + temperature + ", sealNo="
				+ sealNo + ", containerSize=" + containerSize + ", remarks=" + remarks + ", warehouseLevel="
				+ warehouseLevel + ", site=" + site + ", warehouseApprover=" + warehouseApprover + ", timeInDt="
				+ timeInDt + ", timeOutDt=" + timeOutDt + ", archivedDt=" + archivedDt + ", createdBy=" + createdBy
				+ ", createdByDt=" + createdByDt + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedByDt="
				+ lastModifiedByDt + "]";
	}

}
