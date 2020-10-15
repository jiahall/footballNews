package com.example.news.service;

import java.util.List;

import com.example.news.model.NewListInformationXML;
import com.example.news.model.NewsletterNewsItemXML;

public interface NewsService {

	public List<NewListInformationXML> findAll();

	public void deleteByClubName(String clubName);

	public NewListInformationXML findByClubName(String clubName);

	public Boolean existsByClubName(String clubName);

	public List<NewListInformationXML> findByNewsletterNewsItemsNewsArticleID(String teamName, int ArticleID);

	public void save(NewListInformationXML teamData);

	public void insertNews(List<NewsletterNewsItemXML> newsUpdates, String clubName);
}
