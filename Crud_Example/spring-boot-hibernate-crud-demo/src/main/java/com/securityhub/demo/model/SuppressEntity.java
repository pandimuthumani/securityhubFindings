package com.securityhub.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Security_Suppress")
public class SuppressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "finding_Id", length = 10000)
	private String finding_Id;
	@Column(name = "requester_Name", length = 10000)
	private String requester_Name;
	@Column(name = "comments", length = 10000)
	private String comments;
	@Column(name = "status", length = 255)
	private String status;
	@Column(name = "date")
	private Date date;

	public SuppressEntity() {
		super();
	}

	public SuppressEntity(String finding_Id, String requester_Name, String comments, String status, Date date) {
		super();
		this.finding_Id = finding_Id;
		this.requester_Name = requester_Name;
		this.comments = comments;
		this.status = status;
		this.date = date;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
