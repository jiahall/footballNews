package com.example.news.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class NewListInformation {

	@Id
	String id;
	String clubName, clubWebsiteURL;
	ArrayList<NewsletterNewsItem> newsLetterNewsItems;

	public NewListInformation(String clubName, String clubWebsiteURL,
			ArrayList<NewsletterNewsItem> newsLetterNewsItems) {
		super();
		this.clubName = clubName;
		this.clubWebsiteURL = clubWebsiteURL;
		this.newsLetterNewsItems = newsLetterNewsItems;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubWebsiteURL() {
		return clubWebsiteURL;
	}

	public void setClubWebsiteURL(String clubWebsiteURL) {
		this.clubWebsiteURL = clubWebsiteURL;
	}

	public ArrayList<NewsletterNewsItem> getNewsLetterNewsItems() {
		return newsLetterNewsItems;
	}

	public void setNewsLetterNewsItems(ArrayList<NewsletterNewsItem> newsLetterNewsItems) {
		this.newsLetterNewsItems = newsLetterNewsItems;
	}

}
