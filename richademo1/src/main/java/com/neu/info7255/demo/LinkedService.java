/**
 * 
 */
package com.neu.info7255.demo;

/**
 * @author richabhatia
 *
 */
public class LinkedService {

	private String _org;
	private String objectId;
	private String objectType;
	private String name;
	
	public LinkedService() {
		// TODO Auto-generated constructor stub
	}

	
	

	
	
	@Override
	public String toString() {
		return "LinkedService [_org=" + _org + ", objectId=" + objectId + ", objectType=" + objectType + ", name="
				+ name + "]";
	}






	public LinkedService(String _org, String objectId, String objectType, String name) {
	
		this._org = _org;
		this.objectId = objectId;
		this.objectType = objectType;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
