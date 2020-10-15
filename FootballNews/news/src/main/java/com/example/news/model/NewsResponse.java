package com.example.news.model;

import java.time.LocalDateTime;
import java.util.List;

public class NewsResponse {

	String status, message;
	LocalDateTime createdAt;
	int totalItems;
	List<NewListInformationXML> groupData;
	NewListInformationXML teamData;

	public NewListInformationXML getTeamData() {
		return teamData;
	}

	public void setTeamData(NewListInformationXML teamData) {
		this.teamData = teamData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public List<NewListInformationXML> getGroupData() {
		return groupData;
	}

	public void setGroupData(List<NewListInformationXML> groupData) {
		this.groupData = groupData;
	}

	public NewsResponse(String status, int totalItems, List<NewListInformationXML> groupData) {
		super();
		this.status = status;
		createdAt = LocalDateTime.now();
		this.totalItems = totalItems;
		this.groupData = groupData;
	}

	public NewsResponse(String status, int totalItems, NewListInformationXML teamData) {
		super();
		this.status = status;
		createdAt = LocalDateTime.now();
		this.totalItems = totalItems;
		this.teamData = teamData;
	}

	public NewsResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
		createdAt = LocalDateTime.now();

	}

}
