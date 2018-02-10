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
	private String org;
	private String objectId;
	private String objectType;
	private String planType;
	
	
	private PlanCostShares planCostShares;
	private ArrayList<LinkedPlanServices> linkedPlanServices;
	//= new ArrayList<LinkedPlanServices>();
	
	//LinkedPlanServices linkedPlanServices;
	
	


	



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "PlanDetails creationDate: "+ creationDate + ", org: " +org + ", objectId: " + objectId+ ", objectType: "+objectType;
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
		return org;
	}

	public void setOrg(String _org) {
		this.org = _org;
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
		return linkedPlanServices;
	}



	public void setLinkedPlanServicesList(ArrayList<LinkedPlanServices> linkedPlanServicesList) {
		this.linkedPlanServices = linkedPlanServicesList;
	}

	public void createLinkedPlanServicesList(){
		
		linkedPlanServices = new ArrayList<LinkedPlanServices>();
		
		LinkedService ls = new LinkedService();
		ls.setName("here.com");
		ls.setObjectId("1134520xvc30asdfm");
		ls.setObjectType("service");
		ls.setOrg("Yearly physical exam");
		PlanserviceCostShares pcs = new PlanserviceCostShares();
		pcs.set_org("test");
		pcs.setCopay("90");
		pcs.setDeductible("here.com");
		pcs.setObjectId("6134512xvc1314asdfm");
		pcs.setObjectType("membercostshareobjectType");
		
		
		LinkedPlanServices lps = new LinkedPlanServices();
		lps.setOrg("test");
		lps.setObjectId("test");
		lps.setObjectType("test");
		
		lps.setLinkedService(ls);
		lps.setPlanServiceCostShares(pcs);
		linkedPlanServices.add(lps);
	}

	

	
}
