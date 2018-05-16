package com.bullhorn.rest.authmodel;

public class BhAccessInfo {
	String BhRestToken;
	String restUrl;

	public BhAccessInfo(String BhRestToken, String restUrl) {
		super();
		this.BhRestToken = BhRestToken;
		this.restUrl = restUrl;
	}

	public String getBhRestToken() {
		return BhRestToken;
	}

	public void setBhRestToken(String BhRestToken) {
		this.BhRestToken = BhRestToken;
	}

	public String getRestUrl() {
		return restUrl;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}

	@Override
	public String toString() {
		return "BhRestAccess [BhRestToken=" + BhRestToken + ", BhRestUrl=" + restUrl + "]";
	}

}
