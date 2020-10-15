package com.example.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.model.NewListInformationXML;
import com.example.news.model.NewsResponse;
import com.example.news.service.NewsService;

@RestController
@RequestMapping("/")
public class NewsController {

	@Autowired
	NewsService newsService;

	@GetMapping(value = "/")
	private ResponseEntity<NewsResponse> getAllNews() {

		List<NewListInformationXML> newsList = newsService.findAll();

		if (newsList.isEmpty()) {
			NewsResponse response = new NewsResponse("Error", "No news in database");
			return new ResponseEntity<NewsResponse>(response, HttpStatus.OK);
		}

		NewsResponse response = new NewsResponse("Success", countItems(newsList), newsList);
		return new ResponseEntity<NewsResponse>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{clubName}")
	private ResponseEntity<NewsResponse> getClubNews(@PathVariable("clubName") String clubName) {

		clubName = StringUtils.capitalize(clubName);

		if (newsService.existsByClubName(clubName)) {
			NewListInformationXML clubNews = newsService.findByClubName(clubName);
			NewsResponse response = new NewsResponse("Success", clubNews.getNewsletterNewsItems().size(), clubNews);
			return new ResponseEntity<NewsResponse>(response, HttpStatus.OK);
		}

		NewsResponse response = new NewsResponse("Error", "Team not found");
		return new ResponseEntity<NewsResponse>(response, HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{clubName}/{newsArticleID}")
	private ResponseEntity<NewsResponse> getSingleNews(@PathVariable("clubName") String clubName,
			@PathVariable("newsArticleID") int newsArticleID) {
		clubName = StringUtils.capitalize(clubName);

		List<NewListInformationXML> clubNews = newsService.findByNewsletterNewsItemsNewsArticleID(clubName,
				newsArticleID);

		if (clubNews.isEmpty()) {
			NewsResponse info = new NewsResponse("Error", "Team not found");
			return new ResponseEntity<NewsResponse>(info, HttpStatus.NOT_FOUND);

		} else if (clubNews.get(0).getNewsletterNewsItems() != null) {
			NewsResponse info = new NewsResponse("Success", countItems(clubNews), clubNews);
			return new ResponseEntity<NewsResponse>(info, HttpStatus.OK);
		}

		NewsResponse info = new NewsResponse("Error", "News not found");
		return new ResponseEntity<NewsResponse>(info, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = ("/{clubName}"))
	private ResponseEntity<NewsResponse> deleteNews(@PathVariable("clubName") String clubName) {

		clubName = StringUtils.capitalize(clubName);

		if (newsService.existsByClubName(clubName)) {
			newsService.deleteByClubName(clubName);
			NewsResponse info = new NewsResponse("Success", "Team deleted");
			return new ResponseEntity<NewsResponse>(info, HttpStatus.OK);
		}

		NewsResponse info = new NewsResponse("Error", "Team not found");
		return new ResponseEntity<NewsResponse>(info, HttpStatus.NOT_FOUND);

	}

	private int countItems(List<NewListInformationXML> newsLists) {

		int count = 0;
		for (final NewListInformationXML newsList : newsLists) {
			count += newsList.getNewsletterNewsItems().size();
		}
		return count;
	}
}
