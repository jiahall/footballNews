package com.example.news.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = { "articleURL", "newsArticleID", "publishDate", "taxonomies", "teaserText", "thumbnailImageURL",
		"title", "optaMatchId" })
@XmlRootElement(name = "NewsletterNewsItem")
public class NewsletterNewsItemXML {

	private String articleURL, taxonomies, teaserText, thumbnailImageURL, title, publishDate;
	private int newsArticleID, optaMatchId;

	@XmlElement(name = "ArticleURL")
	public void setArticleURL(String articleURL) {
		this.articleURL = articleURL;
	}

	public String getArticleURL() {
		return articleURL;
	}

	public String getTaxonomies() {
		return taxonomies;
	}

	public String getTeaserText() {
		return teaserText;
	}

	public String getThumbnailImageURL() {
		return thumbnailImageURL;
	}

	public String getTitle() {
		return title;
	}

	public int getNewsArticleID() {
		return newsArticleID;
	}

	public int getOptaMatchId() {
		return optaMatchId;
	}

	public String getPublishDate() {
		return publishDate;
	}

	@XmlElement(name = "Taxonomies")
	public void setTaxonomies(String taxonomies) {
		this.taxonomies = taxonomies;
	}

	@XmlElement(name = "TeaserText")
	public void setTeaserText(String teaserText) {
		this.teaserText = teaserText;
	}

	@XmlElement(name = "ThumbnailImageURL")
	public void setThumbnailImageURL(String thumbnailImageURL) {
		this.thumbnailImageURL = thumbnailImageURL;
	}

	@XmlElement(name = "Title")
	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name = "NewsArticleID")
	public void setNewsArticleID(int newsArticleID) {
		this.newsArticleID = newsArticleID;
	}

	@XmlElement(name = "OptaMatchId")
	public void setOptaMatchId(int optaMatchId) {
		this.optaMatchId = optaMatchId;
	}

	@XmlElement(name = "PublishDate")
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "NewsletterNewsItemXML [articleURL=" + articleURL + ", taxonomies=" + taxonomies + ", teaserText="
				+ teaserText + ", thumbnailImageURL=" + thumbnailImageURL + ", title=" + title + ", newsArticleID="
				+ newsArticleID + ", optaMatchId=" + optaMatchId + ", publishDate=" + publishDate + "]";
	}

}
