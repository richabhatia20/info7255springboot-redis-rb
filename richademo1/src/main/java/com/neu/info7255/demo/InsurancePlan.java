/**
 * 
 */
package com.neu.info7255.demo;

/**
 * @author richabhatia
 *
 */
public class InsurancePlan {
	
	private String creationDate;
	private String org;
	private String objectId;
	private String objectType;
	private String planType;
	
	
	
	
	
	public InsurancePlan(String creationDate, String org, String objectId, String objectType, String planType) {
		
		this.creationDate = creationDate;
		this.org = org;
		this.objectId = objectId;
		this.objectType = objectType;
		this.planType = planType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "PlanDetails creationDate: "+ creationDate + ", org: " +org + ", objectId: " + objectId+ ", objectType: "+objectType;
	}

	

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public InsurancePlan() {
		// TODO Auto-generated constructor stub
	}

}
