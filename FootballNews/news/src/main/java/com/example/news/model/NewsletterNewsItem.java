package com.example.news.model;

public class NewsletterNewsItem {

	String id, articleURL, taxonomies, teaserText, thumbnailImageURL, title, publishDate, optaMatchID;
	int newsArticleID;

	public NewsletterNewsItem() {
	}

	public NewsletterNewsItem(String articleURL, String taxonomies, String teaserText, String thumbnailImageURL,
			String title, String publishDate, String optaMatchID, int newsArticleID) {
		super();
		this.articleURL = articleURL;
		this.taxonomies = taxonomies;
		this.teaserText = teaserText;
		this.thumbnailImageURL = thumbnailImageURL;
		this.title = title;
		this.publishDate = publishDate;
		this.optaMatchID = optaMatchID;
		this.newsArticleID = newsArticleID;
	}

	public String getArticleURL() {
		return articleURL;
	}

	public void setArticleURL(String articleURL) {
		this.articleURL = articleURL;
	}

	public String getTaxonomies() {
		return taxonomies;
	}

	public void setTaxonomies(String taxonomies) {
		this.taxonomies = taxonomies;
	}

	public String getTeaserText() {
		return teaserText;
	}

	public void setTeaserText(String teaserText) {
		this.teaserText = teaserText;
	}

	public String getThumbnailImageURL() {
		return thumbnailImageURL;
	}

	public void setThumbnailImageURL(String thumbnailImageURL) {
		this.thumbnailImageURL = thumbnailImageURL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getOptaMatchID() {
		return optaMatchID;
	}

	public void setOptaMatchID(String optaMatchID) {
		this.optaMatchID = optaMatchID;
	}

	public int getNewsArticleID() {
		return newsArticleID;
	}

	public void setNewsArticleID(int newsArticleID) {
		this.newsArticleID = newsArticleID;
	}

}
