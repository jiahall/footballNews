package com.example.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.news.model.NewListInformationXML;
import com.example.news.model.NewsletterNewsItemXML;
import com.example.news.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsRepository newsRepository;

	@Override
	public List<NewListInformationXML> findAll() {
		return newsRepository.findAll();

	}

	@Override
	public void deleteByClubName(String clubName) {
		newsRepository.deleteByClubName(clubName);

	}

	@Override
	public NewListInformationXML findByClubName(String clubName) {
		return newsRepository.findByClubName(clubName);

	}

	@Override
	public Boolean existsByClubName(String clubName) {

		return newsRepository.existsByClubName(clubName);
	}

	@Override
	public List<NewListInformationXML> findByNewsletterNewsItemsNewsArticleID(String teamName, int ArticleID) {

		return newsRepository.findByNewsletterNewsItemsNewsArticleID(teamName, ArticleID);
	}

	@Override
	public void save(NewListInformationXML teamData) {
		newsRepository.save(teamData);

	}

	@Override
	public void insertNews(List<NewsletterNewsItemXML> newsUpdates, String clubName) {
		newsRepository.insertNews(newsUpdates, clubName);

	}

}
