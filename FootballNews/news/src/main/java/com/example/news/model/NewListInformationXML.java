package com.example.news.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "clubName", "clubWebsiteURL", "newsletterNewsItems" })
@XmlRootElement(name = "NewListInformation")
public class NewListInformationXML {
	private String clubName, clubWebsiteURL, _id;
	List<NewsletterNewsItemXML> newsletterNewsItems;

	public String getClubName() {
		return clubName;
	}

	@XmlElement(name = "ClubName")
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String get_id() {
		return _id;
	}

	public List<NewsletterNewsItemXML> getNewsletterNewsItems() {
		return newsletterNewsItems;
	}

	@XmlElement(name = "NewsletterNewsItem")
	public void setNewsletterNewsItems(List<NewsletterNewsItemXML> newsletterNewsItems) {
		this.newsletterNewsItems = newsletterNewsItems;
	}

	public String getClubWebsiteURL() {
		return clubWebsiteURL;
	}

	@XmlElement(name = "ClubWebsiteURL")
	public void setClubWebsiteURL(String clubWebsiteURL) {
		this.clubWebsiteURL = clubWebsiteURL;
	}

	public void add(NewsletterNewsItemXML newsletterNewsItemXML) {
		if (this.newsletterNewsItems == null) {
			this.newsletterNewsItems = new ArrayList<NewsletterNewsItemXML>();
		}
		this.newsletterNewsItems.add(newsletterNewsItemXML);

	}

	@Override
	public String toString() {

		String results = "";
		for (NewsletterNewsItemXML d : newsletterNewsItems) {
			results += d.toString();
			results += "\n";
		}
		return "NewListInformationXML [clubName=" + clubName + ", clubWebsiteURL=" + clubWebsiteURL
				+ ", newsletterNewsItems=" + newsletterNewsItems.toString() + results + "]";
	}
}
