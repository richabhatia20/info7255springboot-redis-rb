/**
 * 
 */
package com.neu.info7255.demo;

import java.io.Serializable;

/**
 * @author richabhatia
 *
 */
public class LinkedPlanServices implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private LinkedService linkedService;
	private PlanserviceCostShares planServiceCostShares;
	private String _org;
	private String objectId;
	private String objectType;
	
	public LinkedPlanServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LinkedPlanServices [linkedService=" + linkedService + ", planServiceCostShares=" + planServiceCostShares
				+ ", _org=" + _org + ", objectId=" + objectId + ", objectType=" + objectType + "]";
	}

	public LinkedService getLinkedService() {
		return linkedService;
	}

	public void setLinkedService(LinkedService linkedService) {
		this.linkedService = linkedService;
	}

	public PlanserviceCostShares getPlanServiceCostShares() {
		return planServiceCostShares;
	}

	public void setPlanServiceCostShares(PlanserviceCostShares planServiceCostShares) {
		this.planServiceCostShares = planServiceCostShares;
	}

	public String get_org() {
		return _org;
	}

	public void set_org(String _org) {
		this._org = _org;
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
