package net.javatutorial.entity;

import java.sql.Timestamp;

public class Dropdown {
	private String dropdownId;
    private String dropdownKey;
    private String dropdownValue;
    private Timestamp createdDt;
    private Timestamp lastModifiedDt;
    
	public Dropdown(String dropdownId, String dropdownKey, String dropdownValue, Timestamp createdDt,
			Timestamp lastModifiedDt) {
		super();
		this.dropdownId = dropdownId;
		this.dropdownKey = dropdownKey;
		this.dropdownValue = dropdownValue;
		this.createdDt = createdDt;
		this.lastModifiedDt = lastModifiedDt;
	}
	/**
	 * @return the dropdownId
	 */
	public String getDropdownId() {
		return dropdownId;
	}
	/**
	 * @param dropdownId the dropdownId to set
	 */
	public void setDropdownId(String dropdownId) {
		this.dropdownId = dropdownId;
	}
	/**
	 * @return the dropdownKey
	 */
	public String getDropdownKey() {
		return dropdownKey;
	}
	/**
	 * @param dropdownKey the dropdownKey to set
	 */
	public void setDropdownKey(String dropdownKey) {
		this.dropdownKey = dropdownKey;
	}
	/**
	 * @return the dropdownValue
	 */
	public String getDropdownValue() {
		return dropdownValue;
	}
	/**
	 * @param dropdownValue the dropdownValue to set
	 */
	public void setDropdownValue(String dropdownValue) {
		this.dropdownValue = dropdownValue;
	}
	/**
	 * @return the createdDt
	 */
	public Timestamp getCreatedDt() {
		return createdDt;
	}
	/**
	 * @param createdDt the createdDt to set
	 */
	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}
	/**
	 * @return the lastModifiedDt
	 */
	public Timestamp getLastModifiedDt() {
		return lastModifiedDt;
	}
	/**
	 * @param lastModifiedDt the lastModifiedDt to set
	 */
	public void setLastModifiedDt(Timestamp lastModifiedDt) {
		this.lastModifiedDt = lastModifiedDt;
	}
	@Override
	public String toString() {
		return "DropdownList [dropdownId=" + dropdownId + ", dropdownKey=" + dropdownKey + ", dropdownValue="
				+ dropdownValue + ", createdDt=" + createdDt + ", lastModifiedDt=" + lastModifiedDt + "]";
	}
}
