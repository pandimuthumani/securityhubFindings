package com.securityhub.demo.dto;

public class GetSecurityFindingDto {

	private String title;
	private String description;
	private String finding_Id;
	private String severity;
	private String account_Id;

	public GetSecurityFindingDto() {
		super();
	}

	public GetSecurityFindingDto(String title, String description, String finding_Id, String severity,
			String account_Id) {
		super();
		this.title = title;
		this.description = description;
		this.finding_Id = finding_Id;
		this.severity = severity;
		this.account_Id = account_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinding_Id() {
		return finding_Id;
	}

	public void setFinding_Id(String finding_Id) {
		this.finding_Id = finding_Id;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getAccount_Id() {
		return account_Id;
	}

	public void setAccount_Id(String account_Id) {
		this.account_Id = account_Id;
	}

}
