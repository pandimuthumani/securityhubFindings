package com.securityhub.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Security_Findings")
public class EmployeeEntity {

	public EmployeeEntity() {
		super();
	}

	public EmployeeEntity(String title, String description, String finding_Id, String severity, String account_Id) {
		super();
		this.title = title;
		this.description = description;
		this.finding_Id = finding_Id;
		this.severity = severity;
		this.account_Id = account_Id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "title", length = 10000)
	private String title;
	@Column(name = "description", length = 10000)
	private String description;
	@Column(name = "finding_Id", length = 10000)
	private String finding_Id;
	@Column(name = "severity", length = 10000)
	private String severity;
	@Column(name = "account_Id", length = 10000)
	private String account_Id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

//	@Column(name = "aws_Id", nullable = false)
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

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", title=" + title + ", description=" + description + ", finding_Id="
				+ finding_Id + ", severity=" + severity + ", account_Id=" + account_Id + "]";
	}
}