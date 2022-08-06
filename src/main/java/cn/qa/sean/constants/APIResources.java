package cn.qa.sean.constants;

public enum APIResources {
	CreateConnectInfoAPI("/v1/api/connections"),
	DeleteConnectAPI("/v1/api/connections/batchDelete"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private final String resource;

	APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
}
