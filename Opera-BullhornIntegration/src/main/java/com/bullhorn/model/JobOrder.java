package com.bullhorn.model;


public class JobOrder{
    public int Id;
    public Address address;
    public String branchCode;
    public ClientContact clientContact;
    public ClientCorporation clientCorporation;
    public String costCenter;
    public String customText7;
    public String title;
	@Override
	public String toString() {
		return "JobOrder [Id=" + Id + ", clientContact=" + clientContact + ", clientCorporation=" + clientCorporation
				+ ", title=" + title + "]";
	}

}

