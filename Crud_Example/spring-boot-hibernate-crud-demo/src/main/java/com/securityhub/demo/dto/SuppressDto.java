package com.securityhub.demo.dto;

public class SuppressDto {

	public SuppressDto() {
		super();
	}

	private String finding_Id;
	private String requester_Name;
	private String comments;
	private String status;

	public String getFinding_Id() {
		return finding_Id;
	}

	public void setFinding_Id(String finding_Id) {
		this.finding_Id = finding_Id;
	}

	public String getRequester_Name() {
		return requester_Name;
	}

	public void setRequester_Name(String requester_Name) {
		this.requester_Name = requester_Name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
