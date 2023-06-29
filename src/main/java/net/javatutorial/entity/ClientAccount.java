package net.javatutorial.entity;

import java.sql.Timestamp;

public class ClientAccount {
	private String accountId;
    private String name;
    private String[] site; //essentially this is the site name in the site module, site name is also considered a warehouse name
    private String idType;
    private String idNo; // also can be generic username
    private String password;
    private String salt;
    private String accessType; //ADMIN, MANAGEMENT, OFFICER, WAREHOUSE (can be added via dropdown module)
    private Timestamp createdDt;
    private Timestamp modifiedDt;
    
	public ClientAccount(String accountId, String name, String[] site, String idType, String idNo, String password,
			String salt, String accessType, Timestamp createdDt, Timestamp modifiedDt) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.site = site;
		this.idType = idType;
		this.idNo = idNo;
		this.password = password;
		this.salt = salt;
		this.accessType = accessType;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	 * @return the site
	 */
	public String[] getSite() {
		return site;
	}
	/**
	 * @param site the site to set
	 */
	public void setSite(String[] site) {
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return the accessType
	 */
	public String getAccessType() {
		return accessType;
	}
	/**
	 * @param accessType the accessType to set
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
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
	 * @return the modifiedDt
	 */
	public Timestamp getModifiedDt() {
		return modifiedDt;
	}
	/**
	 * @param modifiedDt the modifiedDt to set
	 */
	public void setModifiedDt(Timestamp modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "ClientAccount [accountId=" + accountId + ", name=" + name + ", site=" + site + ", idType=" + idType
				+ ", idNo=" + idNo + ", password=" + password + ", salt=" + salt + ", accessType=" + accessType
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

    
}
