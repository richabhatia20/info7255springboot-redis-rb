/**
 * 
 */
package com.neu.info7255.demo;

/**
 * @author richabhatia
 *
 */
public class PlanserviceCostShares {

	private String deductible;
	private String _org;
	private String copay;
	private String objectId;
	private String objectType;
	
	public PlanserviceCostShares() {
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "PlanserviceCostShares [deductible=" + deductible + ", _org=" + _org + ", copay=" + copay + ", objectId="
				+ objectId + ", objectType=" + objectType + "]";
	}



	public PlanserviceCostShares(String deductible, String _org, String copay, String objectId, String objectType) {
		super();
		this.deductible = deductible;
		this._org = _org;
		this.copay = copay;
		this.objectId = objectId;
		this.objectType = objectType;
	}

	public String getDeductible() {
		return deductible;
	}

	public void setDeductible(String deductible) {
		this.deductible = deductible;
	}

	public String get_org() {
		return _org;
	}

	public void set_org(String _org) {
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