package com.example.news.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.news.model.NewListInformationXML;

import com.example.news.model.NewsletterNewsItemXML;

public class NewsRepositoryImpl implements MongoTemplateRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	NewsRepository repository;

	@Override
	public List<NewListInformationXML> findByNewsletterNewsItemsNewsArticleID(String teamName, int ArticleID) {
		Criteria criteria = Criteria.where("clubName").is(teamName);
		Query query = new Query().addCriteria(criteria);
		query.fields().elemMatch("newsletterNewsItems", Criteria.where("newsArticleID").is(ArticleID));
		query.fields().exclude("_id");
		query.fields().include("clubName");
		query.fields().include("clubWebsiteURL");
		return (List<NewListInformationXML>) mongoTemplate.find(query, NewListInformationXML.class);
	}

	public void insertNews(List<NewsletterNewsItemXML> newsUpdates, String clubName) {

		for (NewsletterNewsItemXML newsUpdate : newsUpdates) {

			List<NewListInformationXML> clubNews = repository.findByNewsletterNewsItemsNewsArticleID(clubName,
					newsUpdate.getNewsArticleID());
			if (clubNews.get(0).getNewsletterNewsItems() != null) {
				break;
			}

			Query sQuery = new Query();

			Criteria sCriteria = Criteria.where("clubName").is(clubName);
			sQuery.addCriteria(sCriteria);
			Update sUpdate = new Update();
			sUpdate.addToSet("newsletterNewsItems", newsUpdate);

			mongoTemplate.findAndModify(sQuery, sUpdate, NewListInformationXML.class);
		}

	}

}
