/**
 * 
 */
package com.neu.info7255.demo;

import java.util.ArrayList;

/**
 * @author richabhatia
 *
 */
public class InsurancePlan {
	
	private String creationDate;
	private String _org;
	private String objectId;
	private String objectType;
	private String planType;
	
	
	private PlanCostShares planCostShares;
	private ArrayList<LinkedPlanServices> linkedPlanServicesList;
	//= new ArrayList<LinkedPlanServices>();
	
	//LinkedPlanServices linkedPlanServices;
	
	


	



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "PlanDetails creationDate: "+ creationDate + ", org: " +_org + ", objectId: " + objectId+ ", objectType: "+objectType;
	}

	

	/*public InsurancePlan(String creationDate, String _org, String objectId, String objectType, String planType,
			PlanCostShares planCostShares, LinkedPlanServices linkedPlanServices) {
		super();
		this.creationDate = creationDate;
		this._org = _org;
		this.objectId = objectId;
		this.objectType = objectType;
		this.planType = planType;
		this.planCostShares = planCostShares;
	
		
		//this.linkedPlanServicesList = new ArrayList<LinkedPlanServices>();
		linkedPlanServicesList.add(linkedPlanServices);
		//this.setLinkedPlanServicesList(linkedPlanServicesList);
	}
	*/


	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getOrg() {
		return _org;
	}

	public void setOrg(String _org) {
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

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public InsurancePlan() {
		// TODO Auto-generated constructor stub
	}

	public PlanCostShares getPlanCostShares() {
		return planCostShares;
	}

	public void setPlanCostShares(PlanCostShares planCostShares) {
		this.planCostShares = planCostShares;
	}



	public ArrayList<LinkedPlanServices> getLinkedPlanServicesList() {
		return linkedPlanServicesList;
	}



	public void setLinkedPlanServicesList(ArrayList<LinkedPlanServices> linkedPlanServicesList) {
		this.linkedPlanServicesList = linkedPlanServicesList;
	}

	public void createLinkedPlanServicesList(){
		
		linkedPlanServicesList = new ArrayList<LinkedPlanServices>();
		
		LinkedService ls = new LinkedService();
		ls.setName("test");
		ls.setObjectId("test");
		ls.setObjectType("test");
		ls.setOrg("test");
		PlanserviceCostShares pcs = new PlanserviceCostShares();
		pcs.set_org("test");
		pcs.setCopay("10");
		pcs.setDeductible("10");
		pcs.setObjectId("test");
		pcs.setObjectType("test");
		
		
		LinkedPlanServices lps = new LinkedPlanServices();
		lps.set_org("test");
		lps.setObjectId("test");
		lps.setObjectType("test");
		
		lps.setLinkedService(ls);
		lps.setPlanServiceCostShares(pcs);
		linkedPlanServicesList.add(lps);
	}

	

	
}
