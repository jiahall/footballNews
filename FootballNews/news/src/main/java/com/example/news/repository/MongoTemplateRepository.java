package com.example.news.repository;

import java.util.List;

import com.example.news.model.NewListInformationXML;
import com.example.news.model.NewsletterNewsItemXML;

public interface MongoTemplateRepository {

	List<NewListInformationXML> findByNewsletterNewsItemsNewsArticleID(String teamName, int ArticleID);

	public void insertNews(List<NewsletterNewsItemXML> newsUpdates, String clubName);

}
