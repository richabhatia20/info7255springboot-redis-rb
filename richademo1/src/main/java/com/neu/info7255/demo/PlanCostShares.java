/**
 * 
 */
package com.neu.info7255.demo;

/**
 * @author richabhatia
 *
 */
public class PlanCostShares {

	private String deductible;
	private String _org;
	private String copay;
	private String objectId;
	private String objectType;
	
	public PlanCostShares() {
		// TODO Auto-generated constructor stub
	}

	public PlanCostShares(String deductible, String _org, String copay, String objectId, String objectType) {
		
		this.deductible = deductible;
		this._org = _org;
		this.copay = copay;
		this.objectId = objectId;
		this.objectType = objectType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "PlanCostShares deductible: "+ deductible + ", copay:"+copay+", org: " +_org + ", objectId: " + objectId+ ", objectType: "+objectType;
	}

	public String getDeductible() {
		return deductible;
	}

	public void setDeductible(String deductible) {
		this.deductible = deductible;
	}

	public String getOrg() {
		return _org;
	}

	public void setOrg(String _org) {
		this._org = _org;
	}

	public String getCopay() {
		return copay;
	}

	public void setCopay(String copay) {
		this.copay = copay;
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
	
	

	
}
