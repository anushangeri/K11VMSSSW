package net.javatutorial.entity;

import java.sql.Timestamp;

public class Site {
	private String siteId;
    private String siteName;
    private String companyName;
    private Timestamp createdDt;
    private Timestamp lastModifiedDt;
    
	public Site(String siteId, String siteName, String companyName, Timestamp createdDt, Timestamp lastModifiedDt) {
		super();
		this.siteId = siteId;
		this.siteName = siteName;
		this.companyName = companyName;
		this.createdDt = createdDt;
		this.lastModifiedDt = lastModifiedDt;
	}    
    
	/**
	 * @return the siteId
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the siteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * @param siteName the siteName to set
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
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
		return "Site [siteId=" + siteId + ", siteName=" + siteName + ", companyName=" + companyName + ", createdDt="
				+ createdDt + ", lastModifiedDt=" + lastModifiedDt + "]";
	}
	
}
